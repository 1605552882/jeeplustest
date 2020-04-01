/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.nursingclass.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.nursingclass.entity.EdNursingclassLog;
import com.jeeplus.modules.nursingclass.mapper.EdNursingclassLogMapper;

/**
 * 护理级别Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdNursingclassLogService extends CrudService<EdNursingclassLogMapper, EdNursingclassLog> {

	public EdNursingclassLog get(String id) {
		return super.get(id);
	}
	
	public List<EdNursingclassLog> findList(EdNursingclassLog edNursingclassLog) {
		return super.findList(edNursingclassLog);
	}
	
	public Page<EdNursingclassLog> findPage(Page<EdNursingclassLog> page, EdNursingclassLog edNursingclassLog) {
		return super.findPage(page, edNursingclassLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdNursingclassLog edNursingclassLog) {
		super.save(edNursingclassLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdNursingclassLog edNursingclassLog) {
		super.delete(edNursingclassLog);
	}
	
}