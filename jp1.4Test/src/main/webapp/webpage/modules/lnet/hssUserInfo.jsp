<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>HSS用户签约信息</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="hssUserInfo.js" %>
	<style type="text/css">
		#documentarydetailsTable {
			 table-layout:fixed; 
		}
		 th {}
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
	<div class="">
		<sys:message content="${message}"/>

		<!-- 搜索 -->
		<form id="searchForm" class="row form-inline" style="border:1px solid #AAAAFF;padding-bottom:5px;padding-top:5px;margin:5px 5px 5px 5px">

				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="col-xs-4" for="numberType">查询方式：</label>
					<select class="form-control col-xs-8" style="height:30px;font-size:13px" id="numberType">
						<option value="1">按手机号码</option>
						<option value="2">按IMSI号码</option>
					</select>
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="">查询号码：</label>
					<input maxlength="32"  class="form-control" id="number" />
				</div>
				
				<div class="col-xs-12">
					<label class="col-xs-12" for="number">地市：</label>
					<label class="col-xs-12 col-sm-offset-2 col-md-offset-1"><input name="city" type="radio" value="1" checked="checked" />深圳，东莞</label>
					<label class="col-xs-12 col-sm-offset-2 col-md-offset-1"><input name="city" type="radio" value="2"/>广州，佛山</label>
					<label class="col-xs-12 col-sm-offset-2 col-md-offset-1"><input name="city" type="radio" value="1"/>汕头，揭阳，梅州，潮州，惠州，韶关，河源，汕尾</label>
					<label class="col-xs-12 col-sm-offset-2 col-md-offset-1"><input name="city" type="radio" value="1"/>中山，湛江，江门，珠海，茂名，云浮，阳江，肇庆，清远</label>
				</div>

			 <div class="col-xs-12 col-sm-12 col-md-5">
			 <div style="margin:2px 0px 0px 5px">
				  <button  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</button>
				  <a  id="reset" class="btn btn-danger btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
				  <button id="hssModifyWindow" class="btn btn-info btn-sm btn-success"><i class=""></i> 业务更改</button>
		    	  <button id="hssBatchOperateWindow" class="btn btn-warning btn-sm"><i class=""></i> 批量操作</button>
	    	 </div>
	    	 </div>
		</form>


		<div class="row r1 form-inline">
			<div class="col-xs-12">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">签约信息</h5>
					</div>
				    <div class="row">
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">L网IMSI</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">G网IMSI</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">国际漫游IMSI</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">手机号码</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">手机状态</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">计费特性</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">EHRPD接入权限</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">APNOI</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">漫游权限</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">终端最大上行带宽MB</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">终端最大下行带宽MB</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4 ">用户</label><input class="col-xs-7 input-height"/></div>
					</div>
				</div>
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">用户APN签约情况</h5>
					</div>
					<!-- 表格 -->
					<table id="apnTable" data-height="520"></table>
				</div>
			</div>
		</div>

	</div>
	</div>
	</div>
</body>
</html>