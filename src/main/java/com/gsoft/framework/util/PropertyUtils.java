/*     */ package com.gsoft.framework.util;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class PropertyUtils
/*     */ {
/*  27 */   private static final Log logger = LogFactory.getLog(PropertyUtils.class);
/*     */ 
/*  29 */   private static final IntParser INT_PARSER = new IntParser();
/*     */ 
/*  31 */   private static final LongParser LONG_PARSER = new LongParser();
/*     */ 
/*  33 */   private static final FloatParser FLOAT_PARSER = new FloatParser();
/*     */ 
/*  35 */   private static final DoubleParser DOUBLE_PARSER = new DoubleParser();
/*     */ 
/*  37 */   private static final BooleanParser BOOLEAN_PARSER = new BooleanParser();
/*     */ 
/*     */   public static void setSimplePropertyValue(Object bean, String property, Object value)
/*     */   {
/*  46 */     if (bean == null) {
/*  47 */       return;
/*     */     }
/*  49 */     Object convertValue = value;
/*  50 */     Field field = FieldUtils.getField(bean.getClass(), property);
/*  51 */     if (field != null) {
/*  52 */       Class fieldType = field.getType();
/*  53 */       if ((fieldType != String.class) && (((value == null) || (value instanceof String)))) {
/*  54 */         convertValue = getConvertValue(value, fieldType);
/*     */       }
/*     */       try
/*     */       {
/*  58 */         org.apache.commons.beanutils.PropertyUtils.setSimpleProperty(bean, property, convertValue);
/*     */       } catch (IllegalAccessException e) {
/*  60 */         logger.warn(e.getMessage());
/*     */       } catch (InvocationTargetException e) {
/*  62 */         logger.warn(e.getMessage());
/*     */       } catch (NoSuchMethodException e) {
/*  64 */         logger.warn(e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Object getConvertValue(Object value, Class<?> type) {
/*  70 */     String strValue = null;
/*  71 */     if (value != null) {
/*  72 */       strValue = value.toString();
/*     */     }
/*  74 */     if (type == Integer.TYPE)
/*  75 */       return Integer.valueOf((StringUtils.isEmpty(strValue)) ? 0 : INT_PARSER.doParse(strValue).intValue());
/*  76 */     if (type == Long.TYPE)
/*  77 */       return Long.valueOf((StringUtils.isEmpty(strValue)) ? 0L : LONG_PARSER.doParse(strValue).longValue());
/*  78 */     if (type == Float.TYPE)
/*  79 */       return Float.valueOf((StringUtils.isEmpty(strValue)) ? 0.0F : FLOAT_PARSER.doParse(strValue).floatValue());
/*  80 */     if (type == Double.TYPE)
/*  81 */       return Double.valueOf((StringUtils.isEmpty(strValue)) ? 0.0D : DOUBLE_PARSER.doParse(strValue).doubleValue());
/*  82 */     if (type == Boolean.TYPE) {
/*  83 */       return Boolean.valueOf((StringUtils.isEmpty(strValue)) ? false : BOOLEAN_PARSER.doParse(strValue).booleanValue());
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setPropertyValue(Object bean, String property, Object value)
/*     */   {
/*  94 */     if (bean == null) {
/*  95 */       return;
/*     */     }
/*  97 */     String[] properties = property.split("\\.");
/*  98 */     if (properties.length > 1) {
/*  99 */       Object objBean = bean;
/* 100 */       Object valueBean = null;
/* 101 */       for (int i = 0; i < properties.length; ++i)
/* 102 */         if (i < properties.length - 1) {
/* 103 */           valueBean = createObject(objBean, properties[i]);
/* 104 */           if (valueBean == null) break;
/* 105 */           setPropertyValue(objBean, properties[i], valueBean);
/* 106 */           objBean = valueBean;
/*     */         }
/*     */         else {
/* 109 */           setSimplePropertyValue(objBean, properties[i], value);
/*     */         }
/*     */     }
/*     */     else {
/* 113 */       setSimplePropertyValue(bean, property, value);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getSimplePropertyValue(Object bean, String property)
/*     */   {
/* 124 */     if (bean == null) {
/* 125 */       return null;
/*     */     }
/* 127 */     if (Map.class.isAssignableFrom(bean.getClass()))
/* 128 */       return ((Map)bean).get(property);
/*     */     try
/*     */     {
/* 131 */       if (ReflectionUtils.findField(bean.getClass(), property) == null) {
/* 132 */         return null;
/*     */       }
/* 134 */       return org.apache.commons.beanutils.PropertyUtils.getSimpleProperty(bean, property);
/*     */     } catch (IllegalAccessException e) {
/* 136 */       logger.warn("【" + property + "】IllegalAccessException：" + e.getMessage());
/*     */     } catch (IllegalStateException e) {
/* 138 */       logger.warn("【" + property + "】IllegalStateException：" + e.getMessage());
/*     */     } catch (InvocationTargetException e) {
/* 140 */       logger.warn("【" + property + "】InvocationTargetException：" + e.getMessage());
/*     */     } catch (NoSuchMethodException e) {
/* 142 */       logger.debug("【" + property + "】NoSuchMethodException：" + e.getMessage());
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   public static Object getPropertyValue(Object bean, String property)
/*     */   {
/* 154 */     String[] properties = property.split("\\.");
/* 155 */     if (properties.length > 1) {
/* 156 */       Object objBean = bean;
/* 157 */       for (int i = 0; i < properties.length; ++i)
/* 158 */         if (i < properties.length - 1) {
/* 159 */           objBean = getSimplePropertyValue(objBean, properties[i]);
/*     */         }
/*     */         else
/* 162 */           return getSimplePropertyValue(objBean, properties[i]);
/*     */     }
/*     */     else
/*     */     {
/* 166 */       return getSimplePropertyValue(bean, property);
/*     */     }
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */   private static Object createObject(Object objBean, String property)
/*     */   {
/* 178 */     PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(objBean.getClass(), property);
/*     */ 
/* 180 */     if (pd != null) {
/* 181 */       Class valueClass = pd.getPropertyType();
/* 182 */       Object valueBean = null;
/*     */       try {
/* 184 */         valueBean = valueClass.newInstance();
/*     */       } catch (InstantiationException e) {
/* 186 */         logger.error("创建对象异常【InstantiationException】:" + e.getMessage());
/*     */       } catch (IllegalAccessException e) {
/* 188 */         logger.error("创建对象异常【IllegalAccessException】:" + e.getMessage());
/*     */       }
/* 190 */       return valueBean;
/*     */     }
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */   private static class BooleanParser extends PropertyUtils.ParameterParser<Boolean>
/*     */   {
/*     */     private BooleanParser()
/*     */     {
///* 263 */       super(null);
/*     */     }
/*     */ 
/*     */     protected String getType() {
/* 267 */       return "boolean";
/*     */     }
/*     */ 
/*     */     protected Boolean doParse(String parameter) throws NumberFormatException
/*     */     {
/* 272 */       return Boolean.valueOf((parameter.equalsIgnoreCase("true")) || (parameter.equalsIgnoreCase("on")) || (parameter.equalsIgnoreCase("yes")) || (parameter.equals("1")));
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class DoubleParser extends PropertyUtils.ParameterParser<Double>
/*     */   {
/*     */     private DoubleParser()
/*     */     {
///* 249 */       super(null);
/*     */     }
/*     */ 
/*     */     protected String getType() {
/* 253 */       return "double";
/*     */     }
/*     */ 
/*     */     protected Double doParse(String parameter) throws NumberFormatException
/*     */     {
/* 258 */       return Double.valueOf(parameter);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class FloatParser extends PropertyUtils.ParameterParser<Float>
/*     */   {
/*     */     private FloatParser()
/*     */     {
///* 234 */       super(null);
/*     */     }
/*     */ 
/*     */     protected String getType() {
/* 238 */       return "float";
/*     */     }
/*     */ 
/*     */     protected Float doParse(String parameter) throws NumberFormatException
/*     */     {
/* 243 */       return Float.valueOf(parameter);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class LongParser extends PropertyUtils.ParameterParser<Long>
/*     */   {
/*     */     private LongParser()
/*     */     {
///* 219 */       super(null);
/*     */     }
/*     */ 
/*     */     protected String getType() {
/* 223 */       return "long";
/*     */     }
/*     */ 
/*     */     protected Long doParse(String parameter) throws NumberFormatException
/*     */     {
/* 228 */       return Long.valueOf(parameter);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class IntParser extends PropertyUtils.ParameterParser<Integer>
/*     */   {
/*     */     private IntParser()
/*     */     {
///* 205 */       super(null);
/*     */     }
/*     */ 
/*     */     protected String getType() {
/* 209 */       return "int";
/*     */     }
/*     */ 
/*     */     protected Integer doParse(String s) throws NumberFormatException
/*     */     {
/* 214 */       return Integer.valueOf(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static abstract class ParameterParser<T>
/*     */   {
/*     */     protected abstract String getType();
/*     */ 
/*     */     protected abstract T doParse(String paramString)
/*     */       throws NumberFormatException;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.PropertyUtils
 * JD-Core Version:    0.5.4
 */