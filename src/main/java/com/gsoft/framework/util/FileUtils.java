/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class FileUtils
/*    */ {
/*    */   public static String formatFileName(String fileName)
/*    */   {
/* 29 */     char[] content = new char[fileName.length()];
/* 30 */     char[] result = new char[fileName.length()];
/* 31 */     fileName.getChars(0, fileName.length(), content, 0);
/*    */ 
/* 33 */     int len = 0;
/* 34 */     for (int i = 0; i < content.length; ++i) {
/* 35 */       if ((content[i] == '%') && (content[(i + 1)] == '2') && (content[(i + 2)] == '0'))
/*    */       {
/* 37 */         result[(len++)] = ' ';
/* 38 */         i += 2;
/*    */       } else {
/* 40 */         result[(len++)] = content[i];
/*    */       }
/*    */     }
/* 43 */     return String.valueOf(result, 0, len);
/*    */   }
/*    */ 
/*    */   public static File checkAndCreateDir(String dirPath)
/*    */   {
/* 52 */     File dir = new File(dirPath);
/* 53 */     if (!dir.exists()) {
/* 54 */       dir.mkdir();
/*    */     }
/* 56 */     return dir;
/*    */   }
/*    */ 
/*    */   public static void checkAndCreateSubDir(String dirPath, String subPath)
/*    */   {
/* 61 */     subPath = subPath.replace("\\", "/");
/* 62 */     String[] subDirs = subPath.split("/");
/*    */ 
/* 64 */     String checkPath = dirPath;
/* 65 */     for (String subDir : subDirs) {
/* 66 */       checkPath = checkPath + "/" + subDir;
/* 67 */       checkAndCreateDir(checkPath);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.FileUtils
 * JD-Core Version:    0.5.4
 */