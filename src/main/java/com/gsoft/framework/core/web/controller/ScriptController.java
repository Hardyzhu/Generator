/*     */ package com.gsoft.framework.core.web.controller;
/*     */ 
/*     */ import com.gsoft.framework.core.web.PageScriptFactory;
/*     */ import com.gsoft.framework.util.PojoMapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/scripts/page"})
/*     */ public class ScriptController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private PageScriptFactory pageScriptFactory;
/*     */ 
/*     */   @RequestMapping({"/{pageId}.html"})
/*     */   public ModelAndView index(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageId") String pageId)
/*     */   {
/*  57 */     String sessionId = request.getSession().getId();
/*  58 */     ModelAndView modelAndView = new ModelAndView();
/*  59 */     modelAndView.addObject("json", this.pageScriptFactory.getPageScript(sessionId, pageId));
/*  60 */     modelAndView.setViewName("data/json");
/*  61 */     return modelAndView;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/genTestPagerRecords.json"})
/*     */   public ModelAndView genTestPagerRecords(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  76 */     ModelAndView modelAndView = new ModelAndView();
/*     */ 
/*  78 */     String[] props = request.getParameterValues("pager:property");
/*  79 */     String pageSize = request.getParameter("pager:pageSize");
/*     */ 
/*  81 */     Map result = new HashMap();
/*     */ 
/*  83 */     result.put("totalCount", pageSize);
/*  84 */     result.put("records", genPagerRecords(props, pageSize));
/*     */ 
/*  86 */     modelAndView.addObject("json", PojoMapper.toJson(result, true));
/*  87 */     modelAndView.setViewName("data/json");
/*  88 */     return modelAndView;
/*     */   }
/*     */ 
/*     */   private List<Map<String, String>> genPagerRecords(String[] props, String pageSize)
/*     */   {
/*     */     int intPageSize;
/*     */     try
/*     */     {
/* 100 */       intPageSize = Integer.parseInt(pageSize);
/*     */     } catch (NumberFormatException e) {
/* 102 */       intPageSize = 10;
/*     */     }
/*     */ 
/* 105 */     List records = new ArrayList();
/*     */ 
/* 107 */     for (int i = 0; i < intPageSize; ++i) {
/* 108 */       records.add(genRecord(props, i));
/*     */     }
/* 110 */     return records;
/*     */   }
/*     */ 
/*     */   private Map<String, String> genRecord(String[] props, int i)
/*     */   {
/* 120 */     Map record = new HashMap();
/* 121 */     for (String prop : props) {
/* 122 */       record.put(prop, prop + "_" + i);
/*     */     }
/* 124 */     return record;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.ScriptController
 * JD-Core Version:    0.5.4
 */