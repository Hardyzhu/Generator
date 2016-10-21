package com.gsoft.framework.core.exception;

import com.gsoft.framework.core.web.view.Message;
import java.util.Locale;
import org.springframework.context.MessageSource;

public abstract interface ExceptionMessage
{
  public abstract Message getExceptionMessage();

  public abstract Message getExceptionMessage(MessageSource paramMessageSource, Locale paramLocale);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.exception.ExceptionMessage
 * JD-Core Version:    0.5.4
 */