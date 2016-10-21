/*    */ package com.gsoft.framework.core.exception;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.commons.logging.Log;
/*    */ 
/*    */ public class DataException extends NestedRuntimeException
/*    */ {
/*    */   private String traces;
/*    */   private static final long serialVersionUID = 2935261162254416210L;
/*    */ 
/*    */   public DataException(String msg, Throwable cause)
/*    */   {
/* 37 */     super(msg, cause);
/* 38 */     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/* 39 */     PrintStream sw = new PrintStream(byteStream);
/* 40 */     cause.printStackTrace(sw);
/*    */ 
/* 42 */     this.traces = byteStream.toString();
/* 43 */     this.logger.error("异常详细信息：" + this.traces);
/*    */   }
/*    */ 
/*    */   public String getTraces()
/*    */   {
/* 50 */     return this.traces;
/*    */   }
/*    */ 
/*    */   public void setTraces(String traces)
/*    */   {
/* 57 */     this.traces = traces;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.DataException
 * JD-Core Version:    0.5.4
 */