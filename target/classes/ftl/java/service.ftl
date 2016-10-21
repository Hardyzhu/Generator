/**
 * 代码声明
 */
package ${package};

import java.util.List;
import com.winphone.entity.PageRestful;
import ${modelPath}.${modelClassName};

public interface ${modelClassName}Service{
    
	/**
	 * 新增
	 */
	int insert${modelClassName}(${modelClassName} ${modelName});
    
	/**
	 * 根据ID删除数据
	 */
	int delete${modelClassName}(String ${modelName}Id);
	
	/**
	 * 修改
	 */
	int update${modelClassName}(${modelClassName} ${modelName});
	
	/**
	 * 根据Id查找数据
	 */
	${modelClassName} selectByPrimaryKey(String ${modelName}Id);
	
	/**
	 * 根据条件查询列表
	 */
	List<${modelClassName}> list${modelClassName}s(${modelClassName} ${modelName});
	
    /**
	 * 根据条件分页查询列表
	 */
	PageRestful listPage${modelClassName}(${modelClassName} ${modelName});
}