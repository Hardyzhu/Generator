/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class ManyToManySetAttribute extends SetAttribute
/*     */ {
/*     */   private static final long serialVersionUID = 7248162591338929689L;
/*     */   private SetManyToMany setManyToMany;
/*     */   private SetJoinTable setJoinTable;
/*     */   private boolean owern;
/*     */   private String mappedBy;
/*     */ 
/*     */   public boolean isOwern()
/*     */   {
/*  37 */     return this.owern;
/*     */   }
/*     */ 
/*     */   public void setOwern(boolean owern)
/*     */   {
/*  43 */     this.owern = owern;
/*     */   }
/*     */ 
/*     */   public String getMappedBy()
/*     */   {
/*  50 */     return this.mappedBy;
/*     */   }
/*     */ 
/*     */   public void setMappedBy(String mappedBy)
/*     */   {
/*  56 */     this.mappedBy = mappedBy;
/*     */   }
/*     */   public SetManyToMany getSetManyToMany() {
/*  59 */     return this.setManyToMany;
/*     */   }
/*     */   public void setSetManyToMany(SetManyToMany setManyToMany) {
/*  62 */     this.setManyToMany = setManyToMany;
/*     */   }
/*     */   public SetJoinTable getSetJoinTable() {
/*  65 */     return this.setJoinTable;
/*     */   }
/*     */   public void setSetJoinTable(SetJoinTable setJoinTable) {
/*  68 */     this.setJoinTable = setJoinTable;
/*     */   }
/*     */ 
/*     */   public String getTargetEntity() {
/*  72 */     return getSetManyToMany().getTargetEntityClass();
/*     */   }
/*     */ 
/*     */   public Map<String, Object> toMap()
/*     */   {
/*  79 */     Map map = new HashMap();
/*  80 */     map.put("setType", "many-to-many");
/*  81 */     map.put("name", getName());
/*  82 */     map.put("FUName", getFUName());
/*  83 */     map.put("description", getCaption());
/*     */ 
/*  85 */     map.put("targetEntity", getSetManyToMany().getTargetEntityClass());
/*  86 */     map.put("cascade", getSetManyToMany().getCascade());
/*     */ 
/*  88 */     if (isOwern())
/*  89 */       map.put("owern", "true");
/*     */     else {
/*  91 */       map.put("mappedBy", getMappedBy());
/*     */     }
/*     */ 
/*  97 */     return map;
/*     */   }
/*     */ 
/*     */   public void parseFromSetElement(Element setElement)
/*     */   {
/*     */     boolean owern;
/*     */     try
/*     */     {
/* 110 */       owern = new Boolean(setElement.attributeValue("owern")).booleanValue();
/*     */     } catch (RuntimeException e) {
/* 112 */       owern = false;
/*     */     }
/*     */ 
/* 116 */     String name = setElement.attributeValue("name");
/* 117 */     String description = setElement.attributeValue("caption");
/* 118 */     Element manyToManyElement = (Element)setElement.selectSingleNode("many-to-many");
/* 119 */     Element joinTableElement = (Element)setElement.selectSingleNode("join-table");
/*     */ 
/* 124 */     setName(name);
/* 125 */     setCaption(description);
/* 126 */     setSetManyToMany(parseManyToManyElement(manyToManyElement));
/* 127 */     if (owern)
/* 128 */       setSetJoinTable(parseJoinTableElement(joinTableElement));
/*     */   }
/*     */ 
/*     */   private SetJoinTable parseJoinTableElement(Element joinTableElement)
/*     */   {
/* 142 */     String name = joinTableElement.attributeValue("name");
/* 143 */     String joinColumns = joinTableElement.attributeValue("joinColumns");
/* 144 */     String inverseJoinColumns = joinTableElement.attributeValue("inverseJoinColumns");
/* 145 */     SetJoinTable setJoinTable = new SetJoinTable();
/*     */ 
/* 147 */     setJoinTable.setInverseJoinColumns(inverseJoinColumns);
/* 148 */     setJoinTable.setJoinColumns(joinColumns);
/* 149 */     setJoinTable.setName(name);
/* 150 */     return setJoinTable;
/*     */   }
/*     */ 
/*     */   private SetManyToMany parseManyToManyElement(Element manyToManyElement)
/*     */   {
/* 162 */     String targetEntity = manyToManyElement.attributeValue("targetEntity");
/* 163 */     String cascade = manyToManyElement.attributeValue("cascade");
/* 164 */     SetManyToMany setManyToMany = new SetManyToMany();
/*     */ 
/* 166 */     setManyToMany.setCascade(cascade);
/* 167 */     setManyToMany.setTargetEntity(targetEntity);
/*     */ 
/* 169 */     return setManyToMany;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.ManyToManySetAttribute
 * JD-Core Version:    0.5.4
 */