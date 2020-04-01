/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.checkresult.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.mapper.CheckresultMapper;

/**
 * 人工检测结果Service
 * @author lxy
 * @version 2019-08-19
 */
@Service
public class CheckresultService extends CrudService<CheckresultMapper, Checkresult> {
	
	@Autowired
	private CheckresultMapper checkresultMapper;
	
	public List<Checkresult> getBySbillno(String sbillno) {
		List<Checkresult> checkresult = checkresultMapper.getBySbillno(sbillno);
		return checkresult;
	}

	public Checkresult get(String id) {
		return super.get(id);
	}
	
	public List<Checkresult> findList(Checkresult checkresult) {
		return super.findList(checkresult);
	}
	
	public Page<Checkresult> findPage(Page<Checkresult> page, Checkresult checkresult) {
		return super.findPage(page, checkresult);
	}
	
	@Transactional(readOnly = false)
	public void save(Checkresult checkresult) {
		super.save(checkresult);
	}
	
	@Transactional(readOnly = false)
	public void delete(Checkresult checkresult) {
		super.delete(checkresult);
	}

	public List<Map<String,Object>> getTopThreeProblems(Checkresult checkresult) {
		return checkresultMapper.getTopThreeProblems(checkresult);
	}
	
}