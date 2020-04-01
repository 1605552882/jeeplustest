<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>贝登量表管理</title>
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
					jp.post("${ctx}/braden/edBradenLog/save",$('#inputForm').serialize(),function(data){
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
			
	        $('#assesstime').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
		});
	</script>
	<%@include file="edBradenLogSum.js" %>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edBradenLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edBradenLog.elderid.id}" labelName="elderid.funame" labelValue="${edBradenLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="姓名" searchKeys="funame" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edBradenLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor" name="assessor.id" value="${edBradenLog.assessor.id}" labelName="assessor.name" labelValue="${edBradenLog.assessor.name}"
							    cssClass="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">感官感觉对不适的压力具有反应的能力：</label></td>
					<td class="width-35">
						<form:radiobuttons path="ganguanganjue" items="${fns:getDictList('braden_ganguanganjue')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">潮湿情况皮肤的潮湿程度：</label></td>
					<td class="width-35">
						<form:radiobuttons path="chaoshiqingkuang" items="${fns:getDictList('braden_chaoshiqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">活动情况身体活动程度：</label></td>
					<td class="width-35">
						<form:radiobuttons path="huodongqingkuang" items="${fns:getDictList('braden_huodongqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">移动情况变换和控制体位的能力：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yidongqingkuang" items="${fns:getDictList('braden_yidongqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">摩擦力：</label></td>
					<td class="width-35">
						<form:radiobuttons path="mocali" items="${fns:getDictList('braden_mocaili')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">营养情况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yinyangqingkuang" items="${fns:getDictList('braden_yingyangqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">总分：</label></td>
					<td class="width-35">
						<form:input path="score" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>