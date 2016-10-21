/**
 *
 */
package ${package};

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 实体: ${modelDescription}
 * @author
 * @version
 */
@XmlRootElement(name = "${className}")
public class ${className} {
	<#list attributes as attribute>
	private ${attribute.type} ${attribute.name};<#if attribute.description??>//${attribute.description}</#if>
	
	</#list>
	private Page page;//分页
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	<#list attributes as attribute>
	public ${attribute.type} get${attribute.FUName}(){
		return this.${attribute.name};
	}
	
	public void set${attribute.FUName}(${attribute.type} ${attribute.name}){
		this.${attribute.name} = ${attribute.name};
	}
	</#list>
	
	<#list foreignAttributes as foreignAttribute>
	public void set${foreignAttribute.FUName}(${foreignAttribute.type} ${foreignAttribute.name}){
		this.${foreignAttribute.name} = ${foreignAttribute.name};
	}
	
	public ${foreignAttribute.type} get${foreignAttribute.FUName}(){
		return this.${foreignAttribute.name};
	}
	</#list>
	
	<#list setAttributes as setAttribute>
	public Set get${setAttribute.FUName}(){
		return this.${setAttribute.name};
	}
	
	public void set${setAttribute.FUName}(Set ${setAttribute.name}){
		this.${setAttribute.name} = ${setAttribute.name};
	}
	</#list>
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		<#list attributes as attribute>
		result = prime * result + ((${attribute.name} == null) ? 0 : ${attribute.name}.hashCode());
		</#list>
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ${className} other = (${className}) obj;
		<#list attributes as attribute>
		if (${attribute.name} == null) {
			if (other.${attribute.name} != null)
				return false;
		} else if (!${attribute.name}.equals(other.${attribute.name}))
			return false;
		</#list>
		return true;
	}
	
	public String toString(){
		return ${toString};
	}
}