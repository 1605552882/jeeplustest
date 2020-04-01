/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.basis_coding.web.basis_coding;

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
import com.jeeplus.modules.basis_coding.entity.basis_coding.BasisCoding;
import com.jeeplus.modules.basis_coding.service.basis_coding.BasisCodingService;

/**
 * 编码表Controller
 * @author 姜森焱
 * @version 2020-01-15
 */
@Controller
@RequestMapping(value = "${adminPath}/basis_coding/basis_coding/basisCoding")
public class BasisCodingController extends BaseController {

	@Autowired
	private BasisCodingService basisCodingService;
	
	@ModelAttribute
	public BasisCoding get(@RequestParam(required=false) String id) {
		BasisCoding entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = basisCodingService.get(id);
		}
		if (entity == null){
			entity = new BasisCoding();
		}
		return entity;
	}
	
	/**
	 * 编码列表页面
	 */
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/basis_coding/basis_coding/basisCodingList";
	}
	
		/**
	 * 编码列表数据
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(BasisCoding basisCoding, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BasisCoding> page = basisCodingService.findPage(new Page<BasisCoding>(request, response), basisCoding); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑编码表单页面
	 */
	@RequiresPermissions(value={"basis_coding:basis_coding:basisCoding:view","basis_coding:basis_coding:basisCoding:add","basis_coding:basis_coding:basisCoding:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(BasisCoding basisCoding, Model model) {
		model.addAttribute("basisCoding", basisCoding);
		if(StringUtils.isBlank(basisCoding.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/basis_coding/basis_coding/basisCodingForm";
	}

	/**
	 * 保存编码
	 */
	@RequiresPermissions(value={"basis_coding:basis_coding:basisCoding:add","basis_coding:basis_coding:basisCoding:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(BasisCoding basisCoding, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, basisCoding)){
			return form(basisCoding, model);
		}
		//新增或编辑表单保存
		basisCodingService.save(basisCoding);//保存
		addMessage(redirectAttributes, "保存编码成功");
		return "redirect:"+Global.getAdminPath()+"/basis_coding/basis_coding/basisCoding/?repage";
	}
	
	/**
	 * 删除编码
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(BasisCoding basisCoding, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		basisCodingService.delete(basisCoding);
		j.setMsg("删除编码成功");
		return j;
	}
	
	/**
	 * 批量删除编码
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			basisCodingService.delete(basisCodingService.get(id));
		}
		j.setMsg("删除编码成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(BasisCoding basisCoding, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "编码"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BasisCoding> page = basisCodingService.findPage(new Page<BasisCoding>(request, response, -1), basisCoding);
    		new ExportExcel("编码", BasisCoding.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出编码记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BasisCoding> list = ei.getDataList(BasisCoding.class);
			for (BasisCoding basisCoding : list){
				try{
					basisCodingService.save(basisCoding);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条编码记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条编码记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入编码失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/basis_coding/basis_coding/basisCoding/?repage";
    }
	
	/**
	 * 下载导入编码数据模板
	 */
	@RequiresPermissions("basis_coding:basis_coding:basisCoding:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "编码数据导入模板.xlsx";
    		List<BasisCoding> list = Lists.newArrayList(); 
    		new ExportExcel("编码数据", BasisCoding.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/basis_coding/basis_coding/basisCoding/?repage";
    }

}