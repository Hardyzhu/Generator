/*    */ package com.gsoft.platform.codegen.model;
/*    */ 
/*    */ import com.gsoft.framework.core.dataobj.Domain;
/*    */ import com.gsoft.framework.core.dataobj.tree.TreeAttribute;
/*    */ import java.util.Map;
/*    */ 
/*    */ public abstract class AbstractAttribute
/*    */   implements Domain
/*    */ {
/*    */   private static final long serialVersionUID = -156057781443860479L;
/*    */   protected String name;
/*    */   protected String caption;
/*    */   protected String funname;
/*    */ 
/*    */   @TreeAttribute("id")
/*    */   public String getName()
/*    */   {
/* 30 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 34 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @TreeAttribute("text")
/*    */   public String getCaption() {
/* 39 */     return this.caption;
/*    */   }
/*    */ 
/*    */   public void setCaption(String caption) {
/* 43 */     this.caption = caption;
/*    */   }
/*    */ 
/*    */   protected String getFUName() {
/* 47 */     return upperFirstChar(getName());
/*    */   }
/*    */ 
/*    */   protected String upperFirstChar(String str) {
/* 51 */     String subStr = "";
/* 52 */     if (str.length() > 1) {
/* 53 */       subStr = str.substring(1);
/*    */     }
/* 55 */     return str.substring(0, 1).toUpperCase() + subStr;
/*    */   }
/*    */ 
/*    */   public String getFunname() {
/* 59 */     this.funname = getFUName();
/* 60 */     return this.funname;
/*    */   }
/*    */ 
/*    */   public abstract Map<String, Object> toMap();
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.AbstractAttribute
 * JD-Core Version:    0.5.4
 */