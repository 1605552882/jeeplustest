<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>一键诊断日志主表管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="diagnServLogList.js"%>
</head>
<style type="text/css">
.form-control {
	height: 25px;
	width: auto;
	padding: 0px;
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

.table-bordered .form-control {
	height: 25px;
}
</style>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 搜索 -->
				<div class="accordion-group">
					<div id="collapseTwo" class="accordion-body">
						<div class="accordion-inner">
							<form:form id="searchForm" modelAttribute="diagnServLog"
								class="form form-horizontal well clearfix"
								style="border:1px solid #AAAAFF;margin-left:5px;margin-right:5px;margin-top:5px;padding-top:5px;padding-bottom:5px;background-color:white;">
								<div class="col-xs-4 divinput">
									<label class="label-item single-overflow pull-left"
										title="检查平台：">检查平台：</label>
									<form:select path="checkPlat" class="form-control m-b">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('')}" itemLabel="label"
											itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
								<div class="col-xs-4 divinput">
									<label class="label-item single-overflow pull-left"
										title="业务名称：">业务名称：</label>
									<form:select path="servNameId" class="form-control m-b">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('')}" itemLabel="label"
											itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
								<div class="col-xs-4 divinput">
									<label class="label-item single-overflow pull-left" title="地市：">地市：</label>
									<form:select path="city" class="form-control m-b">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('')}" itemLabel="label"
											itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
								<div class="col-xs-4 divinput">
									<div style="margin-top: 26px">
										<a id="search"
											class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i
											class="fa fa-search"></i> 查询</a> <a id="reset"
											class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i
											class="fa fa-refresh"></i> 重置</a>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>

				<!-- 工具栏 -->
				<div id="toolbar">
					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:add">
						<a id="add" class="btn btn-primary" onclick="add()"><i
							class="glyphicon glyphicon-plus"></i> 新建</a>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:del">
						<button id="remove" class="btn btn-danger" disabled
							onclick="deleteAll()">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:export">
						<button id="exportAll" class="btn btn-danger"
							onclick="exportAll()">
							<i class="glyphicon glyphicon-export"></i> 导出
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:import">
						<button id="btnImport" class="btn btn-info">
							<i class="fa fa-folder-open-o"></i> 导入
						</button>
						<div id="importBox" class="hide">
							<form id="importForm"
								action="${ctx}/diagn_serv_log/diagn_serv_log/diagnServLog/import"
								method="post" enctype="multipart/form-data"
								style="padding-left: 20px; text-align: center;">
								<br /> <input id="uploadFile" name="file" type="file"
									style="width: 330px" />导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br />


							</form>
						</div>
					</shiro:hasPermission>

				</div>

				<!-- 表格 -->
				<table id="diagnServLogTable" data-toolbar="#toolbar"></table>

				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="diagn_serv_log:diagn_serv_log:diagnServLog:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>