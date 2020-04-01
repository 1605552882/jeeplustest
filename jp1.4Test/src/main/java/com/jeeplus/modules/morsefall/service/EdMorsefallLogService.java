/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.morsefall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.morsefall.entity.EdMorsefallLog;
import com.jeeplus.modules.morsefall.mapper.EdMorsefallLogMapper;

/**
 * 跌倒风险评估Service
 * @author lukbob
 * @version 2018-11-13
 */
@Service
@Transactional(readOnly = true)
public class EdMorsefallLogService extends CrudService<EdMorsefallLogMapper, EdMorsefallLog> {

	public EdMorsefallLog get(String id) {
		return super.get(id);
	}
	
	public List<EdMorsefallLog> findList(EdMorsefallLog edMorsefallLog) {
		return super.findList(edMorsefallLog);
	}
	
	public Page<EdMorsefallLog> findPage(Page<EdMorsefallLog> page, EdMorsefallLog edMorsefallLog) {
		return super.findPage(page, edMorsefallLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdMorsefallLog edMorsefallLog) {
		super.save(edMorsefallLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdMorsefallLog edMorsefallLog) {
		super.delete(edMorsefallLog);
	}
	
}