<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA解除黑名单窗口</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
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
			var aaaQueryParam = JSON.parse(sessionStorage.getItem("aaaQueryParam"));
			var numberType = aaaQueryParam.searchType;
			var operateNumber = aaaQueryParam.userNoValue;
			//显示
			$("#numberTypeStr").html(numberType==0?"IMSI号码：":"手机号码：");
			$("#operateNumberStr").html(operateNumber);
			//表单
			$("#numberType").val(numberType);
			$("#operateNumber").val(operateNumber);

			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/aaa/aaa/removeBlackList",$('#inputForm').serialize(),function(data){
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
		<label>您确定要解除<b id="numberTypeStr"></b><b style="color:red" id="operateNumberStr"></b>的限制吗？</label>
		<input id="numberType" name="numberType" hidden="hidden" />
		<input id="operateNumber" name="operateNumber" hidden="hidden" />
	</form>
</body>
</html>