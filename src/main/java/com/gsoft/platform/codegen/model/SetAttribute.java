package com.gsoft.platform.codegen.model;

import org.dom4j.Element;

public abstract class SetAttribute extends AbstractAttribute
{
  private static final long serialVersionUID = -4133941841220294260L;

  public abstract String getTargetEntity();

  public abstract void parseFromSetElement(Element paramElement);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.model.SetAttribute
 * JD-Core Version:    0.5.4
 */