<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA软拨测</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>

	<style type="text/css">
		#documentarydetailsTable {
			 table-layout:fixed; 
		}
		 th {background-color: #99CCFF;}
	</style>
	<script>
		//1.自定义校验规则
		$.validator.addMethod(
		    //imsi规则
		    "checkImsi",
		    function(value,element,params){
		    	var reg = /^(((46003)|(20404)|(45404)|(45403))[0-9]{10})$/;
			   	return reg.test(value);
		    }
		);
		
		$(document).ready(function() {
			var validateForm;
			//提交
			$("#search").click(function(){
				//清空之前的结果
				$("#result").val("");
				if(validateForm.form()){
					jp.loading();
					$("#inputForm").submit();
				  }
			});
			//重置
			$("#reset").click(function(){
				//清空
				$("#inputForm select,#inputForm input,#result").val("");
				//恢复隐藏内容
				$("#acctPort").val("1813");
				$("#authPort").val("1812");
				//清除错误信息
				$("#inputForm select,#inputForm input").next().remove();
			});
			//修改检验
			$("#sctType").blur(function(){
				validateForm.form();
			});
			validateForm = $("#inputForm").validate({
				//2.使用自定义规则
				rules:{
					"sctType":{
						"required":true
					},
					"nai":{
						"required":true
					},
					"ipAddress":{
						"required":true
					},
					"passwd":{
						"required":true
					},
					"nasPortType":{
						"required":true
					},
					"nasIpAddress":{
						"required":true
					},
					"nasIpAddress":{
						"required":true
					},
					"callingStationId":{
						"required":true,
		                "checkImsi":true
					}
				},
				//3.自定义规则显示错误信息
				messages:{
					"sctType":{
						"required":"请选择软拨测类型",
					},
					"nai":{
						"required":"请输入业务用户名",
					},
					"ipAddress":{
						"required":"请选择服务器IP",
					},
					"passwd":{
						"required":"请输入业务密码",
					},
					"nasPortType":{
						"required":"请选择业务类型",
					},
					"nasIpAddress":{
						"required":"请选择PDSN设备IP地址",
					},
					"callingStationId":{
						"required":"IMSI号码不能为空",
		                "checkImsi":"请填写正确的IMSI号码"
					}
				},
				submitHandler: function(form){
					jp.post("${ctx}/aaa/aaa/getSoftwareCallTestSearchResult",$('#inputForm').serialize(),function(data){
						if(data.success){
							jp.success("查询成功");
							$("#result").val(data.msg);
	                    }else{
	                		var errDetail ="<font color='red'>"+data.msg+"</font>";
		                	top.layer.alert(errDetail , {
		                		  icon: 2,
		                		  area:['auto','auto'],
		                		  title:"请求出错"
		                	})
	                    }
					})
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					}else {
						error.insertAfter(element);
					}
				}
			});
			
			//初始化
			init();
			function init(){
				var rbcTypeStore = getStoreByType("rbclx");
				var rbcIPAddressStore = getStoreByType("rbcfwqip");
				var rbcPortTypeStore = getStoreByType("rbcdklx");
				var rbcPDSNIPAddress = getStoreByType("rbcpdsnsbip");
				for(var i=0; i<rbcTypeStore.codingList.length; i++){
					$("#sctType").append("<option value="+rbcTypeStore.codingList[i].coding+">"+rbcTypeStore.codingList[i].chineseDescription+"</option>");
				}
				for(var i=0; i<rbcIPAddressStore.codingList.length; i++){
					$("#ipAddress").append("<option value="+rbcIPAddressStore.codingList[i].characterValue+">"+rbcIPAddressStore.codingList[i].chineseDescription+"</option>");
				}
				for(var i=0; i<rbcPortTypeStore.codingList.length; i++){
					$("#nasPortType").append("<option value="+rbcPortTypeStore.codingList[i].characterValue+">"+rbcPortTypeStore.codingList[i].chineseDescription+"</option>");
				}
				for(var i=0; i<rbcPDSNIPAddress.codingList.length; i++){
					$("#nasIpAddress").append("<option value="+rbcPDSNIPAddress.codingList[i].characterValue+">"+rbcPDSNIPAddress.codingList[i].chineseDescription+"</option>");
				}
			}

			$("#sctType").change(function(){
				var sctTypeValue = $("#sctType").val();
				switch(sctTypeValue) {
					case "1010" : 
						$("#ipAddress").val("115.168.19.19");
						$("#nai").val("ctnet@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").val("115.168.82.161");
						$("#nasPortType").val("22");
						break;
					case "1020" : 
						$("#ipAddress").val("115.168.19.101");
						$("#nai").val("ctnet@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").append('<option value="115.168.40.10" selected hidden="hidden">115.168.40.10</option>');
						$("#nasIpAddress").val("115.168.40.10");
						$("#nasPortType").val("22");
						break;
					case "1021" : 
						$("#ipAddress").val("115.168.19.101");
						$("#nai").val("ctnet@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").append('<option value="72.25.192.10" selected hidden="hidden">72.25.192.10</option>');
						$("#nasIpAddress").val("72.25.192.10");
						$("#nasPortType").val("22");
						break;
					case "1030" : 
						$("#ipAddress").val("115.168.19.19");
						$("#nai").val("ctwap@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").val("115.168.82.161");
						$("#nasPortType").val("22");
						break;
					case "1040" : 
						$("#ipAddress").val("115.168.19.101");
						$("#nai").val("ctwap@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").append('<option value="115.168.40.10" selected hidden="hidden">115.168.40.10</option>');
						$("#nasIpAddress").val("115.168.40.10");
						$("#nasPortType").val("22");
						break;
					case "1041" : 
						$("#ipAddress").val("115.168.19.101");
						$("#nai").val("ctwap@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").append('<option value="72.25.192.10" selected hidden="hidden">72.25.192.10</option>');
						$("#nasIpAddress").val("72.25.192.10");
						$("#nasPortType").val("22");
						break;
					case "1050" : 
						$("#ipAddress").val("115.168.19.19");
						$("#nai").val("ctbb@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").val("115.168.82.161");
						$("#nasPortType").val("22");
						break;
					case "1060" : 
						$("#ipAddress").val("115.168.19.101");
						$("#nai").val("ctbb@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").append('<option value="115.168.40.10" selected hidden="hidden">115.168.40.10</option>');
						$("#nasIpAddress").val("115.168.40.10");
						$("#nasPortType").val("22");
						break;
					case "1061" : 
						$("#ipAddress").val("115.168.19.101");
						$("#nai").val("ctbb@mycdma.cn");
						$("#passwd").val("vnet.mobi");
						$("#nasIpAddress").append('<option value="72.25.192.10" selected hidden="hidden">72.25.192.10</option>');
						$("#nasIpAddress").val("72.25.192.10");
						$("#nasPortType").val("22");
						break;
					default : 
						$("#ipAddress").val("");
						$("#nai").val("");
						$("#passwd").val("");
						$("#callingStationId").val("");
						$("#nasIpAddress").val("");
						$("#nasPortType").val("");
				}
				if (sctTypeValue == "1070") {
					$("#ipAddress").focus();
				} else {
					$("#callingStationId").focus();
				}
			});
		});
		function getStoreByType(typeId){
			var result;
            $.ajax({
                type: "POST",
                url: "${ctx}/basisCoding/getListStoreByTypeFK",
                data: {"type":typeId},
                dataType: "json",
                async: false,
                success: function(data) {
                	result = data;
                }
            });
            return result;
		}
	</script>
</head>
<body class="bg-white">

		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">软拨测类型：</label></td>
					<td class="width-35">
						<select class="form-control" id="sctType" name="sctType">
						<option value="" disabled selected hidden="hidden">请选择软拨测类型</option>
						</select>
					</td>
					<td class="width-15 active"><label class="pull-right">业务用户名：</label></td>
		   			<td class="width-35">
						<input class="form-control" id="nai" name="nai" placeholder="请输入业务用户名"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">服务器IP：</label></td>
					<td class="width-35">
						<select class="form-control" id="ipAddress" name="ipAddress">
						<option value="" disabled selected hidden="hidden">请选择服务器IP</option>
						</select>
					</td>
					<td class="width-15 active"><label class="pull-right">用机卡IMSI号：</label></td>
					<td class="width-35">
						<input class="form-control" id="callingStationId" name="callingStationId" placeholder="请输入用机卡IMSI号"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">业务密码：</label></td>
					<td class="width-35">
						<input class="form-control" type="password" id="passwd" name="passwd" placeholder="请输入业务密码"/>
					</td>
					<td class="width-15 active"><label class="pull-right">业务类型：</label></td>
					<td class="width-35">
						<select class="form-control" id="nasPortType" name="nasPortType">
						<option value="" disabled selected hidden="hidden">请选择业务类型</option>
						</select>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">PDSN设备IP地址：</label></td>
					<td class="width-35">
						<select class="form-control" id="nasIpAddress" name="nasIpAddress">
						<option value="" disabled selected hidden="hidden">请选择PDSN设备IP地址</option>
						</select>
					</td>
		  		</tr>
		 	</tbody>
		 </table>
		 <!-- 隐藏 -->
	 	 <input id="acctPort" name="acctPort" hidden="hidden" value="1813" readonly/>
	 	 <input id="authPort" name="authPort" hidden="hidden" value="1812" readonly/>
	</form>
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div style="text-align:center">
		  <a  id="search" class="btn btn-primary"><i class="fa fa-search"></i> 查询</a>
		  <a  id="reset" class="btn btn-danger" ><i class="fa fa-refresh"></i> 重置</a>
		 </div>
    </div>
    <hr><hr>
	<form id="inputForm2" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">测试结果：</label></td>
					<td class="width-85">
						<textarea class="form-control" rows="8" id="result">
						</textarea>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form>

</body>
</html>