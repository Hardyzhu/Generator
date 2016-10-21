/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class AbstractTransLogger
/*    */ {
/*    */ 
///*    */   @Autowired(required=false)
///*    */   private TransLogService transLogService;
/*    */ 
///*    */   public void setTransLogService(TransLogService transLogService)
///*    */   {
///* 18 */     this.transLogService = transLogService;
///*    */   }
/*    */ 
/*    */   protected void writeLog(String tranId, String tranCaption, String details, long useTime, String userIP)
/*    */   {
///* 25 */     if (this.transLogService == null) {
///* 26 */       this.transLogService = new DefaultTransLogService();
///*    */     }
///* 28 */     this.transLogService.writeLog(tranId, tranCaption, details, useTime, userIP);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.AbstractTransLogger
 * JD-Core Version:    0.5.4
 */