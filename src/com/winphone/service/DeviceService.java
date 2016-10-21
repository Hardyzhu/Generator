/**
 * 代码声明
 */
package com.winphone.service;

import java.util.List;
import com.winphone.entity.PageRestful;
import com.winphone.entity.Device;

public interface DeviceService{
    
	/**
	 * 新增
	 */
	int insertDevice(Device device);
    
	/**
	 * 根据ID删除数据
	 */
	int deleteDevice(String deviceId);
	
	/**
	 * 修改
	 */
	int updateDevice(Device device);
	
	/**
	 * 根据Id查找数据
	 */
	Device selectByPrimaryKey(String deviceId);
	
	/**
	 * 根据条件查询列表
	 */
	List<Device> listDevices(Device device);
	
    /**
	 * 根据条件分页查询列表
	 */
	PageRestful listPageDevice(Device device);
}