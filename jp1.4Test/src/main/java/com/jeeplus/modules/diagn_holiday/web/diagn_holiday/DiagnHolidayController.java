/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_holiday.web.diagn_holiday;

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
import com.jeeplus.modules.diagn_holiday.entity.diagn_holiday.DiagnHoliday;
import com.jeeplus.modules.diagn_holiday.service.diagn_holiday.DiagnHolidayService;

/**
 * 节假日配置Controller
 * @author 姜森焱
 * @version 2020-02-26
 */
@Controller
@RequestMapping(value = "${adminPath}/diagn_holiday/diagn_holiday/diagnHoliday")
public class DiagnHolidayController extends BaseController {

	@Autowired
	private DiagnHolidayService diagnHolidayService;
	
	@ModelAttribute
	public DiagnHoliday get(@RequestParam(required=false) String id) {
		DiagnHoliday entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diagnHolidayService.get(id);
		}
		if (entity == null){
			entity = new DiagnHoliday();
		}
		return entity;
	}
	
	/**
	 * 节假日配置列表页面
	 */
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/diagn_holiday/diagn_holiday/diagnHolidayList";
	}
	
		/**
	 * 节假日配置列表数据
	 */
	@ResponseBody
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DiagnHoliday diagnHoliday, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DiagnHoliday> page = diagnHolidayService.findPage(new Page<DiagnHoliday>(request, response), diagnHoliday); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑节假日配置表单页面
	 */
	@RequiresPermissions(value={"diagn_holiday:diagn_holiday:diagnHoliday:view","diagn_holiday:diagn_holiday:diagnHoliday:add","diagn_holiday:diagn_holiday:diagnHoliday:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DiagnHoliday diagnHoliday, Model model) {
		model.addAttribute("diagnHoliday", diagnHoliday);
		return "modules/diagn_holiday/diagn_holiday/diagnHolidayForm";
	}

	/**
	 * 保存节假日配置
	 */
	@ResponseBody
	@RequiresPermissions(value={"diagn_holiday:diagn_holiday:diagnHoliday:add","diagn_holiday:diagn_holiday:diagnHoliday:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DiagnHoliday diagnHoliday, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, diagnHoliday)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		diagnHolidayService.save(diagnHoliday);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存节假日配置成功");
		return j;
	}
	
	/**
	 * 删除节假日配置
	 */
	@ResponseBody
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DiagnHoliday diagnHoliday, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		diagnHolidayService.delete(diagnHoliday);
		j.setMsg("删除节假日配置成功");
		return j;
	}
	
	/**
	 * 批量删除节假日配置
	 */
	@ResponseBody
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			diagnHolidayService.delete(diagnHolidayService.get(id));
		}
		j.setMsg("删除节假日配置成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(DiagnHoliday diagnHoliday, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "节假日配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DiagnHoliday> page = diagnHolidayService.findPage(new Page<DiagnHoliday>(request, response, -1), diagnHoliday);
    		new ExportExcel("节假日配置", DiagnHoliday.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出节假日配置记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DiagnHoliday> list = ei.getDataList(DiagnHoliday.class);
			for (DiagnHoliday diagnHoliday : list){
				try{
					diagnHolidayService.save(diagnHoliday);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条节假日配置记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条节假日配置记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入节假日配置失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_holiday/diagn_holiday/diagnHoliday/?repage";
    }
	
	/**
	 * 下载导入节假日配置数据模板
	 */
	@RequiresPermissions("diagn_holiday:diagn_holiday:diagnHoliday:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "节假日配置数据导入模板.xlsx";
    		List<DiagnHoliday> list = Lists.newArrayList(); 
    		new ExportExcel("节假日配置数据", DiagnHoliday.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_holiday/diagn_holiday/diagnHoliday/?repage";
    }

}