/*    */ package com.gsoft.framework.core.web.view;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.hibernate.validator.InvalidValue;
/*    */ 
/*    */ public class InvalidMessage extends Message
/*    */ {
/*    */   private static final long serialVersionUID = -1302692808682355608L;
/* 22 */   private Map<String, String> invalidMessages = new HashMap();
/*    */ 
/*    */   public InvalidMessage(InvalidValue[] invalidValues) {
/* 25 */     super("111112", "");
/* 26 */     this.info = buildInvalidMessage(invalidValues);
/*    */   }
/*    */ 
/*    */   private String buildInvalidMessage(InvalidValue[] invalidValues) {
/* 30 */     StringBuffer messages = new StringBuffer();
/* 31 */     for (InvalidValue invalidValue : invalidValues) {
/* 32 */       if (messages.length() > 0) {
/* 33 */         messages.append("\n");
/*    */       }
/*    */ 
/* 36 */       messages.append(invalidValue.getBeanClass().getName() + "." + invalidValue.getPropertyName()).append(invalidValue.getMessage()).append(" ,实际值[").append(invalidValue.getValue() + ",length=" + invalidValue.getValue().toString().length() + "]");
/*    */ 
/* 40 */       this.invalidMessages.put(invalidValue.getPropertyName(), invalidValue.getMessage());
/*    */     }
/*    */ 
/* 43 */     return messages.toString();
/*    */   }
/*    */ 
/*    */   public Map<String, String> getInvalidMessages() {
/* 47 */     return this.invalidMessages;
/*    */   }
/*    */ 
/*    */   public void setInvalidMessages(Map<String, String> invalidMessages) {
/* 51 */     this.invalidMessages = invalidMessages;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.view.InvalidMessage
 * JD-Core Version:    0.5.4
 */