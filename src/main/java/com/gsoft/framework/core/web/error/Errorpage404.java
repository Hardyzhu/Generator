/*    */ package com.gsoft.framework.core.web.error;
/*    */ 
/*    */ import org.springframework.core.Ordered;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class Errorpage404
/*    */   implements IErrorpageAdapter, Ordered
/*    */ {
/*    */   public boolean supports(String errorCode)
/*    */   {
/* 21 */     return "404".equals(errorCode);
/*    */   }
/*    */ 
/*    */   public String buildErrorInfo(Throwable exception)
/*    */   {
/* 29 */     return "页面未找到";
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 34 */     return 99;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.error.Errorpage404
 * JD-Core Version:    0.5.4
 */