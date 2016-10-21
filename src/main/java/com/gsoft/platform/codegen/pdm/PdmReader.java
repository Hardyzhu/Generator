/*     */ package com.gsoft.platform.codegen.pdm;
/*     */ 
/*     */ import com.gsoft.framework.util.Dom4jUtils;
/*     */ import com.gsoft.framework.util.StringUtils;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.XPath;
/*     */ 
/*     */ public class PdmReader
/*     */ {
/*  32 */   private static final Log logger = LogFactory.getLog(PdmReader.class);
/*     */   private Document doc;
/*     */   private Document modelsDoc;
/*  38 */   List<String> modules = new ArrayList();
/*     */ 
/*  45 */   private static final Map<String, String> namespaceURIs = new HashMap();
/*     */ 
/*     */   static {
/*  48 */     namespaceURIs.put("o", "o");
/*  49 */     namespaceURIs.put("c", "c");
/*  50 */     namespaceURIs.put("a", "a");
/*     */   }
/*     */ 
/*     */   public PdmReader(String fileName) {
/*  54 */     this(fileName, null);
/*  55 */     logger.debug("载入PDM文件:" + fileName);
/*     */   }
/*     */ 
/*     */   public PdmReader(String fileName, String prefix) {
/*  59 */     readDocument(fileName);
/*  60 */     parse(null, (prefix == null) ? null : prefix.toLowerCase());
/*     */   }
/*     */ 
/*     */   public PdmReader(byte[] bytes, String packageName, String prefix) {
/*  64 */     readDocumentFromBytes(bytes);
/*  65 */     parse(packageName, prefix);
/*     */   }
/*     */ 
/*     */   private void readDocumentFromBytes(byte[] bytes) {
/*  69 */     String xmlText = "";
/*     */     try {
/*  71 */       xmlText = new String(bytes, "UTF-8");
/*     */     } catch (UnsupportedEncodingException e) {
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     this.doc = Dom4jUtils.parseText(xmlText);
/*     */   }
/*     */ 
/*     */   private void readDocument(String fileName)
/*     */   {
/*  83 */     this.doc = Dom4jUtils.saxParse(fileName);
/*     */   }
/*     */ 
/*     */   private void parse(String packageName, String prefix) {
/*  87 */     parsePackages(packageName, prefix);
/*     */   }
/*     */ 
/*     */   private void parsePackages(String packageName, String prefix)
/*     */   {
/*  93 */     List<Element> packages = getPackages();
/*     */ 
/*  97 */     this.modelsDoc = DocumentFactory.getInstance().createDocument();
/*  98 */     Element modelsRoot = this.modelsDoc.addElement("models");
/*     */ 
/* 100 */     label272: for (Element element : packages)
/*     */     {
/* 102 */       if (!"Packages".equals(element.getParent().getName())) {
/*     */         continue;
/*     */       }
/*     */       try
/*     */       {
/* 107 */         String id = element.attributeValue("Id");
/* 108 */         if (id != null) {
/* 109 */           String code = element.selectSingleNode("a:Code").getText();
/* 110 */           String name = element.selectSingleNode("a:Name").getText();
/* 111 */           if (StringUtils.isEmpty(prefix)) {
/* 112 */             prefix = code.toLowerCase();
/*     */           }
/* 114 */           this.modules.add(code);
/*     */ 
/* 116 */           if ((packageName != null) && (!code.equals(packageName))) {
/*     */             break label272;
/*     */           }
/* 119 */           Element moduleElement = modelsRoot.addElement("module");
/* 120 */           moduleElement.addAttribute("name", code);
/* 121 */           moduleElement.addAttribute("caption", name);
/*     */ 
/* 123 */           List<Element> modelList = parseTables(element.selectNodes("c:Tables/o:Table"), prefix);
/* 124 */           for (Element modelElement : modelList) {
/* 125 */             moduleElement.add(modelElement);
/*     */           }
/*     */         }
/* 128 */         id = null;
/*     */       }
/*     */       catch (RuntimeException e) {
/* 131 */         e.printStackTrace();
/*     */       }
/* 133 */       prefix = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private List<Element> parseTables(List<Element> tables, String prefix)
/*     */   {
/* 139 */     List modelList = new ArrayList();
/*     */ 
/* 145 */     for (Element element : tables) {
/* 146 */       String id = element.attributeValue("Id");
/* 147 */       if (id != null)
/*     */       {
/* 156 */         Node keysNode = element.selectSingleNode("c:Keys");
/*     */ 
/* 158 */         Node indexsNode = element.selectSingleNode("c:Indexes");
/*     */ 
/* 161 */         if (keysNode != null) {
/* 162 */           id = keysNode.selectSingleNode("o:Key/a:Code").getText();
/*     */ 
/* 164 */           Set keyColumnIds = getKeyColumnIds(keysNode);
/* 165 */           String tableName = element.selectSingleNode("a:Code").getText();
/* 166 */           String modelName = parseName(tableName, prefix);
/* 167 */           if (modelName == null) continue;
/* 168 */           Element modelElement = DocumentFactory.getInstance().createElement("model");
/* 169 */           modelElement.addAttribute("id", id);
/* 170 */           modelElement.addAttribute("name", modelName);
/* 171 */           modelElement.addAttribute("table", tableName);
/* 172 */           modelElement.addAttribute("caption", element.selectSingleNode("a:Name").getText().replace(id, ""));
/* 173 */           modelElement.addAttribute("idType", "String");
/* 174 */           modelElement.addAttribute("toString", "super.toString()");
/*     */ 
/* 178 */           Set foreignsColumns = null;
/* 179 */           if (indexsNode != null) {
/* 180 */             foreignsColumns = parseForeigns(modelElement, indexsNode.selectNodes("o:Index"), prefix);
/*     */           }
/*     */ 
/* 184 */           parseAttributes(modelElement, getColumns(element), foreignsColumns, keyColumnIds, prefix);
/* 185 */           modelList.add(modelElement);
/* 186 */           modelElement = null;
/*     */         }
/* 188 */         keysNode = null;
/*     */       }
/* 190 */       Element modelElement = null;
/* 191 */       id = null;
/*     */     }
/*     */ 
/* 194 */     DocumentHelper.sort(modelList, "@id");
/* 195 */     return modelList;
/*     */   }
/*     */ 
/*     */   private Set<String> parseForeigns(Element modelElement, List<Element> indexes, String prefix)
/*     */   {
/* 202 */     Set foreignsColumns = new HashSet();
/*     */ 
/* 206 */     Element referenceTable = null;
/*     */ 
/* 209 */     label334: for (Element element : indexes) {
/* 210 */       if (element.selectSingleNode("a:Unique") == null) { Element reference = (Element)element.selectSingleNode("c:LinkedObject/o:Reference");
/*     */         String columnId;
/*     */         try {
/* 213 */           columnId = ((Element)element.selectSingleNode("c:IndexColumns/o:IndexColumn/c:Column/o:Column")).attributeValue("Ref");
/*     */         } catch (RuntimeException e1) {
/* 215 */           break label334;
/*     */         }
/* 217 */         Element referenceColumn = getColumn(columnId);
/*     */ 
/* 219 */         if ((reference != null) && (referenceColumn != null)) {
/* 220 */           String ref = reference.attributeValue("Ref");
/* 221 */           Element oReference = getReference(ref);
/*     */           try {
/* 223 */             referenceTable = getTable(((Element)oReference.selectSingleNode("c:ParentTable/o:Table")).attributeValue("Ref"));
/*     */           } catch (RuntimeException e) {
/* 225 */             referenceTable = null;
/*     */           }
/*     */ 
/* 229 */           if ((columnId != null) && (referenceTable != null)) {
/* 230 */             String columnName = referenceColumn.selectSingleNode("a:Code").getText();
/* 231 */             String referenceTableName = referenceTable.selectSingleNode("a:Code").getText();
/* 232 */             foreignsColumns.add(columnId);
/* 233 */             if (isEntity(referenceTable))
/*     */             {
/* 235 */               String name = parseName(referenceTableName, prefix);
/* 236 */               Element foreignElement = modelElement.addElement("foreign");
/* 237 */               foreignElement.addAttribute("name", name);
/* 238 */               foreignElement.addAttribute("column", columnName);
/* 239 */               foreignElement.addAttribute("caption", referenceColumn.selectSingleNode("a:Name").getText());
/* 240 */               foreignElement.addAttribute("refModel", name);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 247 */         String ref = null;
/* 248 */         reference = null;
/* 249 */         Element oReference = null;
/* 250 */         referenceColumn = null;
/* 251 */         referenceTable = null;
/* 252 */         Element foreignElement = null;
/* 253 */         String name = null;
/* 254 */         String columnName = null; }
/*     */ 
/*     */     }
/* 257 */     return foreignsColumns;
/*     */   }
/*     */ 
/*     */   private Set<String> getKeyColumnIds(Node keysNode)
/*     */   {
/* 267 */     List<Element> keyColumns = keysNode.selectNodes("o:Key/c:Key.Columns/o:Column");
/* 268 */     Set keys = new HashSet();
/* 269 */     for (Element element : keyColumns) {
/* 270 */       keys.add(element.attributeValue("Ref"));
/*     */     }
/* 272 */     return keys;
/*     */   }
/*     */ 
/*     */   private void parseAttributes(Element modelElement, List<Element> attributes, Set<String> foreignsColumns, Set<String> keyColumnIds, String prefix)
/*     */   {
/* 279 */     for (Element element : attributes)
/*     */       try {
/* 281 */         String id = element.attributeValue("Id");
/* 282 */         if ((id != null) && (((foreignsColumns == null) || (!foreignsColumns.contains(id)))))
/*     */         {
/* 285 */           Node uniqueNode = element.selectSingleNode("a:Unique");
/* 286 */           Element attributeElement = modelElement.addElement("property");
/*     */ 
/* 288 */           String columnName = element.selectSingleNode("a:Code").getText();
/* 289 */           attributeElement.addAttribute("name", parseName(columnName, prefix));
/* 290 */           attributeElement.addAttribute("caption", element.selectSingleNode("a:Name").getText());
/* 291 */           Node lengthElement = element.selectSingleNode("a:Length");
/* 292 */           if (lengthElement != null) {
/* 293 */             attributeElement.addAttribute("length", lengthElement.getText());
/*     */           }
/* 295 */           if (keyColumnIds.contains(id)) {
/* 296 */             attributeElement.addAttribute("isId", "true");
/*     */           }
/* 298 */           if (uniqueNode != null) attributeElement.addAttribute("notNull", "true");
/* 299 */           attributeElement.addAttribute("type", parseType(element.selectSingleNode("a:DataType").getText()));
/* 300 */           attributeElement.addAttribute("pType", element.selectSingleNode("a:DataType").getText());
/* 301 */           attributeElement.addAttribute("column", columnName);
/*     */ 
/* 303 */           uniqueNode = null;
/* 304 */           columnName = null;
/*     */         }
/* 306 */         Element attributeElement = null;
/* 307 */         id = null;
/*     */       }
/*     */       catch (RuntimeException e) {
/* 310 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private String parseName(String tableName, String prefix)
/*     */   {
/* 316 */     String newName = tableName.toLowerCase();
/* 317 */     if (newName.startsWith(prefix)) {
/* 318 */       newName = newName.substring(prefix.length());
/*     */     }
/*     */ 
/* 322 */     String[] names = newName.toLowerCase().split("_");
/* 323 */     StringBuffer nameBuf = new StringBuffer();
/* 324 */     int i = 0;
/* 325 */     for (String name : names) {
/* 326 */       if (name.length() > 1) {
/* 327 */         if (i > 0)
/* 328 */           nameBuf.append(name.substring(0, 1).toUpperCase() + name.substring(1));
/*     */         else {
/* 330 */           nameBuf.append(name);
/*     */         }
/* 332 */         ++i;
/*     */       }
/*     */     }
/* 335 */     return nameBuf.toString();
/*     */   }
/*     */ 
/*     */   private String parseType(String dataType)
/*     */   {
/* 340 */     if (dataType.indexOf("(") != -1) {
/* 341 */       dataType = dataType.substring(0, dataType.indexOf("("));
/*     */     }
/*     */ 
/* 344 */     if ("NUMBER".equalsIgnoreCase(dataType)) {
/* 345 */       return "Long";
/*     */     }
/* 347 */     if (("DOUBLE".equalsIgnoreCase(dataType)) || 
/* 348 */       ("FLOAT".equalsIgnoreCase(dataType)))
/* 349 */       return "Double";
/* 350 */     if ("DATE".equalsIgnoreCase(dataType))
/* 351 */       return "java.util.Date";
/* 352 */     if ("TIMESTAMP".equalsIgnoreCase(dataType))
/* 353 */       return "java.sql.Timestamp";
/* 354 */     if (("BLOB".equalsIgnoreCase(dataType)) || 
/* 355 */       ("CLOB".equalsIgnoreCase(dataType))) {
/* 356 */       return "byte[]";
/*     */     }
/* 358 */     return "String";
/*     */   }
/*     */ 
/*     */   private List<Element> getPackages()
/*     */   {
/* 363 */     return getElements(this.doc.getRootElement(), "//o:Package", "o", "object");
/*     */   }
/*     */ 
/*     */   private List<Element> getTables(Element element)
/*     */   {
/* 368 */     return getElements(element, "//o:Table", "o", "object");
/*     */   }
/*     */ 
/*     */   private List<Element> getColumns(Element element)
/*     */   {
/* 373 */     Element columnsElement = (Element)element.selectSingleNode("c:Columns");
/* 374 */     return getElements(columnsElement, "o:Column", "o", "object");
/*     */   }
/*     */ 
/*     */   private List<Element> getElements(Element element, String path, String xmlns, String xmlnsValue)
/*     */   {
/* 379 */     List list = null;
/* 380 */     XPath xPath = element.createXPath(path);
/* 381 */     xPath.setNamespaceURIs(Collections.singletonMap(xmlns, xmlnsValue));
/* 382 */     list = xPath.selectNodes(element);
/* 383 */     return list;
/*     */   }
/*     */ 
/*     */   private Element getElementById(String tagName, String id, String xmlns, String xmlnsValue) {
/* 387 */     Element node = null;
/* 388 */     Element root = this.doc.getRootElement();
/* 389 */     XPath xPath = root.createXPath("//" + xmlns + ":" + tagName + "[@Id=\"" + id + "\"]");
/* 390 */     xPath.setNamespaceURIs(Collections.singletonMap(xmlns, xmlnsValue));
/* 391 */     node = (Element)xPath.selectSingleNode(root);
/* 392 */     return node;
/*     */   }
/*     */ 
/*     */   private Element getTable(String id)
/*     */   {
/* 400 */     return getElementById("Table", id, "o", "object");
/*     */   }
/*     */ 
/*     */   private Element getColumn(String id) {
/* 404 */     return getElementById("Column", id, "o", "object");
/*     */   }
/*     */ 
/*     */   private Element getReference(String id) {
/* 408 */     return getElementById("Reference", id, "o", "object");
/*     */   }
/*     */ 
/*     */   private boolean isEntity(Element tableElement) {
/* 412 */     Node keysNode = tableElement.selectSingleNode("c:Keys");
/* 413 */     return keysNode != null;
/*     */   }
/*     */ 
/*     */   public void generatorModels(String fileName) {
/* 417 */     Dom4jUtils.writeFormatDocToFile(fileName, this.modelsDoc, "UTF-8");
/* 418 */     System.out.println("----------------" + fileName + ":PDM转换完成！----------------");
/*     */   }
/*     */ 
/*     */   public void saveModels(String fileName)
/*     */   {
/* 426 */     Dom4jUtils.writeFormatDocToFile(fileName, this.modelsDoc, "UTF-8");
/* 427 */     System.out.println("----------------" + fileName + ":PDM转换完成！----------------");
/*     */   }
/*     */ 
/*     */   public byte[] generatorModels() {
/* 431 */     return this.modelsDoc.asXML().getBytes();
/*     */   }
/*     */ 
/*     */   public List<String> getModules() {
/* 435 */     return this.modules;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.pdm.PdmReader
 * JD-Core Version:    0.5.4
 */