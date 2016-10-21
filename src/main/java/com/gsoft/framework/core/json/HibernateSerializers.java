/*     */ package com.gsoft.framework.core.json;
/*     */ 
/*     */ import com.fasterxml.jackson.databind.BeanDescription;
/*     */ import com.fasterxml.jackson.databind.JavaType;
/*     */ import com.fasterxml.jackson.databind.JsonSerializer;
/*     */ import com.fasterxml.jackson.databind.SerializationConfig;
/*     */ import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
/*     */ import com.fasterxml.jackson.databind.ser.Serializers.Base;
/*     */ import com.fasterxml.jackson.databind.type.CollectionType;
/*     */ import com.fasterxml.jackson.databind.type.MapType;
/*     */ import com.fasterxml.jackson.databind.type.TypeFactory;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.hibernate.collection.PersistentCollection;
/*     */ import org.hibernate.collection.PersistentMap;
/*     */ import org.hibernate.proxy.HibernateProxy;
/*     */ 
/*     */ public class HibernateSerializers 
/*     */ {
/*     */   protected final int _moduleFeatures;
/*     */ 
/*     */   public HibernateSerializers(int features)
/*     */   {
/*  36 */     this._moduleFeatures = features;
/*     */   }
/*     */ 
/*     */   public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc)
/*     */   {
/*  43 */     Class raw = type.getRawClass();
/*     */ 
/*  49 */     if ((Collection.class.isAssignableFrom(raw)) || (Map.class.isAssignableFrom(raw)))
/*     */     {
/*  51 */       return null;
/*     */     }
/*     */ 
/*  57 */     if (PersistentCollection.class.isAssignableFrom(raw))
/*     */     {
/*  59 */       JavaType elementType = _figureFallbackType(config, type);
/*  60 */       return new PersistentCollectionSerializer(elementType, isEnabled(DomainJsonModule.Feature.FORCE_LAZY_LOADING));
/*     */     }
/*     */ 
/*  63 */     if (HibernateProxy.class.isAssignableFrom(raw)) {
/*  64 */       return new HibernateProxySerializer(true);
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */   public JsonSerializer<?> findCollectionSerializer(SerializationConfig config, CollectionType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer)
/*     */   {
/*  74 */     Class raw = type.getRawClass();
/*     */ 
/*  76 */     if (PersistentCollection.class.isAssignableFrom(raw))
/*     */     {
/*  81 */       JavaType elementType = _figureFallbackType(config, type);
/*  82 */       return new PersistentCollectionSerializer(elementType, isEnabled(DomainJsonModule.Feature.FORCE_LAZY_LOADING));
/*     */     }
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */   public JsonSerializer<?> findMapSerializer(SerializationConfig config, MapType type, BeanDescription beanDesc, JsonSerializer<Object> keySerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer)
/*     */   {
/*  93 */     Class raw = type.getRawClass();
/*     */ 
/*  95 */     if (PersistentMap.class.isAssignableFrom(raw)) {
/*  96 */       return new PersistentCollectionSerializer(_figureFallbackType(config, type), isEnabled(DomainJsonModule.Feature.FORCE_LAZY_LOADING));
/*     */     }
/*     */ 
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   public final boolean isEnabled(DomainJsonModule.Feature f) {
/* 103 */     return (this._moduleFeatures & f.getMask()) != 0;
/*     */   }
/*     */ 
/*     */   protected JavaType _figureFallbackType(SerializationConfig config, JavaType persistentType)
/*     */   {
/* 113 */     Class raw = persistentType.getRawClass();
/* 114 */     TypeFactory tf = config.getTypeFactory();
/* 115 */     int paramCount = persistentType.containedTypeCount();
/* 116 */     if (Map.class.isAssignableFrom(raw)) {
/* 117 */       if (paramCount >= 2) {
/* 118 */         return tf.constructMapType(Map.class, persistentType.containedType(0), persistentType.containedType(1));
/*     */       }
/*     */ 
/* 122 */       return tf.constructMapType(Map.class, Object.class, Object.class);
/*     */     }
/* 124 */     if (List.class.isAssignableFrom(raw)) {
/* 125 */       if (paramCount == 1) {
/* 126 */         return tf.constructCollectionType(List.class, persistentType.containedType(0));
/*     */       }
/* 128 */       return tf.constructCollectionType(List.class, Object.class);
/*     */     }
/* 130 */     if (Set.class.isAssignableFrom(raw)) {
/* 131 */       if (paramCount == 1) {
/* 132 */         return tf.constructCollectionType(Set.class, persistentType.containedType(0));
/*     */       }
/* 134 */       return tf.constructCollectionType(Set.class, Object.class);
/*     */     }
/*     */ 
/* 137 */     if (paramCount == 1) {
/* 138 */       return tf.constructCollectionType(Collection.class, persistentType.containedType(0));
/*     */     }
/* 140 */     return tf.constructCollectionType(Collection.class, Object.class);
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.json.HibernateSerializers
 * JD-Core Version:    0.5.4
 */