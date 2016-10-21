/*    */ package com.gsoft.platform.codegen;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public abstract class AbstractDataModel
/*    */   implements DataModel
/*    */ {
/*    */   protected String outPath;
/*    */   protected String description;
/*    */   protected Map<String, Object> data;
/*    */ 
/*    */   public String getOutPath()
/*    */   {
/* 13 */     return this.outPath;
/*    */   }
/*    */ 
/*    */   public void setOutPath(String outPath) {
/* 17 */     this.outPath = outPath;
/*    */   }
/*    */ 
/*    */   public abstract String getFullPath();
/*    */ 
/*    */   public void setData(Map<String, Object> data) {
/* 23 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public String getDescription() {
/* 27 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 31 */     this.description = description;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.AbstractDataModel
 * JD-Core Version:    0.5.4
 */