/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.statistichotword.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.hotword.entity.Hotword;
import com.jeeplus.modules.hotword.service.HotwordService;
import com.jeeplus.modules.statistichotword.entity.Statistichotword;
import com.jeeplus.modules.statistichotword.service.StatistichotwordService;

/**
 * 热词统计Controller
 * @author lxy
 * @version 2019-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/statistichotword/statistichotword")
public class StatistichotwordController extends BaseController {

	@Autowired
	private StatistichotwordService statistichotwordService;
	
	@Autowired
	private HotwordService hotwordService;
	
	@ModelAttribute
	public Statistichotword get(@RequestParam(required=false) String id) {
		Statistichotword entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statistichotwordService.get(id);
		}
		if (entity == null){
			entity = new Statistichotword();
		}
		return entity;
	}
	
	@RequestMapping(value = "subChart")
	public String subChart(@RequestParam String time,@RequestParam String sStatus,Model model) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Statistichotword statistichotword = new Statistichotword();
		statistichotword.setsStatus(sStatus);
		statistichotword.setCreateDate(sd.parse(time + " 00:00:00"));
		List<Statistichotword> list = statistichotwordService.findList(statistichotword);
		model.addAttribute("list", list);
		return "modules/statistichotword/statistichotwordSub";
	}
	/**
	 * 热词统计列表页面
	 */
	//@RequiresPermissions("statistichotword:statistichotword:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/statistichotword/statistichotwordList";
	}
	
		/**
	 * 热词统计列表数据
	 */
	@ResponseBody
	//@RequiresPermissions("statistichotword:statistichotword:list")
	@RequestMapping(value = "data")
	public AjaxJson  data(Statistichotword statistichotword, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		AjaxJson j = new AjaxJson();
		//获取所有
		List<Statistichotword> list = statistichotwordService.findList(statistichotword); 
		//获取热词列表
		Hotword hotword = new Hotword();
		List<Hotword> hotwordList = hotwordService.findList(hotword);
		//y轴
		Map<String, List<String>>  ydataMap = new HashMap<String, List<String>>();
		//放入map
		for (int i = 0; i < hotwordList.size(); i++) {
			List<String> maplist = new ArrayList<String>();
			ydataMap.put(hotwordList.get(i).getHotword(), maplist);
		}
		//x轴
		List<String> xList = new ArrayList<String>();
		//记录热词
		String sign = "";
		for (int i = 0; i < list.size(); i++) {
			String x = sd.format(list.get(i).getCreateDate());
			if (i != 0) {
				if (!x.equals(sd.format(list.get(i-1).getCreateDate()))) {//时间排过序 如果不等于上个时间不插入
					xList.add(x);
					//没有数据的热词补0
					 Iterator<String> iterator = ydataMap.keySet().iterator();
					 while (iterator.hasNext()) {
					        String key = iterator.next();
					        if (!sign.contains(key)) {//如果不包含热词 说明没数据 补齐0
					        	ydataMap.get(key).add("0");
							}
					 }
					 sign = "";
				}
			} else {
				xList.add(x);
			}
			String yname = list.get(i).getHotword();
			String ydata = list.get(i).getTimes();
			if (ydataMap.containsKey(yname)) {
				ydataMap.get(yname).add(ydata);
				sign +=  yname;
			}
		}
		j.setSuccess(true);
		j.put("xList", xList);
		j.put("ydataMap", ydataMap);
		return j;
	}

}