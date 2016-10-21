package com.gsoft.platform.codegen.page;

import com.gsoft.platform.codegen.CodeGenerator;
import com.gsoft.platform.codegen.DataModel;
import com.gsoft.platform.codegen.GenerateConfig;

import java.util.Map;

public class PageGenerate implements CodeGenerator{
  public String[] generatorCode(DataModel code, GenerateConfig config){
    Map<String, Object> data = code.getData();

    String pagePath = config.getJspPath() + "/" + 
      data.get("moduleName") + "/" + 
      data.get("modelName") + "/" + 
      data.get("modelName") + ".jsp";

    return new String[] { 
      pagePath };
  }
}