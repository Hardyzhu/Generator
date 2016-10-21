package com.gsoft.framework.core.web.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataInDesc
{
  public abstract String type();

  public abstract DataInCollection[] lists();

  public abstract Filter[] filters();

  public abstract DataAccess[] dataAccesses();

  public abstract PrincipalCondition[] principalConditions();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.annotation.DataInDesc
 * JD-Core Version:    0.5.4
 */