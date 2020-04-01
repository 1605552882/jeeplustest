/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.activityassess.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.activityassess.entity.EdActivityassessLog;
import com.jeeplus.modules.activityassess.mapper.EdActivityassessLogMapper;

/**
 * 日常活动能力评估Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdActivityassessLogService extends CrudService<EdActivityassessLogMapper, EdActivityassessLog> {

	public EdActivityassessLog get(String id) {
		return super.get(id);
	}
	
	public List<EdActivityassessLog> findList(EdActivityassessLog edActivityassessLog) {
		return super.findList(edActivityassessLog);
	}
	
	public Page<EdActivityassessLog> findPage(Page<EdActivityassessLog> page, EdActivityassessLog edActivityassessLog) {
		return super.findPage(page, edActivityassessLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdActivityassessLog edActivityassessLog) {
		super.save(edActivityassessLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdActivityassessLog edActivityassessLog) {
		super.delete(edActivityassessLog);
	}
	
}