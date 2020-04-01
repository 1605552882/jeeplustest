<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	var smsstatus = null;//短信状态
	var smscity = null;//城市
	//获取短信黑名单状态编码数据
	$.ajax({
		url:'${ctx}/basisCoding/getListStoreByTypeFK',
		dataType:'json',
		data:{"type" : "smsstatus"},
		type:'POST',
		timeout:10000,
		async: false,//同步
		success:function(data,status){
			smsstatus = data.codingList;
		}
	})
	
	//获取城市编码数据
	$.ajax({
		url:'${ctx}/basisCoding/getListStoreByTypeFK',
		dataType:'json',
		data:{"type" : "smscity"},
		type:'POST',
		timeout:10000,
		async: false,//同步
		success:function(data,status){
			smscity = data.codingList;
		}
	})
	
	//获取短信黑名单状态编码对应的值
	function getStatusValueByCode(code){
		var columnValue = code;
		for(var i=0;i<smsstatus.length;i++){
			 var codingDetails = smsstatus[i];
			 if(code == codingDetails.coding){
				columnValue = codingDetails.chineseDescription;
				break;
			 }
		}
		return columnValue;
	}
	
	//获取城市名
	function getCityValueByCode(code){
		var columnValue = code;
		for(var i=0;i<smscity.length;i++){
			 var codingDetails = smscity[i];
			 if(code == codingDetails.coding){
				 columnValue = codingDetails.chineseDescription;
				 break;
			 }
		  }
		return columnValue;
	}
	
	//设置全局url 搜索参数
	var searchParam = {};
	
	//表格
	$('#blackListTable').bootstrapTable({
		 
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
//               url: "${ctx}/sms/selectUserByOther",
               url: "",
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
		        field: '',
		        title: '序号',
		        width : 40,
		        formatter: function (value, row, index) { 
		            return index+1;  
		        }  
		       
		    }
			,{
		        field: 'phoneNum',
		        title: '电话号码',
		        width : 60
		       
		    }
			,{
		        field: 'status',
		        title: '状态',
		        width : 75,
		        formatter: function (value, row, index) {
		            return getStatusValueByCode(value);  
		        } 
		       
		    }
			,{
		        field: 'areaCode',
		        title: '地区',
		        width : 75,
		        formatter: function (value, row, index) {
		            return getCityValueByCode(value);;  
		        }  
		       
		    },{
		        field: 'deadline',
		        title: '进入黑名单时间',
		        width : 110
		    },{
		        field: 'reason',
		        title: '原因',
		        width : 100
		    },{
		        field: 'releaseTime',
		        title: '最近解黑时间',
		        width : 60
		    },{
		        field: 'releaseCount',
		        title: '本月解黑次数',
		        width : 60
		    },{
		        field: 'limit',
		        title: '是否限制解黑',
		        width : 30,
		        formatter: function (value, row, index) {
		        	if(value == "0"){
		        		return "否";
		        	}else{
		        		return "是";
		        	}
		        }  
		    }
		     ]
		
		});
	
	$("#search").click("click", function() {// 绑定查询按扭
		  var userNo = $("#userNo").val();//电话号码
		  if(userNo.replace(/\s*/g,"")==""){
			  jp.warning("号码不能为空！");
			  return;
		  }else if(!checkPhoneNo(userNo)){
			  jp.warning("电话号码格式不正确！");
			  return;
		  }
		  //封装查询参数
		  searchParam.areaCode = userNo;
		  $('#blackListTable').bootstrapTable('refresh', {url:"${ctx}/sms/selectUserInfoByNumber"});
		  
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