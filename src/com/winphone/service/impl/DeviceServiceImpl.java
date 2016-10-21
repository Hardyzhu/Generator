/**
 * 代码声明
 */
package com.winphone.service.impl;

import com.winphone.entity.PageRestful;

import java.util.List;
import com.winphone.entity.Device;
import com.winphone.mapper.DeviceMapper;
import com.winphone.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {
	
	private DeviceMapper deviceMapper;

	public DeviceMapper getDeviceMapper() {
		return deviceMapper;
	}

	public void setDeviceMapper(
			DeviceMapper deviceMapper) {
		this.deviceMapper = deviceMapper;
	}
	
	@Override
	public int insertDevice(Device device) {
		return deviceMapper.insertSelective(device);
	}

	@Override
	public int deleteDevice(String deviceId) {
		return deviceMapper.deleteByPrimaryKey(deviceId);
	}

	@Override
	public int updateDevice(Device device) {
		return deviceMapper.updateByPrimaryKeySelective(device);
	}
	
	@Override
	public Device selectByPrimaryKey(String deviceId){
		return deviceMapper.selectByPrimaryKey(deviceId);
	}

	@Override
	public List<Device> listDevices(Device device) {
		List<Device> deviceList = deviceMapper.listDevices(device);
		return deviceList;
	}
	
	@Override
	public PageRestful listPageDevice(Device device) {
		PageRestful pageRestful=new PageRestful();
		List<Device> devices = deviceMapper.listPageDevice(device);
		devices.add(0,null);
		pageRestful.setDevices(devices);
		pageRestful.setPage(device.getPage());
		return pageRestful;
	}

}