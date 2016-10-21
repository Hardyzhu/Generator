/*    */ package com.gsoft.framework.core.web.export;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class TxtExportAdapter
/*    */   implements WebExportAdapter<TxtWebExporter>
/*    */ {
/*    */   public boolean supports(String type)
/*    */   {
/* 12 */     return "txt".equals(type);
/*    */   }
/*    */ 
/*    */   public TxtWebExporter openExporter(OutputStream outputStream)
/*    */   {
/* 17 */     return new TxtWebExporter(outputStream);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.export.TxtExportAdapter
 * JD-Core Version:    0.5.4
 */