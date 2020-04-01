/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_prject.mapper.diagn_prject;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.diagn_prject.entity.diagn_prject.DiagnPrject;

/**
 * 一件诊断子表MAPPER接口
 * @author 姜森焱
 * @version 2020-02-18
 */
@MyBatisMapper
public interface DiagnPrjectMapper extends BaseMapper<DiagnPrject> {
	public void insertproject(@Param("projectId") String projectId,@Param("servNameId") String servNameId);
	public int updateprojectId();
}