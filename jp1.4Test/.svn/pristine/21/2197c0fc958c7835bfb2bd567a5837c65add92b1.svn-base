<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>我的测试管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%-- <%@include file="myTestList.js" %> --%>
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
		
    </style>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	
	<div class="container-fluid"  style="background-color: #ECECFF">
	 <form class="form-inline">
		<!-- 查询框 -->
		<div class="row box" style="background-color: #ECECFF;margin-left:0px;margin-right:0px;padding-bottom:5px;padding-top:5px">
			<div class="col-xs-3">
				<div class="form-group">
					<label for="searchType" class="control-label">查询方式</label>
					<select class="form-control" style="height:30px;font-size:13px">
					  <option>按手机号查询</option>
					  <option>按IMSI号查询</option>
					</select>
				</div>
			</div>
			
			<div class="col-xs-3">
				<div class="form-group">
					<label for="phoneNum" class="control-label">手机号码</label>
					<input type="text" class="form-control input-sm" id="phoneNum">
				</div>
			</div>
			<div class="col-xs-12" style="padding-left:25px">
				<!-- 按钮 -->
				<div class="row" style="margin-top:8px">
					<a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm" style="width:80px"><i class="fa fa-search"></i>  查询</a>
					<a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" style="width:80px"><i class="fa fa-refresh"></i> 修改</a>
					<a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" style="width:80px"><i class="fa fa-refresh"></i> CRM比对</a>
					<a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" style="width:80px"><i class="fa fa-refresh"></i> 批量操作</a>
				</div>
			</div>
			
		</div>
		
		<!-- 手机信息 -->
		<div class="row" style="margin-top:10px;">
			<div class="col-xs-3">
				<div class="form-group">
					<label for="imsi">IMSI号</label>
					<input type="text" class="form-control input-sm input-sm"" id="imsi">
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label for="province">省</label>
					<input type="text" class="form-control input-sm" id="province">
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label for="location">当前位置</label>
					<input type="text" class="form-control input-sm" id="location">
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label for="phoneState">手机状态</label>
					<input type="text" class="form-control input-sm" id="phoneState">
				</div>
			</div>
		</div>
		
		<div class="row r1">
			<!-- 转呼功能 -->
			<div class="col-xs-6 gn1 box" title="转呼功能" style="background-color: #ECECFF;width:48%;margin-right:1%">
				<div class="row" style="padding-top:20px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:70px;">无条件传呼</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location">呼转号码</label>
							<input type="text" class="form-control input-sm" id="location">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:70px">遇忙传呼</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location">呼转号码</label>
							<input type="text" class="form-control input-sm" id="location">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:70px">无应答传呼</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location">呼转号码</label>
							<input type="text" class="form-control input-sm" id="location">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:70px">默认传呼</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 激活
							</label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location">呼转号码</label>
							<input type="text" class="form-control input-sm" id="location">
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:70px">通信助理</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 授权
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> 激活
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
								<label for="location" class="control-label">短信功能授权</label>
								<div class="radio">
								  <label>
									<input type="radio" name="dxsq" id="roamType1" value="option1">
									不授权
								  </label>
								</div>	
								<div class="radio">
								  <label>
									<input type="radio" name="dxsq" id="roamType2" value="option2">
									授权
								  </label>
								</div>
						</div>
					</div>
				</div>
			    <div class="row" style="padding-top:8px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location" class="control-label">短信功能始呼</label>
							<select class="form-control">
							  <option></option>
							  <option>完全拒绝</option>
							  <option>保留</option>
							  <option>允许特殊情况</option>
							  <option>完全允许</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:8px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location" class="control-label">短信功能终呼</label>
							<select class="form-control">
							  <option></option>
							  <option>完全拒绝</option>
							  <option>保留</option>
							  <option>允许特殊情况</option>
							  <option>完全允许</option>
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
							<label for="location" class="control-label" style="text-align:right;width:60px">呼叫限制</label>
							<select class="form-control">
							  <option></option>
							  <option>双向限制</option>
							  <option>没有限制</option>
							  <option>呼出限制</option>
							  <option>呼入限制</option>
							</select>
						</div>
					</div>
				</div>	
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:60px">欠费锁</label>
							<select class="form-control">
							  <option></option>
							  <option>双向锁</option>
							  <option>没有该锁</option>
							  <option>呼入锁</option>
							  <option>呼出锁</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:60px">申请锁</label>
							<select class="form-control">
							  <option></option>
							  <option>双向锁</option>
							  <option>没有该锁</option>
							  <option>呼入锁</option>
							  <option>呼出锁</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:60px">被盗锁</label>
							<select class="form-control">
							  <option></option>
							  <option>双向锁</option>
							  <option>没有该锁</option>
							  <option>呼入锁</option>
							  <option>呼出锁</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location" class="control-label" style="text-align:right;width:60px">复制锁</label>
							<select class="form-control">
							  <option></option>
							  <option>双向锁</option>
							  <option>没有该锁</option>
							  <option>呼入锁</option>
							  <option>呼出锁</option>
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
							   <input type="checkbox" id="inlineCheckbox1" value="option1"> 1x功能
							</label>
						</div>
					</div>
				</div>	
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label class="checkbox-inline">
							   <input type="checkbox" id="inlineCheckbox1" value="option1"> 三方通话
							</label>
						</div>
					</div>
				</div>	
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label class="checkbox-inline">
							   <input type="checkbox" id="inlineCheckbox1" value="option1"> VoLTE用户
							</label>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top:5px">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="location">智能网</label>
							<select class="form-control">
							  <option></option>
							  <option>普通用户</option>
							  <option>预付费PPC</option>
							  <option>省iVPN</option>
							  <option>全国iVPN</option>
							  <option>超级无绳/携号转网</option>
							  <option>跨省/省内一卡双号</option>
							  <option>非实名制停机</option>
							  <option>VoLTE被叫锚定</option>
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
								<input type="radio" name="roamType" id="roamType1" value="option1">
								无漫游权限
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="roamType" id="roamType2" value="option2">
								省内漫游
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="roamType" id="roamType3" value="option3">
								国内漫游
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="roamType" id="roamType4" value="option3">
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
								<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1">
								其他
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
								本地呼叫
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">
								国内长途
							  </label>
							</div>
							<div class="radio col-xs-12"  style="padding-top:5px">
							  <label>
								<input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">
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
							<input type="checkbox" value="">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12"  style="padding-top:5px">
						  <label>
							<input type="checkbox" value="">
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
							<input type="checkbox" value="">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12" style="padding-top:5px">
						  <label>
							<input type="checkbox" value="">
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
							<input type="checkbox" value="">
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
							<input type="checkbox" value="">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12"  style="padding-top:5px">
						  <label>
							<input type="checkbox" value="">
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
							<input type="checkbox" value="">
								授权
						  </label>
						</div>
						<div class="checkbox col-xs-12"  style="padding-top:5px">
						  <label>
							<input type="checkbox" value="">
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
</body>
</html>