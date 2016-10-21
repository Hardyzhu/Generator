/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ public class PassedMessage extends Message
/*    */ {
/*    */   private static final long serialVersionUID = -1838714533099774309L;
/*    */   private boolean passed;
/*    */ 
/*    */   public boolean isPassed()
/*    */   {
/* 19 */     return this.passed;
/*    */   }
/*    */ 
/*    */   public void setPassed(boolean passed) {
/* 23 */     this.passed = passed;
/*    */   }
/*    */ 
/*    */   public PassedMessage(String code, String info, boolean passed) {
/* 27 */     super(code, info);
/* 28 */     this.passed = passed;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.PassedMessage
 * JD-Core Version:    0.5.4
 */