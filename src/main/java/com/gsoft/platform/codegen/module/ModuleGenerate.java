/*    */ package com.gsoft.platform.codegen.module;
/*    */ 
/*    */ import com.gsoft.platform.codegen.CodeGenerator;
/*    */ import com.gsoft.platform.codegen.DataModel;
/*    */ import com.gsoft.platform.codegen.GenerateConfig;
/*    */ import com.gsoft.platform.codegen.GenerateConstants;
/*    */ import com.gsoft.platform.codegen.freemarker.FreemarkerFactory;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ModuleGenerate
/*    */   implements CodeGenerator
/*    */ {
/*    */   public String[] generatorCode(DataModel domainData, GenerateConfig config)
/*    */   {
/* 24 */     Map data = domainData.getData();
/* 25 */     String path = domainData.getFullPath();
/*    */ 
/* 28 */     String templataPath = 
/* 29 */       GenerateConfig.getInstance().getConfig(GenerateConstants.GENERATOR_DOMAIN);
/*    */ 
/* 31 */     String ibatisPath = templataPath.replace(".ftl", "SqlMap.ftl");
/*    */ 
/* 33 */     FreemarkerFactory.getInstance().writerFile(data, path, templataPath);
/* 34 */     FreemarkerFactory.getInstance().writerFile(data, path.replace(".java", ".xml"), ibatisPath);
/*    */ 
/* 36 */     return new String[] { 
/* 37 */       path };
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.module.ModuleGenerate
 * JD-Core Version:    0.5.4
 */