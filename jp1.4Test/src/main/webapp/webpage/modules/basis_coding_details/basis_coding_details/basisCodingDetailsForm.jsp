<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>编码明细管理</title>
<meta name="decorator" content="ani" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate({
			submitHandler : function(form) {
				jp.loading();
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
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a class="panelButton"
								href="${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails"><i
								class="ti-angle-left"></i> 返回</a>
						</h3>
					</div>
					<div class="panel-body">
						<form:form id="inputForm" modelAttribute="basisCodingDetails"
							action="${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/save"
							method="post" class="form-horizontal">
							<form:hidden path="id" />
							<sys:message content="${message}" />
							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>编码：</label>
								<div class="col-sm-10">
									<form:input path="coding" htmlEscape="false"
										class="form-control required" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">中文描述：</label>
								<div class="col-sm-10">
									<form:input path="chineseDescription" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">英文描述：</label>
								<div class="col-sm-10">
									<form:input path="englishDescription" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">字符值：</label>
								<div class="col-sm-10">
									<form:input path="characterValue" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">浮点值：</label>
								<div class="col-sm-10">
									<form:input path="floatValue" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">整型值：</label>
								<div class="col-sm-10">
									<form:input path="intValue" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>是否默认值（Y：是；N：否）：</label>
								<div class="col-sm-10">
									<form:input path="isdefault" htmlEscape="false"
										class="form-control required" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">备注：</label>
								<div class="col-sm-10">
									<form:input path="notes" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<c:if
								test="${fns:hasPermission('basis_coding_details:basis_coding_details:basisCodingDetails:edit') || isAdd}">
								<div class="col-lg-3"></div>
								<div class="col-lg-6">
									<div class="form-group text-center">
										<div>
											<button class="btn btn-primary btn-block btn-lg btn-parsley"
												data-loading-text="正在提交...">提 交</button>
										</div>
									</div>
								</div>
							</c:if>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>