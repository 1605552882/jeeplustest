<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>一件诊断主表管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="diagnServList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="col-md-12">
			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-body">
						<sys:message content="${message}" />
						<!-- 工具栏 -->
						<div id="toolbar">
							<shiro:hasPermission name="diagn_serv:diagn_serv:diagnServ:add">
								<a id="add" class="btn btn-primary"
									href="${ctx}/diagn_serv/diagn_serv/diagnServ/form"
									title="一件诊断主表"><i class="glyphicon glyphicon-plus"></i> 新建</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="diagn_serv:diagn_serv:diagnServ:edit">
								<button id="edit" class="btn btn-success" disabled
									onclick="edit()">
									<i class="glyphicon glyphicon-edit"></i> 修改
								</button>
							</shiro:hasPermission>

							<shiro:hasPermission name="diagn_serv:diagn_serv:diagnServ:del">
								<button id="remove" class="btn btn-danger" disabled
									onclick="deleteAll()">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>

							</shiro:hasPermission>

						</div>
						<!-- 表格 -->
						<table id="diagnServTable" data-toolbar="#toolbar"></table>
						<!-- context menu -->
						<ul id="context-menu" class="dropdown-menu">
							<shiro:hasPermission name="diagn_serv:diagn_serv:diagnServ:edit">
								<li data-item="edit"><a>编辑</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="diagn_serv:diagn_serv:diagnServ:del">
								<li data-item="delete"><a>删除</a></li>
							</shiro:hasPermission>
							<li data-item="action1"><a>取消</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<input type="text" hidden="true" value="" id="serv_id"/>
				<div class="panel panel-primary">
					<div class="panel-body">
						<!-- 工具栏 -->
						<div id="toolbar1">
							
							<shiro:hasPermission
								name="diagn_prject:diagn_prject:diagnPrject:add">
								<button id="add" class="btn btn-primary" 
									onclick="add()">
									<i class="glyphicon glyphicon-plus"></i> 新建
								</button>
								<%-- <a id="add" class="btn btn-primary"
									href="${ctx}/diagn_prject/diagn_prject/diagnPrject/form"
									title="一件诊断子表"><i class="glyphicon glyphicon-plus"></i> 新建</a> --%>
							</shiro:hasPermission>

							<shiro:hasPermission
								name="diagn_prject:diagn_prject:diagnPrject:edit">
								<button id="edit" class="btn btn-success"
									onclick="edit1()">
									<i class="glyphicon glyphicon-edit"></i> 修改
								</button>
							</shiro:hasPermission>

							<shiro:hasPermission
								name="diagn_prject:diagn_prject:diagnPrject:del">
								<button id="remove" class="btn btn-danger"
									onclick="deleteAll1()">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>
							</shiro:hasPermission>
						</div>
						<!-- 表格 -->
						<table id="diagnPrjectTable" data-toolbar="#toolbar1"></table>

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
		</div>
	</div>
</body>
</html>