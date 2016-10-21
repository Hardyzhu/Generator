/*    */ package com.gsoft.framework.core.orm;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PagerRecords
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8874497501408426939L;
/*    */   private List records;
/*    */   private int totalCount;
/*    */   private Pager pager;
/*    */ 
/*    */   public PagerRecords(List records, int totalCount)
/*    */   {
/* 27 */     this.records = records;
/* 28 */     this.totalCount = totalCount;
/*    */   }
/*    */ 
/*    */   public List getRecords() {
/* 32 */     return this.records;
/*    */   }
/*    */ 
/*    */   public void setRecords(List records) {
/* 36 */     this.records = records;
/*    */   }
/*    */ 
/*    */   public int getTotalCount() {
/* 40 */     return this.totalCount;
/*    */   }
/*    */ 
/*    */   public void setTotalCount(int totalCount) {
/* 44 */     this.totalCount = totalCount;
/*    */   }
/*    */ 
/*    */   public Pager getPager()
/*    */   {
/* 50 */     return this.pager;
/*    */   }
/*    */ 
/*    */   public void setPager(Pager pager)
/*    */   {
/* 56 */     this.pager = pager;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.orm.PagerRecords
 * JD-Core Version:    0.5.4
 */