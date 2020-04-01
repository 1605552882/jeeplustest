/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.demoone.web.demoone;

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
import com.jeeplus.modules.demoone.entity.demoone.Demo_one;
import com.jeeplus.modules.demoone.service.demoone.Demo_oneService;

/**
 * 作为测试Controller
 * @author 姜森焱
 * @version 2019-09-29
 */
@Controller
@RequestMapping(value = "${adminPath}/demoone/demoone/demo_one")
public class Demo_oneController extends BaseController {

	@Autowired
	private Demo_oneService demo_oneService;
	
	@ModelAttribute
	public Demo_one get(@RequestParam(required=false) String id) {
		Demo_one entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demo_oneService.get(id);
		}
		if (entity == null){
			entity = new Demo_one();
		}
		return entity;
	}
	
	/**
	 * jsy测试列表页面
	 */
	@RequiresPermissions("demoone:demoone:demo_one:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/demoone/demoone/demo_oneList";
	}
	
		/**
	 * jsy测试列表数据
	 */
	@ResponseBody
	@RequiresPermissions("demoone:demoone:demo_one:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Demo_one demo_one, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Demo_one> page = demo_oneService.findPage(new Page<Demo_one>(request, response), demo_one); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑jsy测试表单页面
	 */
	@RequiresPermissions(value={"demoone:demoone:demo_one:view","demoone:demoone:demo_one:add","demoone:demoone:demo_one:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Demo_one demo_one, Model model) {
		model.addAttribute("demo_one", demo_one);
		return "modules/demoone/demoone/demo_oneForm";
	}

	/**
	 * 保存jsy测试
	 */
	@ResponseBody
	@RequiresPermissions(value={"demoone:demoone:demo_one:add","demoone:demoone:demo_one:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Demo_one demo_one, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, demo_one)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		demo_oneService.save(demo_one);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存jsy测试成功");
		return j;
	}
	
	/**
	 * 删除jsy测试
	 */
	@ResponseBody
	@RequiresPermissions("demoone:demoone:demo_one:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Demo_one demo_one, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		demo_oneService.delete(demo_one);
		j.setMsg("删除jsy测试成功");
		return j;
	}
	
	/**
	 * 批量删除jsy测试
	 */
	@ResponseBody
	@RequiresPermissions("demoone:demoone:demo_one:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			demo_oneService.delete(demo_oneService.get(id));
		}
		j.setMsg("删除jsy测试成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("demoone:demoone:demo_one:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Demo_one demo_one, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "jsy测试"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Demo_one> page = demo_oneService.findPage(new Page<Demo_one>(request, response, -1), demo_one);
    		new ExportExcel("jsy测试", Demo_one.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出jsy测试记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("demoone:demoone:demo_one:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Demo_one> list = ei.getDataList(Demo_one.class);
			for (Demo_one demo_one : list){
				try{
					demo_oneService.save(demo_one);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条jsy测试记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条jsy测试记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入jsy测试失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/demoone/demoone/demo_one/?repage";
    }
	
	/**
	 * 下载导入jsy测试数据模板
	 */
	@RequiresPermissions("demoone:demoone:demo_one:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "jsy测试数据导入模板.xlsx";
    		List<Demo_one> list = Lists.newArrayList(); 
    		new ExportExcel("jsy测试数据", Demo_one.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/demoone/demoone/demo_one/?repage";
    }

}