<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="aAAList.js" %>
	<!-- include file="/webpage/include/button.jsp" -->
	<style>
		/*背景*/
		.wrapper-content{
			background-color: white
		}
		/*输入控件*/
		input,.form-control{
           height:25px;
        }
        
        /*查询区域*/
        #searchForm{
        	border:1px solid #AAAAFF;
        	padding-bottom:5px;
        	padding-top:5px;
        	margin:5px 5px 5px 5px;
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
		.r1{
			margin-top:10px;
			margin-left:1px;
			margin-right:1px;
		}
		/*按钮*/
		div button{
			margin:5px 5px 0px 0px;
		}
    </style>

</head>
<body>
	<div class="wrapper wrapper-content">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
		<form id="searchForm" class="row form-inline" onsubmit = "return false;">
			 <div class="col-xs-12 col-sm-6 col-md-3">
			 	<label class="control_label" for="searchType">操作方式：</label>
			 	<select class="form-control" id="searchType" style="padding:0" >
			 		<option value="1">按手机号码</option>
			 		<option value="0">按IMSI号码</option>
			 	</select>
			 </div>
			 
			 <div class="col-xs-12 col-sm-6 col-md-4">
			 	<label class="" for="userNoValue">手机号码：</label>
			 	<input maxlength="32" id="userNoValue"/>
			 </div>
			 
			 <div class="col-xs-12 col-sm-12 col-md-5">
			 <div style="margin:2px 0px 0px 5px">
			  <button  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 查询</button>
	    	 </div>
	    	 </div>
	    	 <div style="margin-left:10px">
	    	 <shiro:hasAnyPermissions name="aaa:aaa:jckh,aaa:aaa:kaihu,aaa:aaa:cgkaihu">
	    	 	<button id="jckh" class="btn btn-primary col-xs-2 col-md-1 btn-sm"><i class=""></i> 集成开户</button>
	    	 </shiro:hasAnyPermissions>
	    	 <shiro:hasPermission name="aaa:aaa:cancelAccount">
	    	 	<button id="cancelAccount" class="btn btn-info col-xs-2 col-md-1 btn-sm"><i class=""></i> 销卡</button>
    	 	 </shiro:hasPermission>
    	 	 <shiro:hasPermission name="aaa:aaa:changeNumber">
    	 		<button id="changeNumber" class="btn btn-warning col-xs-2 col-md-1 btn-sm"><i class=""></i> 换号</button>
   	 		 </shiro:hasPermission>
   	 		 <shiro:hasAnyPermissions name="aaa:aaa:addBlackList,aaa:aaa:removeBlackList">
				 <div style="margin-right:5px;padding:0" class="btn-group col-xs-2 col-md-1 btn-sm ">
				 <button data-toggle="dropdown" style="padding:5px 10px 5px 10px"  class="btn btn-danger btn-sm dropdown-toggle col-xs-12">黑名单 <i class="glyphicon glyphicon-triangle-bottom"></i></button>
				 <ul class="dropdown-menu">
				 	<shiro:hasPermission name="aaa:aaa:addBlackList">
						<li><a id="addBlackList" class="btn btn-danger">加入黑名单</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="aaa:aaa:removeBlackList">
						<li><a id="removeBlackList" class="btn btn-danger">解除黑名单</a></li>
					</shiro:hasPermission>
				 </ul>
				 </div>
			 </shiro:hasAnyPermissions>
			 <shiro:hasAnyPermissions name="aaa:aaa:changeIMSI,aaa:aaa:changeCGIMSI">
		 		 <div style="margin-right:5px;padding:0" class="btn-group col-xs-2 col-md-1 btn-sm ">
				 <button data-toggle="dropdown" style="padding:5px 10px 5px 10px" class="btn btn-primary btn-sm dropdown-toggle col-xs-12">换卡 <i class="glyphicon glyphicon-triangle-bottom"></i></button>
				 <ul class="dropdown-menu">
				 	<shiro:hasPermission name="aaa:aaa:changeIMSI">
						<li><a id="changeIMSI" class="btn btn-primary">普通用户换卡</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="aaa:aaa:changeCGIMSI">
						<li><a id="changeCGIMSI" class="btn btn-primary">CG国际漫游卡换卡</a></li>
					</shiro:hasPermission>
				 </ul>
				 </div>
			 </shiro:hasAnyPermissions>
			 <!-- 单选按钮（卡付费类型和漫游权限）不知道对应数据库的哪一个菜单，数据库的菜单（修改用户类型）不知道对应哪个单选按钮 -->
			 <shiro:hasAnyPermissions name="aaa:aaa:tinjji,aaa:aaa:fuji,aaa:aaa:jiasuo,aaa:aaa:jiesuo,aaa:aaa:vpdn,aaa:aaa:bdyw,aaa:aaa:gsz,aaa:aaa:jrlx">
	    	 	<button id="businessModify" class="btn btn-success col-xs-2 col-md-1 btn-sm"><i class=""></i> 业务更改</button>
	    	 </shiro:hasAnyPermissions>
			 <shiro:hasPermission name="aaa:aaa:authenticationLog">
	    	 	<button id="authenticationLog" class="btn btn-warning col-xs-2 col-md-1 btn-sm"><i class=""></i> 认证日志</button>
	    	 </shiro:hasPermission>
	    	 <shiro:hasPermission name="aaa:aaa:softwareCallTest">
	    	 	<button id="AAASoftwareCallTestWindow" class="btn btn-danger col-xs-2 col-md-1 btn-sm"><i class=""></i> 软拨测</button>
	    	 </shiro:hasPermission>
	    	 <button id="AAAAccountLogQueryWindow" class="btn btn-success col-xs-2 col-md-1 btn-sm"><i class=""></i> 开销户日志</button>
	    	 </div>
	    	 
		</form>
		
		<div id="aaaInfo" class="row r1 form-inline">
			<!-- C网 -->
			<div class="col-xs-12 col-md-6">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">C网</h5>
					</div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">MDN号</label><input id="aaaMDN0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">IMSI号码</label><input id="aaaIMSI0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">停复机状态</label><input id="aaaStatus0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">付费类型</label><input id="aaaType0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">漫游权限</label><input id="aaaMYPermissions0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">受限状态</label><input id="aaaBindOption0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">业务类型</label><input id="aaaBindNAI0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">VPDN域名</label><input id="aaaBindVPN0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">归属组</label><input id="aaaOwnerGroup0" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4">接入类型</label><input id="aaaPermitaccType0" class="col-xs-7"/></div>
				</div>
			</div>	
			<!-- G网 -->
			<div class="col-xs-12 col-md-6">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">G网</h5>
					</div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">MDN号</label><input id="aaaMDN1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">IMSI号码</label><input id="aaaIMSI1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">停复机状态</label><input id="aaaStatus1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">付费类型</label><input id="aaaType1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">漫游权限</label><input id="aaaMYPermissions1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">受限状态</label><input id="aaaBindOption1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">业务类型</label><input id="aaaBindNAI1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">VPDN域名</label><input id="aaaBindVPN1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">归属组</label><input id="aaaOwnerGroup1" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4">接入类型</label><input id="aaaGPermitaccType1" class="col-xs-7"/></div>
				</div>
			</div>	
		</div>
		<div class="row r1">
			<!-- 新C网 -->
			<div class="col-xs-12 col-md-6">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">新C网</h5>
					</div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">MDN号</label><input id="aaaMDN2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">IMSI号码</label><input id="aaaIMSI2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">停复机状态</label><input id="aaaStatus2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">付费类型</label><input id="aaaType2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">漫游权限</label><input id="aaaMYPermissions2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">受限状态</label><input id="aaaBindOption2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">业务类型</label><input id="aaaBindNAI2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">VPDN域名</label><input id="aaaBindVPN2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">归属组</label><input id="aaaOwnerGroup2" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4">接入类型</label><input id="" class="col-xs-7"/></div>
				</div>
			</div>	
			<!-- 新G网 -->
			<div class="col-xs-12 col-md-6">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">新G网</h5>
					</div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">MDN号</label><input id="aaaMDN3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">IMSI号码</label><input id="aaaIMSI3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">停复机状态</label><input id="aaaStatus3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">付费类型</label><input id="aaaType3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">漫游权限</label><input id="aaaMYPermissions3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">受限状态</label><input id="aaaBindOption3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">业务类型</label><input id="aaaBindNAI3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">VPDN域名</label><input id="aaaBindVPN3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 0 10px"><label class="control-label col-xs-4">归属组</label><input id="aaaOwnerGroup3" class="col-xs-7"/></div>
					<div class="row" style="padding:5px 0 5px 10px"><label class="control-label col-xs-4">接入类型</label><input id="" class="col-xs-7"/></div>
				</div>
			</div>	
		</div>
	</div>
</body>

</html>