/*    */ package com.gsoft.framework.core.web.export;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.springframework.http.HttpHeaders;
/*    */ import org.springframework.http.MediaType;
/*    */ 
/*    */ public class TxtWebExporter
/*    */   implements WebExporter
/*    */ {
/* 18 */   private static final String LINE_SPLIT = System.getProperty("line.separator");
/*    */   private OutputStream outputStream;
/*    */ 
/*    */   public TxtWebExporter(OutputStream outputStream)
/*    */   {
/* 23 */     this.outputStream = outputStream;
/*    */   }
/*    */ 
/*    */   public void close()
/*    */   {
/*    */     try {
/* 29 */       if (this.outputStream != null)
/* 30 */         this.outputStream.close();
/*    */     }
/*    */     catch (IOException e) {
/* 33 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void writeLine(int rowIndex, Object[] rowData)
/*    */   {
/* 39 */     StringBuilder builder = new StringBuilder();
/*    */ 
/* 41 */     for (Object data : rowData) {
/* 42 */       builder.append(data).append(",");
/*    */     }
/* 44 */     builder.deleteCharAt(builder.length() - 1);
/* 45 */     builder.append(LINE_SPLIT);
/*    */     try
/*    */     {
/* 48 */       this.outputStream.write(builder.toString().getBytes());
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/*    */     }
/*    */   }
/*    */ 
/*    */   public HttpHeaders getHttpHeaders() {
/* 56 */     HttpHeaders headers = new HttpHeaders();
/* 57 */     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
/* 58 */     headers.add("Content-disposition", "attachment; filename=\"grid.csv\"");
/* 59 */     return headers;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.TxtWebExporter
 * JD-Core Version:    0.5.4
 */