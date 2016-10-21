/*    */ package com.gsoft.platform.codegen.model;
/*    */ 
/*    */ public class SetOneToMany
/*    */ {
/*    */   private String targetEntity;
/*    */ 
/*    */   public String getTargetEntity()
/*    */   {
/* 21 */     return this.targetEntity;
/*    */   }
/*    */ 
/*    */   public void setTargetEntity(String targetEntity)
/*    */   {
/* 28 */     this.targetEntity = targetEntity;
/*    */   }
/*    */ 
/*    */   public String getTargetEntityClass() {
/* 32 */     Model targetModel = ModelFactory.getInstance().getModel("test", this.targetEntity);
/* 33 */     if (targetModel != null) return targetModel.getClassName() + ".class";
/* 34 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.SetOneToMany
 * JD-Core Version:    0.5.4
 */