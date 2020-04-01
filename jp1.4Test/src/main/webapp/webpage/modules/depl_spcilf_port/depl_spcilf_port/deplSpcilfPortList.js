<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	initTable();
	
	
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){// 如果是移动端

		 
		  $('#deplSpcilfPortTable').bootstrapTable("toggleView");
		}
	  
	  $('#deplSpcilfPortTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#deplSpcilfPortTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#deplSpcilfPortTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/import/template';
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
		  $('#deplSpcilfPortTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#deplSpcilfPortTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#deplSpcilfPortTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该机器部署端口记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#deplSpcilfPortTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
   function add(){
	  jp.openDialog('新增机器部署端口', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/form",'800px', '500px', $('#deplSpcilfPortTable'));
  }
  function edit(id){// 没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit">
	  jp.openDialog('编辑机器部署端口', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/form?id=" + id,'800px', '500px', $('#deplSpcilfPortTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit">
	  jp.openDialogView('查看机器部署端口', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/form?id=" + id,'800px', '500px', $('#deplSpcilfPortTable'));
	  </shiro:lacksPermission>
  }
  
  
  	function exportAll(){
		 // window.location =
			// "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/export");
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