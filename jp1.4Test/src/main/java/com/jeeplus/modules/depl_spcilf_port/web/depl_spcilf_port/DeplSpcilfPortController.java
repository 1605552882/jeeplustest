/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.depl_spcilf_port.web.depl_spcilf_port;

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
import com.jeeplus.modules.depl_spcilf_port.entity.depl_spcilf_port.DeplSpcilfPort;
import com.jeeplus.modules.depl_spcilf_port.service.depl_spcilf_port.DeplSpcilfPortService;

/**
 * 机器部署端口Controller
 * @author 姜森焱
 * @version 2020-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort")
public class DeplSpcilfPortController extends BaseController {

	@Autowired
	private DeplSpcilfPortService deplSpcilfPortService;
	
	@ModelAttribute
	public DeplSpcilfPort get(@RequestParam(required=false) String id) {
		DeplSpcilfPort entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deplSpcilfPortService.get(id);
		}
		if (entity == null){
			entity = new DeplSpcilfPort();
		}
		return entity;
	}
	
	/**
	 * 机器部署端口列表页面
	 */
	@RequiresPermissions("depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/depl_spcilf_port/depl_spcilf_port/deplSpcilfPortList";
	}
	
		/**
	 * 机器部署端口列表数据
	 */
	@ResponseBody
	@RequestMapping(value = "data")
	public Map<String, Object> data(DeplSpcilfPort deplSpcilfPort, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeplSpcilfPort> page = deplSpcilfPortService.findPage(new Page<DeplSpcilfPort>(request, response), deplSpcilfPort); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑机器部署端口表单页面(只负责添加)
	 */
	@RequiresPermissions(value={"depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:view","depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:add","depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DeplSpcilfPort deplSpcilfPort, Model model) {
		model.addAttribute("deplSpcilfPort", deplSpcilfPort);
		model.addAttribute("isadd", true);
		return "modules/depl_spcilf_port/depl_spcilf_port/deplSpcilfPortForm";
	}
	
	/**
	 * 查看，增加，编辑机器部署端口表单页面
	 */
	@RequiresPermissions(value={"depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:view","depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:add","depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit"},logical=Logical.OR)
	@RequestMapping(value = "form1")
	public String form1(DeplSpcilfPort deplSpcilfPort, Model model) {
		model.addAttribute("deplSpcilfPort", deplSpcilfPort);
		model.addAttribute("isadd", false);
		
		return "modules/depl_spcilf_port/depl_spcilf_port/deplSpcilfPortForm";
	}


	/**
	 * 保存机器部署端口
	 */
	@ResponseBody
	@RequiresPermissions(value={"depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:add","depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DeplSpcilfPort deplSpcilfPort, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, deplSpcilfPort)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		deplSpcilfPortService.save(deplSpcilfPort);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存机器部署端口成功");
		return j;
	}
	
	/**
	 * 删除机器部署端口
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DeplSpcilfPort deplSpcilfPort, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		deplSpcilfPortService.delete(deplSpcilfPort);
		j.setMsg("删除机器部署端口成功");
		return j;
	}
	
	/**
	 * 批量删除机器部署端口
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			deplSpcilfPortService.delete(deplSpcilfPortService.get(id));
		}
		j.setMsg("删除机器部署端口成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(DeplSpcilfPort deplSpcilfPort, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "机器部署端口"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DeplSpcilfPort> page = deplSpcilfPortService.findPage(new Page<DeplSpcilfPort>(request, response, -1), deplSpcilfPort);
    		new ExportExcel("机器部署端口", DeplSpcilfPort.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出机器部署端口记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DeplSpcilfPort> list = ei.getDataList(DeplSpcilfPort.class);
			for (DeplSpcilfPort deplSpcilfPort : list){
				try{
					deplSpcilfPortService.save(deplSpcilfPort);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条机器部署端口记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条机器部署端口记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入机器部署端口失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/?repage";
    }
	
	/**
	 * 下载导入机器部署端口数据模板
	 */
	@RequiresPermissions("depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "机器部署端口数据导入模板.xlsx";
    		List<DeplSpcilfPort> list = Lists.newArrayList(); 
    		new ExportExcel("机器部署端口数据", DeplSpcilfPort.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/?repage";
    }

}