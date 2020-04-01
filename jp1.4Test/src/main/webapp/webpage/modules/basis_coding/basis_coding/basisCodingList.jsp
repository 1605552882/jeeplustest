<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>编码管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="basisCodingList.js"%>
<style type="text/css">
.fixed-table-toolbar {
	padding-top: 7px;
}
</style>
</head>
<body>
	<div class="wrapper wrapper-content" style="font-size: 12px;">
		<div class="col-md-12">
			<div class="panel-body">
				<sys:message content="${message}" />
				<!-- 搜索 -->
				<div class="accordion-group">
					<div id="collapseTwo" class="accordion-body">
						<div class="accordion-inner">
							<form:form id="searchForm" modelAttribute="basisCoding"
								class="form form-horizontal well clearfix">
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left" title="名称：">名称：</label>
									<form:input path="name" htmlEscape="false" maxlength="64"
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
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-primary">
				<!-- 工具栏 -->
				<div id="toolbar">
					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:add">
						<a id="add" class="btn btn-primary"
							href="${ctx}/basis_coding/basis_coding/basisCoding/form"
							title="编码"><i class="glyphicon glyphicon-plus"></i> 新建</a>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 修改
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:del">
						<button id="remove" class="btn btn-danger" disabled
							onclick="deleteAll()">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:export">
						<button id="exportAll" class="btn btn-danger"
							onclick="exportAll()">
							<i class="glyphicon glyphicon-export"></i> 导出
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:import">
						<button id="btnImport" class="btn btn-info">
							<i class="fa fa-folder-open-o"></i> 导入
						</button>
						<div id="importBox" class="hide">
							<form id="importForm"
								action="${ctx}/basis_coding/basis_coding/basisCoding/import"
								method="post" enctype="multipart/form-data"
								style="padding-left: 20px; text-align: center;">
								<br /> <input id="uploadFile" name="file" type="file"
									style="width: 330px" />导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br />


							</form>
						</div>
					</shiro:hasPermission>

				</div>

				<!-- 表格 -->
				<table id="basisCodingTable" data-toolbar="#toolbar"></table>

				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission
						name="basis_coding:basis_coding:basisCoding:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>

		<div class="col-md-8">
			<div class="panel panel-primary">

				<div class="panel-body">
					<sys:message content="${message}" />
					<!-- 工具栏 -->
					<div id="toolbar1">
						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:add">
							<a id="add" class="btn btn-primary"
								href="${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/form"
								title="编码明细"><i class="glyphicon glyphicon-plus"></i> 新建</a>
						</shiro:hasPermission>



						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:edit">
							<button id="edit" class="btn btn-success" disabled
								onclick="edit()">
								<i class="glyphicon glyphicon-edit"></i> 修改
							</button>
						</shiro:hasPermission>


						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:del">
							<button id="remove" class="btn btn-danger" disabled
								onclick="deleteAll()">
								<i class="glyphicon glyphicon-remove"></i> 删除
							</button>
						</shiro:hasPermission>

						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:export">
							<button id="exportAll" class="btn btn-danger"
								onclick="exportAll()">
								<i class="glyphicon glyphicon-export"></i> 导出
							</button>
						</shiro:hasPermission>

						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:import">
							<button id="btnImport" class="btn btn-info">
								<i class="fa fa-folder-open-o"></i> 导入
							</button>
							<div id="importBox" class="hide">
								<form id="importForm"
									action="${ctx}/basis_coding_details/basis_coding_details/basisCodingDetails/import"
									method="post" enctype="multipart/form-data"
									style="padding-left: 20px; text-align: center;">
									<br /> <input id="uploadFile" name="file" type="file"
										style="width: 330px" />导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br />


								</form>
							</div>
						</shiro:hasPermission>
						<!-- <a class="accordion-toggle btn btn-default" data-toggle="collapse"
							data-parent="#accordion2" href="#collapseTwo"> <i
							class="fa fa-search"></i> 检索
						</a> -->
					</div>

					<!-- 表格 -->
					<table id="basisCodingDetailsTable" data-toolbar="#toolbar1"></table>

					<!-- context menu -->
					<ul id="context-menu" class="d ropdown-menu">
						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:edit">
							<li data-item="edit"><a>编辑</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission
							name="basis_coding_details:basis_coding_details:basisCodingDetails:del">
							<li data-item="delete"><a>删除</a></li>
						</shiro:hasPermission>
						<!-- 
						<li data-item="action1"><a>取消</a></li> -->
					</ul>
				</div>
			</div>

		</div>

	</div>
</body>
</html>