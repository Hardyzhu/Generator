/*     */ package com.gsoft.framework.core.web.menu;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.tree.TreeAttribute;
/*     */ 
/*     */ public class DefaultMenu
/*     */   implements IMenu
/*     */ {
/*     */   private static final long serialVersionUID = -6256436543642454970L;
/*     */   private String menuStyle;
/*     */   private String menuId;
/*     */   private String menuSrc;
/*     */   private String target;
/*     */   private String parentMenuId;
/*     */   private String menuCaption;
/*     */ 
/*     */   public String getMenuStyle()
/*     */   {
/*  49 */     return this.menuStyle;
/*     */   }
/*     */ 
/*     */   public void setMenuStyle(String menuStyle)
/*     */   {
/*  56 */     this.menuStyle = menuStyle;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("id")
/*     */   public String getMenuId()
/*     */   {
/*  64 */     return this.menuId;
/*     */   }
/*     */ 
/*     */   public void setMenuId(String menuId)
/*     */   {
/*  71 */     this.menuId = menuId;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("href")
/*     */   public String getMenuSrc()
/*     */   {
/*  79 */     return this.menuSrc;
/*     */   }
/*     */ 
/*     */   public void setMenuSrc(String src)
/*     */   {
/*  86 */     this.menuSrc = src;
/*     */   }
/*     */ 
/*     */   public String getTarget()
/*     */   {
/*  93 */     return this.target;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("target")
/*     */   public void setTarget(String target)
/*     */   {
/* 101 */     this.target = target;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("parentId")
/*     */   public String getParentMenuId()
/*     */   {
/* 109 */     return this.parentMenuId;
/*     */   }
/*     */ 
/*     */   public void setParentMenuId(String parentMenuId)
/*     */   {
/* 116 */     this.parentMenuId = parentMenuId;
/*     */   }
/*     */ 
/*     */   @TreeAttribute("text")
/*     */   public String getMenuCaption()
/*     */   {
/* 124 */     return this.menuCaption;
/*     */   }
/*     */ 
/*     */   public void setMenuCaption(String menuCaption)
/*     */   {
/* 131 */     this.menuCaption = menuCaption;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 135 */     return this.menuCaption + " " + this.menuId;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.menu.DefaultMenu
 * JD-Core Version:    0.5.4
 */