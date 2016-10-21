/*     */ package com.gsoft.platform.codegen.freemarker;
/*     */ 
/*     */ import com.gsoft.platform.codegen.GenerateConfig;
/*     */ import com.gsoft.platform.codegen.GenerateConstants;
/*     */ import com.gsoft.platform.codegen.GenerateException;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.DefaultObjectWrapper;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.core.io.DefaultResourceLoader;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.ResourceLoader;
/*     */ 
/*     */ public class FreemarkerFactory
/*     */ {
/*  37 */   private final Log logger = LogFactory.getLog(FreemarkerFactory.class);
/*     */ 
/*  39 */   private static FreemarkerFactory freemarkerFactory = null;
/*     */   private Configuration cfg;
/*  43 */   ResourceLoader resourceLoader = new DefaultResourceLoader();
/*     */ 
/*     */   private FreemarkerFactory() {
/*  46 */     initConfiguration();
/*     */   }
/*     */ 
/*     */   public static FreemarkerFactory getInstance() {
/*  50 */     if (freemarkerFactory == null) {
/*  51 */       freemarkerFactory = new FreemarkerFactory();
/*     */     }
/*  53 */     return freemarkerFactory;
/*     */   }
/*     */ 
/*     */   public void writerFile(Map<String, Object> data, String codePath, String temlatePath)
/*     */   {
/*     */     try
/*     */     {
/*  64 */       File file = new File(codePath);
/*  65 */       if (file.exists())
/*     */       {
/*  68 */         return;
/*     */       }
/*  70 */       this.logger.info("新建文件：" + codePath);
/*     */ 
/*  72 */       Template template = null;
/*     */       try {
/*  74 */         template = this.cfg.getTemplate(temlatePath);
/*     */       } catch (IOException e) {
/*  76 */         this.logger.error("模版【" + temlatePath + "】读取异常：" + e.getMessage());
/*  77 */         return;
/*     */       }
/*     */ 
/*  81 */       Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
/*     */       try
/*     */       {
/*  84 */         template.process(data, writer);
/*  85 */         this.logger.info("代码生成成功！");
/*     */       } catch (TemplateException e) {
/*  87 */         this.logger.error("模版处理异常：" + e.getMessage());
/*     */       } finally {
/*  89 */         writer.close();
/*     */       }
/*     */     } catch (IOException e) {
/*  92 */       this.logger.error("文件写入异常：" + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initConfiguration()
/*     */   {
/* 101 */     this.cfg = new Configuration();
/* 102 */     this.cfg.setDefaultEncoding("UTF-8");
/* 103 */     this.cfg.setOutputEncoding("UTF-8");
/*     */     try {
/* 105 */       this.cfg.setDirectoryForTemplateLoading(new File(getDirectoryForTemplate()));
/*     */     } catch (IOException e) {
/* 107 */       this.logger.info("配置文件读取错误：" + e.getMessage());
/* 108 */       throw new GenerateException(e.getMessage());
/*     */     }
/* 110 */     this.cfg.setObjectWrapper(new DefaultObjectWrapper());
/*     */   }
/*     */ 
/*     */   private String getDirectoryForTemplate() {
/* 114 */     Resource path = this.resourceLoader.getResource("classpath:");
/*     */     File classPath;
/*     */     try
/*     */     {
/* 118 */       classPath = path.getFile();
/*     */     } catch (IOException e) {
/* 120 */       return null;
/*     */     }
/* 123 */     return classPath.getAbsolutePath() + "/" + 
/* 124 */       GenerateConfig.getInstance().getConfig(GenerateConstants.GENERATOR_FTLDIRECTORY);
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.freemarker.FreemarkerFactory
 * JD-Core Version:    0.5.4
 */