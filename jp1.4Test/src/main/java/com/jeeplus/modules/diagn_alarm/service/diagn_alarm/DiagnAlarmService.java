/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_alarm.service.diagn_alarm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.diagn_alarm.entity.diagn_alarm.DiagnAlarm;
import com.jeeplus.modules.diagn_alarm.mapper.diagn_alarm.DiagnAlarmMapper;

/**
 * 一键诊断告警信息Service
 * @author 姜森焱
 * @version 2020-03-18
 */
@Service
@Transactional(readOnly = true)
public class DiagnAlarmService extends CrudService<DiagnAlarmMapper, DiagnAlarm> {
	@Autowired
	public DiagnAlarmMapper alarmMapper;
	
	public DiagnAlarm get(String id) {
		return super.get(id);
	}
	
	public List<DiagnAlarm> findList(DiagnAlarm diagnAlarm) {
		return super.findList(diagnAlarm);
	}
	
	public Page<DiagnAlarm> findPage(Page<DiagnAlarm> page, DiagnAlarm diagnAlarm) {
		return super.findPage(page, diagnAlarm);
	}
	@Transactional(readOnly = false)
	public String updateconfirmWhether(DiagnAlarm DiagnAlarm) {
		return alarmMapper.updateconfirmWhether(DiagnAlarm);
	}
	@Transactional(readOnly = false)
	public void save(DiagnAlarm diagnAlarm) {
		super.save(diagnAlarm);
	}
	
	@Transactional(readOnly = false)
	public void delete(DiagnAlarm diagnAlarm) {
		super.delete(diagnAlarm);
	}
	
}