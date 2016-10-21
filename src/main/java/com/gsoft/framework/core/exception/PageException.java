/*    */ package com.gsoft.framework.core.exception;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class PageException extends NestedRuntimeException
/*    */ {
/*    */   private String message;
/*    */   private String traces;
/*    */   private static final long serialVersionUID = 2935261162254416210L;
/*    */ 
/*    */   public PageException(String msg, Throwable cause)
/*    */   {
/* 38 */     super(msg, cause);
/* 39 */     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/* 40 */     PrintStream sw = new PrintStream(byteStream);
/* 41 */     cause.printStackTrace(sw);
/*    */ 
/* 43 */     this.traces = byteStream.toString();
/*    */ 
/* 45 */     if (cause instanceof BusException)
/* 46 */       this.message = cause.getMessage();
/*    */     else
/* 48 */       this.message = "您访问的页面存在异常信息！";
/*    */   }
/*    */ 
/*    */   public String getTraces()
/*    */   {
/* 56 */     return this.traces;
/*    */   }
/*    */ 
/*    */   public void setTraces(String traces)
/*    */   {
/* 63 */     this.traces = traces;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 71 */     return this.message;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.PageException
 * JD-Core Version:    0.5.4
 */