/*    */ package com.gsoft.platform.codegen;
/*    */ 
/*    */ public abstract class AbstractJavaDataModel extends AbstractDataModel
/*    */ {
/*    */   protected String packageName;
/*    */   protected String className;
/* 15 */   protected String toString = "";
/*    */ 
/*    */   public String getPackageName() {
/* 18 */     return this.packageName;
/*    */   }
/*    */ 
/*    */   public void setPackageName(String packageName) {
/* 22 */     this.packageName = packageName;
/*    */   }
/*    */ 
/*    */   public String getClassName() {
/* 26 */     return this.className;
/*    */   }
/*    */ 
/*    */   public void setClassName(String className) {
/* 30 */     this.className = className;
/*    */   }
/*    */ 
/*    */   public String getToString() {
/* 34 */     return this.toString;
/*    */   }
/*    */ 
/*    */   public void setToString(String toString) {
/* 38 */     if (toString == null) toString = "\"\"";
/* 39 */     this.toString = toString;
/*    */   }
/*    */ 
/*    */   public String getFullPath() {
/* 43 */     return this.outPath + "/" + getPackageName().replace('.', '/') + "/" + getClassName() + ".java";
/*    */   }
/*    */ 
/*    */   public String getClassPath() {
/* 47 */     return getPackageName() + "." + getClassName();
/*    */   }
/*    */ 
/*    */   protected String upperFirstChar(String str) {
/* 51 */     String subStr = "";
/* 52 */     if (str.length() > 1) {
/* 53 */       subStr = str.substring(1);
/*    */     }
/* 55 */     return str.substring(0, 1).toUpperCase() + subStr;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.AbstractJavaDataModel
 * JD-Core Version:    0.5.4
 */