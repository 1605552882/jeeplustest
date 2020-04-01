<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>特殊护理记录管理</title>
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
					jp.post("${ctx}/specialnursing/edSpecialnursingLog/save",$('#inputForm').serialize(),function(data){
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
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edSpecialnursingLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edSpecialnursingLog.elderid.id}" labelName="elderid.funame" labelValue="${edSpecialnursingLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="姓名" searchKeys="funame" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edSpecialnursingLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">有无特殊护理：</label></td>
					<td class="width-35">
						<form:radiobuttons path="youwuteshuhuli" items="${fns:getDictList('youwuteshuhuli')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">特殊护理内容：</label></td>
					<td class="width-35">
						<sys:checkbox id="teshuhulineirong" name="teshuhulineirong" items="${fns:getDictList('teshuhulineirong')}" values="${edSpecialnursingLog.teshuhulineirong}" cssClass="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor" name="assessor.id" value="${edSpecialnursingLog.assessor.id}" labelName="assessor.name" labelValue="${edSpecialnursingLog.assessor.name}"
							    cssClass="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">评估人员职位：</label></td>
					<td class="width-35">
						<sys:treeselect id="assessordept" name="assessordept.id" value="${edSpecialnursingLog.assessordept.id}" labelName="assessordept.name" labelValue="${edSpecialnursingLog.assessordept.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>