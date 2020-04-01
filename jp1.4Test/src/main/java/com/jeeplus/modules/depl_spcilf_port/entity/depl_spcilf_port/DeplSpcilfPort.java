/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.depl_spcilf_port.entity.depl_spcilf_port;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 机器部署端口Entity
 * @author 姜森焱
 * @version 2020-03-04
 */
public class DeplSpcilfPort extends DataEntity<DeplSpcilfPort> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 程序名字
	private String takeTime;		// 执行力度（小时/天）
	private String svn;		// SVN存放目录
	private String duplicate;		// 备份目录
	private String proSolu;		// 程序故障处理
	private String backupCate;		// 程序升级部署方法
	private String usallProblem;		// 常见问题与使用技巧
	private String targetid;		// targetid
	private String port;		// 端口
	private String way;		// 部署目录
	private String func;		// 实现功能
	private String howtodo;		// 程序执行方法
	private String isupdate;//判断选中配置修改端口
	
	
	public String getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(String isupdate) {
		this.isupdate = isupdate;
	}

	public DeplSpcilfPort() {
		super();
	}

	public DeplSpcilfPort(String id){
		super(id);
	}

	@ExcelField(title="程序名字", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="执行力度（小时/天）", align=2, sort=2)
	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	
	@ExcelField(title="SVN存放目录", align=2, sort=3)
	public String getSvn() {
		return svn;
	}

	public void setSvn(String svn) {
		this.svn = svn;
	}
	
	@ExcelField(title="备份目录", align=2, sort=4)
	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}
	
	@ExcelField(title="程序故障处理", align=2, sort=5)
	public String getProSolu() {
		return proSolu;
	}

	public void setProSolu(String proSolu) {
		this.proSolu = proSolu;
	}
	
	@ExcelField(title="程序升级部署方法", align=2, sort=6)
	public String getBackupCate() {
		return backupCate;
	}

	public void setBackupCate(String backupCate) {
		this.backupCate = backupCate;
	}
	
	@ExcelField(title="常见问题与使用技巧", align=2, sort=7)
	public String getUsallProblem() {
		return usallProblem;
	}

	public void setUsallProblem(String usallProblem) {
		this.usallProblem = usallProblem;
	}
	
	@ExcelField(title="targetid", align=2, sort=8)
	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	
	@ExcelField(title="端口", align=2, sort=9)
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	@ExcelField(title="部署目录", align=2, sort=10)
	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}
	
	@ExcelField(title="实现功能", align=2, sort=11)
	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}
	
	@ExcelField(title="程序执行方法", align=2, sort=12)
	public String getHowtodo() {
		return howtodo;
	}

	public void setHowtodo(String howtodo) {
		this.howtodo = howtodo;
	}
	
}