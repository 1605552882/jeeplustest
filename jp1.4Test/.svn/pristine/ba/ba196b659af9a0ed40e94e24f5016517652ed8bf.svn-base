/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.basis_coding_details.service.basis_coding_details;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.basis_coding_details.entity.basis_coding_details.BasisCodingDetails;
import com.jeeplus.modules.basis_coding_details.mapper.basis_coding_details.BasisCodingDetailsMapper;

/**
 * 编码明细表Service
 * @author 姜森焱
 * @version 2020-01-15
 */
@Service
@Transactional(readOnly = true)
public class BasisCodingDetailsService extends CrudService<BasisCodingDetailsMapper, BasisCodingDetails> {

	public BasisCodingDetails get(String id) {
		return super.get(id);
	}
	
	public List<BasisCodingDetails> findList(BasisCodingDetails basisCodingDetails) {
		return super.findList(basisCodingDetails);
	}
	
	public Page<BasisCodingDetails> findPage(Page<BasisCodingDetails> page, BasisCodingDetails basisCodingDetails) {
		return super.findPage(page, basisCodingDetails);
	}
	
	@Transactional(readOnly = false)
	public void save(BasisCodingDetails basisCodingDetails) {
		super.save(basisCodingDetails);
	}
	
	@Transactional(readOnly = false)
	public void delete(BasisCodingDetails basisCodingDetails) {
		super.delete(basisCodingDetails);
	}
	
}