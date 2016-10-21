/*     */ package com.gsoft.framework.core.json;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonGenerator;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.databind.BeanProperty;
/*     */ import com.fasterxml.jackson.databind.JsonSerializer;
/*     */ import com.fasterxml.jackson.databind.SerializerProvider;
/*     */ import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
/*     */ import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
/*     */ import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
/*     */ import java.io.IOException;
/*     */ import org.hibernate.proxy.HibernateProxy;
/*     */ import org.hibernate.proxy.LazyInitializer;
/*     */ 
/*     */ public class HibernateProxySerializer extends JsonSerializer<HibernateProxy>
/*     */ {
/*     */   protected final BeanProperty _property;
/*     */   protected final boolean _forceLazyLoading;
/*     */   protected PropertySerializerMap _dynamicSerializers;
/*     */ 
/*     */   public HibernateProxySerializer(boolean forceLazyLoading)
/*     */   {
/*  47 */     this._forceLazyLoading = forceLazyLoading;
/*  48 */     this._dynamicSerializers = PropertySerializerMap.emptyMap();
/*  49 */     this._property = null;
/*     */   }
/*     */ 
/*     */   public void serialize(HibernateProxy value, JsonGenerator jgen, SerializerProvider provider)
/*     */     throws IOException, JsonProcessingException
/*     */   {
/*  62 */     Object proxiedValue = findProxied(value);
/*     */ 
/*  64 */     if (proxiedValue == null) {
/*  65 */       provider.defaultSerializeNull(jgen);
/*  66 */       return;
/*     */     }
/*  68 */     findSerializer(provider, proxiedValue).serialize(proxiedValue, jgen, provider);
/*     */   }
/*     */ 
/*     */   public void serializeWithType(HibernateProxy value, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer)
/*     */     throws IOException, JsonProcessingException
/*     */   {
/*  75 */     Object proxiedValue = findProxied(value);
/*  76 */     if (proxiedValue == null) {
/*  77 */       provider.defaultSerializeNull(jgen);
/*  78 */       return;
/*     */     }
/*     */ 
/*  85 */     findSerializer(provider, proxiedValue).serializeWithType(proxiedValue, jgen, provider, typeSer);
/*     */   }
/*     */ 
/*     */   protected JsonSerializer<Object> findSerializer(SerializerProvider provider, Object value)
/*     */     throws IOException, JsonProcessingException
/*     */   {
/* 100 */     Class type = value.getClass();
/*     */ 
/* 105 */     PropertySerializerMap.SerializerAndMapResult result = this._dynamicSerializers.findAndAddSerializer(type, provider, this._property);
/*     */ 
/* 107 */     if (this._dynamicSerializers != result.map) {
/* 108 */       this._dynamicSerializers = result.map;
/*     */     }
/* 110 */     return result.serializer;
/*     */   }
/*     */ 
/*     */   protected Object findProxied(HibernateProxy proxy)
/*     */   {
/* 120 */     LazyInitializer init = proxy.getHibernateLazyInitializer();
/* 121 */     if ((!this._forceLazyLoading) && (init.isUninitialized())) {
/* 122 */       return null;
/*     */     }
/* 124 */     return init.getImplementation();
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.json.HibernateProxySerializer
 * JD-Core Version:    0.5.4
 */