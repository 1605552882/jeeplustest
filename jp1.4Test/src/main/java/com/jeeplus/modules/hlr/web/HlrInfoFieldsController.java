/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hlr.web;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
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
import com.jeeplus.common.socket.SocketTool;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.ToolFunctions;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.hlr.entity.HlrInfoFields;
import com.jeeplus.modules.hlr.service.HlrInfoFieldsService;
import com.jeeplus.modules.sys.interceptor.LogInterceptor;

/**
 * hlr信息管理Controller
 * 
 * @author 钟晖
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hlr/hlrInfoFields")
public class HlrInfoFieldsController extends BaseController {

	@Autowired
	private HlrInfoFieldsService hlrInfoFieldsService;

	@ModelAttribute
	public HlrInfoFields get(@RequestParam(required = false) String id) {
		HlrInfoFields entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = hlrInfoFieldsService.get(id);
		}
		if (entity == null) {
			entity = new HlrInfoFields();
		}
		return entity;
	}

	/**
	 * hlr信息管理列表页面
	 */
	@RequiresPermissions("hlr:hlrInfoFields:list")
	@RequestMapping(value = { "list", "" })
	public String list() {
		return "modules/hlr/hlrInfoFieldsList3";
	}

	public void decode(HlrInfoFields hc, String codeStr) throws Exception, IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Class<?> classType = hc.getClass();
		Field[] fields = classType.getDeclaredFields();

		codeStr = codeStr.substring(0, 28) + "|" + codeStr.substring(28); // 将程控位与呼转号码分离
		// 通过默认构造方法创建一个新的对象
		String[] codeArr = codeStr.split("\\|");

		// 其他信息
		for (int j = 1; j < codeArr.length; j++) {
			Field field = fields[25 + j];
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
			setMethod.invoke(hc, new Object[] { codeArr[j] });

		}
		// 28位程控位
		for (int j = 0; j < codeArr[0].length() && j < 26; j++) {
			Field field = fields[j];
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });

			setMethod.invoke(hc, new Object[] { String.valueOf(codeArr[0].charAt(j)) });
		}
		// 设定程控位
		Field field = fields[fields.length - 1];
		String fieldName = field.getName();
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String setMethodName = "set" + firstLetter + fieldName.substring(1);
		Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });

		setMethod.invoke(hc, new Object[] { String.valueOf(codeArr[0]) });
	}
	private String getPhoneFeeType(HttpServletRequest request, String mdn) {
		String cmd = "QUERY|01|" + mdn;
		try {
			String result = SocketTool.sendCmd(request.getSession(), cmd);
			String phoneFeeType = result.split("\\|", -1)[3];
			return phoneFeeType;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * hlr信息管理列表数据
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequiresPermissions("hlr:hlrInfoFields:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(HlrInfoFields hlrInfoFields, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
//		Page<HlrInfoFields> page = hlrInfoFieldsService.findPage(new Page<HlrInfoFields>(request, response),
//				hlrInfoFields);
		//此处如需要开通socket要挂vpn开通17的8080后台
		String conn_Id=LogInterceptor.getConnId();
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String userNoValue = request.getParameter("userNo");
		String searchType = request.getParameter("queryType");
		HlrInfoFields hlrInfo = new HlrInfoFields();
		String cmd = "HLRQRY|" + userNoValue;
		String systemReturn = "";//SocketTool.sendCmd(request.getSession(), cmd);
		System.out.println("systemReturn:" + systemReturn);
		String message = ToolFunctions.getMessage(systemReturn, cmd);
		if (message.equals("") == true) {
			systemReturn = systemReturn.replace("| ", "|"); // 为了接下来的解码排除空格的干扰做准备
			String[] tempSystemReturnArray = systemReturn.split("\\|", -1);
			String systemReturnPart = tempSystemReturnArray[0] + "|" + tempSystemReturnArray[1] + "|"
					+ tempSystemReturnArray[2] + "|" + tempSystemReturnArray[3] + "|" + tempSystemReturnArray[4] + "|"
					+ tempSystemReturnArray[5] + "|" + tempSystemReturnArray[6] + "|" + tempSystemReturnArray[7] + "|"
					+ tempSystemReturnArray[8];

			decode(hlrInfo, systemReturnPart.substring(cmd.length()));
			if (tempSystemReturnArray != null && tempSystemReturnArray.length > 9) {
				String[] tempExtendArray = tempSystemReturnArray[9].split("\\#@", -1);
				hlrInfo.setRruaType(tempExtendArray[0]);
				hlrInfo.setRruaStatus(tempExtendArray[1]);
				hlrInfo.setUzid1(tempExtendArray[2]);
				hlrInfo.setUzid2(tempExtendArray[3]);
				hlrInfo.setUzid3(tempExtendArray[4]);
				hlrInfo.setUzid4(tempExtendArray[5]);
				hlrInfo.setUzid5(tempExtendArray[6]);
				hlrInfo.setUzid6(tempExtendArray[7]);
			}
		} else {
			operateSuccessFlg = false;
		}
		String phoneFeeType = "";//this.getPhoneFeeType(request, userNoValue);
		Map<String, Object> map = new HashMap<String, Object>();
//		HlrInfoFields info = new HlrInfoFields();
//		info.setApplyChain("1");
//		info.setAppreciation("0");
//		info.setArea("广州");
//		info.setBase1xFunction("1");
//		info.setBetChain("1");
//		info.setBrtFunction("0");
//		info.setCallDisplay1("1");
//		info.setCallDisplay2("0");
//		info.setCallKeeping("0");
//		info.setCallLimit("1");
//		info.setCallWaiting("1");
//		info.setCallingPrivileges("2");
//		info.setCfmn("1");
//		info.setCopyChain("1");
//		info.setDefaultCall("0");
//		info.setDefaultCallNo("0");
//		info.setFunctionCode("B000010221331000710011111100");
//		info.setMultiCall("0");
//		info.setNetWork("7");
//		info.setNoAnswer("0");
//		info.setNoAnswerNo("");
//		info.setNoDisturb("0");
//		info.setPhoneType("HW");
//		info.setProvince("广东省");
//		info.setRabedChain("1");
//		info.setRoamingPrivileges("2");
//		info.setRruaStatus("");
//		info.setRruaType("3");
//		info.setSearchResult("460036241123673");
//		info.setSmsAnswerCall("3");
//		info.setSmsFunction("1");
//		info.setSmsOriginalCall("3");
//		info.setUserStatus("8");
//		info.setUzid1("");
//		info.setUzid2("");
//		info.setUzid3("");
//		info.setUzid4("");
//		info.setUzid5("");
//		info.setUzid6("");
//		info.setVolte("0");
//		info.setWhenBusy("0");
//		info.setWhenBusyNo("");
//		info.setWithoutCondition("0");
//		info.setWithoutConditionNo("");
		map.put("hlrInfo", hlrInfo);
		map.put("conn_Id", conn_Id);
		map.put("success", true);
		return map;
//		return getBootstrapData(page);
	}
	/**
	 * hlr修改相关信息,修改完之后继续查询
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequiresPermissions("hlr:hlrInfoFields:list")
	@RequestMapping(value = "modify")
	public Map<String, Object> modify(HlrInfoFields hlrInfoFields, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String userNoValue = request.getParameter("userNo");
		String searchType = request.getParameter("queryType");
		HlrInfoFields hlrInfo = new HlrInfoFields();
		String cmd = "HLRQRY|" + userNoValue;
		String systemReturn = SocketTool.sendCmd(request.getSession(), cmd);
		System.out.println("systemReturn:" + systemReturn);
		String message = ToolFunctions.getMessage(systemReturn, cmd);
		if (message.equals("") == true) {
			systemReturn = systemReturn.replace("| ", "|"); // 为了接下来的解码排除空格的干扰做准备
			String[] tempSystemReturnArray = systemReturn.split("\\|", -1);
			String systemReturnPart = tempSystemReturnArray[0] + "|" + tempSystemReturnArray[1] + "|"
					+ tempSystemReturnArray[2] + "|" + tempSystemReturnArray[3] + "|" + tempSystemReturnArray[4] + "|"
					+ tempSystemReturnArray[5] + "|" + tempSystemReturnArray[6] + "|" + tempSystemReturnArray[7] + "|"
					+ tempSystemReturnArray[8];

			decode(hlrInfo, systemReturnPart.substring(cmd.length()));
			if (tempSystemReturnArray != null && tempSystemReturnArray.length > 9) {
				String[] tempExtendArray = tempSystemReturnArray[9].split("\\#@", -1);
				hlrInfo.setRruaType(tempExtendArray[0]);
				hlrInfo.setRruaStatus(tempExtendArray[1]);
				hlrInfo.setUzid1(tempExtendArray[2]);
				hlrInfo.setUzid2(tempExtendArray[3]);
				hlrInfo.setUzid3(tempExtendArray[4]);
				hlrInfo.setUzid4(tempExtendArray[5]);
				hlrInfo.setUzid5(tempExtendArray[6]);
				hlrInfo.setUzid6(tempExtendArray[7]);
			}
		} else {
			operateSuccessFlg = false;
		}
		String phoneFeeType = this.getPhoneFeeType(request, userNoValue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hlrInfo", hlrInfo);
		map.put("success", true);
		return map;
//		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑hlr信息管理表单页面
	 */
	@RequiresPermissions(value = { "hlr:hlrInfoFields:view", "hlr:hlrInfoFields:add",
			"hlr:hlrInfoFields:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(HlrInfoFields hlrInfoFields, Model model) {
		model.addAttribute("hlrInfoFields", hlrInfoFields);
		return "modules/hlr/hlrInfoFieldsForm";
	}

	/**
	 * 保存hlr信息管理
	 */
	@ResponseBody
	@RequiresPermissions(value = { "hlr:hlrInfoFields:add", "hlr:hlrInfoFields:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(HlrInfoFields hlrInfoFields, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, hlrInfoFields)) {
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		hlrInfoFieldsService.save(hlrInfoFields);// 新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存hlr信息管理成功");
		return j;
	}

	/**
	 * 删除hlr信息管理
	 */
	@ResponseBody
	@RequiresPermissions("hlr:hlrInfoFields:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(HlrInfoFields hlrInfoFields, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		hlrInfoFieldsService.delete(hlrInfoFields);
		j.setMsg("删除hlr信息管理成功");
		return j;
	}

	/**
	 * 批量删除hlr信息管理
	 */
	@ResponseBody
	@RequiresPermissions("hlr:hlrInfoFields:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			hlrInfoFieldsService.delete(hlrInfoFieldsService.get(id));
		}
		j.setMsg("删除hlr信息管理成功");
		return j;
	}

	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("hlr:hlrInfoFields:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public AjaxJson exportFile(HlrInfoFields hlrInfoFields, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
			String fileName = "hlr信息管理" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<HlrInfoFields> page = hlrInfoFieldsService.findPage(new Page<HlrInfoFields>(request, response, -1),
					hlrInfoFields);
			new ExportExcel("hlr信息管理", HlrInfoFields.class).setDataList(page.getList()).write(response, fileName)
					.dispose();
			j.setSuccess(true);
			j.setMsg("导出成功！");
			return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出hlr信息管理记录失败！失败信息：" + e.getMessage());
		}
		return j;
	}

	/**
	 * 导入Excel数据
	 * 
	 */
	@RequiresPermissions("hlr:hlrInfoFields:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HlrInfoFields> list = ei.getDataList(HlrInfoFields.class);
			for (HlrInfoFields hlrInfoFields : list) {
				try {
					hlrInfoFieldsService.save(hlrInfoFields);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条hlr信息管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条hlr信息管理记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入hlr信息管理失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hlr/hlrInfoFields/?repage";
	}

	/**
	 * 下载导入hlr信息管理数据模板
	 */
	@RequiresPermissions("hlr:hlrInfoFields:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "hlr信息管理数据导入模板.xlsx";
			List<HlrInfoFields> list = Lists.newArrayList();
			new ExportExcel("hlr信息管理数据", HlrInfoFields.class, 1).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hlr/hlrInfoFields/?repage";
	}

}