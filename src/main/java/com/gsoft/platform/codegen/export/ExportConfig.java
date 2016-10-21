/*     */ package com.gsoft.platform.codegen.export;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.annotation.Attribute;
/*     */ import com.gsoft.framework.util.PropertyUtils;
/*     */ import com.gsoft.framework.util.ReflectionUtils;
/*     */ import com.gsoft.framework.util.StringUtils;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ExportConfig extends HashMap<String, Object>
/*     */ {
/*     */   private static final long serialVersionUID = 7642725241448362001L;
/*     */ 
/*     */   @Attribute(caption = "", defaultvalue = "", description = "", fieldType = "", notNull = false, rtexprvalue = false, value = "")
/*     */   private String projectName;
/*     */ 
/*     */   @Attribute(caption = "", defaultvalue = "", description = "", fieldType = "", notNull = false, rtexprvalue = false, value = "")
/*     */   private String projectNamePrefix;
/*     */ 
/*     */   @Attribute(caption = "", defaultvalue = "", description = "", fieldType = "", notNull = false, rtexprvalue = false, value = "")
/*     */   private String groupId;
/*     */ 
/*     */   @Attribute(caption = "", defaultvalue = "", description = "", fieldType = "", notNull = false, rtexprvalue = false, value = "")
/*     */   private String projectCaption;
/*     */ 
/*     */   @Attribute(caption = "", defaultvalue = "", description = "", fieldType = "", notNull = false, rtexprvalue = false, value = "")
/*  34 */   private String projectVersion = "1.0.0";
/*     */   private TemplateProcessor templateProcessor;
/*     */ 
/*     */   public ExportConfig(String projectName)
/*     */   {
/*  43 */     this.projectName = projectName;
/*     */   }
/*     */ 
/*     */   public ExportConfig(String projectName, Map<String, Object> options)
/*     */   {
/*  50 */     if (options != null) {
/*  51 */       putAll(options);
/*     */     }
/*     */ 
/*  54 */     injectOptions(options);
/*  55 */     this.projectName = projectName;
/*     */   }
/*     */ 
/*     */   private void injectOptions(Map<String, Object> options)
/*     */   {
/*  63 */     List<Field> fields = ReflectionUtils.annotationedField(super.getClass(), Attribute.class);
/*     */ 
/*  66 */     for (Field field : fields) {
/*  67 */       String fieldName = field.getName();
/*  68 */       if ((fieldName != null) && (options.containsKey(fieldName))) {
/*  69 */         PropertyUtils.setSimplePropertyValue(this, fieldName, options.get(fieldName));
/*     */       }
/*  71 */       fieldName = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProjectName()
/*     */   {
/*  79 */     if (StringUtils.isNotEmpty(this.projectNamePrefix)) {
/*  80 */       return this.projectNamePrefix + "-" + this.projectName;
/*     */     }
/*  82 */     return this.projectName;
/*     */   }
/*     */ 
/*     */   public void setProjectName(String projectName) {
/*  86 */     this.projectName = projectName;
/*     */   }
/*     */ 
/*     */   public String getProjectCaption() {
/*  90 */     return this.projectCaption;
/*     */   }
/*     */ 
/*     */   public void setProjectCaption(String projectCaption) {
/*  94 */     this.projectCaption = projectCaption;
/*     */   }
/*     */ 
/*     */   public String getProjectVersion() {
/*  98 */     return this.projectVersion;
/*     */   }
/*     */ 
/*     */   public void setProjectVersion(String projectVersion) {
/* 102 */     this.projectVersion = projectVersion;
/*     */   }
/*     */ 
/*     */   public String getGroupId() {
/* 106 */     return this.groupId;
/*     */   }
/*     */ 
/*     */   public void setGroupId(String groupId) {
/* 110 */     this.groupId = groupId;
/*     */   }
/*     */ 
/*     */   public void setProjectNamePrefix(String projectNamePrefix) {
/* 114 */     this.projectNamePrefix = projectNamePrefix;
/*     */   }
/*     */ 
/*     */   public String getProjectNamePrefix() {
/* 118 */     return this.projectNamePrefix;
/*     */   }
/*     */ 
/*     */   public TemplateProcessor getTemplateProcessor() {
/* 122 */     return this.templateProcessor;
/*     */   }
/*     */ 
/*     */   public void setTemplateProcessor(TemplateProcessor templateProcessor) {
/* 126 */     this.templateProcessor = templateProcessor;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.export.ExportConfig
 * JD-Core Version:    0.5.4
 */