/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_prject.service.diagn_prject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.diagn_prject.entity.diagn_prject.DiagnPrject;
import com.jeeplus.modules.diagn_prject.mapper.diagn_prject.DiagnPrjectMapper;

/**
 * 一件诊断子表Service
 * @author 姜森焱
 * @version 2020-02-18
 */
@Service
@Transactional(readOnly = true)
public class DiagnPrjectService extends CrudService<DiagnPrjectMapper, DiagnPrject> {
	
	@Autowired
	private DiagnPrjectMapper diagnPrjectMapper;
	
	public DiagnPrject get(String id) {
		return super.get(id);
	}
	
	public List<DiagnPrject> findList(DiagnPrject diagnPrject) {
		return super.findList(diagnPrject);
	}
	
	public Page<DiagnPrject> findPage(Page<DiagnPrject> page, DiagnPrject diagnPrject) {
		return super.findPage(page, diagnPrject);
	}
	@Transactional(readOnly = false)
	public int updateprojectId() {
		return diagnPrjectMapper.updateprojectId();
	}
	@Transactional(readOnly = false)
	public void insertproject(String projectId,String servNameId) {
		diagnPrjectMapper.insertproject(projectId, servNameId);
	}
	@Transactional(readOnly = false)
	public void save(DiagnPrject diagnPrject) {
		super.save(diagnPrject);
	}
	
	@Transactional(readOnly = false)
	public void delete(DiagnPrject diagnPrject) {
		super.delete(diagnPrject);
	}
	
}