/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sourcestatistic.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.sourcestatistic.entity.Sourcestatistic;
import com.jeeplus.modules.sourcestatistic.mapper.SourcestatisticMapper;

/**
 * 工单申告来源统计Service
 * @author zqb
 * @version 2019-12-06
 */
@Service
@Transactional(readOnly = true)
public class SourcestatisticService extends CrudService<SourcestatisticMapper, Sourcestatistic> {
	
	@Autowired
	private SourcestatisticMapper sourcestatisticMapper;

	public Sourcestatistic get(String id) {
		return super.get(id);
	}
	
	public List<Sourcestatistic> findList(Sourcestatistic sourcestatistic) {
		return super.findList(sourcestatistic);
	}
	
	public Page<Sourcestatistic> findPage(Page<Sourcestatistic> page, Sourcestatistic sourcestatistic) {
		return super.findPage(page, sourcestatistic);
	}
	
	@Transactional(readOnly = false)
	public void save(Sourcestatistic sourcestatistic) {
		super.save(sourcestatistic);
	}
	
	@Transactional(readOnly = false)
	public void delete(Sourcestatistic sourcestatistic) {
		super.delete(sourcestatistic);
	}

	public Map<String,Object> findStatisticList(Sourcestatistic sourcestatistic) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("queryParams",sourcestatistic);//查询参数

		//日期设置
		Date begin = sourcestatistic.getBeginCreateDate();//2019-09-03 00:00:00
		Date end = sourcestatistic.getEndCreateDate();//2019-09-06 23:59:59
		List<Date> dateList = null;
		String timeFlag = sourcestatistic.getTimeFlag();
		dateList = getDate(begin, end, timeFlag);
		//X轴数组
		List<String> datefmt = new ArrayList<String>();
		SimpleDateFormat dateFormat = null;
		if("1".equals(timeFlag)) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			for(int j=0; j<dateList.size(); j++) {
				datefmt.add(dateFormat.format(dateList.get(j)));
			}
		}else if("2".equals(timeFlag)) {
			dateFormat = new SimpleDateFormat("yyyy-MM");
			for(int j=0; j<dateList.size(); j++) {
				datefmt.add(dateFormat.format(dateList.get(j)));
			}
		}
		result.put("datefmt", datefmt);

		//准备查询
		//例如结束日期是2019-10，则查询时改为2019-10-31 23:59:59，即要拿出10月的数据
		if("2".equals(timeFlag)) {
			Calendar nextMonth = Calendar.getInstance();
			nextMonth.setTime(sourcestatistic.getEndCreateDate());
			nextMonth.add(Calendar.MONTH, 1);
			nextMonth.add(Calendar.SECOND, -1);
			sourcestatistic.setEndCreateDate(nextMonth.getTime());
		}

		//获取所有来源(前提是来源的数量不为零)
		List<String> sourceList = sourcestatisticMapper.findSource(sourcestatistic);
		result.put("sourceList", sourceList);
		

		List<Sourcestatistic> list = sourcestatisticMapper.findStatisticList(sourcestatistic);
		
		//初始化Y轴数据
		int dateSize = dateList.size();
		int[][] data = new int[sourceList.size()][dateSize];
		
		//放数据
		for(int i=0; i<list.size(); i++) {//全部数据
			Sourcestatistic item = list.get(i);
			Date createDate = item.getCreateDate();
			int times = Integer.valueOf(item.getTimes());
			//申告来源
			String sourceDB = item.getSource();
			for(int j=0; j<dateList.size(); j++) {
				if("1".equals(timeFlag) && createDate.getTime()-dateList.get(j).getTime()>=0 && createDate.getTime()-dateList.get(j).getTime()<24*60*60*1000) {
					for(int n=0; n<sourceList.size(); n++) {
						if(sourceDB.equals(sourceList.get(n))) {
							data[n][j] += times;
						}
					}
				}else if("2".equals(timeFlag)) {
					Calendar calCre = Calendar.getInstance();
					Calendar calLis = Calendar.getInstance();
					calCre.setTime(createDate);
					calLis.setTime(dateList.get(j));
					if(calCre.get(Calendar.YEAR)==calLis.get(Calendar.YEAR) && calCre.get(Calendar.MONTH)==calLis.get(Calendar.MONTH)) {
						for(int n=0; n<sourceList.size(); n++) {
							if(sourceDB.equals(sourceList.get(n))) {
								data[n][j] += times;
								break;
							}
						}
					}
				}
			}
		}
		result.put("data", data);
		return result;
	}
	/**
	 * 日期分割
	 * @param begin
	 * @param end
	 * @param timeFlag
	 * @return
	 */
	public List<Date> getDate(Date begin, Date end, String timeFlag){

		List<Date> allDate = new ArrayList<Date>();

	    allDate.add(begin);
	    Calendar calBegin = Calendar.getInstance();
	    // 使用给定的 Date 设置此 Calendar 的时间
	    calBegin.setTime(begin);
	    Calendar calEnd = Calendar.getInstance();
	    // 使用给定的 Date 设置此 Calendar 的时间
	    calEnd.setTime(end);
	    if("2".equals(timeFlag)) {
	    	calEnd.set(Calendar.DAY_OF_MONTH,1);
	    }
	    calEnd.set(Calendar.HOUR_OF_DAY,0);
	    calEnd.set(Calendar.MINUTE,0);
	    calEnd.set(Calendar.SECOND,0);
	    end = calEnd.getTime();
	    // 此日期是否在指定日期之后
	    while (end.after(calBegin.getTime())) {
	        // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	        if("1".equals(timeFlag)) {//日
	        	calBegin.add(Calendar.DAY_OF_MONTH, 1);
	        }else if("2".equals(timeFlag)) {//月
	        	calBegin.add(Calendar.MONTH, 1);
	        }
	        allDate.add(calBegin.getTime());
	    }
	    return allDate;
	}	
}