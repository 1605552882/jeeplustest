<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	function initTable(newUrl,info){
		 $('#basisCodingDetailsTable').bootstrapTable('destroy');
    	 $('#basisCodingDetailsTable').bootstrapTable({
    		 
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
                  url:newUrl,
                  // 默认值为 'limit',传给服务端的参数为：limit, offset, search,
					// sort, order Else
                  // queryParamsType:'',
                  // //查询参数,每次调用是会带上这个参数，可自定义
                  queryParams : function(params) {
                  	var searchParam = $("#searchForm").serializeJSON();
                  	searchParam.typeFk=info;
                  	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
                  	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
                  	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                      return searchParam;
                  },
                  // 分页方式：client客户端分页，server服务端分页（*）
                  sidePagination: "server",
                  contextMenuTrigger:"right",// pc端 按右键弹出菜单
                  contextMenuTriggerMobile:"press",// 手机端
													// 弹出菜单，click：单击，
													// press：长按。
                  contextMenu: '#context-menu',
                  onContextMenuItem: function(row, $el){
                      if($el.data("item") == "edit"){
                      	window.location = "${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/form?id=" + row.id;
                      } else if($el.data("item") == "delete"){
                           jp.confirm('确认要删除该编码明细记录吗？', function(){
                          	jp.loading();
                          	jp.get("${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/delete?id="+row.id, function(data){
                      	  		if(data.success){
                      	  			$('#basisCodingDetailsTable').bootstrapTable('refresh');
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
   		        field: 'coding',
   		        title: '编码',
   		        sortable: true
   		        ,formatter:function(value, row , index){
   		        	return "<a href='${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/form?id="+row.id+"'>"+value+"</a>";
   		         }
   		       
   		    }
   			,{
   		        field: 'chineseDescription',
   		        title: '中文描述',
   		        sortable: true
   		       
   		    }
   			,{
   		        field: 'englishDescription',
   		        title: '英文描述',
   		        sortable: true
   		       
   		    }
   			,{
   		        field: 'characterValue',
   		        title: '字符值',
   		        sortable: true
   		       
   		    }
   			,{
   		        field: 'floatValue',
   		        title: '浮点值',
   		        sortable: true
   		       
   		    }
   			,{
   		        field: 'intValue',
   		        title: '整型值',
   		        sortable: true
   		       
   		    }
   			,{
   		        field: 'isdefault',
   		        title: '是否默认值（Y：是；N：否）',
   		        sortable: true
   		       
   		    }
   			,{
   		        field: 'notes',
   		        title: '备注',
   		        sortable: true
   		       
   		    }
   		     ]
   		
   		});
	}
	initTable();
	$('#basisCodingTable').bootstrapTable({
		 
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
               url: "${ctx}/basis_coding/basis_coding/basisCoding/data",
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
                   	window.location = "${ctx}/basis_coding/basis_coding/basisCoding/form?id=" + row.id;
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该编码记录吗？', function(){              
                       	jp.loading();
                       	jp.get("${ctx}/basis_coding/basis_coding/basisCoding/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#basisCodingTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
            	 var info=row.typeCoding;
            	 initTable("${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/data",info);
           	  
           		
               },
               columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'typeCoding',
		        title: '类型编码',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return "<a href='${ctx}/basis_coding/basis_coding/basisCoding/form?id="+row.id+"'>"+value+"</a>";
		         }
		       
		    }
			,{
		        field: 'name',
		        title: '名称',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){// 如果是移动端

		 
		  $('#basisCodingTable').bootstrapTable("toggleView");
		}
	  
	  $('#basisCodingTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#basisCodingTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#basisCodingTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/basis_coding/basis_coding/basisCoding/import/template';
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
		  $('#basisCodingTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#basisCodingTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#basisCodingTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该编码记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/basis_coding/basis_coding/basisCoding/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#basisCodingTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function edit(){
	  window.location = "${ctx}/basis_coding/basis_coding/basisCoding/form?id=" + getIdSelections();
  }
  
	function exportAll(){
		 // window.location =
			// "${ctx}/basis_coding/basis_coding/basisCoding/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/basis_coding/basis_coding/basisCoding/export");
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