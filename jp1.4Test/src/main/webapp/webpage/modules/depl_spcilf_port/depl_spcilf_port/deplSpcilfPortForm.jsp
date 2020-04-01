<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>机器部署端口管理</title>
<meta name="decorator" content="ani" />
<script type="text/javascript">
	var validateForm;
	var $table; // 父页面table表格id
	var $topIndex;//弹出窗口的 index
	function doSubmit(table, index) {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		if (validateForm.form()) {
			$table = table;
			$topIndex = index;
			jp.loading();
			$("#inputForm").submit();
			return true;
		}

		return false;
	}

	$(document).ready(function() {
		validateForm = $("#inputForm").validate({
			submitHandler : function(form) {
				jp.post("${ctx}/depl_spcilf_port/depl_spcilf_port/deplSpcilfPort/save", $('#inputForm').serialize(), function(data) {
					if (data.success) {
						$table.bootstrapTable('refresh');
						jp.success(data.msg);
						jp.close($topIndex);//关闭dialog

					} else {
						jp.error(data.msg);
					}
				})
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});

	});
	var isadd = $
	{
		isadd
	};
	if (isadd) {
	} else {
		var html = '<td class="width-15 active"><label class="pull-right">选择端口：</label></td>' + '<td colspan="3"><select class="form-control "  style="width: 100%">' + '<option></option>'
				+ '</select></td>';
		$("#duankou").append(html);
	}
</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="deplSpcilfPort"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="targetid" />
		<sys:message content="${message}" />
		<table class="table table-bordered">
			<tbody>
				<tr id="duankou">


				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">程序名字：</label></td>
					<td class="width-35"><form:input path="name"
							htmlEscape="false" class="form-control " /></td>
					<td class="width-15 active"><label class="pull-right">执行力度（小时/天）：</label></td>
					<td class="width-35"><form:input path="takeTime"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">SVN存放目录：</label></td>
					<td class="width-35"><form:input path="svn" htmlEscape="false"
							class="form-control " /></td>
					<td class="width-15 active"><label class="pull-right">备份目录：</label></td>
					<td class="width-35"><form:input path="duplicate"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">程序故障处理：</label></td>
					<td class="width-35"><form:input path="proSolu"
							htmlEscape="false" class="form-control " /></td>
					<td class="width-15 active"><label class="pull-right">程序升级部署方法：</label></td>
					<td class="width-35"><form:input path="backupCate"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">常见问题与使用技巧：</label></td>
					<td class="width-35"><form:input path="usallProblem"
							htmlEscape="false" class="form-control " /></td>
					<td class="width-15 active"><label class="pull-right">端口：</label></td>
					<td class="width-35"><form:input path="port"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">部署目录：</label></td>
					<td class="width-35"><form:input path="way" htmlEscape="false"
							class="form-control " /></td>
					<td class="width-15 active"><label class="pull-right">实现功能：</label></td>
					<td class="width-35"><form:input path="func"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">程序执行方法：</label></td>
					<td class="width-35"><form:input path="howtodo"
							htmlEscape="false" class="form-control " /></td>
					<td class="width-15 active"></td>
					<td class="width-35"></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>