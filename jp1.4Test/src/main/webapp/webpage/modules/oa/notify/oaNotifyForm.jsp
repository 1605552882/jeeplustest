<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>通知管理</title>
<meta name="decorator" content="ani" />
<script type="text/javascript">
	var validateForm;
	function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		if (validateForm.form()) {
			$("#inputForm").submit();
			return true;
		}

		return false;
	}
	$(document).ready(function() {
		//$("#name").focus();
		validateForm = $("#inputForm").validate({
			submitHandler : function(form) {

				form.submit();
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
</script>
<script type="text/javascript">
	$(function() {
		$('#datetimepicker1').datetimepicker({
			format : 'YYYY-MM-DD',
			locale : moment.locale('zh-cn')
		});
		$('#datetimepicker2').datetimepicker({
			format : 'YYYY-MM-DD hh:mm',
			locale : moment.locale('zh-cn')
		});
	});
</script>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a class="panelButton"
								href="${ctx}/oa/oaNotify${isSelf?'/self':'' }"><i
								class="ti-angle-left"></i> 返回</a>
						</h3>
					</div>
					<div class="panel-body">
						<form:form id="inputForm" modelAttribute="oaNotify"
							action="${ctx}/oa/oaNotify/save" method="post"
							class="form-horizontal">
							<form:hidden path="id" />
							<sys:message content="${message}" />

							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>类型：</label>
								<div class="col-sm-10">
									<form:select path="type" class="form-control required">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('oa_notify_type')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>展示位置：</label>
								<div class="col-sm-10">
									<form:select path="type" class="form-control required">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('oaNotifyDate')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>展示有效时间：</label>
								<div class="col-sm-10">
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control"/> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>

								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>标题：</label>
								<div class="col-sm-10">
									<form:input path="title" htmlEscape="false" maxlength="200"
										class="form-control required" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>内容：</label>
								<div class="col-sm-10">
									<form:textarea path="content" htmlEscape="false" rows="6"
										maxlength="2000" class="form-control required" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">附件：</label>
								<div class="col-sm-10">
									<%-- <c:if test="${oaNotify.status ne '1'}">
										<form:hidden id="files" path="files" htmlEscape="false"
											maxlength="255" class="form-control" />
										<sys:ckfinder input="files" type="files"
											uploadPath="/oa/notify" selectMultiple="true" />
									</c:if>
									<c:if test="${oaNotify.status eq '1'}">
										<form:hidden id="files" path="files" htmlEscape="false"
											maxlength="255" class="form-control" />
										<sys:ckfinder input="files" type="files"
											uploadPath="/oa/notify" selectMultiple="true" readonly="true" />
									</c:if> --%>
									<input type="file" name="fileInfo"/>
								</div>
							</div>

							<c:if test="${oaNotify.status ne '1'}">
								<div class="form-group">
									<label class="col-sm-2 control-label"><font color="red">*</font>状态：</label>
									<div class="col-sm-10">
										<form:radiobuttons path="status"
											items="${fns:getDictList('oa_notify_status')}"
											itemLabel="label" itemValue="value" htmlEscape="false"
											class="i-checks required" />
									</div>
								</div>

							</c:if>



							<c:if test="${oaNotify.status ne '1'}">
								<shiro:hasPermission name="oa:oaNotify:edit">
									<div class="col-lg-3"></div>
									<div class="col-lg-6">
										<div class="form-group text-center">
											<label></label>

											<div>
												<button class="btn btn-primary btn-block btn-lg btn-parsley"
													data-loading-text="正在提交...">提 交</button>
											</div>
										</div>
									</div>
								</shiro:hasPermission>
							</c:if>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>