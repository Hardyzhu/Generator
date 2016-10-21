 package com.gsoft.platform.codegen;
 
 import com.gsoft.framework.util.CollectionUtils;
import com.gsoft.platform.codegen.compile.ModelCompile;
import com.gsoft.platform.codegen.controller.ControllerData;
import com.gsoft.platform.codegen.dao.DaoData;
import com.gsoft.platform.codegen.domain.DomainData;
import com.gsoft.platform.codegen.model.Model;
import com.gsoft.platform.codegen.model.ModelFactory;
import com.gsoft.platform.codegen.page.PageData;
import com.gsoft.platform.codegen.service.ServiceData;
import com.gsoft.platform.codegen.util.GenerateUtil;
import com.gsoft.platform.codegen.web.WebData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
 public class GenerateEngine{
   private static final Log logger = LogFactory.getLog(GenerateEngine.class);
   private GenerateConfig config;
 
   public GenerateConfig getConfig(){
     if (this.config == null) {
       this.config = new GenerateConfig();
     }
     return this.config;
   }
 
   public void setConfig(GenerateConfig config){
     this.config = config;
   }
 
   public void generate(String moduleName, String modelName, int type)
     throws GenerateException{
   }
 
   public void generate(String moduleName)
     throws GenerateException{
     generate(moduleName, getConfig());
   }
 
   public void generate(String moduleName, GenerateConfig config)
     throws GenerateException{
     if (config == null) {
       config = getConfig();
     }
     List<Model> models = ModelFactory.getInstance().getModuleModels(moduleName);
     for (Model model : models) {
       generate(model, moduleName, config);
     }
 
     logger.info("代码生成完成");
   }
 
   public void remove(String moduleName, String modelName)
     throws GenerateException{
     excludeRemove(moduleName, modelName, false);
   }
 
   public void removeExcludeDomain(String moduleName, String modelName)
     throws GenerateException{
     excludeRemove(moduleName, modelName, true);
   }
 
   private void excludeRemove(String moduleName, String modelName, boolean removeDomain){
     Model model = ModelFactory.getInstance().getModel(moduleName, modelName);
     if (model != null) {
       String srcPath = getSourceOutPath();
       String pagePath = getPageSourceRootPath() + "/" + getModulePath(moduleName) + "/" + modelName;
       String javaModuleOutPath = srcPath + "/" + getConfig().getPackagePrefix().replace('.', '/') + "/" + getModulePath(moduleName) + "/";
       String entityOutPath = srcPath + "/" + getConfig().getPackagePrefix().replace('.', '/') + "/entity/" + getModulePath(moduleName) + "/";
       deleteFile(pagePath);
 
       deleteFile(javaModuleOutPath + "/action/" + modelName + "Action.java");
 
       deleteFile(javaModuleOutPath + "/service/" + modelName + "Manager.java");
 
       deleteFile(javaModuleOutPath + "/dao/" + modelName + "Dao.java");
 
       if (removeDomain)
         deleteFile(entityOutPath + "/" + modelName + ".java");
     }
   }
 
   public void removeModule(String moduleName){
     String srcPath = getSourceOutPath();
     String pagePath = getPageSourceRootPath() + "/" + moduleName;
     String javaModuleOutPath = srcPath + "/" + getConfig().getPackagePrefix().replace('.', '/') + "/" + moduleName + "/";
     String testModuleOutPath = javaModuleOutPath.replace("/src/", "/test/");
 
     deleteFile(pagePath);
     deleteFile(javaModuleOutPath);
     deleteFile(testModuleOutPath);
   }
 
   private void deleteFile(String filePath) {
     File file = new File(filePath);
     if (file.isDirectory()) {
       File[] childs = file.listFiles();
       for (File child : childs) {
         deleteFile(child.getAbsolutePath());
       }
     }
     file.delete();
   }
 
   @SuppressWarnings({ "unchecked", "rawtypes" })
public List<String> generate(Model model, String moduleName, GenerateConfig config)
     throws GenerateException{
     if (model.getIdAttrName() == null){
       return new ArrayList();
     }
 
     String outPath = 
       config.getSourceHome() + "/" + config.getJavaMainHome();
     String packagePrefix = config.getPackagePrefix();
 
     String modulePrefix = packagePrefix + "." + moduleName;
 
     String serviceBeanName = model.getName() + "Service";
 
     DomainData domainData = getDomainData(model, modulePrefix + ".entity", outPath);
 
     DaoData daoData = getDaoData(domainData, modulePrefix, outPath);
 
     ServiceData serviceData = 
       getServiceData(daoData, modulePrefix, serviceBeanName, outPath);
 
     WebData webData = 
       getWebData(model,daoData,moduleName, serviceData, modulePrefix, outPath);
     
     ControllerData controllerData = 
    	       getControllerData(model,daoData,moduleName, serviceData, modulePrefix, outPath);
 
     PageData pageData = getPageData(domainData, moduleName);
 
     moduleFileDirectory(model, packagePrefix, moduleName, config);
 
     String[] domainFiles = GenerateFactory.getInstance().generatorCode("domain", domainData, config);
     String[] daoFiles = GenerateFactory.getInstance().generatorCode("dao", daoData, config);
     String[] serviceFiles = GenerateFactory.getInstance().generatorCode("service", serviceData, config);
//     String[] webFiles = GenerateFactory.getInstance().generatorCode("web", webData, config);
     String[] controllerFiles = GenerateFactory.getInstance().generatorCode("controller", controllerData, config);
//     String[] pageFiles = GenerateFactory.getInstance().generatorCode("page", pageData, config);
 
     List files = new ArrayList();
     files.addAll(CollectionUtils.arrayToList(domainFiles));
     files.addAll(CollectionUtils.arrayToList(daoFiles));
     files.addAll(CollectionUtils.arrayToList(serviceFiles));
     files.addAll(CollectionUtils.arrayToList(controllerFiles));
//     files.addAll(CollectionUtils.arrayToList(pageFiles));
 
     ModelCompile.getInstance().compile(domainData);
 
     return files;
   }
 
   private ControllerData getControllerData(Model model, DaoData daoData,
		String moduleName, ServiceData serviceData, String modulePrefix,
		String outPath) {
	   ControllerData controllerData = new ControllerData(daoData,moduleName, serviceData,model);
	   controllerData.setClassName(serviceData.getDaoCode().getDomainCode()
	       .getClassName());
	   controllerData.setDescription("action");
	   controllerData.setOutPath(outPath);
	   controllerData.setPackageName(modulePrefix + ".controller");
	   return controllerData;
   }

   private String getProjectHome() {
     return getConfig().getSourceHome();
   }
 
   private String getSourceOutPath(){
     String outPath = getProjectHome() + "/" + getConfig().getJavaMainHome();
     return outPath;
   }
 
   private String getPageSourceRootPath(){
     return getProjectHome() + "/" + getConfig().getJspHome();
   }
 
   private PageData getPageData(DomainData domainData, String moduleName){
     String outPath = getPageSourceRootPath() + "/" + getModulePath(moduleName) + "/" + domainData.getName();
     checkFileDirectory(getPageSourceRootPath() + "/" + getModulePath(moduleName));
     checkFileDirectory(outPath);
 
     String checkPath = this.config.getSrcMainJavaPath();
     checkFileDirectory(checkPath);
     checkPath = checkPath + "/" + moduleName;
     checkFileDirectory(checkPath);
     checkPath = checkPath + "/" + domainData.getName();
     checkFileDirectory(checkPath);
 
     PageData pageData = new PageData(moduleName, domainData);
     pageData.setOutPath(outPath);
     return pageData;
   }
 
   private void moduleFileDirectory(Model model, String packagePrefix, String moduleName, GenerateConfig config){
     String classPath = GenerateUtil.classNameToPath(packagePrefix);
     String javaBasePath = config.getSrcMainJavaPath() + 
       "/" + classPath;
     String testBasePath = config.getSrcTestJavaPath() + 
       "/" + classPath;
 
     String modulePath = getModulePath(moduleName);
 
     String[] packagePaths = packagePrefix.split("\\.");
 
     String javaPackageFilePath = config.getSrcMainJavaPath() + "/";
//     String testPackageFilePath = config.getSrcTestJavaPath() + "/";
     String resourcesPackageFilePath = config.getSrcMainJavaPath() + "/";
 
     for (String packagePath : packagePaths) {
       javaPackageFilePath = javaPackageFilePath + packagePath + "/";
//       testPackageFilePath = testPackageFilePath + packagePath + "/";
       resourcesPackageFilePath = resourcesPackageFilePath + packagePath + "/";
       checkFileDirectory(javaPackageFilePath);
//       checkFileDirectory(testPackageFilePath);
       checkFileDirectory(resourcesPackageFilePath);
     }
 
     checkFileDirectory(javaBasePath + "/");
     checkFileDirectory(javaBasePath + "/" + modulePath);
 
     checkFileDirectory(javaBasePath + "/" + modulePath + "/entity");
     checkFileDirectory(javaBasePath + "/" + modulePath + "/controller");
 
     checkFileDirectory(javaBasePath + "/" + modulePath + "/mapper");
//     checkFileDirectory(javaBasePath + "/" + modulePath + "/mapper/hibernate");
 
     checkFileDirectory(javaBasePath + "/" + modulePath + "/service");
     checkFileDirectory(javaBasePath + "/" + modulePath + "/service/impl");
 
     checkFileDirectory(javaBasePath + "/" + modulePath + "/web");
 
//     checkFileDirectory(testBasePath + "/");
//     checkFileDirectory(testBasePath + "/" + modulePath);
//     checkFileDirectory(testBasePath + "/" + modulePath + "/service");
 
//     String jspPath = config.getJspPath();
//     checkFileDirectory(jspPath);
//     checkFileDirectory(jspPath + "/" + modulePath);
//     checkFileDirectory(jspPath + "/" + modulePath + "/" + model.getName());
   }
 
   private void checkFileDirectory(String directoryPath){
     File directory = new File(directoryPath);
     if (!directory.exists())
       directory.mkdirs();
   }
 
   private DomainData getDomainData(Model model, String packageName, String outPath){
     String modelName = model.getName();
     String modelClassName = modelName.substring(0, 1).toUpperCase() + 
       modelName.substring(1);
 
     DomainData domainCode = new DomainData(model);
     domainCode.setClassName(modelClassName);
     domainCode.setOutPath(outPath);
     domainCode.setPackageName(packageName);
     return domainCode;
   }
 
   private DaoData getDaoData(DomainData domainCode, String modulePrefix, String outPath){
     DaoData daoCode = new DaoData(domainCode);
     daoCode.setClassName(domainCode.getClassName() + "Mapper");
     daoCode.setDescription("mapper");
     daoCode.setOutPath(outPath);
     daoCode.setPackageName(modulePrefix + ".mapper");
     return daoCode;
   }
 
   private ServiceData getServiceData(DaoData daoCode, String modulePrefix, String beanName, String outPath){
     ServiceData serviceCode = new ServiceData(daoCode, beanName);
     serviceCode.setClassName(daoCode.getDomainCode().getClassName() + 
       "Service");
     serviceCode.setDescription("service");
     serviceCode.setOutPath(outPath);
     serviceCode.setPackageName(modulePrefix + ".service");
     return serviceCode;
   }
 
   private WebData getWebData(Model model,DaoData daoCode, String moduleName, ServiceData serviceData, String modulePrefix, String outPath){
     WebData webData = new WebData(daoCode,moduleName, serviceData,model);
     webData.setClassName(serviceData.getDaoCode().getDomainCode()
       .getClassName());
     webData.setDescription("action");
     webData.setOutPath(outPath);
     webData.setPackageName(modulePrefix + ".web");
     return webData;
   }
 
   private String getModulePath(String moduleName){
     return moduleName.replace('.', '/');
   }
 }