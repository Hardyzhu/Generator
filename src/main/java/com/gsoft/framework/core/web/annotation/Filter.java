package com.gsoft.framework.core.web.annotation;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public @interface Filter
{
  public abstract String name();

  public abstract String operator();

  public abstract String mapProperty();

  public abstract Class<? extends Serializable> valueClazz();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.annotation.Filter
 * JD-Core Version:    0.5.4
 */