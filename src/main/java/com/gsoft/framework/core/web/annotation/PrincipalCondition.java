package com.gsoft.framework.core.web.annotation;

import java.lang.annotation.Annotation;

public @interface PrincipalCondition
{
  public abstract String property();

  public abstract String operator();

  public abstract String principalProperty();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.annotation.PrincipalCondition
 * JD-Core Version:    0.5.4
 */