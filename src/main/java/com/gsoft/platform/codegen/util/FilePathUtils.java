/*    */ package com.gsoft.platform.codegen.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import org.springframework.core.io.ClassPathResource;
/*    */ import org.springframework.core.io.Resource;
/*    */ 
/*    */ public class FilePathUtils
/*    */ {
/*    */   public static String getClassesFilePath()
/*    */   {
/* 27 */     String classPath = "ftl";
/* 28 */     Resource resource = new ClassPathResource(classPath);
/*    */     File file;
/*    */     try
/*    */     {
/* 31 */       file = resource.getFile();
/*    */     } catch (IOException e) {
/* 33 */       return null;
/*    */     }
/* 35 */     return file.getAbsolutePath().replace(classPath, "");
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 39 */     System.out.println(getClassesFilePath());
/* 40 */     System.out.println(System.getProperties());
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.util.FilePathUtils
 * JD-Core Version:    0.5.4
 */