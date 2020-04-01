/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.braden.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.braden.entity.EdBradenLog;
import com.jeeplus.modules.braden.mapper.EdBradenLogMapper;

/**
 * 贝登量表Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdBradenLogService extends CrudService<EdBradenLogMapper, EdBradenLog> {

	public EdBradenLog get(String id) {
		return super.get(id);
	}
	
	public List<EdBradenLog> findList(EdBradenLog edBradenLog) {
		return super.findList(edBradenLog);
	}
	
	public Page<EdBradenLog> findPage(Page<EdBradenLog> page, EdBradenLog edBradenLog) {
		return super.findPage(page, edBradenLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdBradenLog edBradenLog) {
		super.save(edBradenLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdBradenLog edBradenLog) {
		super.delete(edBradenLog);
	}
	
}