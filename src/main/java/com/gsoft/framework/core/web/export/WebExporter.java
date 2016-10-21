package com.gsoft.framework.core.web.export;

import org.springframework.http.HttpHeaders;

public abstract interface WebExporter
{
  public abstract void close();

  public abstract void writeLine(int paramInt, Object[] paramArrayOfObject);

  public abstract HttpHeaders getHttpHeaders();
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.WebExporter
 * JD-Core Version:    0.5.4
 */