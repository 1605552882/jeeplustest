package com.jeeplus.modules.nursingclass.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.activityassess.entity.EdActivityassessLog;
import com.jeeplus.modules.activityassess.mapper.EdActivityassessLogMapper;
import com.jeeplus.modules.elder.entity.EdElderInf;
import com.jeeplus.modules.mentalassess.entity.EdMentalassessLog;
import com.jeeplus.modules.mentalassess.mapper.EdMentalassessLogMapper;



@Controller
@RequestMapping(value = "${adminPath}/nursingclass/edNursingclassLogEx")
public class EdNursingclassLogExController extends BaseController {

	@Autowired
	private EdActivityassessLogMapper activityMapper;
	
	@Autowired
	private EdMentalassessLogMapper mentalMapper;
	
	
	@ResponseBody
	@RequestMapping(value = "basedata")
	public AjaxJson loadBaseData(String elderid){
		EdActivityassessLog act =new EdActivityassessLog();
		act.setElderid(new EdElderInf(elderid));
		Page<EdActivityassessLog> page = new Page<EdActivityassessLog>();
		page.setPageSize(1);
		page.setPageNo(1);
		page.setOrderBy(" createDate desc ");
		act.setPage(page);
		List<EdActivityassessLog>  list =activityMapper.findList(act);
		
		AjaxJson j = new AjaxJson();
		
		String fenji ="";
		if(list != null && list.size() > 0){
			EdActivityassessLog ret = list.get(0);
			fenji = ret.getRichanghuodongnenglifenji();
			j.put("fenji", fenji==null?"":fenji.trim() );
		}else{
			j.put("fenji", "");
		}
		
		EdMentalassessLog ment = new EdMentalassessLog();
		ment.setElderid(new EdElderInf(elderid));
		Page<EdMentalassessLog> mpage = new Page<EdMentalassessLog>();
		page.setPageSize(1);
		page.setPageNo(1);
		page.setOrderBy(" createDate desc ");
		ment.setPage(mpage);
		List<EdMentalassessLog>  mlist =mentalMapper.findList(ment);
		
		
		String zhili ="";
		if(mlist != null && mlist.size() > 0){
			EdMentalassessLog ret = mlist.get(0);
			zhili = ret.getZhiliqingkuang();
			j.put("zhili", zhili==null?"":zhili.trim() );
		}else{
			j.put("zhili", "");
		}
		
		String huli ="";
		if(zhili!=null && fenji != null){
			huli = calcClass(fenji,zhili);
		}
		j.put("huli", huli);
		
		j.setSuccess(true);
		return j;
	}
	
	
	public String calcClass(String fengji,String zhili){
		String ret="";
		if(fengji.equals("10")){
			if(zhili.equals("10")){
				ret ="10";
			}else if(zhili.equals("20")){
				ret ="20";
			}else if(zhili.equals("30")){
				ret ="30";
			}else if(zhili.equals("40")){
				ret ="50";
			}
		}else if(fengji.equals("20")){
			if(zhili.equals("10")){
				ret ="20";
			}else if(zhili.equals("20")){
				ret ="20";
			}else if(zhili.equals("30")){
				ret ="30";
			}else if(zhili.equals("40")){
				ret ="50";
			}
		}else if(fengji.equals("30")|| fengji.equals("40")){
			if(zhili.equals("10")){
				ret ="40";
			}else if(zhili.equals("20")){
				ret ="40";
			}else if(zhili.equals("30")){
				ret ="50";
			}else if(zhili.equals("40")){
				ret ="70";
			}
		}else if(fengji.equals("50")|| fengji.equals("60")){
			if(zhili.equals("10")){
				ret ="60";
			}else if(zhili.equals("20")){
				ret ="60";
			}else if(zhili.equals("30")){
				ret ="70";
			}else if(zhili.equals("40")){
				ret ="70";
			}
		}
		return ret;
	}
	
}
