<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>HSS业务更改</title>
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
		$("#operatorTypeDiv,#mdnDiv,#payTypeDiv,#lteDiv,#ehrpdDiv,#apnDiv,#roamDiv").hide();

		$("input[name='business']").click(function(){
			$("#operatorTypeDiv,#imsiDiv,#payTypeDiv,#lteDiv,#ehrpdDiv,#apnDiv,#roamDiv").hide();
			var businessType = $(this).val();
			switch(businessType){
			case 'RMV_KI':
				$("#imsiDiv").show();
				break;
			case 'RMV_SUB':
				$("#operatorTypeDiv,#imsiDiv").show();
				break;
			case 'MOD_OPTGPRS':
				$("#operatorTypeDiv,#imsiDiv,#payTypeDiv").show();
				break;
			case 'MOD_LCK':
				$("#operatorTypeDiv,#imsiDiv,#lteDiv,#ehrpdDiv").show();
				break;
			case 'ADD_OPTGPRS':
				$("#operatorTypeDiv,#imsiDiv,#apnDiv").show();
				break;
			case 'DEL_OPTGPRS':
				$("#operatorTypeDiv,#imsiDiv,#apnDiv").show();
				break;
			case 'ADD_VPDN':
				$("#operatorTypeDiv,#imsiDiv").show();
				break;
			case 'DEL_VPDN':
				$("#operatorTypeDiv,#imsiDiv").show();
				break;
			case 'MOD_DIAMRRS':
				$("#operatorTypeDiv,#roamDiv").show();
				break;
			case 'ADD_LBO':
				$("#operatorTypeDiv,#imsiDiv").show();
				break;
			case 'DEL_LBO':
				$("#operatorTypeDiv,#imsiDiv").show();
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
						<label class=""><input type="radio" checked="checked" name="business" value="RMV_KI">删除KI</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="RMV_SUB">销户</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="MOD_OPTGPRS">修改付费属性</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="MOD_LCK">停复机</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="ADD_OPTGPRS">增加APN</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="DEL_OPTGPRS">删除APN</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="ADD_VPDN">签约VPDN ONLY</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="DEL_VPDN">取消VPDN ONLY</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="MOD_DIAMRRS">修改漫游权限</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="ADD_LBO">改为HBO用户</label>
					</td>
					<td class="width-15 active" colspan="2">
						<label class=""><input type="radio" name="business" value="DEL_LBO">改为LBO用户</label>
					</td>
		  		</tr>
		  		<tr>
		  			<td class="width-15 active" colspan="3">
						<label class="" style="color:red">按手机号码操作格式为86+手机号码</label>
					</td>
		  		</tr>
		 	</tbody>
		</table>
		</form>
				
		<div style="margin:0 15px 0 15px">
			<div class="col-xs-12" id="operatorTypeDiv">
				<label class="col-xs-4">操作类型：</label>
				<select class=" col-xs-8">
					<option>按手机号码操作</option>
					<option>按IMSI号码操作</option>
				</select>
			</div>
	  		<div class="col-xs-12" id="imsiDiv">
				<label class="col-xs-4">IMSI号码：</label>
				<input class="form-input col-xs-8" />
			</div>
			<div class="col-xs-12" id="mdnDiv">
				<label class="col-xs-4">手机号码：</label>
				<input class="form-input col-xs-8" />
			</div>
			<div class="col-xs-12" id="payTypeDiv">
				<label class="col-xs-4">付费类型：</label>
				<select class=" col-xs-8">
					<option>预付费</option>
					<option>后付费</option>
				</select>
			</div>
			<div class="col-xs-12" id="lteDiv">
			<label class="col-xs-4">LTE数据业务：</label>
				<select class=" col-xs-8">
					<option>停机</option>
					<option>复通</option>
				</select>
			</div>
			<div class="col-xs-12" id="ehrpdDiv">
				<label class="col-xs-4">ehrpd业务：</label>
				<select class=" col-xs-8">
					<option>停机</option>
					<option>复通</option>
				</select>
			</div>
			<div class="col-xs-12" id="apnDiv">
				<label class="col-xs-4">APN：</label>
				<select class=" col-xs-8">
					<option>CTNET</option>
					<option>CTWAP</option>
					<option>CTLTE</option>
					<option>CTVPDN</option>
				</select>
			</div>
			<div class="col-xs-12" id="roamDiv">
				<label class="col-xs-4">漫游权限：</label>
				<select class=" col-xs-8">
					<option>国内漫游</option>
					<option>省内漫游</option>
					<option>国际漫游</option>
					<option>澳门漫游</option>
				</select>
			</div>
		</div>
</body>
</html>