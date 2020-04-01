/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.web_log.mapper.web_log;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.web_log.entity.web_log.WebLog;

/**
 * web请求日志MAPPER接口
 * @author 姜森焱
 * @version 2019-12-26
 */
@MyBatisMapper
public interface WebLogMapper extends BaseMapper<WebLog> {
	
}