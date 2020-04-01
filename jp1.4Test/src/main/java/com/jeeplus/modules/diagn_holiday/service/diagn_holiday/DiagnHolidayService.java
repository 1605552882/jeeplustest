/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_holiday.service.diagn_holiday;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.diagn_holiday.entity.diagn_holiday.DiagnHoliday;
import com.jeeplus.modules.diagn_holiday.mapper.diagn_holiday.DiagnHolidayMapper;

/**
 * 节假日配置Service
 * @author 姜森焱
 * @version 2020-02-26
 */
@Service
@Transactional(readOnly = true)
public class DiagnHolidayService extends CrudService<DiagnHolidayMapper, DiagnHoliday> {

	public DiagnHoliday get(String id) {
		return super.get(id);
	}
	
	public List<DiagnHoliday> findList(DiagnHoliday diagnHoliday) {
		return super.findList(diagnHoliday);
	}
	
	public Page<DiagnHoliday> findPage(Page<DiagnHoliday> page, DiagnHoliday diagnHoliday) {
		return super.findPage(page, diagnHoliday);
	}
	
	@Transactional(readOnly = false)
	public void save(DiagnHoliday diagnHoliday) {
		super.save(diagnHoliday);
	}
	
	@Transactional(readOnly = false)
	public void delete(DiagnHoliday diagnHoliday) {
		super.delete(diagnHoliday);
	}
	
}