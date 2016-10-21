/*    */ package com.gsoft.framework.core.exception;
/*    */ 
/*    */ import com.gsoft.framework.core.web.view.InvalidMessage;
/*    */ import com.gsoft.framework.core.web.view.Message;
/*    */ import java.util.Locale;
/*    */ import org.hibernate.validator.InvalidValue;
/*    */ import org.springframework.context.MessageSource;
/*    */ 
/*    */ public class DomainValidatorException extends YouiException
/*    */   implements ExceptionMessage
/*    */ {
/*    */   private static final long serialVersionUID = -1979382516757606789L;
/*    */   private InvalidValue[] invalidValues;
/*    */ 
/*    */   public DomainValidatorException(InvalidValue[] invalidValues)
/*    */   {
/* 42 */     super(new InvalidMessage(invalidValues).info);
/* 43 */     this.invalidValues = invalidValues;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 48 */     return getExceptionMessage().getInfo();
/*    */   }
/*    */ 
/*    */   public Message getExceptionMessage() {
/* 52 */     return new InvalidMessage(this.invalidValues);
/*    */   }
/*    */ 
/*    */   public Message getExceptionMessage(MessageSource messageSource, Locale locale)
/*    */   {
/* 58 */     return new InvalidMessage(this.invalidValues);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.DomainValidatorException
 * JD-Core Version:    0.5.4
 */