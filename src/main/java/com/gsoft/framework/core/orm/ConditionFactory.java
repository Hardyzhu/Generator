/*    */ package com.gsoft.framework.core.orm;
/*    */ 
/*    */ import com.gsoft.framework.core.orm.hibernate.HibernateCondition;
/*    */ import com.gsoft.framework.core.orm.hibernate.HibernateOrder;
/*    */ 
/*    */ public class ConditionFactory
/*    */ {
/* 14 */   private static ConditionFactory conditionFactory = null;
/*    */ 
/*    */   public static ConditionFactory getInstance()
/*    */   {
/* 21 */     if (conditionFactory == null) {
/* 22 */       conditionFactory = new ConditionFactory();
/*    */     }
/* 24 */     return conditionFactory;
/*    */   }
/*    */ 
/*    */   public Condition getCondition(String property, String operator, Object value) {
/* 28 */     return new HibernateCondition(property, operator, value);
/*    */   }
/*    */ 
/*    */   public Order getOrder(String propertyName, boolean ascending) {
/* 32 */     return new HibernateOrder(propertyName, ascending);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.orm.ConditionFactory
 * JD-Core Version:    0.5.4
 */