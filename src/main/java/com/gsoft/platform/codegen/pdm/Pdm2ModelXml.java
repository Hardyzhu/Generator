 package com.gsoft.platform.codegen.pdm;
 
 import com.gsoft.framework.util.CollectionUtils;
 import com.gsoft.framework.util.Dom4jUtils;
 import com.gsoft.framework.util.StringUtils;
 import java.io.IOException;
 import java.io.StringWriter;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.dom4j.Document;
 import org.dom4j.DocumentFactory;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.dom4j.Node;
 import org.dom4j.XPath;
 import org.dom4j.io.OutputFormat;
 import org.dom4j.io.XMLWriter;
 import org.springframework.util.Assert;
 
 public class Pdm2ModelXml{
   private Document pdmDoc;
   private Document modelDoc;
   private String prefix = "youi";
   private static final String XPATH_MODULE_PACKAGE = "o:RootObject/c:Children/o:Model/c:Packages/o:Package/c:Packages/o:Package";
   private static final String XPATH_MODULE_ENTITY = "c:Tables/o:Table";
   private static final String PDM_ATTR_ID = "Id";
   private static final String PDM_ATTR_REF = "Ref";
   private static final String XPATH_PDM_NAME = "a:Name";
   private static final String XPATH_PDM_CODE = "a:Code";
   private static final String XPATH_ENTITY_PRIMARYKEY = "c:PrimaryKey/o:Key";
   private static final Map<String, String> namespaceURIs = new HashMap();
 
   static {
     namespaceURIs.put("o", "object");
     namespaceURIs.put("c", "collection");
     namespaceURIs.put("a", "attribute");
   }
 
   public Pdm2ModelXml(Document pdmDoc) {
     this(pdmDoc, null);
   }
 
   public Pdm2ModelXml(Document pdmDoc, String prefix) {
     if (StringUtils.isNotEmpty(prefix)) {
       this.prefix = prefix;
     }
     this.pdmDoc = pdmDoc;
     this.modelDoc = DocumentFactory.getInstance().createDocument();
 
     precess();
   }
 
   public Document getModelDoc(){
     return this.modelDoc;
   }
 
   private void precess(){
     processModulePackage();
   }
 
   private void processModulePackage(){
     Element modelRoot = this.modelDoc.addElement("models");
 
     List<Element> pdmPackageElements = 
       getPdmElements(this.pdmDoc.getRootElement(), "o:RootObject/c:Children/o:Model/c:Packages/o:Package/c:Packages/o:Package");
 
     for (Element pdmPackageElement : pdmPackageElements)
       addModuleElement(modelRoot, pdmPackageElement);
   }
 
   private void addModuleElement(Element modelRoot, Element pdmPackageElement){
     if (pdmPackageElement == null) return;
 
     String code = pdmPackageElement.selectSingleNode("a:Code").getText();
     String name = pdmPackageElement.selectSingleNode("a:Name").getText();
 
     Element moduleElement = modelRoot.addElement("module");
 
     Element namespaceElement = pdmPackageElement.getParent().getParent();
 
     moduleElement.addAttribute("namespace", namespaceElement.selectSingleNode("a:Code").getText());
     moduleElement.addAttribute("name", code);
     moduleElement.addAttribute("caption", name);
 
     processModuleEntity(pdmPackageElement, moduleElement);
   }
 
   private void processModuleEntity(Element pdmPackageElement, Element moduleElement){
     List<Element> pdmEntityElements = getPdmElements(pdmPackageElement, "c:Tables/o:Table");
     List<Element> modelElements = new ArrayList();
 
     Element modelElement = null;
 
     for (Element pdmEntityElement : pdmEntityElements) {
       modelElement = buildModelElement(pdmEntityElement);
       if (modelElement != null) {
         modelElements.add(modelElement);
         modelElement = null;
       }
     }
 
     DocumentHelper.sort(modelElements, "@id");
     for (Element element : modelElements)
       moduleElement.add(element);
   }
 
   private Element buildModelElement(Element pdmEntityElement){
     Element modelElement = DocumentFactory.getInstance().createElement("model");
 
     String id = pdmEntityElement.attributeValue("Id");
 
     List primarykeyElement = 
       getPdmElements(pdmEntityElement, "c:PrimaryKey/o:Key");
 
     if (CollectionUtils.isEmpty(primarykeyElement))
     {
       return null;
     }
     String ref = ((Element)primarykeyElement.get(0)).attributeValue("Ref");
     Element keyElement = getElementById(ref, "o:Key");
     Assert.notNull(keyElement, "pdm文件中缺少对于的key元素");
 
     id = keyElement.selectSingleNode("a:Code").getText();
 
     List<Element> keyColumns = keyElement.selectNodes("c:Key.Columns/o:Column");
     List keyRefs = new ArrayList();
     for (Element keyColumn : keyColumns) {
       keyRefs.add(keyColumn.attributeValue("Ref"));
     }
 
     String tableName = pdmEntityElement.selectSingleNode("a:Code").getText();
     String modelName = parseName(tableName);
     String caption = pdmEntityElement.selectSingleNode("a:Name").getText();
 
     modelElement.addAttribute("id", id);
     modelElement.addAttribute("name", modelName);
     modelElement.addAttribute("table", tableName);
     modelElement.addAttribute("caption", caption.replaceFirst(id, ""));
     modelElement.addAttribute("idType", "String");
     modelElement.addAttribute("toString", "super.toString()");
 
     List forginRefs = addModelForginAttrElements(modelElement, pdmEntityElement);
 
     addModelAttrElements(modelElement, pdmEntityElement, keyRefs, forginRefs);
 
     return modelElement;
   }
 
   private List<String> addModelForginAttrElements(Element modelElement, Element pdmEntityElement){
     List forginRefs = new ArrayList();
 
     List<Element> indexRefElements = 
       pdmEntityElement.selectNodes("c:Indexes/o:Index/c:LinkedObject/o:Reference");
 
     for (Element indexRefElement : indexRefElements) {
       String ref = indexRefElement.attributeValue("Ref");
       Element refElement = getElementById(ref, "o:Reference");
       if (refElement == null)
         continue;
       Element tableRef = getPdmElement(refElement, "c:ParentTable/o:Table");
       if (tableRef != null) {
         String tableId = tableRef.attributeValue("Ref");
         Element tableElement = getElementById(tableId, "o:Table");
         String forginTableName = tableElement.selectSingleNode("a:Code").getText();
         String forginModelName = parseName(forginTableName);
 
         String moduleName = tableElement.getParent().getParent().selectSingleNode("a:Code").getText();
 
         Element columnRefElement = 
           getPdmElement(indexRefElement.getParent().getParent(), "c:IndexColumns/o:IndexColumn/c:Column/o:Column");
 
         if (columnRefElement == null) {
           continue;
         }
 
         String columnRef = columnRefElement.attributeValue("Ref");
 
         Element columnElement = getElementById(columnRef, "o:Column");
 
         Element foreignElement = modelElement.addElement("foreign");
         foreignElement.addAttribute("name", forginModelName);
         foreignElement.addAttribute("column", columnElement.selectSingleNode("a:Code").getText());
         foreignElement.addAttribute("caption", columnElement.selectSingleNode("a:Name").getText());
         foreignElement.addAttribute("refModel", moduleName + "." + forginModelName);
         forginRefs.add(columnRef);
       }
     }
 
     return forginRefs;
   }
 
   private void addModelAttrElements(Element modelElement, Element pdmEntityElement, List<String> keyRefs, List<String> forginRefs){
     List<Element> columnElements = pdmEntityElement.selectNodes("c:Columns/o:Column");
     for (Element columnElement : columnElements)
     {
       if (forginRefs.contains(columnElement.attributeValue("Id"))) {
         continue;
       }
 
       addModelAttrElement(modelElement, columnElement, keyRefs);
     }
   }
 
   private void addModelAttrElement(Element modelElement, Element columnElement, List<String> keyRefs){
     Element attributeElement = modelElement.addElement("property");
 
     String caption = columnElement.selectSingleNode("a:Name").getText();
     String dataType = typeConvert(columnElement.selectSingleNode("a:DataType").getText());
 
     Node uniqueNode = columnElement.selectSingleNode("a:Unique");
 
     String columnName = columnElement.selectSingleNode("a:Code").getText();
 
     attributeElement.addAttribute("name", parseName(columnName));
     attributeElement.addAttribute("caption", caption);
 
     Node lengthElement = columnElement.selectSingleNode("a:Length");
     if (lengthElement != null) {
       attributeElement.addAttribute("length", lengthElement.getText());
     }
     if (keyRefs.contains(columnElement.attributeValue("Id"))) {
       attributeElement.addAttribute("isId", "true");
     }
     if (uniqueNode != null) attributeElement.addAttribute("notNull", "true");
     attributeElement.addAttribute("type", dataType);
     attributeElement.addAttribute("pType", columnElement.selectSingleNode("a:DataType").getText().split("\\(")[0]);
     attributeElement.addAttribute("column", columnName);
   }
 
   private List<Element> getPdmElements(Element scopeElement, String selectXPath){
     List list = null;
     XPath xPath = scopeElement.createXPath(selectXPath);
     xPath.setNamespaceURIs(namespaceURIs);
     list = xPath.selectNodes(scopeElement);
     return list;
   }
 
   private Element getPdmElement(Element scopeElement, String selectXPath){
     XPath xPath = scopeElement.createXPath(selectXPath);
     xPath.setNamespaceURIs(namespaceURIs);
     Node node = xPath.selectSingleNode(scopeElement);
     return (node == null) ? null : (Element)node;
   }
 
   private Element getElementById(String id, String tagName){
     if (StringUtils.isEmpty(id)) return null;
 
     Node node = this.pdmDoc.selectSingleNode("//" + tagName + "[@Id=\"" + id + "\"]");
     return (node == null) ? null : (Element)node;
   }
 
   private String parseName(String oldName){
     String newName = oldName.toLowerCase();
     if ((this.prefix != null) && (newName.startsWith(this.prefix))) {
       newName = newName.substring(this.prefix.length());
     }
 
     String[] names = newName.toLowerCase().split("_");
     StringBuffer nameBuf = new StringBuffer();
     int i = 0;
     for (String name : names) {
       if (name.length() > 1) {
         if (i > 0)
           nameBuf.append(name.substring(0, 1).toUpperCase() + name.substring(1));
         else {
           nameBuf.append(name);
         }
         ++i;
       }
     }
     return nameBuf.toString();
   }
 
   private String typeConvert(String pdmDataType){
     if (pdmDataType.indexOf("(") != -1) {
       pdmDataType = pdmDataType.substring(0, pdmDataType.indexOf("("));
     }
 
     if ("NUMBER".equalsIgnoreCase(pdmDataType)) {
       return "Long";
     }
     if (("DOUBLE".equalsIgnoreCase(pdmDataType)) || 
       ("FLOAT".equalsIgnoreCase(pdmDataType)))
       return "Double";
     if ("DATE".equalsIgnoreCase(pdmDataType))
       return "java.util.Date";
     if ("TIMESTAMP".equalsIgnoreCase(pdmDataType))
       return "java.sql.Timestamp";
     if (("BLOB".equalsIgnoreCase(pdmDataType)) || 
       ("CLOB".equalsIgnoreCase(pdmDataType))) {
       return "byte[]";
     }
     return "String";
   }
 
   public static void main(String[] args){
     Document document = Dom4jUtils.saxParse("E:/IDE/admin.xml");
     Pdm2ModelXml pdm2ModelXml = new Pdm2ModelXml(document);
 
     OutputFormat format = null;
     XMLWriter output = null;
     StringWriter stringWriter = new StringWriter();
 
     format = OutputFormat.createPrettyPrint();
     format.setEncoding("UTF-8");
     format.setOmitEncoding(false);
     output = new XMLWriter(stringWriter, format);
     try {
       output.write(pdm2ModelXml.getModelDoc());
     }
     catch (IOException e) {
       e.printStackTrace();
     }
 
     System.out.println(stringWriter.toString());
   }
 }
