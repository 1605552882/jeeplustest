<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>生理状况评估管理</title>
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
					jp.post("${ctx}/physiologyassess/edPhysiologyassessLog/save",$('#inputForm').serialize(),function(data){
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
	<%@include file="edPhysiologyassessLogSum.js" %>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edPhysiologyassessLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edPhysiologyassessLog.elderid.id}" labelName="elderid.funame" labelValue="${edPhysiologyassessLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="姓名" fieldKeys="funame" searchLabels="姓名" searchKeys="funame" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edPhysiologyassessLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">血压(mmHg)：</label></td>
					<td class="width-35">
						<form:input path="xueya" htmlEscape="false"    class="form-control  isIntGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">脉搏(次/min)：</label></td>
					<td class="width-35">
						<form:input path="maibo" htmlEscape="false"    class="form-control  isIntGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">呼吸(次/min)：</label></td>
					<td class="width-35">
						<form:input path="huxi" htmlEscape="false"    class="form-control  isIntGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">体温(℃)：</label></td>
					<td class="width-35">
						<form:input path="tiwen" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体重(千克)：</label></td>
					<td class="width-35">
						<form:input path="tizzhong" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">身高(米)：</label></td>
					<td class="width-35">
						<form:input path="shengao" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体重指标(BMI)：</label></td>
					<td class="width-35">
						<form:input path="tizhongzhibiao" htmlEscape="false"    class="form-control  isIntGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">睡眠状况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="shuimianzhuangkuang" items="${fns:getDictList('shuimianzhuangkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">皮肤状况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="pifuzhuangkuang" items="${fns:getDictList('pifuzhuangkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">营养状况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yinyangzhuangkuang" items="${fns:getDictList('yingyangzhuangkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">吞咽情况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="tunyanqingkuang" items="${fns:getDictList('tunyanqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">饮食情况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yinshiqingkuang" items="${fns:getDictList('yinshi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">咀嚼情况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jujueqingkuang" items="${fns:getDictList('jujueqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">牙齿状况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yachizhuangkuang" items="${fns:getDictList('yachizhuangkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">小便：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xiaobian" items="${fns:getDictList('xiaobian')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">大便：</label></td>
					<td class="width-35">
						<form:radiobuttons path="dabian" items="${fns:getDictList('dabian')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">吸烟：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xiyan" items="${fns:getDictList('xiyan')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">吸烟数量：</label></td>
					<td class="width-35">
						<form:input path="xiyanshuliang" htmlEscape="false"    class="form-control  isIntGteZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">已戒掉年份：</label></td>
					<td class="width-35">
						<form:input path="yijiediaonianfen" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">饮酒：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yinjiu" items="${fns:getDictList('yinjiu')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">饮酒份量：</label></td>
					<td class="width-35">
						<form:input path="yinjiufenliang" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">已戒酒年份：</label></td>
					<td class="width-35">
						<form:input path="yijiejiunianfen" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">痛症：</label></td>
					<td class="width-35">
						<form:radiobuttons path="tongzheng" items="${fns:getDictList('tongzheng')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">痛症位置：</label></td>
					<td class="width-35">
						<form:input path="tongzhengweizhi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor" name="assessor.id" value="${edPhysiologyassessLog.assessor.id}" labelName="assessor.name" labelValue="${edPhysiologyassessLog.assessor.name}"
							    cssClass="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">评估人员职位：</label></td>
					<td class="width-35">
						<sys:treeselect id="assessordept" name="assessordept.id" value="${edPhysiologyassessLog.assessordept.id}" labelName="assessordept.name" labelValue="${edPhysiologyassessLog.assessordept.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>