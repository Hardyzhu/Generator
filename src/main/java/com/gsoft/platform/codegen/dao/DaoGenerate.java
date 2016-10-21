package com.gsoft.platform.codegen.dao;

import com.gsoft.platform.codegen.CodeGenerator;
import com.gsoft.platform.codegen.DataModel;
import com.gsoft.platform.codegen.GenerateConfig;
import com.gsoft.platform.codegen.GenerateConstants;
import com.gsoft.platform.codegen.freemarker.FreemarkerFactory;
import com.gsoft.platform.codegen.util.GenerateUtil;
import com.gsoft.platform.codegen.util.JavaImplFileUtil;

import java.util.Map;

public class DaoGenerate implements CodeGenerator{
	
  public String[] generatorCode(DataModel daoData, GenerateConfig config){
    Map<String, Object> data = daoData.getData();

    String classPath = GenerateUtil.classNameToPath(((DaoData)daoData).getPackageName()) + "/" + daoData.getClassName();

    String javaCodepath = config.getSrcMainJavaPath() + "/" + classPath + ".java";

    String daoImplPath = getImplPath(javaCodepath);

    String daoTemplatePath = config.getFtlHome() + GenerateConstants.GENERATOR_DAO;

    FreemarkerFactory.getInstance().writerFile(data, javaCodepath, daoTemplatePath);

    return new String[] { 
      javaCodepath, 
      daoImplPath };
  }

  private String getImplPath(String daoPath){
    return JavaImplFileUtil.getImplFilePath(daoPath, "hibernate", "Hibernate");
  }
}