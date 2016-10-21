/*     */ package com.gsoft.platform.codegen.export;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import org.apache.commons.io.output.ByteArrayOutputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class SourceProjectExporter
/*     */ {
/*  29 */   private static final Log logger = LogFactory.getLog(SourceProjectExporter.class);
/*     */ 
/*     */   public void export(OutputStream out, ExportConfig exportConfig)
/*     */   {
/*  34 */     ZipOutputStream zipOut = null;
/*     */     try {
/*  36 */       logger.info("***********************开始导出项目***********************");
/*     */ 
/*  38 */       InputStream inputStream = 
/*  39 */         SourceProjectExporter.class.getResourceAsStream("project-tmp.zip");
/*     */ 
/*  41 */       ZipInputStream zipInput = new ZipInputStream(inputStream);
/*     */ 
/*  44 */       zipOut = new ZipOutputStream(new BufferedOutputStream(out));
/*     */ 
/*  46 */       ZipEntry nextEntry = zipInput.getNextEntry();
/*     */ 
/*  48 */       while (nextEntry != null)
/*     */       {
/*  50 */         ByteArrayOutputStream tmpOut = null;
/*  51 */         if ((nextEntry != null) && (!nextEntry.isDirectory())) {
/*  52 */           String entryName = nextEntry.getName();
/*  53 */           if ((exportConfig.getTemplateProcessor() != null) && (exportConfig.getTemplateProcessor().supports(entryName))) {
/*  54 */             tmpOut = innerCopyIn2Out(zipInput, null);
/*     */ 
/*  56 */             List<TemplateResult> templateResults = exportConfig.getTemplateProcessor().process(entryName, tmpOut);
/*  57 */             if ((templateResults != null) && (templateResults.size() > 0))
/*  58 */               for (TemplateResult templateResult : templateResults)
/*  59 */                 if (templateResult.getByteOut() != null) {
/*  60 */                   ZipEntry moduleZipEntry = new ZipEntry(templateResult.getEntryName());
/*  61 */                   zipOut.putNextEntry(moduleZipEntry);
/*  62 */                   zipOut.write(templateResult.getByteOut());
/*  63 */                   zipOut.closeEntry();
/*     */                 }
/*     */           }
/*     */           else
/*     */           {
/*  68 */             ZipEntry zipEntry = new ZipEntry(entryName);
/*     */ 
/*  70 */             zipOut.putNextEntry(zipEntry);
/*  71 */             innerCopyIn2Out(zipInput, zipOut);
/*     */ 
/*  73 */             zipOut.closeEntry();
/*     */           }
/*     */         }
/*     */ 
/*  77 */         nextEntry = zipInput.getNextEntry();
/*     */       }
/*  79 */       logger.info("***********************完成导出项目***********************");
/*     */     } catch (Exception e) {
/*  81 */       logger.error(e.getMessage());
/*  82 */       e.printStackTrace();
/*     */     } finally {
/*  84 */       if (zipOut != null) {
/*     */         try {
/*  86 */           zipOut.close();
/*     */         } catch (IOException e) {
/*  88 */           logger.error(e.getMessage());
/*     */         }
/*     */       }
/*     */ 
/*  92 */       if (out != null)
/*     */         try {
/*  94 */           out.close();
/*     */         } catch (IOException e) {
/*  96 */           logger.error(e.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   private ByteArrayOutputStream innerCopyIn2Out(InputStream in, OutputStream out)
/*     */     throws IOException
/*     */   {
/* 117 */     int bufSize = 1024;
/* 118 */     byte[] bufBytes = new byte[bufSize];
/* 119 */     int readLen = 0;
/* 120 */     ByteArrayOutputStream tmpOut = new ByteArrayOutputStream();
/* 121 */     while ((readLen = in.read(bufBytes, 0, 1024)) != -1) {
/* 122 */       if (out != null) {
/* 123 */         out.write(bufBytes, 0, readLen);
/*     */       }
/* 125 */       tmpOut.write(bufBytes, 0, readLen);
/*     */     }
/* 127 */     return tmpOut;
/*     */   }
/*     */ 
/*     */   private boolean replaceStrategies(ZipInputStream zipInput, ZipOutputStream zipOutput, String entryName, ExportConfig exportConfig)
/*     */     throws IOException
/*     */   {
/* 141 */     ContentStrategy contentStrategy = null;
/* 142 */     if ((entryName.endsWith("pom.xml")) || 
/* 143 */       (entryName.endsWith(".project")))
/*     */     {
/* 145 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 146 */       innerCopyIn2Out(zipInput, out);
/*     */ 
/* 148 */       contentStrategy = new ReplaceProjectNameStrategy(
/* 149 */         out.toString("UTF-8"), 
/* 150 */         exportConfig.getGroupId(), 
/* 151 */         exportConfig.getProjectName());
/*     */ 
/* 154 */       Object modules = exportConfig.get("modules");
/* 155 */       if ((entryName.startsWith("modules/tmp")) && (modules != null) && (modules instanceof String[])) {
/* 156 */         String tmpContent = new String(contentStrategy.getBytes());
/* 157 */         for (String module : (String[])modules) {
/* 158 */           String moduleEntry = entryName.replace("modules/tmp", "modules/" + module);
/* 159 */           logger.info("复制文件:" + moduleEntry);
/* 160 */           addZipEntry(zipOutput, tmpContent.replace("-tmp", "-" + module).getBytes(), moduleEntry);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 167 */     if (contentStrategy == null) {
/* 168 */       return false;
/*     */     }
/*     */ 
/* 171 */     addZipEntry(zipOutput, contentStrategy.getBytes(), entryName);
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   private void addZipEntry(ZipOutputStream zipOut, byte[] bytes, String entryName)
/*     */     throws IOException
/*     */   {
/* 183 */     ZipEntry zipEntry = new ZipEntry(entryName);
/* 184 */     zipOut.putNextEntry(zipEntry);
/* 185 */     int len = bytes.length;
/* 186 */     int index = 0;
/* 187 */     while (len > 0) {
/* 188 */       if (len >= 1024) {
/* 189 */         zipOut.write(bytes, index, 1024);
/* 190 */         index += 1024;
/* 191 */         len -= 1024;
/*     */       } else {
/* 193 */         zipOut.write(bytes, index, len);
/* 194 */         len = 0;
/*     */       }
/*     */     }
/* 197 */     zipOut.closeEntry();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 203 */     SourceProjectExporter sourceExporter = new SourceProjectExporter();
/*     */     try
/*     */     {
/* 206 */       String projectName = "myProject";
/*     */ 
/* 208 */       ExportConfig exportConfig = new ExportConfig(projectName);
/* 209 */       exportConfig.setGroupId("com.gsoft." + projectName);
/* 210 */       exportConfig.setProjectNamePrefix("gsoft");
/*     */ 
/* 213 */       FileOutputStream out = new FileOutputStream("D:/" + exportConfig.getProjectName() + ".zip");
/* 214 */       sourceExporter.export(out, exportConfig);
/*     */     } catch (FileNotFoundException e) {
/* 216 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-codegen.jar
 * Qualified Name:     com.gsoft.platform.codegen.export.SourceProjectExporter
 * JD-Core Version:    0.5.4
 */