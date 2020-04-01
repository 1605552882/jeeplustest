<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>申告种类统计管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="sourcestatisticList.js" %>
	<!-- 引入 multi select -->
	<link href="${ctxStatic}/plugin/bootstrapTable/bootstrap-select.min.css"rel="stylesheet" />
	<script src="${ctxStatic}/plugin/bootstrapTable/bootstrap-select.min.js"></script>
	<!-- 引入 echarts.js -->
	<%@ include file="/webpage/include/echarts.jsp"%>
	<style type="text/css">
		 th {background-color: #99CCFF;}
	</style>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title"></h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="sourcestatistic" class="form form-horizontal well clearfix">

			<div class="col-sm-2">
				<label class="label-item single-overflow pull-left" title="方式：">方式：</label>
				<form:select id="timeFlag" path="timeFlag"  class="form-control m-b">
					<option value="1">天</option>
					<option value="2">月</option>
				</form:select>
			</div>
			<div class="col-sm-2">
				<label class="label-item single-overflow pull-left" title="分组：">分组：</label>
				<form:select id="groupFlag" path="groupFlag"  class="form-control m-b">
					<option value="1">申告来源</option>
					<option value="2">申告地市</option>
				</form:select>
			</div>
			<div class="col-sm-2">
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
							<div class='input-group date' id='beginCreateDate' >
				                   <input type='text'  name="beginCreateDate" class="form-control"  />
				                   <span class="input-group-addon">
				                       <span class="glyphicon glyphicon-calendar"></span>
				                   </span>
				            </div>	
						</div>
						<div class="col-xs-12 col-sm-1">
				        		~
				       	</div>
				        <div class="col-xs-12 col-sm-5">
				          	<div class='input-group date' id='endCreateDate' style="left: -10px;" >
				                   <input type='text'  name="endCreateDate" class="form-control" />
				                   <span class="input-group-addon">
				                       <span class="glyphicon glyphicon-calendar"></span>
				                   </span>
				           	</div>	
				        </div>
				    </div>
				</div>
			</div>

		 <div class="col-xs-12">
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
		<shiro:hasPermission name="sourcestatistic:sourcestatistic:export">
			<button id="exportAll" class="btn btn-danger"  onclick="exportAll()">
            	<i class="glyphicon glyphicon-export"></i> 导出
       		</button>
		</shiro:hasPermission>	
       	<a class="btn btn-primary" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
			<i class="fa fa-search"></i> 检索
		</a>
    </div>
		
	<!-- 柱状图 -->
	<div id="mychart" class="col-lg-12"></div>
	</div>
	</div>
	</div>
</body>
</html>