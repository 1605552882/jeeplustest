/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv_log.web.diagn_serv_log;

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
import com.jeeplus.modules.diagn_serv_log.entity.diagn_serv_log.DiagnServLog;
import com.jeeplus.modules.diagn_serv_log.service.diagn_serv_log.DiagnServLogService;

/**
 * 一键诊断详细信息Controller
 * @author 姜森焱
 * @version 2020-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/diagn_serv_log/diagn_serv_log/diagnServLog")
public class DiagnServLogController extends BaseController {

	@Autowired
	private DiagnServLogService diagnServLogService;
	
	@ModelAttribute
	public DiagnServLog get(@RequestParam(required=false) String id) {
		DiagnServLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diagnServLogService.get(id);
		}
		if (entity == null){
			entity = new DiagnServLog();
		}
		return entity;
	}
	
	/**
	 * 一键诊断日志主表列表页面
	 */
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/diagn_serv_log/diagn_serv_log/diagnServLogList";
	}
	
		/**
	 * 一键诊断日志主表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DiagnServLog diagnServLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DiagnServLog> page = diagnServLogService.findPage(new Page<DiagnServLog>(request, response), diagnServLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑一键诊断日志主表表单页面
	 */
	@RequiresPermissions(value={"diagn_serv_log:diagn_serv_log:diagnServLog:view","diagn_serv_log:diagn_serv_log:diagnServLog:add","diagn_serv_log:diagn_serv_log:diagnServLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DiagnServLog diagnServLog, Model model) {
		model.addAttribute("diagnServLog", diagnServLog);
		return "modules/diagn_serv_log/diagn_serv_log/diagnServLogForm";
	}

	/**
	 * 保存一键诊断日志主表
	 */
	@ResponseBody
	@RequiresPermissions(value={"diagn_serv_log:diagn_serv_log:diagnServLog:add","diagn_serv_log:diagn_serv_log:diagnServLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DiagnServLog diagnServLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, diagnServLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		diagnServLogService.save(diagnServLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存一键诊断日志主表成功");
		return j;
	}
	
	/**
	 * 删除一键诊断日志主表
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DiagnServLog diagnServLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		diagnServLogService.delete(diagnServLog);
		j.setMsg("删除一键诊断日志主表成功");
		return j;
	}
	
	/**
	 * 批量删除一键诊断日志主表
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			diagnServLogService.delete(diagnServLogService.get(id));
		}
		j.setMsg("删除一键诊断日志主表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(DiagnServLog diagnServLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "一键诊断日志主表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DiagnServLog> page = diagnServLogService.findPage(new Page<DiagnServLog>(request, response, -1), diagnServLog);
    		new ExportExcel("一键诊断日志主表", DiagnServLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出一键诊断日志主表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DiagnServLog> list = ei.getDataList(DiagnServLog.class);
			for (DiagnServLog diagnServLog : list){
				try{
					diagnServLogService.save(diagnServLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条一键诊断日志主表记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条一键诊断日志主表记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入一键诊断日志主表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_serv_log/diagn_serv_log/diagnServLog/?repage";
    }
	
	/**
	 * 下载导入一键诊断日志主表数据模板
	 */
	@RequiresPermissions("diagn_serv_log:diagn_serv_log:diagnServLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "一键诊断日志主表数据导入模板.xlsx";
    		List<DiagnServLog> list = Lists.newArrayList(); 
    		new ExportExcel("一键诊断日志主表数据", DiagnServLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_serv_log/diagn_serv_log/diagnServLog/?repage";
    }

}