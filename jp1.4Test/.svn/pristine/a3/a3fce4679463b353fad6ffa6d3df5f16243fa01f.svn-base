/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.treatmentorder.entity.SdTreatmentOrderLog;
import com.jeeplus.modules.treatmentorder.mapper.SdTreatmentOrderLogMapper;

/**
 * 求医订单Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class SdTreatmentOrderLogService extends CrudService<SdTreatmentOrderLogMapper, SdTreatmentOrderLog> {

	public SdTreatmentOrderLog get(String id) {
		return super.get(id);
	}
	
	public List<SdTreatmentOrderLog> findList(SdTreatmentOrderLog sdTreatmentOrderLog) {
		return super.findList(sdTreatmentOrderLog);
	}
	
	public Page<SdTreatmentOrderLog> findPage(Page<SdTreatmentOrderLog> page, SdTreatmentOrderLog sdTreatmentOrderLog) {
		return super.findPage(page, sdTreatmentOrderLog);
	}
	
	@Transactional(readOnly = false)
	public void save(SdTreatmentOrderLog sdTreatmentOrderLog) {
		super.save(sdTreatmentOrderLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(SdTreatmentOrderLog sdTreatmentOrderLog) {
		super.delete(sdTreatmentOrderLog);
	}
	
}