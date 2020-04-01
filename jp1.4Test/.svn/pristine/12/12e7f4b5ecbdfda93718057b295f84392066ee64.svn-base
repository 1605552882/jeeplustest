/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.checkresult.mapper;

import java.util.List;
import java.util.Map;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.checkresult.entity.Checkresult;

/**
 * 人工检测结果MAPPER接口
 * @author lxy
 * @version 2019-08-19
 */
@MyBatisMapper
public interface CheckresultMapper extends BaseMapper<Checkresult> {
	public List<Checkresult> getBySbillno(String sbillno);

	public List<Map<String, Object>> getTopThreeProblems(Checkresult checkresult);
}