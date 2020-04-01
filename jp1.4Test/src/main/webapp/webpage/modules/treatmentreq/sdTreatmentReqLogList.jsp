<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>求医记录管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="sdTreatmentReqLogList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">求医记录列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="sdTreatmentReqLog" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="地点：">地点：</label>
										<sys:gridselect url="${ctx}/treamentplace/sdTreatmentplaceInf/data" id="sdTreatmentplaceInf" name="sdTreatmentplaceInf.id" value="${sdTreatmentReqLog.sdTreatmentplaceInf.id}" labelName="sdTreatmentplaceInf.placename" labelValue="${sdTreatmentReqLog.sdTreatmentplaceInf.placename}"
							 title="选择地点" cssClass="form-control required" fieldLabels="地点" fieldKeys="placename" searchLabels="地点" searchKeys="placename" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="预约人：">预约人：</label>
										<sys:gridselect url="${ctx}/patient/sdPatientInf/data" id="sdPatientInf" name="sdPatientInf.id" value="${sdTreatmentReqLog.sdPatientInf.id}" labelName="sdPatientInf.fullname" labelValue="${sdTreatmentReqLog.sdPatientInf.fullname}"
							 title="选择预约人" cssClass="form-control required" fieldLabels="病人" fieldKeys="fullname" searchLabels="病人" searchKeys="fullname" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="记录状态：">&nbsp;记录状态：</label>
					<div class="col-xs-12">
						<form:radiobuttons class="i-checks" path="reqstate" items="${fns:getDictList('treatmentreq_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</div>
				</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="标题：">标题：</label>
				<form:input path="reqtitle" htmlEscape="false" maxlength="200"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="备注信息：">备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255"  class=" form-control"/>
			</div>
		 <div class="col-xs-12 col-sm-6 col-md-4">
			<div style="margin-top:26px">
			  <a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
			<shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:add">
				<a id="add" class="btn btn-primary" onclick="add()"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:export">
				<button id="exportAll" class="btn btn-danger"  onclick="exportAll()">
	            	<i class="glyphicon glyphicon-export"></i> 导出
	        	</button>
			</shiro:hasPermission>			
			
			<shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/treatmentreq/sdTreatmentReqLog/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission>
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="sdTreatmentReqLogTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="treatmentreq:sdTreatmentReqLog:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>