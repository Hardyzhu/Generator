/*    */ package com.gsoft.framework.core.web.controller;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.context.request.WebRequest;
/*    */ import org.springframework.web.context.request.WebRequestInterceptor;
/*    */ import org.springframework.web.servlet.handler.DispatcherServletWebRequest;
/*    */ 
/*    */ @Component("webRequestInterceptor")
/*    */ public class TimeWebRequestInterceptor
/*    */   implements WebRequestInterceptor
/*    */ {
/* 35 */   private static final Log logger = LogFactory.getLog(TimeWebRequestInterceptor.class);
/*    */   public static final String TIME_ACCESS = "TIME_ACCESS";
/*    */ 
/*    */   public void preHandle(WebRequest request)
/*    */     throws Exception
/*    */   {
/* 43 */     long startTime = System.currentTimeMillis();
/*    */ 
/* 45 */     request.setAttribute("TIME_ACCESS", new Long(startTime), 0);
/*    */   }
/*    */ 
/*    */   public void postHandle(WebRequest request, ModelMap model)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   public void afterCompletion(WebRequest request, Exception ex)
/*    */     throws Exception
/*    */   {
/* 61 */     long endTime = System.currentTimeMillis();
/* 62 */     long useTime = 0L;
/* 63 */     Object startTime = request.getAttribute("TIME_ACCESS", 0);
/* 64 */     if (startTime != null) {
/* 65 */       useTime = endTime - ((Long)startTime).longValue();
/*    */     }
/* 67 */     if (ex != null) {
/* 68 */       logger.error("异常信息：" + ex.getMessage());
/*    */     }
/*    */ 
/* 71 */     String uri = "";
/* 72 */     if (request instanceof DispatcherServletWebRequest) {
/* 73 */       DispatcherServletWebRequest dsw = (DispatcherServletWebRequest)request;
/* 74 */       uri = dsw.getRequest().getRequestURI();
/*    */     }
/* 76 */     if (logger.isDebugEnabled())
/* 77 */       logger.debug("访问【" + uri + "】耗时" + useTime + "毫秒.");
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.TimeWebRequestInterceptor
 * JD-Core Version:    0.5.4
 */