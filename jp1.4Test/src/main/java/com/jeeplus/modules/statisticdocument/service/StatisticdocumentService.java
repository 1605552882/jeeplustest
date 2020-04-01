/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.statisticdocument.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.statisticdocument.entity.Statisticdocument;
import com.jeeplus.modules.statisticdocument.mapper.StatisticdocumentMapper;

/**
 * 表单统计Service
 * @author lxy
 * @version 2019-09-05
 */
@Service
@Transactional(readOnly = true)
public class StatisticdocumentService extends CrudService<StatisticdocumentMapper, Statisticdocument> {
	
	@Autowired
	StatisticdocumentMapper statisticdocumentMapper;

	public Statisticdocument get(String id) {
		return super.get(id);
	}
	
	public List<Statisticdocument> find(Statisticdocument statisticdocument) {
		return statisticdocumentMapper.find(statisticdocument);
	}

	public Statisticdocument findMonth(Statisticdocument statisticdocument) {
		return statisticdocumentMapper.findMonth(statisticdocument);
	}

	public List<Statisticdocument> getData(Statisticdocument statisticdocument) throws ParseException {
		List<Statisticdocument> document = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int timeflag = statisticdocument.getDaynum();// 统计时间flag
		if (timeflag == 1) {//按日统计
			//30天前
			Calendar calend = Calendar.getInstance();
			calend.add(Calendar.DAY_OF_MONTH, -30);
			String start =df.format(calend.getTime())  + " 00:00:00"; //df.format(calend.getTime())
			Date startDate = sdf.parse(start);
			//昨天
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH, -1);
			String last =  df.format(ca.getTime())+ " 00:00:00"; //df.format(ca.getTime())
			Date enDate = sdf.parse(last);
			statisticdocument.setStart(startDate);
			statisticdocument.setEnd(enDate);
			document = this.find(statisticdocument);
		} else if (timeflag == 2) { //按月统计
			Calendar calend = Calendar.getInstance();
			int month = calend.get(Calendar.MONTH);
			for (int i = month; i >= 0; i--) {
				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.MONTH, -i);
				String time = sdf.format(ca.getTime());
				statisticdocument.setCreatTime(sdf.parse(time));
				Statisticdocument sdocument = this.findMonth(statisticdocument);
				document.add(sdocument);
			}
		} else if (timeflag == 3) {
			
		}
		return document;
	}

	public List<Statisticdocument> getsdData(Statisticdocument statisticdocument){
		List<Statisticdocument> document = new ArrayList<>();
		Statisticdocument sdocument = this.findMonth(statisticdocument);
		document.add(sdocument);
		return document;
	}
	
}