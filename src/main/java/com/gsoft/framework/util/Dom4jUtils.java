/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.OutputFormat;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.dom4j.io.XMLWriter;
/*     */ 
/*     */ public class Dom4jUtils
/*     */ {
/*  45 */   private static final Log log = LogFactory.getLog(Dom4jUtils.class);
/*     */ 
/*     */   public static Document saxParse(String filePath)
/*     */   {
/*  53 */     Document doc = null;
/*  54 */     SAXReader saxReader = new SAXReader();
/*     */     try {
/*  56 */       doc = saxReader.read(filePath);
/*     */     } catch (DocumentException e) {
/*  58 */       log.error("SAXReader解析xml文件【" + filePath + "】异常:" + e.getMessage());
/*     */     }
/*  60 */     return doc;
/*     */   }
/*     */ 
/*     */   public static Document saxParse(InputStream input) {
/*  64 */     Document doc = null;
/*  65 */     SAXReader saxReader = new SAXReader();
/*     */     try {
/*  67 */       doc = saxReader.read(input);
/*     */     } catch (DocumentException e) {
/*  69 */       log.error("SAXReader解析xml文件异常:" + e.getMessage());
/*     */     }
/*  71 */     return doc;
/*     */   }
/*     */ 
/*     */   public static void writeFormatDocToFile(String filePath, Document doc, String encoding)
/*     */   {
/*  82 */     FileOutputStream os = null;
/*     */     try {
/*  84 */       os = new FileOutputStream(filePath);
/*     */     } catch (FileNotFoundException e) {
/*  86 */       throw new RuntimeException(e);
/*     */     }
/*  88 */     formatXml(doc, os, encoding);
/*     */   }
/*     */ 
/*     */   public static void formatXml(Document doc, OutputStream os, String encoding)
/*     */   {
/*  98 */     if (os == null) return;
/*  99 */     OutputFormat format = null;
/* 100 */     XMLWriter output = null;
/*     */     try {
/* 102 */       format = OutputFormat.createPrettyPrint();
/* 103 */       format.setEncoding(encoding);
/* 104 */       format.setOmitEncoding(false);
/* 105 */       output = new XMLWriter(new BufferedWriter(new OutputStreamWriter(os, "UTF-8")), format);
/* 106 */       output.write(doc);
/*     */     } catch (IOException e) {
/* 108 */       log.error("xml文件IO写入错误:" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 111 */         output.close();
/*     */       } catch (IOException e) {
/* 113 */         log.info("文件关闭失败：" + e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void addAttribute(Element modelElement, String attrName, String attrValue)
/*     */   {
/* 124 */     if (attrValue != null)
/* 125 */       modelElement.addAttribute(attrName, attrValue);
/*     */   }
/*     */ 
/*     */   public static Document parseText(String xmlText)
/*     */   {
/*     */     Document doc;
/*     */     try
/*     */     {
/* 136 */       doc = DocumentHelper.parseText(xmlText);
/*     */     } catch (DocumentException e) {
/* 138 */       doc = null;
/* 139 */       e.printStackTrace();
/*     */     }
/* 141 */     return doc;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.Dom4jUtils
 * JD-Core Version:    0.5.4
 */