/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class OneToManySetAttribute extends SetAttribute
/*     */ {
/*     */   private static final long serialVersionUID = -3764561625926883408L;
/*     */   private SetOneToMany setOneToMany;
/*     */   private SetJoinColumn setJoinColumn;
/*     */ 
/*     */   public Map<String, Object> toMap()
/*     */   {
/*  31 */     Map map = new HashMap();
/*  32 */     map.put("setType", "one-to-many");
/*  33 */     map.put("name", getName());
/*  34 */     map.put("FUName", getFUName());
/*  35 */     map.put("description", getCaption());
/*     */ 
/*  37 */     map.put("targetEntity", getSetOneToMany().getTargetEntityClass());
/*  38 */     map.put("joinColumnName", getSetJoinColumn().getName());
/*  39 */     return map;
/*     */   }
/*     */   public SetOneToMany getSetOneToMany() {
/*  42 */     return this.setOneToMany;
/*     */   }
/*     */   public void setSetOneToMany(SetOneToMany setOneToMany) {
/*  45 */     this.setOneToMany = setOneToMany;
/*     */   }
/*     */   public SetJoinColumn getSetJoinColumn() {
/*  48 */     return this.setJoinColumn;
/*     */   }
/*     */   public void setSetJoinColumn(SetJoinColumn setJoinColumn) {
/*  51 */     this.setJoinColumn = setJoinColumn;
/*     */   }
/*     */ 
/*     */   public String getTargetEntity() {
/*  55 */     return this.setOneToMany.getTargetEntityClass();
/*     */   }
/*     */ 
/*     */   public void parseFromSetElement(Element setElement)
/*     */   {
/*  65 */     String name = setElement.attributeValue("name");
/*  66 */     String description = setElement.attributeValue("caption");
/*     */ 
/*  68 */     Element oneToManyElement = (Element)setElement.selectSingleNode("one-to-many");
/*  69 */     Element joinColumnElement = (Element)setElement.selectSingleNode("join-column");
/*     */ 
/*  73 */     setName(name);
/*  74 */     setCaption(description);
/*  75 */     setSetOneToMany(parseOneToManyElement(oneToManyElement));
/*  76 */     setSetJoinColumn(parseJoinColumnElement(joinColumnElement));
/*     */   }
/*     */ 
/*     */   private SetOneToMany parseOneToManyElement(Element oneToManyElement)
/*     */   {
/*  87 */     SetOneToMany setOneToMany = new SetOneToMany();
/*  88 */     String targetEntity = oneToManyElement.attributeValue("targetEntity");
/*  89 */     setOneToMany.setTargetEntity(targetEntity);
/*     */ 
/*  91 */     return setOneToMany;
/*     */   }
/*     */ 
/*     */   private SetJoinColumn parseJoinColumnElement(Element joinColumnElement)
/*     */   {
/* 102 */     SetJoinColumn setJoinColumn = new SetJoinColumn();
/* 103 */     String name = joinColumnElement.attributeValue("name");
/*     */ 
/* 105 */     setJoinColumn.setName(name);
/* 106 */     return setJoinColumn;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.OneToManySetAttribute
 * JD-Core Version:    0.5.4
 */