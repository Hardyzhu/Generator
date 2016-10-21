/*    */ package com.gsoft.framework.core.web.error;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.beans.factory.BeanFactoryUtils;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ import org.springframework.core.OrderComparator;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class ErrorpageDispatcherImpl
/*    */   implements ErrorpageDispatcher, ApplicationContextAware
/*    */ {
/*    */   private List<IErrorpageAdapter> errorpageAdapters;
/*    */ 
/*    */   public String getErrorInfo(String errorCode, Throwable exception)
/*    */   {
/* 29 */     if (this.errorpageAdapters != null) {
/* 30 */       for (IErrorpageAdapter errorpageAdapter : this.errorpageAdapters) {
/* 31 */         if (errorpageAdapter.supports(errorCode)) {
/* 32 */           return errorpageAdapter.buildErrorInfo(exception);
/*    */         }
/*    */       }
/*    */     }
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */   public void setApplicationContext(ApplicationContext context)
/*    */     throws BeansException
/*    */   {
/* 42 */     initErrorpageAdapters(context);
/*    */   }
/*    */ 
/*    */   private void initErrorpageAdapters(ApplicationContext context) {
/* 46 */     if (this.errorpageAdapters == null) {
/* 47 */       Map beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, IErrorpageAdapter.class, true, false);
/* 48 */       if (beans != null) {
/* 49 */         this.errorpageAdapters = new ArrayList(beans.values());
/* 50 */         Collections.sort(this.errorpageAdapters, new OrderComparator());
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.error.ErrorpageDispatcherImpl
 * JD-Core Version:    0.5.4
 */