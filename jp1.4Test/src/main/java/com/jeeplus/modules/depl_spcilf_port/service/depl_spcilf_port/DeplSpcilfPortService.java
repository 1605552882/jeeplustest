/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.depl_spcilf_port.service.depl_spcilf_port;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.depl_spcilf_port.entity.depl_spcilf_port.DeplSpcilfPort;
import com.jeeplus.modules.depl_spcilf_port.mapper.depl_spcilf_port.DeplSpcilfPortMapper;

/**
 * 机器部署端口Service
 * @author 姜森焱
 * @version 2020-03-04
 */
@Service
@Transactional(readOnly = true)
public class DeplSpcilfPortService extends CrudService<DeplSpcilfPortMapper, DeplSpcilfPort> {

	public DeplSpcilfPort get(String id) {
		return super.get(id);
	}
	
	public List<DeplSpcilfPort> findList(DeplSpcilfPort deplSpcilfPort) {
		return super.findList(deplSpcilfPort);
	}
	
	public Page<DeplSpcilfPort> findPage(Page<DeplSpcilfPort> page, DeplSpcilfPort deplSpcilfPort) {
		return super.findPage(page, deplSpcilfPort);
	}
	
	@Transactional(readOnly = false)
	public void save(DeplSpcilfPort deplSpcilfPort) {
		super.save(deplSpcilfPort);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeplSpcilfPort deplSpcilfPort) {
		super.delete(deplSpcilfPort);
	}
	
}