<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>跌倒风险评估管理</title>
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
					jp.post("${ctx}/morsefall/edMorsefallLog/save",$('#inputForm').serialize(),function(data){
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
	<%@include file="edMorsefallLogSum.js" %>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edMorsefallLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edMorsefallLog.elderid.id}" labelName="elderid.funame" labelValue="${edMorsefallLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="姓名" searchKeys="funame" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edMorsefallLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">曾有摔倒记录：</label></td>
					<td class="width-35">
						<form:radiobuttons path="cengyoushuaidaijilu" items="${fns:getDictList('shuaidaojilu')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">多于一项医疗诊断：</label></td>
					<td class="width-35">
						<form:radiobuttons path="duoyuyixiangyiliaozhengduan" items="${fns:getDictList('yiliaozhenduan')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">步行辅助器：</label></td>
					<td class="width-35">
						<form:radiobuttons path="buxingfuzhuqi" items="${fns:getDictList('buxingfuzhuqi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">静脉注射治疗：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jingmaizhushe" items="${fns:getDictList('jingpaizhushe')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">步态：</label></td>
					<td class="width-35">
						<form:radiobuttons path="butai" items="${fns:getDictList('butai')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">精神状态：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jingshenzhuangtai" items="${fns:getDictList('jingshengzhuangtai')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">总分(指标分数为55)：</label></td>
					<td class="width-35">
						<form:input path="score" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>