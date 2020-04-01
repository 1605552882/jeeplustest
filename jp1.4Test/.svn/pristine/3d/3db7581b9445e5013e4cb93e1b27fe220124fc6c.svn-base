<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ENUM网元信息</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="enum.js" %>
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
			margin-top:30px;
			margin-left:1px;
			margin-right:1px;
		}
		
		.r2{
			margin-top:10px;
			margin-left:1px;
			margin-right:1px;
		}
		
		.gn1{
           height:100px;		   
        }
		
       .gn2{
           height:180px;		   
        }
        
		.form-control{
           height:25px;
           font-size:14px;
        }
		
		.base label{
			width:90px;
		}
		
		#enumModal {
         	position: absolute;
	        top: 40%;
	        left: 50%;
	        transform: translateX(-50%) translateY(-50%);
    	}
		
    </style>
</head>
<body>
	<div class="container-fluid"  style="background-color: white">
		 <form class="form-inline" id="enumForm">
			<!-- 查询框 -->
			<div class="row" style="border:1px solid #AAAAFF;margin-left:5px;margin-right:5px;margin-top:5px;padding-top:5px;padding-bottom:5px;">
				<div class="col-xs-12" style="margin-top:5px">
					<div class="form-group">
						<label for="number" class="control-label">手机号码</label>
						<input name="number" id="number" type="text" class="form-control input-sm" id="userNo" style="height:25px;font-size:14px;margin-left:10px">
					</div>
				</div>
				<div class="col-xs-12" style="padding-left:25px;margin-top:5px;margin-bottom:5px">
					<!-- 按钮 -->
					<div class="row" style="margin-top:8px">
						<a  id="search" class="btn btn-primary btn-sm" style="width:80px"><i class="fa fa-search"></i>  查询</a>
						<a  id="add" class="btn btn-success btn-sm" data-toggle="modal" data-target="#enumModal" style="width:80px;margin-left:5px"><i class="glyphicon glyphicon-plus"></i> 添加</a>
						<a  id="del" class="btn btn-danger btn-sm" style="width:80px;margin-left:5px"><i class="glyphicon glyphicon-trash"></i> 删除</a>
					</div>
				</div>
				
			</div>
			
			<!-- <div class="row r1" style="margin-top:30px">
				<div class="col-xs-12">
					<div class="form-group">
						<label for="CONTENT">ENUM记录</label>
						<input name="CONTENT" type="text" readonly style="height:25px;font-size:14px;background-color:white;width:500px;margin-left:10px" class="form-control input-sm" name="searchResult" id="searchResult">
					</div>
				</div>
			</div> -->
			
			<div class="row r1">
				<!-- ENUM -->
				<div class="col-xs-6">
					<div class="gn1 panel panel-primary">
						<div class="panel-heading">
							<h5 class="panel-title">ENUM</h5>
						</div>
						<div class="row" style="padding-top:15px">
							<div class="col-xs-12">
								<div class="form-group" style="padding-left:10px">
									<label for="CONTENT">ENUM记录</label>
									<input name="CONTENT" type="text" readonly style="height:25px;margin-left:20px;font-size:14px;background-color:white;width:420px;" class="form-control input-sm" name="searchResult" id="searchResult">
								</div>
							</div>
						</div>
					</div>	
				</div>
			</div>
			
			<div class="row r2">
				<!-- NPC -->
				<div class="col-xs-6">
					<div class="gn2 base panel panel-primary">
						<div class="panel-heading">
							<h5 class="panel-title">NPC</h5>
						</div>
						<div class="row" style="padding-top:10px">
							<div class="col-xs-6">
								<div class="form-group" style="padding-left:10px">
									<label for="npvalidtime" class="control-label">开户时间</label>
									<input name="npvalidtime" id="npvalidtime" type="text" class="form-control" style="width:150px">
								</div>
							</div>
							<div class="col-xs-6">
								<div class="form-group">
									<label for="portinnetid" class="control-label" style="text-align:right">携入网络</label>
									<input name="portinnetid" id="portinnetid" type="text" class="form-control" style="width:150px;margin-left:10px">
								</div>
							</div>
						</div>
					
						<div class="row" style="padding-top:10px">
							<div class="col-xs-6">
								<div class="form-group" style="padding-left:10px">
									<label for="portoutnetid" class="control-label">携出网络</label>
									<input name="portoutnetid" id="portoutnetid" type="text" class="form-control" style="width:150px" >
								</div>
							</div>
							<div class="col-xs-6">
								<div class="form-group">
									<label for="porttype" class="control-label" style="text-align:right">携带类型</label>
									<input name="porttype" id="porttype" type="text" class="form-control" style="width:150px;margin-left:10px">
								</div>
							</div>
						</div>
					
						<div class="row" style="padding-top:10px">
							<div class="col-xs-6">
								<div class="form-group" style="padding-left:10px">
									<label for="homenetid" class="control-label">号码拥有网络</label>
									<input name="homenetid" id="homenetid" type="text" class="form-control" style="width:150px">
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
	
	<!-- 添加ENUM弹窗 -->
	<div id="enumModal" class="modal inmodal bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
                <div class="modal-content" style="width:300px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">增加ENUM记录</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="num">手机号码</label>
                            <input type="text" class="form-control pull-right" id="num" name="num" style="margin-right:20px;width:170px;">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="form-group">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <!-- <button type="submit" class="btn btn-primary">确定</button> -->
                            <button id="addEnum" class="btn btn-primary">确定</button>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    

	

</body>
</html>