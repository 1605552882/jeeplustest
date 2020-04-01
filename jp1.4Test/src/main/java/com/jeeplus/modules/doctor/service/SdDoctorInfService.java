/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.doctor.entity.SdDoctorInf;
import com.jeeplus.modules.doctor.mapper.SdDoctorInfMapper;
import com.jeeplus.modules.doctor.entity.SdDoctorOfferLog;
import com.jeeplus.modules.doctor.mapper.SdDoctorOfferLogMapper;
import com.jeeplus.modules.doctor.entity.SdDoctorRatedLog;
import com.jeeplus.modules.doctor.mapper.SdDoctorRatedLogMapper;

/**
 * 医生信息Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class SdDoctorInfService extends CrudService<SdDoctorInfMapper, SdDoctorInf> {

	@Autowired
	private SdDoctorOfferLogMapper sdDoctorOfferLogMapper;
	@Autowired
	private SdDoctorRatedLogMapper sdDoctorRatedLogMapper;
	
	public SdDoctorInf get(String id) {
		SdDoctorInf sdDoctorInf = super.get(id);
		sdDoctorInf.setSdDoctorOfferLogList(sdDoctorOfferLogMapper.findList(new SdDoctorOfferLog(sdDoctorInf)));
		sdDoctorInf.setSdDoctorRatedLogList(sdDoctorRatedLogMapper.findList(new SdDoctorRatedLog(sdDoctorInf)));
		return sdDoctorInf;
	}
	
	public List<SdDoctorInf> findList(SdDoctorInf sdDoctorInf) {
		return super.findList(sdDoctorInf);
	}
	
	public Page<SdDoctorInf> findPage(Page<SdDoctorInf> page, SdDoctorInf sdDoctorInf) {
		return super.findPage(page, sdDoctorInf);
	}
	
	@Transactional(readOnly = false)
	public void save(SdDoctorInf sdDoctorInf) {
		super.save(sdDoctorInf);
		for (SdDoctorOfferLog sdDoctorOfferLog : sdDoctorInf.getSdDoctorOfferLogList()){
			if (sdDoctorOfferLog.getId() == null){
				continue;
			}
			if (SdDoctorOfferLog.DEL_FLAG_NORMAL.equals(sdDoctorOfferLog.getDelFlag())){
				if (StringUtils.isBlank(sdDoctorOfferLog.getId())){
					sdDoctorOfferLog.setDoctorid(sdDoctorInf);
					sdDoctorOfferLog.preInsert();
					sdDoctorOfferLogMapper.insert(sdDoctorOfferLog);
				}else{
					sdDoctorOfferLog.preUpdate();
					sdDoctorOfferLogMapper.update(sdDoctorOfferLog);
				}
			}else{
				sdDoctorOfferLogMapper.delete(sdDoctorOfferLog);
			}
		}
		for (SdDoctorRatedLog sdDoctorRatedLog : sdDoctorInf.getSdDoctorRatedLogList()){
			if (sdDoctorRatedLog.getId() == null){
				continue;
			}
			if (SdDoctorRatedLog.DEL_FLAG_NORMAL.equals(sdDoctorRatedLog.getDelFlag())){
				if (StringUtils.isBlank(sdDoctorRatedLog.getId())){
					sdDoctorRatedLog.setDoctorid(sdDoctorInf);
					sdDoctorRatedLog.preInsert();
					sdDoctorRatedLogMapper.insert(sdDoctorRatedLog);
				}else{
					sdDoctorRatedLog.preUpdate();
					sdDoctorRatedLogMapper.update(sdDoctorRatedLog);
				}
			}else{
				sdDoctorRatedLogMapper.delete(sdDoctorRatedLog);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SdDoctorInf sdDoctorInf) {
		super.delete(sdDoctorInf);
		sdDoctorOfferLogMapper.delete(new SdDoctorOfferLog(sdDoctorInf));
		sdDoctorRatedLogMapper.delete(new SdDoctorRatedLog(sdDoctorInf));
	}
	
}