/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mscid_config.web.mscid_config;

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
import com.jeeplus.modules.mscid_config.entity.mscid_config.MscidConfig;
import com.jeeplus.modules.mscid_config.service.mscid_config.MscidConfigService;

/**
 * MSCID配置Controller
 * @author 姜森焱
 * @version 2020-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/mscid_config/mscid_config/mscidConfig")
public class MscidConfigController extends BaseController {

	@Autowired
	private MscidConfigService mscidConfigService;
	
	@ModelAttribute
	public MscidConfig get(@RequestParam(required=false) String id) {
		MscidConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mscidConfigService.get(id);
		}
		if (entity == null){
			entity = new MscidConfig();
		}
		return entity;
	}
	
	/**
	 * MSCID配置列表页面
	 */
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mscid_config/mscid_config/mscidConfigList";
	}
	
		/**
	 * MSCID配置列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MscidConfig mscidConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MscidConfig> page = mscidConfigService.findPage(new Page<MscidConfig>(request, response), mscidConfig); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑MSCID配置表单页面
	 */
	@RequiresPermissions(value={"mscid_config:mscid_config:mscidConfig:view","mscid_config:mscid_config:mscidConfig:add","mscid_config:mscid_config:mscidConfig:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MscidConfig mscidConfig, Model model) {
		model.addAttribute("mscidConfig", mscidConfig);
		if(StringUtils.isBlank(mscidConfig.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/mscid_config/mscid_config/mscidConfigForm";
	}

	/**
	 * 保存MSCID配置
	 */
	@RequiresPermissions(value={"mscid_config:mscid_config:mscidConfig:add","mscid_config:mscid_config:mscidConfig:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(MscidConfig mscidConfig, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, mscidConfig)){
			return form(mscidConfig, model);
		}
		//新增或编辑表单保存
		mscidConfigService.save(mscidConfig);//保存
		addMessage(redirectAttributes, "保存MSCID配置成功");
		return "redirect:"+Global.getAdminPath()+"/mscid_config/mscid_config/mscidConfig/?repage";
	}
	
	/**
	 * 删除MSCID配置
	 */
	@ResponseBody
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MscidConfig mscidConfig, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mscidConfigService.delete(mscidConfig);
		j.setMsg("删除MSCID配置成功");
		return j;
	}
	
	/**
	 * 批量删除MSCID配置
	 */
	@ResponseBody
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mscidConfigService.delete(mscidConfigService.get(id));
		}
		j.setMsg("删除MSCID配置成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MscidConfig mscidConfig, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "MSCID配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MscidConfig> page = mscidConfigService.findPage(new Page<MscidConfig>(request, response, -1), mscidConfig);
    		new ExportExcel("MSCID配置", MscidConfig.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出MSCID配置记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MscidConfig> list = ei.getDataList(MscidConfig.class);
			for (MscidConfig mscidConfig : list){
				try{
					mscidConfigService.save(mscidConfig);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条MSCID配置记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条MSCID配置记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入MSCID配置失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mscid_config/mscid_config/mscidConfig/?repage";
    }
	
	/**
	 * 下载导入MSCID配置数据模板
	 */
	@RequiresPermissions("mscid_config:mscid_config:mscidConfig:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "MSCID配置数据导入模板.xlsx";
    		List<MscidConfig> list = Lists.newArrayList(); 
    		new ExportExcel("MSCID配置数据", MscidConfig.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mscid_config/mscid_config/mscidConfig/?repage";
    }

}