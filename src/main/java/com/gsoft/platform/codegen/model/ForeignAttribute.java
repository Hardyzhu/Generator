/*    */ package com.gsoft.platform.codegen.model;
/*    */ 
/*    */ import com.gsoft.platform.codegen.GenerateException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ForeignAttribute extends AbstractAttribute
/*    */ {
/*    */   private static final long serialVersionUID = 2868285119680368181L;
/*    */   private String column;
/*    */   private String cascade;
/*    */   private String refModuleName;
/*    */   private String refModelName;
/*    */   private Model refModel;
/*    */ 
/*    */   public ForeignAttribute()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ForeignAttribute(String name, String column, String refModuleName, String refModelName)
/*    */   {
/* 30 */     this.name = name;
/* 31 */     this.column = column;
/* 32 */     this.refModelName = refModelName;
/* 33 */     this.refModuleName = refModuleName;
/*    */   }
/*    */ 
/*    */   public String getColumn() {
/* 37 */     return this.column;
/*    */   }
/*    */ 
/*    */   public void setColumn(String column) {
/* 41 */     this.column = column;
/*    */   }
/*    */ 
/*    */   public Model getRefModel() {
/* 45 */     if (this.refModel == null)
/*    */     {
/* 47 */       String[] splitRefModels = this.refModelName.split("\\.");
/* 48 */       if (splitRefModels.length == 2) {
/* 49 */         this.refModuleName = splitRefModels[0];
/* 50 */         this.refModelName = splitRefModels[1];
/*    */       }
/* 52 */       this.refModel = ModelFactory.getInstance().getModel(this.refModuleName, this.refModelName);
/*    */     }
/* 54 */     return this.refModel;
/*    */   }
/*    */ 
/*    */   public void setRefModel(Model refModel) {
/* 58 */     this.refModel = refModel;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> toMap()
/*    */   {
/* 63 */     Map map = new HashMap();
/* 64 */     Model refModel = getRefModel();
/* 65 */     if (refModel != null) {
/* 66 */       map.put("type", getRefModel().getClassName());
/* 67 */       map.put("name", getName());
/* 68 */       map.put("FUName", getFUName());
/* 69 */       map.put("column", getColumn());
/* 70 */       if (this.caption != null) map.put("description", getCaption());
/* 71 */       if (this.cascade != null) map.put("cascade", getCascade()); 
/*    */     }
/*    */     else {
/* 73 */       throw new GenerateException("外键模型：" + this.refModelName + "未找到，如果是引用的其他模块的外键，需要使用模块前缀比如admin." + this.refModelName + "！");
/*    */     }
/* 75 */     return map;
/*    */   }
/*    */ 
/*    */   public String getCascade() {
/* 79 */     return this.cascade;
/*    */   }
/*    */ 
/*    */   public void setCascade(String cascade) {
/* 83 */     this.cascade = cascade;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.ForeignAttribute
 * JD-Core Version:    0.5.4
 */