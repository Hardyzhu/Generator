/**
 * 代码声明
 */
package ${package}.impl;

import com.winphone.entity.PageRestful;

import java.util.List;
import ${modelPath}.${modelClassName};
import ${daoClassPath};
import ${serviceClassPath};

public class ${modelClassName}ServiceImpl implements ${modelClassName}Service {
	
	private ${modelClassName}Mapper ${modelName}Mapper;

	public ${modelClassName}Mapper get${modelClassName}Mapper() {
		return ${modelName}Mapper;
	}

	public void set${modelClassName}Mapper(
			${modelClassName}Mapper ${modelName}Mapper) {
		this.${modelName}Mapper = ${modelName}Mapper;
	}
	
	@Override
	public int insert${modelClassName}(${modelClassName} ${modelName}) {
		return ${modelName}Mapper.insertSelective(${modelName});
	}

	@Override
	public int delete${modelClassName}(String ${modelName}Id) {
		return ${modelName}Mapper.deleteByPrimaryKey(${modelName}Id);
	}

	@Override
	public int update${modelClassName}(${modelClassName} ${modelName}) {
		return ${modelName}Mapper.updateByPrimaryKeySelective(${modelName});
	}
	
	@Override
	public ${modelClassName} selectByPrimaryKey(String ${modelName}Id){
		return ${modelName}Mapper.selectByPrimaryKey(${modelName}Id);
	}

	@Override
	public List<${modelClassName}> list${modelClassName}s(${modelClassName} ${modelName}) {
		List<${modelClassName}> ${modelName}List = ${modelName}Mapper.list${modelClassName}s(${modelName});
		return ${modelName}List;
	}
	
	@Override
	public PageRestful listPage${modelClassName}(${modelClassName} ${modelName}) {
		PageRestful pageRestful=new PageRestful();
		List<${modelClassName}> ${modelName}s = ${modelName}Mapper.listPage${modelClassName}(${modelName});
		${modelName}s.add(0,null);
		pageRestful.set${modelClassName}s(${modelName}s);
		pageRestful.setPage(${modelName}.getPage());
		return pageRestful;
	}

}