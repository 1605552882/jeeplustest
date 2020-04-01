<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>HSS受理日志查询</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="hsslog.js" %>
	
	<style type="text/css">
		 th {background-color: #99CCFF;}
	</style>
	
</head>
<body>
	<div class="container-fluid"  style="background-color: white">
	 <form class="form-inline" id="hsslogForm">
		<!-- 查询框 -->
		<div class="row" style="border:1px solid #AAAAFF;margin-left:5px;margin-right:5px;margin-top:10px;padding-top:5px;padding-bottom:5px;">
			<div class="col-xs-3" style="margin-top:5px">
				<div class="form-group">
					<label for="queryType" class="control-label">查询方式</label>
					<select class="form-control" id="queryType">
					  <option value="1">按手机号查询</option>
					  <option value="2">按IMSI号查询</option>
					</select>
				</div>
			</div>
			
			<div class="col-xs-3" style="margin-top:5px">
				<div class="form-group">
					<label for="userNo" class="control-label" style="width:60px;text-align:right;">手机号码</label>
					<input type="text" class="form-control input-sm" id="userNo" style="font-size:14px;">
				</div>
			</div>
			
			<div class="col-xs-6">
				<div class="form-group" style="padding-left:30px">
					<label for="time" class="control-label">年月份</label>
					<div class="input-group date" id="startDate">
					   <input type='text'  name="startDate" class="form-control"/>
					   <span class="input-group-addon">
						   <span class="glyphicon glyphicon-calendar"></span>
					   </span>
					</div>	
					
				</div>
			</div>
			
			<div class="col-xs-12" style="padding-left:25px;margin-top:5px;margin-bottom:5px">
				<!-- 按钮 -->
				<div class="row" style="margin-top:8px">
					<a  id="search" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-search"></i>  查询</a>
				</div>
			</div>
			
		</div>
		
		
		<!-- 表格 -->
		<div class="row" style="margin-top:10px">
			<div class="col-xs-12">
				<table id="hssLogTable" data-height="450" ></table>
			</div>
		</div>
		
	</form> 
 </div>
 
	<!-- 提示框 -->
	<div class="modal fade" id="myModal">
	    <div class="modal-dialog">
	        <div class="modal-content">
	               <div class="modal-header">
	                   <h3><font color="blue">提示</font></h3>
	               </div>
	               <div class="modal-body">
	                   <h4><strong id="msg">请先查询再修改!</strong></h4>
	               </div>
	               <div class="modal-footer">
	                   <button class="btn btn-info" data-dismiss="modal">关闭</button>
	               </div>
	        </div>
	    </div>
	</div>
	

</body>
</html>