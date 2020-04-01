<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常活动能力评估管理</title>
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
					jp.post("${ctx}/activityassess/edActivityassessLog/save",$('#inputForm').serialize(),function(data){
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
	<%@include file="edActivityassessSum.js" %>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edActivityassessLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edActivityassessLog.elderid.id}" labelName="elderid.funame" labelValue="${edActivityassessLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="funame" searchKeys="姓名" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edActivityassessLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进食：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jinshi" items="${fns:getDictList('jinshi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">洗澡：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xizao" items="${fns:getDictList('xizao')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">个人卫生：</label></td>
					<td class="width-35">
						<form:radiobuttons path="gerenweisheng" items="${fns:getDictList('gerenweisheng')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">穿衣：</label></td>
					<td class="width-35">
						<form:radiobuttons path="chuangyi" items="${fns:getDictList('chuangyi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">大便控制：</label></td>
					<td class="width-35">
						<form:radiobuttons path="dabiankongzhi" items="${fns:getDictList('dabiankongzhi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">小便控制：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xiaobiankongzhi" items="${fns:getDictList('xiaobiankongzhi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">如厕：</label></td>
					<td class="width-35">
						<form:radiobuttons path="ruce" items="${fns:getDictList('ruce')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">床椅转移：</label></td>
					<td class="width-35">
						<form:radiobuttons path="chuangyizhuanyi" items="${fns:getDictList('chuangyizhuangyi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">步行：</label></td>
					<td class="width-35">
						<form:radiobuttons path="buxing" items="${fns:getDictList('buxing')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">上落楼梯：</label></td>
					<td class="width-35">
						<form:radiobuttons path="shangluolouti" items="${fns:getDictList('shangluplouti')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">总得分：</label></td>
					<td class="width-35">
						<form:input path="score" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">特殊事项记录：</label></td>
					<td class="width-35">
						<form:textarea path="teshushiqingjilu" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">日常活动能力分级：</label></td>
					<td class="width-35">
						<form:radiobuttons path="richanghuodongnenglifenji" items="${fns:getDictList('richangshenghuonenglifenji')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">评估人员：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor" name="assessor.id" value="${edActivityassessLog.assessor.id}" labelName="assessor.name" labelValue="${edActivityassessLog.assessor.name}"
							    cssClass="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员职位：</label></td>
					<td class="width-35">
						<sys:treeselect id="assessordept" name="assessordept.id" value="${edActivityassessLog.assessordept.id}" labelName="assessordept.name" labelValue="${edActivityassessLog.assessordept.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>