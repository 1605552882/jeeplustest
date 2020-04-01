/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.emb.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.anaaa.service.BasisCodingDetailsService;

/**
 * EMBController
 * @author zqb
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/predos/emb")
public class EMBController extends BaseController {
	/**
	 * 端到端查询页面
	 */
	@RequiresPermissions("predos:emb:list")
	@RequestMapping("list")
	public String list() {
		return "modules/emb/embList";
	}
}