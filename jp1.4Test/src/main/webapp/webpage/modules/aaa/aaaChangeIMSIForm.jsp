<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA普通用户换卡窗口</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		//1.自定义校验规则
		$.validator.addMethod(
		    //新c网imsi规则
		    "checkCImsi",
		    function(value,element,params){
		    	var reg = /^46003\d{10}$/;
			   	return reg.test(value);
		    }
		);
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
			  $topIndex = index;
			  jp.loading();
			  $("#inputForm").submit();
			  return true;
		  }

		  return false;
		}

		$(document).ready(function() {
			
			var aaaInfo = JSON.parse(sessionStorage.getItem("aaaInfo"));
			$("#operateNumber").val(aaaInfo[0].aaaMDN);
			$("#imsiNumber").val(aaaInfo[0].aaaIMSI);
			
			validateForm = $("#inputForm").validate({
				//2.使用自定义规则
				rules:{
					"newIMSINumber":{
						"required":true,
		                "checkCImsi":true
					}
				},
				//3.自定义规则显示错误信息
				messages:{
					"newIMSINumber":{
						"required":"IMSI号码不能为空",
		                "checkCImsi":"请填写正确的C网IMSI号码"
					}
				},
				submitHandler: function(form){
					jp.post("${ctx}/aaa/aaa/changeIMSI",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	var sucDetail ="<font color='green'>"+data.msg+"</font>";
		                	top.layer.alert(sucDetail , {
		                		  icon: 1,
		                		  area:['auto','auto'],
		                		  title:"请求成功"
		                	})
	                    	jp.close($topIndex);//关闭dialog
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
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
	</script>
</head>
<body class="bg-white">
		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">手机号码：</label></td>
					<td class="width-35">
						<input id="operateNumber" name="operateNumber" class="form-control" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">IMSI号码：</label></td>
					<td class="width-35">
						<input id="imsiNumber" name="imsiNumber" class="form-control" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">新IMSI号码：</label></td>
		   			<td class="width-35">
						<input id="newIMSINumber" name="newIMSINumber" class="form-control"/>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form>
</body>
</html>