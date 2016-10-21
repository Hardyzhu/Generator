/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.apache.shiro.crypto.hash.Md5Hash;
/*    */ 
/*    */ public class PasswordUtils
/*    */ {
/*    */   public static String md5Password(String password)
/*    */   {
/* 39 */     return new Md5Hash(password).toHex();
/*    */   }
/*    */ 
/*    */   public static String md5Password(String password, String salt) {
/* 43 */     return new Md5Hash(password, salt).toHex();
/*    */   }
/*    */ 
/*    */   public static String randomPassword()
/*    */   {
/* 52 */     char[] pwdchs = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 'd', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
/*    */ 
/* 57 */     Random rand = new Random();
/* 58 */     String pwd = "";
/*    */ 
/* 60 */     for (int j = 0; j < 6; ++j) {
/* 61 */       pwd = pwd + pwdchs[rand.nextInt(62)];
/*    */     }
/* 63 */     return pwd;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.PasswordUtils
 * JD-Core Version:    0.5.4
 */