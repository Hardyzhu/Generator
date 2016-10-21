/*    */ package com.gsoft.platform.codegen.dao;
/*    */ 
/*    */ import com.gsoft.platform.codegen.AbstractJavaDataModel;
/*    */ import com.gsoft.platform.codegen.domain.DomainData;
/*    */ import com.gsoft.platform.codegen.model.Model;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DaoData extends AbstractJavaDataModel
/*    */ {
/*    */   private DomainData domainCode;
/*    */ 
/*    */   public DaoData(DomainData domainCode)
/*    */   {
/* 21 */     this.domainCode = domainCode;
/*    */   }
/*    */ 
/*    */   public DomainData getDomainCode() {
/* 25 */     return this.domainCode;
/*    */   }
/*    */ 
/*    */   public void setDomainCode(DomainData domainCode) {
/* 29 */     this.domainCode = domainCode;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getData() {
/* 33 */     Map root = new HashMap();
/* 34 */     root.put("modelName", this.domainCode.getName());
/* 35 */     root.put("moduleName", this.domainCode.getModel().getModuleName());
/*    */ 
/* 37 */     root.put("modelPath", this.domainCode.getPackageName());
/* 38 */     root.put("modelClassName", this.domainCode.getClassName());
/* 39 */     root.put("idType", this.domainCode.getModel().getIdType());
/* 40 */     root.put("package", getPackageName());
/* 41 */     root.put("daoClassPath", getClassPath());
/*    */ 
/* 43 */     if (this.domainCode.getSetAttributesMapList().size() > 0) {
/* 44 */       root.put("hasSet", "ture");
/*    */     }
/* 46 */     return root;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.dao.DaoData
 * JD-Core Version:    0.5.4
 */