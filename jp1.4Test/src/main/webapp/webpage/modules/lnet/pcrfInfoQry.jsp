<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>签约套餐信息</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="pcrfInfoQry.js" %>
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

				<div class="col-xs-12 col-sm-6">
					<label class="col-xs-2" for="number">用户号码：</label>
					<input maxlength="32"  class="form-control col-xs-10" id="number" />
				</div>
				
				<div class="col-xs-12">
					<label class="col-xs-12">地市：</label>
					<label class="col-xs-12 col-xs-offset-2 col-sm-offset-1"><input name="city" type="radio" value="1" checked="checked" />深圳，东莞</label>
					<label class="col-xs-12 col-xs-offset-2 col-sm-offset-1"><input name="city" type="radio" value="2"/>广州，佛山</label>
					<label class="col-xs-12 col-xs-offset-2 col-sm-offset-1"><input name="city" type="radio" value="1"/>汕头，揭阳，梅州，潮州，惠州，韶关，河源，汕尾</label>
					<label class="col-xs-12 col-xs-offset-2 col-sm-offset-1"><input name="city" type="radio" value="1"/>中山，湛江，江门，珠海，茂名，云浮，阳江，肇庆，清远</label>
				</div>

			 <div class="col-xs-12 col-sm-12 col-md-5">
			 <div style="margin:2px 0px 0px 5px">
			  <button  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</button>
			  <a  id="reset" class="btn btn-danger btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			  <button id="pcrfModifyWindow" class="btn btn-info btn-sm btn-success"><i class=""></i> 业务更改</button>
	    	  <button id="pcrfBatchOperateWindow" class="btn btn-warning btn-sm"><i class=""></i> 批量操作</button>
	    	 </div>
	    	 </div>
	    	 
		</form>


		<div class="row r1 form-inline">
			<div class="col-xs-12">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">PCRF用户签约信息</h5>
					</div>
				    <div class="row">
					<div class="col-xs-12 col-md-4" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">当前超流量状态</label><input class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-4" style="padding:5px 0 5px 10px;"><label class="control-label col-xs-4 ">付费类型</label><input class="col-xs-7 input-height"/></div>
					</div>
				</div>
				
				<!-- 表格 -->
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">PCRF用户签约信息</h5>
					</div>
					<table id="table" data-height="520"></table>
				</div>
			</div>
		</div>

	
	</div>
	</div>
	</div>
</body>
</html>