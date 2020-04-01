/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.serv_project_label.mapper.serv_project_label;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.serv_project_label.entity.serv_project_label.ServProjectLabel;

/**
 * 智能预处理配置MAPPER接口
 * @author 姜森焱
 * @version 2020-02-20
 */
@MyBatisMapper
public interface ServProjectLabelMapper extends BaseMapper<ServProjectLabel> {
	public void updateserv(@Param("serv")String serv,@Param("serv1")String serv1);
	
	public void deleteserv(@Param("serv")String serv);
}