/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Module
/*     */ {
/*     */   private String name;
/*     */   private String caption;
/*  33 */   private List<Model> models = new ArrayList();
/*     */ 
/*     */   public Module()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Module(String name, String caption) {
/*  40 */     this.name = name;
/*  41 */     this.caption = caption;
/*     */   }
/*     */ 
/*     */   public void addModel(Model model)
/*     */   {
/*  48 */     if ((model != null) && (!this.models.contains(model)))
/*  49 */       this.models.add(model);
/*     */   }
/*     */ 
/*     */   public Model getModel(String modelName)
/*     */   {
/*  59 */     for (Model model : this.models) {
/*  60 */       if ((model != null) && (model.getName().equals(modelName))) {
/*  61 */         return model;
/*     */       }
/*     */     }
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */   public void removeModel(Model model)
/*     */   {
/*  72 */     this.models.remove(model);
/*     */   }
/*     */ 
/*     */   public void removeModel(String modelName)
/*     */   {
/*  80 */     for (Model model : this.models)
/*  81 */       if (model.getName().equals(modelName))
/*  82 */         this.models.remove(model);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  91 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  98 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getCaption()
/*     */   {
/* 105 */     return this.caption;
/*     */   }
/*     */ 
/*     */   public void setCaption(String caption)
/*     */   {
/* 112 */     this.caption = caption;
/*     */   }
/*     */ 
/*     */   public List<Model> getModels()
/*     */   {
/* 119 */     return this.models;
/*     */   }
/*     */ 
/*     */   public void setModels(List<Model> models)
/*     */   {
/* 126 */     this.models = models;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 134 */     int prime = 31;
/* 135 */     int result = 1;
/* 136 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 137 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 145 */     if (this == obj)
/* 146 */       return true;
/* 147 */     if (obj == null)
/* 148 */       return false;
/* 149 */     if (super.getClass() != obj.getClass())
/* 150 */       return false;
/* 151 */     Module other = (Module)obj;
/* 152 */     if (this.name == null)
/* 153 */       if (other.name != null)
/* 154 */         return false;
/* 155 */     else if (!this.name.equals(other.name))
/* 156 */       return false;
/* 157 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.Module
 * JD-Core Version:    0.5.4
 */