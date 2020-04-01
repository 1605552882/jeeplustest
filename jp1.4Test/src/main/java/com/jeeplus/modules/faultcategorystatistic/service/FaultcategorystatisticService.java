/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.faultcategorystatistic.service;

import java.text.DecimalFormat;
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
import com.jeeplus.modules.faultcategorystatistic.entity.Faultcategorystatistic;
import com.jeeplus.modules.faultcategorystatistic.mapper.FaultcategorystatisticMapper;
import com.jeeplus.modules.sys.entity.DictValue;
import com.jeeplus.modules.sys.utils.DictUtils;

/**
 * 故障种类统计表Service
 * @author lxy
 * @version 2019-10-15
 */
@Service
@Transactional(readOnly = true)
public class FaultcategorystatisticService extends CrudService<FaultcategorystatisticMapper, Faultcategorystatistic> {
	@Autowired
	FaultcategorystatisticMapper faultcategorystatisticMapper;

	public Faultcategorystatistic get(String id) {
		return super.get(id);
	}
	
	public List<Faultcategorystatistic> findList(Faultcategorystatistic faultcategorystatistic) {
		return super.findList(faultcategorystatistic);
	}
	
	public Map<String, Object> findStatisticList(Faultcategorystatistic faultcategorystatistic) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("queryParams",faultcategorystatistic);//查询参数
		//分类 指定的还是全部
		//获取五大申告种类
		List<String> fiveFaultcategory = new ArrayList<String>();
		List<DictValue> fiveFaultcategoryDict = DictUtils.getDictList("fiveFaultcategory");
		for (DictValue kv : fiveFaultcategoryDict) {// k=移动语音，v=手机移动语音
			fiveFaultcategory.add(kv.getValue());
		}
		List<String> faultcategoryList = new ArrayList<String>();
		List<String> faultcategoryLabelList = new ArrayList<String>();
		String faultcategory = faultcategorystatistic.getFaultcategory();
		if(faultcategory!=null && !faultcategory.equals("")) {
			//逗号分割
			String[] split = faultcategory.split(",");
			for (String item : split) {
				if(!item.equals("")) {
					faultcategoryList.add(item);
					faultcategoryLabelList.add(DictUtils.getDictLabel(item, "fiveFaultcategory", "未定义"));
				}
			}
		}else {
			//获取五大申告种类
			faultcategoryList.addAll(fiveFaultcategory);
			for (DictValue kv : fiveFaultcategoryDict) {
				faultcategoryLabelList.add(kv.getLabel());
			}
		}
		result.put("faultcategoryList", faultcategoryList);
		result.put("faultcategoryLabelList", faultcategoryLabelList);
		
		//日期设置
		Date begin = faultcategorystatistic.getBeginCreateDate();//2019-09-03 00:00:00
		Date end = faultcategorystatistic.getEndCreateDate();//2019-09-06 23:59:59
		List<Date> dateList = null;
		String timeFlag = faultcategorystatistic.getTimeFlag();
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
			nextMonth.setTime(faultcategorystatistic.getEndCreateDate());
			nextMonth.add(Calendar.MONTH, 1);
			nextMonth.add(Calendar.SECOND, -1);
			faultcategorystatistic.setEndCreateDate(nextMonth.getTime());
			
			if(!"".equals(faultcategorystatistic.getContrastFlag())) {
				nextMonth = Calendar.getInstance();
				nextMonth.setTime(faultcategorystatistic.getEndContrastDate());
				nextMonth.add(Calendar.MONTH, 1);
				nextMonth.add(Calendar.SECOND, -1);
				faultcategorystatistic.setEndContrastDate(nextMonth.getTime());
			}
			
		}
		//需要的申告种类和不需要的种类
		List<String> useFaultcategory = new ArrayList<String>();
		List<String> uselessFaultcategory = new ArrayList<String>();
		for (String item : faultcategoryList) {
			useFaultcategory.add(item);
		}
		if(useFaultcategory.contains("其他")) {
			uselessFaultcategory.addAll(fiveFaultcategory);
			useFaultcategory.remove("其他");
			uselessFaultcategory.remove("其他");
			faultcategorystatistic.setUselessFaultcategory(uselessFaultcategory);
		}
		faultcategorystatistic.setUseFaultcategory(useFaultcategory);
		faultcategorystatistic.setFaultcategory("");//不作为查询条件
		
		//来源 指定的还是全部
		//获取所有来源(前提是来源的数量不为零)
		List<String> allCityList = faultcategorystatisticMapper.findCity(faultcategorystatistic);
		
		List<String> cityList = new ArrayList<String>();
		List<String> cityLabelList = new ArrayList<String>();
		String city = faultcategorystatistic.getCity();
		if(city!=null && !city.equals("")) {
			//逗号分割
			String[] split = city.split(",");
			for (String item : split) {
				if(!item.equals("")) {
					cityList.add(item);
					cityLabelList.add(DictUtils.getDictLabel(item, "documentCity", "未定义"));
				}
			}
		}else {
			//获取所有来源
			cityList.addAll(allCityList);
			for (String item : allCityList) {
				cityLabelList.add(DictUtils.getDictLabel(item, "documentCity", "未定义"));
			}
		}
		result.put("cityList", cityList);
		result.put("cityLabelList", cityLabelList);
		
		//需要的工单来源
		List<String> useCity = new ArrayList<String>();
		for (String item : cityList) {
			useCity.add(item);
		}
		faultcategorystatistic.setUseCity(useCity);
		faultcategorystatistic.setCity("");//不作为查询条件
		List<Faultcategorystatistic> list = faultcategorystatisticMapper.findStatisticList(faultcategorystatistic);
		
		//初始化Y轴数据
		int[][] data = null;
		int dateSize = dateList.size();
		if("1".equals(faultcategorystatistic.getGroupFlag())) {//分组标识	申告种类
			int faultcategorySize = faultcategoryList.size();
			if(faultcategorySize>0 && dateSize>0) {
				data = new int[faultcategorySize][dateSize];
			}
		}else {//申告来源
			int citySize = cityList.size();
			if(citySize>0 && dateSize>0) {
				data = new int[citySize][dateSize];
			}
		}
		
		//放数据
		for(int i=0; i<list.size(); i++) {//全部数据
			Faultcategorystatistic item = list.get(i);
			Date createDate = item.getCreateDate();
			int times = Integer.valueOf(item.getTimes());
			
			//申告种类
			if("1".equals(faultcategorystatistic.getGroupFlag())) {
				String faultcategoryDB = item.getFaultcategory();
				for(int j=0; j<dateList.size(); j++) {
					if("1".equals(timeFlag) && createDate.getTime()-dateList.get(j).getTime()>=0 && createDate.getTime()-dateList.get(j).getTime()<24*60*60*1000) {
						boolean isOther = true;
						for(int n=0; n<faultcategoryList.size() && isOther; n++) {
							if(faultcategoryDB.indexOf(faultcategoryList.get(n))!=-1) {
								//非其他
								data[n][j] += times;
								isOther = false;
							}
						}
						if(isOther) {//其他
							data[faultcategoryList.indexOf("其他")][j] += times;
						}
					}else if("2".equals(timeFlag)) {
						Calendar calCre = Calendar.getInstance();
						Calendar calLis = Calendar.getInstance();
						calCre.setTime(createDate);
						calLis.setTime(dateList.get(j));
						if(calCre.get(Calendar.YEAR)==calLis.get(Calendar.YEAR) && calCre.get(Calendar.MONTH)==calLis.get(Calendar.MONTH)) {

							boolean isOther = true;
							for(int n=0; n<faultcategoryList.size() && isOther; n++) {
								if(faultcategoryDB.indexOf(faultcategoryList.get(n))!=-1) {
									//非其他
									data[n][j] += times;
									isOther = false;
									break;
								}
							}
							if(isOther) {//其他
								data[faultcategoryList.indexOf("其他")][j] += times;
							}
						
						}
					}
				}
			}else {
				//申告来源
				String cityDB = item.getCity();
				for(int j=0; j<dateList.size(); j++) {
					if("1".equals(timeFlag) && createDate.getTime()-dateList.get(j).getTime()>=0 && createDate.getTime()-dateList.get(j).getTime()<24*60*60*1000) {
						for(int n=0; n<cityList.size(); n++) {
							if(cityDB.equals(cityList.get(n))) {
								data[n][j] += times;
							}
						}
					}else if("2".equals(timeFlag)) {
						Calendar calCre = Calendar.getInstance();
						Calendar calLis = Calendar.getInstance();
						calCre.setTime(createDate);
						calLis.setTime(dateList.get(j));
						if(calCre.get(Calendar.YEAR)==calLis.get(Calendar.YEAR) && calCre.get(Calendar.MONTH)==calLis.get(Calendar.MONTH)) {
							for(int n=0; n<cityList.size(); n++) {
								if(cityDB.equals(cityList.get(n))) {
									data[n][j] += times;
									break;
								}
							}
						}
					}
				}
			}
			
			
		}
		result.put("data", data);
		if(!"".equals(faultcategorystatistic.getContrastFlag())) {//同比和环比数据
			result.putAll(getContrastData(faultcategorystatistic, faultcategoryList, cityList, data));
		}
		return result;
	}
	
	/**
	 * 获取同比和环比的数据
	 * @param faultcategorystatistic
	 * @return
	 */
	public Map<String,Object> getContrastData(Faultcategorystatistic faultcategorystatistic, List<String> faultcategoryList, List<String> cityList, int[][] data){
		Map<String,Object> result = new HashMap<String,Object>();
		
		String timeFlag = faultcategorystatistic.getTimeFlag();
		Date beginContrast = faultcategorystatistic.getBeginContrastDate();//2019-09-03 00:00:00
		Date endContrast = faultcategorystatistic.getEndContrastDate();//2019-09-06 23:59:59
		List<Date> dateContrastList = null;
		dateContrastList = getDate(beginContrast, endContrast, timeFlag);
		List<String> dateContrastfmt = new ArrayList<String>();
		SimpleDateFormat dateContrastFormat = null;
		if("1".equals(timeFlag)) {
			dateContrastFormat = new SimpleDateFormat("yyyy-MM-dd");
			for(int j=0; j<dateContrastList.size(); j++) {
				dateContrastfmt.add(dateContrastFormat.format(dateContrastList.get(j)));
			}
		}else if("2".equals(timeFlag)) {
			dateContrastFormat = new SimpleDateFormat("yyyy-MM");
			for(int j=0; j<dateContrastList.size(); j++) {
				dateContrastfmt.add(dateContrastFormat.format(dateContrastList.get(j)));
			}
		}
		result.put("dateContrastfmt", dateContrastfmt);
		//准备查询
		//更新查询的开始和结束日期，用同比、环比的开始和结束日期
		faultcategorystatistic.setBeginCreateDate(faultcategorystatistic.getBeginContrastDate());
		faultcategorystatistic.setEndCreateDate(faultcategorystatistic.getEndContrastDate());
		
		List<Faultcategorystatistic> list = faultcategorystatisticMapper.findStatisticList(faultcategorystatistic);
		
		//初始化Y轴数据
		int[][] contrastData = null;
		int dateContrastSize = dateContrastList.size();
		int[] contrastDataSum = new int[dateContrastSize];
		if("1".equals(faultcategorystatistic.getGroupFlag())) {//分组标识	申告种类
			int faultcategorySize = faultcategoryList.size();
			if(faultcategorySize>0 && dateContrastSize>0) {
				contrastData = new int[faultcategorySize][dateContrastSize];
			}
		}else {//申告来源
			int citySize = cityList.size();
			if(citySize>0 && dateContrastSize>0) {
				contrastData = new int[citySize][dateContrastSize];
			}
		}
		
		//放数据
		for(int i=0; i<list.size(); i++) {//全部数据
			Faultcategorystatistic item = list.get(i);
			Date createDate = item.getCreateDate();
			int times = Integer.valueOf(item.getTimes());
			
			//申告种类
			if("1".equals(faultcategorystatistic.getGroupFlag())) {
				String faultcategoryDB = item.getFaultcategory();
				for(int j=0; j<dateContrastList.size(); j++) {
					if("1".equals(timeFlag) && createDate.getTime()-dateContrastList.get(j).getTime()>=0 && createDate.getTime()-dateContrastList.get(j).getTime()<24*60*60*1000) {
						boolean isOther = true;
						for(int n=0; n<faultcategoryList.size() && isOther; n++) {
							if(faultcategoryDB.indexOf(faultcategoryList.get(n))!=-1) {
								//非其他
								contrastData[n][j] += times;
								isOther = false;
							}
						}
						if(isOther) {//其他
							contrastData[faultcategoryList.indexOf("其他")][j] += times;
						}
						
					}else if("2".equals(timeFlag)) {
						Calendar calCre = Calendar.getInstance();
						Calendar calLis = Calendar.getInstance();
						calCre.setTime(createDate);
						calLis.setTime(dateContrastList.get(j));
						if(calCre.get(Calendar.YEAR)==calLis.get(Calendar.YEAR) && calCre.get(Calendar.MONTH)==calLis.get(Calendar.MONTH)) {

							boolean isOther = true;
							for(int n=0; n<faultcategoryList.size() && isOther; n++) {
								if(faultcategoryDB.indexOf(faultcategoryList.get(n))!=-1) {
									//非其他
									contrastData[n][j] += times;
									isOther = false;
									break;
								}
							}
							if(isOther) {//其他
								contrastData[faultcategoryList.indexOf("其他")][j] += times;
							}
						
						}
						
					}
					
				}
			}else {
				//申告来源
				String cityDB = item.getCity();
				for(int j=0; j<dateContrastList.size(); j++) {
					if("1".equals(timeFlag) && createDate.getTime()-dateContrastList.get(j).getTime()>=0 && createDate.getTime()-dateContrastList.get(j).getTime()<24*60*60*1000) {
						for(int n=0; n<cityList.size(); n++) {
							if(cityDB.equals(cityList.get(n))) {
								contrastData[n][j] += times;
							}
						}
						
					}else if("2".equals(timeFlag)) {
						Calendar calCre = Calendar.getInstance();
						Calendar calLis = Calendar.getInstance();
						calCre.setTime(createDate);
						calLis.setTime(dateContrastList.get(j));
						if(calCre.get(Calendar.YEAR)==calLis.get(Calendar.YEAR) && calCre.get(Calendar.MONTH)==calLis.get(Calendar.MONTH)) {
							for(int n=0; n<cityList.size(); n++) {
								if(cityDB.equals(cityList.get(n))) {
									contrastData[n][j] += times;
									break;
								}
							}
						}
						
					}
					
				}
			}
			
			
		}
		
		int[] temp = new int[contrastDataSum.length];
		for(int i=0; i<contrastData.length; i++) {
			for(int j=0; j<contrastData[i].length;j++) {
				contrastDataSum[j] += contrastData[i][j];
				temp[j] += data[i][j];
			}
		}
		//同比环比
		double[] contrastPercent = new double[contrastDataSum.length];
		for(int j=0; j<contrastDataSum.length; j++) {
			DecimalFormat df = new DecimalFormat("0.00");
			if(temp[j]==0 && contrastDataSum[j]==0) {
				contrastPercent[j] = 0;
			}else if(temp[j]>0 && contrastDataSum[j]==0){
				contrastPercent[j] = 100;
			}else {
				contrastPercent[j] = Double.valueOf(df.format((double)(temp[j]-contrastDataSum[j])*100/contrastDataSum[j]));
			}
		}
		result.put("contrastData", contrastData);
		result.put("contrastPercent", contrastPercent);
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
	/**
	 * 所有分类
	 * @return
	 */
	public List<String> findFaultcategory() {
		return faultcategorystatisticMapper.findFaultcategorysResult();
	}

	public List<Faultcategorystatistic> findMonth(Faultcategorystatistic faultcategorystatistic) {
		return faultcategorystatisticMapper.findMonth(faultcategorystatistic);
	}

	/**
	 * 地市对应次数
	 * @param faultcategorystatistic
	 * @return
	 */
	public List<Faultcategorystatistic> findCityData(Faultcategorystatistic faultcategorystatistic) {
		//例如结束日期是2019-10，则查询时改为2019-10-31 23:59:59，即要拿出10月的数据
		if("2".equals(faultcategorystatistic.getTimeFlag())) {
			Calendar nextMonth = Calendar.getInstance();
			nextMonth.setTime(faultcategorystatistic.getEndCreateDate());
			nextMonth.add(Calendar.MONTH, 1);
			nextMonth.add(Calendar.SECOND, -1);
			faultcategorystatistic.setEndCreateDate(nextMonth.getTime());
		}
		String faultcategory = faultcategorystatistic.getFaultcategory();
		//其他
		if("其他".equals(faultcategory)) {
			//获取五大申告种类
			List<String> fiveFaultcategory = new ArrayList<String>();
			List<DictValue> fiveFaultcategoryDict = DictUtils.getDictList("fiveFaultcategory");
			for (DictValue kv : fiveFaultcategoryDict) {
				fiveFaultcategory.add(kv.getValue());
			}
			fiveFaultcategory.remove("其他");
			faultcategorystatistic.setUselessFaultcategory(fiveFaultcategory);
			faultcategorystatistic.setFaultcategory("");//不作为查询条件
		}
		return faultcategorystatisticMapper.findCityData(faultcategorystatistic);
	}
	
	/**
	 * 分类对应次数
	 * @param faultcategorystatistic
	 * @return
	 */
	public List<Faultcategorystatistic> findFaultCategoryData(Faultcategorystatistic faultcategorystatistic) {
		List<Faultcategorystatistic> result = new ArrayList<Faultcategorystatistic>();
		//例如结束日期是2019-10，则查询时改为2019-10-31 23:59:59，即要拿出10月的数据
		if("2".equals(faultcategorystatistic.getTimeFlag())) {
			Calendar nextMonth = Calendar.getInstance();
			nextMonth.setTime(faultcategorystatistic.getEndCreateDate());
			nextMonth.add(Calendar.MONTH, 1);
			nextMonth.add(Calendar.SECOND, -1);
			faultcategorystatistic.setEndCreateDate(nextMonth.getTime());
		}
		
		List<DictValue> fiveFaultcategoryDict = DictUtils.getDictList("fiveFaultcategory");
		List<String> fiveFaultcategory = new ArrayList<String>();
		for (DictValue kv : fiveFaultcategoryDict) {// k=移动语音，v=手机移动语音
			fiveFaultcategory.add(kv.getValue());
		}
		
		String faultcategory = faultcategorystatistic.getFaultcategory();
		Map<String,Integer> faultcategoryMap = new HashMap<String,Integer>();
		List<String> useFaultcategory = new ArrayList<String>();
		List<String> uselessFaultcategory = new ArrayList<String>();
		//限制分类
		if(faultcategory==null || "".equals(faultcategory) || faultcategory.split(",").length>0) {
			String[] arr = faultcategory.split(",");
			for (String item : arr) {
				if(!"".equals(item)) {
					useFaultcategory.add(item);
					faultcategoryMap.put(item, 0);
				}
			}
			if(useFaultcategory.contains("其他")) {
				useFaultcategory.remove("其他");
				uselessFaultcategory.addAll(fiveFaultcategory);
				uselessFaultcategory.remove("其他");
				faultcategorystatistic.setUselessFaultcategory(uselessFaultcategory);
			}
			faultcategorystatistic.setUseFaultcategory(useFaultcategory);
		}else {
			//没有限制分类
		}
		
		//查询结果
		List<Faultcategorystatistic> data = faultcategorystatisticMapper.findFaultCategoryData(faultcategorystatistic);

		for (Faultcategorystatistic item : data) {
			int times = Integer.valueOf(item.getTimes());
			String itemFaultcategory = item.getFaultcategory();
			boolean isOther = true;
			for(Map.Entry<String, Integer> entry : faultcategoryMap.entrySet()){
			    String mapKey = entry.getKey();
			    int mapValue = entry.getValue();
			    if(itemFaultcategory.contains(mapKey)) {
			    	faultcategoryMap.put(mapKey, mapValue+times);
			    	isOther = false;
			    }
			}
			if(isOther) {
				faultcategoryMap.put("其他", faultcategoryMap.get("其他")+times);
			}
		}
		for(Map.Entry<String, Integer> entry : faultcategoryMap.entrySet()){
		    String mapKey = entry.getKey();
		    int mapValue = entry.getValue();
		    Faultcategorystatistic temp = new Faultcategorystatistic();
		    temp.setFaultcategory(mapKey);
		    temp.setTimes(mapValue+"");
		    result.add(temp);
		}
		return result;
	}
}
