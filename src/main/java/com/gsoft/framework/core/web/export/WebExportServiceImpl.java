/*     */ package com.gsoft.framework.core.web.export;
/*     */ 
///*     */ import com.gsoft.framework.core.convert.IConvert;
///*     */ import com.gsoft.framework.core.convert.IConvertProviderFactory;
/*     */ import com.gsoft.framework.core.orm.Pager;
/*     */ import com.gsoft.framework.core.orm.PagerRecords;
/*     */ import com.gsoft.framework.util.PropertyUtils;
/*     */ import com.gsoft.framework.util.StringUtils;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.BeanFactoryUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
/*     */ import org.springframework.core.OrderComparator;
/*     */ import org.springframework.http.HttpHeaders;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public class WebExportServiceImpl
/*     */   implements WebExportService, ApplicationContextAware
/*     */ {
/*     */ 
/*     */   @Autowired(required=false)
///*     */   private IConvertProviderFactory convertProviderFactory;
/*     */   private List<WebExportAdapter> webExportAdapters;
/*     */ 
/*     */   public HttpHeaders writePagerRecords(OutputStream outputStream, String type, PagerRecords pagerRecords)
/*     */   {
/*  46 */     WebExportAdapter webExportAdapter = null;
/*  47 */     if (this.webExportAdapters != null)
/*     */     {
/*  49 */       for (WebExportAdapter adapter : this.webExportAdapters) {
/*  50 */         if (adapter.supports(type)) {
/*  51 */           webExportAdapter = adapter;
/*  52 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  57 */     if (webExportAdapter != null) { List cols = parseColModels(pagerRecords.getPager());
/*     */ 
/*  60 */       WebExporter webExport = null;
/*     */       int index;
/*     */       Iterator i$;
/*     */       try { webExport = webExportAdapter.openExporter(outputStream);
/*     */ 
/*  64 */         webExport.writeLine(0, pagerRecords.getPager().getExportHeaders());
/*     */ 
/*  66 */         List records = pagerRecords.getRecords();
/*     */ 
/*  68 */         index = 1;
/*  69 */         for (i$ = records.iterator(); i$.hasNext(); ) { Object record = i$.next();
/*  70 */           webExport.writeLine(index, parseRowData(cols, record));
/*  71 */           ++index; }
/*     */       } catch (Exception e)
/*     */       {
/*  74 */         e.printStackTrace();
/*     */       } finally {
/*  76 */         if (webExport != null) {
/*  77 */           webExport.close();
/*     */         }
/*     */       }
/*     */ 
/*  81 */       return webExport.getHttpHeaders(); }
/*     */ 
/*     */ 
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */   private Object[] parseRowData(List<ExportCol> cols, Object record) {
/*  88 */     List values = new ArrayList();
/*  89 */     for (ExportCol col : cols) {
/*  90 */       Object value = PropertyUtils.getPropertyValue(record, col.getName());
/*  91 */       values.add((value == null) ? "" : convertValue(col, value));
/*     */     }
/*     */ 
/*  94 */     return values.toArray(new Object[values.size()]);
/*     */   }
/*     */ 
/*     */   private String convertValue(ExportCol col, Object value)
/*     */   {
///* 104 */     if ((this.convertProviderFactory != null) && (StringUtils.isNotEmpty(col.getConvert()))) {
///* 105 */       IConvert convert = this.convertProviderFactory.getConvert(col.getConvert(), null);
///* 106 */       if (convert != null) {
///* 107 */         Object convertValue = convert.get(value.toString());
///* 108 */         if (convertValue != null) {
///* 109 */           return convertValue.toString();
///*     */         }
///*     */       }
///*     */     }
/* 113 */     return value.toString();
/*     */   }
/*     */ 
/*     */   private List<ExportCol> parseColModels(Pager pager)
/*     */   {
/* 118 */     List exportCols = new ArrayList();
/* 119 */     int i = 0;
/* 120 */     for (String property : pager.getExportProperties()) {
/* 121 */       exportCols.add(createExportCol(property, pager.getExportHeaders()[i], (pager.getExportConverts() != null) ? pager.getExportConverts()[i] : null));
/*     */ 
/* 123 */       ++i;
/*     */     }
/*     */ 
/* 126 */     return exportCols;
/*     */   }
/*     */ 
/*     */   private ExportCol createExportCol(String property, String caption, String convert)
/*     */   {
/* 131 */     ExportCol exportCol = new ExportCol();
/* 132 */     exportCol.setName(property);
/* 133 */     exportCol.setCaption(caption);
/* 134 */     if (!"noConvert".equals(convert)) {
/* 135 */       exportCol.setConvert(convert);
/*     */     }
/* 137 */     return exportCol;
/*     */   }
/*     */ 
/*     */   public void setApplicationContext(ApplicationContext applicationContext)
/*     */     throws BeansException
/*     */   {
/* 143 */     if (this.webExportAdapters == null)
/* 144 */       initWebExportAdapters(applicationContext);
/*     */   }
/*     */ 
/*     */   private void initWebExportAdapters(ApplicationContext applicationContext)
/*     */   {
/* 154 */     Map matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, WebExportAdapter.class, true, false);
/*     */ 
/* 157 */     if (!matchingBeans.isEmpty()) {
/* 158 */       this.webExportAdapters = new ArrayList(matchingBeans.values());
/*     */ 
/* 161 */       OrderComparator.sort(this.webExportAdapters);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.WebExportServiceImpl
 * JD-Core Version:    0.5.4
 */