/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.complaindocument.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.service.CheckresultService;
import com.jeeplus.modules.complaindocument.entity.Complaindocument;
import com.jeeplus.modules.complaindocument.mapper.ComplaindocumentMapper;

/**
 * 重复投诉工单Service
 * @author zqb
 * @version 2019-10-11
 */
@Service
@Transactional(readOnly = true)
public class ComplaindocumentService extends CrudService<ComplaindocumentMapper, Complaindocument> {
	
	@Autowired
	private CheckresultService checkresultService;
	

	public Complaindocument get(String id) {
		return super.get(id);
	}
	
	public List<Complaindocument> findList(Complaindocument complaindocument) {
		return super.findList(complaindocument);
	}
	
	public Page<Complaindocument> findPage(Page<Complaindocument> page, Complaindocument complaindocument) {
		return super.findPage(page, complaindocument);
	}
	
	@Transactional(readOnly = false)
	public void save(Complaindocument complaindocument) {
		super.save(complaindocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(Complaindocument complaindocument) {
		super.delete(complaindocument);
	}

	public Page<Complaindocument> changeList(Page<Complaindocument> page) {
		List<Complaindocument> list = page.getList();
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