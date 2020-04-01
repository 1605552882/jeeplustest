<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
			jp.post("${ctx}/anaaa/meid/data",{},function(data){
				//验证MEID
				var isExist = false;
				for(var i=0; i<data.rows.length; i++){
					if($("#meid").val()==data.rows[i].coding){
						isExist = true;
					}
				}
				if(isExist){
				  if(validateForm.form()){
					  $table = table;
					  $topIndex = index;
					  jp.loading();
					  $("#inputForm").submit();
					  return true;
				  }
				}else{
					jp.error("MEID填写错误，请重新填写");
					return false;
				}
			});
		}

		$(document).ready(function() {
			
			var anaaaInfo = JSON.parse(sessionStorage.getItem("anaaaInfo"));
			$("#imsi").val(anaaaInfo.imsi);
			$("#meid").val(anaaaInfo.meid);
			
			validateForm = $("#inputForm").validate({
				
				submitHandler: function(form){
					jp.post("${ctx}/anaaa/anaaa/bindPhoneCard",{"imsi":$("#imsi").val(),"meid":$("#meid").val(),"userNo":anaaaInfo.mdn},function(data){
						if(data.success){
	                    	$table.bootstrapTable('refresh');
	                    	jp.success(data.msg);
	                    	jp.close($topIndex);//关闭dialog

	                    }else{
            	  			jp.error(data.msg);
	                    }
					})
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
			
		});
	</script>
</head>
<body class="bg-white">
		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">IMSI号：</label></td>
					<td class="width-35">
						<input id="imsi" class="form-control" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">MEID号：</label></td>
					<td class="width-35">
						<input id="meid" class="form-control" />
					</td>
				</tr>
		 	</tbody>
		</table>
	</form>
</body>
</html>