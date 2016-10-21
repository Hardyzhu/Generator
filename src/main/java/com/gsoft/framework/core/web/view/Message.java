/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Message
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -694991823003131043L;
/*    */   public String info;
/*    */   public String code;
/*    */ 
/*    */   public Message(String code, String info)
/*    */   {
/* 40 */     this.code = code;
/* 41 */     this.info = info;
/*    */   }
/*    */ 
/*    */   public String getInfo()
/*    */   {
/* 48 */     return this.info;
/*    */   }
/*    */ 
/*    */   public void setInfo(String info)
/*    */   {
/* 55 */     this.info = info;
/*    */   }
/*    */ 
/*    */   public String getCode()
/*    */   {
/* 62 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code)
/*    */   {
/* 69 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 74 */     return "Message [info=" + this.info + ", code=" + this.code + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.Message
 * JD-Core Version:    0.5.4
 */