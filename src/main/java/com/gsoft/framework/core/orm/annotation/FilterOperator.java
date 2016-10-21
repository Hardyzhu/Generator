package com.gsoft.framework.core.orm.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterOperator
{
  public abstract String operator();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.orm.annotation.FilterOperator
 * JD-Core Version:    0.5.4
 */