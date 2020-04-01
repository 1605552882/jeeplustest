/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.statistichotword.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.statistichotword.entity.Statistichotword;
import com.jeeplus.modules.statistichotword.mapper.StatistichotwordMapper;

/**
 * 热词统计Service
 * @author lxy
 * @version 2019-11-14
 */
@Service
@Transactional(readOnly = true)
public class StatistichotwordService extends CrudService<StatistichotwordMapper, Statistichotword> {

	public Statistichotword get(String id) {
		return super.get(id);
	}
	
	public List<Statistichotword> findList(Statistichotword statistichotword) {
		return super.findList(statistichotword);
	}
	
	public Page<Statistichotword> findPage(Page<Statistichotword> page, Statistichotword statistichotword) {
		return super.findPage(page, statistichotword);
	}
	
	@Transactional(readOnly = false)
	public void save(Statistichotword statistichotword) {
		super.save(statistichotword);
	}
	
	@Transactional(readOnly = false)
	public void delete(Statistichotword statistichotword) {
		super.delete(statistichotword);
	}
	
}