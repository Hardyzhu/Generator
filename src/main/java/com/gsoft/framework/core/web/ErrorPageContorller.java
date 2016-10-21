/*    */ package com.gsoft.framework.core.web;
/*    */ 
/*    */ import com.gsoft.framework.core.web.error.ErrorpageDispatcher;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/errorpage"})
/*    */ public class ErrorPageContorller
/*    */ {
/*    */ 
/*    */   @Autowired(required=false)
/*    */   private ErrorpageDispatcher errorpageDispatcher;
/*    */ 
/*    */   @RequestMapping({"/{errorCode}.html"})
/*    */   public ModelAndView index(@PathVariable("errorCode") String errorCode, HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 33 */     ModelAndView model = new ModelAndView();
/*    */ 
/* 35 */     request.getAttributeNames();
/*    */ 
/* 37 */     Object exception = request.getAttribute("javax.servlet.error.exception");
/*    */ 
/* 39 */     model.addObject("errorCode", errorCode);
/*    */ 
/* 41 */     String errorInfo = "页面错误";
/*    */ 
/* 43 */     if (this.errorpageDispatcher != null) {
/* 44 */       Throwable throwException = (exception != null) ? (Throwable)exception : null;
/* 45 */       errorInfo = this.errorpageDispatcher.getErrorInfo(errorCode, throwException);
/*    */     }
/*    */ 
/* 48 */     model.addObject("errorInfo", errorInfo);
/*    */ 
/* 50 */     if (request.getParameter("page:pageId") != null)
/* 51 */       model.setViewName("errorpage/pagerror");
/*    */     else {
/* 53 */       model.setViewName("errorpage/error");
/*    */     }
/*    */ 
/* 56 */     return model;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.ErrorPageContorller
 * JD-Core Version:    0.5.4
 */