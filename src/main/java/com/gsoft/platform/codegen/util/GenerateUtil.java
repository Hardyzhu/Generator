/*    */ package com.gsoft.platform.codegen.util;
/*    */ 
/*    */ import com.gsoft.framework.util.Assert;
/*    */ import com.gsoft.platform.codegen.GenerateConfig;
/*    */ import com.gsoft.platform.codegen.GenerateConstants;
/*    */ import com.gsoft.platform.codegen.model.Model;
/*    */ 
/*    */ public class GenerateUtil
/*    */ {
/*    */   public static String replaceSrcToTest(String srcPath)
/*    */   {
/* 22 */     String srcHome = GenerateConfig.getInstance().getConfig(GenerateConstants.GENERATOR_SRC_HOME);
/* 23 */     String testHome = GenerateConfig.getInstance().getConfig(GenerateConstants.GENERATOR_TEST_HOME);
/* 24 */     return srcPath.replaceAll(srcHome, testHome);
/*    */   }
/*    */ 
/*    */   public static String classNameToPath(String className) {
/* 28 */     return className.replace(".", "/");
/*    */   }
/*    */ 
/*    */   public static String getModelSimpleClassName(Model model)
/*    */   {
/* 37 */     Assert.notNull(model, "模型对象不能为空！");
/* 38 */     String modelName = model.getName();
/* 39 */     Assert.notNull(modelName, "模型名称不能为空！");
/* 40 */     Assert.isTrue(modelName.length() > 1, "模型名[" + modelName + "]长度必须大于1！");
/* 41 */     return modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
/*    */   }
/*    */ 
/*    */   public static String getRefModelClassName(Model refModel, Model model, String packageName)
/*    */   {
/* 53 */     String simpleClassName = getModelSimpleClassName(refModel);
/*    */ 
/* 55 */     String refPackageName = 
/* 56 */       packageName.replace("." + model.getModuleName() + ".entity", "." + refModel.getModuleName() + ".entity");
/* 57 */     return refPackageName + "." + simpleClassName;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.util.GenerateUtil
 * JD-Core Version:    0.5.4
 */