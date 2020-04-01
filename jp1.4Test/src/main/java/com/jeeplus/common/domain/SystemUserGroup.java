package com.jeeplus.common.domain;

public class SystemUserGroup {
	
	private int nickId;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private String managementType;
	
	private String isManagementGroup;
	
	private String shieldGroup;
	
	private String permissions;
	
	private String network;
	
	private String queryRegion;

	public int getNickId() {
		return nickId;
	}

	public void setNickId(int nickId) {
		this.nickId = nickId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManagementType() {
		return managementType;
	}

	public void setManagementType(String managementType) {
		this.managementType = managementType;
	}

	public String getIsManagementGroup() {
		return isManagementGroup;
	}

	public void setIsManagementGroup(String isManagementGroup) {
		this.isManagementGroup = isManagementGroup;
	}

	public String getShieldGroup() {
		return shieldGroup;
	}

	public void setShieldGroup(String shieldGroup) {
		this.shieldGroup = shieldGroup;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

    public String getQueryRegion() {
        return queryRegion;
    }

    public void setQueryRegion(String queryRegion) {
        this.queryRegion = queryRegion;
    }
	
}
