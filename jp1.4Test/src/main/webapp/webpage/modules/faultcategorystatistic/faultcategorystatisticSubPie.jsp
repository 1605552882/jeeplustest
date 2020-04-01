<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="ani"/>
	<!-- 引入 echarts.js -->
	<%@ include file="/webpage/include/echarts.jsp"%>
	<%@include file="faultcategorystatisticSubPie.js" %>
</head>
<body class="bg-white">
	<form:form id="searchForm" modelAttribute="faultcategorystatistic" class="form form-horizontal well clearfix" hidden="hidden" >
			<div>
				<input id="timeFlag" name="timeFlag">
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="单据状态：">单据状态：</label>
				<input id="sStatus" name="sStatus">
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="故障种类：">故障种类：</label>
				<input id="faultcategory" name="faultcategory">
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="故障来源：">故障来源：</label>
				<input id="city" name="city">
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
	</form:form>
	<div id="mychart" class="col-sm-12"></div>
</body>
</html>