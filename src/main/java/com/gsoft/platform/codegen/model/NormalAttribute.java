/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class NormalAttribute extends AbstractAttribute
/*     */ {
/*     */   private static final long serialVersionUID = -8480517526082121483L;
/*     */   private String type;
/*     */   private String column;
/*     */   private String isId;
/*     */   private String length;
/*     */   private String min;
/*     */   private String notNull;
/*     */   private String filterOperator;
/*  28 */   private String unique = "false";
/*     */ 
/*     */   public String getUnique() {
/*  31 */     return this.unique;
/*     */   }
/*     */ 
/*     */   public void setUnique(String unique) {
/*  35 */     this.unique = unique;
/*     */   }
/*     */ 
/*     */   public NormalAttribute()
/*     */   {
/*     */   }
/*     */ 
/*     */   public NormalAttribute(String type, String name, String column, String isId, String length, String min, String caption, String notNull)
/*     */   {
/*  54 */     this.type = type;
/*  55 */     this.name = name;
/*  56 */     this.column = column;
/*  57 */     this.isId = isId;
/*  58 */     this.length = length;
/*  59 */     this.min = min;
/*  60 */     this.caption = caption;
/*  61 */     this.notNull = notNull;
/*     */   }
/*     */ 
/*     */   public String getType() {
/*  65 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  69 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getColumn() {
/*  73 */     return this.column;
/*     */   }
/*     */ 
/*     */   public void setColumn(String column) {
/*  77 */     this.column = column;
/*     */   }
/*     */ 
/*     */   public String getIsId() {
/*  81 */     if ((this.isId == null) || (this.isId.equals(""))) {
/*  82 */       this.isId = "false";
/*     */     }
/*  84 */     return this.isId;
/*     */   }
/*     */ 
/*     */   public void setIsId(String isId) {
/*  88 */     this.isId = isId;
/*     */   }
/*     */ 
/*     */   public String getLength() {
/*  92 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void setLength(String length) {
/*  96 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public String getMin() {
/* 100 */     return this.min;
/*     */   }
/*     */ 
/*     */   public void setMin(String min) {
/* 104 */     this.min = min;
/*     */   }
/*     */ 
/*     */   public String getNotNull() {
/* 108 */     return this.notNull;
/*     */   }
/*     */ 
/*     */   public void setNotNull(String notNull) {
/* 112 */     this.notNull = notNull;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> toMap()
/*     */   {
/* 125 */     Map map = new HashMap();
/* 126 */     map.put("type", getType());
/* 127 */     map.put("name", getName());
/* 128 */     map.put("FUName", getFUName());
/* 129 */     map.put("column", getColumn());
/* 130 */     map.put("id", getIsId());
/* 131 */     map.put("length", getLength());
/* 132 */     map.put("min", getMin());
/* 133 */     map.put("description", getCaption());
/* 134 */     map.put("notNull", getNotNull());
/* 135 */     map.put("unique", getUnique());
/* 136 */     if (this.filterOperator != null) map.put("filterOperator", getFilterOperator());
/* 137 */     return map;
/*     */   }
/*     */ 
/*     */   public String getFilterOperator() {
/* 141 */     return this.filterOperator;
/*     */   }
/*     */ 
/*     */   public void setFilterOperator(String filterOperator) {
/* 145 */     this.filterOperator = filterOperator;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.NormalAttribute
 * JD-Core Version:    0.5.4
 */