/*    */ package com.gsoft.framework.core.web.controller;
/*    */ 
/*    */ import com.gsoft.framework.core.i18n.I18nKey;
/*    */ import java.util.Locale;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.MessageSource;
/*    */ import org.springframework.context.NoSuchMessageException;
/*    */ 
/*    */ public abstract class BaseController
/*    */ {
/*    */   public static final String URL_TYPE_PAGE = "page";
/*    */   public static final String URL_TYPE_DATA = "data";
/*    */   protected final Log logger;
/*    */   protected MessageSource messageSource;
/*    */ 
/*    */   public BaseController()
/*    */   {
/* 39 */     this.logger = LogFactory.getLog(super.getClass());
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void init(MessageSource messageSource)
/*    */   {
/* 46 */     this.messageSource = messageSource;
/*    */   }
/*    */ 
/*    */   abstract String getUrlType();
/*    */ 
/*    */   protected String getI18Message(String name, String[] args)
/*    */   {
/*    */     try
/*    */     {
/* 71 */       Locale local = Locale.getDefault();
/* 72 */       return this.messageSource.getMessage(name, args, local);
/*    */     } catch (NoSuchMessageException e) {
/* 74 */       this.logger.warn("messages文件中【" + name + "】没有找到！");
/* 75 */     }return null;
/*    */   }
/*    */ 
/*    */   protected String getI18Message(String name) {
/* 79 */     return getI18Message(name, null);
/*    */   }
/*    */ 
/*    */   protected String getI18Message(I18nKey i18nKey) {
/* 83 */     return getI18Message(i18nKey.getKey(), i18nKey.getArgs());
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.BaseController
 * JD-Core Version:    0.5.4
 */