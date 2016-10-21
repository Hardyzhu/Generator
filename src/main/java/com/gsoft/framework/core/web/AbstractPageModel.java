/*    */ package com.gsoft.framework.core.web;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public abstract class AbstractPageModel
/*    */   implements IPageModel
/*    */ {
/*    */   protected final Log logger;
/*    */ 
/*    */   public AbstractPageModel()
/*    */   {
/* 34 */     this.logger = LogFactory.getLog(super.getClass());
/*    */   }
/*    */   protected Map<String, ?> addModelObject(Map<String, Object> modelMap, String key, Object value) {
/* 37 */     if (modelMap == null) {
/* 38 */       modelMap = new HashMap();
/*    */     }
/*    */ 
/* 42 */     modelMap.put(key, value);
/* 43 */     return modelMap;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.AbstractPageModel
 * JD-Core Version:    0.5.4
 */