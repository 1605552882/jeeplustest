<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>人工检测结果管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					jp.loading();
					form.submit();
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
			
	        $('#dcreatetime').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
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
				<a class="panelButton" href="${ctx}/checkresult/checkresult"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="checkresult" action="${ctx}/checkresult/checkresult/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
				<div class="form-group">
					<label class="col-sm-2 control-label">单据编号：</label>
					<div class="col-sm-10">
						<form:input path="sbillno" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">存在问题：</label>
					<div class="col-sm-10">
						<form:select path="hasproblem" cssClass="form-control ">
							<form:options items="${fns:getDictList('document_problem')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
						<%-- <form:input path="hasproblem" htmlEscape="false"    class="form-control "/> --%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">详细情况：</label>
					<div class="col-sm-10">
						<form:input path="details" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">责任班组：</label>
					<div class="col-sm-10">
						<form:input path="dutyGroup" htmlEscape="false"    class="form-control "/> 
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">责任人：</label>
					<div class="col-sm-10">
						<form:input path="dutyPeople" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">创建时间：</label>
					<div class="col-sm-10">
						<p class="input-group">
							<div class='input-group form_datetime' id='dcreatetime'>
			                    <input type='text'  name="dcreatetime" class="form-control"  value="<fmt:formatDate value="${checkresult.dcreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>						            
			            </p>
					</div>
				</div>
		<c:if test="${fns:hasPermission('checkresult:checkresult:edit') || isAdd}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
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