<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工单超时管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="overtimedocumentList.js" %>
	<style type="text/css">
		 th {background-color: #84C1FF;}
	</style>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="overtimedocument" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-3">
				<label class="label-item single-overflow pull-left" title="工单编号：">工单编号：</label>
				<form:input path="sbillno" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="单据状态：">单据状态：</label>
				<form:select  path="sStatus"   class=" form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('documentarydetails_sStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-6">
				 <div class="form-group">
					<label class="label-item single-overflow pull-left" title="申告时间：">&nbsp;申告时间：</label>
					<div class="col-xs-12">
						   <div class="col-xs-12 col-sm-5">
					        	  <div class='input-group date' id='beginReportTime' style="left: -10px;" >
					                   <input type='text'  name="beginReportTime" class="form-control"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='endReportTime' style="left: -10px;" >
					                   <input type='text'  name="endReportTime" class="form-control" />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					           	</div>	
					        </div>
					</div>
				</div>
			</div>
		 <div class="col-xs-12 col-sm-6 col-md-4">
			<div style="margin-top:26px">
			  <a  id="search" class="btn btn-primary"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-danger" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
			<shiro:hasPermission name="overtimedocument:overtimedocument:add">
				<a id="add" class="btn btn-primary" href="${ctx}/overtimedocument/overtimedocument/form" title="工单超时"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="overtimedocument:overtimedocument:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="overtimedocument:overtimedocument:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="overtimedocument:overtimedocument:export">
				<button id="exportAll" class="btn btn-danger"  onclick="exportAll()">
	            	<i class="glyphicon glyphicon-export"></i> 导出
	        	</button>
			</shiro:hasPermission>			
			
			<shiro:hasPermission name="overtimedocument:overtimedocument:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/overtimedocument/overtimedocument/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission>
	        	<a class="btn btn-primary" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="overtimedocumentTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="overtimedocument:overtimedocument:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="overtimedocument:overtimedocument:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>