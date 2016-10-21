/*     */ package com.gsoft.framework.core.web.controller;
/*     */ 
/*     */ import com.gsoft.framework.core.bind.DomainDataBinder;
/*     */ import com.gsoft.framework.core.dataobj.Domain;
/*     */ import com.gsoft.framework.core.exception.DomainValidatorException;
/*     */ import com.gsoft.framework.core.orm.Condition;
/*     */ import com.gsoft.framework.core.orm.Order;
/*     */ import com.gsoft.framework.core.orm.Pager;
/*     */ import com.gsoft.framework.core.web.annotation.DataInDesc;
/*     */ import com.gsoft.framework.core.web.annotation.Filter;
/*     */ import com.gsoft.framework.util.ConditionUtils;
/*     */ import com.gsoft.framework.util.StringUtils;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.FileItemFactory;
/*     */ import org.apache.commons.fileupload.FileUploadException;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.hibernate.validator.ClassValidator;
/*     */ import org.hibernate.validator.InvalidValue;
/*     */ import org.springframework.beans.BeanUtils;
/*     */ import org.springframework.beans.MutablePropertyValues;
/*     */ import org.springframework.beans.PropertyValues;
/*     */ import org.springframework.validation.BindingResult;
/*     */ 
/*     */ public class RequestDataIn<T extends Domain>
/*     */   implements DataIn<T>
/*     */ {
/*  73 */   private static final Log logger = LogFactory.getLog(RequestDataIn.class);
/*     */   public static final String PREFIX_PROPERTY = "";
/*     */   public static final String PREFIX_ARRAY_PROPERTY = "list:";
/*     */   public static final String PREFIX_ORDERBY_DESC = "desc:";
/*     */   public static final String PREFIX_ORDERBY_ASC = "asc:";
/*     */   public static final String PREFIX_OPERATOR = "operator:";
/*     */   private HttpServletRequest webRequest;
/*     */   private DataInDesc dataInDesc;
/*     */   private Map<String, String[]> parameterMap;
/*     */   private Map<String, FileItem> uploadFiles;
/*     */ 
/*     */   public RequestDataIn()
/*     */   {
/*     */   }
/*     */ 
/*     */   public RequestDataIn(HttpServletRequest webRequest, DataInDesc dataInDesc)
/*     */   {
/* 107 */     this.webRequest = webRequest;
/* 108 */     this.dataInDesc = dataInDesc;
/*     */ 
/* 110 */     this.uploadFiles = new HashMap();
/* 111 */     parseParameterMapFromRequest();
/*     */   }
/*     */ 
/*     */   private void parseParameterMapFromRequest()
/*     */   {
/* 119 */     if (ServletFileUpload.isMultipartContent(this.webRequest))
/* 120 */       this.parameterMap = parseParameterMapFromMultipartRequest();
/*     */     else
/* 122 */       this.parameterMap = this.webRequest.getParameterMap();
/*     */   }
/*     */ 
/*     */   private Map<String, String[]> parseParameterMapFromMultipartRequest()
/*     */   {
/* 133 */     Map parameterMap = new HashMap();
/*     */ 
/* 135 */     FileItemFactory factory = new DiskFileItemFactory();
/*     */ 
/* 137 */     ServletFileUpload upload = new ServletFileUpload(factory);
/*     */     try
/*     */     {
/* 142 */       List<FileItem> items = upload.parseRequest(this.webRequest);
/* 143 */       for (FileItem diskFile : items) {
/* 144 */         String parameterName = diskFile.getFieldName();
/*     */         try {
/* 146 */           Integer.parseInt(parameterName);
/*     */         }
/*     */         catch (NumberFormatException numExc)
/*     */         {
/* 151 */           if (parameterName != null);
/* 152 */           if (diskFile.isFormField()) {
/* 153 */             List values = new ArrayList();
/* 154 */             if (parameterMap.containsKey(parameterName)) {
/* 155 */               values.addAll(Arrays.asList((Object[])parameterMap.get(parameterName)));
/*     */             }
/*     */ 
/* 158 */             String value = diskFile.getString("UTF-8");
/* 159 */             if (StringUtils.isNotEmpty(value)) {
/* 160 */               values.add(value);
/*     */             }
/*     */ 
/* 163 */             parameterMap.put(parameterName, values.toArray(new String[values.size()]));
/*     */           } else {
/* 165 */             this.uploadFiles.put(parameterName, diskFile);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (FileUploadException e) {
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/*     */     }
/* 173 */     return parameterMap;
/*     */   }
/*     */ 
/*     */   public Collection<Condition> getConditions(Domain bean, BindingResult result)
/*     */   {
/* 181 */     Map filterMap = annotionedFilterMap();
/*     */ 
/* 185 */     for (Map.Entry entry : this.parameterMap.entrySet())
/*     */     {
/* 187 */       String operatorProperty = (String)entry.getKey();
/* 188 */       if ((operatorProperty.startsWith("operator:")) && (!operatorProperty.equals("operator:")))
/*     */       {
/* 190 */         String[] values = (String[])this.parameterMap.get(operatorProperty);
/* 191 */         if ((values != null) && (values.length > 0)) {
/* 192 */           filterMap.put(operatorProperty.substring("operator:".length()), values[0]);
/*     */         }
/*     */       }
/* 195 */       operatorProperty = null;
/*     */     }
/*     */ 
/* 198 */     Collection conditions = ConditionUtils.getConditions(null, bean, result, filterMap, this.parameterMap);
/*     */ 
/* 200 */     if (this.dataInDesc == null) return conditions;
/*     */ 
/* 219 */     return conditions;
/*     */   }
/*     */ 
/*     */   private Map<String, String> annotionedFilterMap()
/*     */   {
/* 225 */     Map filterMap = new HashMap();
/* 226 */     if (this.dataInDesc == null) return filterMap;
/* 227 */     Filter[] filters = this.dataInDesc.filters();
/* 228 */     if (filters != null) {
/* 229 */       for (Filter filter : filters) {
/* 230 */         filterMap.put(filter.name(), filter.operator());
/*     */       }
/*     */     }
/* 233 */     return filterMap;
/*     */   }
/*     */ 
/*     */   public T getDomain(T bean, BindingResult result)
/*     */   {
/* 242 */     if (ServletFileUpload.isMultipartContent(this.webRequest))
/*     */     {
/* 244 */       DomainDataBinder dataBinder = new DomainDataBinder(bean);
/* 245 */       PropertyValues pvs = new MutablePropertyValues(this.parameterMap);
/* 246 */       dataBinder.bind(pvs);
/*     */ 
/* 248 */       BeanUtils.copyProperties(dataBinder.getTarget(), bean);
/*     */     }
/*     */ 
/* 252 */     ClassValidator domainValidator = new ClassValidator(bean.getClass());
/* 253 */     InvalidValue[] invalidValues = domainValidator.getInvalidValues(bean);
/*     */ 
/* 255 */     if (invalidValues.length > 0) {
/* 256 */       throw new DomainValidatorException(invalidValues);
/*     */     }
/*     */ 
/* 259 */     if ((this.dataInDesc == null) || 
/* 275 */       (this.uploadFiles.size() > 0));
/* 278 */     return bean;
/*     */   }
/*     */ 
/*     */   public Pager getPager()
/*     */   {
/* 285 */     String pageSize = getParameterValue("pager:pageSize");
/* 286 */     String pageIndex = getParameterValue("pager:pageIndex");
/* 287 */     String pageType = getParameterValue("pager:pageType");
/* 288 */     String pageExport = getParameterValue("pager:export");
/* 289 */     String[] pagerProperties = getParameterValues("pager:property");
/* 290 */     Pager pager = new Pager(pageSize, pageIndex, pageType);
/*     */ 
/* 292 */     pager.setPagerProperties(pagerProperties);
/*     */ 
/* 294 */     if ((pageExport != null) && (((pageExport.equals("xls")) || (pageExport.equals("pdf")) || (pageExport.equals("print")))))
/*     */     {
/* 298 */       pager.setExport(pageExport);
/*     */ 
/* 300 */       String[] pageHeaders = getParameterValues("pager:header");
/* 301 */       pager.setExportHeaders(pageHeaders);
/*     */ 
/* 303 */       pager.setExportProperties(getParameterValues("pager:property"));
/* 304 */       pager.setExportConverts(getParameterValues("pager:convert"));
/*     */     }
/* 306 */     return pager;
/*     */   }
/*     */ 
/*     */   public Collection<Order> getOrders()
/*     */   {
/* 313 */     Collection orders = new ArrayList();
/* 314 */     String[] orderBys = getParameterValues("orderBy");
/*     */ 
/* 316 */     if (orderBys != null) {
/* 317 */       for (String propertyName : orderBys) {
/* 318 */         if (propertyName.startsWith("asc:"))
/* 319 */           orders.add(ConditionUtils.getOrder(propertyName.substring("asc:".length()), true));
/* 320 */         else if (propertyName.startsWith("desc:")) {
/* 321 */           orders.add(ConditionUtils.getOrder(propertyName.substring("desc:".length()), false));
/*     */         }
/*     */       }
/*     */     }
/* 325 */     return orders;
/*     */   }
/*     */ 
/*     */   public String getParameterValue(String name)
/*     */   {
/* 333 */     String[] values = getParameterValues(name);
/* 334 */     return ((values != null) && (values.length > 0)) ? values[0] : null;
/*     */   }
/*     */ 
/*     */   public String[] getParameterValues(String name)
/*     */   {
/* 342 */     return (String[])this.parameterMap.get(name);
/*     */   }
/*     */ 
/*     */   public String[] getPropertyValues(String propertyName)
/*     */   {
/* 349 */     return getParameterValues("" + propertyName);
/*     */   }
/*     */ 
/*     */   public String getPropertyValue(String propertyName)
/*     */   {
/* 356 */     String[] values = getPropertyValues(propertyName);
/* 357 */     return ((values != null) && (values.length > 0)) ? values[0].trim() : null;
/*     */   }
/*     */ 
/*     */   public byte[] getByteProperty(String propertyName) {
/* 361 */     if ((this.uploadFiles != null) && (this.uploadFiles.containsKey(propertyName))) {
/* 362 */       return ((FileItem)this.uploadFiles.get(propertyName)).get();
/*     */     }
/* 364 */     return null;
/*     */   }
/*     */ 
/*     */   public OutputStream getOutputStreamProperty(String propertyName) {
/* 368 */     if ((this.uploadFiles != null) && (this.uploadFiles.containsKey(propertyName))) {
/*     */       try {
/* 370 */         return ((FileItem)this.uploadFiles.get(propertyName)).getOutputStream();
/*     */       } catch (IOException e) {
/* 372 */         logger.info("" + e.getMessage());
/*     */       }
/*     */     }
/* 375 */     return null;
/*     */   }
/*     */ 
/*     */   public InputStream getInputStreamProperty(String propertyName) {
/* 379 */     if ((this.uploadFiles != null) && (this.uploadFiles.containsKey(propertyName))) {
/*     */       try {
/* 381 */         return ((FileItem)this.uploadFiles.get(propertyName)).getInputStream();
/*     */       } catch (IOException e) {
/* 383 */         logger.info("" + e.getMessage());
/*     */       }
/*     */     }
/* 386 */     return null;
/*     */   }
/*     */ 
/*     */   public InputStreamReader getInputStreamReaderProperty(String propertyName) {
/* 390 */     if ((this.uploadFiles != null) && (this.uploadFiles.containsKey(propertyName))) {
/*     */       try {
/* 392 */         FileItem item = (FileItem)this.uploadFiles.get(propertyName);
/* 393 */         InputStream in = item.getInputStream();
/* 394 */         String charSet = item.getContentType();
/* 395 */         if (charSet == null)
/*     */         {
/* 400 */           charSet = "GBK";
/*     */         }
/*     */ 
/* 403 */         logger.info("上传文件编码:" + charSet);
/* 404 */         return new InputStreamReader(in, charSet);
/*     */       } catch (IOException e) {
/* 406 */         logger.warn("文件上传警告：" + e.getMessage());
/*     */       } catch (Exception e) {
/* 408 */         e.printStackTrace();
/* 409 */         logger.warn("文件上传警告：" + e.getMessage());
/*     */       }
/*     */     }
/* 412 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.RequestDataIn
 * JD-Core Version:    0.5.4
 */