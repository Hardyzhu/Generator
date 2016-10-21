package com.gsoft.framework.core.dataobj.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute
{
  public abstract String value();

  public abstract boolean rtexprvalue();

  public abstract boolean notNull();

  public abstract String fieldType();

  public abstract String caption();

  public abstract String defaultvalue();

  public abstract String description();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.dataobj.annotation.Attribute
 * JD-Core Version:    0.5.4
 */