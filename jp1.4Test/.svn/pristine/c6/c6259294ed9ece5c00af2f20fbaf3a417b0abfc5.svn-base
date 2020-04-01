<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>长者特别情况管理</title>
	<meta name="decorator" content="ani"/>
	<!-- SUMMERNOTE -->
	<%@include file="/webpage/include/summernote.jsp" %>
	<script type="text/javascript">

		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					jp.loading();
					$("input[name='content']").val($('#content').summernote('code'));//取富文本的值
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
			
				//富文本初始化
			$('#content').summernote({
				height: 300,                
                lang: 'zh-CN'
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
				<a class="panelButton" href="${ctx}/specialdiscuss/edSpecialDiscussLog"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="edSpecialDiscussLog" action="${ctx}/specialdiscuss/edSpecialDiscussLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
				<div class="form-group">
					<label class="col-sm-2 control-label">长者：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edSpecialDiscussLog.elderid.id}" labelName="elderid.funame" labelValue="${edSpecialDiscussLog.elderid.funame}"
							 title="选择长者" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="姓名" searchKeys="funame" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>标题：</label>
					<div class="col-sm-10">
						<form:input path="title" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>正文：</label>
					<div class="col-sm-10">
                        <input type="hidden" name="content"/>
						<div id="content">
                          ${fns:unescapeHtml(edSpecialDiscussLog.content)}
                        </div>
					</div>
				</div>
		<c:if test="${fns:hasPermission('specialdiscuss:edSpecialDiscussLog:edit') || isAdd}">
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