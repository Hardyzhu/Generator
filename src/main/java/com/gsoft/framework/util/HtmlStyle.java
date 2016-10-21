/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ public class HtmlStyle
/*    */ {
/*    */   private StringBuffer styles;
/*    */ 
/*    */   public HtmlStyle()
/*    */   {
/* 27 */     this.styles = new StringBuffer();
/*    */   }
/*    */   public void addStyle(String style) {
/* 30 */     this.styles.append(" " + style);
/*    */   }
/*    */ 
/*    */   public String getStyles() {
/* 34 */     return this.styles.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.HtmlStyle
 * JD-Core Version:    0.5.4
 */