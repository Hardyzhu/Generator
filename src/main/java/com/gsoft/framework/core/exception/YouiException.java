/*    */ package com.gsoft.framework.core.exception;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.commons.logging.Log;
/*    */ 
/*    */ public class YouiException extends NestedRuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -620521102514596635L;
/*    */ 
/*    */   public YouiException(String msg)
/*    */   {
/* 35 */     super(msg);
/* 36 */     logMessage(msg);
/*    */   }
/*    */ 
/*    */   public YouiException(Throwable cause) {
/* 40 */     super(cause.getMessage());
/* 41 */     logCause(cause, cause.getMessage());
/*    */   }
/*    */ 
/*    */   public YouiException(String msg, Throwable cause) {
/* 45 */     super(msg, cause);
/* 46 */     logCause(cause, msg);
/*    */   }
/*    */ 
/*    */   private void logCause(Throwable cause, String message)
/*    */   {
/* 53 */     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/* 54 */     PrintStream sw = new PrintStream(byteStream);
/* 55 */     cause.printStackTrace(sw);
/* 56 */     logMessage(message);
/* 57 */     if (this.logger.isDebugEnabled())
/* 58 */       this.logger.debug("平台异常轨迹:" + byteStream.toString());
/*    */   }
/*    */ 
/*    */   private void logMessage(String message)
/*    */   {
/* 63 */     this.logger.error("平台异常信息：" + message + "!");
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.YouiException
 * JD-Core Version:    0.5.4
 */