<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工单详情页面管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					jp.loading();
					form.submit();
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
			$('#dfirstreptime').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
			
	        $('#dfaultapttime').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#darchivetime').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#reportTime').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        
	        $('#deal_info_box').slideUp('slow');
	        $('#deal_info_box1').slideUp('slow');
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
		/* function delRow(obj, prefix){
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
		} */
		
		$(function view_details_click(){
			    $("#detailmain_a").click(function () {
			        if($("#deal_info_box").is(":hidden")){
			            $('#deal_info_box').slideDown('slow');
			            $(this).text("收起︿");
			        }
			        else{
			            $('#deal_info_box').slideUp('slow');
			            $(this).text("展开﹀");

			        }
			    });
			
			 $("#detailmain_a1").click(function () {
					        if($("#deal_info_box1").is(":hidden")){
					            $('#deal_info_box1').slideDown('slow');
					            $(this).text("收起︿");
					        }
					        else{
					            $('#deal_info_box1').slideUp('slow');
					            $(this).text("展开﹀");

					        }
					    });
			});
	</script>
</head>
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="documentarydetails" action="${ctx}/documentdetect/documentarydetails/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>故障单号：</label>
					<div class="col-sm-10">
						<form:input path="sbillno" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障标题：</label>
					<div class="col-sm-10">
						<form:input path="sfaulttitle" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障种类：</label>
					<div class="col-sm-10">
						<form:input path="sfaultcategory" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障现象：</label>
					<div class="col-sm-10">
						<form:textarea path="sfaultrepresent" htmlEscape="false" rows="2"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障内容：</label>
					<div class="col-sm-10">
						<form:textarea path="srepfaultdetail" htmlEscape="false"  rows="5"   class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结单信息：</label>
					<div class="col-sm-10">
						<form:textarea path="sprocesssummary" htmlEscape="false" rows="4" class="form-control"  />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障原因：</label>
					<div class="col-sm-10">
						<form:input path="sfaultcauseid" htmlEscape="false" class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">单据状态：</label>
					<div class="col-sm-10">
						<form:input path="sStatus" htmlEscape="false" class="form-control "/>
					</div>
				</div>
			<a class="btn btn-white btn-sm" href="javascript:void(0);" id="detailmain_a1" title="折叠"><i class="fa fa-plus"></i> 
            	 
            </a> 
			<div class="tab-content" id="deal_info_box1">
				<div class="form-group">
					<label class="col-sm-2 control-label">回单质量评价：</label>
					<div class="col-sm-10">
						<form:input path="sansquaeval" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<table class="table table-bordered">
			   <tbody>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>剩余时间：</label></td>
						<td class="width-35">
							<form:input path="dremaintimetimit" htmlEscape="false"
							class="form-control " />
						</td>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>故障时限(分)：</label></td>
						<td class="width-35">
							<form:input path="dfaulttimelimit" htmlEscape="false"
							class="form-control " />
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>故障历时(分)：</label></td>
						<td class="width-35">
						<div class=" input-group" style=" width: 100%;">
							 <form:input path="ddurtime" htmlEscape="false"
							class="form-control " />
						</div>
						</td>
						<td class="width-15 active"><label class="pull-right">网运时限(分)：</label></td>
						<td class="width-35">
							<form:input path="dntfaultime" htmlEscape="false"
							class="form-control " />
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">网运剩余时间(分)：</label></td>
						<td class="width-35">
							<form:input path="dntiremain" htmlEscape="false"
							class="form-control " />
						</td>
						<td class="width-15 active"><label class="pull-right">受理时间：</label></td>
						<td class="width-35">
							<p class="input-group">
								<div class='input-group form_datetime' id='dfaultapttime'>
									<input type='text' name="dfaultapttime" class="form-control "
										value="<fmt:formatDate value="${documentarydetails.dfaultapttime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</p>
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>首次回应时间：</label></td>
						<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='dfirstreptime'>
								<input type='text' name="dfirstreptime" class="form-control "
									value="<fmt:formatDate value="${documentarydetails.dfirstreptime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</p>
						</td>
						<td class="width-15 active"><label class="pull-right">确认时间：</label></td>
						<td class="width-35">
							<p class="input-group">
							<div class='input-group form_datetime' id='darchivetime'>
								<input type='text' name="darchivetime" class="form-control "
									value="<fmt:formatDate value="${documentarydetails.darchivetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
							</p>
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申告时间：</label></td>
						<td class="width-35">
							<p class="input-group">
							<div class='input-group form_datetime' id='reportTime'>
								<input type='text' name="reportTime" class="form-control "
									value="<fmt:formatDate value="${documentarydetails.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
							</p>
						</td>
					</tr>
			 	</tbody>
			</table>
			</div>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">单据反馈信息：</a>
                </li>
            </ul>
            <a class="btn btn-white btn-sm" href="javascript:void(0);" id="detailmain_a" title="折叠"><i class="fa fa-plus"></i> 
            </a> 
            <div class="tab-content" id="deal_info_box">
				<div id="tab-1" class="tab-pane fade in  active">
				<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th width="10">反馈序号</th>
						<th width="80">反馈部门</th>
						<th width="80">反馈人员</th>
						<th width="80">反馈人电话</th>
						<th width="80">反馈时间</th>
						<th width="80">反馈来源</th>
						<th >反馈内容</th>
					</tr>
				</thead>
				<tbody id="feedbackList">
				</tbody>
			</table>
			<script type="text/template" id="feedbackTpl">//<!--
				<tr id="feedbackList{{idx}}">
					<td>
						{{row.iserialno}}
					</td>
					
					
					<td>
						{{row.sdeptname}}
					</td>
					
					
					<td>
						{{row.sstaffname}}
					</td>
					
					
					<td>
						{{row.sstaffphone}}
					</td>
					
					
					<td>
						{{row.dcreatetime}}
					</td>
					
					
					<td>
						{{row.ssource}}
					</td>
					
					
					<td>
						{{row.sprocdesc}}
					</td>
					
				</tr>//-->
			</script>
			<script type="text/javascript">
				var feedbackRowIdx = 0, feedbackTpl = $("#feedbackTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(documentarydetails.feedbackList)};
					for (var i=0; i<data.length; i++){
						addRow('#feedbackList', feedbackRowIdx, feedbackTpl, data[i]);
						feedbackRowIdx = feedbackRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
		</div>				
	</div>
	</div>
</div>
</div>
</body>
</html>