/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimefeedback.service;

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
import com.jeeplus.modules.overtimefeedback.entity.Overtimefeedback;
import com.jeeplus.modules.overtimefeedback.mapper.OvertimefeedbackMapper;

/**
 * 反馈超时Service
 * @author lxy
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class OvertimefeedbackService extends CrudService<OvertimefeedbackMapper, Overtimefeedback> {
	
	@Autowired
	private CheckresultService checkresultService;

	public Overtimefeedback get(String id) {
		return super.get(id);
	}
	
	public List<Overtimefeedback> findList(Overtimefeedback overtimefeedback) {
		return super.findList(overtimefeedback);
	}
	
	public Page<Overtimefeedback> findPage(Page<Overtimefeedback> page, Overtimefeedback overtimefeedback) {
		return super.findPage(page, overtimefeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(Overtimefeedback overtimefeedback) {
		super.save(overtimefeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(Overtimefeedback overtimefeedback) {
		super.delete(overtimefeedback);
	}

	public Page<Overtimefeedback> findPage(Overtimefeedback overtimefeedback, HttpServletRequest request,
			HttpServletResponse response) {
		Page<Overtimefeedback> page = this.findPage(new Page<Overtimefeedback>(request, response), overtimefeedback); 
		List<Overtimefeedback> list = page.getList();
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