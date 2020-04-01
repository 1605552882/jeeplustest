<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>PCRF业务更改</title>
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
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/lnet/hss/hssModify",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	$table.bootstrapTable('refresh');
	                    	jp.success(data.msg);
	                    	jp.close($topIndex);//关闭dialog

	                    }else{
            	  			jp.error(data.msg);
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
			
		//初始化
		$("#mdnTipDiv,#menuTypeDiv,#menuTypeTipDiv").hide();

		$("input[name='business']").click(function(){
			$("#mdnDiv,#mdnTipDiv,#payTypeDiv,#menuTypeDiv,#menuTypeTipDiv").hide();
			var businessType = $(this).val();
			switch(businessType){
			case 'addSubscriber':
				$("#mdnDiv,#payTypeDiv").show();
				break;
			case 'delSubscriber':
				$("#mdnDiv").show();
				break;
			case 'updateSubscriber':
				$("#mdnDiv,#payTypeDiv").show();
				break;
			case 'subscribeService':
				$("#mdnDiv,#menuTypeDiv,#menuTypeTipDiv").show();
				break;
			case 'delsubscribeService':
				$("#mdnDiv,#menuTypeDiv").show();
				break;
			case 'updateQosSubscriber':
				$("#mdnDiv,#mdnTipDiv").show();
				break;
			default:
				break;
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
					<td class="width-15 active">
						<label class=""><input type="radio" checked="checked" name="business" value="addSubscriber">开户</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="delSubscriber">销户</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="updateSubscriber">修改付费属性</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="subscribeService">签套餐</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="delsubscribeService">删除用户签约套餐</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="updateQosSubscriber">用户QOS优先接入配置</label>
					</td>
		  		</tr>
		 	</tbody>
		</table>
		</form>
		
		<div class="" style="margin:0 15px 0 15px">
			<div class="col-xs-12" id="mdnDiv">
				<label class="col-xs-4">MDN号：</label>
				<input class="form-input col-xs-8" />
			</div>
			<div class="col-xs-12 text-center" id="mdnTipDiv">
				<span style="color:red">提示：每个地市号码可以每天可签约100次,有效期限15天</span>
			</div>
			<div class="col-xs-12" id="payTypeDiv">
				<label class="col-xs-4">付费类型：</label>
				<select class=" col-xs-8">
					<option>预付费</option>
					<option>后付费</option>
				</select>
			</div>
			<div class="col-xs-12" id="menuTypeDiv">
			<label class="col-xs-4">套餐编码：</label>
				<input class="form-input col-xs-8" />
			</div>
			<div class="col-xs-12 text-center" id="menuTypeTipDiv">
				<span style="color:red">提示：套餐qos6无法签约，请用 用户QOS优先接入配置 签约</span>
			</div>
		</div>
</body>
</html>