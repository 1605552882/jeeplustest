/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialnursing.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.jeeplus.modules.specialnursing.entity.EdSpecialnursingLog;
import com.jeeplus.modules.specialnursing.service.EdSpecialnursingLogService;

/**
 * 特殊护理记录Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/specialnursing/edSpecialnursingLog")
public class EdSpecialnursingLogController extends BaseController {

	@Autowired
	private EdSpecialnursingLogService edSpecialnursingLogService;
	
	@ModelAttribute
	public EdSpecialnursingLog get(@RequestParam(required=false) String id) {
		EdSpecialnursingLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edSpecialnursingLogService.get(id);
		}
		if (entity == null){
			entity = new EdSpecialnursingLog();
		}
		return entity;
	}
	
	/**
	 * 特殊护理记录列表页面
	 */
	@RequiresPermissions("specialnursing:edSpecialnursingLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/specialnursing/edSpecialnursingLogList";
	}
	
		/**
	 * 特殊护理记录列表数据
	 */
	@ResponseBody
	@RequiresPermissions("specialnursing:edSpecialnursingLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdSpecialnursingLog edSpecialnursingLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdSpecialnursingLog> page = edSpecialnursingLogService.findPage(new Page<EdSpecialnursingLog>(request, response), edSpecialnursingLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑特殊护理记录表单页面
	 */
	@RequiresPermissions(value={"specialnursing:edSpecialnursingLog:view","specialnursing:edSpecialnursingLog:add","specialnursing:edSpecialnursingLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdSpecialnursingLog edSpecialnursingLog, Model model) {
		model.addAttribute("edSpecialnursingLog", edSpecialnursingLog);
		return "modules/specialnursing/edSpecialnursingLogForm";
	}

	/**
	 * 保存特殊护理记录
	 */
	@ResponseBody
	@RequiresPermissions(value={"specialnursing:edSpecialnursingLog:add","specialnursing:edSpecialnursingLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdSpecialnursingLog edSpecialnursingLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edSpecialnursingLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edSpecialnursingLogService.save(edSpecialnursingLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存特殊护理记录成功");
		return j;
	}
	
	/**
	 * 删除特殊护理记录
	 */
	@ResponseBody
	@RequiresPermissions("specialnursing:edSpecialnursingLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdSpecialnursingLog edSpecialnursingLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edSpecialnursingLogService.delete(edSpecialnursingLog);
		j.setMsg("删除特殊护理记录成功");
		return j;
	}
	
	/**
	 * 批量删除特殊护理记录
	 */
	@ResponseBody
	@RequiresPermissions("specialnursing:edSpecialnursingLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edSpecialnursingLogService.delete(edSpecialnursingLogService.get(id));
		}
		j.setMsg("删除特殊护理记录成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("specialnursing:edSpecialnursingLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdSpecialnursingLog edSpecialnursingLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "特殊护理记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdSpecialnursingLog> page = edSpecialnursingLogService.findPage(new Page<EdSpecialnursingLog>(request, response, -1), edSpecialnursingLog);
    		new ExportExcel("特殊护理记录", EdSpecialnursingLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出特殊护理记录记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("specialnursing:edSpecialnursingLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdSpecialnursingLog> list = ei.getDataList(EdSpecialnursingLog.class);
			for (EdSpecialnursingLog edSpecialnursingLog : list){
				try{
					edSpecialnursingLogService.save(edSpecialnursingLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条特殊护理记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条特殊护理记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入特殊护理记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/specialnursing/edSpecialnursingLog/?repage";
    }
	
	/**
	 * 下载导入特殊护理记录数据模板
	 */
	@RequiresPermissions("specialnursing:edSpecialnursingLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "特殊护理记录数据导入模板.xlsx";
    		List<EdSpecialnursingLog> list = Lists.newArrayList(); 
    		new ExportExcel("特殊护理记录数据", EdSpecialnursingLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/specialnursing/edSpecialnursingLog/?repage";
    }

}