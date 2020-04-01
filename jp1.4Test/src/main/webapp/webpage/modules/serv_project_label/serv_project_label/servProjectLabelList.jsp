<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>智能预处理配置管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="servProjectLabelList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="col-md-12">

			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-body">
						<!-- 工具栏 -->
						<div id="toolbar">
							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:add">
								<a id="add" class="btn btn-primary"
									href="${ctx}/serv_project_label/serv_project_label/servProjectLabel/saddform"
									title="智能预处理配置"><i class="glyphicon glyphicon-plus"></i> 新建</a>
							</shiro:hasPermission>

							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:edit">
								<button id="edit" class="btn btn-success" disabled
									onclick="edit('1')">
									<i class="glyphicon glyphicon-edit"></i> 修改
								</button>
							</shiro:hasPermission>

							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:del">
								<button id="remove" class="btn btn-danger" disabled
									onclick="deleteAll()">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>

							</shiro:hasPermission>


						</div>

						<!-- 表格 -->
						<table id="servProjectLabelTable" data-toolbar="#toolbar"></table>

					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="panel panel-primary">
					<div class="panel-body">
						<!-- 工具栏 -->
						<div id="toolbar1">
							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:add1">
								<a id="add" class="btn btn-primary"
									href="${ctx}/serv_project_label/serv_project_label/servProjectLabel/form"
									title="智能预处理配置"><i class="glyphicon glyphicon-plus"></i> 新建</a>
							</shiro:hasPermission>

							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:edit1">
								<button id="edit" class="btn btn-success" disabled
									onclick="edit1()">
									<i class="glyphicon glyphicon-edit"></i> 修改
								</button>
							</shiro:hasPermission>

							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:del1">
								<button id="remove" class="btn btn-danger" disabled
									onclick="deleteAll1()">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>
							</shiro:hasPermission>

						</div>

						<!-- 表格 -->
						<table id="servProjectLabelTables" data-toolbar="#toolbar1"></table>

						<!-- context menu -->
						<ul id="context-menu" class="dropdown-menu">
							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:edit">
								<li data-item="edit"><a>编辑</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission
								name="serv_project_label:serv_project_label:servProjectLabel:del">
								<li data-item="delete"><a>删除</a></li>
							</shiro:hasPermission>
							<li data-item="action1"><a>取消</a></li>
						</ul>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>