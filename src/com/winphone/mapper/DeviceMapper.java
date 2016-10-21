/**
 * 代码声明
 */
package com.winphone.mapper;

import java.util.List;
import com.winphone.entity.Device;

public interface DeviceMapper {
	
	/**
	 * 根据条件插入对象字段
	 */
    int insertSelective(Device device);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String deviceId);
    /**
	 * 根据条件修改对象属性
	 */
    int updateByPrimaryKeySelective(Device device);
    /**
	 * 根据主键查询
	 */
    Device selectByPrimaryKey(String deviceId);
    /**
     * 根据条件查询列表
     */
	List<Device> listDevices(Device device);
    /**
     * 根据条件分页查询列表
     */
	List<Device> listPageDevice(Device device);
    
}