<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>病人管理</title>
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
					jp.post("${ctx}/patient/sdPatientInf/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="sdPatientInf" action="${ctx}/patient/sdPatientInf/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35">
						<form:input path="fullname" htmlEscape="false" maxlength="12"  minlength="4"   class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">手机号：</label></td>
					<td class="width-35">
						<form:input path="mobileno" htmlEscape="false"    class="form-control  isMobile"/>
					</td>
				</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">预约人评价：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#sdPatientRatedLogList', sdPatientRatedLogRowIdx, sdPatientRatedLogTpl);sdPatientRatedLogRowIdx = sdPatientRatedLogRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>评估师</th>
						<th><font color="red">*</font>订单号</th>
						<th><font color="red">*</font>评价内容</th>
						<th><font color="red">*</font>评价时间</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="sdPatientRatedLogList">
				</tbody>
			</table>
			<script type="text/template" id="sdPatientRatedLogTpl">//<!--
				<tr id="sdPatientRatedLogList{{idx}}">
					<td class="hide">
						<input id="sdPatientRatedLogList{{idx}}_id" name="sdPatientRatedLogList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="sdPatientRatedLogList{{idx}}_delFlag" name="sdPatientRatedLogList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<sys:gridselect url="${ctx}/doctor/sdDoctorInf/data" id="sdPatientRatedLogList{{idx}}_doctorid" name="sdPatientRatedLogList[{{idx}}].doctorid.id" value="{{row.doctorid.id}}" labelName="sdPatientRatedLogList{{idx}}.doctorid.fullname" labelValue="{{row.doctorid.fullname}}"
							 title="选择评估师" cssClass="form-control  required" fieldLabels="医生" fieldKeys="fullname" searchLabels="医生" searchKeys="fullname" ></sys:gridselect>
					</td>
					
					
					<td>
						<input id="sdPatientRatedLogList{{idx}}_orderid" name="sdPatientRatedLogList[{{idx}}].orderid" type="text" value="{{row.orderid}}"    class="form-control required"/>
					</td>
					
					
					<td>
						<input id="sdPatientRatedLogList{{idx}}_rateddetail" name="sdPatientRatedLogList[{{idx}}].rateddetail" type="text" value="{{row.rateddetail}}"    class="form-control required"/>
					</td>
					
					
					<td>
						<input id="sdPatientRatedLogList{{idx}}_ratedtime" name="sdPatientRatedLogList[{{idx}}].ratedtime" type="text" value="{{row.ratedtime}}"    class="form-control required"/>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#sdPatientRatedLogList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var sdPatientRatedLogRowIdx = 0, sdPatientRatedLogTpl = $("#sdPatientRatedLogTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(sdPatientInf.sdPatientRatedLogList)};
					for (var i=0; i<data.length; i++){
						addRow('#sdPatientRatedLogList', sdPatientRatedLogRowIdx, sdPatientRatedLogTpl, data[i]);
						sdPatientRatedLogRowIdx = sdPatientRatedLogRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>