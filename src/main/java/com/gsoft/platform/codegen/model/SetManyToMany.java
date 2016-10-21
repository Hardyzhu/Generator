/*    */ package com.gsoft.platform.codegen.model;
/*    */ 
/*    */ public class SetManyToMany
/*    */ {
/*    */   private String targetEntity;
/*    */   private String cascade;
/*    */ 
/*    */   public String getTargetEntity()
/*    */   {
/* 10 */     return this.targetEntity;
/*    */   }
/*    */ 
/*    */   public void setTargetEntity(String targetEntity) {
/* 14 */     this.targetEntity = targetEntity;
/*    */   }
/*    */ 
/*    */   public String getCascade() {
/* 18 */     return this.cascade;
/*    */   }
/*    */ 
/*    */   public void setCascade(String cascade) {
/* 22 */     this.cascade = cascade;
/*    */   }
/*    */ 
/*    */   public String getTargetEntityClass() {
/* 26 */     Model targetModel = ModelFactory.getInstance().getModel("test", this.targetEntity);
/* 27 */     if (targetModel != null) return targetModel.getClassName() + ".class";
/* 28 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.SetManyToMany
 * JD-Core Version:    0.5.4
 */