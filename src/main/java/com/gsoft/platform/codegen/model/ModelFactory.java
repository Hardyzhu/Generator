package com.gsoft.platform.codegen.model;

import com.gsoft.framework.util.Dom4jUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class ModelFactory
{
  private static final Log logger = LogFactory.getLog(ModelFactory.class);
  private static final String XPATH_MODULES = "//models/module";
  private static final ModelFactory instance = new ModelFactory();
  private Map<String, Module> modules;
  private List<String> modelFiles;

  private ModelFactory()
  {
    this.modules = new LinkedHashMap();
    this.modelFiles = new ArrayList();
  }

  public static ModelFactory getInstance()
  {
    return instance;
  }

  public void registerModelFile(String modelFile)
  {
    if (!this.modelFiles.contains(modelFile)) {
      logger.info("注册模型文件开始：" + modelFile);
      parseModelFile(modelFile);
      this.modelFiles.add(modelFile);
      logger.info("注册模型文件结束：" + modelFile);
    }
  }

  public void registerModuleModels(String moduleName, byte[] bytes)
  {
    if (bytes == null) return;
    String xmlText = "";
    try {
      xmlText = new String(bytes, "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {
    }
    Document doc = Dom4jUtils.parseText(xmlText);

    if (doc != null) {
      List<Element> moduleElements = doc.selectNodes("//models/module");
      ModelFile modelFile = new ModelFile("model-" + moduleName + ".xml", doc);
      for (Element element : moduleElements)
        parseModule(element, modelFile);
    }
  }

  public void registerModuleModel(Module module, Model model)
  {
    String moduleName = module.getName();
    List allModules = getModules();

    if (!allModules.contains(module)) {
      this.modules.put(moduleName, module);
    }
    Model exsitModel = getModel(moduleName, model.getName());
    if (exsitModel != null)
    {
      getModuleModels(moduleName).remove(exsitModel);
    }
    getModuleModels(moduleName).add(model);
  }

  public void registerModels(Document modelDoc)
  {
    if (modelDoc != null) {
      List<Element> moduleElements = modelDoc.selectNodes("//models/module");

      for (Element element : moduleElements) {
        String moduleName = element.attributeValue("name");
        ModelFile modelFile = new ModelFile("model-" + moduleName + ".xml", modelDoc);
        parseModule(element, modelFile);

        moduleName = null;
        modelFile = null;
      }
    }
  }

  private Document getModelDoc()
  {
    Document doc = DocumentFactory.getInstance().createDocument();
    doc.addElement("models");
    doc.setXMLEncoding("UTF-8");
    return doc;
  }

  public Document getModuleDoc(String moduleName)
  {
    Document moduleDoc = getModelDoc();
    Element moduleElement = moduleDoc.getRootElement().addElement("module");
    moduleElement.addAttribute("name", moduleName);
    List<Model> models = getModuleModels(moduleName);
    if (models == null) return null;

    for (Model model : models) {
      Element modelElement = model.getModelFile().getModelElement(model);
      if (modelElement != null) {
        moduleElement.add(modelElement.createCopy());
      }
      modelElement = null;
    }
    return moduleDoc;
  }

  public void registerModuleModels(String[] moduleNames, Document doc)
  {
    if (doc == null) return;
    for (String moduleName : moduleNames) {
      this.modules.remove(moduleName);
      ModelFile modelFile = new ModelFile("model-" + moduleName + ".xml", doc);
      Element moduleElement = (Element)doc.selectSingleNode("//models/module[@name='" + moduleName + "']");
      parseModule(moduleElement, modelFile);
    }
  }

  public List<Module> getModules()
  {
    List moduleList = new ArrayList();
    moduleList.addAll(this.modules.values());
    return moduleList;
  }

  public List<Model> getModuleModels(String moduleName)
  {
    Module module = (Module)this.modules.get(moduleName);
    if (module != null) {
      return module.getModels();
    }
    logger.warn("模块【" + moduleName + "】未找到！");

    return null;
  }

  public List<Model> getModuleModels(String moduleName, String basePackage)
  {
    Module module = (Module)this.modules.get(moduleName);
    if (module != null) {
      return module.getModels();
    }
    logger.warn("模块【" + moduleName + "】未找到！");

    return null;
  }

  public Model getModel(String moduleName, String modelName)
  {
    Module module = (Module)this.modules.get(moduleName);
    if (module != null) {
      return module.getModel(modelName);
    }
    return null;
  }

  public void removeModel(String moduleName, String modelName)
  {
    Module module = (Module)this.modules.get(moduleName);
    if (module != null)
      module.removeModel(modelName);
  }

  public Element getOrmConfig()
  {
    List<Module> moduleList = getModules();
    Element modelListElement = DocumentFactory.getInstance().createElement("list");

    for (Module module : moduleList) {
      for (Model model : module.getModels()) {
        modelListElement.add(createValueElement(model));
      }
    }
    logger.info(modelListElement.asXML());
    return modelListElement;
  }

  private Element createValueElement(Model model)
  {
    Element element = DocumentFactory.getInstance().createElement("value");

    return element;
  }

  private void parseModelFile(String fileName)
  {
    String filePath = fileName;
    Document doc = Dom4jUtils.saxParse(filePath);
    if (doc != null) {
      List<Element> moduleElements = doc.selectNodes("//models/module");
      ModelFile modelFile = new ModelFile(filePath, doc);
      for (Element element : moduleElements)
        parseModule(element, modelFile);
    }
  }

  private void parseModule(Element element, ModelFile modelFile)
  {
    if (element == null) return;

    String moduleName = element.attributeValue("name");
    String moduleCaption = element.attributeValue("caption");
    Module module = (Module)this.modules.get(moduleName);
    if (module == null) {
      module = new Module();
      module.setName(moduleName);
      module.setCaption(moduleCaption);
      this.modules.put(moduleName, module);
    }

    List<Element> modelElements = element.selectNodes("model");
    
    List<Model> modelList = new ArrayList<>();

    for (Element modelElement : modelElements) {
      Model exisiModel = getModel(moduleName, modelElement.attributeValue("name"));
      if (exisiModel != null){
        module.removeModel(exisiModel);
      }
      Model model = parseModel(modelElement, moduleName, modelFile);
      if(model != null){
    	  modelList.add(model); 
      }
//      module.addModel(parseModel(modelElement, moduleName, modelFile));
    }
    module.setModels(modelList);
  }

  private Model parseModel(Element modelElement, String moduleName, ModelFile modelFile)
  {
    String id = modelElement.attributeValue("id");
    String name = modelElement.attributeValue("name");
    String caption = modelElement.attributeValue("caption");
    String table = modelElement.attributeValue("table");
    String idType = modelElement.attributeValue("idType");
    String toString = modelElement.attributeValue("toString");
    String modelXPath = modelElement.getPath();

    Model model = new Model(id, name, caption);
    model.setTable(table);
    model.setIdType(idType);
    model.setToString(toString);
    model.setModuleName(moduleName);
    model.setClassName(modelClassName(moduleName, name));

    model.setAttributes(parseAttributes(modelElement));

    model.setForeignAttributes(parseForeignAttributes(modelElement, moduleName));

    model.setSetAttributes(parseSetAttributes(modelElement));

    model.setModelFile(modelFile);
    model.setModelXPath(modelXPath);

    model.setSupportWorkflow("true".equals(modelElement.attributeValue("supportWorkflow")));
    model.setWorkflowCaption(modelElement.attributeValue("workflowCaption"));
    model.setWorkflowName(modelElement.attributeValue("workflowName"));

    logger.info("注册模型：" + name + "-" + caption + " : " + model.getModuleName() + " " + model.getName());
    return model;
  }

  private List<NormalAttribute> parseAttributes(Element modelElement)
  {
    List<Element> attributeElementList = modelElement.selectNodes("property");
    List attributes = new LinkedList();

    for (Element attributeElement : attributeElementList) {
      attributes.add(parseAttributeElement(attributeElement));
    }
    return attributes;
  }

  private NormalAttribute parseAttributeElement(Element attributeElement)
  {
    String name = attributeElement.attributeValue("name");
    String description = attributeElement.attributeValue("caption");
    String isId = attributeElement.attributeValue("isId");
    String notNull = attributeElement.attributeValue("notNull");
    String type = attributeElement.attributeValue("type");
    String column = attributeElement.attributeValue("column");
    String length = attributeElement.attributeValue("length");
    String min = attributeElement.attributeValue("min");
    String unique = attributeElement.attributeValue("unique");
    String filterOperator = attributeElement.attributeValue("filterOperator");
    NormalAttribute attribute = new NormalAttribute(type, name, column, isId, 
      length, min, description, notNull);
    attribute.setUnique(unique);
    if (filterOperator != null) attribute.setFilterOperator(filterOperator);
    return attribute;
  }

  private List<SetAttribute> parseSetAttributes(Element modelElement)
  {
    List<Element> setAttributeElementList = modelElement.selectNodes("set");
    List setAttributes = new LinkedList();

    for (Element setAttributeElement : setAttributeElementList) {
      setAttributes.add(parseSetAttributeElement(setAttributeElement));
    }
    return setAttributes;
  }

  private SetAttribute parseSetAttributeElement(Element setAttributeElement)
  {
    SetAttribute setAttribute = null;
    if (setAttributeElement.selectNodes("many-to-many").size() == 1)
      setAttribute = new ManyToManySetAttribute();
    else if (setAttributeElement.selectNodes("one-to-many").size() == 1) {
      setAttribute = new OneToManySetAttribute();
    }
    setAttribute.parseFromSetElement(setAttributeElement);
    return setAttribute;
  }

  private List<ForeignAttribute> parseForeignAttributes(Element modelElement, String moduleName)
  {
    List<Element> foreignAttributeElementList = modelElement.selectNodes("foreign");
    List foreignAttributes = new LinkedList();

    for (Element attributeElement : foreignAttributeElementList) {
      foreignAttributes.add(parseForeignAttributeElement(attributeElement, moduleName));
    }
    return foreignAttributes;
  }

  private ForeignAttribute parseForeignAttributeElement(Element foreignAttributeElement, String module)
  {
    String cascade = null;

    String name = foreignAttributeElement.attributeValue("name");
    String column = foreignAttributeElement.attributeValue("column");
    cascade = foreignAttributeElement.attributeValue("cascade");
    String description = foreignAttributeElement.attributeValue("caption");
    String refModel = foreignAttributeElement.attributeValue("refModel");
    String refModelName;
    String refModuleName;
    if (refModel.indexOf(".") == -1) {
      refModuleName = module;
      refModelName = refModel;
    } else {
      refModuleName = refModel.split("\\.")[0];
      refModelName = refModel.split("\\.")[1];
    }

    ForeignAttribute foreignAttribute = new ForeignAttribute(name, column, refModuleName, refModelName);
    foreignAttribute.setCascade(cascade);
    foreignAttribute.setCaption(description);
    return foreignAttribute;
  }

  private String modelClassName(String moduleName, String name)
  {
    StringBuffer buf = new StringBuffer();

    buf.append("." + moduleName + ".entity.");

    buf.append(name.substring(0, 1).toUpperCase());
    buf.append(name.substring(1));
    return buf.toString();
  }

  public void clear()
  {
    this.modelFiles.clear();
    this.modules.clear();
  }
}