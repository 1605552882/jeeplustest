<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>hlr_config管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="hlrConfigList.js"%>
<style type="text/css">
.form-control {
	height: 25px;
	width: auto;
	padding:0px;
}

.pulltype {
	text-align: right;
	width: 80px;
}

.divinput {
	margin: 3px 3px;
	width: 30%;
}

.panel-heading {
	padding: 8px;
	height: 31px;
	color: #fff;
	background-color: #3ca2e0;
	border-color: #3ca2e0;
}

.required {
	width: 100%;
}
.table-bordered .form-control{
	height: 25px;
}
</style>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<!-- <div class="panel-heading">
				<h3 class="panel-title">HLR配置</h3>
			</div> -->
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 搜索 -->

				<div id="collapseTwo" class="row">
					<div class="accordion-inner">
						<form:form id="searchForm" modelAttribute="hlrConfig"
							class="form form-horizontal well clearfix"
							style="border:1px solid #AAAAFF;margin-left:5px;margin-right:5px;margin-top:5px;padding-top:5px;padding-bottom:5px;background-color:white;">
							<div class="col-xs-4 divinput">

								<label class="label-item single-overflow pull-left pulltype"
									title="IP地址:">IP地址:</label>
								<form:input path="ip" id="ips" htmlEscape="false" maxlength="15"
									class=" form-control" />

							</div>
							<div class="col-xs-4 divinput">
								<label class="label-item single-overflow pull-left pulltype"
									title="机型:">机型:</label>
								<form:input path="spctype" htmlEscape="false" maxlength="16"
									class=" form-control" id="spctypes" />
							</div>
							<div class="col-xs-4 divinput">
								<label class="label-item single-overflow pull-left pulltype"
									title="地区标识:">地区标识:</label>
								<form:input path="locname" htmlEscape="false" maxlength="8"
									class=" form-control" id="locnames" />
							</div>
							<div class="col-xs-4 divinput">
								<label class="label-item single-overflow pull-left pulltype"
									title="hlr名称:">hlr名称:</label>
								<form:input path="hlrname" htmlEscape="false" maxlength="32"
									class=" form-control" id="hlrnames" />
							</div>
							<div class="col-xs-4 divinput">
								<label class="label-item single-overflow pull-left pulltype"
									title="手机号码头:">手机号码头:</label>
								<form:input path="numHead" htmlEscape="false"
									class=" form-control" id="numHeads" />
							</div>
							<div class="col-xs-4 divinput">
								<label class="label-item single-overflow pull-left pulltype"
									title="IMSI号码头:">IMSI号码头:</label>
								<form:input path="imsiHead" htmlEscape="false"
									class=" form-control" id="imsiHeads" />
							</div>
							<div class="col-xs-12">
								<div style="margin-top: 5px; margin-left: 6.6%;">
									<a id="search" class="btn btn-primary btn-sm"><i
										class="fa fa-search"></i> 查询</a> <a id="reset"
										class="btn btn-warning btn-sm"><i class="fa fa-refresh"></i>
										重置</a>
								</div>
							</div>
						</form:form>
					</div>
				</div>

				<div class="row" style="margin-left: 1px;">
					<div class="col-md-4"
						style="border: 1px solid #AAAAFF; height: 678px; padding-left:0px; padding-right:0px">
						<div class="panel-heading">
							<h5 class="panel-title">菜单信息</h5>
						</div>
						<div id="toolbar">
							<!-- 工具栏 -->
							<shiro:hasPermission name="hlr_config:hlr_config:hlrConfig:add">
								<a id="add" class="btn btn-primary btn-sm" onclick="add()"><i
									class="glyphicon glyphicon-plus"></i> 新建</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="hlr_config:hlr_config:hlrConfig:edit">
								<button id="edit" class="btn btn-success btn-sm" disabled
									onclick="edit()">
									<i class="glyphicon glyphicon-edit"></i> 修改
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="hlr_config:hlr_config:hlrConfig:del">
								<button id="remove" class="btn btn-danger btn-sm" disabled
									onclick="deleteAll()">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>
							</shiro:hasPermission>


						</div>
						<!-- 表格 -->
						<div style="padding:5px;font-size: 12px;">
						<table id="hlrConfigTable" data-toolbar="#toolbar"></table>
						</div>
						<!-- onclick="TabClick();" -->
					</div>
					<div class="col-md-8"
						style="border: 1px solid #AAAAFF; margin-left: 5px; width: 66%; height: 678px; padding-left:0px; padding-right:0px">
						<div class="panel-heading">
							<h5 class="panel-title">机型信息</h5>
						</div>
						<form:form id="inputForm" onsubmit="return false"
							modelAttribute="hlrConfig" class="form-horizontal">
							<form:hidden path="id" id="id" />

							<sys:message content="${message}" />
							<table class="table table-bordered" style="font-size: 12px;height: 600px;">
								<tbody>
									<tr>

										<td class="active" style="width:12%"><label class="pull-right"><font
												color="red">*</font>IP地址:</label></td>
										<td class=""><form:input path="ip"
												htmlEscape="false" class="form-control required" id="ip" /></td>
										<td class="active" style="width:12%"><label class="pull-right"><font
												color="red">*</font>端口:</label></td>
										<td class=""><form:input path="port"
												htmlEscape="false" class="form-control required" id="port" /></td>
									</tr>
									<tr>
										<td class="active"><label class="pull-right">机型:</label></td>
										<td class=""><form:input path="spctype"
												htmlEscape="false" class="form-control required"
												id="spctype" /></td>
										<td class="active"><label class="pull-right">地区标识:</label></td>
										<td class=""><form:input path="locname"
												htmlEscape="false" class="form-control required"
												id="locname" /></td>
									</tr>
									<tr>
										<td class="active"><label class="pull-right">hlr名称:</label></td>
										<td class=""><form:input path="hlrname"
												htmlEscape="false" class="form-control required"
												id="hlrname" /></td>
										<td class="active"><label class="pull-right">用户名:</label></td>
										<td class=""><form:input path="userid"
												htmlEscape="false" class="form-control required" id="userid" /></td>
									</tr>
									<tr>
										<td class="active"><label class="pull-right">密码:</label></td>
										<td class=""><form:input path="cipher"
												htmlEscape="false" class="form-control required" id="cipher" readonly="readonly"/></td>
										<td class="active"><label class="pull-right">在用标识:</label></td>
										<td class=""><select name="flag"
											class="selectpicker  form-control required" id="flag"
											style="font-size: 12px;" readonly="readonly">
												<option value="0">未用</option>
												<option value="1">在用</option>
										</select></td>
									</tr>
									<tr>
										<td class="active"><label class="pull-right">IMSI号码头:</label></td>
										<td class=""><form:textarea path="imsiHead"
												id="imsiHead" htmlEscape="false" rows="3" maxlength="200"
												class="form-control required" style="height: 325px;" readonly="readonly"/></td>
										<td class="active"><label class="pull-right">手机号码头:</label></td>

										<td class="" style=""><form:textarea
												path="imsiHead" id="numHead" htmlEscape="false" rows="3"
												maxlength="200" class="form-control required" style="height: 325px;" readonly="readonly"/></td>
									</tr>

								</tbody>
							</table>


						</form:form>
					</div>
					
				</div>





				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission name="hlr_config:hlr_config:hlrConfig:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="hlr_config:hlr_config:hlrConfig:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>