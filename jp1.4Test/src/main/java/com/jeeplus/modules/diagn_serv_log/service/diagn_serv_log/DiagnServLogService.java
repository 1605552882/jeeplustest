/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv_log.service.diagn_serv_log;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.diagn_serv_log.entity.diagn_serv_log.DiagnServLog;
import com.jeeplus.modules.diagn_serv_log.mapper.diagn_serv_log.DiagnServLogMapper;

/**
 * 一键诊断详细信息Service
 * @author 姜森焱
 * @version 2020-02-28
 */
@Service
@Transactional(readOnly = true)
public class DiagnServLogService extends CrudService<DiagnServLogMapper, DiagnServLog> {

	public DiagnServLog get(String id) {
		return super.get(id);
	}
	
	public List<DiagnServLog> findList(DiagnServLog diagnServLog) {
		return super.findList(diagnServLog);
	}
	
	public Page<DiagnServLog> findPage(Page<DiagnServLog> page, DiagnServLog diagnServLog) {
		return super.findPage(page, diagnServLog);
	}
	
	@Transactional(readOnly = false)
	public void save(DiagnServLog diagnServLog) {
		super.save(diagnServLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(DiagnServLog diagnServLog) {
		super.delete(diagnServLog);
	}
	
}