/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ public class Progress extends Message
/*    */ {
/*    */   private static final long serialVersionUID = 5908457723545041165L;
/*    */   public static final int PROGRESS_READY = -1;
/*    */   public static final int PROGRESS_COMPLETE = 100;
/*    */   private double percent;
/*    */ 
/*    */   public Progress(double percent)
/*    */   {
/* 27 */     super("000000", "" + percent);
/* 28 */     this.percent = percent;
/*    */   }
/*    */ 
/*    */   public double getPercent() {
/* 32 */     return this.percent;
/*    */   }
/*    */ 
/*    */   public void setPercent(double percent) {
/* 36 */     this.percent = percent;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.Progress
 * JD-Core Version:    0.5.4
 */