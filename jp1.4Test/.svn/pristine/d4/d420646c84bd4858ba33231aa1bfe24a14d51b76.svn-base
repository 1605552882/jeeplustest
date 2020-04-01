/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.web_log.service.web_log;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.web_log.entity.web_log.WebLog;
import com.jeeplus.modules.web_log.mapper.web_log.WebLogMapper;

/**
 * web请求日志Service
 * @author 姜森焱
 * @version 2019-12-26
 */
@Service
@Transactional(readOnly = true)
public class WebLogService extends CrudService<WebLogMapper, WebLog> {

	public WebLog get(String id) {
		return super.get(id);
	}
	
	public List<WebLog> findList(WebLog webLog) {
		return super.findList(webLog);
	}
	
	public Page<WebLog> findPage(Page<WebLog> page, WebLog webLog) {
		return super.findPage(page, webLog);
	}
	
	@Transactional(readOnly = false)
	public void save(WebLog webLog) {
		super.save(webLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(WebLog webLog) {
		super.delete(webLog);
	}
	
}