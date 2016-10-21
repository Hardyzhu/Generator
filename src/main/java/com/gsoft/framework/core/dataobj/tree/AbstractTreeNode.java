/*     */ package com.gsoft.framework.core.dataobj.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractTreeNode
/*     */   implements TreeNode
/*     */ {
/*     */   private static final long serialVersionUID = -9047174823228858025L;
/*     */   protected TreeNode parent;
/*     */   protected String parentId;
/*  25 */   protected List<TreeNode> children = new ArrayList();
/*     */   protected String id;
/*     */   protected String code;
/*     */   protected String text;
/*     */   protected String group;
/*     */   private int num;
/*     */   private int level;
/*     */ 
/*     */   public AbstractTreeNode(String id, String text)
/*     */   {
/*  40 */     this.id = id;
/*  41 */     this.text = text;
/*     */   }
/*     */ 
/*     */   public void addChild(TreeNode treeNode)
/*     */   {
/*  48 */     if ((treeNode != null) && (!this.children.contains(treeNode)))
/*  49 */       this.children.add(treeNode);
/*     */   }
/*     */ 
/*     */   public void removeChild(TreeNode treeNode)
/*     */   {
/*  57 */     if (treeNode != null)
/*  58 */       this.children.remove(treeNode);
/*     */   }
/*     */ 
/*     */   public TreeNode getParent()
/*     */   {
/*  66 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(TreeNode parent)
/*     */   {
/*  73 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public List<TreeNode> getChildren()
/*     */   {
/*  80 */     return this.children;
/*     */   }
/*     */ 
/*     */   public void setChildren(List<TreeNode> children)
/*     */   {
/*  87 */     this.children = children;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  94 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 101 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 108 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 115 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 122 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 129 */     this.text = text;
/*     */   }
/*     */ 
/*     */   public String getGroup()
/*     */   {
/* 136 */     return this.group;
/*     */   }
/*     */ 
/*     */   public void setGroup(String group)
/*     */   {
/* 143 */     this.group = group;
/*     */   }
/*     */ 
/*     */   public int getNum()
/*     */   {
/* 150 */     return this.num;
/*     */   }
/*     */ 
/*     */   public void setNum(int num)
/*     */   {
/* 157 */     this.num = num;
/*     */   }
/*     */ 
/*     */   public String getParentId()
/*     */   {
/* 164 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(String parentId)
/*     */   {
/* 171 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public int getLevel()
/*     */   {
/* 178 */     return this.level;
/*     */   }
/*     */ 
/*     */   public void setLevel(int level)
/*     */   {
/* 185 */     this.level = level;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.dataobj.tree.AbstractTreeNode
 * JD-Core Version:    0.5.4
 */