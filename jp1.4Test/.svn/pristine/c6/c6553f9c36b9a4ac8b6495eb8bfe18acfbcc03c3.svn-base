<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>根据号码查询黑名单</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="numBlackListQryWindow.js" %>
	
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
				
				<div class="col-xs-6" style="margin-top:5px">
					<div class="form-group">
						<label for="userNo" class="control-label">号码</label>
						<input type="text" class="form-control input-sm" name="userNo" id="userNo" style="font-size:14px;">
						<a  id="search" class="btn btn-primary btn-sm" style="width:80px;margin-left:5px;"><i class="fa fa-search"></i>  查询</a>
					</div>
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