<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	 //初始化开始时间和结束时间
	 var yesterday = new Date(new Date().getTime()-3600*1000*24);
	 var start = new Date(yesterday);//昨天开始
	 start.setHours(0);
	 start.setMinutes(0);
	 start.setSeconds(0);
	 var end = new Date(yesterday);//昨天结束
	 end.setHours(23);
	 end.setMinutes(59);
	 end.setSeconds(59);
	 $('#startDate').datetimepicker({
		 format: "YYYY-MM-DD",
		 defaultDate:start
	})
	$('#startTime').datetimepicker({
		 format: "HH:mm:ss",
		 defaultDate:start
	})
	$('#endDate').datetimepicker({
		 format: "YYYY-MM-DD",
		 defaultDate:end
	})
	$('#endTime').datetimepicker({
		 format: "HH:mm:ss",
		 defaultDate:end
	})
	//初始化表格
	initTable();
	function initTable(newUrl){
	$('#accountLogTable').bootstrapTable("destroy");
	$('#accountLogTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: false,
               //显示切换手机试图按钮
               showToggle: false,
               //显示 内容列下拉框
    	       showColumns: false,
    	       //显示到处按钮
    	       showExport: false,
    	       //显示切换分页按钮
    	       showPaginationSwitch: false,
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
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: newUrl,
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义    
               contentType : "application/x-www-form-urlencoded",
               queryParams : function(params) {
        	    var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "client",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){},
               /*onClickRow: function(row, $el){
               },*/
               responseHandler: function(data){
            	   debugger
            	   return data.body.alList;
            	},
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: 'no',
		        title: '序号',
		        width: '5%',
		        formatter:function(value, row , index){
	                //获取每页显示的数量
	                var pageSize=$('#accountLogTable').bootstrapTable('getOptions').pageSize;
	                //获取当前是第几页
	                var pageNumber=$('#accountLogTable').bootstrapTable('getOptions').pageNumber;
	                //返回序号，注意index是从0开始的，所以要加上1
	                return pageSize * (pageNumber - 1) + index + 1;
		        }
		    },{
		        field: 'procTime',
		        title: '记录时间'
		    },{
		        field: 'imsi',
		        title: 'IMSI'
		    }
			,{
		        field: 'action',
		        title: '操作'
		    }
			,{
		        field: 'mdn',
		        title: 'MDN'
		    },{
		        field: 'rescode',
		        title: '操作结果'
		    }
		     ]
		
		});
	}
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端
		  $('#accountLogTable').bootstrapTable("toggleView");
		}

	$("#search").click("click", function() {// 绑定查询按扭
		//验证查询号码
		// imsi正则验证
		var imsiNoRegex = /^(((46003)|(20404)|(45404)|(45403))[0-9]{10})$/;
		if(imsiNoRegex.test($("#imsiNumber").val())){
			initTable("${ctx}/aaa/aaa/getAccountLogToGridJSON");
		}else{
			jp.warning("请输入正确的IMSI号码");
		}
	});
	
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#imsiNumber").val("");
//		  $('#accountLogTable').bootstrapTable('refresh');
		});

  	function exportAll(){
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/aaa/aaa/exportAccountLog");
		 $('body').append(form);
		  $("#searchForm input").each(function(index, element) { 
		 			 	var input1 = $('<input>'); 
		 				input1.attr('type','hidden'); 
		 				input1.attr('name',element.name); 
		 				input1.attr('value',element.value); 
		 				form.append(input1);
		 				    }); 
		 form.submit();
	}
  	
});
</script>