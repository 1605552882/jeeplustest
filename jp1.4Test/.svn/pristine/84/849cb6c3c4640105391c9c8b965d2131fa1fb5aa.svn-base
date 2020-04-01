<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#sdTreatmentReqLogTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //显示详情按钮
    	       detailView: true,
    	       	//显示详细内容函数
	           detailFormatter: "detailFormatter",
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
               url: "${ctx}/treatmentreq/sdTreatmentReqLog/data",
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
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	edit(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该求医记录记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/treatmentreq/sdTreatmentReqLog/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#sdTreatmentReqLogTable').bootstrapTable('refresh');
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
		        field: 'sdTreatmentplaceInf.placename',
		        title: '地点',
		        sortable: true
		        ,formatter:function(value, row , index){
		            if(value == null){
		            	return "<a href='javascript:edit(\""+row.id+"\")'>-</a>";
		            }else{
		                return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		            }
		        }
		       
		    }
			,{
		        field: 'sdPatientInf.fullname',
		        title: '预约人',
		        sortable: true
		       
		    }
			,{
		        field: 'reqstate',
		        title: '记录状态',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('treatmentreq_state'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'reqtitle',
		        title: '标题',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#sdTreatmentReqLogTable').bootstrapTable("toggleView");
		}
	  
	  $('#sdTreatmentReqLogTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#sdTreatmentReqLogTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#sdTreatmentReqLogTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/treatmentreq/sdTreatmentReqLog/import/template';
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
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#sdTreatmentReqLogTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#sdTreatmentReqLogTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#sdTreatmentReqLogTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该求医记录记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/treatmentreq/sdTreatmentReqLog/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#sdTreatmentReqLogTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  
  function add(){
	  jp.openDialog('新增求医记录', "${ctx}/treatmentreq/sdTreatmentReqLog/form",'800px', '500px', $('#sdTreatmentReqLogTable'));
  }
  
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:edit">
	  jp.openDialog('编辑求医记录', "${ctx}/treatmentreq/sdTreatmentReqLog/form?id=" + id,'800px', '500px', $('#sdTreatmentReqLogTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="treatmentreq:sdTreatmentReqLog:edit">
	  jp.openDialogView('查看求医记录', "${ctx}/treatmentreq/sdTreatmentReqLog/form?id=" + id,'800px', '500px', $('#sdTreatmentReqLogTable'));
	  </shiro:lacksPermission>
  }
  
  
	function exportAll(){
		 // window.location = "${ctx}/treatmentreq/sdTreatmentReqLog/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/treatmentreq/sdTreatmentReqLog/export");
		 $('body').append(form);
		 /**
		  * @FIXME 在模板中全部添加下面代码
		  */		 
		 		 $("#searchForm input").each(function(index, element) { 
		 			 	var input1 = $('<input>'); 
		 				input1.attr('type',element.type); 
		 				input1.attr('name',element.name); 
		 				input1.attr('value',element.value); 
		 				form.append(input1);
		 				    }); 
		 
		 form.submit();
	}  
  
		   
  function detailFormatter(index, row) {
	  var htmltpl =  $("#sdTreatmentReqLogChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/treatmentreq/sdTreatmentReqLog/detail?id="+row.id, function(sdTreatmentReqLog){
    	var sdTreatmentReqLogChild1RowIdx = 0, sdTreatmentReqLogChild1Tpl = $("#sdTreatmentReqLogChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  sdTreatmentReqLog.sdTreatmentInviteLogList;
		for (var i=0; i<data1.length; i++){
			addRow('#sdTreatmentReqLogChild-'+row.id+'-1-List', sdTreatmentReqLogChild1RowIdx, sdTreatmentReqLogChild1Tpl, data1[i]);
			sdTreatmentReqLogChild1RowIdx = sdTreatmentReqLogChild1RowIdx + 1;
		}
				
      	  			
      })
     
        return html;
    }
  
	function addRow(list, idx, tpl, row){
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
	}
			
</script>
<script type="text/template" id="sdTreatmentReqLogChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">预约评估邀请</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
								<th>预约人</th>
								<th>评估师</th>
								<th>邀请时间</th>
							</tr>
						</thead>
						<tbody id="sdTreatmentReqLogChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="sdTreatmentReqLogChild1Tpl">//<!--
				<tr>
					<td>
						{{row.patientid.fullname}}
					</td>
					<td>
						{{row.doctorid.fullname}}
					</td>
					<td>
						{{row.invitetime}}
					</td>
				</tr>//-->
	</script>
