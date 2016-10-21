/*    */ package com.gsoft.framework.core.web.data;
/*    */ 
///*    */ import com.gsoft.framework.core.Constants;
/*    */ import com.gsoft.framework.core.web.controller.BaseDataController;
/*    */ import com.gsoft.framework.core.web.view.DataModelAndView;
/*    */ import com.gsoft.framework.core.web.view.Message;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/common"})
/*    */ public class CommonData extends BaseDataController
/*    */ {
/*    */   @RequestMapping({"/changeDecorator.json"})
/*    */   public DataModelAndView changeDecorator(HttpServletRequest request, HttpServletResponse response, @RequestParam("decorator") String decorator)
/*    */   {
///* 49 */     request.getSession().getServletContext().setAttribute(Constants.SESSION_DECORATOR, decorator);
///* 50 */     request.getSession().getServletContext().setAttribute(Constants.SESSION_THEME, decorator);
/*    */ 
/* 52 */     return new DataModelAndView(new Message("000000", ""));
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/accessDenied.json"})
/*    */   public DataModelAndView accessDenied(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 62 */     return new DataModelAndView(new Message("111111", "您的会话已经过期!"));
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/loginSuccess.json"})
/*    */   public DataModelAndView loginSuccess(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 72 */     String principal = request.getParameter("principal");
/* 73 */     return new DataModelAndView(new Message("000000", "用户【" + principal + "】登录成功!"));
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/loginFailed.json"})
/*    */   public DataModelAndView loginFailed(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 83 */     String error = request.getParameter("error");
/*    */ 
/* 96 */     return new DataModelAndView(new Message("999999", error));
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.data.CommonData
 * JD-Core Version:    0.5.4
 */