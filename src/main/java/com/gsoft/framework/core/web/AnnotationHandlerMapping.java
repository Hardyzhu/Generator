/*    */ package com.gsoft.framework.core.web;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import org.springframework.core.annotation.AnnotationUtils;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ public class AnnotationHandlerMapping
/*    */ {
///*    */   protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType)
///*    */   {
///* 45 */     RequestMappingInfo info = null;
///* 46 */     RequestMapping methodAnnotation = (RequestMapping)AnnotationUtils.findAnnotation(method, RequestMapping.class);
///*    */ 
///* 48 */     if (methodAnnotation != null) {
///* 49 */       RequestCondition methodCondition = getCustomMethodCondition(method);
///* 50 */       info = createRequestMappingInfo(methodAnnotation, methodCondition, method);
///* 51 */       RequestMapping typeAnnotation = (RequestMapping)AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
///* 52 */       if (typeAnnotation != null) {
///* 53 */         RequestCondition typeCondition = getCustomTypeCondition(handlerType);
///* 54 */         info = createRequestMappingInfo(typeAnnotation, typeCondition, method).combine(info);
///*    */       }
///*    */     }
///* 57 */     return info;
///*    */   }
/*    */ 
///*    */   private RequestMappingInfo createRequestMappingInfo(RequestMapping annotation, RequestCondition<?> customCondition, Method method)
///*    */   {
///* 70 */     String[] pathValues = annotation.value();
///*    */ 
///* 72 */     if ((pathValues == null) || (pathValues.length == 0)) {
///* 73 */       pathValues = new String[] { "/" + method.getName() + ".json" };
///*    */     }
///*    */ 
///* 76 */     return new RequestMappingInfo(new PatternsRequestCondition(pathValues, getUrlPathHelper(), getPathMatcher(), true, true, getFileExtensions()), new RequestMethodsRequestCondition(annotation.method()), new ParamsRequestCondition(annotation.params()), new HeadersRequestCondition(annotation.headers()), new ConsumesRequestCondition(annotation.consumes(), annotation.headers()), new ProducesRequestCondition(annotation.produces(), annotation.headers(), getContentNegotiationManager()), customCondition);
///*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.AnnotationHandlerMapping
 * JD-Core Version:    0.5.4
 */