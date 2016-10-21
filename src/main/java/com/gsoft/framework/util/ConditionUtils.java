/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import com.gsoft.framework.core.dataobj.Domain;
/*     */ import com.gsoft.framework.core.orm.Condition;
/*     */ import com.gsoft.framework.core.orm.ConditionFactory;
/*     */ import com.gsoft.framework.core.orm.OrCondition;
/*     */ import com.gsoft.framework.core.orm.Order;
/*     */ import com.gsoft.framework.core.orm.hibernate.HibernateCondition;
/*     */ import com.gsoft.framework.core.orm.hibernate.HibernateOrCondition;
/*     */ import com.gsoft.framework.core.orm.hibernate.HibernateOrder;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Transient;
/*     */ import org.springframework.beans.BeanUtils;
/*     */ import org.springframework.validation.BindingResult;
/*     */ 
/*     */ public class ConditionUtils
/*     */ {
/*     */   public static OrCondition getOrCondition(Condition cond1, Condition cond2)
/*     */   {
/*  54 */     return new HibernateOrCondition(cond1, cond2);
/*     */   }
/*     */ 
/*     */   public static Collection<Condition> getConditions(String prefix, Domain bean, BindingResult result, Map<String, String> filterMap, Map<String, String[]> parameterMap)
/*     */   {
/*  71 */     Collection conditions = new ArrayList();
/*  72 */     if (prefix != null)
/*  73 */       prefix = prefix + ".";
/*     */     else {
/*  75 */       prefix = "";
/*     */     }
/*  77 */     PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
/*     */ 
/*  79 */     for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
/*  80 */       String name = propertyDescriptor.getName();
/*  81 */       if (!"class".equals(name)) {
/*  82 */         String propertyName = prefix + name;
/*     */ 
/*  84 */         Transient tsi = (Transient)propertyDescriptor.getReadMethod().getAnnotation(Transient.class);
/*  85 */         if (tsi != null) {
/*     */           continue;
/*     */         }
/*     */ 
/*  89 */         Object value = result.getFieldValue(propertyName);
/*  90 */         String operator = (String)filterMap.get(propertyName);
/*  91 */         if (operator == null) operator = "EQUALS";
/*  92 */         if ((value != null) && (!value.equals("")))
/*  93 */           if (value instanceof Domain) {
/*  94 */             conditions.addAll(getConditions(propertyName, (Domain)value, result, filterMap, parameterMap)); } else {
/*  95 */             if (value instanceof Set)
/*     */               continue;
/*  97 */             if (value instanceof Collection)
/*     */               continue;
/*  99 */             if (value instanceof Map)
/*     */             {
/* 101 */               for (Map.Entry entry : parameterMap.entrySet())
/* 102 */                 if ((((String)entry.getKey()).startsWith(prefix + propertyName + ".")) && (((String[])entry.getValue()).length > 0))
/*     */                 {
/* 104 */                   conditions.add(ConditionFactory.getInstance().getCondition((String)entry.getKey(), operator, ((String[])entry.getValue())[0]));
/*     */                 }
/*     */             }
/*     */             else
/*     */             {
/* 109 */               if (!parameterMap.containsKey(propertyName)) {
/*     */                 continue;
/*     */               }
/* 112 */               conditions.add(ConditionFactory.getInstance().getCondition(propertyName, operator, value));
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/* 117 */     return conditions;
/*     */   }
/*     */ 
/*     */   public static Condition getCondition(String propertyName, String operator, Object value)
/*     */   {
/* 128 */     return new HibernateCondition(propertyName, operator, value);
/*     */   }
/*     */ 
/*     */   public static Order getOrder(String propertyName, boolean ascending)
/*     */   {
/* 138 */     return new HibernateOrder(propertyName, ascending);
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.ConditionUtils
 * JD-Core Version:    0.5.4
 */