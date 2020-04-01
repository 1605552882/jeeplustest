/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.errordocument.service;

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
import com.jeeplus.modules.errordocument.entity.Errordocument;
import com.jeeplus.modules.errordocument.mapper.ErrordocumentMapper;

/**
 * 疑似异常工单Service
 * @author lxy
 * @version 2019-08-28
 */
@Service
@Transactional(readOnly = true)
public class ErrordocumentService extends CrudService<ErrordocumentMapper, Errordocument> {
	
	@Autowired
	private CheckresultService checkresultService;

	public Errordocument get(String id) {
		return super.get(id);
	}
	
	public List<Errordocument> findList(Errordocument errordocument) {
		return super.findList(errordocument);
	}
	
	public Page<Errordocument> findPage(Page<Errordocument> page, Errordocument errordocument) {
		return super.findPage(page, errordocument);
	}
	
	@Transactional(readOnly = false)
	public void save(Errordocument errordocument) {
		super.save(errordocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(Errordocument errordocument) {
		super.delete(errordocument);
	}

	public Page<Errordocument> findpage(Errordocument errordocument, HttpServletRequest request,
			HttpServletResponse response, boolean isPage) {
		Page<Errordocument> page = null;
		if(isPage) {
			page = this.findPage(new Page<Errordocument>(request, response), errordocument); 
		}else {
			page = this.findPage(new Page<Errordocument>(request, response,-1), errordocument); 
		}
		List<Errordocument> list = page.getList();
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