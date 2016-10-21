/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class StringUtils extends org.springframework.util.StringUtils
/*     */ {
/*     */   private static final int FLAG_BEG = 1;
/*     */   private static final int FLAG_END = 9;
/*     */ 
/*     */   public static boolean isEmpty(String str)
/*     */   {
/*  30 */     return (str == null) || (str.equals(""));
/*     */   }
/*     */ 
/*     */   public static boolean isNotEmpty(String str)
/*     */   {
/*  39 */     return !isEmpty(str);
/*     */   }
/*     */ 
/*     */   public static String upperCaseFirstLetter(String str)
/*     */   {
/*  48 */     if ((str == null) || (str.length() == 0)) return str;
/*  49 */     return str.substring(0, 1).toUpperCase() + str.substring(1);
/*     */   }
/*     */ 
/*     */   public static String lowerCaseFirstLetter(String str)
/*     */   {
/*  58 */     if ((str == null) || (str.length() == 0)) return str;
/*  59 */     return str.substring(0, 1).toLowerCase() + str.substring(1);
/*     */   }
/*     */ 
/*     */   public static String findNotEmpty(String[] strs)
/*     */   {
/*  67 */     for (String str : strs) {
/*  68 */       if (isNotEmpty(str)) {
/*  69 */         return str;
/*     */       }
/*     */     }
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   public static String decodeXmlText(String str)
/*     */   {
/*  81 */     if (isEmpty(str)) {
/*  82 */       return null2Empty(str);
/*     */     }
/*     */ 
/*  85 */     StringBuffer results = new StringBuffer();
/*  86 */     int mflag = -1;
/*  87 */     int begPos = 0; int endPos = 0;
/*  88 */     for (int i = 0; i < str.length(); ++i) {
/*  89 */       char c = str.charAt(i);
/*  90 */       if ((i > 0) && (c == '#') && (str.substring(i - 1, i + 1).equals("&#")))
/*     */       {
/*  92 */         mflag = 1;
/*  93 */         begPos = i;
/*  94 */       } else if ((c == ';') && (mflag == 1)) {
/*  95 */         mflag = 9;
/*  96 */         endPos = i;
/*     */       }
/*  98 */       if (mflag == 1)
/*     */         continue;
/* 100 */       if ((mflag == 9) && (endPos > begPos)) {
/* 101 */         results.deleteCharAt(results.length() - 1);
/* 102 */         results.append(String.valueOf((char)Integer.valueOf(str.substring(begPos + 1, endPos), 10).intValue()));
/* 103 */         mflag = -1;
/*     */       } else {
/* 105 */         results.append(c);
/*     */       }
/*     */     }
/* 108 */     return results.toString();
/*     */   }
/*     */ 
/*     */   public static String null2Empty(String str)
/*     */   {
/* 117 */     return (str == null) ? "" : str;
/*     */   }
/*     */ 
/*     */   public static String prefixUpperAnd2Lower(String str, String prefix)
/*     */   {
/* 126 */     if (str == null) {
/* 127 */       return null;
/*     */     }
/*     */ 
/* 130 */     String word = str + prefix;
/* 131 */     Pattern pattern = Pattern.compile("[A-Z]");
/* 132 */     Matcher matcher = pattern.matcher(word);
/* 133 */     String[] results = pattern.split(word);
/*     */ 
/* 135 */     if ((results.length == 2) && (str.length() == 1)) {
/* 136 */       return str.toLowerCase();
/*     */     }
/*     */ 
/* 139 */     int index = 1;
/* 140 */     while ((matcher.find()) && (index < results.length)) {
/* 141 */       results[index] = (prefix + matcher.group().toLowerCase() + results[index]);
/* 142 */       ++index;
/*     */     }
/* 144 */     String result = arrayToDelimitedString(results, "");
/*     */ 
/* 146 */     return result.substring(0, result.length() - 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.StringUtils
 * JD-Core Version:    0.5.4
 */