<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>一件诊断子表管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="diagnPrjectList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">一件诊断子表列表</h3>
			</div>
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 工具栏 -->
				<div id="toolbar">
					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:add">
						<a id="add" class="btn btn-primary"
							href="${ctx}/diagn_prject/diagn_prject/diagnPrject/form"
							title="一件诊断子表"><i class="glyphicon glyphicon-plus"></i> 新建</a>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:del">
						<button id="remove" class="btn btn-danger" disabled
							onclick="deleteAll()">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:export">
						<button id="exportAll" class="btn btn-danger"
							onclick="exportAll()">
							<i class="glyphicon glyphicon-export"></i> 导出
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:import">
						<button id="btnImport" class="btn btn-info">
							<i class="fa fa-folder-open-o"></i> 导入
						</button>
						<div id="importBox" class="hide">
							<form id="importForm"
								action="${ctx}/diagn_prject/diagn_prject/diagnPrject/import"
								method="post" enctype="multipart/form-data"
								style="padding-left: 20px; text-align: center;">
								<br /> <input id="uploadFile" name="file" type="file"
									style="width: 330px" />导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br />


							</form>
						</div>
					</shiro:hasPermission>
				</div>

				<!-- 表格 -->
				<table id="diagnPrjectTable" data-toolbar="#toolbar"></table>

				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="diagn_prject:diagn_prject:diagnPrject:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>