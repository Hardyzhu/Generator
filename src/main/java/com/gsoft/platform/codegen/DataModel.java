package com.gsoft.platform.codegen;

import java.util.Map;

public abstract interface DataModel
{
  public abstract Map<String, Object> getData();

  public abstract String getFullPath();

  public abstract String getOutPath();

  public abstract void setOutPath(String paramString);

  public abstract String getClassName();

  public abstract void setClassName(String paramString);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.DataModel
 * JD-Core Version:    0.5.4
 */