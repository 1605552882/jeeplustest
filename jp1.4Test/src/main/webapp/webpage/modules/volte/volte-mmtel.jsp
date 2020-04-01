<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>VoLTE-MMTEL信息管理管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="volte-mmtel.js" %>
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
		
		.r2{
			margin-top:-20px;
			margin-left:1px;
			margin-right:1px;
			height:265px;
		}
		
       .gn1{
           height:220px;		   
        }
		
		.gn2{
           height:170px;
        }
		
        
		.form-control{
           height:25px;
           font-size:14px;
        }
		
		.sel{
			padding:0%;
		}
		
		.base label{
			width:90px;
		}
		
    </style>
</head>
<body>
	<div class="container-fluid"  style="background-color: white">
	 <form class="form-inline" id="volteForm">
		<!-- 查询框 -->
		<div class="row" style="border:1px solid #AAAAFF;margin-left:5px;margin-right:5px;margin-top:5px;padding-top:5px;padding-bottom:5px;">
			
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
					<a  id="update" class="btn btn-danger btn-sm" style="width:80px"><i class="glyphicon glyphicon-edit"></i> 修改</a>
					<a  id="del" class="btn btn-warning btn-sm"><i class="glyphicon glyphicon-trash"></i> 删除VoLTE-MMTEL用户</a>
				</div>
			</div>
			
		</div>
		
		<div class="row r1">
			<!-- 基本信息 -->
			<div class="col-xs-6">
				<div class="gn1 base panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">基本信息</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="MSISDN" class="control-label">手机号码</label>
								<input name="MSISDN" id="MSISDN" type="text" class="form-control" style="width:150px">
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="CALLOUT" class="control-label">呼出停机标识</label>
								<select name="CALLOUT" id="CALLOUT" class="form-control sel" style="width:150px;">
								  <option value="" style="display: none"></option>
								  <option value="0">正常</option>
								  <option value="1">停机</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="AREA_NO" class="control-label">归属地区</label>
								<input name="AREA_NO" id="AREA_NO" type="text" class="form-control" style="width:150px" >
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="CALLIN" class="control-label">呼入停机标识</label>
								<select name="CALLIN" id="CALLIN" class="form-control sel" style="width:150px" >
								  <option value="" style="display: none"></option>
								  <option value="0">正常</option>
								  <option value="1">停机</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="REG_NO" class="control-label">省号标识</label>
								<input name="REG_NO" id="REG_NO" type="text" class="form-control" style="width:150px">
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="limCalOut_act1" class="control-label">呼出限制</label>
								<select name="limCalOut_act1" class="form-control sel" style="width:150px" id="limCalOut_act1">
								  <option value="" style="display: none"></option>
								  <option value="0">不限制呼出</option>
								  <option value="1">限制所有呼出</option>
								  <option value="2">限制国际呼出</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="CallIn_act" class="control-label">欠费呼入标识</label>
								<select name="CallIn_act" class="form-control sel" style="width:150px" id="CallIn_act">
								  <option value="" style="display: none"></option>
								  <option value="0">正常</option>
								  <option value="1">欠费</option>
								</select>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="limCalIn_act1" class="control-label">呼入限制</label>
								<select name="limCalIn_act1" class="form-control sel" style="width:150px" id="limCalIn_act1">
								  <option value="" style="display: none"></option>
								  <option value="0">不限制呼入</option>
								  <option value="1">限制所有呼入</option>
								  <option value="2">限制漫游时所有呼入</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="arre_out" class="control-label">欠费呼出标识</label>
								<select name="arre_out" class="form-control sel" style="width:150px" id="arre_out">
								  <option value="" style="display: none"></option>
								  <option value="0">正常</option>
								  <option value="1">欠费</option>
								</select>
							</div>
						</div>
					</div>
					
				</div>	
			</div>
			
			<!-- 呼转补充业务 -->
			<div class="col-xs-6">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">呼转补充业务</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="unCondConv" class="control-label" style="text-align:right;width:70px;">无条件前转</label>
								<label class="checkbox-inline" style="margin-left:10px">
								  <input name="unCondConv_reg" type="checkbox" value="1"> 授权
								</label>
								<label class="checkbox-inline">
								  <input name="unCondConv_act" type="checkbox" value="2" class="zhActive"> 激活
								</label>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="unCondConv_no">呼转号码</label>
								<input name="unCondConv_no" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="busyConv" class="control-label" style="text-align:right;width:70px">遇忙前转</label>
								<label class="checkbox-inline" style="margin-left:10px">
								  <input name="busyConv_reg" type="checkbox" value="1"> 授权
								</label>
								<label class="checkbox-inline">
								  <input name="busyConv_act" type="checkbox" value="2" class="zhActive"> 激活
								</label>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="busyConv_no">呼转号码</label>
								<input name="busyConv_no" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="unTouchConv" class="control-label" style="text-align:right;width:70px">不可及前转</label>
								<label class="checkbox-inline" style="margin-left:10px">
								  <input name="unTouchConv_reg" type="checkbox" value="1"> 授权
								</label>
								<label class="checkbox-inline">
								  <input name="unTouchConv_act" type="checkbox" value="2" class="zhActive"> 激活
								</label>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="unTouchConv_no">呼转号码</label>
								<input name="unTouchConv_no" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="unRespConv" class="control-label" style="text-align:right;width:70px">无应答前转</label>
								<label class="checkbox-inline" style="margin-left:10px">
								  <input name="unRespConv_reg" type="checkbox" value="1"> 授权
								</label>
								<label class="checkbox-inline">
								  <input name="unRespConv_act" type="checkbox" value="2" class="zhActive"> 激活
								</label>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="unRespConv_no">呼转号码</label>
								<input name="unRespConv_no" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
				</div>	
			</div>
				
		</div>
		
		<!-- 业务信息 -->
		<div class="row r2">
			<!-- 基本补充业务 -->
			<div class="col-xs-3">
				<div class="gn2 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">基本补充业务</h5>
					</div>
					<div class="row" style="padding-top:10px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="callFlag_reg" class="control-label" style="width:90px">主叫标识显示</label>
								<label class="checkbox-inline" style="margin-left:10px">
								   <input name="callFlag_reg"  type="checkbox" value="1"> 登记
								</label>
							</div>
						</div>
					</div>	
					<div class="row" style="padding-top:5px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="callWait_reg" class="control-label" style="width:90px">呼叫等待</label>
								<label class="checkbox-inline" style="margin-left:10px">
								   <input name="callWait_reg"  type="checkbox" value="1"> 登记
								</label>
							</div>
						</div>
					</div>	
					<div class="row" style="padding-top:5px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="callMany_reg" class="control-label" style="width:90px">多方通话</label>
								<label class="checkbox-inline" style="margin-left:10px">
								   <input name="callMany_reg" type="checkbox" value="1"> 登记
								</label>
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="callAss_reg" class="control-label" style="width:90px">通信助理</label>
								<label class="checkbox-inline" style="margin-left:10px">
								   <input name="callAss_reg" type="checkbox" value="1"> 登记
								</label>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
			<!-- 一号通(一号多终端业务) -->
			<div class="col-xs-5">
				<div class="gn2 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">一号通(一号多终端业务)</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="callOne_reg" class="control-label" style="text-align:right;width:70px;">主叫一号通</label>
								<label class="checkbox-inline" style="margin-left:10px">
								  <input name="callOne_reg" type="checkbox" value="1"> 登记
								</label>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group" style="margin-left:-10px;">
								<label for="callOne_no">主号号码</label>
								<input name="callOne_no" readOnly type="text" class="form-control input-sm" style="width:150px">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-6">
							<div class="form-group" style="padding-left:10px">
								<label for="calledOne_reg" class="control-label" style="text-align:right;width:70px">被叫一号通</label>
								<label class="checkbox-inline" style="margin-left:10px">
								  <input name="calledOne_reg" type="checkbox" value="1"> 登记
								</label>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group" style="margin-left:-10px;">
								<label for="calledOne_no">副号号码</label>
								<input name="calledOne_no" readOnly type="text" class="form-control input-sm" style="width:150px">
							</div>
						</div>
					</div>
				</div>	
			</div>
			
			<!-- 漫游地(SCSCF) -->
			<div class="col-xs-4">
				<div class="gn2 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">漫游地(SCSCF)</h5>
					</div>
					<div class="row" style="padding-top:10px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="reg_loc" class="control-label" style="width:90px">漫游地</label>
								<input name="reg_loc" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>	
					<div class="row" style="padding-top:5px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="actscsc" class="control-label" style="width:90px">注册SCSCF</label>
								<input name="actscsc" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>	
					<div class="row" style="padding-top:5px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="reg_PSBC" class="control-label" style="width:90px">注册PSBC</label>
								<input name="reg_PSBC" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px;padding-left:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="tcpwl" class="control-label" style="width:90px">网络类型</label>
								<input name="tcpwl" readOnly type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		
	</form> 
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