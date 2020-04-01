/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hotword.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.hotword.entity.Hotword;
import com.jeeplus.modules.hotword.mapper.HotwordMapper;

/**
 * 热词Service
 * @author lxy
 * @version 2019-11-14
 */
@Service
@Transactional(readOnly = true)
public class HotwordService extends CrudService<HotwordMapper, Hotword> {

	public Hotword get(String id) {
		return super.get(id);
	}
	
	public List<Hotword> findList(Hotword hotword) {
		return super.findList(hotword);
	}
	
	public Page<Hotword> findPage(Page<Hotword> page, Hotword hotword) {
		return super.findPage(page, hotword);
	}
	
	@Transactional(readOnly = false)
	public void save(Hotword hotword) {
		super.save(hotword);
	}
	
	@Transactional(readOnly = false)
	public void delete(Hotword hotword) {
		super.delete(hotword);
	}
	
}