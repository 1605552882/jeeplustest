/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.groupdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.groupdetails.entity.Groupdetails;
import com.jeeplus.modules.groupdetails.mapper.GroupdetailsMapper;

/**
 * 班组详情Service
 * @author lxy
 * @version 2019-08-28
 */
@Service
@Transactional(readOnly = true)
public class GroupdetailsService extends CrudService<GroupdetailsMapper, Groupdetails> {
	
	@Autowired
	GroupdetailsMapper  mapper;
	
	public Groupdetails getbyName(String name) {
		return mapper.getbyName(name);
	}

	public Groupdetails get(String id) {
		return super.get(id);
	}
	
	public List<Groupdetails> findList(Groupdetails groupdetails) {
		return super.findList(groupdetails);
	}
	
	public Page<Groupdetails> findPage(Page<Groupdetails> page, Groupdetails groupdetails) {
		return super.findPage(page, groupdetails);
	}
	
	@Transactional(readOnly = false)
	public void save(Groupdetails groupdetails) {
		super.save(groupdetails);
	}
	
	@Transactional(readOnly = false)
	public void delete(Groupdetails groupdetails) {
		super.delete(groupdetails);
	}
	
}