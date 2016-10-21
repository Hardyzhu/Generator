/*    */ package com.gsoft.framework.util;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonFactory;
/*    */ import com.fasterxml.jackson.core.JsonGenerationException;
/*    */ import com.fasterxml.jackson.core.JsonGenerator;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;
/*    */ import com.gsoft.framework.core.dataobj.Domain;
/*    */ import com.gsoft.framework.core.json.DomainJsonModule;
/*    */ import java.io.IOException;
/*    */ import java.io.StringWriter;
/*    */ 
/*    */ public class PojoMapper
/*    */ {
/* 31 */   private static ObjectMapper objectMapper = new ObjectMapper();
/*    */ 
/* 33 */   private static ObjectMapper domainMapper = new ObjectMapper();
/* 34 */   private static JsonFactory jf = new JsonFactory();
/*    */ 
/*    */   public static String domainToJson(Domain domain)
/*    */   {
/* 52 */     return toJson(domainMapper, domain, false);
/*    */   }
/*    */ 
/*    */   public static String toJson(Object pojo, boolean prettyPrint) {
/* 56 */     return toJson(objectMapper, pojo, false);
/*    */   }
/*    */ 
/*    */   public static String toJson(ObjectMapper objectMapper, Object pojo, boolean prettyPrint) {
/* 60 */     StringWriter sw = new StringWriter();
/*    */     try
/*    */     {
/* 63 */       JsonGenerator jg = jf.createJsonGenerator(sw);
/*    */ 
/* 65 */       if (prettyPrint) {
/* 66 */         jg.useDefaultPrettyPrinter();
/*    */       }
/*    */ 
/* 69 */       objectMapper.writeValue(jg, pojo);
/*    */     } catch (JsonMappingException e) {
/* 71 */       throw new RuntimeException(e);
/*    */     } catch (JsonGenerationException e) {
/* 73 */       throw new RuntimeException(e);
/*    */     } catch (IOException e) {
/* 75 */       throw new RuntimeException(e);
/*    */     }
/* 77 */     return sw.toString();
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 37 */     domainMapper.registerModule(new DomainJsonModule());
/* 38 */     objectMapper.registerModule(new Hibernate3Module());
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.util.PojoMapper
 * JD-Core Version:    0.5.4
 */