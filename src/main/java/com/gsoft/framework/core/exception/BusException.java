/*     */ package com.gsoft.framework.core.exception;
/*     */ 
/*     */ import com.gsoft.framework.core.web.view.Message;
/*     */ import com.gsoft.framework.util.StringUtils;
/*     */ import java.util.Locale;
/*     */ import org.springframework.context.MessageSource;
/*     */ import org.springframework.context.NoSuchMessageException;
/*     */ 
/*     */ public class BusException extends NestedRuntimeException
/*     */   implements ExceptionMessage
/*     */ {
/*     */   private static final long serialVersionUID = 6646424463408177264L;
/*     */   private String code;
/*     */   private String[] args;
/*     */   private String i18nKey;
/*     */ 
/*     */   public BusException(String msg)
/*     */   {
/*  51 */     this("999999", msg);
/*     */   }
/*     */ 
/*     */   public BusException(String i18nKey, String[] args)
/*     */   {
/*  59 */     this("999999", i18nKey, args);
/*     */   }
/*     */ 
/*     */   public BusException(String code, String i18nKey, String[] args)
/*     */   {
/*  68 */     this(code, i18nKey);
/*  69 */     this.args = args;
/*  70 */     this.i18nKey = i18nKey;
/*     */   }
/*     */ 
/*     */   public BusException(String code, String msg)
/*     */   {
/*  78 */     super(msg);
/*  79 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public BusException(String msg, Throwable cause)
/*     */   {
/*  87 */     this("999999", msg, cause);
/*     */   }
/*     */ 
/*     */   public BusException(String code, String msg, Throwable cause)
/*     */   {
/*  96 */     super(msg, cause);
/*  97 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Message getExceptionMessage() {
/* 101 */     return new Message(this.code, getMessage());
/*     */   }
/*     */ 
/*     */   public Message getExceptionMessage(MessageSource messageSource, Locale locale) {
/* 105 */     String msg = getMessage();
/* 106 */     if (StringUtils.isNotEmpty(this.i18nKey)) {
/*     */       try {
/* 108 */         msg = messageSource.getMessage(this.i18nKey, this.args, locale);
/*     */       } catch (NoSuchMessageException e) {
/* 110 */         msg = this.i18nKey;
/*     */       }
/*     */     }
/* 113 */     return new Message(this.code, msg);
/*     */   }
/*     */ 
/*     */   public String[] getArgs()
/*     */   {
/* 120 */     return this.args;
/*     */   }
/*     */ 
/*     */   public void setArgs(String[] args)
/*     */   {
/* 127 */     this.args = args;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.BusException
 * JD-Core Version:    0.5.4
 */