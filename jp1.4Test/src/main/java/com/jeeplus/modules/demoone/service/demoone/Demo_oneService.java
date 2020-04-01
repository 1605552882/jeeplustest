/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.demoone.service.demoone;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.demoone.entity.demoone.Demo_one;
import com.jeeplus.modules.demoone.mapper.demoone.Demo_oneMapper;

/**
 * 作为测试Service
 * @author 姜森焱
 * @version 2019-09-29
 */
@Service
@Transactional(readOnly = true)
public class Demo_oneService extends CrudService<Demo_oneMapper, Demo_one> {

	public Demo_one get(String id) {
		return super.get(id);
	}
	
	public List<Demo_one> findList(Demo_one demo_one) {
		return super.findList(demo_one);
	}
	
	public Page<Demo_one> findPage(Page<Demo_one> page, Demo_one demo_one) {
		return super.findPage(page, demo_one);
	}
	
	@Transactional(readOnly = false)
	public void save(Demo_one demo_one) {
		super.save(demo_one);
	}
	
	@Transactional(readOnly = false)
	public void delete(Demo_one demo_one) {
		super.delete(demo_one);
	}
	
}