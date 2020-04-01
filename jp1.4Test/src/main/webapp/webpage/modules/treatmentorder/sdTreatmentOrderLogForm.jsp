<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>求医订单管理</title>
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
					jp.post("${ctx}/treatmentorder/sdTreatmentOrderLog/save",$('#inputForm').serialize(),function(data){
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
			
		});
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="sdTreatmentOrderLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>订单号：</label></td>
					<td class="width-35">
						<form:input path="orderno" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>预约人：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/patient/sdPatientInf/data" id="patientid" name="patientid.id" value="${sdTreatmentOrderLog.patientid.id}" labelName="patientid.fullname" labelValue="${sdTreatmentOrderLog.patientid.fullname}"
							 title="选择预约人" cssClass="form-control required" fieldLabels="病人" fieldKeys="fullname" searchLabels="病人" searchKeys="fullname" ></sys:gridselect>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>评估师：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/doctor/sdDoctorInf/data" id="doctorid" name="doctorid.id" value="${sdTreatmentOrderLog.doctorid.id}" labelName="doctorid.fullname" labelValue="${sdTreatmentOrderLog.doctorid.fullname}"
							 title="选择评估师" cssClass="form-control required" fieldLabels="医生" fieldKeys="fullname" searchLabels="医生" searchKeys="fullname" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>订单状态：</label></td>
					<td class="width-35">
						<form:radiobuttons path="orderstate" items="${fns:getDictList('sd_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>诊金：</label></td>
					<td class="width-35">
						<form:input path="receptionfee" htmlEscape="false"    class="form-control required isFloatGteZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>就诊时间：</label></td>
					<td class="width-35">
						<form:input path="receptiontime" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>