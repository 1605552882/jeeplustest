/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hlr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.hlr.entity.HlrInfoFields;
import com.jeeplus.modules.hlr.mapper.HlrInfoFieldsMapper;

/**
 * hlr信息管理Service
 * @author 钟晖
 * @version 2019-10-25
 */
@Service
@Transactional(readOnly = true)
public class HlrInfoFieldsService extends CrudService<HlrInfoFieldsMapper, HlrInfoFields> {

	public HlrInfoFields get(String id) {
		return super.get(id);
	}
	
	public List<HlrInfoFields> findList(HlrInfoFields hlrInfoFields) {
		return super.findList(hlrInfoFields);
	}
	
	public Page<HlrInfoFields> findPage(Page<HlrInfoFields> page, HlrInfoFields hlrInfoFields) {
		return super.findPage(page, hlrInfoFields);
	}
	
	@Transactional(readOnly = false)
	public void save(HlrInfoFields hlrInfoFields) {
		super.save(hlrInfoFields);
	}
	
	@Transactional(readOnly = false)
	public void delete(HlrInfoFields hlrInfoFields) {
		super.delete(hlrInfoFields);
	}
	
}