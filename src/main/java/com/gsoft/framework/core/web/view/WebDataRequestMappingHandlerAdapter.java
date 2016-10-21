/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
///*    */ import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
///*    */ import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
/*    */ 
/*    */ public class WebDataRequestMappingHandlerAdapter 
/*    */ {
/*    */ 
///*    */   @Resource(name="dataModelAndViewReturnValueHandler")
///*    */   private DataModelAndViewReturnValueHandler dataModelAndViewReturnValueHandler;
///*    */ 
///*    */   public void afterPropertiesSet()
///*    */   {
///* 24 */     super.afterPropertiesSet();
///*    */ 
///* 26 */     List returnValueHandlers = new ArrayList();
///*    */ 
///* 28 */     returnValueHandlers.add(this.dataModelAndViewReturnValueHandler);
///* 29 */     returnValueHandlers.addAll(getReturnValueHandlers().getHandlers());
///* 30 */     setReturnValueHandlers(returnValueHandlers);
///*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.WebDataRequestMappingHandlerAdapter
 * JD-Core Version:    0.5.4
 */