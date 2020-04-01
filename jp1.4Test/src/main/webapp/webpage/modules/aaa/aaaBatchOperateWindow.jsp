<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA批量操作</title>
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
						<label class="col-xs-12"><input type="radio" name="qx"/>批量查询(MDN)</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>批量查询(IMSI)</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>开户</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>销卡（MDN或IMSI）</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>停复机</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>卡业务受限加解锁</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>卡绑定VPDN域名</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>卡绑定业务</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>更改卡归属组</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>更改卡付费类型</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>加入黑名单</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>解除黑名单</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>更改漫游权限</label>
						<label class="col-xs-12"><input type="radio" name="qx"/>更改接入类型</label>
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