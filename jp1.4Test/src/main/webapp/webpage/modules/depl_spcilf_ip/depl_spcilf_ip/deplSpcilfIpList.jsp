<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>机器部署信息管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="deplSpcilfIpList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 搜索 -->
				<div class="accordion-group">
					<div id="collapseTwo" class="accordion-body">
						<div class="accordion-inner">
							<form:form id="searchForm" modelAttribute="deplSpcilfIp"
								class="form form-horizontal well clearfix">
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="机器名：">请选择：</label>
									<form:input path="name" htmlEscape="false" maxlength="55"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="机器名：">机器名：</label>
									<form:input path="name" htmlEscape="false" maxlength="55"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="配置信息：">配置信息：</label>
									<form:select path="sys" class="form-control m-b">
										<form:option value="" label="" />
										<form:option value="windows" label="windows" />
										<form:option value="Linux" label="Linux" />
									</form:select>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left" title="地址：">地址：</label>
									<form:input path="ip" htmlEscape="false" maxlength="15"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="物理位置：">物理位置：</label>
									<form:input path="address" htmlEscape="false" maxlength="55"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left" title="用途：">用途：</label>
									<form:input path="useful" htmlEscape="false" maxlength="55"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
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
						name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:add">
						<a id="add" class="btn btn-primary" onclick="add()"><i
							class="glyphicon glyphicon-plus"></i> 添加机器配置</a>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 修改机器配置
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:del">
						<button id="remove" class="btn btn-danger" disabled
							onclick="deleteAll()">
							<i class="glyphicon glyphicon-remove"></i> 删除机器配置
						</button>
					</shiro:hasPermission>

				</div>

				<!-- 表格 -->
				<table id="deplSpcilfIpTable" data-toolbar="#toolbar"></table>



				<!-- 工具栏 -->
				<div id="toolbar1">
					<shiro:hasPermission
						name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:add">
						<a id="add" class="btn btn-primary" onclick="add1()"><i
							class="glyphicon glyphicon-plus"></i> 添加端口配置</a>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:edit">
						<button id="edit" class="btn btn-success" 
							onclick="edit1()">
							<i class="glyphicon glyphicon-edit"></i> 修改端口配置
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="depl_spcilf_port:depl_spcilf_port:deplSpcilfPort:del">
						<button id="remove" class="btn btn-danger" 
							onclick="deleteAll1()">
							<i class="glyphicon glyphicon-remove"></i> 删除端口配置
						</button>
					</shiro:hasPermission>



				</div>

				<!-- 表格 -->
				<table id="deplSpcilfPortTable" data-toolbar="#toolbar1"></table>
				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission
						name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="depl_spcilf_ip:depl_spcilf_ip:deplSpcilfIp:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>