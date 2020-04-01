<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ANAAA业务更改窗口</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
			  $topIndex = index;
			  jp.loading();
			  $("#inputForm").submit();
			  return true;
		  }

		  return false;
		}

		$(document).ready(function() {
			
			var anaaaInfo = JSON.parse(sessionStorage.getItem("anaaaInfo"));
			$("#mdn").val(anaaaInfo.mdn);
			
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/anaaa/anaaa/modify",{"operation":$("input[name='operation']").val(),"userNoValue":anaaaInfo.imsi,"opeatorType":0,"phone":anaaaInfo.mdn},function(data){
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
					<td class="width-15 active"><label class="pull-right">手机号码：</label></td>
					<td class="width-35" colspan="2">
						<input id="mdn" class="form-control" disabled="disabled" />
					</td>
				</tr>
		 	</tbody>
		</table>
		</form>
		
		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="operation" value="1" checked="checked">
						异常鉴权次数清理</label>
					</td>
					<td class="width-15 active">
						<label class="2"><input type="radio" name="operation">
						停机</label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active">
						<label class="3"><input type="radio" name="operation">
						复机</label>
					</td>
					<td class="width-15 active">
						<label class="4"><input type="radio" name="operation">
						销卡</label>
					</td>
		  		</tr>
		 	</tbody>
		</table>
		</form>
</body>
</html>