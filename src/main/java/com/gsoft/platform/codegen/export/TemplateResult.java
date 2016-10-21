/*    */ package com.gsoft.platform.codegen.export;
/*    */ 
/*    */ public class TemplateResult
/*    */ {
/*    */   private String entryName;
/*    */   private byte[] byteOut;
/*    */ 
/*    */   public TemplateResult(String entryName, byte[] byteOut)
/*    */   {
/* 17 */     this.entryName = entryName;
/* 18 */     this.byteOut = byteOut;
/*    */   }
/*    */ 
/*    */   public String getEntryName() {
/* 22 */     return this.entryName;
/*    */   }
/*    */ 
/*    */   public void setEntryName(String entryName) {
/* 26 */     this.entryName = entryName;
/*    */   }
/*    */ 
/*    */   public byte[] getByteOut() {
/* 30 */     return this.byteOut;
/*    */   }
/*    */ 
/*    */   public void setByteOut(byte[] byteOut) {
/* 34 */     this.byteOut = byteOut;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.export.TemplateResult
 * JD-Core Version:    0.5.4
 */