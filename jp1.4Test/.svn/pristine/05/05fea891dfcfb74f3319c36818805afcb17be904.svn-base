/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.nutrition.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.nutrition.entity.EdNutritionLog;
import com.jeeplus.modules.nutrition.mapper.EdNutritionLogMapper;

/**
 * 营养筛选问卷Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdNutritionLogService extends CrudService<EdNutritionLogMapper, EdNutritionLog> {

	public EdNutritionLog get(String id) {
		return super.get(id);
	}
	
	public List<EdNutritionLog> findList(EdNutritionLog edNutritionLog) {
		return super.findList(edNutritionLog);
	}
	
	public Page<EdNutritionLog> findPage(Page<EdNutritionLog> page, EdNutritionLog edNutritionLog) {
		return super.findPage(page, edNutritionLog);
	}
	
	@Transactional(readOnly = false)
	public void save(EdNutritionLog edNutritionLog) {
		super.save(edNutritionLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(EdNutritionLog edNutritionLog) {
		super.delete(edNutritionLog);
	}
	
}