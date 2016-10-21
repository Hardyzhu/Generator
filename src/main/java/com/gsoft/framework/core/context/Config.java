/*     */ package com.gsoft.framework.core.context;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Config
/*     */ {
/*  22 */   private static Config instance = null;
/*     */   public static final String CONIFG_PAGE_SIZE = "pager.pageSize";
/*     */   public static final String CONIFG_LAYOUT_DEFAULT = "layout.decorator";
/*     */   public static final String CONIFG_MENU_HTML_CLASS = "menu.html.class";
/*     */   public static final String URL_DATA_POSTFIX = ".json";
/*     */   private Properties properties;
/*     */   private Properties decorators;
/*  36 */   private String defaultLayout = "default";
/*     */ 
/*     */   public Properties getProperties()
/*     */   {
/*  45 */     return this.properties;
/*     */   }
/*     */ 
/*     */   public static Config getInstance() {
/*  49 */     if (instance == null) {
/*  50 */       instance = new Config();
/*     */     }
/*  52 */     return instance;
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties properties)
/*     */   {
/*  59 */     this.properties = properties;
/*     */   }
/*     */ 
/*     */   public Properties getDecorators()
/*     */   {
/*  66 */     return this.decorators;
/*     */   }
/*     */ 
/*     */   public void setDecorators(Properties decorators)
/*     */   {
/*  73 */     this.decorators = decorators;
/*     */   }
/*     */ 
/*     */   public String getDefaultLayout()
/*     */   {
/*  80 */     return this.defaultLayout;
/*     */   }
/*     */ 
/*     */   public void setDefaultLayout(String defaultLayout)
/*     */   {
/*  86 */     this.defaultLayout = defaultLayout;
/*     */   }
/*     */ 
/*     */   public String getProperty(String name)
/*     */   {
/*  94 */     if (this.properties == null) return null;
/*  95 */     return this.properties.getProperty(name);
/*     */   }
/*     */ 
/*     */   public String getDecoratorsHtml()
/*     */   {
/* 102 */     if (this.decorators == null) return "";
/* 103 */     StringBuffer htmls = new StringBuffer();
/* 104 */     htmls.append("<div class=\"system-decorators\">");
/*     */ 
/* 106 */     Set keys = this.decorators.keySet();
/* 107 */     for (Iterator i$ = keys.iterator(); i$.hasNext(); ) { Object key = i$.next();
/* 108 */       htmls.append("<div class=\"decorator-").append(key).append("\"").append(" onclick=\"changeDecorator('").append(key).append("')\"").append(">").append(this.decorators.get(key)).append("</div>"); }
/*     */ 
/*     */ 
/* 116 */     htmls.append("</div>");
/* 117 */     return htmls.toString();
/*     */   }
/*     */ 
/*     */   static Config init() {
/* 121 */     instance = new Config();
/* 122 */     return instance;
/*     */   }
/*     */   public int getIntProperty(String name) {
/* 125 */     String value = this.properties.getProperty(name);
/* 126 */     return Integer.parseInt(value);
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.context.Config
 * JD-Core Version:    0.5.4
 */