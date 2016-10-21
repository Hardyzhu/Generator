/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import com.gsoft.framework.util.Dom4jUtils;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class ModelFactory
/*     */ {
/*  42 */   private static final Log logger = LogFactory.getLog(ModelFactory.class);
/*     */   private static final String XPATH_MODULES = "//models/module";
/*  46 */   private static final ModelFactory instance = new ModelFactory();
/*     */   private Map<String, Module> modules;
/*     */   private List<String> modelFiles;
/*     */ 
/*     */   private ModelFactory()
/*     */   {
/*  56 */     this.modules = new LinkedHashMap();
/*  57 */     this.modelFiles = new ArrayList();
/*     */   }
/*     */ 
/*     */   public static ModelFactory getInstance()
/*     */   {
/*  65 */     return instance;
/*     */   }
/*     */ 
/*     */   public void registerModelFile(String modelFile)
/*     */   {
/*  72 */     if (!this.modelFiles.contains(modelFile)) {
/*  73 */       logger.info("注册模型文件开始：" + modelFile);
/*  74 */       parseModelFile(modelFile);
/*  75 */       this.modelFiles.add(modelFile);
/*  76 */       logger.info("注册模型文件结束：" + modelFile);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void registerModuleModels(String moduleName, byte[] bytes)
/*     */   {
/*  84 */     if (bytes == null) return;
/*  85 */     String xmlText = "";
/*     */     try {
/*  87 */       xmlText = new String(bytes, "UTF-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     }
/*  91 */     Document doc = Dom4jUtils.parseText(xmlText);
/*     */ 
/*  93 */     if (doc != null) {
/*  94 */       List<Element> moduleElements = doc.selectNodes("//models/module");
/*  95 */       ModelFile modelFile = new ModelFile("model-" + moduleName + ".xml", doc);
/*  96 */       for (Element element : moduleElements)
/*  97 */         parseModule(element, modelFile);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void registerModuleModel(Module module, Model model)
/*     */   {
/* 103 */     String moduleName = module.getName();
/* 104 */     List allModules = getModules();
/*     */ 
/* 106 */     if (!allModules.contains(module)) {
/* 107 */       this.modules.put(moduleName, module);
/*     */     }
/* 109 */     Model exsitModel = getModel(moduleName, model.getName());
/* 110 */     if (exsitModel != null)
/*     */     {
/* 112 */       getModuleModels(moduleName).remove(exsitModel);
/*     */     }
/* 114 */     getModuleModels(moduleName).add(model);
/*     */   }
/*     */ 
/*     */   public void registerModels(Document modelDoc)
/*     */   {
/* 123 */     if (modelDoc != null) {
/* 124 */       List<Element> moduleElements = modelDoc.selectNodes("//models/module");
/*     */ 
/* 127 */       for (Element element : moduleElements) {
/* 128 */         String moduleName = element.attributeValue("name");
/* 129 */         ModelFile modelFile = new ModelFile("model-" + moduleName + ".xml", modelDoc);
/* 130 */         parseModule(element, modelFile);
/*     */ 
/* 132 */         moduleName = null;
/* 133 */         modelFile = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private Document getModelDoc()
/*     */   {
/* 142 */     Document doc = DocumentFactory.getInstance().createDocument();
/* 143 */     doc.addElement("models");
/* 144 */     doc.setXMLEncoding("UTF-8");
/* 145 */     return doc;
/*     */   }
/*     */ 
/*     */   public Document getModuleDoc(String moduleName)
/*     */   {
/* 154 */     Document moduleDoc = getModelDoc();
/* 155 */     Element moduleElement = moduleDoc.getRootElement().addElement("module");
/* 156 */     moduleElement.addAttribute("name", moduleName);
/* 157 */     List<Model> models = getModuleModels(moduleName);
/* 158 */     if (models == null) return null;
/*     */ 
/* 160 */     for (Model model : models) {
/* 161 */       Element modelElement = model.getModelFile().getModelElement(model);
/* 162 */       if (modelElement != null) {
/* 163 */         moduleElement.add(modelElement.createCopy());
/*     */       }
/* 165 */       modelElement = null;
/*     */     }
/* 167 */     return moduleDoc;
/*     */   }
/*     */ 
/*     */   public void registerModuleModels(String[] moduleNames, Document doc)
/*     */   {
/* 173 */     if (doc == null) return;
/* 174 */     for (String moduleName : moduleNames) {
/* 175 */       this.modules.remove(moduleName);
/* 176 */       ModelFile modelFile = new ModelFile("model-" + moduleName + ".xml", doc);
/* 177 */       Element moduleElement = (Element)doc.selectSingleNode("//models/module[@name='" + moduleName + "']");
/* 178 */       parseModule(moduleElement, modelFile);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Module> getModules()
/*     */   {
/* 186 */     List moduleList = new ArrayList();
/* 187 */     moduleList.addAll(this.modules.values());
/* 188 */     return moduleList;
/*     */   }
/*     */ 
/*     */   public List<Model> getModuleModels(String moduleName)
/*     */   {
/* 196 */     Module module = (Module)this.modules.get(moduleName);
/* 197 */     if (module != null) {
/* 198 */       return module.getModels();
/*     */     }
/* 200 */     logger.warn("模块【" + moduleName + "】未找到！");
/*     */ 
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */   public List<Model> getModuleModels(String moduleName, String basePackage)
/*     */   {
/* 210 */     Module module = (Module)this.modules.get(moduleName);
/* 211 */     if (module != null) {
/* 212 */       return module.getModels();
/*     */     }
/* 214 */     logger.warn("模块【" + moduleName + "】未找到！");
/*     */ 
/* 216 */     return null;
/*     */   }
/*     */ 
/*     */   public Model getModel(String moduleName, String modelName)
/*     */   {
/* 226 */     Module module = (Module)this.modules.get(moduleName);
/* 227 */     if (module != null) {
/* 228 */       return module.getModel(modelName);
/*     */     }
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */   public void removeModel(String moduleName, String modelName)
/*     */   {
/* 240 */     Module module = (Module)this.modules.get(moduleName);
/* 241 */     if (module != null)
/* 242 */       module.removeModel(modelName);
/*     */   }
/*     */ 
/*     */   public Element getOrmConfig()
/*     */   {
/* 250 */     List<Module> moduleList = getModules();
/* 251 */     Element modelListElement = DocumentFactory.getInstance().createElement("list");
/*     */ 
/* 253 */     for (Module module : moduleList) {
/* 254 */       for (Model model : module.getModels()) {
/* 255 */         modelListElement.add(createValueElement(model));
/*     */       }
/*     */     }
/* 258 */     logger.info(modelListElement.asXML());
/* 259 */     return modelListElement;
/*     */   }
/*     */ 
/*     */   private Element createValueElement(Model model)
/*     */   {
/* 268 */     Element element = DocumentFactory.getInstance().createElement("value");
/*     */ 
/* 270 */     return element;
/*     */   }
/*     */ 
/*     */   private void parseModelFile(String fileName)
/*     */   {
/* 278 */     String filePath = fileName;
/* 279 */     Document doc = Dom4jUtils.saxParse(filePath);
/* 280 */     if (doc != null) {
/* 281 */       List<Element> moduleElements = doc.selectNodes("//models/module");
/* 282 */       ModelFile modelFile = new ModelFile(filePath, doc);
/* 283 */       for (Element element : moduleElements)
/* 284 */         parseModule(element, modelFile);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseModule(Element element, ModelFile modelFile)
/*     */   {
/* 295 */     if (element == null) return;
/*     */ 
/* 299 */     String moduleName = element.attributeValue("name");
/* 300 */     String moduleCaption = element.attributeValue("caption");
/* 301 */     Module module = (Module)this.modules.get(moduleName);
/* 302 */     if (module == null) {
/* 303 */       module = new Module();
/* 304 */       module.setName(moduleName);
/* 305 */       module.setCaption(moduleCaption);
/* 306 */       this.modules.put(moduleName, module);
/*     */     }
/*     */ 
/* 310 */     List<Element> modelElements = element.selectNodes("model");
/*     */ 
/* 312 */     for (Element modelElement : modelElements) {
/* 313 */       Model exisiModel = getModel(moduleName, modelElement.attributeValue("name"));
/* 314 */       if (exisiModel != null)
/*     */       {
/* 316 */         module.removeModel(exisiModel);
/*     */       }
/* 318 */       module.addModel(parseModel(modelElement, moduleName, modelFile));
/*     */     }
/*     */   }
/*     */ 
/*     */   private Model parseModel(Element modelElement, String moduleName, ModelFile modelFile)
/*     */   {
/* 337 */     String id = modelElement.attributeValue("id");
/* 338 */     String name = modelElement.attributeValue("name");
/* 339 */     String caption = modelElement.attributeValue("caption");
/* 340 */     String table = modelElement.attributeValue("table");
/* 341 */     String idType = modelElement.attributeValue("idType");
/* 342 */     String toString = modelElement.attributeValue("toString");
/* 343 */     String modelXPath = modelElement.getPath();
/*     */ 
/* 345 */     Model model = new Model(id, name, caption);
/* 346 */     model.setTable(table);
/* 347 */     model.setIdType(idType);
/* 348 */     model.setToString(toString);
/* 349 */     model.setModuleName(moduleName);
/* 350 */     model.setClassName(modelClassName(moduleName, name));
/*     */ 
/* 352 */     model.setAttributes(parseAttributes(modelElement));
/*     */ 
/* 354 */     model.setForeignAttributes(parseForeignAttributes(modelElement, moduleName));
/*     */ 
/* 356 */     model.setSetAttributes(parseSetAttributes(modelElement));
/*     */ 
/* 358 */     model.setModelFile(modelFile);
/* 359 */     model.setModelXPath(modelXPath);
/*     */ 
/* 361 */     model.setSupportWorkflow("true".equals(modelElement.attributeValue("supportWorkflow")));
/* 362 */     model.setWorkflowCaption(modelElement.attributeValue("workflowCaption"));
/* 363 */     model.setWorkflowName(modelElement.attributeValue("workflowName"));
/*     */ 
/* 365 */     logger.info("注册模型：" + name + "-" + caption + " : " + model.getModuleName() + " " + model.getName());
/* 366 */     return model;
/*     */   }
/*     */ 
/*     */   private List<NormalAttribute> parseAttributes(Element modelElement)
/*     */   {
/* 379 */     List<Element> attributeElementList = modelElement.selectNodes("property");
/* 380 */     List attributes = new LinkedList();
/*     */ 
/* 382 */     for (Element attributeElement : attributeElementList) {
/* 383 */       attributes.add(parseAttributeElement(attributeElement));
/*     */     }
/* 385 */     return attributes;
/*     */   }
/*     */ 
/*     */   private NormalAttribute parseAttributeElement(Element attributeElement)
/*     */   {
/* 407 */     String name = attributeElement.attributeValue("name");
/* 408 */     String description = attributeElement.attributeValue("caption");
/* 409 */     String isId = attributeElement.attributeValue("isId");
/* 410 */     String notNull = attributeElement.attributeValue("notNull");
/* 411 */     String type = attributeElement.attributeValue("type");
/* 412 */     String column = attributeElement.attributeValue("column");
/* 413 */     String length = attributeElement.attributeValue("length");
/* 414 */     String min = attributeElement.attributeValue("min");
/* 415 */     String unique = attributeElement.attributeValue("unique");
/* 416 */     String filterOperator = attributeElement.attributeValue("filterOperator");
/* 417 */     NormalAttribute attribute = new NormalAttribute(type, name, column, isId, 
/* 418 */       length, min, description, notNull);
/* 419 */     attribute.setUnique(unique);
/* 420 */     if (filterOperator != null) attribute.setFilterOperator(filterOperator);
/* 421 */     return attribute;
/*     */   }
/*     */ 
/*     */   private List<SetAttribute> parseSetAttributes(Element modelElement)
/*     */   {
/* 433 */     List<Element> setAttributeElementList = modelElement.selectNodes("set");
/* 434 */     List setAttributes = new LinkedList();
/*     */ 
/* 436 */     for (Element setAttributeElement : setAttributeElementList) {
/* 437 */       setAttributes.add(parseSetAttributeElement(setAttributeElement));
/*     */     }
/* 439 */     return setAttributes;
/*     */   }
/*     */ 
/*     */   private SetAttribute parseSetAttributeElement(Element setAttributeElement)
/*     */   {
/* 447 */     SetAttribute setAttribute = null;
/* 448 */     if (setAttributeElement.selectNodes("many-to-many").size() == 1)
/* 449 */       setAttribute = new ManyToManySetAttribute();
/* 450 */     else if (setAttributeElement.selectNodes("one-to-many").size() == 1) {
/* 451 */       setAttribute = new OneToManySetAttribute();
/*     */     }
/* 453 */     setAttribute.parseFromSetElement(setAttributeElement);
/* 454 */     return setAttribute;
/*     */   }
/*     */ 
/*     */   private List<ForeignAttribute> parseForeignAttributes(Element modelElement, String moduleName)
/*     */   {
/* 468 */     List<Element> foreignAttributeElementList = modelElement.selectNodes("foreign");
/* 469 */     List foreignAttributes = new LinkedList();
/*     */ 
/* 471 */     for (Element attributeElement : foreignAttributeElementList) {
/* 472 */       foreignAttributes.add(parseForeignAttributeElement(attributeElement, moduleName));
/*     */     }
/* 474 */     return foreignAttributes;
/*     */   }
/*     */ 
/*     */   private ForeignAttribute parseForeignAttributeElement(Element foreignAttributeElement, String module)
/*     */   {
/* 491 */     String cascade = null;
/*     */ 
/* 494 */     String name = foreignAttributeElement.attributeValue("name");
/* 495 */     String column = foreignAttributeElement.attributeValue("column");
/* 496 */     cascade = foreignAttributeElement.attributeValue("cascade");
/* 497 */     String description = foreignAttributeElement.attributeValue("caption");
/* 498 */     String refModel = foreignAttributeElement.attributeValue("refModel");
/*     */     String refModelName;
/*     */     String refModuleName;
/* 499 */     if (refModel.indexOf(".") == -1) {
/* 500 */       refModuleName = module;
/* 501 */       refModelName = refModel;
/*     */     } else {
/* 503 */       refModuleName = refModel.split("\\.")[0];
/* 504 */       refModelName = refModel.split("\\.")[1];
/*     */     }
/*     */ 
/* 507 */     ForeignAttribute foreignAttribute = new ForeignAttribute(name, column, refModuleName, refModelName);
/* 508 */     foreignAttribute.setCascade(cascade);
/* 509 */     foreignAttribute.setCaption(description);
/* 510 */     return foreignAttribute;
/*     */   }
/*     */ 
/*     */   private String modelClassName(String moduleName, String name)
/*     */   {
/* 520 */     StringBuffer buf = new StringBuffer();
/*     */ 
/* 523 */     buf.append("." + moduleName + ".entity.");
/*     */ 
/* 525 */     buf.append(name.substring(0, 1).toUpperCase());
/* 526 */     buf.append(name.substring(1));
/* 527 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 532 */     this.modelFiles.clear();
/* 533 */     this.modules.clear();
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.ModelFactory
 * JD-Core Version:    0.5.4
 */