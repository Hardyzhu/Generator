package com.gsoft.platform.codegen.export;

import java.util.List;
import org.apache.commons.io.output.ByteArrayOutputStream;

public abstract interface TemplateProcessor
{
  public abstract boolean supports(String paramString);

  public abstract List<TemplateResult> process(String paramString, ByteArrayOutputStream paramByteArrayOutputStream);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.export.TemplateProcessor
 * JD-Core Version:    0.5.4
 */