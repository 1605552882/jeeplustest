/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialevent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.specialevent.entity.EdSpecialEventLog;
import com.jeeplus.modules.specialevent.mapper.EdSpecialEventLogMapper;

/**
 * 长者特殊事件Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdSpecialEventLogService extends CrudService<EdSpecialEventLogMapper, EdSpecialEventLog> {

	public EdSpecialEventLog get(String id) {
		return super.get(id);
	}
	
	public List<EdSpecialEventLog> findList(EdSpecialEventLog edSpecialEventLog) {
		return super.findList(edSpecialEventLog);
	}
	
	public Page<EdSpecialEventLog> findPage(Page<EdSpecialEventLog> page, EdSpecialEventLog edSpecialEventLog) {
		return super.findPage(page, edSpecialEventLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdSpecialEventLog edSpecialEventLog) {
		super.save(edSpecialEventLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdSpecialEventLog edSpecialEventLog) {
		super.delete(edSpecialEventLog);
	}
	
}