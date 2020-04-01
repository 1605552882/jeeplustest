/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.interface_config.service.interface_config;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.interface_config.entity.interface_config.InterfaceConfig;
import com.jeeplus.modules.interface_config.mapper.interface_config.InterfaceConfigMapper;

/**
 * 测试编码表Service
 * @author 姜森焱
 * @version 2020-02-04
 */
@Service
@Transactional(readOnly = true)
public class InterfaceConfigService extends CrudService<InterfaceConfigMapper, InterfaceConfig> {

	public InterfaceConfig get(String id) {
		return super.get(id);
	}
	
	public List<InterfaceConfig> findList(InterfaceConfig interfaceConfig) {
		return super.findList(interfaceConfig);
	}
	
	public Page<InterfaceConfig> findPage(Page<InterfaceConfig> page, InterfaceConfig interfaceConfig) {
		return super.findPage(page, interfaceConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(InterfaceConfig interfaceConfig) {
		super.save(interfaceConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(InterfaceConfig interfaceConfig) {
		super.delete(interfaceConfig);
	}
	
}