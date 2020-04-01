<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<%@ taglib prefix="act" tagdir="/WEB-INF/tags/act"%>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="decorator" content="ani" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>用户管理</title>
</head>

<body class="bg-white">

	<link rel="stylesheet" type="text/css"
		href="${ctxStatic}/common/css/formSelects-v4.css" />
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>头像：</label></td>
					<td class="width-35"><form:hidden id="nameImage" path="photo"
							htmlEscape="false" maxlength="255" class="input-xlarge" /> <sys:ckfinder
							input="nameImage" type="images" uploadPath="/photo"
							selectMultiple="false" maxWidth="100" maxHeight="100" /></td>
					<td class="active"><label class="pull-right">是否管理员:</label></td>
					<td><form:select path="isAdmin" class="form-control">
							<form:options items="${fns:getDictList('yes_no')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
				</tr>

				<tr>
					<td class="active"><label class="pull-right"><font
							color="red">*</font>部门:</label></td>
					<td><form:input path="department" htmlEscape="false"
							maxlength="50" class="form-control required" /></td>
					<td class="active"><label class="pull-right"><font
							color="red">*</font>工号:</label></td>
					<td><form:input path="no" htmlEscape="false" maxlength="50"
							class="form-control required" /></td>
				</tr>

				<tr>
					<td class="active"><label class="pull-right"><font
							color="red">*</font>姓名:</label></td>
					<td><form:input path="name" htmlEscape="false" maxlength="50"
							class="form-control required" /></td>
					<td class="active"><label class="pull-right"><font
							color="red">*</font>登录名:</label></td>
					<td><input id="oldLoginName" name="oldLoginName" type="hidden"
						value="${user.loginName}"> <form:input path="loginName"
							htmlEscape="false" maxlength="50"
							class="form-control required userName" /></td>
				</tr>


				<tr>
					<td class="active"><label class="pull-right"><c:if
								test="${empty user.id}">
								<font color="red">*</font>
							</c:if>密码:</label></td>
					<td><input id="newPassword" name="newPassword" type="password"
						value="" maxlength="50" minlength="3"
						class="form-control ${empty user.id?'required':''}" /> <c:if
							test="${not empty user.id}">
							<span class="help-inline">若不修改密码，请留空。</span>
						</c:if></td>
					<td class="active"><label class="pull-right"><c:if
								test="${empty user.id}">
								<font color="red">*</font>
							</c:if>确认密码:</label></td>
					<td><input id="confirmNewPassword" name="confirmNewPassword"
						type="password"
						class="form-control ${empty user.id?'required':''}" value=""
						maxlength="50" minlength="3" equalTo="#newPassword" /></td>
				</tr>

				<tr>
					<td class="active"><label class="pull-right">邮箱:</label></td>
					<td><form:input path="email" htmlEscape="false"
							maxlength="100" class="form-control email" /></td>
					<td class="active"><label class="pull-right">电话:</label></td>
					<td><form:input path="phone" htmlEscape="false"
							maxlength="100" class="form-control" /></td>
				</tr>

				<tr>
					<td class="active"><label class="pull-right">手机:</label></td>
					<td><form:input path="mobile" htmlEscape="false"
							maxlength="100" class="form-control" /></td>
					<td class="active"><label class="pull-right">是否允许登录:</label></td>
					<td><form:select path="loginFlag" class="form-control">
							<form:options items="${fns:getDictList('yes_no')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
				</tr>

				<tr>
					<td class="active"><label class="pull-right">备注:</label></td>
					<td colspan="3"><form:textarea path="remarks"
							htmlEscape="false" rows="3" maxlength="200" class="form-control" /></td>


				</tr>

				<tr>
					<td class="active"><label class="pull-right"><font
							color="red">*</font>用户角色:</label></td>
					<td colspan="3">
						<div>
							<select id="roleIdList" name="roleIdList" xm-select="select1"
								xm-select-search="">

								<c:forEach items="${allRoles }" var="roleinfo">
									<c:choose>

										<c:when test="${roleinfo.istt=='1' }">
											<option value="${roleinfo.id }" selected="selected">${roleinfo.name }</option>
										</c:when>

										<c:otherwise>
											<option value="${roleinfo.id }">${roleinfo.name }</option>
										</c:otherwise>

									</c:choose>
									


								</c:forEach>

							</select>
						</div>
					</td>
				</tr>

				<c:if test="${not empty user.id}">
					<tr>
						<td class=""><label class="pull-right">创建时间:</label></td>
						<td><span class="lbl"><fmt:formatDate
									value="${user.createDate}" type="both" dateStyle="full" /></span></td>
						<td class=""><label class="pull-right">最后登陆:</label></td>
						<td><span class="lbl">IP:
								${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate
									value="${user.loginDate}" type="both" dateStyle="full" />
						</span></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form:form>
	<script type="text/javascript" charset="utf-8">

	var validateForm;
	var $table; // 父页面table表格id
	var $topIndex;//弹出窗口的 index
	//debugger
	function doSubmit(table, index) {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		/* $("#no").focus();
		validateForm = $("#inputForm").validate({
			rules : {
				loginName : {
					remote : "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')
				}
			},
			messages : {
				loginName : {
					remote : "用户登录名已存在"
				},
				confirmNewPassword : {
					equalTo : "输入与上面相同的密码"
				}
			},
			submitHandler : function(form) {
				jp.post("${ctx}/sys/user/save", $('#inputForm').serialize(), function(data) {
					if (data.success) {
						$table.bootstrapTable('refresh');
						jp.success(data.msg);

					} else {
						jp.error(data.msg);
					}

					jp.close($topIndex);//关闭dialog
				});

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
 */
		if (table!=null) {
			$table = table;
			$topIndex = index;
			
			//jp.loading();
			$("#inputForm").submit();
			$table.bootstrapTable('refresh');
			jp.success("提交成功");
			jp.close($topIndex);//关闭dialog
			return true;
		}

		return false;
	}
	$(document).ready(function() {
		$("#no").focus();
		validateForm = $("#inputForm").validate({
			rules : {
				loginName : {
					remote : "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')
				}
			},
			messages : {
				loginName : {
					remote : "用户登录名已存在"
				},
				confirmNewPassword : {
					equalTo : "输入与上面相同的密码"
				}
			},
			submitHandler : function(form) {
				jp.post("${ctx}/sys/user/save", $('#inputForm').serialize(), function(data) {
					if (data.success) {
						$table.bootstrapTable('refresh');
						jp.success(data.msg);

					} else {
						jp.error(data.msg);
					}

					jp.close($topIndex);//关闭dialog
				});

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
	<script src="${ctxStatic}/common/js/jquery.js"
		type="text/javascript" charset="utf-8"></script>
	<script
		src="${ctxStatic}/common/js/formSelects-v4.min.js"
		type="text/javascript" charset="utf-8"></script>

</body>

</html>