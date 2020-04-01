/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mscid_config.service.mscid_config;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.mscid_config.entity.mscid_config.MscidConfig;
import com.jeeplus.modules.mscid_config.mapper.mscid_config.MscidConfigMapper;

/**
 * MSCID配置Service
 * @author 姜森焱
 * @version 2020-02-09
 */
@Service
@Transactional(readOnly = true)
public class MscidConfigService extends CrudService<MscidConfigMapper, MscidConfig> {

	public MscidConfig get(String id) {
		return super.get(id);
	}
	
	public List<MscidConfig> findList(MscidConfig mscidConfig) {
		return super.findList(mscidConfig);
	}
	
	public Page<MscidConfig> findPage(Page<MscidConfig> page, MscidConfig mscidConfig) {
		return super.findPage(page, mscidConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(MscidConfig mscidConfig) {
		super.save(mscidConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(MscidConfig mscidConfig) {
		super.delete(mscidConfig);
	}
	
}