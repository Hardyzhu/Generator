/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import com.gsoft.framework.core.web.annotation.DataInfo;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.core.MethodParameter;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.web.context.request.NativeWebRequest;
///*    */ import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
///*    */ import org.springframework.web.method.support.ModelAndViewContainer;
///*    */ import org.springframework.web.servlet.SmartView;
/*    */ import org.springframework.web.servlet.View;
/*    */ 
/*    */ @Component("dataModelAndViewReturnValueHandler")
/*    */ public class DataModelAndViewReturnValueHandler extends AbstractTransLogger
/*    */ {
/*    */   public boolean supportsReturnType(MethodParameter returnType)
/*    */   {
/* 28 */     return DataModelAndView.class.equals(returnType.getParameterType());
/*    */   }
/*    */ 
///*    */   public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
///*    */     throws Exception
///*    */   {
///* 36 */     if (returnValue == null) {
///* 37 */       mavContainer.setRequestHandled(true);
///* 38 */       return;
///*    */     }
///*    */ 
///* 41 */     long endTime = System.currentTimeMillis();
///* 42 */     long useTime = 0L;
///* 43 */     Object startTime = webRequest.getAttribute("TIME_ACCESS", 0);
///* 44 */     if (startTime != null) {
///* 45 */       useTime = endTime - ((Long)startTime).longValue();
///*    */     }
///*    */ 
///* 48 */     DataModelAndView mav = (DataModelAndView)returnValue;
///*    */ 
///* 50 */     if (mav.isReference()) {
///* 51 */       String viewName = mav.getViewName();
///* 52 */       mavContainer.setViewName(viewName);
///* 53 */       if ((viewName != null) && (viewName.startsWith("redirect:")))
///* 54 */         mavContainer.setRedirectModelScenario(true);
///*    */     }
///*    */     else
///*    */     {
///* 58 */       View view = mav.getView();
///* 59 */       mavContainer.setView(view);
///* 60 */       if ((view instanceof SmartView) && 
///* 61 */         (((SmartView)view).isRedirectView())) {
///* 62 */         mavContainer.setRedirectModelScenario(true);
///*    */       }
///*    */     }
///*    */ 
///* 66 */     mavContainer.addAllAttributes(mav.getModel());
///*    */ 
///* 68 */     DataInfo dataInfo = (DataInfo)returnType.getMethodAnnotation(DataInfo.class);
///* 69 */     if (dataInfo != null) {
///* 70 */       mav.setCaption(dataInfo.text());
///* 71 */       mav.setId(dataInfo.functionId());
///*    */ 
///* 73 */       String details = mav.getLogContent();
///* 74 */       if (dataInfo.log()) {
///* 75 */         HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class);
///*    */ 
///* 77 */         String userIP = request.getRemoteAddr();
///*    */ 
///* 79 */         writeLog(dataInfo.functionId(), dataInfo.text(), details, useTime, userIP);
///*    */       }
///*    */     }
///*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.DataModelAndViewReturnValueHandler
 * JD-Core Version:    0.5.4
 */