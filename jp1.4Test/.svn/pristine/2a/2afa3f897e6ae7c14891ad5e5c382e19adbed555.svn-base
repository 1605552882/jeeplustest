<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工单抽取管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="documentchouseList.js" %>
	<style type="text/css">
		 th {background-color: #99CCFF;}
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
			<form:form id="searchForm" modelAttribute="documentchouse" class="form form-horizontal well clearfix">
			
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="关键词：">关键词：</label>
				<form:input path="key" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="业务类型：">业务类型：</label>
				<form:input path="busytype" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			
			<!-- 
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="责任人：">责任人：</label>
				<form:input path="people" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			-->	
			
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left"
					title="责任人：">责任人：</label>
				<form:select path="people" Class="selectpicker  form-control " maxlength="32">
					<form:option value="" label="" />
					<form:options items="${fns:getdutypeople()}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
								
			
			 <div class="col-xs-12 col-sm-4 col-md-2">
				<label class="label-item single-overflow pull-left" title="抽样比例">抽样比例：</label>
				<form:select path="sbillno"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('choose')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
					                   <input type='text'  name="beginReportTime" class="form-control required"  />
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
					                   <input type='text'  name="endReportTime" class="form-control required" />
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
			  <button  id="search" class="btn btn-primary"><i class="fa fa-search"></i> 查询</button>
			  <a  id="reset" class="btn btn-danger" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
			<shiro:hasPermission name="documentchoose:documentchouse:add">
				<a id="add" class="btn btn-primary" href="${ctx}/documentchoose/documentchouse/form" title="工单抽取"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="documentchoose:documentchouse:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="documentchoose:documentchouse:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="documentchoose:documentchouse:export">
				<button id="exportAll" class="btn btn-danger"  onclick="exportAll()">
	            	<i class="glyphicon glyphicon-export"></i> 导出
	        	</button>
			</shiro:hasPermission>			
			
			<shiro:hasPermission name="documentchoose:documentchouse:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/documentchoose/documentchouse/import" method="post" enctype="multipart/form-data"
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
	<table id="documentchouseTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="documentchoose:documentchouse:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="documentchoose:documentchouse:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>