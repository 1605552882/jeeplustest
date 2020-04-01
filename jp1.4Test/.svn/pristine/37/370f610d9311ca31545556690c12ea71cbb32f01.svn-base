<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>端到端查询</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="embList.js" %>
	<style type="text/css">
		#documentarydetailsTable {
			 table-layout:fixed; 
		}
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
		
		.r1{
			margin-top:10px;
			margin-left:1px;
			margin-right:1px;
		}
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
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
			<form id="searchForm" class="row form-inline" style="border:1px solid #AAAAFF;padding-bottom:5px;padding-top:5px;margin:5px 5px 5px 5px">

			<div class="col-xs-12 col-sm-12 col-md-4">
				<label class="label-item single-overflow pull-left" title="用户号码：">用户号码：</label>
				<input id="number" name="number"  class="form-control"/>
			</div>

			<div class="col-xs-12 col-sm-6 col-md-4" style="margin-top:8px">
				<label class="control_label">&nbsp;开始时间：</label>
				<div class='input-group date' id='beginCreateTime' >
	                   <input type='text'  name="beginCreateTime" class="form-control"  />
	                   <span class="input-group-addon" style="padding:0 6px 0 6px">
	                       <span class="glyphicon glyphicon-calendar"></span>
	                   </span>
	            </div>
			</div>
			
			<div class="col-xs-12 col-sm-6 col-md-4" style="margin-top:8px">
				<label class="control_label">&nbsp;结束时间：</label>
				<div class='input-group date' id='endCreateTime' >
	                   <input type='text'  name="endCreateTime" class="form-control"  />
	                   <span class="input-group-addon" style="padding:0 6px 0 6px">
	                       <span class="glyphicon glyphicon-calendar"></span>
	                   </span>
	            </div>
			</div>
			
			<div class="col-xs-12" style="margin-top:8px">
			  <a  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</a>
			 </div>
	</form>
	
		<div class="row r1 form-inline">
			<!--  -->
			<div class="col-xs-12">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title"></h5>
					</div>
				    <div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">是否存在弱覆盖问题</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">找到投诉相关小区数</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">是否存在SGW质差原因</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">是否存在SP质差原因</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">用户是否使用质差终端</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">省内申迪平台查询是否成功</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">终端型号</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">集团平台查询是否成功</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">IsRf</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12" style="padding:5px 0 5px 10px"><label class="control-label col-xs-2 ">最终结论</label><textarea rows="5" cols="20" class="col-xs-7 input-height"/></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12">
			<div class="gn1 panel panel-primary">
				<div class="panel-heading">
					<h5 class="panel-title">投诉小区信息</h5>
				</div>
				<!-- 表格 -->
				<table id="authenticationTable" data-height="520"></table>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>