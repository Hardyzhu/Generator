package com.gsoft.framework.core.dataobj.tree;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeAttribute{
  public static final String TREE_ATTR_ID = "id";
  public static final String TREE_ATTR_CODE = "code";
  public static final String TREE_ATTR_PARENT = "parent";
  public static final String TREE_ATTR_PID = "parentId";
  public static final String TREE_ATTR_TEXT = "text";
  public static final String TREE_ATTR_GOURP = "group";
  public static final String TREE_ATTR_SRC = "src";
  public abstract String value();
}