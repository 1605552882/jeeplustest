/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialnursing.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.specialnursing.entity.EdSpecialnursingLog;
import com.jeeplus.modules.specialnursing.mapper.EdSpecialnursingLogMapper;

/**
 * 特殊护理记录Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdSpecialnursingLogService extends CrudService<EdSpecialnursingLogMapper, EdSpecialnursingLog> {

	public EdSpecialnursingLog get(String id) {
		return super.get(id);
	}
	
	public List<EdSpecialnursingLog> findList(EdSpecialnursingLog edSpecialnursingLog) {
		return super.findList(edSpecialnursingLog);
	}
	
	public Page<EdSpecialnursingLog> findPage(Page<EdSpecialnursingLog> page, EdSpecialnursingLog edSpecialnursingLog) {
		return super.findPage(page, edSpecialnursingLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdSpecialnursingLog edSpecialnursingLog) {
		super.save(edSpecialnursingLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdSpecialnursingLog edSpecialnursingLog) {
		super.delete(edSpecialnursingLog);
	}
	
}