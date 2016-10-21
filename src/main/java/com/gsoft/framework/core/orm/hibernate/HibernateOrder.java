/*    */ package com.gsoft.framework.core.orm.hibernate;
/*    */ 
/*    */ public class HibernateOrder extends org.hibernate.criterion.Order
/*    */   implements com.gsoft.framework.core.orm.Order
/*    */ {
/*    */   private String propertyName;
/*    */   private boolean ascending;
/*    */   private static final long serialVersionUID = 6022774683096569626L;
/*    */ 
/*    */   public HibernateOrder(String propertyName, boolean ascending)
/*    */   {
/* 17 */     super(propertyName, ascending);
/* 18 */     this.propertyName = propertyName;
/* 19 */     this.ascending = ascending;
/*    */   }
/*    */ 
/*    */   public String getProperty() {
/* 23 */     return this.propertyName;
/*    */   }
/*    */ 
/*    */   public boolean isAscending()
/*    */   {
/* 28 */     return this.ascending;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.orm.hibernate.HibernateOrder
 * JD-Core Version:    0.5.4
 */