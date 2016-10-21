/*    */ package com.gsoft.platform.codegen.pdm;
/*    */ 
/*    */ import com.gsoft.framework.util.Dom4jUtils;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.dom4j.Document;
/*    */ import org.springframework.context.ResourceLoaderAware;
/*    */ import org.springframework.core.io.DefaultResourceLoader;
/*    */ import org.springframework.core.io.Resource;
/*    */ import org.springframework.core.io.ResourceLoader;
/*    */ 
/*    */ public class PdmXmlReader
/*    */   implements ResourceLoaderAware
/*    */ {
/* 41 */   private static final Log logger = LogFactory.getLog(PdmXmlReader.class);
/*    */ 
/* 43 */   protected ResourceLoader resourceLoader = new DefaultResourceLoader();
/*    */ 
/*    */   public void setResourceLoader(ResourceLoader resourceLoader)
/*    */   {
/* 53 */     this.resourceLoader = resourceLoader;
/*    */   }
/*    */ 
/*    */   public Document read(String resourceLocation)
/*    */   {
/* 61 */     Document modelDoc = null;
/* 62 */     Document pdmDoc = null;
/*    */ 
/* 64 */     Resource recource = 
/* 65 */       this.resourceLoader.getResource(resourceLocation);
/* 66 */     if (recource.exists()) {
/* 67 */       InputStream input = null;
/*    */       try {
/* 69 */         input = recource.getInputStream();
/*    */       } catch (IOException e) {
/* 71 */         logger.error("PDM文件【" + resourceLocation + "】读取失败！");
/*    */       }
/* 73 */       if (input != null) {
/* 74 */         pdmDoc = Dom4jUtils.saxParse(input);
/*    */ 
/* 76 */         modelDoc = new Pdm2ModelXml(pdmDoc).getModelDoc();
/*    */       }
/*    */     }
/*    */ 
/* 80 */     return modelDoc;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.pdm.PdmXmlReader
 * JD-Core Version:    0.5.4
 */