<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
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
	 $('#beginReportTime').datetimepicker({
		 format: "YYYY-MM-DD 00:00:00",
		 defaultDate:yesterdayBegin
	});
	$('#endReportTime').datetimepicker({
		 format: "YYYY-MM-DD 23:59:59",
		 defaultDate:yesterdayEnd
	});
	
	$('#documentchouseTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               //showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
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
               url: "${ctx}/documentchoose/documentchouse/data",
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
               sidePagination: "client",
               responseHandler: function(data){
            	     return data.rows;
            	},
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	window.location = "${ctx}/documentchoose/documentchouse/form?id=" + row.id;
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该工单抽取记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/documentchoose/documentchouse/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#documentchouseTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
               },
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: 'sbillno',
		        title: '单据编号',
		        width: '20%',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return "<a onclick=\"newtable('"+row.sbillno+"','"+row.remarks+"','"+row.srepfaultdetail+"','"+row.sprocesssummary+"','"+row.checkrule+"')\">"+value+"</a>";
		        }
		       
		    }
			,{
		        field: 'result',
		        title: '结论',
		        sortable: true
		       
		    }
			,{
		        field: 'reportTime',
		        title: '申告时间',
		        sortable: true
		       
		    },{
		        field: 'group',
		        title: '责任班组',
		        sortable: true
		       
		    },{
		        field: 'people',
		        title: '责任人',
		        sortable: true
		       
		    },{
		        field: 'remarks',
		        title: '是否检查',
		        sortable: true,
		        formatter:function(value, row , index){
		        	if (row.remarks == "已检查") {
		        		return "<font color='#FF0000' >已检查</span>";
		        	} else {
		        		return "未检查";
		        	}
		        }
		       
		    },{
		        field: 'sStatus',
		        title: '单据状态',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#documentchouseTable').bootstrapTable("toggleView");
		}
	  
	  $('#documentchouseTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#documentchouseTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#documentchouseTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/documentchoose/documentchouse/import/template';
				  },
			    btn2: function(index, layero){
				        var inputForm =top.$("#importForm");
				        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				        inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				        inputForm.onsubmit = function(){
				        	jp.loading('  正在导入，请稍等...');
				        }
				        inputForm.submit();
					    jp.close(index);
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		
		$("#searchForm").validate({
			submitHandler: function(form){
				 $('#documentchouseTable').bootstrapTable('refresh');
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});    
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#documentchouseTable').bootstrapTable('refresh');
		});
		
		var a = setInterval(function(){reflush()}, 1000);
		
	});
	function newtable(sbillno, remarks ,srepfaultdetail, sprocesssummary, checkrule) {

		jp.post("${ctx}/overtimedocument/overtimedocument/newtable",{sbillno:sbillno},function(data){
			if(data.success){
				var detailId = data.body.documentarydetails.id;
				//window.location="${ctx}/documentdetect/documentarydetails/samplform?id="+id;
				jp.openTab("${ctx}/documentdetect/documentarydetails/samplform?id="+detailId + "&detail="+srepfaultdetail+"&summary="+sprocesssummary+"&rule="+checkrule ,"抽样-工单明细", false);
			}
		});
	}
		
  function getIdSelections() {
        return $.map($("#documentchouseTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该工单抽取记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/documentchoose/documentchouse/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#documentchouseTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function edit(){
	  window.location = "${ctx}/documentchoose/documentchouse/form?id=" + getIdSelections();
  }
  
	function exportAll(){
		 // window.location = "${ctx}/documentchoose/documentchouse/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/documentchoose/documentchouse/export");
		 $('body').append(form);
		  $("#searchForm input").each(function(index, element) {
		 			 	var input1 = $('<input>'); 
		 				input1.attr('type','hidden'); 
		 				input1.attr('name',element.name); 
		 				input1.attr('value',element.value); 
		 				form.append(input1);
		 				    });
		  $("#searchForm select").each(function(index, element) {
			 	var input1 = $('<input>'); 
				input1.attr('type','hidden'); 
				input1.attr('name',element.name); 
				input1.attr('value',element.value); 
				form.append(input1);
				    });
		 form.submit();
	}
	
	function reflush() {
		var data=sessionStorage.getItem("token");
		if (data) {
			$('#documentchouseTable').bootstrapTable('refresh');
			sessionStorage.removeItem('token');
		}
	}
	
  
</script>