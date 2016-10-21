/**
 * 代码声明
 */
package ${package};

import java.util.List;
import ${modelPath}.${modelClassName};

public interface ${modelClassName}Mapper {
	
	/**
	 * 根据条件插入对象字段
	 */
    int insertSelective(${modelClassName} ${modelName});
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String ${modelName}Id);
    /**
	 * 根据条件修改对象属性
	 */
    int updateByPrimaryKeySelective(${modelClassName} ${modelName});
    /**
	 * 根据主键查询
	 */
    ${modelClassName} selectByPrimaryKey(String ${modelName}Id);
    /**
     * 根据条件查询列表
     */
	List<${modelClassName}> list${modelClassName}s(${modelClassName} ${modelName});
    /**
     * 根据条件分页查询列表
     */
	List<${modelClassName}> listPage${modelClassName}(${modelClassName} ${modelName});
    
}