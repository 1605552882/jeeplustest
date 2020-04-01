/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.edseedoctor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.edseedoctor.entity.EdSeedoctorLog;
import com.jeeplus.modules.edseedoctor.mapper.EdSeedoctorLogMapper;

/**
 * 就医复诊资料Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdSeedoctorLogService extends CrudService<EdSeedoctorLogMapper, EdSeedoctorLog> {

	public EdSeedoctorLog get(String id) {
		return super.get(id);
	}
	
	public List<EdSeedoctorLog> findList(EdSeedoctorLog edSeedoctorLog) {
		return super.findList(edSeedoctorLog);
	}
	
	public Page<EdSeedoctorLog> findPage(Page<EdSeedoctorLog> page, EdSeedoctorLog edSeedoctorLog) {
		return super.findPage(page, edSeedoctorLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdSeedoctorLog edSeedoctorLog) {
		super.save(edSeedoctorLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdSeedoctorLog edSeedoctorLog) {
		super.delete(edSeedoctorLog);
	}
	
}