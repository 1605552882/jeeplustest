/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv.mapper.diagn_serv;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.diagn_serv.entity.diagn_serv.DiagnServ;

/**
 * 一件诊断主表MAPPER接口
 * @author 姜森焱
 * @version 2020-02-18
 */
@MyBatisMapper
public interface DiagnServMapper extends BaseMapper<DiagnServ> {
	public void updateservid();
}