/*    */ package com.gsoft.platform.codegen;
/*    */ 
/*    */ public class GenerateException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -1901096479042727635L;
/*    */   private String message;
/*    */ 
/*    */   public GenerateException(String message)
/*    */   {
/* 20 */     super(message);
/* 21 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 25 */     return this.message;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.GenerateException
 * JD-Core Version:    0.5.4
 */