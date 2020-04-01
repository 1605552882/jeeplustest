/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentreq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.treatmentreq.entity.SdTreatmentReqLog;
import com.jeeplus.modules.treatmentreq.mapper.SdTreatmentReqLogMapper;
import com.jeeplus.modules.treatmentreq.entity.SdTreatmentInviteLog;
import com.jeeplus.modules.treatmentreq.mapper.SdTreatmentInviteLogMapper;

/**
 * 求医记录Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class SdTreatmentReqLogService extends CrudService<SdTreatmentReqLogMapper, SdTreatmentReqLog> {

	@Autowired
	private SdTreatmentInviteLogMapper sdTreatmentInviteLogMapper;
	
	public SdTreatmentReqLog get(String id) {
		SdTreatmentReqLog sdTreatmentReqLog = super.get(id);
		sdTreatmentReqLog.setSdTreatmentInviteLogList(sdTreatmentInviteLogMapper.findList(new SdTreatmentInviteLog(sdTreatmentReqLog)));
		return sdTreatmentReqLog;
	}
	
	public List<SdTreatmentReqLog> findList(SdTreatmentReqLog sdTreatmentReqLog) {
		return super.findList(sdTreatmentReqLog);
	}
	
	public Page<SdTreatmentReqLog> findPage(Page<SdTreatmentReqLog> page, SdTreatmentReqLog sdTreatmentReqLog) {
		return super.findPage(page, sdTreatmentReqLog);
	}
	
	@Transactional(readOnly = false)
	public void save(SdTreatmentReqLog sdTreatmentReqLog) {
		super.save(sdTreatmentReqLog);
		for (SdTreatmentInviteLog sdTreatmentInviteLog : sdTreatmentReqLog.getSdTreatmentInviteLogList()){
			if (sdTreatmentInviteLog.getId() == null){
				continue;
			}
			if (SdTreatmentInviteLog.DEL_FLAG_NORMAL.equals(sdTreatmentInviteLog.getDelFlag())){
				if (StringUtils.isBlank(sdTreatmentInviteLog.getId())){
					sdTreatmentInviteLog.setReqid(sdTreatmentReqLog);
					sdTreatmentInviteLog.preInsert();
					sdTreatmentInviteLogMapper.insert(sdTreatmentInviteLog);
				}else{
					sdTreatmentInviteLog.preUpdate();
					sdTreatmentInviteLogMapper.update(sdTreatmentInviteLog);
				}
			}else{
				sdTreatmentInviteLogMapper.delete(sdTreatmentInviteLog);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SdTreatmentReqLog sdTreatmentReqLog) {
		super.delete(sdTreatmentReqLog);
		sdTreatmentInviteLogMapper.delete(new SdTreatmentInviteLog(sdTreatmentReqLog));
	}
	
}