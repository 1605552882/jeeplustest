/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.system_parameter.service.system_parameter;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.system_parameter.entity.system_parameter.SystemParameter;
import com.jeeplus.modules.system_parameter.mapper.system_parameter.SystemParameterMapper;

/**
 * 系统参数表Service
 * @author 姜森焱
 * @version 2020-02-09
 */
@Service
@Transactional(readOnly = true)
public class SystemParameterService extends CrudService<SystemParameterMapper, SystemParameter> {

	public SystemParameter get(String id) {
		return super.get(id);
	}
	
	public List<SystemParameter> findList(SystemParameter systemParameter) {
		return super.findList(systemParameter);
	}
	
	public Page<SystemParameter> findPage(Page<SystemParameter> page, SystemParameter systemParameter) {
		return super.findPage(page, systemParameter);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemParameter systemParameter) {
		super.save(systemParameter);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemParameter systemParameter) {
		super.delete(systemParameter);
	}
	
}