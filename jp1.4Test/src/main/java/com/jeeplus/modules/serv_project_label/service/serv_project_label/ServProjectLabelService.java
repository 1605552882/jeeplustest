/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.serv_project_label.service.serv_project_label;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.serv_project_label.entity.serv_project_label.ServProjectLabel;
import com.jeeplus.modules.serv_project_label.mapper.serv_project_label.ServProjectLabelMapper;

/**
 * 智能预处理配置Service
 * @author 姜森焱
 * @version 2020-02-20
 */
@Service
@Transactional(readOnly = true)
public class ServProjectLabelService extends CrudService<ServProjectLabelMapper, ServProjectLabel> {
	@Autowired
	private ServProjectLabelMapper labelMapper;

	public ServProjectLabel get(String id) {
		return super.get(id);
	}
	
	public List<ServProjectLabel> findList(ServProjectLabel servProjectLabel) {
		return super.findList(servProjectLabel);
	}
	
	public Page<ServProjectLabel> findPage(Page<ServProjectLabel> page, ServProjectLabel servProjectLabel) {
		return super.findPage(page, servProjectLabel);
	}
	
	@Transactional(readOnly = false)
	public void deleteserv(String serv) {
		labelMapper.deleteserv(serv);
	}
	
	@Transactional(readOnly = false)
	public void updateserv(String serv,String serv1) {
		labelMapper.updateserv(serv,serv1);
	}
	
	@Transactional(readOnly = false)
	public void save(ServProjectLabel servProjectLabel) {
		super.save(servProjectLabel);
	}
	
	@Transactional(readOnly = false)
	public void delete(ServProjectLabel servProjectLabel) {
		super.delete(servProjectLabel);
	}
	
}