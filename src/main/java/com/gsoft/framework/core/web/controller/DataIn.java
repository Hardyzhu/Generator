package com.gsoft.framework.core.web.controller;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.core.orm.Condition;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import org.springframework.validation.BindingResult;

public abstract interface DataIn<T extends Domain>
{
  public abstract Pager getPager();

  public abstract Collection<Condition> getConditions(Domain paramDomain, BindingResult paramBindingResult);

  public abstract T getDomain(T paramT, BindingResult paramBindingResult);

  public abstract Collection<Order> getOrders();

  public abstract byte[] getByteProperty(String paramString);

  public abstract OutputStream getOutputStreamProperty(String paramString);

  public abstract String[] getPropertyValues(String paramString);

  public abstract InputStream getInputStreamProperty(String paramString);

  public abstract InputStreamReader getInputStreamReaderProperty(String paramString);

  public abstract String getPropertyValue(String paramString);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.DataIn
 * JD-Core Version:    0.5.4
 */