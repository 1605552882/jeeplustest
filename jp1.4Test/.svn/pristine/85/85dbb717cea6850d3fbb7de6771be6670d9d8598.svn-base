/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.personalfunction.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.personalfunction.entity.EdPersonalfunctionInf;
import com.jeeplus.modules.personalfunction.mapper.EdPersonalfunctionInfMapper;

/**
 * 个人功能评估Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdPersonalfunctionInfService extends CrudService<EdPersonalfunctionInfMapper, EdPersonalfunctionInf> {

	public EdPersonalfunctionInf get(String id) {
		return super.get(id);
	}
	
	public List<EdPersonalfunctionInf> findList(EdPersonalfunctionInf edPersonalfunctionInf) {
		return super.findList(edPersonalfunctionInf);
	}
	
	public Page<EdPersonalfunctionInf> findPage(Page<EdPersonalfunctionInf> page, EdPersonalfunctionInf edPersonalfunctionInf) {
		return super.findPage(page, edPersonalfunctionInf);
	}
	
	@Transactional(readOnly = false)
	public void save(EdPersonalfunctionInf edPersonalfunctionInf) {
		super.save(edPersonalfunctionInf);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdPersonalfunctionInf edPersonalfunctionInf) {
		super.delete(edPersonalfunctionInf);
	}
	
}