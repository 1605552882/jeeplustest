/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pri_number_head.web.pri_number_head;

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
import com.jeeplus.modules.pri_number_head.entity.pri_number_head.PriNumberHead;
import com.jeeplus.modules.pri_number_head.service.pri_number_head.PriNumberHeadService;

/**
 * 号码头归属地表Controller
 * @author 姜森焱
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/pri_number_head/pri_number_head/priNumberHead")
public class PriNumberHeadController extends BaseController {

	@Autowired
	private PriNumberHeadService priNumberHeadService;
	
	@ModelAttribute
	public PriNumberHead get(@RequestParam(required=false) String id) {
		PriNumberHead entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = priNumberHeadService.get(id);
		}
		if (entity == null){
			entity = new PriNumberHead();
		}
		return entity;
	}
	
	/**
	 * 号码头列表页面
	 */
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/pri_number_head/pri_number_head/priNumberHeadList";
	}
	
		/**
	 * 号码头列表数据
	 */
	@ResponseBody
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(PriNumberHead priNumberHead, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PriNumberHead> page = priNumberHeadService.findPage(new Page<PriNumberHead>(request, response), priNumberHead); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑号码头表单页面
	 */
	@RequiresPermissions(value={"pri_number_head:pri_number_head:priNumberHead:view","pri_number_head:pri_number_head:priNumberHead:add","pri_number_head:pri_number_head:priNumberHead:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PriNumberHead priNumberHead, Model model) {
		model.addAttribute("priNumberHead", priNumberHead);
		return "modules/pri_number_head/pri_number_head/priNumberHeadForm";
	}

	/**
	 * 保存号码头
	 */
	@ResponseBody
	@RequiresPermissions(value={"pri_number_head:pri_number_head:priNumberHead:add","pri_number_head:pri_number_head:priNumberHead:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(PriNumberHead priNumberHead, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, priNumberHead)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		priNumberHeadService.save(priNumberHead);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存号码头成功");
		return j;
	}
	
	/**
	 * 删除号码头
	 */
	@ResponseBody
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(PriNumberHead priNumberHead, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		priNumberHeadService.delete(priNumberHead);
		j.setMsg("删除号码头成功");
		return j;
	}
	
	/**
	 * 批量删除号码头
	 */
	@ResponseBody
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			priNumberHeadService.delete(priNumberHeadService.get(id));
		}
		j.setMsg("删除号码头成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(PriNumberHead priNumberHead, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "号码头"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PriNumberHead> page = priNumberHeadService.findPage(new Page<PriNumberHead>(request, response, -1), priNumberHead);
    		new ExportExcel("号码头", PriNumberHead.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出号码头记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PriNumberHead> list = ei.getDataList(PriNumberHead.class);
			for (PriNumberHead priNumberHead : list){
				try{
					priNumberHeadService.save(priNumberHead);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条号码头记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条号码头记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入号码头失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pri_number_head/pri_number_head/priNumberHead/?repage";
    }
	
	/**
	 * 下载导入号码头数据模板
	 */
	@RequiresPermissions("pri_number_head:pri_number_head:priNumberHead:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "号码头数据导入模板.xlsx";
    		List<PriNumberHead> list = Lists.newArrayList(); 
    		new ExportExcel("号码头数据", PriNumberHead.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pri_number_head/pri_number_head/priNumberHead/?repage";
    }

}