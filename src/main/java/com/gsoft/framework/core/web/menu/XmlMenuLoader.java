/*     */ package com.gsoft.framework.core.web.menu;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.tree.HtmlTreeNode;
/*     */ import com.gsoft.framework.core.dataobj.tree.TreeNode;
/*     */ import com.gsoft.framework.core.dataobj.tree.TreeNodeConfig;
/*     */ import com.gsoft.framework.core.dataobj.tree.TreeUtils;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.ResourceLoader;
/*     */ 
/*     */ public class XmlMenuLoader
/*     */ {
/*     */   private ResourceLoader resourceLoader;
/*     */ 
/*     */   public XmlMenuLoader(ResourceLoader resourceLoader)
/*     */   {
/*  37 */     this.resourceLoader = resourceLoader;
/*     */   }
/*     */ 
/*     */   public HtmlTreeNode load(String resourcePath)
/*     */   {
/*  42 */     Resource resource = this.resourceLoader.getResource(resourcePath);
/*     */ 
/*  44 */     if (resource != null) {
/*  45 */       Document doc = parseDocument(resource);
/*     */ 
/*  47 */       if (doc != null) {
/*  48 */         return TreeUtils.listToHtmlTree(getXmlMenus(doc), null, resourcePath, new TreeNodeConfig()
/*     */         {
/*     */           public void render(TreeNode treeNode) {
/*  51 */             treeNode.setGroup("menu-item");
/*     */           }
/*     */         });
/*     */       }
/*     */     }
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   public List<IMenu> getMenus(String menuResource)
/*     */   {
/*  65 */     Resource resource = this.resourceLoader.getResource(menuResource);
/*     */ 
/*  67 */     if (resource != null) {
/*  68 */       Document doc = parseDocument(resource);
/*     */ 
/*  70 */       if (doc != null) {
/*  71 */         return getXmlMenus(doc);
/*     */       }
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   private List<IMenu> getXmlMenus(Document doc)
/*     */   {
/*  79 */     List menus = new ArrayList();
/*  80 */     if (doc != null) {
/*  81 */       List<Element> menuElements = doc.selectNodes("//menu");
/*  82 */       for (Element element : menuElements) {
/*  83 */         IMenu menu = new DefaultMenu();
/*  84 */         menu.setMenuId(element.attributeValue("id"));
/*  85 */         menu.setMenuCaption(element.attributeValue("text"));
/*  86 */         menu.setMenuSrc(element.attributeValue("href"));
/*  87 */         menu.setMenuStyle(element.attributeValue("icon"));
/*  88 */         if (element.getParent().getName().equals("menu")) {
/*  89 */           menu.setParentMenuId(element.getParent().attributeValue("id"));
/*     */         }
/*  91 */         menu.setTarget(element.attributeValue("target"));
/*  92 */         menus.add(menu);
/*     */       }
/*     */     }
/*  95 */     return menus;
/*     */   }
/*     */ 
/*     */   private Document parseDocument(Resource resource) {
/*  99 */     Document doc = null;
/* 100 */     SAXReader saxReader = new SAXReader();
/*     */     try
/*     */     {
/* 103 */       doc = saxReader.read(resource.getInputStream());
/*     */     }
/*     */     catch (DocumentException e) {
/* 106 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/* 109 */       e.printStackTrace();
/*     */     }
/* 111 */     return doc;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.menu.XmlMenuLoader
 * JD-Core Version:    0.5.4
 */