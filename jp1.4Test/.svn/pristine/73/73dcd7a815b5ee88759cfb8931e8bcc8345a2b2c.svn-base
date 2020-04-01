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
    
    //禁用停机状态下拉选
    $("#EPSLOCK option").attr("disabled","disabled");
	
     /**
      * volte-hss查询
      */
	$("#search").click("click", function() {// 绑定查询按扭
		  var phoneNum = $("#userNo").val();//电话号码
		  var type = $("#queryType").val();//查询类型：1手机号  2IMSI号
		  var city = $("#city").val();//城市
		  //校验手机号/IMSI号
		  if(phoneNum.replace(/\s*/g,"")==""){
			  if(type==1){
				  jp.warning("电话号码不能为空！");
			  }else{
				  jp.warning("IMSI号不能为空！");
			  }
			  return;
		  }else{
			 if(type==1 && !checkPhoneNo(phoneNum)){
				 jp.warning("电话号码格式不正确！");
				 return;
			 }else if(type==2 && !checkIMSINoFormat(phoneNum)){ 
				 jp.warning("请输入正确IMSI号码！");
				 return;
			 };
		  }
		  if(city.replace(/\s*/g,"")==""){
			  jp.warning("请选择地市！");
			  return;
		  }
		  //封装查询参数
		  var params = {};
          params.start = 0;
          params.limit = 10;
          params.city = city;
          params.type = type;
          params.usernumber = phoneNum;
          //查询前清空表格
          $("#apnTable").bootstrapTable('removeAll');
          $("#sifcTable").bootstrapTable('removeAll');
		  jp.loading("正在操作，请稍后...");
          //volteHss界面左侧查询
          $.ajax({
				url:'${ctx}/volte/volteHss/queryVolteHss',
				dataType:'json',
				data:params,
				type:'POST',
				timeout:10000,
				success:function(data,status){
					if(data.success){
						volteInfo = data.volteInfo;
						$("#IMSI_LTE1").val(volteInfo.IMSI_LTE);//L网IMSI号
						$("#MSISDN1").val(volteInfo.MSISDN);//手机号码
						$("input[name='Ramble'][value='"+volteInfo.Ramble+"']").prop("checked", "checked");//漫游权限
						//SIFC业务数据
						var hsifcArr = volteInfo["HSIFC"].split("-");
				        for (var i = 0; i < hsifcArr.length; i++) {
				            if (!hsifcArr[i] == "") {
				                var sifcIden = hsifcArr[i];
				                var sifc = formatHsifc(sifcIden);
				                var sifcRecord = { "sifcIden": sifcIden, "sifc": sifc };
				                $("#sifcTable").bootstrapTable('append', sifcRecord);
				            }
				        }
					}else{
						jp.error(data.errMsg);
					};
					
				}
			})
			//4GHss查询 (volteHss界面右侧)
			$.ajax({
				url:'${ctx}/volte/volteHss/query4GHss',
				dataType:'json',
				data:params,
				type:'POST',
				timeout:10000,
				success:function(data,status){
					if(data.success){
						$("#IMSI_LTE2").val(data.IMSI_LTE);
						$("#MSISDN2").val(data.MSISDN);
						$("#VOLTETAG").val(data.VOLTETAG);
						$("#EPSLOCK").val(data.EPSLOCK);
						volteInfo.EPSLOCK = data.EPSLOCK;
						$("#apnTable").bootstrapTable('append', data.infoList);
					}else{
						jp.error(data.errMsg);
					};
					
				}
			})
			jp.close();
	});
	
	  /**
	   * volte-hss修改
	   */
	  $("#update").click(function(){
		  //判断volteInfo是否为空
		  if(volteInfo == null){
			  jp.warning("请先查询再修改");
			  return;
		  }else{
			  var params = {};
			  params.usernumber = volteInfo.usernumber;
			  params.type = volteInfo.type;
			  params.Ramble = $("input[name='Ramble']:checked").val();
			  params.EPSLOCK = volteInfo.EPSLOCK;
			  params.VOLTETAG= $("select[name='VOLTETAG']").val();
			  params.city = $("#city").val();
			  //城市不能为空
			  if(params.city.replace(/\s*/g,"")==""){
				  jp.warning("请选择地市！");
				  return;
			  }
			  
			  jp.loading("正在操作，请稍后...");
			  $.ajax({
				  url:'${ctx}/volte/volteHss/hssmodify',
					dataType:'json',
					data:params,
					type:'POST',
					timeout:10000,
					success:function(data,status){
						jp.close();
						if(data.success){
							$("#search").click();
						}else{
							jp.error(data.errMsg);
						}
						if(data.message!=null&&!data.message.replace(/\s*/g,"")==""){
							jp.warning(data.message);
						}else if(!data.success){
							jp.warning("修改失败");
						}
					
					}
			})
			  
		  }
	  })
	  
	   $("#del").click(function(){
		  //判断volteInfo是否为空
		  if(volteInfo == null){
			  jp.warning("请先查询再修改");
			  return;
		  }
		  jp.confirm('你确定要删除吗？',  function(){
			  jp.loading("正在操作，请稍后...");
			  var params = {};
			  params.type = $("#queryType").val();
		      params.usernumber = $("#userNo").val();
			  $.ajax({
				  url:'${ctx}/volte/volteHss/deleVoLTEHSS',
				  dataType:'json',
				  data:params,
				  type:'POST',
				  timeout:10000,
				  success:function(data,status){
					  jp.close();
					  if(data.success){
						  jp.success("删除成功！");
					  }else{
						  jp.error("删除失败！");
					  }
					  
				  }
			  })
		  })
	  })
	  
	  /**
	   * 增加IMS-APN
	   */
	  $("#addAPN").click(function(){
		  var phoneNum = $("#num").val();//电话号码
		  var type = $("#type").val();//操作类型：1按手机号操作  0按IMSI操作
		  var apntplid = $("#apntplid").val();//apn
		  var city = $("#city").val();
		  //校验手机号/IMSI号
		  if(phoneNum.replace(/\s*/g,"")==""){
			  if(type==1){
				  jp.warning("电话号码不能为空！");
			  }else{
				  jp.warning("IMSI号不能为空！");
			  }
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
		  if(apntplid.replace(/\s*/g,"")==""){
			  jp.warning("请选择APN！");
			  return;
		  }
		  if(city.replace(/\s*/g,"")==""){
			  jp.warning("请选择城市！");
			  return;
		  }
		  //发送ajax请求
		  var params = {};
		  params.num = phoneNum;
		  params.type = type;
		  params.apntplid = apntplid;
		  params.businessType = "ADD_APN";
		  params.city = city;
		  jp.loading("正在操作，请稍后...");
		  $.ajax({
			  url:'${ctx}/volte/volteHss/businessModify',
			  dataType:'json',
			  data:params,
			  type:'POST',
			  timeout:10000,
			  success:function(data,status){
				  jp.close();
				  if(data.success){
					  jp.success(data.result);
					  $('#apnModal').modal('hide');
					  $("#search").click();
				  }else{
					  jp.error(data.result);
				  }
			  }
		  
		  })
		  
	  })
	  
	  /**
	   * 删除IMS-APN
	   */
	  $("#delAPN").click(function(){
		  //判断volteInfo是否为空
		  if(volteInfo == null){
			  jp.warning("请先查询!");
			  return;
		  }
		  jp.confirm('你确定要删除吗？',  function(){
			  jp.loading("正在操作，请稍后...");
			  var params = {};
			  params.type = $("#queryType").val();
		      params.num = $("#userNo").val();
		      params.city = $("#city").val();
		      params.businessType = "DEL_APN";
			  $.ajax({
				  url:'${ctx}/volte/volteHss/businessModify',
				  dataType:'json',
				  data:params,
				  type:'POST',
				  timeout:10000,
				  success:function(data,status){
					  jp.close();
					  if(data.success){
						  jp.success("删除成功！");
						  $("#search").click();
					  }else{
						  jp.error("删除失败,请与管理员联系！");
					  }
					  
				  }
			  })
		  })
	  })
	  

	//SIFC表格
	$('#sifcTable').bootstrapTable({
		  	   //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: false,
    	       //显示切换分页按钮
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
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: 'sifcIden',
		        title: 'SIFC',
		        width : '10%'
		       
		    }
			,{
		        field: 'sifc',
		        title: '标识',
		        width : '10%'
		       
		    }
		     ]
		
		});
	
	//用户APN签约情况表格
	$('#apnTable').bootstrapTable({
		 
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
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: 'APNName',
		        title: 'APN',
		        width : '10%'
		       
		    }
			,{
		        field: 'APNIden',
		        title: '标识',
		        width : '10%'
		       
		    }
		     ]/*,
		     data: [{
		    	 	APNName: 116,
		    	 	APNIden: 'IMS'
	            }, {
	            	APNName: 117,
	            	APNIden: '紧急呼叫'
	            }]*/
		
		});
	
	$("#queryType").change(function(){
	     var selVal=$(this).val();
	     if(selVal==2){
	    	 $("label[for='userNo']:first").html("IMSI号码"); 
	     }else{
	    	 $("label[for='userNo']:first").html("手机号码");
	     }
	});
	
	$("#type").change(function(){
		var selVal=$(this).val();
		if(selVal==0){
			$("label[for='num']").html("IMSI号码"); 
		}else{
			$("label[for='num']").html("手机号码");
		}
	});
	
	//HSIFC标识码表
	function formatHsifc(v) {
        if (v == 2070) {
            return "彩铃";
        } else if (v == 1910) {
            return "基本业务";
        } else if (v == 1911) {
            return "基本业务（一号多终端副卡）";
        } else if (v == 1920) {
            return "预付费";
        } else if (v == 1930) {
            return "省IVPN";
        } else if (v == 1931) {
            return "省IVPN（一号多终端副卡）";
        } else if (v == 1932) {
            return "亲友在线";
        } else if (v == 1934) {
            return "省内一卡双号";
        } else if (v == 1936) {
            return "省际一卡双号";
        } else if (v == 1940) {
            return "省IVPN测试";
        } else if (v == 1950) {
            return "携号转网";
        } else if (v == 1952) {
            return "一号双机";
        } else if (v == 1980) {
            return "短信1";
        } else if (v == 1982) {
            return "短信2";
        } else if (v == 1984) {
            return "短信3";
        } else if (v == 1986) {
            return "短信4";
        } else if (v == 7350) {
            return "集团IVPN";
        } else {
            return v;
        }
    }
	
	  
	 
});
		

</script>