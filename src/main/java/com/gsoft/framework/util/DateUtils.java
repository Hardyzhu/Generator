/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DateUtils
/*    */ {
/*    */   public static String getToday()
/*    */   {
/* 13 */     String time = "";
/* 14 */     time = getToday("yyyy-MM-dd");
/* 15 */     return time;
/*    */   }
/*    */ 
/*    */   public static String getToday(String format)
/*    */   {
/* 23 */     return getDateStr(Calendar.getInstance().getTime(), format);
/*    */   }
/*    */ 
/*    */   public static String getDateStr(Date date, String format)
/*    */   {
/* 33 */     SimpleDateFormat df = new SimpleDateFormat(format);
/* 34 */     return df.format(date);
/*    */   }
/*    */ 
/*    */   public static Date parseMills(long millis)
/*    */   {
/* 42 */     Calendar calendar = Calendar.getInstance();
/* 43 */     calendar.setTimeInMillis(millis);
/* 44 */     return calendar.getTime();
/*    */   }
/*    */ 
/*    */   public static String getYearCode()
/*    */   {
/* 52 */     Calendar cal = Calendar.getInstance();
/* 53 */     int year = cal.get(1);
/* 54 */     String yearStr = new Integer(year).toString();
/* 55 */     return yearStr.substring(2, 4);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 59 */     String dateStr = getDateStr(parseMills(1350994695000L), "yyyy-MM-dd HH:mm:ss");
/* 60 */     System.out.println(dateStr);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.DateUtils
 * JD-Core Version:    0.5.4
 */