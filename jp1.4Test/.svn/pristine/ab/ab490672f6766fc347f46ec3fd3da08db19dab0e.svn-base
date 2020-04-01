/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.patient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.patient.entity.SdPatientInf;
import com.jeeplus.modules.patient.mapper.SdPatientInfMapper;
import com.jeeplus.modules.patient.entity.SdPatientRatedLog;
import com.jeeplus.modules.patient.mapper.SdPatientRatedLogMapper;

/**
 * 病人基础信息Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class SdPatientInfService extends CrudService<SdPatientInfMapper, SdPatientInf> {

	@Autowired
	private SdPatientRatedLogMapper sdPatientRatedLogMapper;
	
	public SdPatientInf get(String id) {
		SdPatientInf sdPatientInf = super.get(id);
		sdPatientInf.setSdPatientRatedLogList(sdPatientRatedLogMapper.findList(new SdPatientRatedLog(sdPatientInf)));
		return sdPatientInf;
	}
	
	public List<SdPatientInf> findList(SdPatientInf sdPatientInf) {
		return super.findList(sdPatientInf);
	}
	
	public Page<SdPatientInf> findPage(Page<SdPatientInf> page, SdPatientInf sdPatientInf) {
		return super.findPage(page, sdPatientInf);
	}
	
	@Transactional(readOnly = false)
	public void save(SdPatientInf sdPatientInf) {
		super.save(sdPatientInf);
		for (SdPatientRatedLog sdPatientRatedLog : sdPatientInf.getSdPatientRatedLogList()){
			if (sdPatientRatedLog.getId() == null){
				continue;
			}
			if (SdPatientRatedLog.DEL_FLAG_NORMAL.equals(sdPatientRatedLog.getDelFlag())){
				if (StringUtils.isBlank(sdPatientRatedLog.getId())){
					sdPatientRatedLog.setPatientid(sdPatientInf);
					sdPatientRatedLog.preInsert();
					sdPatientRatedLogMapper.insert(sdPatientRatedLog);
				}else{
					sdPatientRatedLog.preUpdate();
					sdPatientRatedLogMapper.update(sdPatientRatedLog);
				}
			}else{
				sdPatientRatedLogMapper.delete(sdPatientRatedLog);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SdPatientInf sdPatientInf) {
		super.delete(sdPatientInf);
		sdPatientRatedLogMapper.delete(new SdPatientRatedLog(sdPatientInf));
	}
	
}