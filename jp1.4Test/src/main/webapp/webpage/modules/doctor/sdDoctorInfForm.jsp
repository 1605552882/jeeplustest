<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>医生管理</title>
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
					jp.post("${ctx}/doctor/sdDoctorInf/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="sdDoctorInf" action="${ctx}/doctor/sdDoctorInf/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>姓名：</label></td>
					<td class="width-35">
						<form:input path="fullname" htmlEscape="false"    class="form-control required isChinese"/>
					</td>
					<td class="width-15 active"><label class="pull-right">手机号：</label></td>
					<td class="width-35">
						<form:input path="mobileno" htmlEscape="false"    class="form-control  isMobile"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">医院：</label></td>
					<td class="width-35">
						<form:input path="hospital" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">科室：</label></td>
					<td class="width-35">
						<form:input path="department" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35">
						<form:input path="doctortitle" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">介绍：</label></td>
					<td class="width-35">
						<form:input path="introduction" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>审核状态：</label></td>
					<td class="width-35">
						<form:radiobuttons path="auditstate" items="${fns:getDictList('doctor_audit_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">接诊状态：</label></td>
					<td class="width-35">
						<form:radiobuttons path="receptionstate" items="${fns:getDictList('doctor_recept_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">诊金：</label></td>
					<td class="width-35">
						<form:input path="receptionfee" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">接单记录：</a>
                </li>
				<li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">评估师评价：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#sdDoctorOfferLogList', sdDoctorOfferLogRowIdx, sdDoctorOfferLogTpl);sdDoctorOfferLogRowIdx = sdDoctorOfferLogRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>预约评估信息</th>
						<th><font color="red">*</font>接单时间</th>
						<th>接诊时间</th>
						<th>备注</th>
						<th>诊金</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="sdDoctorOfferLogList">
				</tbody>
			</table>
			<script type="text/template" id="sdDoctorOfferLogTpl">//<!--
				<tr id="sdDoctorOfferLogList{{idx}}">
					<td class="hide">
						<input id="sdDoctorOfferLogList{{idx}}_id" name="sdDoctorOfferLogList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="sdDoctorOfferLogList{{idx}}_delFlag" name="sdDoctorOfferLogList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="sdDoctorOfferLogList{{idx}}_reqid" name="sdDoctorOfferLogList[{{idx}}].reqid" type="text" value="{{row.reqid}}"    class="form-control required"/>
					</td>
					
					
					<td>
						<input id="sdDoctorOfferLogList{{idx}}_offertime" name="sdDoctorOfferLogList[{{idx}}].offertime" type="text" value="{{row.offertime}}"    class="form-control required"/>
					</td>
					
					
					<td>
						<input id="sdDoctorOfferLogList{{idx}}_receptiontime" name="sdDoctorOfferLogList[{{idx}}].receptiontime" type="text" value="{{row.receptiontime}}"    class="form-control "/>
					</td>
					
					
					<td>
						<input id="sdDoctorOfferLogList{{idx}}_comment" name="sdDoctorOfferLogList[{{idx}}].comment" type="text" value="{{row.comment}}"    class="form-control "/>
					</td>
					
					
					<td>
						<input id="sdDoctorOfferLogList{{idx}}_receptionfee" name="sdDoctorOfferLogList[{{idx}}].receptionfee" type="text" value="{{row.receptionfee}}"    class="form-control "/>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#sdDoctorOfferLogList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var sdDoctorOfferLogRowIdx = 0, sdDoctorOfferLogTpl = $("#sdDoctorOfferLogTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(sdDoctorInf.sdDoctorOfferLogList)};
					for (var i=0; i<data.length; i++){
						addRow('#sdDoctorOfferLogList', sdDoctorOfferLogRowIdx, sdDoctorOfferLogTpl, data[i]);
						sdDoctorOfferLogRowIdx = sdDoctorOfferLogRowIdx + 1;
					}
				});
			</script>
			</div>
				<div id="tab-2" class="tab-pane fade">
			<a class="btn btn-white btn-sm" onclick="addRow('#sdDoctorRatedLogList', sdDoctorRatedLogRowIdx, sdDoctorRatedLogTpl);sdDoctorRatedLogRowIdx = sdDoctorRatedLogRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>预约人</th>
						<th><font color="red">*</font>订单号</th>
						<th><font color="red">*</font>评价内容</th>
						<th><font color="red">*</font>评价时间</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="sdDoctorRatedLogList">
				</tbody>
			</table>
			<script type="text/template" id="sdDoctorRatedLogTpl">//<!--
				<tr id="sdDoctorRatedLogList{{idx}}">
					<td class="hide">
						<input id="sdDoctorRatedLogList{{idx}}_id" name="sdDoctorRatedLogList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="sdDoctorRatedLogList{{idx}}_delFlag" name="sdDoctorRatedLogList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<sys:gridselect url="${ctx}/patient/sdPatientInf/data" id="sdDoctorRatedLogList{{idx}}_patientid" name="sdDoctorRatedLogList[{{idx}}].patientid.id" value="{{row.patientid.id}}" labelName="sdDoctorRatedLogList{{idx}}.patientid.fullname" labelValue="{{row.patientid.fullname}}"
							 title="选择预约人" cssClass="form-control  required" fieldLabels="病人" fieldKeys="fullname" searchLabels="病人" searchKeys="fullname" ></sys:gridselect>
					</td>
					
					
					<td>
						<input id="sdDoctorRatedLogList{{idx}}_orderid" name="sdDoctorRatedLogList[{{idx}}].orderid" type="text" value="{{row.orderid}}"    class="form-control required"/>
					</td>
					
					
					<td>
						<textarea id="sdDoctorRatedLogList{{idx}}_rateddetail" name="sdDoctorRatedLogList[{{idx}}].rateddetail" rows="4"    class="form-control required">{{row.rateddetail}}</textarea>
					</td>
					
					
					<td>
						<div class='input-group form_datetime' id="sdDoctorRatedLogList{{idx}}_ratedtime">
		                    <input type='text'  name="sdDoctorRatedLogList[{{idx}}].ratedtime" class="form-control required"  value="{{row.ratedtime}}"/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>						            
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#sdDoctorRatedLogList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var sdDoctorRatedLogRowIdx = 0, sdDoctorRatedLogTpl = $("#sdDoctorRatedLogTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(sdDoctorInf.sdDoctorRatedLogList)};
					for (var i=0; i<data.length; i++){
						addRow('#sdDoctorRatedLogList', sdDoctorRatedLogRowIdx, sdDoctorRatedLogTpl, data[i]);
						sdDoctorRatedLogRowIdx = sdDoctorRatedLogRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>