/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import com.gsoft.framework.util.Dom4jUtils;
/*     */ import java.io.PrintStream;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class ModelFile
/*     */ {
/*     */   private String filePath;
/*     */   private Document doc;
/*     */ 
/*     */   public ModelFile(String filePath, Document doc)
/*     */   {
/*  38 */     this.filePath = filePath;
/*  39 */     this.doc = doc;
/*     */   }
/*     */ 
/*     */   public void saveModel(Model model)
/*     */   {
/*  47 */     String modelXPath = model.getModelXPath();
/*  48 */     Element modelElement = (Element)this.doc.selectSingleNode(modelXPath);
/*  49 */     Element moduleElement = modelElement.getParent();
/*  50 */     moduleElement.remove(modelElement);
/*  51 */     moduleElement.add(createModelElement(model));
/*     */   }
/*     */ 
/*     */   public void save()
/*     */   {
/*  58 */     String encoding = "UTF-8";
/*  59 */     Dom4jUtils.writeFormatDocToFile(this.filePath, this.doc, encoding);
/*     */   }
/*     */ 
/*     */   private Element createModelElement(Model model)
/*     */   {
/*  69 */     Element modelElement = DocumentFactory.getInstance().createElement("model");
/*  70 */     Dom4jUtils.addAttribute(modelElement, "id", model.getId());
/*  71 */     Dom4jUtils.addAttribute(modelElement, "name", model.getName());
/*  72 */     Dom4jUtils.addAttribute(modelElement, "caption", model.getCaption());
/*  73 */     Dom4jUtils.addAttribute(modelElement, "table", model.getTable());
/*     */ 
/*  75 */     Dom4jUtils.addAttribute(modelElement, "idType", model.getIdType());
/*  76 */     Dom4jUtils.addAttribute(modelElement, "toString", model.getToString());
/*  77 */     return modelElement;
/*     */   }
/*     */ 
/*     */   public String getFilePath()
/*     */   {
/*  84 */     return this.filePath;
/*     */   }
/*     */ 
/*     */   public void setFilePath(String filePath)
/*     */   {
/*  91 */     this.filePath = filePath;
/*     */   }
/*     */ 
/*     */   public Document getDoc()
/*     */   {
/*  98 */     return this.doc;
/*     */   }
/*     */ 
/*     */   public void setDoc(Document doc)
/*     */   {
/* 105 */     this.doc = doc;
/*     */   }
/*     */ 
/*     */   public Element getModelElement(Model model) {
/* 109 */     String xPath = "models/module[@name='" + model.getModuleName() + "']/model[@name='" + model.getName() + "']";
/* 110 */     Node node = this.doc.selectSingleNode(xPath);
/* 111 */     System.out.println("-------" + xPath + ":" + node);
/* 112 */     return (node == null) ? null : (Element)node;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.ModelFile
 * JD-Core Version:    0.5.4
 */