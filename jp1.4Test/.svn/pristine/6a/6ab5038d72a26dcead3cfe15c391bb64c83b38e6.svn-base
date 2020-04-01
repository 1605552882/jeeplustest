<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>hlr信息管理管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="ctogInfo.js" %>
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
			height:470px;
		}
		
		
       .gn1{
           height:325px;		   
        }
        
       .gn2{
           height:120px;		   
        }
        
		.form-control{
           height:25px;
           font-size:14px;
        }
		
		.sel{
			padding:0%;
		}
		
		.gn1 label{
			width:145px;
			margin-left:10px;
		}
		
		.gn2 label{
			width:120px;
			margin-left:10px;
		}
		
		.r1 .form-control{
			background-color:white;
			readonly:readonly;
		}
		
		th {
			text-align:center;
		}
		td {
			text-align:center;
		}
		
    </style>
</head>
<body>
	<div class="container-fluid"  style="background-color: white">
	 <form class="form-inline" id="ctogForm">
		<!-- 查询框 -->
		<div class="row" style="border:1px solid #AAAAFF;margin-left:5px;margin-right:5px;margin-top:5px;padding-top:5px;padding-bottom:5px;">
			<div class="col-xs-3">
				<div class="form-group">
					<label for="searchType" class="control-label">查询方式</label>
					<select class="form-control" name="searchType" id="searchType" style="height:30px;font-size:13px">
					  <option value="0">按手机号码</option>
					  <option value="1">按C网IMSI号码</option>
					  <option value="2">按G网IMSI号码</option>
					</select>
				</div>
			</div>
			
			<div class="col-xs-6">
				<div class="form-group">
					<label for="userNo" class="control-label" style="width:90px;text-align:right;">手机号码</label>
					<input type="text" class="form-control input-sm" id="userNo">
				</div>
			</div>
			<div class="col-xs-12" style="padding-left:25px">
				<!-- 按钮 -->
				<div class="row" style="margin-top:8px">
					<a  id="search" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-search"></i>  查询</a>
					<a  id="update" class="btn btn-warning btn-sm"><i class="glyphicon glyphicon-edit"></i> 修改漫游权限</a>
					<a  id="delUserData" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i> 删除用户数据</a>
					<a  id="delUserLoc" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i> 删除用户位置信息</a>
					<a  id="hlrCompare" class="btn btn-success btn-sm"><i class="glyphicon glyphicon-asterisk"></i> HLR比对</a>
				</div>
			</div>
			
		</div>
		
		<div class="row r1">
			<!-- 用户基本信息 -->
			<div class="col-xs-4">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">用户基本信息</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="isdn">用户电话号码</label>
								<input name="isdn"  id="isdn" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="gimsi">G网IMSI号码</label>
								<input name="gimsi" id="gimsi" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="imsi">C网IMSI号码</label>
								<input name="imsi" id="imsi" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="gdualImsi">LTE-IMSI</label>
								<input name="gdualImsi" id="gdualImsi" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="gimsiType">用户卡类型</label>
								<input name="gimsiType" id="gimsiType" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="outerRoamAuth">跨网漫游权限</label>
								<select name="outerRoamAuth" class="form-control sel" style="width:192px" id="outerRoamAuth">
								  <option value="0">未开通</option>
								  <option value="1">仅国际漫游</option>
								  <option value="2">仅国内漫游</option>
								  <option value="3">国内+国际漫游</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="smsdpf">短消息投递挂起标志</label>
								<input name="smsdpf" id="smsdpf" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="esn">电子序列号</label>
								<input name="esn" id="esn" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="nam">网络接入模式</label>
								<input name="nam" id="nam" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
				</div>	
			</div>
				
			<!-- 预付费属性 -->
			<div class="col-xs-4">
				<div class="gn2 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">预付费属性</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="ocsitpl">预付费标识</label>
								<input name="ocsitpl" id="ocsitpl" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="charge">PS域计费特性</label>
								<input name="charge" id="charge" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 用户位置信息 -->
			<div class="col-xs-4" style="background-color: while">
				<div class="gn2 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">用户位置信息</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="mscNumber">登陆MSC号码</label>
								<input name="mscNumber" id="mscNumber" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="vlrNumber">登陆VLR号码</label>
								<input name="vlrNumber" id="vlrNumber" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
				</div>
			</div>
		
		</div>
		
	</form> 
	
		<!-- 修改漫游权限弹窗 -->
		<!-- <div id="outerRoamAuthModal" class="modal inmodal bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	                <div class="modal-content" style="width:300px;">
	                    <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                            <span aria-hidden="true">×</span></button>
	                        <h4 class="modal-title">修改跨网漫游权</h4>
	                    </div>
	                    <div class="modal-body">
	                        <div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="outerRoamAuth1">跨网漫游权限</label>
										<select name="outerRoamAuth1" class="form-control sel" style="width:120px" id="outerRoamAuth1">
										  <option value="0">未开通</option>
										  <option value="1">仅国际漫游</option>
										  <option value="2">仅国内漫游</option>
										  <option value="3">国内+国际漫游</option>
										</select>
									</div>
								</div>
							</div>
	                    </div>
	                    <div class="modal-footer">
	                        <div class="form-group">
	                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                            <button id="updateAuth" class="btn btn-primary">确定</button>
	                        </div>
	                    </div>
	                </div>
	        </div>
	    </div> -->
	    
	    <!-- 修改跨网漫游权限弹窗 -->
		<div id="outerRoamAuthModal" class="modal inmodal bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	                <div class="modal-content" style="width:300px;">
	                    <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                            <span aria-hidden="true">×</span></button>
	                        <h4 class="modal-title">修改跨网漫游权限</h4>
	                    </div>
	                    <div class="modal-body">
	                        <div class="form-group">
	                            <label for="outerRoamAuth1">跨网漫游权限</label>
								<select id="outerRoamAuth1" name="outerRoamAuth1" class="form-control sel pull-right" style="margin-right:20px;width:150px;">
								  <option value="0">未开通</option>
								  <option value="1">仅国际漫游</option>
								  <option value="2">仅国内漫游</option>
								  <option value="3">国内+国际漫游</option>
								</select>
	                        </div>
	                    </div>
	                    <div class="modal-footer">
	                        <div class="form-group">
	                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                            <button id="updateAuth" class="btn btn-primary">确定</button>
	                        </div>
	                    </div>
	                </div>
	        </div>
	    </div>
	    
	    <!-- HLR比对弹窗 -->
		<div id="hlrCompareModal" class="modal inmodal bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	                <div class="modal-content" style="width:600px;">
	                    <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                            <span aria-hidden="true">×</span></button>
	                        <h4 class="modal-title">HLR比对结果</h4>
	                    </div>
	                    <div class="modal-body">
	                    	<table id="hlrCompareTable" data-height="200" ></table>
	                    </div>
	                    <div class="modal-footer">
	                        <div class="form-group" style="text-align:center"">
	                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                        </div>
	                    </div>
	                </div>
	        </div>
	    </div>
	
 </div>

</body>
</html>