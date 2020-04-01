/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.physiologyassess.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.physiologyassess.entity.EdPhysiologyassessLog;
import com.jeeplus.modules.physiologyassess.mapper.EdPhysiologyassessLogMapper;

/**
 * 生理状况评估Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdPhysiologyassessLogService extends CrudService<EdPhysiologyassessLogMapper, EdPhysiologyassessLog> {

	public EdPhysiologyassessLog get(String id) {
		return super.get(id);
	}
	
	public List<EdPhysiologyassessLog> findList(EdPhysiologyassessLog edPhysiologyassessLog) {
		return super.findList(edPhysiologyassessLog);
	}
	
	public Page<EdPhysiologyassessLog> findPage(Page<EdPhysiologyassessLog> page, EdPhysiologyassessLog edPhysiologyassessLog) {
		return super.findPage(page, edPhysiologyassessLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdPhysiologyassessLog edPhysiologyassessLog) {
		super.save(edPhysiologyassessLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdPhysiologyassessLog edPhysiologyassessLog) {
		super.delete(edPhysiologyassessLog);
	}
	
}