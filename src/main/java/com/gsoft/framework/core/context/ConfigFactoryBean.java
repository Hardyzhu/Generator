/*    */ package com.gsoft.framework.core.context;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.beans.factory.FactoryBean;
/*    */ import org.springframework.beans.factory.InitializingBean;
/*    */ 
/*    */ public class ConfigFactoryBean
/*    */   implements FactoryBean<Config>, InitializingBean
/*    */ {
/* 34 */   private static final Log logger = LogFactory.getLog(ConfigFactoryBean.class);
/*    */   private Properties properties;
/*    */   private Properties decorators;
/*    */   private Config config;
/*    */ 
/*    */   public Properties getProperties()
/*    */   {
/* 46 */     return this.properties;
/*    */   }
/*    */ 
/*    */   public void setProperties(Properties properties)
/*    */   {
/* 53 */     this.properties = properties;
/*    */   }
/*    */ 
/*    */   public Properties getDecorators()
/*    */   {
/* 60 */     return this.decorators;
/*    */   }
/*    */ 
/*    */   public void setDecorators(Properties decorators)
/*    */   {
/* 67 */     this.decorators = decorators;
/*    */   }
/*    */ 
/*    */   public Config getObject() throws Exception {
/* 71 */     return this.config;
/*    */   }
/*    */ 
/*    */   public Class<Config> getObjectType() {
/* 75 */     return Config.class;
/*    */   }
/*    */ 
/*    */   public boolean isSingleton() {
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */   public void afterPropertiesSet() throws Exception
/*    */   {
/* 84 */     logger.debug("---------------初始化平台配置文件------------");
/* 85 */     this.config = Config.init();
/* 86 */     this.config.setDecorators(this.decorators);
/* 87 */     this.config.setProperties(this.properties);
/* 88 */     String defaultLayout = this.properties.getProperty("layout.decorator");
/* 89 */     if (defaultLayout != null)
/* 90 */       this.config.setDefaultLayout(defaultLayout);
/*    */   }
/*    */ }

/* Location:           C:\Users\风中凌乱\Desktop\generator-core.jar
 * Qualified Name:     com.gsoft.framework.core.context.ConfigFactoryBean
 * JD-Core Version:    0.5.4
 */