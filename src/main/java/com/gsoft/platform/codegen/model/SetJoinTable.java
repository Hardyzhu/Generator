/*    */ package com.gsoft.platform.codegen.model;
/*    */ 
/*    */ public class SetJoinTable
/*    */ {
/*    */   private String name;
/*    */   private String joinColumns;
/*    */   private String inverseJoinColumns;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 23 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 27 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getJoinColumns() {
/* 31 */     return this.joinColumns;
/*    */   }
/*    */ 
/*    */   public void setJoinColumns(String joinColumns) {
/* 35 */     this.joinColumns = joinColumns;
/*    */   }
/*    */ 
/*    */   public String getInverseJoinColumns() {
/* 39 */     return this.inverseJoinColumns;
/*    */   }
/*    */ 
/*    */   public void setInverseJoinColumns(String inverseJoinColumns) {
/* 43 */     this.inverseJoinColumns = inverseJoinColumns;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.SetJoinTable
 * JD-Core Version:    0.5.4
 */