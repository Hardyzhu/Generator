/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.springframework.web.servlet.View;
/*    */ import org.springframework.web.servlet.view.AbstractUrlBasedView;
/*    */ import org.springframework.web.servlet.view.InternalResourceView;
/*    */ import org.springframework.web.servlet.view.InternalResourceViewResolver;
/*    */ 
/*    */ public class PageResourceViewResolver extends InternalResourceViewResolver
/*    */ {
/*    */   protected AbstractUrlBasedView buildView(String viewName)
/*    */     throws Exception
/*    */   {
/* 21 */     InternalResourceView view = (InternalResourceView)super.buildView(viewName);
/*    */ 
/* 35 */     return view;
/*    */   }
/*    */ 
/*    */   public View resolveViewName(String viewName, Locale locale) throws Exception
/*    */   {
/* 40 */     return super.resolveViewName(viewName, locale);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.PageResourceViewResolver
 * JD-Core Version:    0.5.4
 */