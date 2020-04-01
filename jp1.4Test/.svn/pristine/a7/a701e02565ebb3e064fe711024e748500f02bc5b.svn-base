package com.jeeplus.common.base;

import java.io.Serializable;
import java.util.List;

public interface BaseService {
	
	public Object getEntityByPrimaryKey(Class<?> clazz, Serializable primaryKey);
	
	public String baseItem(Class<?> clazz, String operatorMode, String primaryKey, Object entity) throws Exception;
	
	public List<?> getListByHql(String hql) throws Exception;
	
	public List<?> getListByHqlByPage(String hql, int pageSize) throws Exception;
	
}
