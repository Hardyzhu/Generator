/**
 *
 */
package com.winphone.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 实体: 设备信息表
 * @author
 * @version
 */
@XmlRootElement(name = "Device")
public class Device {
	private String deviceYpoint ;//设备Y坐标
	
	private String deviceModel;//设备型号
	
	private String createTime;//创建时间
	
	private String productionDate;//出厂日期
	
	private String updateTime;//修改时间
	
	private String updateUser;//修改人
	
	private String warrantyTerm;//质保期限
	
	private String deviceId;//设备ID
	
	private String installAddress;//安装地址
	
	private String parkId;//所属园区
	
	private String deviceAutoStatus;//设备手自动状态
	
	private String buildId;//所属楼栋
	
	private String deviceXpoint ;//设备X坐标
	
	private String deviceRunStatus;//设备运行状态
	
	private String floorId;//所属楼层
	
	private String deviceTypeId;//设备类型
	
	private String createUser;//创建人
	
	private String deviceSwitchStatus;//设备开关状态
	
	private String deviceNo;//设备编号
	
	private Page page;//分页
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public String getDeviceYpoint (){
		return this.deviceYpoint ;
	}
	
	public void setDeviceYpoint (String deviceYpoint ){
		this.deviceYpoint  = deviceYpoint ;
	}
	public String getDeviceModel(){
		return this.deviceModel;
	}
	
	public void setDeviceModel(String deviceModel){
		this.deviceModel = deviceModel;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	public String getProductionDate(){
		return this.productionDate;
	}
	
	public void setProductionDate(String productionDate){
		this.productionDate = productionDate;
	}
	public String getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	public String getUpdateUser(){
		return this.updateUser;
	}
	
	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser;
	}
	public String getWarrantyTerm(){
		return this.warrantyTerm;
	}
	
	public void setWarrantyTerm(String warrantyTerm){
		this.warrantyTerm = warrantyTerm;
	}
	public String getDeviceId(){
		return this.deviceId;
	}
	
	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}
	public String getInstallAddress(){
		return this.installAddress;
	}
	
	public void setInstallAddress(String installAddress){
		this.installAddress = installAddress;
	}
	public String getParkId(){
		return this.parkId;
	}
	
	public void setParkId(String parkId){
		this.parkId = parkId;
	}
	public String getDeviceAutoStatus(){
		return this.deviceAutoStatus;
	}
	
	public void setDeviceAutoStatus(String deviceAutoStatus){
		this.deviceAutoStatus = deviceAutoStatus;
	}
	public String getBuildId(){
		return this.buildId;
	}
	
	public void setBuildId(String buildId){
		this.buildId = buildId;
	}
	public String getDeviceXpoint (){
		return this.deviceXpoint ;
	}
	
	public void setDeviceXpoint (String deviceXpoint ){
		this.deviceXpoint  = deviceXpoint ;
	}
	public String getDeviceRunStatus(){
		return this.deviceRunStatus;
	}
	
	public void setDeviceRunStatus(String deviceRunStatus){
		this.deviceRunStatus = deviceRunStatus;
	}
	public String getFloorId(){
		return this.floorId;
	}
	
	public void setFloorId(String floorId){
		this.floorId = floorId;
	}
	public String getDeviceTypeId(){
		return this.deviceTypeId;
	}
	
	public void setDeviceTypeId(String deviceTypeId){
		this.deviceTypeId = deviceTypeId;
	}
	public String getCreateUser(){
		return this.createUser;
	}
	
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}
	public String getDeviceSwitchStatus(){
		return this.deviceSwitchStatus;
	}
	
	public void setDeviceSwitchStatus(String deviceSwitchStatus){
		this.deviceSwitchStatus = deviceSwitchStatus;
	}
	public String getDeviceNo(){
		return this.deviceNo;
	}
	
	public void setDeviceNo(String deviceNo){
		this.deviceNo = deviceNo;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceYpoint  == null) ? 0 : deviceYpoint .hashCode());
		result = prime * result + ((deviceModel == null) ? 0 : deviceModel.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((productionDate == null) ? 0 : productionDate.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((updateUser == null) ? 0 : updateUser.hashCode());
		result = prime * result + ((warrantyTerm == null) ? 0 : warrantyTerm.hashCode());
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((installAddress == null) ? 0 : installAddress.hashCode());
		result = prime * result + ((parkId == null) ? 0 : parkId.hashCode());
		result = prime * result + ((deviceAutoStatus == null) ? 0 : deviceAutoStatus.hashCode());
		result = prime * result + ((buildId == null) ? 0 : buildId.hashCode());
		result = prime * result + ((deviceXpoint  == null) ? 0 : deviceXpoint .hashCode());
		result = prime * result + ((deviceRunStatus == null) ? 0 : deviceRunStatus.hashCode());
		result = prime * result + ((floorId == null) ? 0 : floorId.hashCode());
		result = prime * result + ((deviceTypeId == null) ? 0 : deviceTypeId.hashCode());
		result = prime * result + ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((deviceSwitchStatus == null) ? 0 : deviceSwitchStatus.hashCode());
		result = prime * result + ((deviceNo == null) ? 0 : deviceNo.hashCode());
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
		final Device other = (Device) obj;
		if (deviceYpoint  == null) {
			if (other.deviceYpoint  != null)
				return false;
		} else if (!deviceYpoint .equals(other.deviceYpoint ))
			return false;
		if (deviceModel == null) {
			if (other.deviceModel != null)
				return false;
		} else if (!deviceModel.equals(other.deviceModel))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (productionDate == null) {
			if (other.productionDate != null)
				return false;
		} else if (!productionDate.equals(other.productionDate))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		if (warrantyTerm == null) {
			if (other.warrantyTerm != null)
				return false;
		} else if (!warrantyTerm.equals(other.warrantyTerm))
			return false;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (installAddress == null) {
			if (other.installAddress != null)
				return false;
		} else if (!installAddress.equals(other.installAddress))
			return false;
		if (parkId == null) {
			if (other.parkId != null)
				return false;
		} else if (!parkId.equals(other.parkId))
			return false;
		if (deviceAutoStatus == null) {
			if (other.deviceAutoStatus != null)
				return false;
		} else if (!deviceAutoStatus.equals(other.deviceAutoStatus))
			return false;
		if (buildId == null) {
			if (other.buildId != null)
				return false;
		} else if (!buildId.equals(other.buildId))
			return false;
		if (deviceXpoint  == null) {
			if (other.deviceXpoint  != null)
				return false;
		} else if (!deviceXpoint .equals(other.deviceXpoint ))
			return false;
		if (deviceRunStatus == null) {
			if (other.deviceRunStatus != null)
				return false;
		} else if (!deviceRunStatus.equals(other.deviceRunStatus))
			return false;
		if (floorId == null) {
			if (other.floorId != null)
				return false;
		} else if (!floorId.equals(other.floorId))
			return false;
		if (deviceTypeId == null) {
			if (other.deviceTypeId != null)
				return false;
		} else if (!deviceTypeId.equals(other.deviceTypeId))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (deviceSwitchStatus == null) {
			if (other.deviceSwitchStatus != null)
				return false;
		} else if (!deviceSwitchStatus.equals(other.deviceSwitchStatus))
			return false;
		if (deviceNo == null) {
			if (other.deviceNo != null)
				return false;
		} else if (!deviceNo.equals(other.deviceNo))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}