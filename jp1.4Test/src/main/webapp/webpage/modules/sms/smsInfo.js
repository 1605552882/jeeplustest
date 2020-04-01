<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	var width = window.screen.width;
	var height = window.screen.height;
	
	//设置输入框为只读
	$(".r1 .form-control").attr("readonly",true);
	
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
	
	 /**
	   * 用户查询
	   */    
	  $("#search").click("click", function() {// 绑定查询按扭
		  var userNumber = $("#userNumber").val();//用户号码
		  //校验手机号/IMSI号
		  if(userNumber.replace(/\s*/g,"")==""){
			  jp.warning("电话号码不能为空！");
			  return;
		  }else if(!checkPhoneNo(userNumber)){
			  jp.warning("电话号码格式不正确！");
			  return;
		  }
		 
		  //查询前先清空输入框
		  $('#smsForm')[0].reset();
		  $("#userNumber").val(userNumber);
		  jp.loading("正在操作，请稍后...");
		  jp.post("${ctx}/sms/selectUserSatus", {userNo:userNumber},function(data){
			  jp.close();
			  if(data.success){
				  //设置值
				  setValues(data.list[0]);
			  }else{
				  jp.error(data.message);
			  }
		  })
		  
		});
	  
	  //设置表单的值
	  function setValues(userInfo){
		  //用户优先级 0普通优先级   1高优先级
		  if(userInfo.userQueryFormPanelUserPoriperty == "0"){
			  userInfo.userQueryFormPanelUserPoriperty = "普通优先级";
		  }else if(userInfo.userQueryFormPanelUserPoriperty == "1"){
			  userInfo.userQueryFormPanelUserPoriperty = "高优先级";
		  };
		  //语言   0英语,1普通话,2其他
		  if(userInfo.userQueryFormPanelLanguage == "0"){
			  userInfo.userQueryFormPanelLanguage = "英语";
		  }else if(userInfo.userQueryFormPanelLanguage == "1"){
			  userInfo.userQueryFormPanelLanguage = "普通话";
		  }else if(userInfo.userQueryFormPanelLanguage == "2"){
			  userInfo.userQueryFormPanelLanguage = "其他";
		  };
		  //付费方式   0后付费用户   1预付费用户   2后付费用户
		  if(userInfo.userQueryFormPanelPayType == "0"){
			  userInfo.userQueryFormPanelPayType = "后付费用户";
		  }else if(userInfo.userQueryFormPanelPayType == "1"){
			  userInfo.userQueryFormPanelPayType = "预付费用户";
		  }else if(userInfo.userQueryFormPanelPayType == "2"){
			  userInfo.userQueryFormPanelPayType = "后付费用户";
		  };
		  //设置输入框的值
		  for (x in userInfo) { 
			  $("input[name='"+x+"']").val(userInfo[x]);
		  }
	  }
	  
	//根据号码查询黑名单
	$("#numBlackListQryWindow").click(function(){
		var userNumber = $("#userNumber").val();
		sessionStorage.setItem("userNumber",userNumber);
		jp.openDialogView("根据号码查询黑名单","${ctx}/sms/numBlackListQryWindow",width*0.7+"",height*0.8+"", "");
	})
	
	//根据号码解除黑名单
	$("#numBlackListDelWindow").click(function(){
		var userNumber = $("#userNumber").val();
		sessionStorage.setItem("userNumber",userNumber);
		jp.openDialogView("根据号码解除黑名单","${ctx}/sms/numBlackListDelWindow",width*0.4+"",height*0.3+"", "");
	})
	
	//根据区号时间查询黑名单
	$("#areaCodeBlackListWindow").click(function(){
		var userNumber = $("#userNumber").val();
		sessionStorage.setItem("userNumber",userNumber);
		jp.openDialogView("根据区号查询黑名单","${ctx}/sms/areaCodeBlackListWindow",width*0.7+"",height*0.8+"", "");
	})
	 
});
		

</script>