/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sourcestatistic.mapper;

import java.util.List;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.sourcestatistic.entity.Sourcestatistic;

/**
 * 工单申告来源统计MAPPER接口
 * @author zqb
 * @version 2019-12-06
 */
@MyBatisMapper
public interface SourcestatisticMapper extends BaseMapper<Sourcestatistic> {

	List<String> findSource(Sourcestatistic sourcestatistic);

	List<Sourcestatistic> findStatisticList(Sourcestatistic sourcestatistic);
	
}