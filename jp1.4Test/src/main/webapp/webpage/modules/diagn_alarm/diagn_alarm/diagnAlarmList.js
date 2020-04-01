<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#diagnAlarmTable').bootstrapTable({
		 
		  // 请求方法
               method: 'get',
               // 类型json
               dataType: "json",
               // 显示刷新按钮
               showRefresh: false,
               // 显示切换手机试图按钮
               showToggle: false,
               // 显示 内容列下拉框
    	       showColumns: false,
    	       // 显示到处按钮
    	       showExport: false,
    	       // 显示切换分页按钮
    	       showPaginationSwitch: false,
    	       // 最低显示2行
    	       minimumCountColumns: 2,
               // 是否显示行间隔色
               striped: true,
               // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
               cache: false,    
               // 是否显示分页（*）
               pagination: true,   
                // 排序方式
               sortOrder: "asc",  
               // 初始化加载第一页，默认第一页
               pageNumber:1,   
               // 每页的记录行数（*）
               pageSize: 10,  
               // 可供选择的每页的行数（*）
               pageList: [10, 25, 50, 100],
               // 这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
               url: "${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/data",
               // 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
				// Else
               // queryParamsType:'',
               // //查询参数,每次调用是会带上这个参数，可自定义
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               // 分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               contextMenuTrigger:"right",// pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",// 手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	edit(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该一键诊断告警信息记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#diagnAlarmTable').bootstrapTable('refresh');
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
               columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'faultDescription',
		        title: '故障描述',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		         }
		       
		    }
			,{
		        field: 'alarmSource',
		        title: '告警来源',
		        sortable: true
		       
		    }
			,{
		        field: 'alarmTime',
		        title: '告警时间',
		        sortable: true
		       
		    }
			,{
		        field: 'alarmLevel',
		        title: '告警级别',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('diagn_alarm_level'))}, value, row.alarmLevel);
		        }
		       
		    }
			,{
		        field: 'proposalRepair',
		        title: '建议修复',
		        sortable: true
		       
		    }
			,{
		        field: 'alarmInfo',
		        title: '详细信息',
		        sortable: true
		       
		    }
			,{
		        field: 'confirmUser',
		        title: '确认人',
		        sortable: true
		       
		    }
			,{
		        field: 'confirmWhether',
		        title: '是否确认',
		        sortable: true
		       
		    }
			,{
		        field: 'confirmTime',
		        title: '确认时间',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){// 如果是移动端

		 
		  $('#diagnAlarmTable').bootstrapTable("toggleView");
		}
	  
	  $('#diagnAlarmTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#diagnAlarmTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#diagnAlarmTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/import/template';
				  },
			    btn2: function(index, layero){
				        var inputForm =top.$("#importForm");
				        var top_iframe = top.getActiveTab().attr("name");// 获取当前active的tab的iframe
				        inputForm.attr("target",top_iframe);// 表单提交成功后，从服务器返回的url在当前tab中展示
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
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#diagnAlarmTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#diagnAlarmTable').bootstrapTable('refresh');
		});
		
		$('#alarmTime').datetimepicker({
			 format: "YYYY-MM-DD HH:mm:ss"
		});
		
	});
		
  function getIdSelections() {
        return $.map($("#diagnAlarmTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该一键诊断告警信息记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#diagnAlarmTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function updateconfirmWhether(){
	  	var id=getIdSelections();
	  	 if(id.length<=0){
	  		 jp.alert("请选择一条告警！");
				return;
			}
		jp.confirm('是否要确认这条告警？', function(){
			jp.loading();  	
			jp.get("${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/updateconfirmWhether?id=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#diagnAlarmTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
}
   function add(){
	  jp.openDialog('新增一键诊断告警信息', "${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/form",'800px', '500px', $('#diagnAlarmTable'));
  }
  function edit(id){// 没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="diagn_alarm:diagn_alarm:diagnAlarm:edit">
	  jp.openDialog('编辑一键诊断告警信息', "${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/form?id=" + id,'800px', '500px', $('#diagnAlarmTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="diagn_alarm:diagn_alarm:diagnAlarm:edit">
	  jp.openDialogView('查看一键诊断告警信息', "${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/form?id=" + id,'800px', '500px', $('#diagnAlarmTable'));
	  </shiro:lacksPermission>
  }
  
  
  	function exportAll(){
		 // window.location =
			// "${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/diagn_alarm/diagn_alarm/diagnAlarm/export");
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