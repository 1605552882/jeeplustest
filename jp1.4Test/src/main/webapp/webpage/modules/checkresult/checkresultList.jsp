<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>人工检测结果管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="checkresultList.js" %>
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
			<form:form id="searchForm" modelAttribute="checkresult" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="单据编号：">单据编号：</label>
				<form:input path="sbillno" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="存在问题：">存在问题：</label>
				<form:select path="hasproblem" cssClass="form-control ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('document_problem')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="单据状态：">单据状态：</label>
				<form:select path="sStatus" cssClass="form-control ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('documentarydetails_sStatus')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-6">
				 <div class="form-group">
					<label class="label-item single-overflow pull-left " title="创建时间：">&nbsp;创建时间：</label>
					<div class="col-xs-12">
						   <div class="col-xs-12 col-sm-5">
					        	  <div class='input-group date' id='beginDcreatetime' style="left: -10px;" >
					                   <input type='text'  name="beginDcreatetime" class="form-control required"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='endDcreatetime' style="left: -10px;" >
					                   <input type='text'  name="endDcreatetime" class="form-control  required" />
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
			<shiro:hasPermission name="checkresult:checkresult:add">
				<a id="add" class="btn btn-primary" href="${ctx}/checkresult/checkresult/form" title="人工检测结果"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="checkresult:checkresult:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="checkresult:checkresult:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="checkresult:checkresult:export">
				<button id="exportAll" class="btn btn-danger"  onclick="exportAll()">
	            	<i class="glyphicon glyphicon-export"></i> 导出
	        	</button>
			</shiro:hasPermission>			
			
			<shiro:hasPermission name="checkresult:checkresult:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/checkresult/checkresult/import" method="post" enctype="multipart/form-data"
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
	<table id="checkresultTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="checkresult:checkresult:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="checkresult:checkresult:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>