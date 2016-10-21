package com.gsoft.framework.core.web.menu;

import com.gsoft.framework.core.dataobj.Domain;

public abstract interface IMenu extends Domain
{
  public abstract String getMenuId();

  public abstract void setMenuId(String paramString);

  public abstract String getMenuSrc();

  public abstract void setMenuSrc(String paramString);

  public abstract String getMenuStyle();

  public abstract void setMenuStyle(String paramString);

  public abstract String getMenuCaption();

  public abstract void setMenuCaption(String paramString);

  public abstract String getParentMenuId();

  public abstract void setParentMenuId(String paramString);

  public abstract String getTarget();

  public abstract void setTarget(String paramString);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.menu.IMenu
 * JD-Core Version:    0.5.4
 */