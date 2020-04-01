/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.basis_coding_details.web.basis_coding_details;

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
import com.jeeplus.modules.basis_coding_details.entity.basis_coding_details.BasisCodingDetails;
import com.jeeplus.modules.basis_coding_details.service.basis_coding_details.BasisCodingDetailsService;

/**
 * 编码明细表Controller
 * @author 姜森焱
 * @version 2020-01-15
 */
@Controller
@RequestMapping(value = "${adminPath}/basis_coding_details/basis_coding_details/basisCodingDetails")
public class BasisCodingDetailsController extends BaseController {

	@Autowired
	private BasisCodingDetailsService basisCodingDetailsService;
	
	@ModelAttribute
	public BasisCodingDetails get(@RequestParam(required=false) String id) {
		BasisCodingDetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = basisCodingDetailsService.get(id);
		}
		if (entity == null){
			entity = new BasisCodingDetails();
		}
		return entity;
	}
	
	/**
	 * 编码明细列表页面
	 */
	@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/basis_coding_details/basis_coding_details/basisCodingDetailsList";
	}
	
		/**
	 * 编码明细列表数据
	 */
	@ResponseBody
	/*@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:list")*/
	@RequestMapping(value = "data")
	public Map<String, Object> data(BasisCodingDetails basisCodingDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BasisCodingDetails> page = basisCodingDetailsService.findPage(new Page<BasisCodingDetails>(request, response), basisCodingDetails); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑编码明细表单页面
	 */
	@RequiresPermissions(value={"basis_coding_details:basis_coding_details:basisCodingDetails:view","basis_coding_details:basis_coding_details:basisCodingDetails:add","basis_coding_details:basis_coding_details:basisCodingDetails:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(BasisCodingDetails basisCodingDetails, Model model) {
		model.addAttribute("basisCodingDetails", basisCodingDetails);
		if(StringUtils.isBlank(basisCodingDetails.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/basis_coding_details/basis_coding_details/basisCodingDetailsForm";
	}

	/**
	 * 保存编码明细
	 */
	@RequiresPermissions(value={"basis_coding_details:basis_coding_details:basisCodingDetails:add","basis_coding_details:basis_coding_details:basisCodingDetails:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(BasisCodingDetails basisCodingDetails, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, basisCodingDetails)){
			return form(basisCodingDetails, model);
		}
		//新增或编辑表单保存
		basisCodingDetailsService.save(basisCodingDetails);//保存
		addMessage(redirectAttributes, "保存编码明细成功");
		return "redirect:"+Global.getAdminPath()+"/basis_coding_details/basis_coding_details/basisCodingDetails/?repage";
	}
	
	/**
	 * 删除编码明细
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(BasisCodingDetails basisCodingDetails, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		basisCodingDetailsService.delete(basisCodingDetails);
		j.setMsg("删除编码明细成功");
		return j;
	}
	
	/**
	 * 批量删除编码明细
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			basisCodingDetailsService.delete(basisCodingDetailsService.get(id));
		}
		j.setMsg("删除编码明细成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(BasisCodingDetails basisCodingDetails, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "编码明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BasisCodingDetails> page = basisCodingDetailsService.findPage(new Page<BasisCodingDetails>(request, response, -1), basisCodingDetails);
    		new ExportExcel("编码明细", BasisCodingDetails.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出编码明细记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BasisCodingDetails> list = ei.getDataList(BasisCodingDetails.class);
			for (BasisCodingDetails basisCodingDetails : list){
				try{
					basisCodingDetailsService.save(basisCodingDetails);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条编码明细记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条编码明细记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入编码明细失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/basis_coding_details/basis_coding_details/basisCodingDetails/?repage";
    }
	
	/**
	 * 下载导入编码明细数据模板
	 */
	@RequiresPermissions("basis_coding_details:basis_coding_details:basisCodingDetails:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "编码明细数据导入模板.xlsx";
    		List<BasisCodingDetails> list = Lists.newArrayList(); 
    		new ExportExcel("编码明细数据", BasisCodingDetails.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/basis_coding_details/basis_coding_details/basisCodingDetails/?repage";
    }

}