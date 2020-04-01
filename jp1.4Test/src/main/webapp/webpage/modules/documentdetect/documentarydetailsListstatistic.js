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
	})
	$('#endReportTime').datetimepicker({
		 format: "YYYY-MM-DD 23:59:59",
		 defaultDate:yesterdayEnd
	})
	
	$('#documentarydetailsTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               //showToggle: true,
               //显示 内容列下拉框
    	       //showColumns: true,
    	       //显示到处按钮
    	       //showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
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
               url: "${ctx}/documentdetect/documentarydetails/statisticsData",
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
               responseHandler: function(res) {
            	   var tempRows = res.rows;
            	   if(tempRows.length > 0){
                	    //故障内容
                	   $("input[id='srepfaultdetails']").each(function(){
					            //$(this).attr("name")  每个input框name的值
					            var val =  $(this).val().trim();
					            if(val!=""){
					            	for(var i=0;i<tempRows.length; i++){
		                		   		tempRows[i].srepfaultdetail = tempRows[i].srepfaultdetail.replace(new RegExp( val, 'ig'), "<b style='color:red;'>"+  val +"</b>");
		                		   }
					            }
					  });
					  //结单信息
                	   $("input[id='sprocesssummarys']").each(function(){
					         var val =  $(this).val().trim();
					         if(val!=""){
						         for(var i=0;i<tempRows.length; i++){
						         	tempRows[i].sprocesssummary = tempRows[i].sprocesssummary.replace(new RegExp(val, 'g'), "<b style='color:red;'>"+ val +"</b>");
						         }
					         }
                	   });
                	   //热词
                	   var hotword = $("#hotword").val().trim();
                	   if (hotword != "") {
                	   		 for(var i=0;i<tempRows.length; i++){
                			   var result = tempRows[i].srepfaultdetail.match(new RegExp(hotword,'gi'));
                			   if(result!=null){
                				   $.each(result, function (index, item) {
		                       	        if(tempRows[i].srepfaultdetail.search("<b style='color:red;'>"+ item +"</b>") == -1){
		                       	        	tempRows[i].srepfaultdetail = tempRows[i].srepfaultdetail.replace(new RegExp(item, 'g'), "<b style='color:red;'>"+ item +"</b>");
		                       	        }
	                       	    	});
                			   }
                		   }
                	   }
            	   }
            	   return res;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               //contextMenuTrigger:"right",//pc端 按右键弹出菜单
               //contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               //contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	window.location = "${ctx}/documentdetect/documentarydetails/form?id=" + row.id;
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该工单详情页面记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/documentdetect/documentarydetails/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#documentarydetailsTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
               /*onClickRow: function(row, $el){
               },*/
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: 'sbillno',
		        title: '故障单号',
		        //width : 110 ,
		        width : '15%',
		        formatter:function(value, row , index){
		        	return "<a  onclick=\"newtable('"+row.id+"')\" >"+value+"</a>";  //href='javascript:newtable("+row.id+");'
		         }
		       
		    }
			/*,{
		        field: 'sfaulttitle',
		        title: '故障标题',
		        width : 150
		       
		    }*/
			,{
		        field: 'sfaultcategory',
		        title: '故障种类',
//		        width :110
		        width : '15%'
		       
		    }
			,{
		        field: 'srepfaultdetail',
		        title: '故障内容',
//		        width : 160
		        width : '31%' 
		       
		    },{
		        field: 'sprocesssummary',
		        title: '结单信息',
//		        width : 160
		        width : '31%' 
		       
		    },{
		        field: 'sStatus',
		        title: '单据状态',
//		        width : 160
		        width : '8%' 
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#documentarydetailsTable').bootstrapTable("toggleView");
		}
	  
	  $('#documentarydetailsTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
              'check-all.bs.table uncheck-all.bs.table', function () {
          $('#remove').prop('disabled', ! $('#documentarydetailsTable').bootstrapTable('getSelections').length);
          $('#edit').prop('disabled', $('#documentarydetailsTable').bootstrapTable('getSelections').length!=1);
      });
		  
	  $("#search").click("click", function() {// 绑定查询按扭
	  	var srepfaultdetail = $('#srepfaultdetail').val();
	  	var hotword = $('#hotword').val();
		if (sprocesssummary == "" && hotword == "") {
			jp.alert('请选择热词或故障内容的其中一个');
			return;
		}	  
		  $('#documentarydetailsTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#documentarydetailsTable').bootstrapTable('refresh');
		});
		
	});

	function newtable(id) {
		jp.openTab("${ctx}/documentdetect/documentarydetails/samplform?id="+id,"原始工单-工单明细", false);
	}
		
  function getIdSelections() {
        return $.map($("#documentarydetailsTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
	function addRow(list, idx, tpl, row){
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
	}
			
	function exportAll(){
		 // window.location = "${ctx}/documentdetect/documentarydetails/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/documentdetect/documentarydetails/export");
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
</script>
<script type="text/template" id="documentarydetailsChildrenTpl">
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">单据反馈信息</a></li>
		</ul>
		<div class="tab-content" >
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active" >
						<table class="ani table" style="table-layout : fixed ;" >
						<thead>
							<tr>
								<th width=180>单据编号</th>
								<th width=80>反馈序号</th>
								<th width=80>反馈部门</th>
								<th width=120>反馈人员</th>
								<th width=120>反馈人电话</th>
								<th width=120>反馈时间</th>
								<th width=120>反馈来源</th>
								<th width=500>反馈内容</th>
								<th >更新时间</th>
							</tr>
						</thead>
						<tbody id="documentarydetailsChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>
	</script>
	<script type="text/template" id="documentarydetailsChild1Tpl">
				<tr>
					<td>
						{{row.documentarydetails.sbillno}}
					</td>
					<td>
						{{row.iserialno}}
					</td>
					<td>
						{{row.sdeptname}}
					</td>
					<td>
						{{row.sstaffname}}
					</td>
					<td>
						{{row.sstaffphone}}
					</td>
					<td>
						{{row.dcreatetime}}
					</td>
					<td>
						{{row.ssource}}
					</td>
					<td>
						{{row.sprocdesc}}
					</td>
					<td>
						{{row.updateDate}}
					</td>
				</tr>
	</script>
	