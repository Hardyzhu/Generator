/*    */ package com.gsoft.framework.core.web.exception;
/*    */ 
/*    */ import com.gsoft.framework.core.exception.ExceptionUtils;
/*    */ import com.gsoft.framework.core.web.view.DataModelAndView;
/*    */ import com.gsoft.framework.core.web.view.Message;
/*    */ import com.gsoft.framework.util.RequestUtils;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.context.MessageSource;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
/*    */ 
/*    */ public class DefaultHandlerExceptionResolver extends SimpleMappingExceptionResolver
/*    */ {
/*    */   private MessageSource messageSource;
/*    */ 
/*    */   public void setMessageSource(MessageSource messageSource)
/*    */   {
/* 35 */     this.messageSource = messageSource;
/*    */   }
/*    */ 
/*    */   protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
/*    */   {
/* 46 */     Locale locale = RequestUtils.getLocale(request);
/* 47 */     Message errorMessage = ExceptionUtils.getErrorMessage(ex, this.messageSource, locale);
/* 48 */     request.setAttribute("errorMessage", errorMessage);
/*    */ 
/* 55 */     if (request.getRequestURI().endsWith("json")) {
/* 56 */       return new DataModelAndView(errorMessage);
/*    */     }
/* 58 */     return super.doResolveException(request, response, handler, ex);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.exception.DefaultHandlerExceptionResolver
 * JD-Core Version:    0.5.4
 */