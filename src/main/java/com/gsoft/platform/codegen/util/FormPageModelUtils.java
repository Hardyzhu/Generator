/*     */ package com.gsoft.platform.codegen.util;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class FormPageModelUtils
/*     */ {
/*     */   public static Element buildGrid(String gridName, String gridCaption, String gridSrc, List<Param> inputParams, List<Param> outputParams)
/*     */   {
/*  44 */     Document pageElement = DocumentFactory.getInstance().createDocument();
/*     */ 
/*  46 */     Element gridElement = pageElement.addElement("grid");
/*  47 */     String gridId = "grid_" + gridName;
/*  48 */     addWidgetAttribute(gridElement, "component", "grid", gridId);
/*  49 */     gridElement.addAttribute("id", gridId);
/*  50 */     gridElement.addAttribute("caption", gridCaption);
/*  51 */     gridElement.addAttribute("submit", "查 询 ");
/*  52 */     gridElement.addAttribute("reset", "重 置");
/*  53 */     gridElement.addAttribute("src", gridSrc);
/*     */ 
/*  55 */     Element fieldLayoutElement = gridElement.addElement("fieldLayout");
/*  56 */     addWidgetAttribute(fieldLayoutElement, "component", "fieldLayout", "fieldLayout_" + gridName);
/*  57 */     String prefix = "query";
/*  58 */     fieldLayoutElement.addAttribute("prefix", prefix);
/*  59 */     fieldLayoutElement.addAttribute("columns", "2");
/*  60 */     fieldLayoutElement.addAttribute("caption", "查询条件");
/*     */ 
/*  62 */     if (inputParams != null) {
/*  63 */       for (Param param : inputParams) {
/*  64 */         addFieldElement(fieldLayoutElement, param, prefix);
/*     */       }
/*     */     }
/*     */ 
/*  68 */     if (outputParams != null) {
/*  69 */       for (Param param : outputParams) {
/*  70 */         addGridColElement(gridElement, param);
/*     */       }
/*     */     }
/*     */ 
/*  74 */     return gridElement.createCopy();
/*     */   }
/*     */ 
/*     */   public static Element buildForm(String formName, String formCaption, List<Param> params)
/*     */   {
/*  84 */     Document document = DocumentFactory.getInstance().createDocument();
/*     */ 
/*  86 */     Element formElement = document.addElement("form");
/*     */ 
/*  88 */     formElement.addAttribute("id", "form_" + formName);
/*  89 */     formElement.addAttribute("caption", formCaption);
/*     */ 
/*  91 */     formElement.addAttribute("submit", "提  交 ");
/*  92 */     formElement.addAttribute("reset", "重 置");
/*     */ 
/*  94 */     addWidgetAttribute(formElement, "component", "form", "form_" + formName);
/*     */ 
/*  96 */     Element fieldLayoutElement = formElement.addElement("fieldLayout");
/*  97 */     addWidgetAttribute(fieldLayoutElement, "component", "fieldLayout", "fieldLayout_" + formName);
/*  98 */     String prefix = "record";
/*  99 */     fieldLayoutElement.addAttribute("prefix", prefix);
/* 100 */     for (Param param : params) {
/* 101 */       addFieldElement(fieldLayoutElement, param, prefix);
/*     */     }
/*     */ 
/* 104 */     return formElement.createCopy();
/*     */   }
/*     */ 
/*     */   private static void addWidgetAttribute(Element element, String widgetType, String widgetName, String widgetId)
/*     */   {
/* 119 */     element.addAttribute("widgetType", widgetType);
/* 120 */     element.addAttribute("widgetName", widgetName);
/* 121 */     element.addAttribute("widgetId", widgetId);
/*     */   }
/*     */ 
/*     */   private static void addFieldElement(Element fieldLayoutElement, Param param, String prefix)
/*     */   {
/* 131 */     String property = param.getName();
/* 132 */     Element fieldElement = fieldLayoutElement.addElement("fieldText");
/* 133 */     fieldElement.addAttribute("property", property);
/* 134 */     fieldElement.addAttribute("caption", param.getCaption());
/* 135 */     fieldElement.addAttribute("fieldType", "fieldText");
/* 136 */     addWidgetAttribute(fieldElement, "field", "fieldText", prefix + "_" + property);
/*     */   }
/*     */ 
/*     */   private static void addGridColElement(Element gridElement, Param param)
/*     */   {
/* 146 */     String property = param.getName();
/* 147 */     Element gridColElement = gridElement.addElement("gridCol");
/* 148 */     addWidgetAttribute(gridColElement, "component", "gridCol", "grid_" + property);
/* 149 */     gridColElement.addAttribute("property", property);
/* 150 */     gridColElement.addAttribute("caption", param.getCaption());
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.util.FormPageModelUtils
 * JD-Core Version:    0.5.4
 */