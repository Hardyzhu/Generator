package com.winphone.controller;


import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.winphone.common.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ${modelPath}.${modelClassName};
import ${modelPath}.PageRestful;
import ${serviceClassPath};
import com.winphone.utils.DateUtil;

@Controller
@RequestMapping("web/${modelName}")
public class ${modelClassName}Controller {
	@Autowired
	private ${modelClassName}Service ${modelName}Service;
	
    /**
     * 新增
     */
	@ResponseBody
	@RequestMapping("/insert${modelClassName}.json")
	public Info insert${modelClassName}(@RequestBody ${modelClassName} ${modelName}){
		Info info = new Info();
		${modelName}.set${modelClassName}Id(UUID.randomUUID().toString());
		${modelName}.setCreateUser(${modelName}.getUpdateUser());
		${modelName}.setCreateTime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		${modelName}.setUpdateUser(${modelName}.getUpdateUser());
		${modelName}.setUpdateTime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		int num = ${modelName}Service.insert${modelClassName}(${modelName});
		if (num > 0) {
			info.setCode("200");
			info.setInfo("新增成功");
		} else {
			info.setCode("500");
			info.setInfo("新增失败");
		}
	    return info;// 200表示成功,500表示失败
	}
	/**
     * 删除
     */
	@ResponseBody
	@RequestMapping("/delete${modelClassName}.json")
	public Info delete${modelClassName}(@RequestBody ${modelClassName} ${modelName}){
		Info info = new Info();
		int num = ${modelName}Service.delete${modelClassName}(${modelName}.get${modelClassName}Id());
		if (num > 0) {
			info.setCode("200");
			info.setInfo("删除成功");
		} else {
			info.setCode("500");
			info.setInfo("删除失败");
		}
	    return info;// 200表示成功,500表示失败
	}
	/**
     * 修改
     */
	@ResponseBody
	@RequestMapping("/update${modelClassName}.json")
	public Info update${modelClassName}(@RequestBody ${modelClassName} ${modelName}){
		Info info = new Info();
		${modelName}.setUpdateTime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		int num = ${modelName}Service.update${modelClassName}(${modelName});
		if (num > 0) {
			info.setCode("200");
			info.setInfo("修改成功");
		} else {
			info.setCode("500");
			info.setInfo("修改失败");
		}
	    return info;// 200表示成功,500表示失败
	}
	/**
	 * 根据条件分页查询信息列表
	 */
	@ResponseBody
	@RequestMapping(value="/listPage${modelClassName}.json")
	public PageRestful listPage${modelClassName}(@RequestBody ${modelClassName} ${modelName}){
		PageRestful pageRestful = ${modelName}Service.listPage${modelClassName}(${modelName});
		return pageRestful;
	}
	/**
	 * 根据主键查询
	 */
	@ResponseBody
	@RequestMapping(value = "/selectByPrimaryKey.json")
	public ${modelClassName} selectByPrimaryKey(@RequestBody ${modelClassName} ${modelName}) {
		return ${modelName}Service.selectByPrimaryKey(${modelName}.get${modelClassName}Id());
	}
}