<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	var width = window.screen.width;
	var height = window.screen.height;
	
    //定义全局anaaaInfo信息变量
	var anaaaInfo = null;
	
	//业务更改
	$("#ANAAAModifyWindow").click(function(){
		jp.openDialog("ANAAA业务更改窗口","${ctx}/anaaa/anaaa/anaaaModifyWindow",width*0.3+"",height*0.4+"", "");
	})

	//开启IOS测试
	$("#ANAAABindPhoneCardWindow").click(function(){
		jp.openDialog("ANAAA开启IOS测试窗口","${ctx}/anaaa/anaaa/anaaaBindPhoneCardWindow",width*0.4+"",height*0.3+"", "");
	})
	
	//关闭IOS测试
	$("#ANAAAUnbindPhoneCardWindow").click(function(){
		jp.openDialog("ANAAA关闭IOS测试窗口","${ctx}/anaaa/anaaa/anaaaUnbindPhoneCardWindow",width*0.4+"",height*0.3+"", "");
	})
	
	//认证日志查询
	$("#ANAAAAuthLogWindow").click(function(){
		jp.openDialogView("ANAAA认证日志查询窗口","${ctx}/anaaa/anaaa/anaaaAuthLogWindow",width*0.7+"",height*0.8+"", "");
	})
	
	//开销户接口日志查询
	$("#ANAAAAccountLogWindow").click(function(){
		jp.openDialogView("ANAAA开销户接口日志查询窗口","${ctx}/anaaa/anaaa/anaaaAccountLogWindow",width*0.7+"",height*0.8+"", "");
	})
	
	//MEID维护
	$("#MeidManagementWindow").click(function(){
		jp.openDialog("ANAAAMEID维护窗口","${ctx}/anaaa/meid/list",width*0.7+"",height*0.8+"", "");
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
		  jp.post("${ctx}/anaaa/anaaa/query",{userNoValue:userNoValue,searchType:searchType},function(data){
			  	jp.close();
				if(data.success){
					anaaaInfo = data.body;
					setValues(anaaaInfo);
					btnEnable();
					sessionStorage.setItem("anaaaInfo",JSON.stringify(anaaaInfo));
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
	function clearANAAAInfo(){
		$("#anaaaInfo input").val("");
	}
	
	//展示anaaa信息
	function setValues(anaaaInfo){
		$("#mdn").val(anaaaInfo.mdn);
		$("#imsi").val(anaaaInfo.imsi);
		$("#status").val(anaaaInfo.status);
		$("#uimType").val(anaaaInfo.uimType);
		$("#failTimes").val(anaaaInfo.failTimes);
		$("#hardwareIdAuth").val(anaaaInfo.hardwareIdAuth);
		$("#meid").val(anaaaInfo.meid);
	}
	
	//按钮可用
	function btnEnable(){
		$("#ANAAAModifyWindow").attr("disabled",false);
		$("#ANAAAUnbindPhoneCardWindow").attr("disabled",false);
		$("#ANAAABindPhoneCardWindow").attr("disabled",false);
	}
	btnDisable();
	//按钮不可用
	function btnDisable(){
		$("#ANAAAModifyWindow").attr("disabled",true);
		$("#ANAAAUnbindPhoneCardWindow").attr("disabled",true);
		$("#ANAAABindPhoneCardWindow").attr("disabled",true);
	}
	
	//切换查询方式
	$("#searchType").change(function(){
		var val = $(this).val();
		if(val=="1"){
			$("#userNoLabel").val("手机号码");
		}else{//0
			$("#userNoLabel").val("IMSI号码");
		}
	});
})
</script>