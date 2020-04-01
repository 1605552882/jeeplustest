/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimedocument.service;

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
import com.jeeplus.modules.overtimedocument.entity.Overtimedocument;
import com.jeeplus.modules.overtimedocument.mapper.OvertimedocumentMapper;

/**
 * 工单超时Service
 * @author lxy
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class OvertimedocumentService extends CrudService<OvertimedocumentMapper, Overtimedocument> {
	
	@Autowired
	private CheckresultService checkresultService;
	
	public Overtimedocument get(String id) {
		return super.get(id);
	}
	
	public List<Overtimedocument> findList(Overtimedocument overtimedocument) {
		return super.findList(overtimedocument);
	}
	
	public Page<Overtimedocument> findPage(Page<Overtimedocument> page, Overtimedocument overtimedocument) {
		return super.findPage(page, overtimedocument);
	}
	
	@Transactional(readOnly = false)
	public void save(Overtimedocument overtimedocument) {
		super.save(overtimedocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(Overtimedocument overtimedocument) {
		super.delete(overtimedocument);
	}

	public Page<Overtimedocument> findPage(Overtimedocument overtimedocument, HttpServletRequest request,
			HttpServletResponse response) {
		Page<Overtimedocument> page = this.findPage(new Page<Overtimedocument>(request, response), overtimedocument);
		List<Overtimedocument> list = page.getList();
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