package com.jeeplus.common.domain;

import java.util.Date;

public class groupBarrier {
	private Integer id;
	
	private String userid;
	
	private Date create_date;
	
	private Date end_date;
	
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	private String attr_city;
	
	private String malf_city;
	
	private String serv;
	
	private String msg;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getAttr_city() {
		return attr_city;
	}

	public void setAttr_city(String attr_city) {
		this.attr_city = attr_city;
	}

	public String getMalf_city() {
		return malf_city;
	}

	public void setMalf_city(String malf_city) {
		this.malf_city = malf_city;
	}

	public String getServ() {
		return serv;
	}

	public void setServ(String serv) {
		this.serv = serv;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
	


	

	
}
