/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimesign.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.service.CheckresultService;
import com.jeeplus.modules.overtimesign.entity.Overtimesigning;
import com.jeeplus.modules.overtimesign.mapper.OvertimesigningMapper;

/**
 * 超时签单Service
 * @author lxy
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class OvertimesigningService extends CrudService<OvertimesigningMapper, Overtimesigning> {
	
	@Autowired
	private CheckresultService checkresultService;

	public Overtimesigning get(String id) {
		return super.get(id);
	}
	
	public List<Overtimesigning> findList(Overtimesigning overtimesigning) {
		return super.findList(overtimesigning);
	}
	
	public Page<Overtimesigning> findPage(Page<Overtimesigning> page, Overtimesigning overtimesigning) {
		return super.findPage(page, overtimesigning);
	}
	
	@Transactional(readOnly = false)
	public void save(Overtimesigning overtimesigning) {
		super.save(overtimesigning);
	}
	
	@Transactional(readOnly = false)
	public void delete(Overtimesigning overtimesigning) {
		super.delete(overtimesigning);
	}

	public Page<Overtimesigning> findPage(Overtimesigning overtimesigning, HttpServletRequest request,
			HttpServletResponse response) {
		Page<Overtimesigning> page = this.findPage(new Page<Overtimesigning>(request, response), overtimesigning); 
		List<Overtimesigning> list = page.getList();
		for (int i = 0; i < list.size(); i++) {
			List<Checkresult>  checkresult = checkresultService.getBySbillno(list.get(i).getSbillno());
			if (checkresult.size() != 0) {
				list.get(i).setRemarks("已检查");
			} else {
				list.get(i).setRemarks("未检查");
			}
		}
		page.setList(list);
		return page;
	}
	
}