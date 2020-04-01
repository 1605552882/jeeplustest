<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>求医记录管理</title>
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
					jp.post("${ctx}/treatmentreq/sdTreatmentReqLog/save",$('#inputForm').serialize(),function(data){
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
		
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
			$(list+idx).find(".form_datetime").each(function(){
				 $(this).datetimepicker({
					 format: "YYYY-MM-DD HH:mm:ss"
			    });
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="sdTreatmentReqLog" action="${ctx}/treatmentreq/sdTreatmentReqLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>地点：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/treamentplace/sdTreatmentplaceInf/data" id="sdTreatmentplaceInf" name="sdTreatmentplaceInf.id" value="${sdTreatmentReqLog.sdTreatmentplaceInf.id}" labelName="sdTreatmentplaceInf.placename" labelValue="${sdTreatmentReqLog.sdTreatmentplaceInf.placename}"
							 title="选择地点" cssClass="form-control required" fieldLabels="地点" fieldKeys="placename" searchLabels="地点" searchKeys="placename" ></sys:gridselect>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>预约人：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/patient/sdPatientInf/data" id="sdPatientInf" name="sdPatientInf.id" value="${sdTreatmentReqLog.sdPatientInf.id}" labelName="sdPatientInf.fullname" labelValue="${sdTreatmentReqLog.sdPatientInf.fullname}"
							 title="选择预约人" cssClass="form-control required" fieldLabels="病人" fieldKeys="fullname" searchLabels="病人" searchKeys="fullname" ></sys:gridselect>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>记录状态：</label></td>
					<td class="width-35">
						<form:radiobuttons path="reqstate" items="${fns:getDictList('treatmentreq_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>标题：</label></td>
					<td class="width-35">
						<form:input path="reqtitle" htmlEscape="false" maxlength="100"  minlength="10"   class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>内容：</label></td>
					<td class="width-35">
						<form:textarea path="reqdetail" htmlEscape="false" rows="4" maxlength="1000"  minlength="10"   class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">预约评估邀请：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#sdTreatmentInviteLogList', sdTreatmentInviteLogRowIdx, sdTreatmentInviteLogTpl);sdTreatmentInviteLogRowIdx = sdTreatmentInviteLogRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>预约人</th>
						<th><font color="red">*</font>评估师</th>
						<th><font color="red">*</font>邀请时间</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="sdTreatmentInviteLogList">
				</tbody>
			</table>
			<script type="text/template" id="sdTreatmentInviteLogTpl">//<!--
				<tr id="sdTreatmentInviteLogList{{idx}}">
					<td class="hide">
						<input id="sdTreatmentInviteLogList{{idx}}_id" name="sdTreatmentInviteLogList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="sdTreatmentInviteLogList{{idx}}_delFlag" name="sdTreatmentInviteLogList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<sys:gridselect url="${ctx}/patient/sdPatientInf/data" id="sdTreatmentInviteLogList{{idx}}_patientid" name="sdTreatmentInviteLogList[{{idx}}].patientid.id" value="{{row.patientid.id}}" labelName="sdTreatmentInviteLogList{{idx}}.patientid.fullname" labelValue="{{row.patientid.fullname}}"
							 title="选择预约人" cssClass="form-control  required" fieldLabels="病人" fieldKeys="fullname" searchLabels="病人" searchKeys="fullname" ></sys:gridselect>
					</td>
					
					
					<td>
						<sys:gridselect url="${ctx}/doctor/sdDoctorInf/data" id="sdTreatmentInviteLogList{{idx}}_doctorid" name="sdTreatmentInviteLogList[{{idx}}].doctorid.id" value="{{row.doctorid.id}}" labelName="sdTreatmentInviteLogList{{idx}}.doctorid.fullname" labelValue="{{row.doctorid.fullname}}"
							 title="选择评估师" cssClass="form-control  required" fieldLabels="医生" fieldKeys="fullname" searchLabels="医生" searchKeys="fullname" ></sys:gridselect>
					</td>
					
					
					<td>
						<input id="sdTreatmentInviteLogList{{idx}}_invitetime" name="sdTreatmentInviteLogList[{{idx}}].invitetime" type="text" value="{{row.invitetime}}"    class="form-control required"/>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#sdTreatmentInviteLogList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var sdTreatmentInviteLogRowIdx = 0, sdTreatmentInviteLogTpl = $("#sdTreatmentInviteLogTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(sdTreatmentReqLog.sdTreatmentInviteLogList)};
					for (var i=0; i<data.length; i++){
						addRow('#sdTreatmentInviteLogList', sdTreatmentInviteLogRowIdx, sdTreatmentInviteLogTpl, data[i]);
						sdTreatmentInviteLogRowIdx = sdTreatmentInviteLogRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>