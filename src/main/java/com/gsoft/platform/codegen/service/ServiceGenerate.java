package com.gsoft.platform.codegen.service;

import com.gsoft.platform.codegen.CodeGenerator;
import com.gsoft.platform.codegen.DataModel;
import com.gsoft.platform.codegen.GenerateConfig;
import com.gsoft.platform.codegen.GenerateConstants;
import com.gsoft.platform.codegen.freemarker.FreemarkerFactory;
import com.gsoft.platform.codegen.util.GenerateUtil;
import com.gsoft.platform.codegen.util.JavaImplFileUtil;

import java.util.Map;

public class ServiceGenerate implements CodeGenerator{
	
  public String[] generatorCode(DataModel serviceData, GenerateConfig config){
    Map<String, Object> data = serviceData.getData();
    String classPath = GenerateUtil.classNameToPath(((ServiceData)serviceData).getPackageName()) + "/" + serviceData.getClassName();

    String javaCodepath = config.getSrcMainJavaPath() + "/" + classPath + ".java";

    String serviceImplPath = getImplPath(javaCodepath);

    String testCodepath = config.getUnitSourcePath() + "/" + classPath + "Test.java";

    String serviceTemplatePath = config.getFtlHome() + GenerateConstants.GENERATOR_SERVICE;
    String serviceImplTemplatePath = config.getFtlHome() + GenerateConstants.GENERATOR_SERVICEIMPL;

    FreemarkerFactory.getInstance()
      .writerFile(data, javaCodepath, serviceTemplatePath);

    FreemarkerFactory.getInstance()
      .writerFile(data, serviceImplPath, serviceImplTemplatePath);

    return new String[] { 
      javaCodepath, 
      serviceImplPath, 
      testCodepath };
  }

  private String getImplPath(String servicePath){
    return JavaImplFileUtil.getImplFilePath(servicePath, "impl", "Impl");
  }
}