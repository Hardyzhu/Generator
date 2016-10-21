/*     */ package com.gsoft.framework.core.web.controller;
/*     */ 
/*     */ public class UrlResource
/*     */ {
/*     */   private String httpMethod;
/*     */   private String url;
/*     */   private String type;
/*     */   private String urlId;
/*     */   private String urlText;
/*     */   private String[] subPages;
/*     */   private String[] datas;
/*     */ 
/*     */   public UrlResource(String urlId, String httpMethod, String url)
/*     */   {
/*  45 */     this.urlId = urlId;
/*  46 */     this.httpMethod = httpMethod;
/*  47 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getHttpMethod()
/*     */   {
/*  55 */     return this.httpMethod;
/*     */   }
/*     */ 
/*     */   public void setHttpMethod(String httpMethod)
/*     */   {
/*  63 */     this.httpMethod = httpMethod;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/*  71 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/*  78 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/*  85 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/*  92 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getUrlId()
/*     */   {
/*  99 */     return this.urlId;
/*     */   }
/*     */ 
/*     */   public void setUrlId(String urlId)
/*     */   {
/* 106 */     this.urlId = urlId;
/*     */   }
/*     */ 
/*     */   public String[] getSubPages()
/*     */   {
/* 113 */     return this.subPages;
/*     */   }
/*     */ 
/*     */   public void setSubPages(String[] subPages)
/*     */   {
/* 120 */     this.subPages = subPages;
/*     */   }
/*     */ 
/*     */   public String[] getDatas()
/*     */   {
/* 127 */     return this.datas;
/*     */   }
/*     */ 
/*     */   public void setDatas(String[] datas)
/*     */   {
/* 134 */     this.datas = datas;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 142 */     int prime = 31;
/* 143 */     int result = 1;
/* 144 */     result = 31 * result + ((this.httpMethod == null) ? 0 : this.httpMethod.hashCode());
/* 145 */     result = 31 * result + ((this.url == null) ? 0 : this.url.hashCode());
/* 146 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 154 */     if (this == obj)
/* 155 */       return true;
/* 156 */     if (obj == null)
/* 157 */       return false;
/* 158 */     if (super.getClass() != obj.getClass())
/* 159 */       return false;
/* 160 */     UrlResource other = (UrlResource)obj;
/* 161 */     if (this.httpMethod == null)
/* 162 */       if (other.httpMethod != null)
/* 163 */         return false;
/* 164 */     else if (!this.httpMethod.equals(other.httpMethod))
/* 165 */       return false;
/* 166 */     if (this.url == null)
/* 167 */       if (other.url != null)
/* 168 */         return false;
/* 169 */     else if (!this.url.equals(other.url))
/* 170 */       return false;
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 175 */     StringBuffer buffer = new StringBuffer();
/* 176 */     buffer.append(this.urlText + " ").append(this.urlId + " ").append(this.httpMethod + " ").append(this.url);
/*     */ 
/* 180 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public void setUrlText(String urlText) {
/* 184 */     this.urlText = urlText;
/*     */   }
/*     */ 
/*     */   public String getUrlText()
/*     */   {
/* 191 */     return this.urlText;
/*     */   }
/*     */ 
/*     */   public boolean contains(String httpMethod, String url)
/*     */   {
/* 200 */     if (this.url == null)
/* 201 */       if (url != null)
/* 202 */         return false;
/* 203 */     else if (!this.url.equals(url))
/* 204 */       return false;
/* 205 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.web.controller.UrlResource
 * JD-Core Version:    0.5.4
 */