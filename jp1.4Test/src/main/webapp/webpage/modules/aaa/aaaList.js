<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	var width = window.screen.width;
	var height = window.screen.height;
	//集成开户
	$("#jckh").click(function(){
		jp.openDialog("AAA开户集成窗口","${ctx}/aaa/aaa/jckhForm",width*0.7+"",height*0.5+"", "");
	})
	
	//销卡
	$("#cancelAccount").click(function(){
		var userNoValue = $("#userNoValue").val();//号码
		var searchType = $("#searchType").val();//查询类型：1手机号  0IMSI号
		var aaaQueryParam = {userNoValue:userNoValue,searchType:searchType};
		sessionStorage.setItem("aaaQueryParam",JSON.stringify(aaaQueryParam));
		jp.openDialog("AAA销卡窗口","${ctx}/aaa/aaa/cancelAccountForm",width*0.3+"",height*0.3+"", "");
	})
	
	//换号
	$("#changeNumber").click(function(){
		sessionStorage.setItem("aaaInfo",JSON.stringify(aaaInfo));
		jp.openDialog("AAA换号窗口","${ctx}/aaa/aaa/changeNumberForm",width*0.4+"",height*0.3+"", "");
	})

	//加入黑名单
	$("#addBlackList").click(function(){
		var userNoValue = $("#userNoValue").val();//号码
		var searchType = $("#searchType").val();//查询类型：1手机号  0IMSI号
		var aaaQueryParam = {userNoValue:userNoValue,searchType:searchType};
		sessionStorage.setItem("aaaQueryParam",JSON.stringify(aaaQueryParam));
		jp.openDialog("AAA加入黑名单窗口","${ctx}/aaa/aaa/addBlackListForm",width*0.4+"",height*0.7+"", "");
	})
	
	//解除黑名单
	$("#removeBlackList").click(function(){
		var userNoValue = $("#userNoValue").val();//号码
		var searchType = $("#searchType").val();//查询类型：1手机号  0IMSI号
		var aaaQueryParam = {userNoValue:userNoValue,searchType:searchType};
		sessionStorage.setItem("aaaQueryParam",JSON.stringify(aaaQueryParam));
		jp.openDialog("AAA解除黑名单窗口","${ctx}/aaa/aaa/removeBlackListForm",width*0.4+"",height*0.2+"", "");
	})
	
	//业务更改
	$("#businessModify").click(function(){
		sessionStorage.setItem("aaaInfo",JSON.stringify(aaaInfo));
		jp.openDialog("AAA业务更改窗口","${ctx}/aaa/aaa/businessModifyForm",width*0.4+"",height*0.5+"", "");
	})
	
	//普通用户换卡
	$("#changeIMSI").click(function(){
		sessionStorage.setItem("aaaInfo",JSON.stringify(aaaInfo));
		jp.openDialog("AAA普通用户换卡窗口","${ctx}/aaa/aaa/changeIMSIForm",width*0.4+"",height*0.4+"", "");
	})
	
	//CG国际漫游卡换卡
	$("#changeCGIMSI").click(function(){
		sessionStorage.setItem("aaaInfo",JSON.stringify(aaaInfo));
		jp.openDialog("AAACG国际漫游卡换卡窗口","${ctx}/aaa/aaa/changeCGIMSIForm",width*0.4+"",height*0.6+"", "");
	})
	
	//认证日志
	$("#authenticationLog").click(function(){
		var aaaData = {"numberType":$("#numberType").val(),"number":$("#number").val()};
		sessionStorage.setItem("aaaData",JSON.stringify(aaaData));
		console.log("aaaData",JSON.stringify(aaaData));
		jp.openDialog("AAA认证日志查询窗口","${ctx}/aaa/aaa/authenticationLogForm",width*0.7+"",height*0.8+"", "");
	})
	
	//开销户日志
	$("#AAAAccountLogQueryWindow").click(function(){
		var aaaData = {"numberType":$("#numberType").val(),"number":$("#number").val()};
		sessionStorage.setItem("aaaData",JSON.stringify(aaaData));
		console.log("aaaData",JSON.stringify(aaaData));
		jp.openDialog("AAA开销户日志查询窗口","${ctx}/aaa/aaa/accountLogQueryWindow",width*0.7+"",height*0.8+"", "");
	})
	
	//软拨测页面
	$("#AAASoftwareCallTestWindow").click(function(){
		var aaaData = {"numberType":$("#numberType").val(),"number":$("#number").val()};
		sessionStorage.setItem("aaaData",JSON.stringify(aaaData));
		console.log("aaaData",JSON.stringify(aaaData));
		jp.openDialogView("AAA软拨测页面查询窗口","${ctx}/aaa/aaa/softwareCallTestWindow",width*0.7+"",height*0.8+"", "");
	})
	
	//批量操作页面
	$("#AAABatchOperateWindow").click(function(){
		var aaaData = {"numberType":$("#numberType").val(),"number":$("#number").val()};
		sessionStorage.setItem("aaaData",JSON.stringify(aaaData));
		console.log("aaaData",JSON.stringify(aaaData));
		jp.openDialog("AAA/批量操作查询窗口","${ctx}/aaa/aaa/batchOperateWindow",width*0.7+"",height*0.8+"", "");
	})

	//验证IMSI号码
    function checkIMSINoFormat(imsi) {
	   	var reg = /^(((46003)|(20404)|(45404)|(45403))[0-9]{10})$/;
	   	return reg.test(imsi);
    }
    //验证手机号
    function checkPhoneNo(phoneNo){
		var reg = /^(((13|15|17|18|19)[0-9]{9})|((147)[0-9]{8}))$/;
	 	return reg.test(phoneNo);
    }
    //定义全局aaaInfo信息变量
	var aaaInfo = null;
	
	//查询
	$("#search").click("click", function() {// 绑定查询按扭
		  var userNoValue = $("#userNoValue").val();//号码
		  var searchType = $("#searchType").val();//查询类型：1手机号  0IMSI号
		  //校验手机号/IMSI号
		  if(userNoValue.replace(/\s*/g,"")==""){
			  if(searchType==1){
				  jp.warning("手机号码不能为空！");
			  }else{
				  jp.warning("IMSI号不能为空！");
			  }
			  return;
		  }else{
			 if(searchType==1 && !checkPhoneNo(userNoValue)){
				 jp.warning("手机号码格式不正确！");
				 return;
			 }else if(searchType==0 && !checkIMSINoFormat(userNoValue)){
				 jp.warning("请输入正确IMSI号码！");
				 return;
			 };
		  }
		  jp.loading("正在操作，请稍后...");
		  jp.post("${ctx}/aaa/aaa/infoQuery",{userNoValue:userNoValue,searchType:searchType},function(data){
			  	jp.close();
				if(data.success){
					aaaInfo = data.body.aaaInfo;
					setValues(aaaInfo);
					btnEnable();
				}else{
					jp.warning(data.message);
				}
		  });
		});
	
	$("#searchType").change(function(){
	     var selVal=$(this).val();
	     if(selVal==0){
	    	 $("label[for='userNoValue']").html("IMSI号码："); 
	     }else{
	    	 $("label[for='userNoValue']").html("手机号码：");
	     }
	});
	
	//清空
	function clearAAAInfo(){
		$("#aaaInfo input").val("");
	}
	
	//展示aaa信息
	function setValues(aaaInfo){
		for(var i=0; i<aaaInfo.length; i++){
			$("#aaaMDN"+i).val(aaaInfo[i].aaaMDN);
			$("#aaaIMSI"+i).val(aaaInfo[i].aaaIMSI);
			$("#aaaStatus"+i).val(AAATFJ[aaaInfo[i].aaaStatus]);
			$("#aaaMYPermissions"+i).val(AAAMYQX[aaaInfo[i].aaaMYPermissions]);
			$("#aaaType"+i).val(AAAFFLX[aaaInfo[i].aaaType]);
			$("#aaaBindOption"+i).val(AAASXZT[aaaInfo[i].aaaBindOption]);
			$("#aaaBindNAI"+i).val(aaaInfo[i].aaaBindNAI);
			$("#aaaBindVPN"+i).val(aaaInfo[i].aaaBindVPN);
			$("#aaaOwnerGroup"+i).val(AAAGSZLX[aaaInfo[i].aaaOwnerGroup]);
			if(i == 0 && aaaInfo[i].aaaPermitaccType!=null){
				$("#aaaPermitaccType"+i).val(AAAPERTYPE[aaaInfo[i].aaaPermitaccType]);
			}
			if(i == 1 && aaaInfo[i].aaaGPermitaccType!=null){
				$("#aaaGPermitaccType"+i).val(AAAPERTYPE[aaaInfo[i].aaaGPermitaccType]);
			}
		}
	}
	
	//按钮可用
	function btnEnable(){
		$("#cancelAccount").attr("disabled",false);
		$("#changeNumber").attr("disabled",false);
		$("#addBlackList").attr("disabled",false);
		$("#removeBlackList").attr("disabled",false);
		$("#businessModify").attr("disabled",false);
		$("#changeIMSI").attr("disabled",false);
		$("#changeCGIMSI").attr("disabled",false);
	}
	btnDisable();
	//按钮不可用
	function btnDisable(){
		$("#cancelAccount").attr("disabled",true);
		$("#changeNumber").attr("disabled",true);
		$("#addBlackList").attr("disabled",true);
		$("#removeBlackList").attr("disabled",true);
		$("#businessModify").attr("disabled",true);
		$("#changeIMSI").attr("disabled",true);
		$("#changeCGIMSI").attr("disabled",true);
	}
	
	/**
	 * 临时：到时候看看是要从basiccode还是从jeeplus的字典表sys_dict_type和sys_dict_value取数据
	 */
	var AAATFJ = {0:"正常",1:"停机"};//停复机
	var AAAFFLX = {0:"普通",1:"PPC（预付费）"};//付费类型
	var AAAMYQX = {0:"允许省际+国际漫游",
					1:"限制省际漫游、允许国际漫游",
					2:"允许省际漫游、限制国际漫游",
					3:"限制省际+国际漫游"};//漫游权限
	var AAASXZT = {0:"解锁",1:"加锁"};//受限状态
	var AAAGSZLX = {
			"gold_user":"金牌用户",
			"silver_user":"银牌用户",
			"Copper_User":"铜牌用户",
			"HA-CCG":"内容计费",
			"Qchat":"天翼对讲",
			"VT":"视频通话",
			"普通用户":"default"
	};//归属组
	var AAAPERTYPE = {
		0:"1X,EVDO,C+W",
		1:"1X",
		2:"EVDO",
		3:"1X,EVDO",
		4:"C+W",
		5:"1X,C+W",
		6:"EVDO,C+W",
		7:"1X,EVDO,C+W"
	};//接入类型
});
</script>