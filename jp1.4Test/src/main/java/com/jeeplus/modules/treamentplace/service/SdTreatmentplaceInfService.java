/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treamentplace.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.treamentplace.entity.SdTreatmentplaceInf;
import com.jeeplus.modules.treamentplace.mapper.SdTreatmentplaceInfMapper;

/**
 * 治疗地点Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class SdTreatmentplaceInfService extends CrudService<SdTreatmentplaceInfMapper, SdTreatmentplaceInf> {

	public SdTreatmentplaceInf get(String id) {
		return super.get(id);
	}
	
	public List<SdTreatmentplaceInf> findList(SdTreatmentplaceInf sdTreatmentplaceInf) {
		return super.findList(sdTreatmentplaceInf);
	}
	
	public Page<SdTreatmentplaceInf> findPage(Page<SdTreatmentplaceInf> page, SdTreatmentplaceInf sdTreatmentplaceInf) {
		return super.findPage(page, sdTreatmentplaceInf);
	}
	
	@Transactional(readOnly = false)
	public void save(SdTreatmentplaceInf sdTreatmentplaceInf) {
		super.save(sdTreatmentplaceInf);
	}
	
	@Transactional(readOnly = false)
	public void delete(SdTreatmentplaceInf sdTreatmentplaceInf) {
		super.delete(sdTreatmentplaceInf);
	}
	
}