/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.depl_spcilf_ip.service.depl_spcilf_ip;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.depl_spcilf_ip.entity.depl_spcilf_ip.DeplSpcilfIp;
import com.jeeplus.modules.depl_spcilf_ip.mapper.depl_spcilf_ip.DeplSpcilfIpMapper;

/**
 * 机器部署信息Service
 * @author 姜森焱
 * @version 2020-03-04
 */
@Service
@Transactional(readOnly = true)
public class DeplSpcilfIpService extends CrudService<DeplSpcilfIpMapper, DeplSpcilfIp> {

	public DeplSpcilfIp get(String id) {
		return super.get(id);
	}
	
	public List<DeplSpcilfIp> findList(DeplSpcilfIp deplSpcilfIp) {
		return super.findList(deplSpcilfIp);
	}
	
	public Page<DeplSpcilfIp> findPage(Page<DeplSpcilfIp> page, DeplSpcilfIp deplSpcilfIp) {
		return super.findPage(page, deplSpcilfIp);
	}
	
	@Transactional(readOnly = false)
	public void save(DeplSpcilfIp deplSpcilfIp) {
		super.save(deplSpcilfIp);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeplSpcilfIp deplSpcilfIp) {
		super.delete(deplSpcilfIp);
	}
	
}