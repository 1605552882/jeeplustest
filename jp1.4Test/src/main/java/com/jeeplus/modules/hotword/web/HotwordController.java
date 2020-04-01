/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hotword.web;

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
import com.jeeplus.modules.hotword.entity.Hotword;
import com.jeeplus.modules.hotword.service.HotwordService;

/**
 * 热词Controller
 * @author lxy
 * @version 2019-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hotword/hotword")
public class HotwordController extends BaseController {

	@Autowired
	private HotwordService hotwordService;
	
	@ModelAttribute
	public Hotword get(@RequestParam(required=false) String id) {
		Hotword entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hotwordService.get(id);
		}
		if (entity == null){
			entity = new Hotword();
		}
		return entity;
	}
	
	/**
	 * 热词列表页面
	 */
	@RequiresPermissions("hotword:hotword:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/hotword/hotwordList";
	}
	
		/**
	 * 热词列表数据
	 */
	@ResponseBody
	@RequiresPermissions("hotword:hotword:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Hotword hotword, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Hotword> page = hotwordService.findPage(new Page<Hotword>(request, response), hotword); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑热词表单页面
	 */
	@RequiresPermissions(value={"hotword:hotword:view","hotword:hotword:add","hotword:hotword:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Hotword hotword, Model model) {
		model.addAttribute("hotword", hotword);
		if(StringUtils.isBlank(hotword.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/hotword/hotwordForm";
	}

	/**
	 * 保存热词
	 */
	@RequiresPermissions(value={"hotword:hotword:add","hotword:hotword:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Hotword hotword, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hotword)){
			return form(hotword, model);
		}
		//新增或编辑表单保存
		hotwordService.save(hotword);//保存
		addMessage(redirectAttributes, "保存热词成功");
		return "redirect:"+Global.getAdminPath()+"/hotword/hotword/?repage";
	}
	
	/**
	 * 删除热词
	 */
	@ResponseBody
	@RequiresPermissions("hotword:hotword:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Hotword hotword, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		hotwordService.delete(hotword);
		j.setMsg("删除热词成功");
		return j;
	}
	
	/**
	 * 批量删除热词
	 */
	@ResponseBody
	@RequiresPermissions("hotword:hotword:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hotwordService.delete(hotwordService.get(id));
		}
		j.setMsg("删除热词成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("hotword:hotword:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Hotword hotword, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "热词"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Hotword> page = hotwordService.findPage(new Page<Hotword>(request, response, -1), hotword);
    		new ExportExcel("热词", Hotword.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出热词记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("hotword:hotword:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Hotword> list = ei.getDataList(Hotword.class);
			for (Hotword hotword : list){
				try{
					hotwordService.save(hotword);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条热词记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条热词记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入热词失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hotword/hotword/?repage";
    }
	
	/**
	 * 下载导入热词数据模板
	 */
	@RequiresPermissions("hotword:hotword:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "热词数据导入模板.xlsx";
    		List<Hotword> list = Lists.newArrayList(); 
    		new ExportExcel("热词数据", Hotword.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hotword/hotword/?repage";
    }

}