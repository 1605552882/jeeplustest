/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mentalassess.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.mentalassess.entity.EdMentalassessLog;
import com.jeeplus.modules.mentalassess.mapper.EdMentalassessLogMapper;

/**
 * 心理社交评估Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdMentalassessLogService extends CrudService<EdMentalassessLogMapper, EdMentalassessLog> {

	public EdMentalassessLog get(String id) {
		return super.get(id);
	}
	
	public List<EdMentalassessLog> findList(EdMentalassessLog edMentalassessLog) {
		return super.findList(edMentalassessLog);
	}
	
	public Page<EdMentalassessLog> findPage(Page<EdMentalassessLog> page, EdMentalassessLog edMentalassessLog) {
		return super.findPage(page, edMentalassessLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdMentalassessLog edMentalassessLog) {
		super.save(edMentalassessLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdMentalassessLog edMentalassessLog) {
		super.delete(edMentalassessLog);
	}
	
}