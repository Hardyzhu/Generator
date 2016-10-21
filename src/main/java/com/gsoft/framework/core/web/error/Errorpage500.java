/*    */ package com.gsoft.framework.core.web.error;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.springframework.core.Ordered;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class Errorpage500
/*    */   implements IErrorpageAdapter, Ordered
/*    */ {
/*    */   public boolean supports(String errorCode)
/*    */   {
/* 24 */     return "500".equals(errorCode);
/*    */   }
/*    */ 
/*    */   public String buildErrorInfo(Throwable exception)
/*    */   {
/* 33 */     StringBuffer buf = new StringBuffer();
/* 34 */     buf.append("内部服务器错误");
/* 35 */     if (exception != null) {
/* 36 */       buf.append("</br>");
/* 37 */       ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/* 38 */       PrintStream sw = new PrintStream(byteStream);
/* 39 */       exception.printStackTrace(sw);
/* 40 */       buf.append(byteStream.toString());
/*    */     }
/* 42 */     return buf.toString();
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 47 */     return 99;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.error.Errorpage500
 * JD-Core Version:    0.5.4
 */