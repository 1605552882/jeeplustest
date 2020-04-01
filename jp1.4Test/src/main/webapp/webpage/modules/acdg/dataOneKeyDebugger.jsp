<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>移动宽带一键诊断</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="dataOneKeyDebugger.js" %>

	<style>
        .normal-div{
        	border:1px solid green;
        	border-radius:5px 5px 5px 5px;
        }
        
        .error-div{
        	border:1px solid red;
        	border-radius:5px 5px 5px 5px;
        }
           
       .panel-heading{
			height:15px;
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
        .wrapper-content{
        	background-color: white;
        }
        #searchForm{
        	border:1px solid #AAAAFF;
        	padding-bottom:5px;
        	padding-top:5px;
        	margin:5px 5px 5px 5px;
        }
		.numberDiv{
			margin-bottom:20px;
		}
		.buttonDiv{
			margin:2px 0px 0px 5px;
		}
		#msg1,#msg2{
			color:green;
		}
		#msg3{
			color:red;
		}
    </style>

</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="">
	<div class="">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
		<form id="searchForm" class="row form-inline">

			<div class="col-xs-12 col-md-4 numberDiv">
				<label class="" for="number">电话号码：</label>
				<input maxlength="32"  class="form-control" id="number" />
			</div>
			
			 <div class="col-xs-12 col-sm-12 col-md-3 buttonDiv">
			  <a  id="search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 开始诊断</a>
			  <a  id="stop" class="btn btn-danger btn-sm" disabled="disabled"><i class="fa fa-refresh"></i> 停止诊断</a>
	    	 </div>
	    	 
	    	 <!-- 进度条 -->
			<div class="progress progress-striped active col-md-9" hidden="hidden">
				<div id="progress" class="progress-bar progress-bar-success" role="progressbar"
					 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
					 style="width: 0%;">
					<span class="sr-only"></span>
				</div>
			</div>
			
			 <div id="msg1">进度：0%</div>
			 <div id="msg2" style="color:green" class="col-xs-12" hidden="hidden">检测完成</div>
			 <div id="msg3" style="color:red" class="col-xs-12" hidden="hidden">检测中断</div>
		</form>
		
		<div class="row r1 form-inline">
			<!-- 正常项目 -->
			<div class="col-xs-4">
				<div class="panel panel-primary normal-div">
					<div class="panel-heading" style="background-color:green;border-color:green">
						<h5 class="panel-title">正常项目</h5>
					</div>
					<div id="panel-content1" style="height:300px"></div>
				</div>
			</div>
			<!-- 异常项目 -->
			<div class="col-xs-4">
				<div class="panel panel-primary error-div">
					<div class="panel-heading" style="background-color:red;border-color:red">
						<h5 class="panel-title">异常项目</h5>
					</div>
					<div id="panel-content2" style="height:300px"></div>
				</div>
			</div>
			<!-- 项目进度 -->
			<div class="col-xs-4 text-center">
				<div class="col-xs-6"><canvas id="canvas1"></canvas><div>AAA</div></div>
				<div class="col-xs-6"><canvas id="canvas2"></canvas><div>ANAAA</div></div>
				<div class="col-xs-6"><canvas id="canvas3"></canvas><div>HSS</div></div>
				<div class="col-xs-6"><canvas id="canvas4"></canvas><div>软拨测</div></div>
			</div>
		</div>

	</div>
	</div>
	</div>
</body>

</html>