/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentdetect.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.documentdetect.entity.Feedback;

/**
 * 单据反馈信息MAPPER接口
 * @author liang
 * @version 2019-08-05
 */
@MyBatisMapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
	
	public Integer findcount(@Param("begin")Date begin, @Param("end")Date end);
	
}