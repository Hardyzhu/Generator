/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ import com.gsoft.framework.core.dataobj.Domain;
/*    */ 
/*    */ public class TransLogUtils
/*    */ {
/*    */   public static String getDomainSaveLog(Domain domain, String[][] propertyDescs)
/*    */   {
/* 20 */     StringBuffer buffer = new StringBuffer();
/* 21 */     if (domain != null) {
/* 22 */       for (String[] propertyDesc : propertyDescs) {
/* 23 */         if (propertyDesc.length == 2) {
/* 24 */           buffer.append("，");
/* 25 */           buffer.append(propertyDesc[1]);
/* 26 */           buffer.append("【");
/* 27 */           buffer.append(PropertyUtils.getPropertyValue(domain, propertyDesc[0]));
/* 28 */           buffer.append("】");
/*    */         }
/*    */       }
/*    */     }
/* 32 */     return (buffer.length() > 0) ? buffer.substring(1) : buffer.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.TransLogUtils
 * JD-Core Version:    0.5.4
 */