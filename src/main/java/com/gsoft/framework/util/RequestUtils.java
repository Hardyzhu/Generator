/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.web.context.WebApplicationContext;
/*     */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*     */ 
/*     */ public class RequestUtils
/*     */ {
/*     */   public static String getQueryParameter(HttpServletRequest request, String name)
/*     */   {
/*  47 */     String queryString = request.getQueryString();
/*     */ 
/*  49 */     if (StringUtils.isEmpty(queryString)) {
/*  50 */       return null;
/*     */     }
/*     */     try
/*     */     {
/*  54 */       queryString = URLDecoder.decode(queryString, "GBK");
/*     */     } catch (UnsupportedEncodingException e) {
/*  56 */       return request.getParameter(name);
/*     */     }
/*     */ 
/*  59 */     String[] keyValues = queryString.split("&");
/*  60 */     for (String keyValue : keyValues) {
/*  61 */       String[] aKeyValue = keyValue.split("=");
/*     */ 
/*  63 */       if ((aKeyValue.length == 2) && (name.equals(aKeyValue[0]))) {
/*  64 */         return aKeyValue[1];
/*     */       }
/*     */     }
/*  67 */     return request.getParameter(name);
/*     */   }
/*     */ 
/*     */   public static String getParameterValue(HttpServletRequest request, String name) {
/*  71 */     return request.getParameter(name);
/*     */   }
/*     */ 
/*     */   public static String[] getParameterValues(HttpServletRequest request, String name)
/*     */   {
/*  76 */     return request.getParameterValues(name);
/*     */   }
/*     */ 
/*     */   public static WebApplicationContext getApplicationContext(HttpServletRequest request)
/*     */   {
/*  85 */     return WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
/*     */   }
/*     */ 
/*     */   public static String getRealPath(HttpServletRequest request, String path)
/*     */   {
/*  95 */     WebApplicationContext context = getApplicationContext(request);
/*  96 */     Assert.notNull(context, "spring WebApplicationContext未在request中找到！");
/*  97 */     URL url = null;
/*     */     try
/*     */     {
/* 100 */       Resource resource = context.getResource(path);
/* 101 */       url = resource.getURL();
/*     */ 
/* 108 */       if (!"file".equals(url.getProtocol()))
/* 109 */         url = resource.getFile().toURI().toURL();
/*     */     }
/*     */     catch (IOException e) {
/* 112 */       String errorMsg = "路径：" + path + "未找到！";
/*     */ 
/* 114 */       throw new RuntimeException(errorMsg, e);
/*     */     }
/* 116 */     return (url != null) ? url.getPath() : null;
/*     */   }
/*     */ 
/*     */   public static Locale getLocale(HttpServletRequest request)
/*     */   {
/* 124 */     return Locale.getDefault();
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.RequestUtils
 * JD-Core Version:    0.5.4
 */