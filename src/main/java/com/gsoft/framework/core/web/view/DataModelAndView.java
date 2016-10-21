/*     */ package com.gsoft.framework.core.web.view;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.Domain;
/*     */ import com.gsoft.framework.core.orm.Pager;
/*     */ import com.gsoft.framework.core.orm.PagerRecords;
/*     */ import com.gsoft.framework.util.PojoMapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ 
/*     */ public class DataModelAndView extends BaseModelAndView
/*     */ {
/*     */   private static final String MODELANDVIEW_PATH = "data/json";
/*     */ 
/*     */   public DataModelAndView(String html)
/*     */   {
/*  42 */     super("data/json");
/*  43 */     Map record = new HashMap();
/*  44 */     record.put("html", html);
/*  45 */     output2Json(record);
/*     */   }
/*     */ 
/*     */   public DataModelAndView(Domain domain)
/*     */   {
/*  52 */     super("data/json");
/*  53 */     output2Json(domain);
/*     */   }
/*     */ 
/*     */   public DataModelAndView(Domain domain, boolean ignoreColl)
/*     */   {
/*  60 */     super("data/json");
/*  61 */     output2Json(domain);
/*     */   }
/*     */ 
/*     */   public DataModelAndView(Domain domain, String[] notIgnoreColls)
/*     */   {
/*  74 */     super("data/json");
/*     */     try
/*     */     {
/*  77 */       for (String notIgnoreColl : notIgnoreColls) {
/*  78 */         Object value = PropertyUtils.getSimpleProperty(domain, notIgnoreColl);
/*  79 */         if (value instanceof Collection)
/*  80 */           ((Collection)value).size();
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*  86 */     output2Json(domain);
/*     */   }
/*     */ 
/*     */   public DataModelAndView(List<? extends Domain> list)
/*     */   {
/*  96 */     super("data/json");
/*  97 */     if (list == null) {
/*  98 */       list = new ArrayList();
/*     */     }
/* 100 */     Map result = new HashMap();
/* 101 */     result.put("totalCount", Integer.valueOf(list.size()));
/* 102 */     result.put("records", list);
/*     */ 
/* 104 */     addObject("json", PojoMapper.toJson(result, false));
/*     */   }
/*     */ 
/*     */   public DataModelAndView(Object[] records)
/*     */   {
/* 111 */     super("data/json");
/* 112 */     addObject("json", PojoMapper.toJson(records, false));
/*     */   }
/*     */ 
/*     */   public DataModelAndView(PagerRecords pagerRecords, String[] ignoreFields)
/*     */   {
/* 119 */     super("data/json");
/* 120 */     Pager pager = pagerRecords.getPager();
/* 121 */     List records = pagerRecords.getRecords();
/* 122 */     if (pager.getExport() != null) {
/* 123 */       addObject("pagerRecords", pagerRecords);
/*     */ 
/* 125 */       setViewName("forward:/webexport/" + pager.getExport() + ".html");
/*     */     } else {
/* 127 */       Map result = new HashMap();
/* 128 */       result.put("totalCount", Integer.valueOf(pagerRecords.getTotalCount()));
/* 129 */       result.put("records", records);
/* 130 */       addObject("json", PojoMapper.toJson(result, false));
/*     */     }
/*     */   }
/*     */ 
/*     */   public DataModelAndView(PagerRecords pagerRecords) {
/* 135 */     this(pagerRecords, null);
/*     */   }
/*     */ 
/*     */   public DataModelAndView(Message message)
/*     */   {
/* 143 */     super("data/json");
/* 144 */     Map result = new HashMap();
/* 145 */     result.put("message", message);
/* 146 */     addObject("json", PojoMapper.toJson(result, false));
/*     */   }
/*     */ 
/*     */   public DataModelAndView(String[] ids) {
/* 150 */     super("data/json");
/* 151 */     Map result = new HashMap();
/* 152 */     result.put("ids", ids);
/* 153 */     addObject("json", PojoMapper.toJson(result, false));
/*     */   }
/*     */ 
/*     */   private void output2Json(Object record)
/*     */   {
/* 160 */     String jsonStr = "";
/* 161 */     if (record == null) record = "";
/* 162 */     if (Domain.class.isAssignableFrom(record.getClass()))
/* 163 */       jsonStr = PojoMapper.domainToJson((Domain)record);
/*     */     else {
/* 165 */       jsonStr = PojoMapper.toJson(record, false);
/*     */     }
/* 167 */     StringBuffer jsonBuf = new StringBuffer();
/* 168 */     jsonBuf.append("{\"record\":").append(jsonStr).append("}");
/* 169 */     addObject("json", jsonBuf.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.DataModelAndView
 * JD-Core Version:    0.5.4
 */