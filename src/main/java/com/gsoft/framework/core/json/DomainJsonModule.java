/*     */ package com.gsoft.framework.core.json;
/*     */ 
/*     */ import com.fasterxml.jackson.core.Version;
/*     */ import com.fasterxml.jackson.databind.AnnotationIntrospector;
/*     */ import com.fasterxml.jackson.databind.Module;
/*     */ import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.ser.Serializers;
/*     */ import com.fasterxml.jackson.datatype.hibernate3.HibernateAnnotationIntrospector;
/*     */ 
/*     */ public class DomainJsonModule extends Module
/*     */ {
/*  81 */   protected static final int DEFAULT_FEATURES = Feature.collectDefaults();
/*     */ 
/*  87 */   protected int _moduleFeatures = DEFAULT_FEATURES;
/*     */ 
/*     */   public String getModuleName()
/*     */   {
/* 100 */     return "jackson-datatype-hibernate";
/*     */   }
/*     */ 
/*     */   public Version version()
/*     */   {
/* 105 */     return ModuleVersion.instance.version();
/*     */   }
/*     */ 
/*     */   public void setupModule(Module.SetupContext context)
/*     */   {
/* 115 */     AnnotationIntrospector ai = annotationIntrospector();
/* 116 */     if (ai != null) {
/* 117 */       context.appendAnnotationIntrospector(ai);
/*     */     }
/* 119 */     context.addSerializers((Serializers) new HibernateSerializers(this._moduleFeatures));
/*     */   }
/*     */ 
/*     */   protected AnnotationIntrospector annotationIntrospector()
/*     */   {
/* 128 */     HibernateAnnotationIntrospector ai = new HibernateAnnotationIntrospector();
/* 129 */     ai.setUseTransient(isEnabled(Feature.USE_TRANSIENT_ANNOTATION));
/* 130 */     return ai;
/*     */   }
/*     */ 
/*     */   public DomainJsonModule enable(Feature f)
/*     */   {
/* 140 */     this._moduleFeatures |= f.getMask();
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */   public DomainJsonModule disable(Feature f) {
/* 145 */     this._moduleFeatures &= (f.getMask() ^ 0xFFFFFFFF);
/* 146 */     return this;
/*     */   }
/*     */ 
/*     */   public final boolean isEnabled(Feature f) {
/* 150 */     return (this._moduleFeatures & f.getMask()) != 0;
/*     */   }
/*     */ 
/*     */   public DomainJsonModule configure(Feature f, boolean state) {
/* 154 */     if (state)
/* 155 */       enable(f);
/*     */     else {
/* 157 */       disable(f);
/*     */     }
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */   public static enum Feature
/*     */   {
/*  35 */     FORCE_LAZY_LOADING(false), 
/*     */ 
/*  48 */     USE_TRANSIENT_ANNOTATION(true);
/*     */ 
/*     */     final boolean _defaultState;
/*     */     final int _mask;
/*     */ 
/*     */     public static int collectDefaults()
/*     */     {
/*  58 */       int flags = 0;
/*  59 */       for (Feature f : values()) {
/*  60 */         if (f.enabledByDefault()) {
/*  61 */           flags |= f.getMask();
/*     */         }
/*     */       }
/*  64 */       return flags;
/*     */     }
/*     */ 
/*     */     private Feature(boolean defaultState) {
/*  68 */       this._defaultState = defaultState;
/*  69 */       this._mask = (1 << ordinal());
/*     */     }
/*     */ 
/*     */     public boolean enabledByDefault() {
/*  73 */       return this._defaultState;
/*     */     }
/*     */ 
/*     */     public int getMask() {
/*  77 */       return this._mask;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.json.DomainJsonModule
 * JD-Core Version:    0.5.4
 */