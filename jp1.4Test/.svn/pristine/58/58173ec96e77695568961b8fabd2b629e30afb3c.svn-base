<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#edElderInfTable').bootstrapTable({
		 
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
               url: "${ctx}/elder/edElderInf/data",
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
                        jp.confirm('确认要删除该长者基本资料记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/elder/edElderInf/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#edElderInfTable').bootstrapTable('refresh');
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
		        field: 'funame',
		        title: '姓名',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		         }
		       
		    }
			,{
		        field: 'sex',
		        title: '性别',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('male_or_femal'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'nianling',
		        title: '年龄',
		        sortable: true
		       
		    }
			,{
		        field: 'ruzhuriqi',
		        title: '入住日期',
		        sortable: true
		       
		    }
			,{
		        field: 'yuzhangzheguanxi',
		        title: '与长者关系',
		        sortable: true
		       
		    }
			,{
		        field: 'zhangzhehuojiashu',
		        title: '长者/家属',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#edElderInfTable').bootstrapTable("toggleView");
		}
	  
	  $('#edElderInfTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#edElderInfTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#edElderInfTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/elder/edElderInf/import/template';
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
		  $('#edElderInfTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#edElderInfTable').bootstrapTable('refresh');
		});
		
				$('#chushengriqi').datetimepicker({
					 format: "YYYY-MM-DD HH:mm:ss"
				});
				$('#ruzhuriqi').datetimepicker({
					 format: "YYYY-MM-DD HH:mm:ss"
				});
		
	});
		
  function getIdSelections() {
        return $.map($("#edElderInfTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该长者基本资料记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/elder/edElderInf/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#edElderInfTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  
  function add(){
	  jp.openDialog('新增长者基本资料', "${ctx}/elder/edElderInf/form",'800px', '500px', $('#edElderInfTable'));
  }
  
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="elder:edElderInf:edit">
	  jp.openDialog('编辑长者基本资料', "${ctx}/elder/edElderInf/form?id=" + id,'800px', '500px', $('#edElderInfTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="elder:edElderInf:edit">
	  jp.openDialogView('查看长者基本资料', "${ctx}/elder/edElderInf/form?id=" + id,'800px', '500px', $('#edElderInfTable'));
	  </shiro:lacksPermission>
  }
  
  
	function exportAll(){
		 // window.location = "${ctx}/elder/edElderInf/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/elder/edElderInf/export");
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
	  var htmltpl =  $("#edElderInfChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/elder/edElderInf/detail?id="+row.id, function(edElderInf){
    	var edElderInfChild1RowIdx = 0, edElderInfChild1Tpl = $("#edElderInfChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  edElderInf.edFamilyInfList;
		for (var i=0; i<data1.length; i++){
			addRow('#edElderInfChild-'+row.id+'-1-List', edElderInfChild1RowIdx, edElderInfChild1Tpl, data1[i]);
			edElderInfChild1RowIdx = edElderInfChild1RowIdx + 1;
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
<script type="text/template" id="edElderInfChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">家庭状况</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
								<th>姓名</th>
								<th>年龄</th>
								<th>关系</th>
								<th>性别</th>
							</tr>
						</thead>
						<tbody id="edElderInfChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="edElderInfChild1Tpl">//<!--
				<tr>
					<td>
						{{row.fullname}}
					</td>
					<td>
						{{row.nianling}}
					</td>
					<td>
						{{row.guanxi}}
					</td>
					<td>
						{{dict.sex}}
					</td>
				</tr>//-->
	</script>
