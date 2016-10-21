<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="generator.${daoClassPath}" >

  <resultMap id="BaseResultMap" type="${modelClassPath}" >
  	<#list attributes as attribute>
	<#if attribute.id=="true">
	<id column="${attribute.column}" property="${attribute.name}" jdbcType="CHAR" />
	</#if>
	</#list>
	<#list attributes as attribute>
	<#if attribute.id!="true">
	<result column="${attribute.column}" property="${attribute.name}" jdbcType="CHAR" />
	</#if>
	</#list>
  </resultMap>
  
  <sql id="Base_Column_List" >
  	<#list attributes as attribute>${attribute.column},</#list>
  </sql>
  
  <delete id="deleteByPrimaryKey" parameterType="java.lang.String" >
    delete from ${tableName} where
    
    <#list attributes as attribute>
	<#if attribute.id=="true">
	${attribute.column} = #\{${attribute.name},jdbcType=VARCHAR\}
	</#if>
	</#list>
	
  </delete>
  
  
</mapper>