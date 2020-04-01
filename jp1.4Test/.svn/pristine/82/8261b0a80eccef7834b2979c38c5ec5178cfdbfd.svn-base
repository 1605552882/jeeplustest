<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	var width = window.screen.width;
	var height = window.screen.height;
	//业务更改
	$("#pcrfModifyWindow").click(function(){
		jp.openDialog("PCRF业务更改","${ctx}/lnet/pcrf/pcrfModifyWindow",width*0.4+"",height*0.4+"", "");
	})
	
	//批量操作
	$("#pcrfBatchOperateWindow").click(function(){
		jp.openDialog("PCRF批量操作","${ctx}/lnet/pcrf/pcrfBatchOperateWindow",width*0.7+"",height*0.8+"", "");
	})
	
	//初始化表格
	initTable();
	function initTable(){
	$('#table').bootstrapTable({
		 
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
               url: "",
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
               sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){},
               /*onClickRow: function(row, $el){
               },*/
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: '',
		        title: '用户标识',
		        formatter:function(value, row , index){
		        }
		       
		    },{
		        field: '',
		        title: '业务编码'
		    }
			,{
		        field: '',
		        title: '业务名称'
		       
		    }
		     ]
		
		});
	}
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端
		  $('#table').bootstrapTable("toggleView");
		}


	});
  

	$("#search").click("click", function() {// 绑定查询按扭
	  initTable();
	});
	
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $('#table').bootstrapTable('refresh');
		});

  	function exportAll(){
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "");
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
</script>