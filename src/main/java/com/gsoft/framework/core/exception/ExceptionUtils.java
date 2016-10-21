/*    */ package com.gsoft.framework.core.exception;
/*    */ 
/*    */ import com.gsoft.framework.core.context.Config;
/*    */ import com.gsoft.framework.core.web.view.Message;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Locale;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.context.MessageSource;
/*    */ 
/*    */ public class ExceptionUtils
/*    */ {
/* 23 */   private static final Log logger = LogFactory.getLog(ExceptionUtils.class);
/*    */ 
/*    */   public static Message getErrorMessage(Exception ex, MessageSource messageSource, Locale locale)
/*    */   {
/* 30 */     logTrace(ex);
/*    */ 
/* 33 */     if (ex instanceof ExceptionMessage) {
/* 34 */       return ((ExceptionMessage)ex).getExceptionMessage(messageSource, locale);
/*    */     }
/*    */ 
/* 40 */     Throwable cause = ex.getCause();
/* 41 */     while (cause != null) {
/* 42 */       if (cause instanceof ExceptionMessage) {
/* 43 */         return ((ExceptionMessage)cause).getExceptionMessage(messageSource, locale);
/*    */       }
/* 45 */       cause = cause.getCause();
/*    */     }
/* 47 */     String msg = "系统异常:" + ex.getMessage();
/* 48 */     String msgCode = "999999";
/*    */ 
/* 53 */     return new Message(msgCode, msg);
/*    */   }
/*    */ 
/*    */   public static void logTrace(Throwable cause) {
/* 57 */     String traceError = Config.getInstance().getProperty("trace.log");
/* 58 */     if (("true".equals(traceError)) && (cause != null)) {
/* 59 */       ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/* 60 */       PrintStream sw = new PrintStream(byteStream);
/* 61 */       cause.printStackTrace(sw);
/* 62 */       String traces = byteStream.toString();
/* 63 */       logger.error(traces);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.ExceptionUtils
 * JD-Core Version:    0.5.4
 */