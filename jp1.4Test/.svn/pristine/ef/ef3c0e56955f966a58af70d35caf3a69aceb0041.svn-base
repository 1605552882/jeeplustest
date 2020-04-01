<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>HSS用户基本信息查询</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	
	<style>
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
			<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="" for="number">查询号码：</label>
				<input maxlength="32"  class="form-control" id="number" />
			</div>
			 <div class="col-xs-12 col-sm-12 col-md-5">
			 <div style="margin:2px 0px 0px 5px">
			  <button  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</button>
			  <a  id="reset" class="btn btn-danger btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
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
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">L网IMSI</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">G网IMSI</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">用户状态</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">用户计费签约</label><input class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">用户ODB 信息</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4 ">接入网络模式</label><input class="col-xs-7 input-height"/></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>

</html>