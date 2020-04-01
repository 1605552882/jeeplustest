<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>一键诊断告警信息管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="diagnAlarmList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">一键诊断告警信息列表</h3>
			</div>
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 搜索 -->
				<div class="accordion-group">
					<div id="collapseTwo" class="accordion-body">
						<div class="accordion-inner">
							<form:form id="searchForm" modelAttribute="diagnAlarm"
								class="form form-horizontal well clearfix">
								<div class="col-xs-4 divinput">
									<label class="label-item single-overflow pull-left"
										title="告警时间：">&nbsp;开始时间：</label>
									<div class="col-xs-8">
										<div class='input-group date' id='alarmTime'>
											<input type='text' name="alarmTime" class="form-control" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="col-xs-4 divinput">

									<label class="label-item single-overflow pull-left"
										title="告警时间：">&nbsp;结束时间：</label>
									<div class="col-xs-8">
										<div class='input-group date' id='alarmTime'>
											<input type='text' name="alarmTime" class="form-control" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>

								</div>
								<div class="col-xs-4 divinput">
									<label class="label-item single-overflow pull-left"
										title="告警级别：">告警级别：</label>
									<form:select path="alarmLevel" class="form-control m-b">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('diagn_alarm_level')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
								<div class="col-xs-4 divinput">
									<label class="label-item single-overflow pull-left"
										title="专业类别：">专业类别：</label>
									<form:input path="majorCategory" htmlEscape="false"
										maxlength="5" class=" form-control" />
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
					<shiro:hasPermission name="diagn_alarm:diagn_alarm:diagnAlarm:add">
						<a id="add" class="btn btn-primary" onclick="add()"><i
							class="glyphicon glyphicon-plus"></i> 新建</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="diagn_alarm:diagn_alarm:diagnAlarm:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="diagn_alarm:diagn_alarm:diagnAlarm:del">
						<button id="remove" class="btn btn-danger" disabled
							onclick="deleteAll()">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
					</shiro:hasPermission>

					<button id="updateconfirmWhether" class="btn btn-primary"
						onclick="updateconfirmWhether()">
						确认
					</button>

				</div>

				<!-- 表格 -->
				<table id="diagnAlarmTable" data-toolbar="#toolbar"></table>

				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission name="diagn_alarm:diagn_alarm:diagnAlarm:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="diagn_alarm:diagn_alarm:diagnAlarm:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>