/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.interface_config.web.interface_config;

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
import com.jeeplus.modules.interface_config.entity.interface_config.InterfaceConfig;
import com.jeeplus.modules.interface_config.service.interface_config.InterfaceConfigService;

/**
 * 测试编码表Controller
 * @author 姜森焱
 * @version 2020-02-04
 */
@Controller
@RequestMapping(value = "${adminPath}/interface_config/interface_config/interfaceConfig")
public class InterfaceConfigController extends BaseController {

	@Autowired
	private InterfaceConfigService interfaceConfigService;
	
	@ModelAttribute
	public InterfaceConfig get(@RequestParam(required=false) String id) {
		InterfaceConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = interfaceConfigService.get(id);
		}
		if (entity == null){
			entity = new InterfaceConfig();
		}
		return entity;
	}
	
	/**
	 * 测试编码列表页面
	 */
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/interface_config/interface_config/interfaceConfigList";
	}
	
		/**
	 * 测试编码列表数据
	 */
	@ResponseBody
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(InterfaceConfig interfaceConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InterfaceConfig> page = interfaceConfigService.findPage(new Page<InterfaceConfig>(request, response), interfaceConfig); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑测试编码表单页面
	 */
	@RequiresPermissions(value={"interface_config:interface_config:interfaceConfig:view","interface_config:interface_config:interfaceConfig:add","interface_config:interface_config:interfaceConfig:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(InterfaceConfig interfaceConfig, Model model) {
		model.addAttribute("interfaceConfig", interfaceConfig);
		return "modules/interface_config/interface_config/interfaceConfigForm";
	}

	/**
	 * 保存测试编码
	 */
	@ResponseBody
	@RequiresPermissions(value={"interface_config:interface_config:interfaceConfig:add","interface_config:interface_config:interfaceConfig:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(InterfaceConfig interfaceConfig, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, interfaceConfig)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		interfaceConfigService.save(interfaceConfig);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存测试编码成功");
		return j;
	}
	
	/**
	 * 删除测试编码
	 */
	@ResponseBody
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(InterfaceConfig interfaceConfig, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		interfaceConfigService.delete(interfaceConfig);
		j.setMsg("删除测试编码成功");
		return j;
	}
	
	/**
	 * 批量删除测试编码
	 */
	@ResponseBody
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			interfaceConfigService.delete(interfaceConfigService.get(id));
		}
		j.setMsg("删除测试编码成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(InterfaceConfig interfaceConfig, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "测试编码"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<InterfaceConfig> page = interfaceConfigService.findPage(new Page<InterfaceConfig>(request, response, -1), interfaceConfig);
    		new ExportExcel("测试编码", InterfaceConfig.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出测试编码记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<InterfaceConfig> list = ei.getDataList(InterfaceConfig.class);
			for (InterfaceConfig interfaceConfig : list){
				try{
					interfaceConfigService.save(interfaceConfig);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条测试编码记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条测试编码记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入测试编码失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/interface_config/interface_config/interfaceConfig/?repage";
    }
	
	/**
	 * 下载导入测试编码数据模板
	 */
	@RequiresPermissions("interface_config:interface_config:interfaceConfig:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "测试编码数据导入模板.xlsx";
    		List<InterfaceConfig> list = Lists.newArrayList(); 
    		new ExportExcel("测试编码数据", InterfaceConfig.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/interface_config/interface_config/interfaceConfig/?repage";
    }

}