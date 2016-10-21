package com.gsoft.platform.codegen.controller;

import com.gsoft.platform.codegen.CodeGenerator;
import com.gsoft.platform.codegen.DataModel;
import com.gsoft.platform.codegen.GenerateConfig;
import com.gsoft.platform.codegen.freemarker.FreemarkerFactory;
import com.gsoft.platform.codegen.util.GenerateUtil;

import java.util.Map;

public class ControllerGenerate implements CodeGenerator{
  
	public String[] generatorCode(DataModel controllerData, GenerateConfig config){
	    Map<String, Object> data = controllerData.getData();
	    String classPath = GenerateUtil.classNameToPath(((ControllerData)controllerData).getPackageName()) + 
	      "/" + controllerData.getClassName();
	    String controllerDataPath = config.getSrcMainJavaPath() + "/" + classPath + "Controller.java";
	
	    String dataTemplatePath = GenerateConfig.getInstance().getConfig("generator.controller");
	    FreemarkerFactory.getInstance().writerFile(data, controllerDataPath, dataTemplatePath);
	    return new String[] { controllerDataPath };
	  }
}