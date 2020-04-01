<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ENUM受理日志查询</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="enumLogInfo.js" %>
	<style type="text/css">
		#enumLogTable {
			 table-layout:fixed; 
		}
		 th {background-color: #99CCFF;}
		 .form-control{
           height:25px;
        }
        .input-height{
           height:25px;
        }
        /*表格点击背景*/
        .changeColor{
            background-color: #31b0d5  !important;
            color: white;
        }
     	/*面板*/ 
        .panel-primary{
        	border:1px solid blue;
        	border-radius:5px 5px 5px 5px;
        }
        .panel-heading{
			height:15px;
			background-color:blue;
		}
		.panel-title{
			margin-top:-7px;
		}
		#cmd{
			padding:20px;
		}
	</style>
</head>
<body>
	<div class="wrapper wrapper-content" style="background-color: white">
	<div class="">
	<div class="">
		<sys:message content="${message}"/>

	<!-- 搜索 -->

			<form id="searchForm" class="row form-inline" style="border:1px solid #AAAAFF;padding-bottom:5px;padding-top:5px;margin:5px 0px 5px 0px">

			<div class="col-xs-12">
				<div class="col-sm-4 col-md-4">
				<label class="control_label ">查询号码：</label>
				<input id="number" name="number"  class="form-control"/>
				</div>
			</div>

			<div class="col-xs-12" style="margin-top:8px">
				<label class="control_label">&nbsp;开始时间：</label>
				<div class='input-group date' id='startDate' >
	                   <input type='text'  name="startDate" class="form-control"  />
	                   <span class="input-group-addon" style="padding:0 6px 0 6px">
	                       <span class="glyphicon glyphicon-calendar"></span>
	                   </span>
	            </div>
	            <div class='input-group date' id='startTime' >
	                   <input type='text'  name="startTime" class="form-control"  />
	                   <span class="input-group-addon" style="padding:0 6px 0 6px">
	                       <span class="glyphicon glyphicon-calendar"></span>
	                   </span>
	            </div>
			</div>
			
			<div class="col-xs-12" style="margin-top:8px">
				<label class="control_label">&nbsp;结束时间：</label>
				<div class='input-group date' id='endDate' >
	                   <input type='text'  name="endDate" class="form-control"  />
	                   <span class="input-group-addon" style="padding:0 6px 0 6px">
	                       <span class="glyphicon glyphicon-calendar"></span>
	                   </span>
	            </div>
	            <div class='input-group date' id='endTime'>
	                   <input type='text'  name="endTime" class="form-control"  />
	                   <span class="input-group-addon" style="padding:0 6px 0 6px">
	                       <span class="glyphicon glyphicon-calendar"></span>
	                   </span>
	            </div>
			</div>
		
			<div class="col-xs-12" style="margin-top:8px">
			  <a  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-danger btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			  <!-- <a  id="" class="btn btn-success btn-sm" ><i class=""></i> 导出excel</a> -->
			 </div>
	
		</form>

	<!-- 表格 -->
	<table id="enumLogTable"></table>
	<div class="gn1 panel panel-primary">
		<div class="panel-heading">
			<h5 class="panel-title">操作指令</h5>
		</div>
		<div class="row" id="cmd">
		</div>
	</div>
					
	</div>
	</div>
	</div>

</body>
</html>