/*    */ package com.gsoft.platform.codegen.page;
/*    */ 
/*    */ import com.gsoft.platform.codegen.AbstractDataModel;
/*    */ import com.gsoft.platform.codegen.GenerateConfig;
/*    */ import com.gsoft.platform.codegen.domain.DomainData;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PageData extends AbstractDataModel
/*    */ {
/*    */   private DomainData domainData;
/*    */   private String moduleName;
/*    */ 
/*    */   public PageData(String moduleName, DomainData domainData)
/*    */   {
/* 23 */     this.moduleName = moduleName;
/* 24 */     this.domainData = domainData;
/*    */   }
/*    */ 
/*    */   public String getFullPath()
/*    */   {
/* 32 */     return getOutPath() + "/" + this.domainData.getName() + ".jsp";
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getData()
/*    */   {
/* 39 */     Map root = new HashMap();
/* 40 */     root.put("moduleName", this.moduleName);
/* 41 */     root.put("modelName", this.domainData.getName());
/* 42 */     root.put("modelClassName", this.domainData.getClassName());
/* 43 */     root.put("modelDescription", this.domainData.getDescription());
/* 44 */     root.put("attributes", this.domainData.getAttributesMapList());
/* 45 */     root.put("id", this.domainData.getIdAttributes());
/*    */ 
/* 47 */     root.put("childProject", GenerateConfig.getInstance().getConfig("generator.childProject"));
/* 48 */     return root;
/*    */   }
/*    */ 
/*    */   public String getClassName() {
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   public void setClassName(String className)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.page.PageData
 * JD-Core Version:    0.5.4
 */