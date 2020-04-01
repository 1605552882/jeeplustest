/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_alarm.mapper.diagn_alarm;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.diagn_alarm.entity.diagn_alarm.DiagnAlarm;

/**
 * 一键诊断告警信息MAPPER接口
 * @author 姜森焱
 * @version 2020-03-18
 */
@MyBatisMapper
public interface DiagnAlarmMapper extends BaseMapper<DiagnAlarm> {
	public String updateconfirmWhether(DiagnAlarm DiagnAlarm);
}