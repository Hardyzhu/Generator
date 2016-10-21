/*    */ package com.gsoft.framework.core.web.controller;
/*    */ 
/*    */ import com.gsoft.framework.core.orm.PagerRecords;
/*    */ import com.gsoft.framework.core.web.export.WebExportService;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.HttpHeaders;
/*    */ import org.springframework.http.MediaType;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/webexport"})
/*    */ public class WebExportController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private WebExportService webExportService;
/*    */ 
/*    */   @RequestMapping({"/{type}.html"})
/*    */   public ModelAndView export(HttpServletRequest request, HttpServletResponse response, DataIn<?> dataIn, @PathVariable("type") String type)
/*    */     throws Exception
/*    */   {
/* 39 */     Object pagerRecords = request.getAttribute("pagerRecords");
/*    */ 
/* 41 */     if ((pagerRecords != null) && (pagerRecords instanceof PagerRecords)) {
/* 42 */       HttpHeaders headers = this.webExportService.writePagerRecords(response.getOutputStream(), type, (PagerRecords)pagerRecords);
/*    */ 
/* 45 */       if (headers != null) {
/* 46 */         response.setContentType(headers.getContentType().toString());
///* 47 */         Map headerMap = headers.toSingleValueMap();
///* 48 */         for (Map.Entry entry : headerMap.entrySet()) {
///* 49 */           response.setHeader((String)entry.getKey(), (String)entry.getValue());
///*    */         }
/*    */       }
/*    */     }
/* 53 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.WebExportController
 * JD-Core Version:    0.5.4
 */