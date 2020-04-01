<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	//定义查询标志 0未查询 1已查询
	var ctogInfo = null;
	//设置全局查询条件
	var userNo = "";//查询号码
	var type = "";//查询类型
	
	//设置输入框为只读
	$(".r1 .form-control").attr("readonly",true);
	$("#outerRoamAuth option").attr("disabled","disabled");
	
	$("#searchType").change(function(){
	     var selVal=$(this).val();
	     if(selVal==1){
	    	 $("label[for='userNo']").html("C网IMSI号码"); 
	     }else if(selVal == 2){
	    	 $("label[for='userNo']").html("G网IMSI号码");
	     }else{
	    	 $("label[for='userNo']").html("手机号码");
	     }
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
	
	 /**
	   * 双网模关查询
	   */    
	  $("#search").click("click", function() {// 绑定查询按扭
		  userNo = $("#userNo").val();//用户号码
		  type = $("#searchType").val();//查询类型：0手机号  1C网IMSI号码 2G网IMSI号码
		  //校验手机号/IMSI号
		  if(userNo.replace(/\s*/g,"")==""){
			  if(type==0){
				  jp.warning("电话号码不能为空！");
			  }else{
				  jp.warning("IMSI号不能为空！");
			  }
			  return;
		  }else{
			 if(type==0 && !checkPhoneNo(userNo)){
				 jp.warning("电话号码格式不正确！");
				 return;
			 }else if((type==1||type==2)&&!checkIMSINoFormat(userNo)){
				 jp.warning("请输入正确IMSI号码！");
				 return;
			 };
		  }
		  //每次查询都要先清空表单
		  $('#ctogForm')[0].reset();
		  $("#userNo").val(userNo);
		  $("#searchType").val(type);
		  jp.loading("正在操作，请稍后...");
		  $.ajax({
				url:'${ctx}/ctog/select',
				dataType:'json',
				data:{userNoValue:userNo,searchType:type},
				type:'POST',
				timeout:10000,
				success:function(data,status){
					jp.close();
					if(data.success){
						ctogInfo = data.result[0];
						setValues(ctogInfo);
					}else{
						jp.error(data.message);
					}
						
				}
			})
		  
		});
	  
	  //修改漫游权限
	  $("#update").click(function(){
		  if(ctogInfo==null){
			  jp.warning("请先查询再修改");
			  return;
		  }
		  //弹出修改漫游权限模态框
		  $("#outerRoamAuthModal").modal('show');
		  //设置弹窗的选中值为原先的值
		  $("#outerRoamAuth option").removeAttr("disabled");//设置option为可用才可以获取值
		  var outerRoamAuth = $("select[name='outerRoamAuth']").val();
		  $("select[name='outerRoamAuth1']").val(outerRoamAuth);
		  $("#outerRoamAuth option").attr("disabled","disabled");
	  })
	  
	  //设置弹窗确定单击事件
	  $("#updateAuth").click(function(){
		  //获取选中的值，判断与原先的是不是一样，若一样则直接关闭弹窗，不一样才发送ajax请求
		  $("#outerRoamAuth option").removeAttr("disabled");//设置option为可用才可以获取值
		  var outerRoamAuth1 = $("select[name='outerRoamAuth1']").val();//选中的值
		  var outerRoamAuth = $("select[name='outerRoamAuth']").val();//原先的值
		  if(outerRoamAuth1 == outerRoamAuth){
			  $("#outerRoamAuth option").attr("disabled","disabled");
			  $('#outerRoamAuthModal').modal('hide');
			  return;
		  }
		  //封装参数
		  var params = {};
		  params.isdn = $("#isdn").val();
		  params.modifyType = 0;
		  params.modifyValue = outerRoamAuth1;
		  params.operatedType = 1;
		  //发送请求
		  $.ajax({
			  url:'${ctx}/ctog/modify',
			  dataType:'json',
			  data:params,
			  type:'POST',
			  timeout:10000,
			  success:function(data,status){
				  jp.close();
				  if(data.success){
					  $('#outerRoamAuthModal').modal('hide');
					  $("select[name='outerRoamAuth']").val(outerRoamAuth1);
				  }else{
					  jp.error(data.message);
				  }
				  $("#outerRoamAuth option").attr("disabled","disabled");
				  
			  }
		  })
	  })
	  
	  //删除用户数据
	  $("#delUserData").click(function(){
		  if(ctogInfo==null){
			  jp.warning("请先作查询操作");
			  return;
		  }
		  var isdn = $("#isdn").val();
		  var msg = "您正在对用户<font color='red'>"+isdn+"</font>执行<font color='red'>删除用户数据</font>操作，确认要继续么？";
		  jp.confirm(msg,  function(){
			  //获取修改的参数
			  var params = {};
			  params.operatedType = 3;
			  params.isdn = isdn;
			  params.gimsi =$("#gimsi").val();
			  //发送ajax查询
			  jp.post("${ctx}/ctog/modify", params,function(data){
				  if(data.success){
					  jp.success(data.message);
					  //清空表单
					  $('#ctogForm')[0].reset();
					  $("#userNo").val(userNo);
					  $("#searchType").val(type);
				  }else{
					  jp.error(data.message);
				  }
			  })
		  })
	  })
	  
	  //删除用户位置信息
	  $("#delUserLoc").click(function(){
		  if(ctogInfo==null){
			  jp.warning("请先作查询操作");
			  return;
		  }
		  var isdn = $("#isdn").val();
		  var msg = "您正在对用户<font color='red'>"+isdn+"</font>执行<font color='red'>删除用户位置信息</font>操作，确认要继续么？";
		  jp.confirm(msg,  function(){
			  //获取修改的参数
			  var params = {};
			  params.operatedType = 4;
			  params.isdn = isdn;
			  //发送ajax查询
			  jp.post("${ctx}/ctog/modify", params,function(data){
				  if(data.success == true){
					  jp.success(data.message);
					  //清空用户位置信息
					  $("#mscNumber").val("");
					  $("#vlrNumber").val("");
				  }else{
					  jp.error(data.message);
				  }
			  })
		  })
	  })
	  
	  
	  /**************HLR比对开始**************/
	  //比对参数
	  var ArrCHCompareProtocol = [['IMSI号码', 'imsi'] , ['漫游权限', 'outerRoamAuth']];
	  //ctog的“仅国际漫游”与HLR的“国际漫游权限”对应
	  var ArrCHHLROuterRoamAuth = ['无漫游权限', '省内漫游权限', '国内漫游权限', '国际漫游权限'];
	  var ArrCHCTOGOuterRoamAuth = ['未开通', '仅国际漫游', '仅国内漫游', '国内+国际漫游'];

	  /* 如果为数组，则解析 */
	  var ArrCHHLRDecodProtocol = ['', ArrCHHLROuterRoamAuth];
	  var ArrCHCTOGDecodProtocol = ['', ArrCHCTOGOuterRoamAuth];
	  
	  $("#hlrCompare").click(function(){
		  if(ctogInfo==null){
			  jp.warning("请先查询该号码的用户信息，再进行比对！");
			  return;
		  }
		  
		  //发送ajax请求
		  var params = {};
		  params.userNoValue = $("#userNo").val();//用户号码
		  params.searchType = $("#searchType").val();//查询类型：0手机号  1C网IMSI号码 2G网IMSI号码;
		  jp.post("${ctx}/ctog/selectFromHLR", params,function(data){
			  if(data.success){
				  //比对
				  hlrCompare(ctogInfo,data.result[0]);
			  }else{
				  jp.error(data.message);
			  }
		  })
		  
		  //弹窗比对窗口，展示数据
		  $("#hlrCompareModal").modal('show');
		  
		  
	  })
	  
	  //比对
	  function hlrCompare(functionCode,functionCodeHLR){
		  //先清空表格
		  $("#hlrCompareTable").bootstrapTable('removeAll');
		  for (var i = 0; i < ArrCHCompareProtocol.length; i++) {
				var record = {};
				record.functionName = ArrCHCompareProtocol[i][0];
				if (typeof ArrCHHLRDecodProtocol[i] == 'object') {
					// 参看ctogProtocol.js中存放的状态为对应的中文意思
					// 如果为数组则进行中为解析 HLR的“国内漫游权限”对应双模网关的“仅国内漫游权限”“未开通”
					// HLR的“国际漫游权限”对应双模网关的“仅国际漫游权限”“国际加国内漫游权限”
					record.status = ArrCHCTOGDecodProtocol[i][functionCode[ArrCHCompareProtocol[i][1]]];
					record.statusHLR = ArrCHHLRDecodProtocol[i][functionCodeHLR[ArrCHCompareProtocol[i][1]]];

				} else {
					record.status = functionCode[ArrCHCompareProtocol[i][1]];
					record.statusHLR = functionCodeHLR[ArrCHCompareProtocol[i][1]];

				}
				// 漫游权限
				if (i == 1) {
					if ((functionCode[ArrCHCompareProtocol[i][1]] == functionCodeHLR[ArrCHCompareProtocol[i][1]])
							|| (functionCode[ArrCHCompareProtocol[i][1]] == 1 && functionCodeHLR[ArrCHCompareProtocol[i][1]] == 3)
							|| (functionCode[ArrCHCompareProtocol[i][1]] == 0 && functionCodeHLR[ArrCHCompareProtocol[i][1]] == 2)) {
						record.comparedResult = '一致';
					} else {
						record.comparedResult = '<font color="red">不一致</font>';
					}
				} else {
					if (record.status == record.statusHLR) {
						record.comparedResult = '一致';
					} else {
						record.comparedResult = '<font color="red">不一致</font>';
					}
				}
				$("#hlrCompareTable").bootstrapTable('append', record);

			}
	  }
	 
	  
	  $('#hlrCompareTable').bootstrapTable({
		  	   //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: false,
    	       showPaginationSwitch: false,
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: false,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               fixedColumns: true, 
               fixedNumber: 1,
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "",
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               columns: [{
            	    field: 'functionName',
            	    title: '功能项',
            	    width : '20%'
            	   }
	               ,{
			        field: 'status',
			        title: 'CTOG平台',
			        width : '20%'
	               },{
			        field: 'statusHLR',
			        title: 'HLR平台',
			        width : '20%'
		           },{
			        field: 'comparedResult',
			        title: '比对结果',
			        width : '20%'
			       }
               ]
	  });
	  /**************HLR比对结束**************/
	  

	  //设置表单的值
	  function setValues(ctogInfo){
		  //根据G网IMSI号码设置用户卡类型
		  ctogInfo.gimsiType=getGimsiType(ctogInfo.gimsi);
		  //设置短消息投递挂起标志   0 无待发短信    1 有待发短信
		  if(ctogInfo.smsdpf == "0"){
			  ctogInfo.smsdpf="无待发短信";
		  }else if(ctogInfo.smsdpf == "1"){
			  ctogInfo.smsdpf="有待发短信";
		  };
		  //设置网络接入模式
		  if(ctogInfo.nam == "0"){
			  ctogInfo.nam="GSM语音功能和GPRS数据功能";
		  }else if(ctogInfo.nam == "1"){
			  ctogInfo.nam="GSM语音功能";
		  }else if(ctogInfo.nam == "2"){
			  ctogInfo.nam="GPRS数据功能";
		  };
		  //设置预付费标识  1 预付费用户     其他值 普通用户
		  if(ctogInfo.ocsitpl == "1"){
			  ctogInfo.ocsitpl="预付费用户";
		  }else{
			  ctogInfo.ocsitpl="普通用户";
		  }
		  //设置PS域计费特性 TODO :
		  //ctog的js方法 getStoreByType("ctog_charge")  再调用stores.js 
		  //最后请求BasisCodingController  查表basis_coding_details2获取对应的值
		  
		  
		  //设置输入框的值
		  for (x in ctogInfo) { 
			  	if(x == "isdn"){
			  		var reg = /^86/;
					if (reg.test(ctogInfo[x])) {
						ctogInfo[x] = ctogInfo[x].substr(2);
					}
			  	}
		        $("input[name='"+x+"']").val(ctogInfo[x]);
		        if(x=="outerRoamAuth"){
		        	//跨网漫游权限
		        	$("select[name='"+x+"']").val(ctogInfo[x]);
		        }
		  }
	  }
	  
	  //获取用户卡类型
	  function getGimsiType(gimsi){
	    if(gimsi==null||gimsi=="") return "";
	    if(gimsi.indexOf("46001") ==0 ) return "旧双模卡";
	    if(gimsi.indexOf("45404") ==0 ) return "旧天翼国际卡";
	    if(gimsi.indexOf("20404") ==0 ) return "新天翼国际卡";
	    //if(gimsi.indexOf("45404") ==0 || gimsi.indexOf("20404") ==0) return "天翼国际卡";
	    return "";
	  }
	 
});
		

</script>