/*    */ package com.gsoft.framework.core.exception;
/*    */ 
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public abstract class NestedRuntimeException extends org.springframework.core.NestedRuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 8884403198190623424L;
/* 34 */   protected final Log logger = LogFactory.getLog(super.getClass());
/*    */ 
/*    */   public NestedRuntimeException(String msg) {
/* 37 */     super(msg);
/* 38 */     logTrace();
/*    */   }
/*    */ 
/*    */   public NestedRuntimeException(String msg, Throwable cause) {
/* 42 */     super(msg, cause);
/* 43 */     logTrace();
/*    */   }
/*    */ 
/*    */   protected void logTrace()
/*    */   {
/* 50 */     ExceptionUtils.logTrace(getCause());
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.NestedRuntimeException
 * JD-Core Version:    0.5.4
 */