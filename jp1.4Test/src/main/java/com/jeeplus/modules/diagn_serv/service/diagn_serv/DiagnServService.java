/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv.service.diagn_serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.diagn_serv.entity.diagn_serv.DiagnServ;
import com.jeeplus.modules.diagn_serv.mapper.diagn_serv.DiagnServMapper;

/**
 * 一件诊断主表Service
 * @author 姜森焱
 * @version 2020-02-18
 */
@Service
@Transactional(readOnly = true)
public class DiagnServService extends CrudService<DiagnServMapper, DiagnServ> {
	
	@Autowired
	private DiagnServMapper diagnServMapper;

	public DiagnServ get(String id) {
		return super.get(id);
	}
	
	public List<DiagnServ> findList(DiagnServ diagnServ) {
		return super.findList(diagnServ);
	}
	
	public Page<DiagnServ> findPage(Page<DiagnServ> page, DiagnServ diagnServ) {
		return super.findPage(page, diagnServ);
	}
	@Transactional(readOnly = false)
	public void updateservid() {
		diagnServMapper.updateservid();
	}
	@Transactional(readOnly = false)
	public void save(DiagnServ diagnServ) {
		super.save(diagnServ);
	}
	
	@Transactional(readOnly = false)
	public void delete(DiagnServ diagnServ) {
		super.delete(diagnServ);
	}
	
}