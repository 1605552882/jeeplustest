<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>月度汇总管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<!-- 引入 echarts.js -->
	<%@ include file="/webpage/include/echarts.jsp"%>
	<%@include file="monthcountList.js" %>
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
			<form:form id="searchForm" modelAttribute="monthcount" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="班组：">班组：</label>
				<form:select path="dutyGroup"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('group')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
					<label class="label-item single-overflow pull-left" title="查询时间：">&nbsp;查询时间：</label>
					<div class="col-xs-12">
						   <div class="col-xs-12 col-sm-5">
					        	  <div class='input-group date' id='beginSearchtime' style="left: -10px;" >
					                   <input type='text'  name="beginSearchtime" class="form-control required"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='endSearchtime' style="left: -10px;" >
					                   <input type='text'  name="endSearchtime" class="form-control required" />
					                   <span class="input-group-addon" style="height:10px'">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					           	</div>	
					        </div>
					</div>
				</div>
			</div>
		 <div class="col-xs-12 col-sm-6 col-md-4">
			<div style="margin-top:26px">
			  <button id="search" class="btn btn-primary"><i class="fa fa-search"></i> 查询</button>
			  <a  id="reset" class="btn btn-danger" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	</div>
	<!-- 工具栏 -->
	<div id="toolbar">
			<shiro:hasPermission name="monthcount:monthcount:add">
				<a id="add" class="btn btn-primary" href="${ctx}/monthcount/monthcount/form" title="月度汇总"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="monthcount:monthcount:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="monthcount:monthcount:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="monthcount:monthcount:export">
				<button id="exportAll" class="btn btn-danger"  onclick="exportAll()">
	            	<i class="glyphicon glyphicon-export"></i> 导出
	        	</button>
			</shiro:hasPermission>			
			
			<shiro:hasPermission name="monthcount:monthcount:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/monthcount/monthcount/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission>
	        	<a class="btn btn-primary" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		    <div id="details"></div>
		<div id="mychart" class="col-lg-12"></div>
	<!-- 表格 -->
	<table id="monthcountTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="monthcount:monthcount:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="monthcount:monthcount:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>
