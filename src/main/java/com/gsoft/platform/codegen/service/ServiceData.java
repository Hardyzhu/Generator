/*    */ package com.gsoft.platform.codegen.service;
/*    */ 
/*    */ import com.gsoft.platform.codegen.AbstractJavaDataModel;
/*    */ import com.gsoft.platform.codegen.dao.DaoData;
/*    */ import com.gsoft.platform.codegen.domain.DomainData;
/*    */ import com.gsoft.platform.codegen.model.Model;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ServiceData extends AbstractJavaDataModel
/*    */ {
/*    */   private DaoData daoCode;
/*    */   private String beanName;
/*    */   private String packageName;
/*    */ 
/*    */   public ServiceData(DaoData daoCode, String beanName)
/*    */   {
/* 26 */     this.daoCode = daoCode;
/* 27 */     this.beanName = beanName;
/* 28 */     this.packageName = daoCode.getPackageName();
/*    */   }
/*    */ 
/*    */   public DaoData getDaoCode()
/*    */   {
/* 33 */     return this.daoCode;
/*    */   }
/*    */ 
/*    */   public void setDaoCode(DaoData daoCode) {
/* 37 */     this.daoCode = daoCode;
/*    */   }
/*    */   public String getBeanName() {
/* 40 */     return this.beanName;
/*    */   }
/*    */ 
/*    */   public void setBeanName(String beanName) {
/* 44 */     this.beanName = beanName;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getData()
/*    */   {
/* 50 */     Map root = new HashMap();
/* 51 */     DomainData domainCode = this.daoCode.getDomainCode();
/* 52 */     String daoClassName = this.daoCode.getClassName();
/* 53 */     String domainClassName = domainCode.getClassName();
/* 54 */     String modelName = domainClassName.substring(0, 1).toLowerCase() + domainClassName.substring(1);
/* 55 */     root.put("modelName", modelName);
/* 56 */     root.put("moduleName", domainCode.getModel().getModuleName());
/* 57 */     root.put("idAttribute", domainCode.getIdAttribute());
/*    */ 
/* 59 */     root.put("modelPath", domainCode.getPackageName());
/* 60 */     root.put("modelClassName", domainCode.getClassName());
/* 61 */     root.put("idType", domainCode.getModel().getIdType());
/* 62 */     root.put("package", getPackageName());
/* 63 */     root.put("daoClassPath", this.daoCode.getPackageName() + "." + this.daoCode.getClassName());
/* 64 */     root.put("serviceClassPath", getPackageName() + "." + getClassName());
/* 65 */     root.put("daoClassName", daoClassName);
/* 66 */     root.put("daoName", daoClassName.substring(0, 1).toLowerCase() + daoClassName.substring(1));
/* 67 */     root.put("setAttributes", domainCode.getSetAttributesMapList());
/* 68 */     root.put("supportWorkflow", Boolean.valueOf(domainCode.getModel().isSupportWorkflow()));
/* 69 */     root.put("workflowName", domainCode.getModel().getWorkflowName());
/* 70 */     root.put("workflowCaption", domainCode.getModel().getWorkflowCaption());
/*    */ 
/* 72 */     if (domainCode.getSetAttributesMapList().size() > 0) {
/* 73 */       root.put("hasSet", "ture");
/*    */     }
/* 75 */     return root;
/*    */   }
/*    */ 
/*    */   public String getPackageName() {
/* 79 */     return this.packageName;
/*    */   }
/*    */ 
/*    */   public void setPackageName(String packageName) {
/* 83 */     this.packageName = packageName;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.service.ServiceData
 * JD-Core Version:    0.5.4
 */