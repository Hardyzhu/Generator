/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import com.gsoft.framework.core.exception.BusException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public class DESUtils
/*     */ {
/*     */   private static final String ALGORITHM_DESEDE = "DESede";
/*     */   private static final String DECRYPT_ENCODING = "UTF-8";
/*     */ 
/*     */   public static String encrypt(String key, String info)
/*     */   {
/*     */     byte[] desKey;
/*     */     try
/*     */     {
/*  39 */       desKey = build3DesKey(key);
/*     */     } catch (Exception e) {
/*  41 */       throw new RuntimeException(new StringBuilder().append("秘钥构建失败：").append(e.getMessage()).toString());
/*     */     }
/*     */ 
/*  44 */     SecretKey secretKey = new SecretKeySpec(desKey, "DESede");
/*  45 */     Cipher cipher = null;
/*  46 */     byte[] byteResult = null;
/*     */     try
/*     */     {
/*  49 */       cipher = Cipher.getInstance("DESede");
/*     */     } catch (NoSuchAlgorithmException e) {
/*  51 */       throw new BusException(e.getMessage(), e);
/*     */     } catch (NoSuchPaddingException e) {
/*  53 */       throw new BusException(e.getMessage(), e);
/*     */     }
/*     */ 
/*  56 */     if (cipher != null)
/*     */     {
/*     */       try {
/*  59 */         cipher.init(1, secretKey);
/*     */       } catch (InvalidKeyException e) {
/*  61 */         throw new BusException(e.getMessage(), e);
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/*  66 */         byteResult = cipher.doFinal(info.getBytes("UTF-8"));
/*     */       } catch (IllegalBlockSizeException e) {
/*  68 */         throw new BusException(e.getMessage(), e);
/*     */       } catch (BadPaddingException e) {
/*  70 */         throw new BusException(e.getMessage(), e);
/*     */       } catch (UnsupportedEncodingException e) {
/*  72 */         throw new BusException(e.getMessage(), e);
/*     */       }
/*     */     }
/*     */ 
/*  76 */     if (byteResult == null) {
/*  77 */       return "";
/*     */     }
/*     */ 
/*  80 */     return byte2HexStr(byteResult);
/*     */   }
/*     */ 
/*     */   public static String decrypt(String dest, String key)
/*     */     throws IllegalBlockSizeException, BadPaddingException
/*     */   {
/*  94 */     SecretKey secretKey = new SecretKeySpec(build3DesKey(key), "DESede");
/*     */ 
/*  96 */     Cipher cipher = null;
/*     */     try {
/*  98 */       cipher = Cipher.getInstance("DESede");
/*     */     } catch (NoSuchAlgorithmException e) {
/* 100 */       throw new BusException(e.getMessage(), e);
/*     */     } catch (NoSuchPaddingException e) {
/* 102 */       throw new BusException(e.getMessage(), e);
/*     */     }
/*     */ 
/* 105 */     byte[] byteResult = null;
/* 106 */     if (cipher != null) {
/*     */       try {
/* 108 */         cipher.init(2, secretKey);
/*     */       } catch (InvalidKeyException e) {
/* 110 */         throw new BusException(e.getMessage(), e);
/*     */       }
/* 112 */       byteResult = cipher.doFinal(str2ByteArray(dest));
/*     */     }
/*     */     try
/*     */     {
/* 116 */       return new String(byteResult, "UTF-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException e)
/*     */     {
/*     */     }
/* 121 */     return "";
/*     */   }
/*     */ 
/*     */   private static String byte2HexStr(byte[] b)
/*     */   {
/* 131 */     StringBuilder sb = new StringBuilder();
/* 132 */     for (int i = 0; i < b.length; ++i) {
/* 133 */       String s = Integer.toHexString(b[i] & 0xFF);
/* 134 */       if (s.length() == 1) {
/* 135 */         sb.append("0");
/*     */       }
/*     */ 
/* 138 */       sb.append(s.toUpperCase());
/*     */     }
/*     */ 
/* 141 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private static byte[] str2ByteArray(String s)
/*     */   {
/* 151 */     int byteArrayLength = s.length() / 2;
/* 152 */     byte[] b = new byte[byteArrayLength];
/* 153 */     for (int i = 0; i < byteArrayLength; ++i) {
/* 154 */       byte b0 = (byte)Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16).intValue();
/* 155 */       b[i] = b0;
/*     */     }
/* 157 */     return b;
/*     */   }
/*     */ 
/*     */   private static byte[] build3DesKey(String keyStr)
/*     */   {
/* 168 */     byte[] key = new byte[24];
/*     */     try
/*     */     {
/* 171 */       byte[] temp = keyStr.getBytes("UTF-8");
/* 172 */       if (key.length > temp.length)
/* 173 */         System.arraycopy(temp, 0, key, 0, temp.length);
/*     */       else
/* 175 */         System.arraycopy(temp, 0, key, 0, key.length);
/*     */     }
/*     */     catch (UnsupportedEncodingException e)
/*     */     {
/*     */     }
/* 180 */     return key;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.DESUtils
 * JD-Core Version:    0.5.4
 */