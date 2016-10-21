package com.gsoft.framework.core.web.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PageInfo
{
  public abstract String pageId();

  public abstract String text();

  public abstract String[] datas();

  public abstract String[] subPages();

  public abstract String page();

  public abstract boolean menu();

  public abstract boolean log();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.annotation.PageInfo
 * JD-Core Version:    0.5.4
 */