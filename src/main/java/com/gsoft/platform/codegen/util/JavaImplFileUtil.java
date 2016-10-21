/*    */ package com.gsoft.platform.codegen.util;
/*    */ 
/*    */ public class JavaImplFileUtil
/*    */ {
/*    */   public static String getImplFilePath(String interfacePath, String implDir, String suffix)
/*    */   {
/*  7 */     int sIndex = interfacePath.lastIndexOf("/");
/*  8 */     String prefix = interfacePath.substring(0, sIndex + 1);
/*  9 */     String implPath = prefix + implDir + "/" + interfacePath.substring(sIndex + 1).replace(".java", new StringBuilder(String.valueOf(suffix)).append(".java").toString());
/* 10 */     return implPath;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.util.JavaImplFileUtil
 * JD-Core Version:    0.5.4
 */