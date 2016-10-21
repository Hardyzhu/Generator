/*     */ package com.gsoft.platform.codegen.domain;
/*     */ 
/*     */ import com.gsoft.platform.codegen.AbstractJavaDataModel;
/*     */ import com.gsoft.platform.codegen.model.ForeignAttribute;
/*     */ import com.gsoft.platform.codegen.model.Model;
/*     */ import com.gsoft.platform.codegen.model.NormalAttribute;
/*     */ import com.gsoft.platform.codegen.model.SetAttribute;
/*     */ import com.gsoft.platform.codegen.util.GenerateUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DomainData extends AbstractJavaDataModel
/*     */ {
/*     */   private Model model;
/*     */ 
/*     */   public DomainData(Model model)
/*     */   {
/*  30 */     this.model = model;
/*  31 */     setDescription(model.getCaption());
/*     */   }
/*     */ 
/*     */   public Model getModel() {
/*  35 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(Model model) {
/*  39 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getData() {
/*  43 */     Map root = new HashMap();
/*  44 */     root.put("modelName", getModel().getName());
/*  45 */     root.put("moduleName", getModel().getModuleName());
/*     */ 
/*  47 */     root.put("package", getPackageName());
/*  48 */     root.put("className", getClassName());
/*  49 */     root.put("tableName", this.model.getTable());
/*  50 */     root.put("modelDescription", this.model.getCaption());
/*  51 */     root.put("toString", this.model.getToString());
/*  52 */     root.put("attributes", getAttributesMapList());
/*  53 */     root.put("foreignAttributes", getForeignAttributesMapList());
/*  54 */     root.put("setAttributes", getSetAttributesMapList());
/*  55 */     root.put("supportWorkflow", Boolean.valueOf(this.model.isSupportWorkflow()));
/*  56 */     root.put("idAttribute", getIdAttribute());
/*     */ 
/*  58 */     return root;
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> getSetAttributesMapList()
/*     */   {
/*  63 */     List setAttributes = new ArrayList();
/*  64 */     Set<SetAttribute> setAttributeSet = new HashSet();
/*  65 */     if (this.model.getSetAttributes() != null) {
/*  66 */       setAttributeSet.addAll(this.model.getSetAttributes());
/*     */     }
/*  68 */     int i = 0;
/*  69 */     for (SetAttribute setAttribute : setAttributeSet) {
/*  70 */       ++i;
/*  71 */       Map setAttributeMap = setAttribute.toMap();
/*  72 */       setAttributes.add(setAttributeMap);
/*  73 */       if (i == setAttributeSet.size())
/*  74 */         setAttributeMap.put("isLast", Boolean.valueOf(true));
/*     */       else {
/*  76 */         setAttributeMap.put("isLast", Boolean.valueOf(false));
/*     */       }
/*     */     }
/*  79 */     return setAttributes;
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> getForeignAttributesMapList()
/*     */   {
/*  88 */     List foreignAttributes = new ArrayList();
/*  89 */     Set<ForeignAttribute> foreignAttributeSet = new HashSet();
/*  90 */     foreignAttributeSet.addAll(this.model.getForeignAttributes());
/*  91 */     int i = 0;
/*  92 */     for (ForeignAttribute foreignAttribute : foreignAttributeSet) {
/*  93 */       ++i;
/*  94 */       Map foreignAttributeMap = foreignAttribute.toMap();
/*  95 */       foreignAttributeMap.put("type", 
/*  96 */         GenerateUtil.getRefModelClassName(foreignAttribute.getRefModel(), this.model, this.packageName));
/*  97 */       if (i == foreignAttributeSet.size())
/*  98 */         foreignAttributeMap.put("isLast", Boolean.valueOf(true));
/*     */       else {
/* 100 */         foreignAttributeMap.put("isLast", Boolean.valueOf(false));
/*     */       }
/* 102 */       foreignAttributes.add(foreignAttributeMap);
/*     */     }
/*     */ 
/* 105 */     return foreignAttributes;
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> getAttributesMapList()
/*     */   {
/* 114 */     List attributes = new ArrayList();
/* 115 */     Set<NormalAttribute> attributeSet = new HashSet();
/*     */ 
/* 117 */     attributeSet.addAll(this.model.getAttributes());
/* 118 */     int i = 0;
/* 119 */     for (NormalAttribute attribute : attributeSet) {
/* 120 */       ++i;
/* 121 */       Map attributeMap = attribute.toMap();
/* 122 */       if (i == attributeSet.size())
/* 123 */         attributeMap.put("isLast", Boolean.valueOf(true));
/*     */       else {
/* 125 */         attributeMap.put("isLast", Boolean.valueOf(false));
/*     */       }
/* 127 */       attributes.add(attributeMap);
/* 128 */       attributeMap = null;
/*     */     }
/* 130 */     return attributes;
/*     */   }
/*     */ 
/*     */   public Object getIdAttributes()
/*     */   {
/* 138 */     Set<NormalAttribute> attributes = new HashSet();
/* 139 */     attributes.addAll(this.model.getAttributes());
/* 140 */     StringBuffer buf = new StringBuffer();
/* 141 */     for (NormalAttribute normalAttribute : attributes) {
/* 142 */       if (normalAttribute.getIsId().equals("true")) {
/* 143 */         buf.append(",");
/* 144 */         buf.append(normalAttribute.getName());
/*     */       }
/*     */     }
/* 147 */     return buf.substring(1);
/*     */   }
/*     */ 
/*     */   public NormalAttribute getIdAttribute() {
/* 151 */     Set<NormalAttribute> attributes = new HashSet();
/* 152 */     attributes.addAll(this.model.getAttributes());
/* 153 */     for (NormalAttribute normalAttribute : attributes) {
/* 154 */       if (normalAttribute.getIsId().equals("true")) {
/* 155 */         return normalAttribute;
/*     */       }
/*     */     }
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 162 */     return this.model.getName();
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.domain.DomainData
 * JD-Core Version:    0.5.4
 */