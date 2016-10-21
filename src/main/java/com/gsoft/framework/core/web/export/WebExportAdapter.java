package com.gsoft.framework.core.web.export;

import java.io.OutputStream;

public abstract interface WebExportAdapter<T extends WebExporter>
{
  public abstract boolean supports(String paramString);

  public abstract T openExporter(OutputStream paramOutputStream);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.WebExportAdapter
 * JD-Core Version:    0.5.4
 */