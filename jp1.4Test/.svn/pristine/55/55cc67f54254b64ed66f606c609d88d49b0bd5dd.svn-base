<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ANAAA批量操作</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
</head>
<body class="bg-white">

		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-35">
						<label style="color:red">批量操作号码,批量操作数据不能超过200条！</label>
						<textarea class="form-control" rows="18">
						</textarea>
					</td>
					<td class="width-15 active">
						<label>批量操作通话权限</label>
						<label><input type="radio" name="qx"/>批量查询</label>
						<label><input type="radio" name="qx"/>根据IMSI销卡</label>
					</td>
		   			<td class="width-35">
		   				<label>批量结果</label>
						<textarea class="form-control" rows="18">
						</textarea>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form>
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div style="text-align:center">
		  <a  id="search" class="btn btn-primary"><i class="fa fa-search"></i> 导入文本</a>
		  <a  id="reset" class="btn btn-danger" ><i class="fa fa-refresh"></i> 清空</a>
		  <a  id="search" class="btn btn-primary"><i class="fa fa-search"></i> 开始批量操作</a>
		  <a  id="reset" class="btn btn-danger" ><i class="fa fa-refresh"></i> 结果导出</a>
		 </div>
    </div>
</body>
</html>