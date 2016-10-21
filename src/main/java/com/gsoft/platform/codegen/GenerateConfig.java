 package com.gsoft.platform.codegen;
 
 import java.util.MissingResourceException;
 import java.util.ResourceBundle;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 public class GenerateConfig{
   private String sourceHome = "";
 
   private String javaMainHome = "src/main/java";
   private String javaTestHome = "";
   private String jspHome = "";
 
   private String ftlHome = "";
   private String packagePrefix;
   protected final Log log = LogFactory.getLog(GenerateConfig.class);
 
   private static GenerateConfig config = null;
   private ResourceBundle rb;
 
   public GenerateConfig(){
     this.rb = ResourceBundle.getBundle("generator");
   }
 
   public static GenerateConfig getInstance() {
     if (config == null) {
       config = new GenerateConfig();
     }
     return config;
   }
   public String getConfig(String key) {
     String config;
     try {
       config = this.rb.getString(key);
     } catch (MissingResourceException mse) {
       config = "";
       this.log.info(key + "未找到！");
     }
     return config;
   }
 
   public void dispose() {
     config = null;
     this.rb = null;
   }
 
   public String getSourceHome(){
     return this.sourceHome;
   }
 
   public void setSourceHome(String sourceHome){
     this.sourceHome = sourceHome;
   }
 
   public String getJavaMainHome(){
     return this.javaMainHome;
   }
 
   public void setJavaMainHome(String javaMainHome){
     this.javaMainHome = javaMainHome;
   }
 
   public String getJavaTestHome(){
     return this.javaTestHome;
   }
 
   public void setJavaTestHome(String javaTestHome){
     this.javaTestHome = javaTestHome;
   }
 
   public String getJspHome(){
     return this.jspHome;
   }
 
   public void setJspHome(String jspHome){
     this.jspHome = jspHome;
   }
 
   public String getFtlHome(){
     return this.ftlHome;
   }
 
   public void setFtlHome(String ftlHome){
     this.ftlHome = ftlHome;
   }
 
   public String getPackagePrefix(){
     return this.packagePrefix;
   }
 
   public void setPackagePrefix(String packagePrefix){
     this.packagePrefix = packagePrefix;
   }
 
   public String getSrcMainJavaPath() {
     return getSourceHome() + "/" + getJavaMainHome();
   }
 
   public String getSrcTestJavaPath() {
     return getSourceHome() + "/" + getJavaTestHome();
   }
 
   public String getJspPath() {
     return getSourceHome() + "/" + getJspHome();
   }
 
   public String getUnitSourcePath() {
     return getSourceHome() + "/" + getJavaTestHome();
   }
 }