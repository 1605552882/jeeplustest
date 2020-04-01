/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.elder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.elder.entity.EdElderInf;
import com.jeeplus.modules.elder.mapper.EdElderInfMapper;
import com.jeeplus.modules.elder.entity.EdFamilyInf;
import com.jeeplus.modules.elder.mapper.EdFamilyInfMapper;

/**
 * 长者基本资料管理Service
 * @author lukbob
 * @version 2018-11-12
 */
@Service
@Transactional(readOnly = true)
public class EdElderInfService extends CrudService<EdElderInfMapper, EdElderInf> {

	@Autowired
	private EdFamilyInfMapper edFamilyInfMapper;
	
	public EdElderInf get(String id) {
		EdElderInf edElderInf = super.get(id);
		edElderInf.setEdFamilyInfList(edFamilyInfMapper.findList(new EdFamilyInf(edElderInf)));
		return edElderInf;
	}
	
	public List<EdElderInf> findList(EdElderInf edElderInf) {
		return super.findList(edElderInf);
	}
	
	public Page<EdElderInf> findPage(Page<EdElderInf> page, EdElderInf edElderInf) {
		return super.findPage(page, edElderInf);
	}
	
	@Transactional(readOnly = false)
	public void save(EdElderInf edElderInf) {
		super.save(edElderInf);
		for (EdFamilyInf edFamilyInf : edElderInf.getEdFamilyInfList()){
			if (edFamilyInf.getId() == null){
				continue;
			}
			if (EdFamilyInf.DEL_FLAG_NORMAL.equals(edFamilyInf.getDelFlag())){
				if (StringUtils.isBlank(edFamilyInf.getId())){
					edFamilyInf.setElderid(edElderInf);
					edFamilyInf.preInsert();
					edFamilyInfMapper.insert(edFamilyInf);
				}else{
					edFamilyInf.preUpdate();
					edFamilyInfMapper.update(edFamilyInf);
				}
			}else{
				edFamilyInfMapper.delete(edFamilyInf);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(EdElderInf edElderInf) {
		super.delete(edElderInf);
		edFamilyInfMapper.delete(new EdFamilyInf(edElderInf));
	}
	
}