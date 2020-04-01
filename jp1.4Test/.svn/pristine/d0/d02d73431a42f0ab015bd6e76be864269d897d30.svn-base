/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.basis_coding.service.basis_coding;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.basis_coding.entity.basis_coding.BasisCoding;
import com.jeeplus.modules.basis_coding.mapper.basis_coding.BasisCodingMapper;

/**
 * 编码表Service
 * @author 姜森焱
 * @version 2020-01-15
 */
@Service
@Transactional(readOnly = true)
public class BasisCodingService extends CrudService<BasisCodingMapper, BasisCoding> {

	public BasisCoding get(String id) {
		return super.get(id);
	}
	
	public List<BasisCoding> findList(BasisCoding basisCoding) {
		return super.findList(basisCoding);
	}
	
	public Page<BasisCoding> findPage(Page<BasisCoding> page, BasisCoding basisCoding) {
		return super.findPage(page, basisCoding);
	}
	
	@Transactional(readOnly = false)
	public void save(BasisCoding basisCoding) {
		super.save(basisCoding);
	}
	
	@Transactional(readOnly = false)
	public void delete(BasisCoding basisCoding) {
		super.delete(basisCoding);
	}
	
}