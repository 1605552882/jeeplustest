/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.medication.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.medication.entity.EdMedicationLog;
import com.jeeplus.modules.medication.mapper.EdMedicationLogMapper;

/**
 * 长者用药情况Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdMedicationLogService extends CrudService<EdMedicationLogMapper, EdMedicationLog> {

	public EdMedicationLog get(String id) {
		return super.get(id);
	}
	
	public List<EdMedicationLog> findList(EdMedicationLog edMedicationLog) {
		return super.findList(edMedicationLog);
	}
	
	public Page<EdMedicationLog> findPage(Page<EdMedicationLog> page, EdMedicationLog edMedicationLog) {
		return super.findPage(page, edMedicationLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdMedicationLog edMedicationLog) {
		super.save(edMedicationLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdMedicationLog edMedicationLog) {
		super.delete(edMedicationLog);
	}
	
}