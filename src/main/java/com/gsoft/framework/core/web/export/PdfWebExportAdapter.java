/*    */ package com.gsoft.framework.core.web.export;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class PdfWebExportAdapter
/*    */ {
///*    */   public boolean supports(String type)
///*    */   {
///* 12 */     return "pdf".equals(type);
///*    */   }
///*    */ 
///*    */   public PdfWebExporter openExporter(OutputStream outputStream)
///*    */   {
///* 17 */     return new PdfWebExporter(outputStream);
///*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.PdfWebExportAdapter
 * JD-Core Version:    0.5.4
 */