/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.depl_spcilf_ip.web.depl_spcilf_ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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
import com.jeeplus.modules.depl_spcilf_ip.entity.depl_spcilf_ip.DeplSpcilfIp;
import com.jeeplus.modules.depl_spcilf_ip.service.depl_spcilf_ip.DeplSpcilfIpService;

/**
 * 机器部署信息Controller
 * 
 * @author 姜森焱
 * @version 2020-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp")
public class DeplSpcilfIpController extends BaseController {

	@Autowired
	private DeplSpcilfIpService deplSpcilfIpService;

	@ModelAttribute
	public DeplSpcilfIp get(@RequestParam(required = false) String id) {
		DeplSpcilfIp entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = deplSpcilfIpService.get(id);
		}
		if (entity == null) {
			entity = new DeplSpcilfIp();
		}
		return entity;
	}

	/**
	 * 机器部署信息列表页面
	 */
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:list")
	@RequestMapping(value = { "list", "" })
	public String list() {
		return "modules/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIpList";
	}

	/**
	 * 机器部署信息列表数据
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DeplSpcilfIp deplSpcilfIp, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<DeplSpcilfIp> page = deplSpcilfIpService.findPage(new Page<DeplSpcilfIp>(request, response), deplSpcilfIp);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑机器部署信息表单页面
	 */
	@RequiresPermissions(value = { "depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:view",
			"depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:add",
			"depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(DeplSpcilfIp deplSpcilfIp, Model model) {
		model.addAttribute("deplSpcilfIp", deplSpcilfIp);
		return "modules/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIpForm";
	}

	/**
	 * 保存机器部署信息
	 */
	@ResponseBody
	@RequiresPermissions(value = { "depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:add",
			"depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DeplSpcilfIp deplSpcilfIp, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, deplSpcilfIp)) {
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		deplSpcilfIpService.save(deplSpcilfIp);// 新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存机器部署信息成功");
		return j;
	}

	/**
	 * 删除机器部署信息
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DeplSpcilfIp deplSpcilfIp, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		deplSpcilfIpService.delete(deplSpcilfIp);
		j.setMsg("删除机器部署信息成功");
		return j;
	}

	/**
	 * 批量删除机器部署信息
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			deplSpcilfIpService.delete(deplSpcilfIpService.get(id));
		}
		j.setMsg("删除机器部署信息成功");
		return j;
	}

	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public AjaxJson exportFile(DeplSpcilfIp deplSpcilfIp, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
			String fileName = "机器部署信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<DeplSpcilfIp> page = deplSpcilfIpService.findPage(new Page<DeplSpcilfIp>(request, response, -1),
					deplSpcilfIp);
			new ExportExcel("机器部署信息", DeplSpcilfIp.class).setDataList(page.getList()).write(response, fileName)
					.dispose();
			j.setSuccess(true);
			j.setMsg("导出成功！");
			return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出机器部署信息记录失败！失败信息：" + e.getMessage());
		}
		return j;
	}

	/**
	 * 导入Excel数据
	 * 
	 */
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DeplSpcilfIp> list = ei.getDataList(DeplSpcilfIp.class);
			for (DeplSpcilfIp deplSpcilfIp : list) {
				try {
					deplSpcilfIpService.save(deplSpcilfIp);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条机器部署信息记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条机器部署信息记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入机器部署信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/?repage";
	}

	/**
	 * 下载导入机器部署信息数据模板
	 */
	@RequiresPermissions("depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "机器部署信息数据导入模板.xlsx";
			List<DeplSpcilfIp> list = Lists.newArrayList();
			new ExportExcel("机器部署信息数据", DeplSpcilfIp.class, 1).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/?repage";
	}

	@RequestMapping(value = "ping")
	public String ping(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			
		
		String ip = request.getParameter("ip");
		int port = 0;
		String json = null;
		if (request.getParameter("port") != null) {
			port = Integer.parseInt(request.getParameter("port"));
			if (PingPort(ip, port)) {
				json = "{ success:true,msg:'PingIP端口成功' }";
			} else {
				json = "{ success:false,msg:'PingIP端口失败' }";
			}
		} else {
			if (Ping(ip)) {
				json = "{ success:true,msg:'PingIP成功' }";
			} else {
				json = "{ success:false,msg:'PingIP失败' }";
			}
		}
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * 连接socket ip是否ping通
	 * @param ip
	 * @return
	 * @throws IOException
	 */
	public static boolean Ping(String ip) throws IOException {
		String cmd = "";
		if (System.getProperties().getProperty("os.name").indexOf("Windows") != -1) {
			cmd = "ping " + ip;
		} else {
			cmd = "ping -c 10 " + ip;
		}
		Process run = Runtime.getRuntime().exec(cmd);
		BufferedReader buf = new BufferedReader(new InputStreamReader(run.getInputStream(), "GBK"));
		String line;
		while ((line = buf.readLine()) != null) {
			if (line.toUpperCase().indexOf("TTL=") != -1) {
				buf.close();
				return true;
			}
		}
		return false;
	}

	/**
	 * 连接socket ip跟端口是否ping通
	 * 
	 * @param ip
	 * @param port
	 * @return
	 * @throws IOException
	 */
	public static boolean PingPort(String ip, int port) throws IOException {
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(10000);
			if (socket.isConnected()) {
				return true;
			}
		} catch (UnknownHostException e) {

			return false;
		} catch (IOException e) {
			return false;
		} finally {
			if (socket != null) {
				socket.close();
			}
		}

		return false;
	}

}