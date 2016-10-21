package com.gsoft.framework.core.web.menu;

import javax.servlet.jsp.PageContext;
import org.springframework.core.io.ResourceLoader;

public abstract interface MenuProvider
{
  public static final String MENU_TYPE_V = "v";
  public static final String MENU_TYPE_H = "h";

  public abstract void loadSystemMenu(ResourceLoader paramResourceLoader);

  public abstract String buildMenuHtml(PageContext paramPageContext, String paramString);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.menu.MenuProvider
 * JD-Core Version:    0.5.4
 */