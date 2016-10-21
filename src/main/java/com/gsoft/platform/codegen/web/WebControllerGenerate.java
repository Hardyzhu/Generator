package com.gsoft.platform.codegen.web;

import com.gsoft.platform.codegen.CodeGenerator;
import com.gsoft.platform.codegen.DataModel;
import com.gsoft.platform.codegen.GenerateConfig;
import com.gsoft.platform.codegen.freemarker.FreemarkerFactory;
import com.gsoft.platform.codegen.util.GenerateUtil;

import java.util.Map;

public class WebControllerGenerate implements CodeGenerator{
  
	public String[] generatorCode(DataModel webData, GenerateConfig config){
	    Map<String, Object> data = webData.getData();
	    String classPath = GenerateUtil.classNameToPath(((WebData)webData).getPackageName()) + 
	      "/" + webData.getClassName();
	    String webDataPath = config.getSrcMainJavaPath() + "/" + classPath + "Mapper.xml";
	
	    String dataTemplatePath = GenerateConfig.getInstance().getConfig("generator.web.data");
	    FreemarkerFactory.getInstance().writerFile(data, webDataPath, dataTemplatePath);
	
	    return new String[] { webDataPath };
	  }
}