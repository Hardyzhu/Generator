/*    */ package com.gsoft.framework.core.orm.hibernate;
/*    */ 
/*    */ import com.gsoft.framework.core.orm.Condition;
/*    */ import com.gsoft.framework.core.orm.OrCondition;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.util.Assert;
/*    */ 
/*    */ public class HibernateOrCondition
/*    */   implements Condition, OrCondition
/*    */ {
/*    */   private Condition cond1;
/*    */   private Condition cond2;
/*    */ 
/*    */   public HibernateOrCondition(Condition cond1, Condition cond2)
/*    */   {
/* 24 */     Assert.notNull(cond1);
/* 25 */     Assert.notNull(cond2);
/* 26 */     this.cond1 = cond1;
/* 27 */     this.cond2 = cond2;
/*    */   }
/*    */ 
/*    */   public String getProperty()
/*    */   {
/* 32 */     return "";
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 37 */     return "";
/*    */   }
/*    */ 
/*    */   public Object getValue()
/*    */   {
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */   public Criterion generateExpression() {
/* 46 */     return Restrictions.or(convertToCriterion(this.cond1), convertToCriterion(this.cond2));
/*    */   }
/*    */ 
/*    */   private Criterion convertToCriterion(Condition cond) {
/* 50 */     if (cond instanceof HibernateCondition)
/* 51 */       return ((HibernateCondition)cond).generateExpression();
/* 52 */     if (cond instanceof HibernateOrCondition) {
/* 53 */       return ((HibernateOrCondition)cond).generateExpression();
/*    */     }
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   public Condition getCond1() {
/* 59 */     return this.cond1;
/*    */   }
/*    */ 
/*    */   public void setCond1(Condition cond1) {
/* 63 */     this.cond1 = cond1;
/*    */   }
/*    */ 
/*    */   public Condition getCond2() {
/* 67 */     return this.cond2;
/*    */   }
/*    */ 
/*    */   public void setCond2(Condition cond2) {
/* 71 */     this.cond2 = cond2;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.orm.hibernate.HibernateOrCondition
 * JD-Core Version:    0.5.4
 */