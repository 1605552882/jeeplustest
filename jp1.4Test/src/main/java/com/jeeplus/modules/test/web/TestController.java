/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.web;

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
import com.jeeplus.modules.test.entity.validation.TestValidation;
import com.jeeplus.modules.test.service.validation.TestValidationService;

/**
 * 测试校验功能Controller
 * @author lgf
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/test/TestController")
public class TestController extends BaseController {

	@Autowired
	private TestValidationService testValidationService;
	
	/**
	 * 测试校验列表页面
	 */
	@RequiresPermissions("test:test/TestController:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/test/myTestList";
	}
	
		/**
	 * 测试校验列表数据
	 */
	@ResponseBody
	@RequiresPermissions("test:validation:testValidation:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(TestValidation testValidation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestValidation> page = testValidationService.findPage(new Page<TestValidation>(request, response), testValidation); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑测试校验表单页面
	 */
	@RequiresPermissions(value={"test:validation:testValidation:view","test:validation:testValidation:add","test:validation:testValidation:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TestValidation testValidation, Model model) {
		model.addAttribute("testValidation", testValidation);
		if(StringUtils.isBlank(testValidation.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/test/validation/testValidationForm";
	}

	/**
	 * 保存测试校验
	 */
	@RequiresPermissions(value={"test:validation:testValidation:add","test:validation:testValidation:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(TestValidation testValidation, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, testValidation)){
			return form(testValidation, model);
		}
		//新增或编辑表单保存
		testValidationService.save(testValidation);//保存
		addMessage(redirectAttributes, "保存测试校验成功");
		return "redirect:"+Global.getAdminPath()+"/test/validation/testValidation/?repage";
	}
	
	/**
	 * 删除测试校验
	 */
	@ResponseBody
	@RequiresPermissions("test:validation:testValidation:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(TestValidation testValidation, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		testValidationService.delete(testValidation);
		j.setMsg("删除测试校验成功");
		return j;
	}
	
	/**
	 * 批量删除测试校验
	 */
	@ResponseBody
	@RequiresPermissions("test:validation:testValidation:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			testValidationService.delete(testValidationService.get(id));
		}
		j.setMsg("删除测试校验成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("test:validation:testValidation:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(TestValidation testValidation, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "测试校验"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TestValidation> page = testValidationService.findPage(new Page<TestValidation>(request, response, -1), testValidation);
    		new ExportExcel("测试校验", TestValidation.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出测试校验记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("test:validation:testValidation:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TestValidation> list = ei.getDataList(TestValidation.class);
			for (TestValidation testValidation : list){
				try{
					testValidationService.save(testValidation);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条测试校验记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条测试校验记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入测试校验失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/validation/testValidation/?repage";
    }
	
	/**
	 * 下载导入测试校验数据模板
	 */
	@RequiresPermissions("test:validation:testValidation:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "测试校验数据导入模板.xlsx";
    		List<TestValidation> list = Lists.newArrayList(); 
    		new ExportExcel("测试校验数据", TestValidation.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/validation/testValidation/?repage";
    }

}