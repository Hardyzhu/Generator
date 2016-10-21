/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import org.springframework.util.Assert;
/*     */ import org.springframework.util.ReflectionUtils;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public final class FieldUtils
/*     */ {
/*     */   public static Field getField(Class<?> clazz, String fieldName)
/*     */     throws IllegalStateException
/*     */   {
/*  52 */     Assert.notNull(clazz, "Class required");
/*  53 */     Assert.hasText(fieldName, "Field name required");
/*     */     try
/*     */     {
/*  56 */       return clazz.getDeclaredField(fieldName);
/*     */     }
/*     */     catch (NoSuchFieldException nsf) {
/*  59 */       if (clazz.getSuperclass() != null) {
/*  60 */         return getField(clazz.getSuperclass(), fieldName);
/*     */       }
/*     */ 
/*  63 */       throw new IllegalStateException("Could not locate field '" + fieldName + "' on class " + clazz);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getFieldValue(Object bean, String fieldName)
/*     */     throws IllegalAccessException
/*     */   {
/*  74 */     Assert.notNull(bean, "Bean cannot be null");
/*  75 */     Assert.hasText(fieldName, "Field name required");
/*  76 */     String[] nestedFields = StringUtils.tokenizeToStringArray(fieldName, ".");
/*  77 */     Class componentClass = bean.getClass();
/*  78 */     Object value = bean;
/*     */ 
/*  80 */     for (String nestedField : nestedFields) {
/*  81 */       Field field = getField(componentClass, nestedField);
/*  82 */       field.setAccessible(true);
/*  83 */       value = field.get(value);
/*  84 */       if (value != null) {
/*  85 */         componentClass = value.getClass();
/*     */       }
/*     */     }
/*     */ 
/*  89 */     return value;
/*     */   }
/*     */ 
/*     */   public static Object getProtectedFieldValue(String protectedField, Object object)
/*     */   {
/*  94 */     Field field = getField(object.getClass(), protectedField);
/*     */     try
/*     */     {
/*  97 */       field.setAccessible(true);
/*     */ 
/*  99 */       return field.get(object);
/*     */     } catch (Exception ex) {
/* 101 */       ReflectionUtils.handleReflectionException(ex);
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setProtectedFieldValue(String protectedField, Object object, Object newValue)
/*     */   {
/* 108 */     Field field = getField(object.getClass(), protectedField);
/*     */     try
/*     */     {
/* 111 */       field.setAccessible(true);
/* 112 */       field.set(object, newValue);
/*     */     } catch (Exception ex) {
/* 114 */       ReflectionUtils.handleReflectionException(ex);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.FieldUtils
 * JD-Core Version:    0.5.4
 */