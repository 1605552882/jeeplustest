<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ANAAA管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="anaaaList.js" %>
	
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
		<form id="searchForm" class="row form-inline" style="border:1px solid #AAAAFF;padding-bottom:5px;padding-top:5px;margin:5px 5px 5px 5px" onsubmit = "return false;">

				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="control_label" for="numberType">操作方式：</label>
					<select class="form-control" style="height:30px;font-size:13px" id="searchType">
						<option value="1">按手机号码</option>
						<option value="0">按IMSI号码</option>
					</select>
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="" id="userNoLabel" for="userNoValue">手机号码：</label>
					<input maxlength="32"  class="form-control" id="userNoValue" />
				</div>


			 <div class="col-xs-12 col-sm-12 col-md-5">
			 <div style="margin:2px 0px 0px 5px">
			  <button  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</button>
			 </div>
	    	 </div>
	    	 <div style="margin-left:10px">
	    	 <shiro:hasPermission name="anaaa:anaaa:modify">
	    	 	<button id="ANAAAModifyWindow" style="margin:5px 5px 0px 0px" class="btn btn-primary col-xs-2 col-md-1 btn-sm"><i class=""></i> 业务更改</button>
 	 		 </shiro:hasPermission>
 	 		 <shiro:hasPermission name="anaaa:anaaa:bind">
		 		 <div style="margin:5px 5px 0px 0px; padding:0" class="btn-group col-xs-2 col-md-2 btn-sm btn-primary">
				 <button data-toggle="dropdown" style="padding:5px 10px 5px 10px" class="btn btn-success btn-sm dropdown-toggle col-xs-12">IOS业务测试 <i class="glyphicon glyphicon-triangle-bottom"></i></button>
				 <ul class="dropdown-menu">
					<li><a id="ANAAABindPhoneCardWindow" class="btn btn-success">开启IOS测试</a></li>
					<li><a id="ANAAAUnbindPhoneCardWindow" class="btn btn-success">关闭IOS测试</a></li>
				 </ul>
				 </div>
			 </shiro:hasPermission>
			 <shiro:hasPermission name="anaaa:anaaa:authLog">
	    	 	<button id="ANAAAAuthLogWindow" style="margin:5px 5px 0px 0px" class="btn btn-warning col-xs-2 col-md-1 btn-sm"><i class=""></i> 认证日志查询</button>
	    	 </shiro:hasPermission>
	    	 <shiro:hasPermission name="anaaa:anaaa:accountLog">
	    	 	<button id="ANAAAAccountLogWindow" style="margin:5px 5px 0px 0px" class="btn btn-danger col-xs-2 col-md-2 btn-sm"><i class=""></i> 开销户接口日志查询</button>
	    	 </shiro:hasPermission>
	    	 <shiro:hasPermission name="anaaa:meid:list">
	    	 	<button id="MeidManagementWindow" style="margin:5px 5px 0px 0px" class="btn btn-success col-xs-2 col-md-1 btn-sm"><i class=""></i> MEID维护</button>
	    	 </shiro:hasPermission>
	    	 </div>
	    	 
		</form>
		
		<div class="row r1 form-inline">
			<!-- ANAAA -->
			<div class="col-xs-12" id="anaaaInfo">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">ANAAA</h5>
					</div>
				    <div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">手机号码</label><input id="mdn" class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">用户状态</label><input id="status" class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">IMSI号码</label><input id="imsi" class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">非法登陆次数</label><input id="failTimes" class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">硬件鉴权类型</label><input id="hardwareIdAuth" class="col-xs-7 input-height"/></div>
					<div class="col-xs-12 col-md-6" style="padding:5px 0 0 10px"><label class="control-label col-xs-4 ">UIM卡类型</label><input id="uimType" class="col-xs-7 input-height"/></div>
					</div>
					<div class="row">
					<div class="col-xs-12 col-md-6" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4 ">MEID</label><input id="meid" class="col-xs-7 input-height"/></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>

</html>