/*     */ package com.gsoft.framework.core.web.controller;
/*     */ 
/*     */ import com.gsoft.framework.core.web.IPageModel;
/*     */ import com.gsoft.framework.core.web.annotation.DataInDesc;
///*     */ import com.gsoft.framework.security.AccountPrincipal;
///*     */ import com.gsoft.framework.security.PrincipalConfig;
/*     */ import com.gsoft.framework.util.RequestUtils;
/*     */ import com.gsoft.framework.util.SecurityUtils;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.util.ReflectionUtils;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.context.WebApplicationContext;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/page"})
/*     */ public class PageController extends BaseController
/*     */ {
/*     */   public static final String ATTR_PAGE_PATH = "page:path";
/*     */   public static final String ATTR_PAGE_PAGEID = "page:pageId";
/*     */ 
/*     */   @RequestMapping({"/{pagePath}/{pageId}.html"})
/*     */   public ModelAndView index(@PathVariable("pagePath") String pagePath, @PathVariable("pageId") String pageId, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  70 */     ModelAndView model = new ModelAndView();
/*     */ 
///*  72 */     AccountPrincipal account = SecurityUtils.getAccount();
///*  73 */     if (account != null) {
///*  74 */       PrincipalConfig userConfig = account.getPrincipalConfig();
///*  75 */       userConfig.put("loginName", account.getLoginName());
///*  76 */       model.addObject("account", userConfig);
///*     */     }
/*     */ 
/*  79 */     String path = pagePath.replace(".", "/");
/*  80 */     model.setViewName(path + "/" + pageId);
/*  81 */     model.addObject("page:path", path);
/*  82 */     model.addObject("page:pageId", pageId);
/*     */ 
/*  85 */     ModelMap pageModel = findPageModelMap(request, pagePath, pageId);
/*  86 */     if (pageModel != null) {
/*  87 */       model.addAllObjects(pageModel);
/*     */     }
/*  89 */     return model;
/*     */   }
/*     */ 
/*     */   private ModelMap findPageModelMap(HttpServletRequest request, String pagePath, String pageId)
/*     */   {
/* 100 */     ModelMap modelMap = new ModelMap();
/* 101 */     WebApplicationContext context = RequestUtils.getApplicationContext(request);
/*     */ 
/* 103 */     IPageModel pageAttribute = null;
/*     */     try {
/* 105 */       pageAttribute = (IPageModel)context.getBean(pagePath, IPageModel.class);
/*     */     }
/*     */     catch (BeansException e) {
/* 108 */       if (this.logger.isDebugEnabled()) {
/* 109 */         this.logger.debug(pagePath + "未找到匹配的IPageModel对象实例！");
/*     */       }
/* 111 */       return modelMap;
/*     */     }
/*     */ 
/* 114 */     if (pageAttribute != null) {
/* 115 */       Class[] types = { HttpServletRequest.class, DataIn.class };
/* 116 */       Method method = ReflectionUtils.findMethod(pageAttribute.getClass(), pageId, types);
/* 117 */       if (method != null) {
/* 118 */         Object[] args = { request, new RequestDataIn(request, (DataInDesc)method.getAnnotation(DataInDesc.class)) };
/*     */ 
/* 122 */         Object resultMap = ReflectionUtils.invokeMethod(method, pageAttribute, args);
/* 123 */         if (resultMap instanceof ModelMap) {
/* 124 */           return (ModelMap)resultMap;
/*     */         }
/*     */       }
/* 127 */       else if (this.logger.isDebugEnabled()) {
/* 128 */         this.logger.debug(pageAttribute.getClass() + "，未找到方法：" + pageId);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 133 */     return modelMap;
/*     */   }
/*     */ 
/*     */   String getUrlType()
/*     */   {
/* 138 */     return "page";
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.PageController
 * JD-Core Version:    0.5.4
 */