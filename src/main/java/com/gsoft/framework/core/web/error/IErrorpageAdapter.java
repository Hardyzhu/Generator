package com.gsoft.framework.core.web.error;

public abstract interface IErrorpageAdapter
{
  public abstract boolean supports(String paramString);

  public abstract String buildErrorInfo(Throwable paramThrowable);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.error.IErrorpageAdapter
 * JD-Core Version:    0.5.4
 */