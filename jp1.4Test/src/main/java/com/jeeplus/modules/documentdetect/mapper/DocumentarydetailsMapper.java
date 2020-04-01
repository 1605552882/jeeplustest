/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentdetect.mapper;

import java.util.List;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.documentdetect.entity.Documentarydetails;

/**
 * 工单详情MAPPER接口
 * @author liang
 * @version 2019-08-05
 */
@MyBatisMapper
public interface DocumentarydetailsMapper extends BaseMapper<Documentarydetails> {
	/**
	 * 获取id by 故障单号
	 * @param id
	 * @return
	 */
	public Documentarydetails getIdBySbillno(String sbillno);
	/**
	 * 获取重复退单
	 * @param repetitiveDocument
	 * @return
	 */
	public List<Documentarydetails> getRepetitiveBySbillno(Documentarydetails repetitiveDocument);
	
	/**
	 * 根据故障种类和故障时间获取原始工单列表
	 * @param documentarydetails
	 * @return
	 */
	public List<Documentarydetails> findFaultCategoryDataList(Documentarydetails documentarydetails);
	
	/**
	 * 获取热词查询信息
	 * @param documentarydetails
	 * @return
	 */
	public List<Documentarydetails> findHotList(Documentarydetails documentarydetails);
	
	
}