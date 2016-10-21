/*     */ package com.gsoft.framework.core.orm.hibernate;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.MappingException;
/*     */ import org.hibernate.dialect.Dialect;
/*     */ import org.hibernate.engine.SessionImplementor;
/*     */ import org.hibernate.id.enhanced.TableGenerator;
/*     */ import org.hibernate.type.IntegerType;
/*     */ import org.hibernate.type.Type;
/*     */ import org.hibernate.util.PropertiesHelper;
/*     */ 
/*     */ public class TableSequenceGenerator extends TableGenerator
/*     */ {
/*     */   public static final String YOUI_DEF_TABLE = "YOUI_TABLE_SEQ";
/*     */   public static final String YOUI_DEF_SEGMENT_COLUMN_PARAM = "SEQ_ID";
/*     */   public static final String YOUI_DEF_VALUE_COLUMN_PARAM = "SEQ_NO";
/*     */   public static final String VALUE_MAX_LENGTTH = "MAX_LENGTH";
/*     */   public static final String VALUE_MIN_LENGTTH = "MIN_LENGTH";
/*     */   public static final String VALUE_PREFIX = "PERFIX";
/*     */   private int minLength;
/*     */   private int maxLength;
/*     */   private String prefix;
/*     */ 
/*     */   public void configure(Type type, Properties params, Dialect dialect)
/*     */     throws MappingException
/*     */   {
/*  46 */     params.put("table_name", PropertiesHelper.getString("table_name", params, "YOUI_TABLE_SEQ"));
/*  47 */     params.put("segment_column_name", PropertiesHelper.getString("segment_column_name", params, "SEQ_ID"));
/*  48 */     params.put("value_column_name", PropertiesHelper.getString("value_column_name", params, "SEQ_NO"));
/*  49 */     params.put("increment_size", Integer.valueOf(PropertiesHelper.getInt("increment_size", params, 1000)));
/*     */ 
/*  51 */     this.minLength = PropertiesHelper.getInt("MIN_LENGTH", params, 0);
/*  52 */     this.maxLength = PropertiesHelper.getInt("MAX_LENGTH", params, 2147483647);
/*  53 */     this.prefix = PropertiesHelper.getString("PERFIX", params, "");
/*     */ 
/*  55 */     super.configure(new IntegerType(), params, dialect);
/*     */   }
/*     */ 
/*     */   public synchronized Serializable generate(SessionImplementor session, Object obj)
/*     */   {
/*     */     Serializable result;
/*     */     try
/*     */     {
/*  64 */       result = super.generate(session, obj);
/*     */     } catch (Exception e) {
/*  66 */       throw new RuntimeException(e);
/*     */     }
/*     */ 
/*  69 */     if ((result != null) && (isNeedLeftPad(result.toString()))) {
/*  70 */       result = StringUtils.leftPad(result.toString(), this.minLength - this.prefix.length(), "0");
/*     */     }
/*     */ 
/*  73 */     return result;
/*     */   }
/*     */ 
/*     */   private boolean isNeedLeftPad(String str) {
/*  77 */     return (this.prefix + str).length() < this.minLength;
/*     */   }
/*     */ 
/*     */   public int getMinLength() {
/*  81 */     return this.minLength;
/*     */   }
/*     */ 
/*     */   public void setMinLength(int minLength) {
/*  85 */     this.minLength = minLength;
/*     */   }
/*     */ 
/*     */   public int getMaxLength() {
/*  89 */     return this.maxLength;
/*     */   }
/*     */ 
/*     */   public void setMaxLength(int maxLength) {
/*  93 */     this.maxLength = maxLength;
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  97 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) {
/* 101 */     this.prefix = prefix;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.orm.hibernate.TableSequenceGenerator
 * JD-Core Version:    0.5.4
 */