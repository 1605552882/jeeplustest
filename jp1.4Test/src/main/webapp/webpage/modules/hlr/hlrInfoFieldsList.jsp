<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>hlr信息管理管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="hlrInfoFieldsList.js" %>
	<style>
       .box{
			margin-top:10px;
			border:1px solid #AAAAFF;
		}
		
		.r1{
			padding:10px;
			margin-top:-5px;
		}
		
		.r2{
			padding:10px;
			margin-top:-10px;
		}
		
		.r3{
			padding:10px;
			margin-top:-10px;
		}
		
       .gn1{
           height:180px;		   
        }
		
		.gn2{
           height:140px;
        }
		
		.gn3{
           height:80px; 
        }
		.form-control{
           height:25px;
        }
		
		.box::before{
			content:attr(title);
			position:absolute;
			left:50px;
			transform:translateX(-50%);
			-webkit-transform:translate(-50%,-50%);
			padding:3px;
			background-color:#ECECFF;
			color:blue;
			font-size:15px;
			font-weight:bold
		}
		
		.sel{
			padding:0%;
		}
		
    </style>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	
	<div class="container-fluid"  style="background-color: #ECECFF">
	 <form class="form-inline" id="hlrForm">
		<!-- 查询框 -->
		<div class="row box" style="background-color: #ECECFF;margin-left:0px;margin-right:0px;padding-bottom:5px;padding-top:5px;margin-top:1px">
			<div class="col-xs-3">
				<div class="form-group">
					<label for="queryType" class="control-label">查询方式</label>
					<select class="form-control" id="queryType" style="height:30px;font-size:13px">
					  <option value="1">按手机号查询</option>
					  <option value="0">按IMSI号查询</option>
					</select>
				</div>
			</div>
			
			<div class="col-xs-3">
				<div class="form-group">
					<label for="userNo" class="control-label">手机号码</label>
					<input type="text" class="form-control input-sm" id="userNo">
				</div>
			</div>
			<div class="col-xs-12" style="padding-left:25px">
				<!-- 按钮 -->
				<div class="row" style="margin-top:8px">
					<a  id="search" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-search"></i>  查询</a>
					<a  id="update" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-refresh"></i> 修改</a>
					<a  id="reset" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-refresh"></i> CRM比对</a>
					<a  id="reset" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-refresh"></i> 批量操作</a>
				</div>
			</div>
			
		</div>
		
		<!-- 手机信息 -->
		<div class="row" style="margin-top:10px;">
			<div class="col-xs-3">
				<div class="form-group">
					<label for="searchResult">IMSI号</label>
					<input type="text" readonly style="background-color:white;" class="form-control input-sm input-sm" name="searchResult" id="searchResult">
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label for="province">省</label>
					<input type="text" readonly style="background-color:white;" class="form-control input-sm" name="province" id="province">
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label for="area">当前位置</label>
					<input type="text" readonly style="background-color:white;" class="form-control input-sm" name="area" id="area">
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label for="userStatus">手机状态</label>
					<select id="userStatus" name="userStatus" class="form-control sel" style="width:200px;">
					  <option value="" style="display: none"></option>
					  <option value="0">欠费</option>
					  <option value="1">用户为正常激活态</option>
					  <option value="2">无效ESN</option>
					  <option value="3">被盗手机</option>
					  <option value="4">复制手机</option>
					  <option value="5">未分配的电话号码</option>
					  <option value="6">不确定</option>
					  <option value="7">多重接入</option>
					  <option value="8">在这个MSC中不能使用</option>
					  <option value="9">缺少鉴权参数</option>
					  <option value="A">终端类型不正确</option>
					  <option value="B">用户去活或关机</option>
					  <option value="C">预开户状态，仅加载了鉴权资源数据</option>
					  <option value="D">已开户，但还没有进行初始位置登记</option>
					  <option value="E">用户成功完成了一次SSD更新</option>
					  <option value="F">业务转换无效</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="row r1">
			<!-- 转呼功能 -->
			<div class="col-xs-6 gn1 box" title="转呼功能" style="background-color: #ECECFF;width:48%;margin-right:1%">
				<div class="row" style="padding-top:20px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="withoutCondition" class="control-label" style="text-align:right;width:70px;">无条件传呼</label>
							<label class="checkbox-inline">
							  <input name="withoutCondition" type="checkbox" value="1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input name="withoutCondition" type="checkbox" value="2" class="zhActive"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="withoutConditionNo">呼转号码</label>
							<input name="withoutConditionNo" readOnly type="text" class="form-control input-sm">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="whenBusy" class="control-label" style="text-align:right;width:70px">遇忙传呼</label>
							<label class="checkbox-inline">
							  <input name="whenBusy" type="checkbox" value="1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input name="whenBusy" type="checkbox" value="2" class="zhActive"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="whenBusyNo">呼转号码</label>
							<input name="whenBusyNo" readOnly type="text" class="form-control input-sm">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="noAnswer" class="control-label" style="text-align:right;width:70px">无应答传呼</label>
							<label class="checkbox-inline">
							  <input name="noAnswer" type="checkbox" value="1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input name="noAnswer" type="checkbox" value="2" class="zhActive"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="noAnswerNo">呼转号码</label>
							<input name="noAnswerNo" readOnly type="text" class="form-control input-sm">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="defaultCall" class="control-label" style="text-align:right;width:70px">默认传呼</label>
							<label class="checkbox-inline">
							  <input name="defaultCall" type="checkbox" value="1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input name="defaultCall" type="checkbox" value="2" class="zhActive"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="defaultCallNo">呼转号码</label>
							<input name="defaultCallNo" readOnly type="text" class="form-control input-sm">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="cfmn" class="control-label" style="text-align:right;width:70px">通信助理</label>
							<label class="checkbox-inline">
							  <input name="cfmn" type="checkbox" value="1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input name="cfmn" type="checkbox" value="2"> 激活
							</label>
						</div>
					</div>
				</div>	
			</div>
			<!-- 短信功能 -->
			<div class="col-xs-3 gn1 box" title="短信功能" style="background-color: #ECECFF;margin-right:1%">
				<div class="row" style="padding-top:20px">
					<div class="col-xs-12">
						<div class="form-group">
								<label for="smsFunction" class="control-label">短信功能授权</label>
								<div class="radio">
								  <label>
									<input name="smsFunction" type="radio" value="0">
									不授权
								  </label>
								</div>	
								<div class="radio">
								  <label>
									<input name="smsFunction" type="radio" value="1">
									授权
								  </label>
								</div>
						</div>
					</div>
				</div>
			    <div class="row" style="padding-top:8px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="smsOriginalCall" class="control-label">短信功能始呼</label>
							<select name="smsOriginalCall" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">完全拒绝</option>
							  <option value="1">保留</option>
							  <option value="2">允许特殊情况</option>
							  <option value="3">完全允许</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:8px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="smsAnswerCall" class="control-label">短信功能终呼</label>
							<select name="smsAnswerCall" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">完全拒绝</option>
							  <option value="1">保留</option>
							  <option value="2">允许特殊情况</option>
							  <option value="3">完全允许</option>
							</select>
						</div>
					</div>
				</div>	
			</div>
			<!-- 其他参数 -->
			<div class="col-xs-3 gn1 box" title="其他参数" style="background-color: #ECECFF">
				<div class="row" style="padding-top:20px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="callLimit" class="control-label" style="text-align:right;width:60px">呼叫限制</label>
							<select name="callLimit" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">双向限制</option>
							  <option value="1">没有限制</option>
							  <option value="2">呼出限制</option>
							  <option value="3">呼入限制</option>
							</select>
						</div>
					</div>
				</div>	
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="betChain" class="control-label" style="text-align:right;width:60px">欠费锁</label>
							<select name="betChain" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">双向锁</option>
							  <option value="1">没有该锁</option>
							  <option value="2">呼入锁</option>
							  <option value="3">呼出锁</option>
							</select>
							
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="applyChain" class="control-label" style="text-align:right;width:60px">申请锁</label>
							<select name="applyChain" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">双向锁</option>
							  <option value="1">没有该锁</option>
							  <option value="2">呼入锁</option>
							  <option value="3">呼出锁</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="rabedChain" class="control-label" style="text-align:right;width:60px">被盗锁</label>
							<select name="rabedChain" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">双向锁</option>
							  <option value="1">没有该锁</option>
							  <option value="2">呼入锁</option>
							  <option value="3">呼出锁</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="copyChain" class="control-label" style="text-align:right;width:60px">复制锁</label>
							<select name="copyChain" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">双向锁</option>
							  <option value="1">没有该锁</option>
							  <option value="2">呼入锁</option>
							  <option value="3">呼出锁</option>
							</select>
						</div>
					</div>
				</div>	
			</div>
		
		</div>
		
		<div class="row r2">
			<!-- 基本功能 -->
			<div class="col-xs-3 gn2 box" title="基本功能" style="background-color: #ECECFF;width:23.5%;margin-right:1%">
				<div class="row" style="padding-top:20px">
					<div class="col-xs-12">
						<div class="form-group">
							<label class="checkbox-inline">
							   <input name="base1xFunction" type="checkbox" value="1"> 1x功能
							</label>
						</div>
					</div>
				</div>	
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label class="checkbox-inline">
							   <input name="multiCall" type="checkbox" value="1"> 三方通话
							</label>
						</div>
					</div>
				</div>	
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label class="checkbox-inline">
							   <input name="volte" type="checkbox" id="volte" value="1"> VoLTE用户
							</label>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location">智能网</label>
							<select name="netWork" class="form-control sel">
							  <option value="" style="display: none"></option>
							  <option value="0">普通用户</option>
							  <option value="1">预付费PPC</option>
							  <option value="2">省iVPN</option>
							  <option value="3">全国iVPN</option>
							  <option value="4">超级无绳/携号转网</option>
							  <option value="5">跨省/省内一卡双号</option>
							  <option value="6">非实名制停机</option>
							  <option value="7">VoLTE被叫锚定</option>
							</select>
						</div>
					</div>
				</div>	
				
			</div>
			
			<!-- 漫游权限 -->
			<div class="col-xs-3 gn2 box" title="漫游权限" style="background-color: #ECECFF;width:23.5%;margin-right:1%">
				<div class="row">
						<div class="form-group">
							<div class="radio col-xs-12"  style="padding-top:20px">
							  <label>
								<input type="radio" name="roamingPrivileges" id="roamingPrivileges1" value="0">
								无漫游权限
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="roamingPrivileges" id="roamingPrivileges2" value="1">
								省内漫游
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="roamingPrivileges" id="roamingPrivileges3" value="2">
								国内漫游
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="roamingPrivileges" id="roamingPrivileges4" value="3">
								国际漫游
							  </label>
							</div>
						</div>
				</div>	
			</div>
			
			<!-- 呼出权限 -->
			<div class="col-xs-3 gn2 box" title="呼出权限" style="background-color: #ECECFF;margin-right:1%">
				<div class="row">
						<div class="form-group">
							<div class="radio col-xs-12"  style="padding-top:20px">
							  <label>
								<input type="radio" name="callingPrivileges" id="callingPrivileges1" value="0">
								其他
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="callingPrivileges" id="callingPrivileges2" value="3">
								本地呼叫
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="callingPrivileges" id="callingPrivileges3" value="2">
								国内长途
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="callingPrivileges" id="callingPrivileges4" value="1">
								国际长途
							  </label>
							</div>
						</div>
				</div>
			</div>
			
			<!-- 彩铃功能 -->
			<div class="col-xs-3 gn2 box" title="彩铃功能" style="background-color: #ECECFF;">
				<div class="row">
					<div class="form-group">
						<div class="checkbox col-xs-12"  style="padding-top:20px">
						  <label>
							<input name="brtFunction" type="checkbox" value="1">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12"  style="padding-top:5px">
						  <label>
							<input name="brtFunction" type="checkbox" value="2">
							    激活
						  </label>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row r3">
			<!-- 呼叫等待 -->
			<div class="col-xs-3 gn3 box" title="呼叫等待" style="background-color: #ECECFF;width:23.5%;margin-right:1%">
				<div class="row">
					<div class="form-group">
						<div class="checkbox col-xs-12" style="padding-top:20px">
						  <label>
							<input name="callWaiting" type="checkbox" value="1">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12" style="padding-top:5px">
						  <label>
							<input name="callWaiting" type="checkbox" value="2">
							    激活
						  </label>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 呼叫保持 -->
			<div class="col-xs-3 gn3 box" title="呼叫保持" style="background-color: #ECECFF;width:23.5%;margin-right:1%">
				<div class="row">
					<div class="form-group">
						<div class="checkbox col-xs-12"  style="padding-top:20px">
						  <label>
							<input name="callKeeping" type="checkbox" value="2">
								开通
						  </label>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 免打扰功能 -->
			<div class="col-xs-3 gn3 box" title="免打扰功能" style="background-color: #ECECFF;margin-right:1%">
				<div class="row">
					<div class="form-group">
						<div class="checkbox col-xs-12"  style="padding-top:20px">
						  <label>
							<input name="noDisturb" type="checkbox" value="1">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12"  style="padding-top:5px">
						  <label>
							<input name="noDisturb" type="checkbox" value="2">
							    激活
						  </label>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 来电显示 -->
			<div class="col-xs-3 gn3 box" title="来电显示" style="background-color: #ECECFF;">
				<div class="row">
					<div class="form-group">
						<div class="checkbox col-xs-12"  style="padding-top:20px">
						  <label>
							<input name="callDisplay1" type="checkbox" value="1">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12"  style="padding-top:5px">
						  <label>
							<input name="callDisplay2" type="checkbox" value="1">
							    激活
						  </label>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</form> 
 </div>
	
	
 
 </div>
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