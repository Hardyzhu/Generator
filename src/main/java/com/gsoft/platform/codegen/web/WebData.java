package com.gsoft.platform.codegen.web;

import com.gsoft.platform.codegen.AbstractJavaDataModel;
import com.gsoft.platform.codegen.dao.DaoData;
import com.gsoft.platform.codegen.domain.DomainData;
import com.gsoft.platform.codegen.model.Model;
import com.gsoft.platform.codegen.service.ServiceData;

import java.util.HashMap;
import java.util.Map;

public class WebData extends AbstractJavaDataModel{
  private ServiceData serviceData;
  private String moduleName;
  private DaoData daoCode;
  private Model model;

  public DaoData getDaoCode(){
    return this.daoCode;
  }

  public void setDaoCode(DaoData daoCode) {
    this.daoCode = daoCode;
  }
  
  public ServiceData getServiceData(){
    return this.serviceData;
  }

  public void setServiceData(ServiceData serviceData){
    this.serviceData = serviceData;
  }

  public WebData(DaoData daoCode,String moduleName, ServiceData serviceData, Model model) {
	this.daoCode = daoCode;
    this.moduleName = moduleName;
    this.serviceData = serviceData;
    this.model = model;
  }
  public Model getModel() {
    return this.model;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  public String getModuleName() {
    return this.moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public Map<String, Object> getData() {
    Map<String, Object> root = new HashMap<String, Object>();
    DomainData modelCode = this.serviceData.getDaoCode().getDomainCode();
    root.put("package", getPackageName());
    root.put("modelClassPath", modelCode.getClassPath());
    root.put("idAttribute", modelCode.getIdAttribute());
    
    root.put("tableName", this.model.getTable());
    root.put("moduleName", getModuleName());
    root.put("modulePath", getModuleName().replace('.', '/'));
    root.put("modelName", modelCode.getName());
    root.put("modelDescription", modelCode.getModel().getCaption());
    root.put("modelId", modelCode.getModel().getId());
    root.put("modelClassName", modelCode.getClassName());
    root.put("serviceClassPath", this.serviceData.getClassPath());
    root.put("serviceClassName", this.serviceData.getClassName());
    root.put("serviceBeanName", this.serviceData.getBeanName());
    root.put("attributes", modelCode.getAttributesMapList());
    root.put("setAttributes", modelCode.getSetAttributesMapList());
    root.put("idAttributes", modelCode.getIdAttributes());
    root.put("daoClassPath", this.daoCode.getPackageName() + "." + this.daoCode.getClassName());
    if (modelCode.getAttributesMapList().size() > 0) {
      root.put("hasSet", "ture");
    }
    return root;
  }

  public String getFullPath() {
    return this.outPath + "/" + getPackageName().replace('.', '/') + "/" + getClassName() + ".java";
  }
}