/*    */ package com.gsoft.framework.core.web.error;
/*    */ 
/*    */ import org.springframework.core.Ordered;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class Errorpage403
/*    */   implements IErrorpageAdapter, Ordered
/*    */ {
/*    */   public boolean supports(String errorCode)
/*    */   {
/* 21 */     return "403".equals(errorCode);
/*    */   }
/*    */ 
/*    */   public String buildErrorInfo(Throwable exception)
/*    */   {
/* 29 */     return "访问权限不足";
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 34 */     return 99;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.error.Errorpage403
 * JD-Core Version:    0.5.4
 */