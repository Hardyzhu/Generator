/*    */ package com.gsoft.framework.util;
/*    */ 
///*    */ import com.gsoft.framework.security.AccountPrincipal;
/*    */ import org.apache.shiro.subject.Subject;
/*    */ 
/*    */ public class SecurityUtils
/*    */ {
/*    */   public static Object getPrincipal()
/*    */   {
/* 12 */     return org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
/*    */   }
/*    */ 
///*    */   public static AccountPrincipal getAccount()
///*    */   {
///*    */     Object principal;
///*    */     try
///*    */     {
///* 21 */       principal = getPrincipal();
///*    */     } catch (Exception e) {
///* 23 */       principal = null;
///*    */     }
///* 25 */     if ((principal != null) && (AccountPrincipal.class.isAssignableFrom(principal.getClass()))) {
///* 26 */       return (AccountPrincipal)principal;
///*    */     }
///* 28 */     return null;
///*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.SecurityUtils
 * JD-Core Version:    0.5.4
 */