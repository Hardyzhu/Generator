/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ public class BaseModelAndView extends ModelAndView
/*    */ {
/*    */   private String id;
/*    */   private String caption;
/*    */   private String logContent;
/*    */ 
/*    */   public BaseModelAndView()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BaseModelAndView(String viewName)
/*    */   {
/* 35 */     setViewName(viewName);
/*    */   }
/*    */ 
/*    */   public String getId()
/*    */   {
/* 48 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id)
/*    */   {
/* 55 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getCaption()
/*    */   {
/* 62 */     return this.caption;
/*    */   }
/*    */ 
/*    */   public void setCaption(String caption)
/*    */   {
/* 69 */     this.caption = caption;
/*    */   }
/*    */ 
/*    */   public String getLogContent()
/*    */   {
/* 76 */     return this.logContent;
/*    */   }
/*    */ 
/*    */   public void setLogContent(String logContent)
/*    */   {
/* 83 */     this.logContent = logContent;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.BaseModelAndView
 * JD-Core Version:    0.5.4
 */