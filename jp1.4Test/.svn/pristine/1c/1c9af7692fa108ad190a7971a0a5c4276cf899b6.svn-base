<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>根据区号查询黑名单</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="areaCodeBlackListWindow.js" %>
	
	<style type="text/css">
		 th {background-color: #99CCFF;}
		 
		.panel-primary{
        	border:1px solid blue;
        	border-radius:5px 5px 5px 5px;
			margin-top:5px;
        }
           
        .panel-heading{
			height:15px;
			background-color:blue;
		}
       
		.panel-title{
			margin-top:-7px;
		}
	</style>
	
</head>
<body>
	<div class="container-fluid"  style="background-color: white">
	 <form class="form-inline">
		<!-- 查询框 -->
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h5 class="panel-title">查询条件</h5>
			</div>
			
			<div class="row" style="margin:5px 5px 5px 5px;padding-top:5px;padding-bottom:5px;">
				
				<div class="col-xs-4" style="margin-top:5px">
					<div class="form-group">
						<label for="areaCode" class="control-label">区号</label>
						<input type="text" class="form-control input-sm" name="areaCode" id="areaCode" style="font-size:14px;">
					</div>
				</div>
				
				<div class="col-xs-4" style="margin-top:5px;margin-left:-50px;">
					<div class="form-group">
						<label for="time" class="control-label">开始时间</label>
						<div class="input-group date" id="startTime">
						   <input type='text'  name="startTime" class="form-control"/>
						   <span class="input-group-addon">
							   <span class="glyphicon glyphicon-calendar"></span>
						   </span>
						</div>	
					</div>
				</div>
				
				<div class="col-xs-4" style="margin-top:5px;margin-left:20px;">
					<div class="form-group">
						<label for="time" class="control-label">结束时间</label>
						<div class="input-group date" id="endTime">
						   <input type='text'  name="endTime" class="form-control"/>
						   <span class="input-group-addon">
							   <span class="glyphicon glyphicon-calendar"></span>
						   </span>
						</div>	
					</div>
				</div>
				
			</div >
			
			<div class="row">
				<div class="col-xs-4" style="padding:5px 5px 5px 20px">
					<a  id="search" class="btn btn-primary btn-sm" style="width:100px"><i class="fa fa-search"></i>  查询</a>
				</div>
			</div>
			
		</div>	
		
		
		
		<!-- 表格 -->
		<div class="row" style="margin-top:10px">
			<div class="col-xs-12">
				<table id="blackListTable" data-height="450" ></table>
			</div>
		</div>
		
	</form> 
 </div>

</body>
</html>