/*     */ package com.gsoft.framework.core.dataobj.tree;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.Domain;
/*     */ import com.gsoft.framework.util.ReflectionUtils;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public class TreeUtils
/*     */ {
/*  42 */   private static final Log logger = LogFactory.getLog(TreeUtils.class);
/*     */ 
/*     */   public static Map<String, Object> getValueMapFromDomain(Domain domain)
/*     */   {
/*  49 */     Map values = new HashMap();
/*     */ 
/*  54 */     values.put("group", domain.getClass().getSimpleName().toLowerCase());
/*     */ 
/*  56 */     List<Method> methods = ReflectionUtils.annotationedGetterMethod(domain.getClass(), TreeAttribute.class);
/*  57 */     for (Method method : methods) {
/*  58 */       TreeAttribute treeAttribute = (TreeAttribute)method.getAnnotation(TreeAttribute.class);
/*  59 */       if (treeAttribute != null) {
/*  60 */         Object value = getTreeAttributeMethodValue(domain, method);
/*  61 */         if (value != null) {
/*  62 */           values.put(treeAttribute.value(), value);
/*     */         }
/*     */       }
/*     */     }
/*  66 */     return values;
/*     */   }
/*     */ 
/*     */   public static HtmlTreeNode listToHtmlTree(List list, String rootId, String rootText, int maxLevel)
/*     */   {
/*  82 */     return listToHtmlTree(list, rootId, rootText, maxLevel, null);
/*     */   }
/*     */ 
/*     */   public static HtmlTreeNode listToHtmlTree(List list, String rootId, String rootText)
/*     */   {
/*  89 */     return listToHtmlTree(list, rootId, rootText, 0, null);
/*     */   }
/*     */ 
/*     */   public static List<TreeNode> listToTreeNodes(List<? extends Domain> list, TreeNodeConfig treeNodeConfig)
/*     */   {
/*  99 */     List treeNodes = new ArrayList();
/* 100 */     for (Domain domain : list) {
/* 101 */       treeNodes.add(new HtmlTreeNode(domain, treeNodeConfig));
/*     */     }
/* 103 */     return treeNodes;
/*     */   }
/*     */ 
/*     */   public static HtmlTreeNode listToHtmlTree(List<? extends Domain> list, String rootId, String rootText, TreeNodeConfig treeNodeConfig)
/*     */   {
/* 110 */     return listToHtmlTree(list, rootId, rootText, 0, treeNodeConfig);
/*     */   }
/*     */ 
/*     */   public static HtmlTreeNode listToHtmlTree(List<? extends Domain> list, String rootId, String rootText, int maxLevel, TreeNodeConfig treeNodeConfig)
/*     */   {
/* 126 */     HtmlTreeNode root = new HtmlTreeNode(rootId, rootText, treeNodeConfig);
/* 127 */     root.setGroup("root");
/*     */ 
/* 129 */     makeHtmlTree(root, listToTreeNodes(list, treeNodeConfig), 0, maxLevel);
/*     */ 
/* 133 */     return root;
/*     */   }
/*     */ 
/*     */   private static void makeHtmlTree(TreeNode root, List<TreeNode> treeNodes, int level, int maxLevel)
/*     */   {
/* 167 */     String parentId = root.getId();
/*     */     List newTreeNodesList;
/* 168 */     if (treeNodes.size() > 0) {
/* 169 */       treeNodes.remove(root);
/* 170 */       newTreeNodesList = new ArrayList(treeNodes);
/* 171 */       if ((maxLevel > 0) && (level >= maxLevel)) return;
/* 172 */       for (TreeNode treeNode : treeNodes)
/* 173 */         if (isCurrentRootChild(parentId, treeNode)) {
/* 174 */           root.addChild(treeNode);
/* 175 */           makeHtmlTree(treeNode, newTreeNodesList, level + 1, maxLevel);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean isCurrentRootChild(String parentId, TreeNode treeNode)
/*     */   {
/* 188 */     String nodeParentId = treeNode.getParentId();
/* 189 */     if ("".equals(parentId)) parentId = null;
/* 190 */     return ((parentId == null) && (nodeParentId == null)) || ((parentId != null) && (parentId.equals(nodeParentId)));
/*     */   }
/*     */ 
/*     */   private static Object getTreeAttributeMethodValue(Domain domain, Method method)
/*     */   {
/* 200 */     String methodName = method.getName();
/* 201 */     if (methodName.startsWith("get"))
/*     */       try {
/* 203 */         return method.invoke(domain, new Object[0]);
/*     */       } catch (IllegalArgumentException e) {
/* 205 */         logger.error("IllegalArgumentException:" + e.getMessage());
/*     */       } catch (IllegalAccessException e) {
/* 207 */         logger.error("IllegalAccessException:" + e.getMessage());
/*     */       } catch (InvocationTargetException e) {
/* 209 */         logger.error("InvocationTargetException:" + e.getMessage());
/*     */       }
/*     */     else {
/* 212 */       logger.warn("请在get方法上使用TreeAttribute注释！");
/*     */     }
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */   public static <T extends Domain> List<TreeNode> buildPathTree(List<String> paths, Map<String, T> domains, TreeNodeConfig nodeConfig)
/*     */   {
/* 225 */     if (paths == null) {
/* 226 */       return null;
/*     */     }
/*     */ 
/* 229 */     if (domains == null) {
/* 230 */       domains = new HashMap();
/*     */     }
/*     */ 
/* 233 */     Collections.sort(paths);
/*     */ 
/* 235 */     HtmlTreeNode rootTreeNode = new HtmlTreeNode("", "");
/*     */ 
/* 237 */     Map allNodes = new HashMap();
/*     */ 
/* 239 */     HtmlTreeNode pageTreeNode = null;
/* 240 */     for (String path : paths) {
/* 241 */       if (path != null);
/* 242 */       String[] splits = path.split("/");
/* 243 */       int level = splits.length - 1;
/* 244 */       if (level == 1) {
/* 245 */         pageTreeNode = new HtmlTreeNode(path, splits[level]);
/* 246 */         rootTreeNode.addChild(pageTreeNode);
/* 247 */         allNodes.put(path, pageTreeNode);
/*     */       } else {
/* 249 */         HtmlTreeNode parentTreeNode = null;
/* 250 */         HtmlTreeNode levelTreeNode = null;
/* 251 */         for (int i = 1; i <= level; ++i) {
/* 252 */           String parentTreeId = StringUtils.arrayToDelimitedString(ArrayUtils.subarray(splits, 0, i), "/");
/* 253 */           String treeId = StringUtils.arrayToDelimitedString(ArrayUtils.subarray(splits, 0, i + 1), "/");
/*     */ 
/* 255 */           if (allNodes.containsKey(parentTreeId))
/* 256 */             parentTreeNode = (HtmlTreeNode)allNodes.get(parentTreeId);
/*     */           else {
/* 258 */             parentTreeNode = rootTreeNode;
/*     */           }
/*     */ 
/* 261 */           if (!allNodes.containsKey(treeId)) {
/* 262 */             levelTreeNode = new HtmlTreeNode(treeId + "/", "");
/* 263 */             if ((i == level) && (domains.containsKey(path))) {
/* 264 */               pageTreeNode = levelTreeNode;
/*     */             } else {
/* 266 */               levelTreeNode.setText(splits[i]);
/* 267 */               levelTreeNode.setGroup("folder");
/*     */             }
/*     */ 
/* 270 */             parentTreeNode.addChild(levelTreeNode);
/* 271 */             allNodes.put(treeId, levelTreeNode);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 276 */       Domain domain = (Domain)domains.get(path);
/*     */ 
/* 278 */       if ((nodeConfig != null) && (pageTreeNode != null) && (domain != null)) {
/* 279 */         pageTreeNode.setDomain(domain);
/* 280 */         nodeConfig.render(pageTreeNode);
/*     */       }
/*     */     }
/*     */ 
/* 284 */     return rootTreeNode.getChildren();
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.dataobj.tree.TreeUtils
 * JD-Core Version:    0.5.4
 */