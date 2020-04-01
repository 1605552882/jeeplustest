package com.jeeplus.modules.sys.utils;

import java.io.Serializable;

public class RequestIp implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ip;

	private long createTime;

	private Integer reCount;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public Integer getReCount() {
		return reCount;
	}

	public void setReCount(Integer reCount) {
		this.reCount = reCount;
	}
}
