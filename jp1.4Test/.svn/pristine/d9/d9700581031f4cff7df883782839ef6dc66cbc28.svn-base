/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.faultcategorystatistic.mapper;

import java.util.List;
import java.util.Map;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.faultcategorystatistic.entity.Faultcategorystatistic;

/**
 * 故障种类统计表MAPPER接口
 * @author lxy
 * @version 2019-10-15
 */
@MyBatisMapper
public interface FaultcategorystatisticMapper extends BaseMapper<Faultcategorystatistic> {

	List<String> findFaultcategorysResult();

	List<Faultcategorystatistic> findMonth(Faultcategorystatistic faultcategorystatistic);

	List<Faultcategorystatistic> findGroupList(Faultcategorystatistic faultcategorystatistic);

	List<Faultcategorystatistic> findCityData(Faultcategorystatistic faultcategorystatistic);
	
	List<Faultcategorystatistic> findFaultCategoryData(Faultcategorystatistic faultcategorystatistic);

	List<Faultcategorystatistic> findStatisticList(Faultcategorystatistic faultcategorystatistic);

	List<String> findCity(Faultcategorystatistic faultcategorystatistic);

}