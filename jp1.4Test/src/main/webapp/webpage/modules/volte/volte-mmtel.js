<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	//定义全局volte信息变量
	var volteInfo = null;
	
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
	   * volte查询
	   */    
	  $("#search").click("click", function() {// 绑定查询按扭
		  var phoneNum = $("#userNo").val();//电话号码
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
		  params.start = 0;
		  params.limit = 10;
		  params.convNo = "1";
		  params.unCondConv = "1";
		  params.unRespConv = "1";
		  params.unTouchConv = "1";
	      params.busyConv = "1";
	      params.usernumber = phoneNum;
	      //每次查询都要先清空表单
	      $('#volteForm')[0].reset();
		  $("#userNo").val(phoneNum);
//		  jp.loading("正在操作，请稍后...");
		  $.ajax({
				url:'${ctx}/volte/volteMmtel/data',
				dataType:'json',
				data:params,
				type:'POST',
				timeout:10000,
				success:function(data,status){
//					jp.close();
					if(data.success){
						volteInfo = data.volteInfo;
						setValues(data.volteInfo)
					}else{
						jp.error(data.errMsg);
					};
					
				}
			})
			//查询漫游地SCSCF
			$.ajax({
				url:'${ctx}/volte/volteMmtel/querySCSCF',
				dataType:'json',
				data:params,
				type:'POST',
				timeout:10000,
				success:function(data,status){
					if(data.success){
						$("input[name='reg_loc']").val(data.reg_loc);//漫游地
						$("input[name='actscsc']").val(data.actscsc);//注册SCSCF
						$("input[name='reg_PSBC']").val(data.reg_PSBC);//注册PSBC
						$("input[name='tcpwl']").val(data.tcpwl);//网络类型
					}else{
						jp.error(data.errMsg);
					};
					
				}
			})
		  
		});
	  
	  //设置表单的值
	  function setValues(volteInfo){
		  	$("input[name='MSISDN'").val(volteInfo.MSISDN);//手机号码
			$("input[name='AREA_NO']").val(volteInfo.AREA_NO);//归属地区
			$("input[name='REG_NO']").val(volteInfo.REG_NO);//省号标识
			if(volteInfo.CallIn_act=="正常"){//欠费呼入标识 正常0 欠费1
				$("select[name='CallIn_act']").val(0);
			}else{
				$("select[name='CallIn_act']").val(1);
			}
			if(volteInfo.arre_out=="正常"){//欠费呼出标识 正常0 欠费1
				$("select[name='arre_out']").val(0);
			}else{
				$("select[name='arre_out']").val(1);
			}
			if(volteInfo.CALLOUT=="正常"){//呼出停机标识 正常0 停机1 
				$("select[name='CALLOUT']").val(0); 
			}else{
				$("select[name='CALLOUT']").val(1); 
			}
			if(volteInfo.CALLIN=="正常"){//呼入停机标识 正常0 停机1 
				$("select[name='CALLIN']").val(0); 
			}else{
				$("select[name='CALLIN']").val(1); 
			}
			$("select[name='limCalOut_act1']").val(volteInfo.limCalOut_act1);//呼出限制
			$("select[name='limCalIn_act1']").val(volteInfo.limCalIn_act1);//呼入限制
			
			//设置有授权激活的复选框
			var volteInfoJson = JSON.parse(JSON.stringify(volteInfo));
			var props = [ "callFlag", "unRegConv", "unTouchConv", "unRespConv", "unCondConv", "busyConv", "callOne", "calledOne", "callKeep", "callWait", "callMany", "retRing", "callAss" ];
			var p1 = null, p2 = null, p3 = null;
			for (var i = 0; i < props.length; i++) {
				var p = props[i];
				p1 = (p + "_reg");
				p2 = (p + "_act");
				p3 = (p + "_no");

				if (volteInfoJson[p1] == "0") {
					$("input[name='"+p1+"']").prop("checked", false);//不授权
				} else if (volteInfoJson[p1] == "1") {
					$("input[name='"+p1+"']").prop("checked", true);//授权
				}
				if (volteInfoJson[p2] == "0") {
					$("input[name='"+p2+"']").prop("checked", false);//不激活
					//设置呼转号码输入框为不可编辑状态
					if(p=="unCondConv" || p=="busyConv" || p=="unTouchConv" || p=="unRespConv"){
						$("input[name='"+p3+"']").attr("readOnly",true);
					}
				} else if (volteInfoJson[p2] == "1") {
					$("input[name='"+p2+"']").prop("checked", true);//激活
					//设置呼转号码输入框为可编辑状态
					if(p=="unCondConv" || p=="busyConv" || p=="unTouchConv" || p=="unRespConv"){
						$("input[name='"+p3+"']").attr("readOnly",false);
					}
				}
			}
			
			//设置呼转号码
			$("input[name='unCondConv_no']").val(volteInfo.unCondConv_no);
			$("input[name='busyConv_no']").val(volteInfo.busyConv_no);
			$("input[name='unTouchConv_no']").val(volteInfo.unTouchConv_no);
			$("input[name='unRespConv_no']").val(volteInfo.unRespConv_no);
			
			//设置主号号码
			$("input[name='callOne_no']").val(volteInfo.callOne_no);
			$("input[name='calledOne_no']").val(volteInfo.calledOne_no);
			
	  }
	  
	  //转呼功能激活复选框设置单击事件
	  $('.zhActive').click(function () {
		  var check = $(this).is(':checked');
		  var name = $(this).prop("name");
		  name = name.replace("act","no");
		  if(check){
			  $("input[name='"+name+"']").attr("readOnly",false);
		  }else{
			  $("input[name='"+name+"']").attr("readOnly",true);
		  }
	  })
	  
	  /**
	   * volte修改
	   */
	  $("#update").click(function(){
		  //判断volteInfo是否为空
		  if(volteInfo == null){
			  jp.warning("请先查询再修改");
			  return;
		  }else{
			  //校验呼转号码
			  if(!checkHZPhoneNo()){
				  jp.warning("呼转号码格式不正确，请检查!");
				  return;
			  }
			  var params = getAfterModFormParams();
			  jp.loading("正在操作，请稍后...");
			  $.ajax({
				  url:'${ctx}/volte/volteMmtel/modify',
					dataType:'json',
					data:params,
					type:'POST',
					timeout:10000,
					success:function(data,status){
						jp.close();
						if(data.success == true){
							jp.success("修改成功!");
							$("#search").click();
						}else{
							jp.error(data.errMsg);
						}
						if(!data.message.replace(/\s*/g,"")==""){
							jp.warning(data.message);
						}else if(!data.success){
							jp.warning("修改失败!");
						}
					
					}
			})
			  
		  }
	  })
	  
	  /**
	   * volte删除
	   */
	  $("#del").click(function(){
		  //判断volteInfo是否为空
		  if(volteInfo == null){
			  jp.warning("请先查询再修改");
			  return;
		  }
		  jp.confirm('你确定要删除吗？***如果删除VoLTE-MMTEL用户，请同时删除ENUM信息!',  function(){
			  jp.loading("正在操作，请稍后...");
			  var params = {};
		      params.usernumber = volteInfo.usernumber;
			  $.ajax({
				  url:'${ctx}/volte/volteMmtel/del',
				  dataType:'json',
				  data:params,
				  type:'POST',
				  timeout:10000,
				  success:function(data,status){
					  jp.close();
					  if(data.success == true){
						  jp.success("删除成功了");
					  }else{
						  jp.error(data.errMsg);
					  }
					  
				  }
			  })
		  })
	  })
	  
	  //获取修改后的表单的参数值
	  function getAfterModFormParams(){
		  var params = {};
		  	//呼转授权
		  	params.unCondConv_reg = $("input[name='unCondConv_reg']").prop("checked");//无条件前转授权
		  	params.busyConv_reg = $("input[name='busyConv_reg']").prop("checked");//遇忙前转授权
		  	params.unTouchConv_reg = $("input[name='unTouchConv_reg']").prop("checked");//不可及前转授权
		  	params.unRespConv_reg = $("input[name='unRespConv_reg']").prop("checked");//无应答前转授权
		  	//呼转激活
		  	var props = [ "unCondConv", "busyConv", "unTouchConv", "unRespConv" ];
		  	for (var i = 0; i < props.length; i++) {
				var prop = props[i];
				var act =  $("input[name='"+prop+"_act']").prop("checked");//获取激活状态
				params[prop+"_act"] = act;//设置激活状态
				if(act){
					//若激活状态为选中，则设置呼转号码
					var no = $("input[name='"+prop+"_no']").val();
					params[prop+"_no"] = no;
				};
			}
		  	
			params.usernumber = volteInfo.usernumber;
			params.type = volteInfo.type;
			//设置基本信息参数
			params.CallIn_act = $("#CallIn_act").find("option:selected").text();// 欠费呼入标识
			params.arre_out = $("#arre_out").find("option:selected").text();// 欠费呼出标识
			params.CALLOUT = $("#CALLOUT").find("option:selected").text();// 呼出停机标识
			params.CALLIN = $("#CALLIN").find("option:selected").text();// 呼入停机标识
			params.limCalOut_act1 = $("#limCalOut_act1").val();//呼出限制
			params.limCalIn_act1 = $("#limCalIn_act1").val();//呼入限制
			//设置补充业务
			params.callFlag_reg = $("input[name='callFlag_reg']").prop("checked") ? "on" : "off";//主叫标识显示
			params.callWait_reg = $("input[name='callWait_reg']").prop("checked") ? "on" : "off";//呼叫等待
			params.callMany_reg = $("input[name='callMany_reg']").prop("checked") ? "on" : "off";//多方通话
			params.callAss_reg = $("input[name='callAss_reg']").prop("checked") ? "on" : "off";//通信助理
			//设置一号通参数
			params.callOne_reg = $("input[name='callOne_reg']").prop("checked") ? "on" : "off";//主叫一号通 登记
			params.calledOne_reg = $("input[name='calledOne_reg']").prop("checked") ? "on" : "off";//被叫一号通 登记
			params.callOne_no = $("input[name='callOne_no']").val();//主号号码
			params.calledOne_no = $("input[name='calledOne_no']").val();//副号号码
			//设置漫游地参数
			params.reg_loc = $("input[name='reg_loc']").val();//漫游地
			params.actscsc = $("input[name='actscsc']").val();//注册SCSCF
			params.reg_PSBC = $("input[name='reg_PSBC']").val();//注册PSBC
			params.tcpwl = $("input[name='tcpwl']").val();//网络类型
			params.newchange = "newchange";
			return params;
	  }
	  
	  //获取普通输入框或者单选框的值
	  function getInputValue(name){
		  var val = $("input[name='"+name+"']").val();
		  return val;
	  }
	  
	  //获取单选框的值
	  function getRadioValue(name){
		  var val = $("input[name='"+name+"']:checked").val();
		  return val;
	  }
	  
	  //获取授权激活复选框的值
	  function getDoubleCheckboxValue(name){
		  var func = $("input[name='"+name+"']").eq(0).prop("checked");//授权
		  var active = $("input[name='"+name+"']").eq(1).prop("checked");//激活
		  if(func==true && active==true){
			  //授权并激活
			  return "1";
		  }else if(func==true && active==false){
			  //授权不激活
			  return "2";
		  }else{
			  //没有授权
			  return "0";
			  
		  }
	  }
	  
	  //获取下拉选的值
	  function getSelectValue(name){
		  var val = $("select[name='"+name+"']").val();
		  return val;
	  }
	  
	  //校验呼转号码
	  function checkHZPhoneNo(){
		  var reg = /^(((86)?((13|14|15|16|17|18|19)[0-9]{9}))|((0[1-9][0-9]{1,2}(-)?)[2-8][0-9]{6,7}))$/;
		  var arr =["unCondConv", "busyConv","unTouchConv","unRespConv"];
		  for (var i = 0; i < arr.length; i++) {
			  var act = $("input[name='"+arr[i]+"_act']").prop("checked");//获取是否激活
			  if(act){//激活才校验号码
				  var phoneNo = $("input[name='"+arr[i]+"_no']").val();//获取激活号码
				  var checkResult = reg.test(phoneNo);
				  if(!checkResult)return false;
			  };
		  }
		  return true;
	  }
	 
});
		

</script>