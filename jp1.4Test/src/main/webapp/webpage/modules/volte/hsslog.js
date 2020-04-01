<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	//设置全局url 搜索参数
	var searchParam = {};
	//年月份控件
	//初始化开始时间和结束时间
	 var yesterday = new Date(new Date().getTime()-3600*1000*24);
	 var yesterdayBegin = new Date(yesterday);//昨天开始
	 yesterdayBegin.setHours(0);
	 yesterdayBegin.setMinutes(0);
	 yesterdayBegin.setSeconds(0);
	 var yesterdayEnd = new Date(yesterday);//昨天结束
	 yesterdayEnd.setHours(23);
	 yesterdayEnd.setMinutes(59);
	 yesterdayEnd.setSeconds(59);
	 $('#startDate').datetimepicker({
		 format: "YYYY-MM",
		 defaultDate:yesterdayBegin
	})
	
	//表格
	$('#hssLogTable').bootstrapTable({
		 
		  	   //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: false,
               //显示切换手机试图按钮
               //showToggle: true,
               //显示 内容列下拉框
    	       //showColumns: true,
    	       //显示到处按钮
    	       //showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: false,
    	       //显示详情按钮
    	       //detailView: true,
    	       	//显示详细内容函数
	          // detailFormatter: "detailFormatter",
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: true,   
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
               url: "${ctx}/hss/queryHssLog",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
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
		        field: 'SERIAL_NO',
		        title: '序号',
		        width : 40
		       
		    }
			,{
		        field: 'OPERATOR_NAME',
		        title: '操作员',
		        width : 60
		       
		    }
			,{
		        field: 'OPERATION_TIME',
		        title: '操作时间',
		        width : 75
		       
		    }
			,{
		        field: 'HLR_INDEX',
		        title: 'HSS序列号',
		        width : 75 
		       
		    },{
		        field: 'IMSI_NO',
		        title: 'IMSI号码',
		        width : 110
		    },{
		        field: 'MSISDN_NO',
		        title: '手机号码',
		        width : 100
		    },{
		        field: 'CMDRESULT',
		        title: '操作结果',
		        width : 60
		    },{
		        field: 'MML_COMMAND',
		        title: '命令功能',
		        width : 60
		    },{
		        field: 'MSG_TYPE',
		        title: '消息类型',
		        width : 60
		    },{
		        field: 'NET_TYPE',
		        title: '网元类型',
		        width : 70
		    },{
		        field: 'ERROR',
		        title: '错误原因',
		        width : 140
		    },{
		        field: 'ERRORcoding',
		        title: '错误码',
		        width : 80
		    },{
		        field: 'MML_COMMANDDETAIL',
		        title: 'MML命令内容',
		        width : 180
		    },{
		        field: 'COMMAND_NO',
		        title: '命令',
		        width : 70
		    },{
		        field: 'ERRORCODE',
		        title: '预处理指引',
		        width : 80
		    }
		     ]
		
		});
	
	$("#queryType").change(function(){
	     var selVal=$(this).val();
	     if(selVal==1){
	    	 $("label[for='userNo']").html("手机号码");
	     }else{
	    	 $("label[for='userNo']").html("IMSI号码"); 
	     }
	});
	
	$("#search").click("click", function() {// 绑定查询按扭
		  var phoneNum = $("#userNo").val();//电话号码
		  var type = $("#queryType").val();//查询类型：1手机号  2IMSI号
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
		 
		  //封装查询参数
		  searchParam.type = type;
		  searchParam.usernumber = phoneNum;
		  searchParam.startDate = $("input[name='startDate']").val();
		  $('#hssLogTable').bootstrapTable('refresh');
		  
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