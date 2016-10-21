package com.gsoft.platform.codegen.domain;

import com.gsoft.platform.codegen.CodeGenerator;
import com.gsoft.platform.codegen.DataModel;
import com.gsoft.platform.codegen.GenerateConfig;
import com.gsoft.platform.codegen.GenerateConstants;
import com.gsoft.platform.codegen.freemarker.FreemarkerFactory;
import com.gsoft.platform.codegen.util.GenerateUtil;

import java.util.Map;

public class DomainGenerate
  implements CodeGenerator
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public String[] generatorCode(DataModel domainData, GenerateConfig config){
    Map data = domainData.getData();

    String classPath = 
      GenerateUtil.classNameToPath(((DomainData)domainData).getPackageName()) + "/" + domainData.getClassName();

    String javaCodepath = config.getSrcMainJavaPath() + "/" + classPath + ".java";

    String templataPath = config.getFtlHome() + GenerateConstants.GENERATOR_DOMAIN;
    FreemarkerFactory.getInstance().writerFile(data, javaCodepath, templataPath);

    return new String[] { 
      javaCodepath };
  }
}
