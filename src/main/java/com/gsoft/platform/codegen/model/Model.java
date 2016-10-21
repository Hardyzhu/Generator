/*     */ package com.gsoft.platform.codegen.model;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.Domain;
/*     */ import com.gsoft.framework.core.dataobj.tree.TreeAttribute;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Model
/*     */   implements Domain
/*     */ {
/*     */   private static final long serialVersionUID = 8809408343097557856L;
/*     */   private String id;
/*     */   private String name;
/*     */   private String caption;
/*     */   private String moduleName;
/*     */   private String table;
/*     */   private String idType;
/*     */   private String idAttrName;
/*     */   private String toString;
/*     */   private String className;
/*     */   private List<NormalAttribute> attributes;
/*     */   private List<ForeignAttribute> foreignAttributes;
/*     */   private List<SetAttribute> setAttributes;
/*     */   private ModelFile modelFile;
/*     */   private String modelXPath;
/*     */   private boolean supportWorkflow;
/*     */   private String workflowName;
/*     */   private String workflowCaption;
/*     */   private String src;
/*     */ 
/*     */   public Model()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Model(String id, String name, String caption)
/*     */   {
/*  78 */     this.id = id;
/*  79 */     this.name = name;
/*  80 */     this.caption = caption;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  87 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  94 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getIdAttrName() {
/*  98 */     return this.idAttrName;
/*     */   }
/*     */ 
/*     */   public void setIdAttrName(String idAttrName) {
/* 102 */     this.idAttrName = idAttrName;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("id")
/*     */   public String getName()
/*     */   {
/* 110 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 117 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("text")
/*     */   public String getCaption()
/*     */   {
/* 125 */     return this.caption;
/*     */   }
/*     */ 
/*     */   public void setCaption(String caption)
/*     */   {
/* 132 */     this.caption = caption;
/*     */   }
/*     */ 
/*     */   public String getModuleName()
/*     */   {
/* 139 */     return this.moduleName;
/*     */   }
/*     */ 
/*     */   public void setModuleName(String moduleName)
/*     */   {
/* 146 */     this.moduleName = moduleName;
/*     */   }
/*     */ 
/*     */   public String getTable()
/*     */   {
/* 153 */     return this.table;
/*     */   }
/*     */ 
/*     */   public void setTable(String table)
/*     */   {
/* 160 */     this.table = table;
/*     */   }
/*     */ 
/*     */   public String getIdType()
/*     */   {
/* 167 */     return this.idType;
/*     */   }
/*     */ 
/*     */   public void setIdType(String idType)
/*     */   {
/* 174 */     this.idType = idType;
/*     */   }
/*     */ 
/*     */   public String getToString()
/*     */   {
/* 181 */     return this.toString;
/*     */   }
/*     */ 
/*     */   public void setToString(String toString)
/*     */   {
/* 188 */     this.toString = toString;
/*     */   }
/*     */ 
/*     */   public String getClassName()
/*     */   {
/* 196 */     return this.className;
/*     */   }
/*     */ 
/*     */   public void setClassName(String className)
/*     */   {
/* 203 */     this.className = className;
/*     */   }
/*     */ 
/*     */   public List<NormalAttribute> getAttributes()
/*     */   {
/* 210 */     return this.attributes;
/*     */   }
/*     */ 
/*     */   public void setAttributes(List<NormalAttribute> attributes)
/*     */   {
/* 217 */     for (NormalAttribute attribute : attributes) {
/* 218 */       if ("true".equals(attribute.getIsId())) {
/* 219 */         setIdAttrName(attribute.getName());
/*     */       }
/*     */     }
/* 222 */     this.attributes = attributes;
/*     */   }
/*     */ 
/*     */   public List<ForeignAttribute> getForeignAttributes()
/*     */   {
/* 229 */     return this.foreignAttributes;
/*     */   }
/*     */ 
/*     */   public void setForeignAttributes(List<ForeignAttribute> foreignAttributes)
/*     */   {
/* 236 */     this.foreignAttributes = foreignAttributes;
/*     */   }
/*     */ 
/*     */   public List<SetAttribute> getSetAttributes()
/*     */   {
/* 243 */     return this.setAttributes;
/*     */   }
/*     */ 
/*     */   public void setSetAttributes(List<SetAttribute> setAttributes)
/*     */   {
/* 250 */     this.setAttributes = setAttributes;
/*     */   }
/*     */ 
/*     */   public ModelFile getModelFile()
/*     */   {
/* 257 */     return this.modelFile;
/*     */   }
/*     */ 
/*     */   public void setModelFile(ModelFile modelFile)
/*     */   {
/* 264 */     this.modelFile = modelFile;
/*     */   }
/*     */ 
/*     */   public String getModelXPath()
/*     */   {
/* 271 */     return this.modelXPath;
/*     */   }
/*     */ 
/*     */   public void setModelXPath(String modelXPath)
/*     */   {
/* 278 */     this.modelXPath = modelXPath;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 286 */     int prime = 31;
/* 287 */     int result = 1;
/* 288 */     result = 31 * result + (
/* 289 */       (this.moduleName == null) ? 0 : this.moduleName.hashCode());
/* 290 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 291 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 299 */     if (this == obj)
/* 300 */       return true;
/* 301 */     if (obj == null)
/* 302 */       return false;
/* 303 */     if (super.getClass() != obj.getClass())
/* 304 */       return false;
/* 305 */     Model other = (Model)obj;
/* 306 */     if (this.moduleName == null)
/* 307 */       if (other.moduleName != null)
/* 308 */         return false;
/* 309 */     else if (!this.moduleName.equals(other.moduleName))
/* 310 */       return false;
/* 311 */     if (this.name == null)
/* 312 */       if (other.name != null)
/* 313 */         return false;
/* 314 */     else if (!this.name.equals(other.name))
/* 315 */       return false;
/* 316 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isSupportWorkflow() {
/* 320 */     return this.supportWorkflow;
/*     */   }
/*     */ 
/*     */   public void setSupportWorkflow(boolean supportWorkflow) {
/* 324 */     this.supportWorkflow = supportWorkflow;
/*     */   }
/*     */ 
/*     */   public String getWorkflowName() {
/* 328 */     return this.workflowName;
/*     */   }
/*     */ 
/*     */   public void setWorkflowName(String workflowName) {
/* 332 */     this.workflowName = workflowName;
/*     */   }
/*     */ 
/*     */   public String getWorkflowCaption() {
/* 336 */     return this.workflowCaption;
/*     */   }
/*     */ 
/*     */   public void setWorkflowCaption(String workflowCaption) {
/* 340 */     this.workflowCaption = workflowCaption;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("src")
/*     */   public String getSrc()
/*     */   {
/* 346 */     return this.src + "?moduleName=" + this.moduleName + "&modelName=" + this.name;
/*     */   }
/*     */ 
/*     */   public void setSrc(String src) {
/* 350 */     this.src = src;
/*     */   }
/*     */ 
/*     */   public String getSimpleClassName() {
/* 354 */     return ((this.className != null) && (this.className.length() > 0)) ? 
/* 355 */       this.className.substring(this.className.lastIndexOf(".") + 1) : "";
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.Model
 * JD-Core Version:    0.5.4
 */