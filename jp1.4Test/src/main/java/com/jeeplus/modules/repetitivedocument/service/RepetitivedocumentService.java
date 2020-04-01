/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.repetitivedocument.service;

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
import com.jeeplus.modules.repetitivedocument.entity.Repetitivedocument;
import com.jeeplus.modules.repetitivedocument.mapper.RepetitivedocumentMapper;

/**
 * 重复退单Service
 * @author lxy
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class RepetitivedocumentService extends CrudService<RepetitivedocumentMapper, Repetitivedocument> {
	
	@Autowired
	private CheckresultService checkresultService;

	public Repetitivedocument get(String id) {
		return super.get(id);
	}
	
	public List<Repetitivedocument> findList(Repetitivedocument repetitivedocument) {
		return super.findList(repetitivedocument);
	}
	
	public Page<Repetitivedocument> findPage(Page<Repetitivedocument> page, Repetitivedocument repetitivedocument) {
		return super.findPage(page, repetitivedocument);
	}
	
	@Transactional(readOnly = false)
	public void save(Repetitivedocument repetitivedocument) {
		super.save(repetitivedocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(Repetitivedocument repetitivedocument) {
		super.delete(repetitivedocument);
	}

	public Page<Repetitivedocument> findPage(Repetitivedocument repetitivedocument, HttpServletRequest request,
			HttpServletResponse response) {
		Page<Repetitivedocument> page = this.findPage(new Page<Repetitivedocument>(request, response), repetitivedocument); 
		List<Repetitivedocument> list = page.getList();
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