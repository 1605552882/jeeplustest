<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>HSS日志查询</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="hssLogQry.js" %>
	<style type="text/css">
		#hssLogTable {
			 table-layout:fixed; 
		}
		 th {background-color: #99CCFF;}
 		.form-control{
           height:25px;
        }
        .input-height{
           height:25px;
        }
	</style>
</head>
<body>
	<div class="wrapper wrapper-content" style="background-color: white">
	<div class="">
	<div class="">
		<sys:message content="${message}"/>

		<!-- 搜索 -->
		<form id="searchForm" class="row form-inline" style="border:1px solid #AAAAFF;padding-bottom:5px;padding-top:5px;margin:5px 5px 5px 5px">

				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="control_label" for="numberType">操作方式：</label>
					<select class="form-control" style="height:30px;font-size:13px" id="numberType">
						<option value="1">按手机号码</option>
						<option value="2">按IMSI号码</option>
					</select>
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="" for="number">查询号码：</label>
					<input maxlength="32"  class="form-control" id="number" />
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="control_label" title="年月份：">&nbsp;年月份：</label>
					<div class='input-group date' id='beginCreateTime' >
		                   <input type='text'  name="beginCreateTime" class="form-control"  />
		                   <span class="input-group-addon" style="padding:0 6px 0 6px">
		                       <span class="glyphicon glyphicon-calendar"></span>
		                   </span>
		            </div>
				</div>

			 <div class="col-xs-12" style="margin-top:8px">
			  <button  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</button>
			  <a  id="reset" class="btn btn-danger btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
	    	 </div>
		</form>

	<!-- 表格 -->
	<table id="hssLogTable" data-height="520"></table>

	</div>
	</div>
	</div>
</body>
</html>