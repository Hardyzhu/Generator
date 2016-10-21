package com.gsoft.platform.codegen;

import com.gsoft.platform.codegen.controller.ControllerGenerate;
import com.gsoft.platform.codegen.dao.DaoGenerate;
import com.gsoft.platform.codegen.domain.DomainGenerate;
import com.gsoft.platform.codegen.page.PageGenerate;
import com.gsoft.platform.codegen.service.ServiceGenerate;
import com.gsoft.platform.codegen.web.WebControllerGenerate;

import freemarker.template.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenerateFactory {
	private static final Log logger = LogFactory.getLog(GenerateFactory.class);

	  private static GenerateFactory instance = new GenerateFactory();
	  protected Configuration cfg;

	  private GenerateFactory()
	  {
	    logger.info("构造生成工厂实例");
	  }

	  public static GenerateFactory getInstance()
	  {
	    return instance;
	  }

	  private CodeGenerator getGenerator(String type) {
	    CodeGenerator generator = null;
	    if (type.equals("domain"))
	      generator = new DomainGenerate();
	    else if (type.equals("dao"))
	      generator = new DaoGenerate();
	    else if (type.equals("service"))
	      generator = new ServiceGenerate();
	    else if (type.equals("web"))
	      generator = new WebControllerGenerate();
	    else if (type.equals("page")) {
	      generator = new PageGenerate();
	    }else if (type.equals("controller")) {
	      generator = new ControllerGenerate();
	    }
	    return generator;
	  }

	  public String[] generatorCode(String type, DataModel code, GenerateConfig config) {
	    return getGenerator(type).generatorCode(code, config);
	  }
}
