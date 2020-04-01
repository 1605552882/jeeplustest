<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	//定义全局变量
	var flag = false;
	   
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
	   * ENUM查询
	   */    
	  $("#search").click("click", function() {// 绑定查询按扭
		  var phoneNum = $("#number").val();//电话号码
		  //校验手机号
		  if(phoneNum.replace(/\s*/g,"")==""){
			  jp.warning("电话号码不能为空！");
			  return;
		  }else{
			 if(!checkPhoneNo(phoneNum)){
				 jp.warning("电话号码格式不正确！");
				 return;
			 }
		  }
		 
		  //封装查询参数
		  var params = {};
	      params.number = phoneNum;
	      //每次查询都要先清空表单
	      $('#enumForm')[0].reset();
		  $("#number").val(phoneNum);
//		  jp.loading("正在操作，请稍后...");
		  $.ajax({
				url:'${ctx}/enum/query',
				dataType:'json',
				data:params,
				type:'POST',
				timeout:10000,
				success:function(data,status){
//					jp.close();
                    if (data.success || data.ResultDescr=='success') {
                    	flag = true;
                        if (data.resultCode == "0"&& data.ResultDescr!='success') {
                            setValues(data);
                            jp.error("查询NPC失败: " + data.ResultDescr);
                        }else if(data.resultCode != "0"&& data.ResultDescr=='success'){
                        	setValues(data);
                        	jp.error("查询ENUM失败: ENUM用户信息不存在");
                        }else if(data.resultCode == "0"&& data.ResultDescr=='success'){
                        	setValues(data);
                        }else if(data.resultCode != "0"&& data.ResultDescr!='success'){
                        	jp.error("查询失败: 用户信息不存在");
                        }
                    	
                    } else {
                    	jp.error("系统警告: " + data.errMsg);
                    }
					
				}
			})
		  
		});
	  
	  //设置表单的值
	  function setValues(enumInfo){
		  	$("input[name='CONTENT'").val(enumInfo.CONTENT);//ENUM记录信息
		  	$("input[name='npvalidtime'").val(enumInfo.npvalidtime);//开户时间
		  	$("input[name='portinnetid']").val(enumInfo.portinnetid);//携入网络
			$("input[name='portoutnetid']").val(enumInfo.portoutnetid);//携出网络
			$("input[name='porttype']").val(enumInfo.porttype);//携带类型
			$("input[name='homenetid']").val(enumInfo.homenetid);//号码拥有网络
	  }
	  
	  
	  /**
	   * ENUM添加
	   */
	  $("#addEnum").click(function(){
		  var phoneNum = $("#num").val();//手机号
		  //校验手机号
		  if(phoneNum.replace(/\s*/g,"")==""){
			  jp.warning("电话号码不能为空！");
			  return;
		  }else{
			 if(!checkPhoneNo(phoneNum)){
				 jp.warning("电话号码格式不正确！");
				 return;
			 }
		  }
		  //发送ajax请求
		  var params = {};
		  params.number = phoneNum;
		  jp.loading("正在操作，请稍后...");
		  $.ajax({
			  url:'${ctx}/enum/addEnum',
			  dataType:'json',
			  data:params,
			  type:'POST',
			  timeout:10000,
			  success:function(data,status){
				  jp.close();
				  if(data.success){
					  jp.success(data.message);
					  $('#enumModal').modal('hide');
				  }else{
					  jp.error(data.message);
				  }
			  }
		  
		  })
		  
	  })
	  
	  /**
	   * ENUM删除
	   */
	  $("#del").click(function(){
		  var phoneNum = $("#number").val();//手机号
		  //校验手机号
		  if(phoneNum.replace(/\s*/g,"")==""){
			  jp.warning("电话号码不能为空！");
			  return;
		  }else{
			  if(!checkPhoneNo(phoneNum)){
				  jp.warning("电话号码格式不正确！");
				  return;
			  }
		  }
		  jp.confirm('你确定要删除吗？***如果删除ENUM信息，请同时删除VoLTE-MMTEL用户!',  function(){
			  jp.loading("正在操作，请稍后...");
			  var params = {};
			  params.number = phoneNum;
			  $.ajax({
				  url:'${ctx}/enum/delEnum',
				  dataType:'json',
				  data:params,
				  type:'POST',
				  timeout:10000,
				  success:function(data,status){
					  jp.close();
					  if(data.success){
						  jp.success(data.message);
					  }else{
						  jp.error(data.message);
					  }
				  }
			  
			  })
		  })
	  })
	  
	 
});
		

</script>