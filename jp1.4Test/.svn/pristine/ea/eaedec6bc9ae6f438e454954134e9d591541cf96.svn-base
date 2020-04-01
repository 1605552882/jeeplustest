<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	/**
	 * 解除黑名单
	 */
	$("#remove").click("click", function() {// 绑定解除按扭
		  var userNo = $("#userNo").val();//电话号码
		  if(userNo.replace(/\s*/g,"")==""){
			  jp.warning("号码不能为空！");
			  return;
		  }else if(!checkPhoneNo(userNo)){
			  jp.warning("电话号码格式不正确！");
			  return;
		  }
		  //封装查询参数
		  var params = {};
		  params.userNo = userNo;
		  params.status = "2";
		  jp.loading("正在操作，请稍后...");
		  jp.post("${ctx}/sms/modify", params,function(data){
			  jp.close();
			  if(data.success){
				  //设置值
				  jp.success(data.message);
			  }else{
				  jp.error(data.message);
			  }
		  })
		  
	});
	
	
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
	 
});
		

</script>