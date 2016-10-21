package com.gsoft.platform.codegen.compile;

import java.io.IOException;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

public class BeanLoader{
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
	private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
	private BeanDefinitionRegistry registry;
	
	public BeanLoader(BeanDefinitionRegistry registry){
	  this.registry = registry;
	}
	
	public void loadBean(Resource resource)throws IOException{
	  MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
	  BeanDefinition candidate = createCandidate(resource, metadataReader);
	  ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
	  candidate.setScope(scopeMetadata.getScopeName());
	  String beanName = this.beanNameGenerator.generateBeanName(candidate, this.registry);
	
	  BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate, beanName);
	  definitionHolder = applyScopedProxyMode(scopeMetadata, 
	    definitionHolder, this.registry);
	  registerBeanDefinition(definitionHolder, this.registry);
	}
	
	private BeanDefinition createCandidate(Resource resource, MetadataReader metadataReader){
	  ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
	  sbd.setResource(resource);
	  sbd.setSource(resource);
	  return sbd;
	}
	
	protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
	  BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
	}
	
	static BeanDefinitionHolder applyScopedProxyMode(ScopeMetadata metadata, BeanDefinitionHolder definition, BeanDefinitionRegistry registry)
	{
	  ScopedProxyMode scopedProxyMode = metadata.getScopedProxyMode();
	  if (scopedProxyMode.equals(ScopedProxyMode.NO)) {
	    return definition;
	  }
	  boolean proxyTargetClass = scopedProxyMode.equals(ScopedProxyMode.TARGET_CLASS);
	  return createScopedProxy(definition, registry, proxyTargetClass);
	}
	
	public static BeanDefinitionHolder createScopedProxy(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry, boolean proxyTargetClass){
	  return ScopedProxyUtils.createScopedProxy(definitionHolder, registry, proxyTargetClass);
	}
	
	public static String getTargetBeanName(String originalBeanName){
	  return ScopedProxyUtils.getTargetBeanName(originalBeanName);
	}
}
