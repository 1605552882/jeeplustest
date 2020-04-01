package com.jeeplus.modules.volte.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.core.web.BaseController;

/**
 * volte信息管理Controller
 * @author 钟晖
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/volte")
public class VolteController extends BaseController {
	
	/**
	 * VoLTE-MMTEL页面
	 */
	@RequestMapping(value = {"volteMmtel", ""})
	public String volteMmtelPage() {
		String page = "modules/volte/volte-mmtel";
		return page;
	}
	
	/**
	 * 查询VoLTE-MMTEL数据
	 */
	@RequestMapping("/volteMmtel/data")
	@ResponseBody
	public Map<String,Object> volteMmtelData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AREA_NO", "广州");
		map.put("REG_NO", "广东");
		map.put("CALLOUT", "停机");
		map.put("CALLIN", "正常");
		map.put("limCalOut_act1", "1");//呼出限制  0不限制呼出  1限制所有呼出 2限制国际呼出
		map.put("limCalIn_act1", "2");//呼入限制 0不限制呼入 1限制所有呼入 2限制漫游时所有呼入
		map.put("arre_out", "正常");//欠费呼出标识
		map.put("CallIn_act", "欠费");//欠费呼入标识
		map.put("unCondConv_reg", "1"); // 无条件前转
		map.put("unCondConv_act", "0");
		map.put("unCondConv_no", "02087188124");
		map.put("busyConv_reg", "1"); // 遇忙前转
		map.put("busyConv_act", "0");
		map.put("busyConv_no", "18078118091");
		map.put("unTouchConv_reg", "1"); // 不可及前转
		map.put("unTouchConv_act", "0");
		map.put("unTouchConv_no", "18078118091");
		map.put("unRespConv_reg", "1"); // 无应答前转
		map.put("unRespConv_act", "1");
		map.put("unRespConv_no", "18078118091");
		map.put("callFlag_reg", "1"); // 主叫标识显示
		map.put("callWait_reg", "0"); // 呼叫等待
		map.put("callWait_act", "0");
		map.put("callMany_reg", "1"); // 多方通话
		map.put("callAss_reg", "1"); // 隐含前转（显示为：通信助理）
		map.put("callOne_reg", "1"); // 主叫一号通
		map.put("callOne_act", "0");
		map.put("callOne_no", "123123");
		map.put("calledOne_reg", "1"); // 被叫一号通
		map.put("calledOne_act", "0");
		map.put("calledOne_no", "123456");
		map.put("usernumber", "1807811809");
		map.put("MSISDN", "18078118091");
		map.put("callOne_no", "18078118012");
		map.put("calledOne_no", "18078118025");
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("volteInfo", map);
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 * 修改VoLTE-MMTEL数据
	 */
	@RequestMapping("/volteMmtel/modify")
	@ResponseBody
	public Map<String,Object> modifyVolteMmtel() {
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success", false);
		return retMap;
	}
	

	/**
	 * 删除VoLTE-MMTEL数据
	 */
	@RequestMapping("/volteMmtel/del")
	@ResponseBody
	public Map<String,Object> delVolteMmtel() {
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 * 查询漫游地SCSCF
	 */
	@RequestMapping("/volteMmtel/querySCSCF")
	@ResponseBody
	public Map<String,Object> querySCSCF() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reg_PSBC", "广东psbc12");
		map.put("actscsc", "SCSCF22");
		map.put("reg_loc", "广州");
		map.put("tcpwl", "4G网络");
		map.put("success", true);
		return map;
	}
	
	/**
	 * VoLTE-HSS管理
	 */
	@RequestMapping(value = {"volteHss", ""})
	public String volteHhsPage() {
		String page = "modules/volte/volte-hss";
		return page;
	}
	
	/**
	 * volteHss界面左侧查询
	 * @return
	 */
	@RequestMapping("/volteHss/queryVolteHss")
	@ResponseBody
	public Map<String,Object> queryVolteHss() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String,Object> volteInfo = new HashMap<String, Object>();
		volteInfo.put("Ramble", "0");
		volteInfo.put("IMSI_LTE", "460110401099824");
		volteInfo.put("HSIFC", "1910-1930-1980-");
		volteInfo.put("IMSI_CDMA", "460030918726224");
		volteInfo.put("type", "1");
		volteInfo.put("usernumber", "18928849206");
		volteInfo.put("MSISDN", "18928849206");
		retMap.put("volteInfo", volteInfo);
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 * volteHss界面右侧查询
	 * @return
	 */
	@RequestMapping("/volteHss/query4GHss")
	@ResponseBody
	public Map<String,Object> query4GHss() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		//APN信息
		List<Map<String,Object>> infoList = new ArrayList<Map<String,Object>>();
		Map<String,Object> apnInfo = new HashMap<String, Object>();
		apnInfo.put("APNName", "116");
		apnInfo.put("APNIden", "IMS");
		Map<String,Object> apnInfo2 = new HashMap<String, Object>();
		apnInfo2.put("APNName", "117");
		apnInfo2.put("APNIden", "紧急呼叫");
		infoList.add(apnInfo);
		infoList.add(apnInfo2);
		
		retMap.put("IMSI_LTE", "460110401099824");
		retMap.put("MSISDN", "8618928849206");
		retMap.put("VOLTETAG", "TRUE");
		retMap.put("EPSLOCK", "1");//停机状态 0正常 1停机
		retMap.put("infoList", infoList);
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 * volteHss修改
	 * @return
	 */
	@RequestMapping("/volteHss/hssmodify")
	@ResponseBody
	public Map<String,Object> hssmodify() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		return retMap;
	}

	/**
	 * volteHss删除
	 * @return
	 * @throws  
	 */
	@RequestMapping("/volteHss/deleVoLTEHSS")
	@ResponseBody
	public Map<String,Object> deleVoLTEHSS() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		return retMap;
	}

	/**
	 * volteHss修改
	 * @return
	 */
	@RequestMapping("/volteHss/businessModify")
	@ResponseBody
	public Map<String,Object> businessModify() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		retMap.put("result", "操作成功");
		return retMap;
	}
	
}