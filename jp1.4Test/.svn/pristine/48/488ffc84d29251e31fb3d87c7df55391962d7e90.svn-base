<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>护理级别管理</title>
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
					jp.post("${ctx}/nursingclass/edNursingclassLog/save",$('#inputForm').serialize(),function(data){
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
	        $('#qianziriqi').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
		});
	</script>
	<%@include file="edNursingclassLogLoadInfo.js" %>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edNursingclassLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edNursingclassLog.elderid.id}" labelName="elderid.funame" labelValue="${edNursingclassLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="姓名" searchKeys="funame" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edNursingclassLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">日常活动能力：</label></td>
					<td class="width-35">
						<form:radiobuttons path="richanghuodongnengli" items="${fns:getDictList('richangshenghuonenglifenji')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">智力：</label></td>
					<td class="width-35">
						<form:radiobuttons path="zhili" items="${fns:getDictList('zhiliqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">护理级别：</label></td>
					<td class="width-35">
						<form:radiobuttons path="hulijibie" items="${fns:getDictList('hulijibie')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">评估人员1：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor1" name="assessor1.id" value="${edNursingclassLog.assessor1.id}" labelName="assessor1.name" labelValue="${edNursingclassLog.assessor1.name}"
							    cssClass="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员2：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor2" name="assessor2.id" value="${edNursingclassLog.assessor2.id}" labelName="assessor2.name" labelValue="${edNursingclassLog.assessor2.name}"
							    cssClass="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">评估人员1职位：</label></td>
					<td class="width-35">
						<sys:treeselect id="assessor1dept" name="assessor1dept.id" value="${edNursingclassLog.assessor1dept.id}" labelName="assessor1dept.name" labelValue="${edNursingclassLog.assessor1dept.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员2职位：</label></td>
					<td class="width-35">
						<sys:treeselect id="assessor2dept" name="assessor2dept.id" value="${edNursingclassLog.assessor2dept.id}" labelName="assessor2dept.name" labelValue="${edNursingclassLog.assessor2dept.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">长者/家属：</label></td>
					<td class="width-35">
						<form:input path="zhangzhehuojiashu" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">签字日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='qianziriqi'>
			                    <input type='text'  name="qianziriqi" class="form-control"  value="<fmt:formatDate value="${edNursingclassLog.qianziriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>