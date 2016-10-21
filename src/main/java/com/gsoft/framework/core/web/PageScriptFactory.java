/*    */ package com.gsoft.framework.core.web;
/*    */ 
/*    */ import org.apache.shiro.cache.Cache;
/*    */ import org.apache.shiro.cache.CacheManager;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class PageScriptFactory
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CacheManager cacheManager;
/*    */ 
/*    */   public void setCacheManager(CacheManager cacheManager)
/*    */   {
/* 41 */     this.cacheManager = cacheManager;
/*    */   }
/*    */ 
/*    */   public String getPageScript(String sessionId, String pageId) {
/* 45 */     Cache scriptCache = this.cacheManager.getCache("org.youi.common.PageScriptFactory");
/*    */ 
/* 48 */     return (String)scriptCache.get(pageId + sessionId);
/*    */   }
/*    */ 
/*    */   public void addPageScript(String sessionId, String pageId, String pageScript) {
/* 52 */     Cache scriptCache = this.cacheManager.getCache("org.youi.common.PageScriptFactory");
/*    */ 
/* 54 */     scriptCache.put(pageId + sessionId, pageScript);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.PageScriptFactory
 * JD-Core Version:    0.5.4
 */