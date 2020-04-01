/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialdiscuss.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.specialdiscuss.entity.EdSpecialDiscussLog;
import com.jeeplus.modules.specialdiscuss.mapper.EdSpecialDiscussLogMapper;

/**
 * 长者特别情况Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdSpecialDiscussLogService extends CrudService<EdSpecialDiscussLogMapper, EdSpecialDiscussLog> {

	public EdSpecialDiscussLog get(String id) {
		return super.get(id);
	}
	
	public List<EdSpecialDiscussLog> findList(EdSpecialDiscussLog edSpecialDiscussLog) {
		return super.findList(edSpecialDiscussLog);
	}
	
	public Page<EdSpecialDiscussLog> findPage(Page<EdSpecialDiscussLog> page, EdSpecialDiscussLog edSpecialDiscussLog) {
		return super.findPage(page, edSpecialDiscussLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdSpecialDiscussLog edSpecialDiscussLog) {
		super.save(edSpecialDiscussLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdSpecialDiscussLog edSpecialDiscussLog) {
		super.delete(edSpecialDiscussLog);
	}
	
}