package com.gsoft.framework.core.dataobj.tree;

import com.gsoft.framework.core.dataobj.Domain;
import java.util.List;

public abstract interface TreeNode extends Domain{
  public static final String SOURCE_DATA_STYLE = "source-data";

  public abstract TreeNode getParent();

  public abstract String getParentId();

  public abstract List<TreeNode> getChildren();

  public abstract void addChild(TreeNode paramTreeNode);

  public abstract void removeChild(TreeNode paramTreeNode);

  public abstract String getId();

  public abstract String getCode();

  public abstract String getText();

  public abstract void setText(String paramString);

  public abstract void setGroup(String paramString);

  public abstract void setId(String paramString);

  public abstract void setCheck(String paramString);

  public abstract void setSrc(String paramString);

  public abstract Domain getDomain();

  public abstract void setDomain(Domain paramDomain);

  public abstract void setExpanded(boolean paramBoolean);

  public abstract boolean getExpanded();

  public abstract void setLevel(int paramInt);

  public abstract int getLevel();

  public abstract String getHref();

  public abstract void setHref(String paramString);
}