<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	function initTable(newUrl,info){
		 $('#deplSpcilfPortTable').bootstrapTable('destroy');
		 $('#deplSpcilfPortTable').bootstrapTable({
			 
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
	             striped: false,
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
	             url: newUrl,
	             // 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
					// Else
	             // queryParamsType:'',
	             // //查询参数,每次调用是会带上这个参数，可自定义
	             queryParams : function(params) {
	             	var searchParam = $("#searchForm").serializeJSON();
	             	searchParam.targetid=info;
	             	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
	             	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
	             	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
	                 return searchParam;
	             },
	             // 分页方式：client客户端分页，server服务端分页（*）
	             sidePagination: "server",
	             contextMenuTrigger:"right",// pc端 按右键弹出菜单
	             contextMenuTriggerMobile:"press",// 手机端 弹出菜单，click：单击，
													// press：长按。
	             contextMenu: '#context-menu',
	             onContextMenuItem: function(row, $el){
	                 if($el.data("item") == "edit"){
	                 	edit(row.id);
	                 } else if($el.data("item") == "delete"){
	                      jp.confirm('确认要删除该机器部署端口记录吗？', function(){
	                     	jp.loading();
	                     	jp.get("${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/delete?id="+row.id, function(data){
	                 	  		if(data.success){
	                 	  			$('#deplSpcilfPortTable').bootstrapTable('refresh');
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
			        field: 'name',
			        title: '程序名字',
			        sortable: true
			        ,formatter:function(value, row , index){
			        	return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
			         }
			       
			    }
				,{
			        field: 'takeTime',
			        title: '执行力度（小时/天）',
			        sortable: true
			       
			    }
				,{
			        field: 'svn',
			        title: 'SVN存放目录',
			        sortable: true
			       
			    }
				,{
			        field: 'duplicate',
			        title: '备份目录',
			        sortable: true
			       
			    }
				,{
			        field: 'proSolu',
			        title: '程序故障处理',
			        sortable: true
			       
			    }
				,{
			        field: 'backupCate',
			        title: '程序升级部署方法',
			        sortable: true
			       
			    }
				,{
			        field: 'usallProblem',
			        title: '常见问题与使用技巧',
			        sortable: true
			       
			    }
				,{
			        field: 'port',
			        title: '端口',
			        sortable: true
			       
			    }
				,{
			        field: 'way',
			        title: '部署目录',
			        sortable: true
			       
			    }
				,{
			        field: 'func',
			        title: '实现功能',
			        sortable: true
			       
			    }
			     ]
			
			});
	
}
	initTable();
	$('#deplSpcilfIpTable').bootstrapTable({
		 
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
             url: "${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/data",
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
                      jp.confirm('确认要删除该机器部署信息记录吗？', function(){
                     	jp.loading();
                     	jp.get("${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/delete?id="+row.id, function(data){
                 	  		if(data.success){
                 	  			$('#deplSpcilfIpTable').bootstrapTable('refresh');
                 	  			jp.success(data.msg);
                 	  		}else{
                 	  			jp.error(data.msg);
                 	  		}
                 	  	})
                 	   
                 	});
                    
                 } 
             },
            
             onClickRow: function(row, $el){
            	 var info=row.id;
            	 initTable("${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/data",info);
             },
             columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'name',
		        title: '机器名',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		         }
		       
		    }
			,{
		        field: 'sys',
		        title: '配置信息',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList(''))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'ip',
		        title: '地址',
		        sortable: true
		       
		    }
			,{
		        field: 'address',
		        title: '物理位置',
		        sortable: true
		       
		    }
			,{
		        field: 'useful',
		        title: '用途',
		        sortable: true
		       
		    }
		     ]
		
		});
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){// 如果是移动端

		 
		  $('#deplSpcilfIpTable').bootstrapTable("toggleView");
		}
	  
	  $('#deplSpcilfIpTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#deplSpcilfIpTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#deplSpcilfIpTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/import/template';
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
		  $('#deplSpcilfIpTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#deplSpcilfIpTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#deplSpcilfIpTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该机器部署信息记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#deplSpcilfIpTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
   function add(){
	  jp.openDialog('新增机器部署信息', "${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/form",'800px', '500px', $('#deplSpcilfIpTable'));
  }
  function edit(id){// 没有权限时，不显示确定按钮
  	  if(id== undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:edit">
	  jp.openDialog('编辑机器部署信息', "${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/form?id=" + id,'800px', '500px', $('#deplSpcilfIpTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:edit">
	  jp.openDialogView('查看机器部署信息', "${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/form?id=" + id,'800px', '500px', $('#deplSpcilfIpTable'));
	  </shiro:lacksPermission>
  }
  
  function getIdSelections1() {
      return $.map($("#deplSpcilfPortTable").bootstrapTable('getSelections'), function (row) {
          return row.id
      });
  }
function deleteAll1(){
	var id=getIdSelections1();
	 if(id.length==0){
		 jp.alert("请选择一个端口配置");
		 return;
	 }
		jp.confirm('确认要删除该机器部署端口记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/deleteAll?ids=" + getIdSelections1(), function(data){
       	  		if(data.success){
       	  			$('#deplSpcilfPortTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
}
 function add1(){
	 taid = getIdSelections();
	 
	 if(taid.length==0){
		 jp.alert("请选择一个机器配置");
		}else{
	  jp.openDialog('新增机器部署端口', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/form?targetid="+taid,'800px', '500px', $('#deplSpcilfPortTable'));
		}
}
function edit1(id){// 没有权限时，不显示确定按钮
	var taid = getIdSelections();
	id=getIdSelections1();
	 if(taid.length==0){
		 jp.alert("请选择一个机器配置");
		 return;
	 }
	 if(id.length==0){
		 jp.alert("请选择一个端口配置");
	 }else{
		 id = getIdSelections1();
		   <shiro:hasPermission name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit">
		  jp.openDialog('编辑机器部署端口', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/form1?targetid=" + taid+"&id="+id,'800px', '500px', $('#deplSpcilfPortTable'));
		   </shiro:hasPermission>
		  <shiro:lacksPermission name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit">
		  jp.openDialogView('查看机器部署端口', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/form1?targetid=" + taid+"&id="+id,'800px', '500px', $('#deplSpcilfPortTable'));
		  </shiro:lacksPermission>
	 }
	 
}
  	function exportAll(){
		 // window.location =
			// "${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/depl_spcilf_ip/depl_spcilf_ip/deplSpcilfIp/export");
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