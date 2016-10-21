/*     */ package com.gsoft.framework.core.json;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonGenerator;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.databind.BeanProperty;
/*     */ import com.fasterxml.jackson.databind.JavaType;
/*     */ import com.fasterxml.jackson.databind.JsonMappingException;
/*     */ import com.fasterxml.jackson.databind.JsonSerializer;
/*     */ import com.fasterxml.jackson.databind.SerializerProvider;
/*     */ import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
/*     */ import com.fasterxml.jackson.databind.ser.ContextualSerializer;

/*     */ import java.io.IOException;

/*     */ import org.hibernate.collection.PersistentCollection;
/*     */ 
/*     */ public class PersistentCollectionSerializer extends JsonSerializer<PersistentCollection>
/*     */   implements ContextualSerializer
/*     */ {
/*     */   protected final boolean _forceLazyLoading;
/*     */   protected final JavaType _serializationType;
/*     */   protected final JsonSerializer<Object> _serializer;
/*     */ 
/*     */   public PersistentCollectionSerializer(JavaType type, boolean forceLazyLoading)
/*     */   {
/*  42 */     this(type, forceLazyLoading, null);
/*     */   }
/*     */ 
/*     */   public PersistentCollectionSerializer(JavaType type, boolean forceLazyLoading, JsonSerializer<?> serializer)
/*     */   {
/*  49 */     this._serializationType = type;
/*  50 */     this._forceLazyLoading = forceLazyLoading;
/*  51 */     this._serializer = (JsonSerializer<Object>) serializer;
/*     */   }
/*     */ 
/*     */   public JsonSerializer<PersistentCollection> createContextual(SerializerProvider provider, BeanProperty property)
/*     */     throws JsonMappingException
/*     */   {
/*  69 */     JsonSerializer ser = provider.findValueSerializer(this._serializationType, property);
/*  70 */     JavaType type = null;
/*  71 */     if (property != null) {
/*  72 */       type = property.getType();
/*     */     }
/*  74 */     return new PersistentCollectionSerializer(type, this._forceLazyLoading, ser);
/*     */   }
/*     */ 
/*     */   public void serialize(PersistentCollection coll, JsonGenerator jgen, SerializerProvider provider)
/*     */     throws IOException, JsonProcessingException
/*     */   {
/*  88 */     if ((!this._forceLazyLoading) && (!coll.wasInitialized())) {
/*  89 */       provider.defaultSerializeNull(jgen);
/*  90 */       return;
/*     */     }
/*  92 */     Object value = coll.getValue();
/*  93 */     if (value == null) {
/*  94 */       provider.defaultSerializeNull(jgen);
/*     */     } else {
/*  96 */       if (this._serializer == null) {
/*  97 */         throw new JsonMappingException("PersistentCollection does not have serializer set");
/*     */       }
/*  99 */       this._serializer.serialize(value, jgen, provider);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void serializeWithType(PersistentCollection coll, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer)
/*     */     throws IOException, JsonProcessingException
/*     */   {
/* 107 */     if ((!this._forceLazyLoading) && (!coll.wasInitialized())) {
/* 108 */       provider.defaultSerializeNull(jgen);
/* 109 */       return;
/*     */     }
/* 111 */     Object value = coll.getValue();
/* 112 */     if (value == null) {
/* 113 */       provider.defaultSerializeNull(jgen);
/*     */     } else {
/* 115 */       if (this._serializer == null) {
/* 116 */         throw new JsonMappingException("PersistentCollection does not have serializer set");
/*     */       }
/* 118 */       this._serializer.serializeWithType(value, jgen, provider, typeSer);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.json.PersistentCollectionSerializer
 * JD-Core Version:    0.5.4
 */