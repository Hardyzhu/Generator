/*    */ package com.gsoft.framework.core.i18n;
/*    */ 
/*    */ public class I18nKey
/*    */ {
/*    */   private String key;
/*    */   private String[] args;
/*    */ 
/*    */   public I18nKey(String key, String[] args)
/*    */   {
/* 33 */     this.key = key;
/* 34 */     this.args = args;
/*    */   }
/*    */ 
/*    */   public String getKey()
/*    */   {
/* 41 */     return this.key;
/*    */   }
/*    */ 
/*    */   public void setKey(String key)
/*    */   {
/* 48 */     this.key = key;
/*    */   }
/*    */ 
/*    */   public String[] getArgs()
/*    */   {
/* 55 */     return this.args;
/*    */   }
/*    */ 
/*    */   public void setArgs(String[] args)
/*    */   {
/* 62 */     this.args = args;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.i18n.I18nKey
 * JD-Core Version:    0.5.4
 */