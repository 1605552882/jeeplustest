<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>短信平台</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="smsInfo.js" %>
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
           height:270px;		   
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
	 <form class="form-inline" id="smsForm">
		<!-- 查询框 -->
		<div class="row" style="border:1px solid #AAAAFF;margin:5px 5px 5px 5px;padding-top:5px;padding-bottom:5px;">
			<div class="row">
				<div class="col-xs-12">
					<div class="form-group" style="padding-left:10px">
						<label for="userNumber" class="control-label">用户号码</label>
						<input type="text" class="form-control input-sm" name="userNumber" id="userNumber">
					</div>
				</div>
			</div>
			<div class="row" style="margin:10px 5px 2px 5px;padding-left:5px">
				<!-- 按钮 -->
				<a  id="search" class="btn btn-primary btn-sm col-xs-2 col-md-1"><i class="fa fa-search"></i>  用户查询</a>
				<div class="col-md-4 col-xs-6" style="margin-left:5px;padding:0">
					<button data-toggle="dropdown" class="btn btn-success btn-sm dropdown-toggle col-xs-6">黑名单 <i class="glyphicon glyphicon-triangle-bottom"></i></button>
					<ul class="dropdown-menu">
						<li><a id="numBlackListQryWindow" href="#">通过号码查询黑名单</a></li>
						<li><a id="numBlackListDelWindow" href="#">通过号码解除黑名单</a></li>
						<li><a id="areaCodeBlackListWindow" href="#">通过区号时间查询黑名单</a></li>
			 		</ul>
			 	</div>
			
			</div>
			
		</div>
		
		<div class="row r1">
			<!-- 查询结果 -->
			<div class="col-xs-4">
				<div class="gn1 panel panel-primary">
					<div class="panel-heading">
						<h5 class="panel-title">查询结果</h5>
					</div>
					<div class="row" style="padding-top:10px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelMinNo">MIN号</label>
								<input name="userQueryFormPanelMinNo"  id="userQueryFormPanelMinNo" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelUserPoriperty">用户优先级</label>
								<input name="userQueryFormPanelUserPoriperty" id="userQueryFormPanelUserPoriperty" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelLanguage">语言</label>
								<input name="userQueryFormPanelLanguage" id="userQueryFormPanelLanguage" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelAuthData">鉴权数据</label>
								<input name="userQueryFormPanelAuthData" id="userQueryFormPanelAuthData" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelOpenTime">开户时间</label>
								<input name="userQueryFormPanelOpenTime" id="userQueryFormPanelOpenTime" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelPayType">付费方式</label>
								<input name="userQueryFormPanelPayType" id="userQueryFormPanelPayType" type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row" style="padding-top:5px">
						<div class="col-xs-12">
							<div class="form-group">
								<label for="userQueryFormPanelOSCNo">OCS号码</label>
								<input name="userQueryFormPanelOSCNo" id="userQueryFormPanelOSCNo" type="text" class="form-control input-sm">
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