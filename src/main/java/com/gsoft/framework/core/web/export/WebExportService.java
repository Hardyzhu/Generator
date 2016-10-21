package com.gsoft.framework.core.web.export;

import com.gsoft.framework.core.orm.PagerRecords;
import java.io.OutputStream;
import org.springframework.http.HttpHeaders;

public abstract interface WebExportService
{
  public abstract HttpHeaders writePagerRecords(OutputStream paramOutputStream, String paramString, PagerRecords paramPagerRecords);
}

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.WebExportService
 * JD-Core Version:    0.5.4
 */