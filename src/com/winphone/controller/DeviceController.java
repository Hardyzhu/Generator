package com.winphone.controller;


import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.winphone.common.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winphone.entity.Device;
import com.winphone.entity.PageRestful;
import com.winphone.service.DeviceService;
import com.winphone.utils.DateUtil;

@Controller
@RequestMapping("web/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
    /**
     * 新增
     */
	@ResponseBody
	@RequestMapping("/insertDevice.json")
	public Info insertDevice(@RequestBody Device device){
		Info info = new Info();
		device.setDeviceId(UUID.randomUUID().toString());
		device.setCreateUser(device.getUpdateUser());
		device.setCreateTime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		device.setUpdateUser(device.getUpdateUser());
		device.setUpdateTime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		int num = deviceService.insertDevice(device);
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
	@RequestMapping("/deleteDevice.json")
	public Info deleteDevice(@RequestBody Device device){
		Info info = new Info();
		int num = deviceService.deleteDevice(device.getDeviceId());
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
	@RequestMapping("/updateDevice.json")
	public Info updateDevice(@RequestBody Device device){
		Info info = new Info();
		device.setUpdateTime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		int num = deviceService.updateDevice(device);
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
	@RequestMapping(value="/listPageDevice.json")
	public PageRestful listPageDevice(@RequestBody Device device){
		PageRestful pageRestful = deviceService.listPageDevice(device);
		return pageRestful;
	}
	/**
	 * 根据主键查询
	 */
	@ResponseBody
	@RequestMapping(value = "/selectByPrimaryKey.json")
	public Device selectByPrimaryKey(@RequestBody Device device) {
		return deviceService.selectByPrimaryKey(device.getDeviceId());
	}
}