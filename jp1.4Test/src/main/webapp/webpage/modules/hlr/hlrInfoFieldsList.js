<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	//定义全局hlr信息变量
	var hlrInfo = null;
	
	$("#queryType").change(function(){
	     var selVal=$(this).val();
	     if(selVal==0){
	    	 $("label[for='userNo']").html("IMSI号码"); 
	     }else{
	    	 $("label[for='userNo']").html("手机号码");
	     }
	});
	
	$("#userStatus option").attr("disabled","disabled");
	   
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
	   * HLR查询
	   */    
	  $("#search").click("click", function() {// 绑定查询按扭
		  var phoneNum = $("#userNo").val();//电话号码
		  var type = $("#queryType").val();//查询类型：1手机号  0IMSI号
		  //校验手机号/IMSI号
		  if(phoneNum.replace(/\s*/g,"")==""){
			  if(type==1){
				 jp.warning("电话号码不能为空！");
			  }else{
				   jp.warning("IMSI号不能为空！");
			  }
			  //$("#myModal").modal();
			  return;
		  }else{
			 if(type==1 && !checkPhoneNo(phoneNum)){
				 jp.warning("电话号码格式不正确！");
				 return;
			 }else if(type==0 && !checkIMSINoFormat(phoneNum)){ 
				 jp.warning("请输入正确IMSI号码！");
				 return;
			 };
		  }
		 
		  //每次查询都要先清空表单
		  $('#hlrForm')[0].reset();
		  $("#userNo").val(phoneNum);
		  $("#queryType").val(type);
		 // jp.loading("正在操作，请稍后...");
		  $.ajax({
				url:'${ctx}/hlr/hlrInfoFields/data',
				dataType:'json',
				data:{userNo:phoneNum,queryType:type,reqinfo:"yes",number:phoneNum},
				type:'POST',
				timeout:10000,
				success:function(data,status){
					hlrInfo = data.hlrInfo;
					jp.alert(data.conn_Id);
					if(data.codes=='10005'){
						jp.alert(data.msg);
					}else{
						setValues(hlrInfo);
					}
				}
			})
		  
		});
	  
	  //设置表单的值
	  function setValues(hlrInfo){
		  	$("#searchResult").val(hlrInfo.searchResult);
			$("input[name='province']").val(hlrInfo.province);
			$("input[name='area']").val(hlrInfo.area);
			$("select[name='userStatus']").val(hlrInfo.userStatus);
			//设置有授权激活的复选框
			var hlrInfoJson = JSON.parse(JSON.stringify(hlrInfo));
			var props = [ "withoutCondition", "whenBusy", "noAnswer", "defaultCall", "cfmn", "brtFunction",
							"callWaiting", "noDisturb", "callDisplay1", "callDisplay2" ];
			for ( var i = 0; i < props.length; i++) {
				var prop = props[i];
				var val = hlrInfoJson[prop];
				if(val==0){//不授权 复选框不选中
					$("input[name='"+prop+"']").each(function() {
						$(this).prop("checked", false);
					});
				}else if(val==1){//授权并激活 复选框全选
					$("input[name='"+prop+"']").each(function() {
						$(this).prop("checked", true);
					});
					//若为转呼功能为激活，则设置对用的呼转号码输入框为可编辑状态
					if(prop=="withoutCondition" || prop=="whenBusy" || prop=="noAnswer" || prop=="defaultCall"){
						$("input[name='"+prop+"No']").attr("readOnly",false);
					}
				}else if(val==2){//授权不激活 只选授权复选框
					//先清空
					$("input[name='"+prop+"']").prop("checked",false);
					//再选择授权复选框
					$("input[name='"+prop+"']").eq(0).prop("checked", true);
					//设置呼转号码输入框为不可编辑状态
					if(prop=="withoutCondition" || prop=="whenBusy" || prop=="noAnswer" || prop=="defaultCall"){
						$("input[name='"+prop+"No']").attr("readOnly",true);
					}
				}
			}
			
			//设置呼转号码
			$("input[name='withoutConditionNo']").val(hlrInfo.withoutConditionNo);
			$("input[name='whenBusyNo']").val(hlrInfo.whenBusyNo);
			$("input[name='noAnswerNo']").val(hlrInfo.noAnswerNo);
			$("input[name='defaultCallNo']").val(hlrInfo.defaultCallNo);
			
			//设置短信功能
			$("input[name='smsFunction'][value='"+hlrInfo.smsFunction+"']").prop("checked", "checked");
			$("select[name='smsOriginalCall']").val(hlrInfo.smsOriginalCall);
			$("select[name='smsAnswerCall']").val(hlrInfo.smsAnswerCall);
			
			//设置其他参数
			$("select[name='callLimit']").val(hlrInfo.callLimit);
			$("select[name='betChain']").val(hlrInfo.betChain);
			$("select[name='applyChain']").val(hlrInfo.applyChain);
			$("select[name='rabedChain']").val(hlrInfo.rabedChain);
			$("select[name='copyChain']").val(hlrInfo.copyChain);
			
			//设置基本功能
			$("input[name='base1xFunction'][value='"+ hlrInfo.base1xFunction +"']").prop("checked",true);
			$("input[name='multiCall'][value='"+ hlrInfo.multiCall +"']").prop("checked",true);
			$("input[name='volte'][value='"+ hlrInfo.volte +"']").prop("checked",true);
			//设置智能网
			$("select[name='netWork']").val(hlrInfo.netWork);
			
			//设置漫游权限
			$("input[name='roamingPrivileges'][value='"+hlrInfo.roamingPrivileges+"']").prop("checked", "checked");
			//设置呼出权限
			$("input[name='callingPrivileges'][value='"+hlrInfo.callingPrivileges+"']").prop("checked", "checked");
			//设置呼叫保持
			$("input[name='callKeeping'][value='"+ hlrInfo.callKeeping +"']").prop("checked",true);
			
			//设置无线小区参数面板
			$("select[name='rruaType']").val(hlrInfo.rruaType);
			$("select[name='rruaStatus']").val(hlrInfo.rruaStatus);
			$("#uzid1").val(hlrInfo.uzid1);
			$("#uzid2").val(hlrInfo.uzid2);
			$("#uzid3").val(hlrInfo.uzid3);
			$("#uzid4").val(hlrInfo.uzid4);
			$("#uzid5").val(hlrInfo.uzid5);
			$("#uzid6").val(hlrInfo.uzid6);
			if(hlrInfo.rruaType == 1){
				$(".r4").show();
			}else{
				$(".r4").hide();
			}
	  }
	  
	  //转呼功能激活复选框设置单击事件
	  $('.zhActive').click(function () {
		  var check = $(this).is(':checked');
		  var name = $(this).prop("name");
		  if(check){
			  $("input[name='"+name+"No']").attr("readOnly",false);
		  }else{
			  $("input[name='"+name+"No']").attr("readOnly",true);
		  }
	  })
	  
	  /**
	   * HLR修改
	   */
	  $("#update").click(function(){
		  //判断hlrInfo是否为空
		  if(hlrInfo == null){
			  jp.warning("请先查询再修改");
			  return;
		  }else{
			  var params = getAfterModFormParams();
			  var changeFlag = false;
				  
			  for ( var p in params) {
				  if (p == "whenBusyNo" || p == "noAnswerNo" || p == "defaultCallNo"
						|| p == "withoutConditionNo") { // 号码参数，
					// 不管有没有改变
					continue;
				  }
				  if (params[p] == hlrInfo[p] || params[p] == null) { // 参数没有改变
					  params[p] = "a";
				  } else {
					  //alert("参数变了: 参数为" + p + ",修改后的值为：" + params[p]+ ",原来的值为： " + hlrInfo[p] );
					  changeFlag = true;
				  }
			  }
			  if (changeFlag == false) {
				   jp.warning("您没有做任何修改，请先做修改。");
				  return;
			  }

			  params.userNo = $("#userNo").val();
			  params.queryType = $("#queryType").val();
			  params.phoneType = hlrInfo.phoneType;
			  jp.loading("正在操作，请稍后...");
			  $.ajax({
					url:'${ctx}/hlr/hlrInfoFields/modify',
					dataType:'json',
					data:params,
					type:'POST',
					timeout:10000,
					success:function(data,status){
						if(data.success == true){
							hlrInfo = data.hlrInfo;
							setValues(hlrInfo);
						}
						if(!data.message.replace(/\s*/g,"")==""){
							jp.warning(data.message);
						}else if(data.success== false){
							jp.warning(data.message);
						}
					
					}
			})
			  
		  }
	  })
	  
	  //获取修改后的表单的参数值
	  function getAfterModFormParams(){
		    var params = {
		    		'whenBusyNo' : getInputValue("whenBusyNo"),
					'defaultCallNo' : getInputValue("defaultCallNo"),
					'noAnswerNo' : getInputValue("noAnswerNo"),
					'withoutConditionNo' : getInputValue("withoutConditionNo"),
					
					'callLimit' : getSelectValue("callLimit"),
					'applyChain' : getSelectValue("applyChain"),
					'copyChain' : getSelectValue("copyChain"),
					'rabedChain' : getSelectValue("rabedChain"),
					'betChain' : getSelectValue("betChain"),
					'netWork' : getSelectValue("netWork"),//智能网
					
					'userStatus' : getInputValue("userStatus"),//手机状态
					'smsOriginalCall' : getSelectValue("smsOriginalCall"),//短信功能始呼
					'smsAnswerCall' : getSelectValue("smsAnswerCall"),//短信功能终呼
					'base1xFunction' : $("input[name='base1xFunction']").eq(0).prop("checked") == true ? "1" : "0",
					'multiCall' : $("input[name='multiCall']").eq(0).prop("checked") == true ? "1" : "0",
					'callKeeping' : $("input[name='callKeeping']").eq(0).prop("checked") == true ? "1" : "0",
					'volte' : $("input[name='volte']").eq(0).prop("checked") == true ? "1" : "0",
					
					'callingPrivileges' : getRadioValue("callingPrivileges"),//呼出权限
					'roamingPrivileges' : getRadioValue("roamingPrivileges"),//漫游权限
					'smsFunction' : getRadioValue("smsFunction"),//短信功能授权
					
					'noAnswer' : getDoubleCheckboxValue("noAnswer"),
					'whenBusy' : getDoubleCheckboxValue("whenBusy"),
					'withoutCondition' : getDoubleCheckboxValue("withoutCondition"),
					'defaultCall' : getDoubleCheckboxValue("defaultCall"),
					'cfmn' : getDoubleCheckboxValue("cfmn"),
					'brtFunction' : getDoubleCheckboxValue("brtFunction"),
					'callWaiting' : getDoubleCheckboxValue("callWaiting"),
					'noDisturb' : getDoubleCheckboxValue("noDisturb"),
					'callDisplay1' : getDoubleCheckboxValue("callDisplay1")
//					'callDisplay2' : getDoubleCheckboxValue("callDisplay2")
		   };
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
	 
});
		

</script>