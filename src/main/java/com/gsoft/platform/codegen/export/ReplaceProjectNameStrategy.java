/*    */ package com.gsoft.platform.codegen.export;
/*    */ 
/*    */ import com.gsoft.framework.util.StringUtils;
/*    */ 
/*    */ public class ReplaceProjectNameStrategy
/*    */   implements ContentStrategy
/*    */ {
/*    */   private byte[] bytes;
/*    */ 
/*    */   public ReplaceProjectNameStrategy(String content, String groupId, String projectName)
/*    */   {
/* 13 */     String newContent = "";
/* 14 */     if (StringUtils.isNotEmpty(content)) {
/* 15 */       newContent = content.replace("%%groupId%%", groupId);
/* 16 */       newContent = newContent.replace("%%projectName%%", projectName);
/*    */     }
/*    */ 
/* 19 */     this.bytes = newContent.getBytes();
/*    */   }
/*    */ 
/*    */   public byte[] getBytes()
/*    */   {
/* 24 */     return this.bytes;
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.export.ReplaceProjectNameStrategy
 * JD-Core Version:    0.5.4
 */