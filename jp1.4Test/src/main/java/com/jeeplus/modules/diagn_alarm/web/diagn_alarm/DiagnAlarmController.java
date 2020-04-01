/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_alarm.web.diagn_alarm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.diagn_alarm.entity.diagn_alarm.DiagnAlarm;
import com.jeeplus.modules.diagn_alarm.service.diagn_alarm.DiagnAlarmService;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.utils.LogUtils;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * 一键诊断告警信息Controller
 * @author 姜森焱
 * @version 2020-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/diagn_alarm/diagn_alarm/diagnAlarm")
public class DiagnAlarmController extends BaseController {

	@Autowired
	private DiagnAlarmService diagnAlarmService;
	
	@ModelAttribute
	public DiagnAlarm get(@RequestParam(required=false) String id) {
		DiagnAlarm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diagnAlarmService.get(id);
		}
		if (entity == null){
			entity = new DiagnAlarm();
		}
		return entity;
	}
	
	/**
	 * 一键诊断告警信息列表页面
	 */
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/diagn_alarm/diagn_alarm/diagnAlarmList";
	}
	
		/**
	 * 一键诊断告警信息列表数据
	 */
	@ResponseBody
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DiagnAlarm diagnAlarm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DiagnAlarm> page = diagnAlarmService.findPage(new Page<DiagnAlarm>(request, response), diagnAlarm); 
		
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑一键诊断告警信息表单页面
	 */
	@RequiresPermissions(value={"diagn_alarm:diagn_alarm:diagnAlarm:view","diagn_alarm:diagn_alarm:diagnAlarm:add","diagn_alarm:diagn_alarm:diagnAlarm:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DiagnAlarm diagnAlarm, Model model) {
		model.addAttribute("diagnAlarm", diagnAlarm);
		return "modules/diagn_alarm/diagn_alarm/diagnAlarmForm";
	}
	/**
	 * 确认告警信息
	 */
	@RequestMapping(value="updateconfirmWhether")
	public String updateconfirmWhether(DiagnAlarm diagnAlarm, Model model) {
		User user=UserUtils.getUser();
		DiagnAlarm DiagnAlarm1=new DiagnAlarm();
		DiagnAlarm1.setId(diagnAlarm.getId());
		DiagnAlarm1.setConfirmUser(user.getName());
		DiagnAlarm1.setConfirmTime(DateUtils.formatDateTime(new Date()));
		diagnAlarmService.updateconfirmWhether(DiagnAlarm1);
		return "modules/diagn_alarm/diagn_alarm/diagnAlarmList";
	}

	/**
	 * 保存一键诊断告警信息
	 */
	@ResponseBody
	@RequiresPermissions(value={"diagn_alarm:diagn_alarm:diagnAlarm:add","diagn_alarm:diagn_alarm:diagnAlarm:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DiagnAlarm diagnAlarm, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, diagnAlarm)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		diagnAlarmService.save(diagnAlarm);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存一键诊断告警信息成功");
		return j;
	}
	
	/**
	 * 删除一键诊断告警信息
	 */
	@ResponseBody
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DiagnAlarm diagnAlarm, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		diagnAlarmService.delete(diagnAlarm);
		j.setMsg("删除一键诊断告警信息成功");
		return j;
	}
	
	/**
	 * 批量删除一键诊断告警信息
	 */
	@ResponseBody
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			diagnAlarmService.delete(diagnAlarmService.get(id));
		}
		j.setMsg("删除一键诊断告警信息成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(DiagnAlarm diagnAlarm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "一键诊断告警信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DiagnAlarm> page = diagnAlarmService.findPage(new Page<DiagnAlarm>(request, response, -1), diagnAlarm);
    		new ExportExcel("一键诊断告警信息", DiagnAlarm.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出一键诊断告警信息记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DiagnAlarm> list = ei.getDataList(DiagnAlarm.class);
			for (DiagnAlarm diagnAlarm : list){
				try{
					diagnAlarmService.save(diagnAlarm);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条一键诊断告警信息记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条一键诊断告警信息记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入一键诊断告警信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_alarm/diagn_alarm/diagnAlarm/?repage";
    }
	
	/**
	 * 下载导入一键诊断告警信息数据模板
	 */
	@RequiresPermissions("diagn_alarm:diagn_alarm:diagnAlarm:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "一键诊断告警信息数据导入模板.xlsx";
    		List<DiagnAlarm> list = Lists.newArrayList(); 
    		new ExportExcel("一键诊断告警信息数据", DiagnAlarm.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_alarm/diagn_alarm/diagnAlarm/?repage";
    }

}