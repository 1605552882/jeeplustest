<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>心理社交评估管理</title>
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
					jp.post("${ctx}/mentalassess/edMentalassessLog/save",$('#inputForm').serialize(),function(data){
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
	<%@include file="edMentalassessLogSum.js" %>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="edMentalassessLog" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">长者id：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/elder/edElderInf/data" id="elderid" name="elderid.id" value="${edMentalassessLog.elderid.id}" labelName="elderid.funame" labelValue="${edMentalassessLog.elderid.funame}"
							 title="选择长者id" cssClass="form-control required" fieldLabels="名字" fieldKeys="funame" searchLabels="名字" searchKeys="funame" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right">评估时间：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='assesstime'>
			                    <input type='text'  name="assesstime" class="form-control"  value="<fmt:formatDate value="${edMentalassessLog.assesstime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">是否接受问答：</label></td>
					<td class="width-35">
						<form:radiobuttons path="shifoujieshouwenda" items="${fns:getDictList('shifouhuida')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">未能/拒绝回答原因：</label></td>
					<td class="width-35">
						<form:input path="weinenghuidayuanyin" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">你住紧嘅地方系属于顺德边一个镇/街?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="zhunagezhen" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">住居地答案：</label></td>
					<td class="width-35">
						<form:input path="juzhudidaan" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">你住系边一条路/小区?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="zhunatiaolu" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">路/小区答案：</label></td>
					<td class="width-35">
						<form:input path="juzhuludaan" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">今日系几号?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jintianjihao" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">系几月份?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yuefen" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">系乜嘢年份?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="nianfen" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">一年有几多日?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yinianjitian" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">新中国系边一年建国?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jianguonianfen" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">你有20元，用咗3元，还有多少?再多3元，还有多少?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="shuxueti" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">中国现任中国国家主席系边个?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="currentpresidentname" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">中国上一中国国家主席叫乜嘢名?：</label></td>
					<td class="width-35">
						<form:radiobuttons path="lastpresidentname" items="${fns:getDictList('right_or_wrong')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">认知能力得分：</label></td>
					<td class="width-35">
						<form:input path="renzhinenglidefen" htmlEscape="false"    class="form-control  isIntGteZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">智力情况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="zhiliqingkuang" items="${fns:getDictList('zhiliqingkuang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/> <!--  -->
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">意识：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yishi" items="${fns:getDictList('yishi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">感知：</label></td>
					<td class="width-35">
						<form:radiobuttons path="ganzhi" items="${fns:getDictList('ganzhi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">感知内容：</label></td>
					<td class="width-35">
						<form:input path="ganzhineirong" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">情绪：</label></td>
					<td class="width-35">
						<form:radiobuttons path="qingxu" items="${fns:getDictList('qingxu')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他情绪：</label></td>
					<td class="width-35">
						<form:input path="qitaqingxu" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">行为：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xingwei" items="${fns:getDictList('xingwei')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他行为：</label></td>
					<td class="width-35">
						<form:input path="qitaxingwei" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">态度：</label></td>
					<td class="width-35">
						<form:radiobuttons path="taidu" items="${fns:getDictList('taidu')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他态度：</label></td>
					<td class="width-35">
						<form:input path="qitataidu" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">思维：</label></td>
					<td class="width-35">
						<form:radiobuttons path="siwei" items="${fns:getDictList('siwei')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他思维：</label></td>
					<td class="width-35">
						<form:input path="qitasiwei" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">家人/保证人到访：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jiarenbaozhengrendaofang" items="${fns:getDictList('daofang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">亲戚到访：</label></td>
					<td class="width-35">
						<form:radiobuttons path="qinqidaofang" items="${fns:getDictList('qinqidaofang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">朋友到访：</label></td>
					<td class="width-35">
						<form:radiobuttons path="pengyoudaofang" items="${fns:getDictList('pengyoudaofang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">与家人关系：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yujiarenguanxi" items="${fns:getDictList('jiarenguanxi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">与院友关系：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yuyuanyouguanxi" items="${fns:getDictList('yuanyouguanxi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">与员工关系：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yuyuangongguanxi" items="${fns:getDictList('yuangongguanxi')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">其他补充数据：</label></td>
					<td class="width-35">
						<form:textarea path="qitabuchongshuju" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评估人员：</label></td>
					<td class="width-35">
						<sys:userselect id="assessor" name="assessor.id" value="${edMentalassessLog.assessor.id}" labelName="assessor.name" labelValue="${edMentalassessLog.assessor.name}"
							    cssClass="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">评估人员职位：</label></td>
					<td class="width-35">
						<sys:treeselect id="assessordept" name="assessordept.id" value="${edMentalassessLog.assessordept.id}" labelName="assessordept.name" labelValue="${edMentalassessLog.assessordept.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>