<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>工单详情页面管理</title>
<meta name="decorator" content="ani" />
<link href="${ctxStatic}/plugin/bootstrapTable/bootstrap-select.min.css"
	rel="stylesheet" />
<script src="${ctxStatic}/plugin/bootstrapTable/bootstrap-select.min.js"></script>
<script type="text/javascript">

		$(document).ready(function() {
			$("#searchsamplForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/documentchoose/documentchouse/save",$('#searchsamplForm').serialize(),function(data){
						  if(data.success){
							jp.alert(data.msg);
							 sessionStorage.setItem("token", true);
					 		//$('#documentchouseTable').bootstrapTable('refresh');
						  } else {
							  jp.error(data.msg);
						  }
					  });
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
	        
			inputDisable();
			//回显检查结论
			showCheckResult();
		});
		
		//回显检查结论
		function showCheckResult(){
			var checkresultlist = ${fns:toJson(documentarydetails.checkresult)};
			if(checkresultlist.length>0){
				$("#peopleDiv,#groupDiv,#buttonDiv").hide();
				$("#peopleAndGroupDiv").show();
				$("#problem option").each(function(i){//只保留改单对应的问题
					if(i != checkresultlist[0].hasproblem){
					$(this).remove();
					}
				})
				$("#details").val(checkresultlist[0].details);
				if(checkresultlist.length>1){
					$("#peopleAndGroupSel").removeAttr("disabled");
				}
				for(var i=0; i<checkresultlist.length; i++){
					//责任班组和责任人
					console.log(checkresultlist[i].dutyPeople==null);
					var dutyPeople = checkresultlist[i].dutyPeople;
					if(dutyPeople==null || dutyPeople=="undefined"){
						dutyPeople = "";
					}
					var dutyGroup = checkresultlist[i].dutyGroup;
					if(dutyGroup==null || dutyGroup=="undefined"){
						dutyGroup = "";
					}
					$("#peopleAndGroupSel").append("<option>"+dutyPeople+"（"+dutyGroup+"）"+"</option>");
				}
			}else{
				$("#peopleDiv,#groupDiv,#buttonDiv").show();
				$("#peopleAndGroupDiv").hide();
				$("#searchsamplForm input,#searchsamplForm select,#searchsamplForm textarea").removeAttr("disabled");
			}
		}
		
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
		
		$(function view_details_click(){
			    $("#detailmain_b").click(function () {
			        if($("#deal_info_box").is(":hidden")){
			            $('#deal_info_box').slideDown('slow');
			            $(this).text("收起︿");
			        }
			        else{
			            $('#deal_info_box').slideUp('slow');
			            $(this).text("展开﹀");

			        }
			    });
			    $("#detailsUl").click(function () {
				        if($("#deal_info_box").is(":hidden")){
				            $('#deal_info_box').slideDown('slow');
				            $(this).next().text("收起︿");
				        }
				        /*else{
				            $('#deal_info_box').slideUp('slow');
				            $(this).next().text("展开﹀");

				        }*/
				    });
			
			
			 $("#detailmain_b1").click(function () {
			        if($("#deal_info_box1").is(":hidden")){
			            $('#deal_info_box1').slideDown('slow');
			            $(this).text("收起︿");
			        }
			        else{
			            $('#deal_info_box1').slideUp('slow');
			            $(this).text("展开﹀");

			        }
			});
			 
			 $("#reset").click("click", function() {// 绑定查询按扭
				 /*  $("#searchsamplForm  select").val(""); */
				  $("#searchsamplForm  textarea").val("");
				 /*  $("#searchsamplForm  input").val("");
				  $("#searchsamplForm  .select-item").html(""); */
					}); 
		
		}); 
		
		function newtable(sbillno) {
			jp.post("${ctx}/overtimedocument/overtimedocument/newtable",{sbillno:sbillno},function(data){
				if(data.success){
					var detailId = data.body.documentarydetails.id;
					//window.location="${ctx}/documentdetect/documentarydetails/samplform?id="+id;
					jp.openTab("${ctx}/documentdetect/documentarydetails/samplform?id="+detailId ,"工单明细", false);
				}	
			});
		}
		
		/*禁用输入*/
		function inputDisable(){
			$("input,textarea,select").attr("disabled","disabled");
			
		}
	</script>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-body">
						<!-- 搜索 -->
						<div class="accordion-group">
							<div id="sampl" class="accordion-body collapse">
								<div class="accordion-inner">
									<form:form id="searchsamplForm"
										modelAttribute="documentarydetails"
										action="${ctx}/documentchoose/documentchouse/save"
										method="post" class="form form-horizontal well clearfix">
										<form:hidden path="sbillno" />
										<form:hidden path="chooseId" />
										<div class="col-xs-12 col-sm-6 col-md-2">
											<label class="label-item single-overflow pull-left"
												title="存在问题：">存在问题：</label>
											<form:select path="problem" cssClass="form-control ">
												<form:options items="${fns:getDictList('document_problem')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>

										<div class="col-xs-12 col-sm-6 col-md-2" id="peopleDiv">
											<label class="label-item single-overflow pull-left"
												title="责任人：">责任人：</label>
											<form:select path="people"
												Class="selectpicker  form-control " multiple="multiple">
												<form:option value="" label="" />
												<form:options items="${fns:getdutypeople()}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>

										<div class="col-xs-12 col-sm-6 col-md-2" id="groupDiv">
											<label class="label-item single-overflow pull-left"
												title="责任班组：">责任班组：</label>
											<div class="col-sm-10">
												<form:select path="group" class="form-control m-b ">
													<form:option value="" label="" />
													<form:options items="${fns:getDictList('group')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>

										</div>
										
										<div class="col-xs-12 col-sm-6 col-md-2" id="peopleAndGroupDiv">
											<label class="label-item single-overflow pull-left"
												title="责任人（责任班组）：">责任人（责任班组）：</label>
											<select 
												Class="selectpicker  form-control" id="peopleAndGroupSel">
											</select>
										</div>

										<div class="col-xs-12 col-sm-6 col-md-4">
											<label class="label-item single-overflow pull-left"
												title="详细情况：">详细情况：</label>
											<div class="col-sm-10">
												<form:textarea path="details" htmlEscape="false" rows="2"
													class="form-control " />
											</div>

										</div>
										
										<form:input hidden="hidden" path="sStatus" htmlEscape="false" value="${sStatus}" />
										
										<div class="col-xs-12 col-sm-6 col-md-4" id="buttonDiv">
											<div style="margin-top: 26px">
												<%-- <c:if test="${fns:hasPermission('documentconfig:documentconfig:add')}">
               			<button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
		        </c:if> --%>
												<button id="searchsampl" class="btn btn-primary"">
													<i class="fa fa-search"></i> 提交
												</button>
												<a id="reset" class="btn btn-danger"><i
													class="fa fa-refresh"></i> 重置</a>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>

						<div id="toolbar">
							<a class="btn btn-primary" data-toggle="collapse"
								data-parent="#accordion3" href="#sampl"> <i
								class="fa fa-search"></i> 结论
							</a>
						</div>
						<form:form id="inputsamplForm" modelAttribute="documentarydetails"
							action="${ctx}/documentdetect/documentarydetails/save"
							method="post" class="form-horizontal">
							<form:hidden path="id" />
							<sys:message content="${message}" />
							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>检查规则：</label>
								<div class="col-sm-10">
									<form:input path="remarks" htmlEscape="false"
										class="form-control required" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><font color="red">*</font>故障单号：</label>
								<div class="col-sm-10">
									<form:input path="sbillno" htmlEscape="false"
										class="form-control required" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">故障标题：</label>
								<div class="col-sm-10">
									<form:input path="sfaulttitle" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">故障种类：</label>
								<div class="col-sm-10">
									<form:input path="sfaultcategory" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">故障现象：</label>
								<div class="col-sm-10">
									<form:textarea path="sfaultrepresent" htmlEscape="false"
										rows="2" class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">故障内容：</label>
								<div class="col-sm-10">
									<form:textarea path="srepfaultdetail" htmlEscape="false"
										rows="5" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">结单信息：</label>
								<div class="col-sm-10">
									<form:textarea path="sprocesssummary" htmlEscape="false"
										rows="4" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">故障原因：</label>
								<div class="col-sm-10">
									<form:input path="sfaultcauseid" htmlEscape="false"
										class="form-control " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">单据状态：</label>
								<div class="col-sm-10">
									<form:input path="sStatus" htmlEscape="false" class="form-control "/>
								</div>
							</div>
							<a class="btn btn-white btn-sm" href="javascript:void(0);"
								id="detailmain_b1" title="折叠"><i class="fa fa-plus"></i> </a>

							<div class="tab-content" id="deal_info_box1">
								<div class="form-group">
									<label class="col-sm-2 control-label">回单质量评价：</label>
									<div class="col-sm-10">
										<form:input path="sansquaeval" htmlEscape="false"
											class="form-control " />
									</div>
								</div>

								<table class="table table-bordered">
									<tbody>
										<tr>
											<td class="width-15 active"><label class="pull-right"><font
													color="red">*</font>剩余时间：</label></td>
											<td class="width-35"><form:input path="dremaintimetimit"
													htmlEscape="false" class="form-control " /></td>
											<td class="width-15 active"><label class="pull-right"><font
													color="red">*</font>故障时限(分)：</label></td>
											<td class="width-35"><form:input path="dfaulttimelimit"
													htmlEscape="false" class="form-control " /></td>
										</tr>
										<tr>
											<td class="width-15 active"><label class="pull-right"><font
													color="red">*</font>故障历时(分)：</label></td>
											<td class="width-35">
												<div class=" input-group" style="width: 100%;">
													<form:input path="ddurtime" htmlEscape="false"
														class="form-control " />
												</div>
											</td>
											<td class="width-15 active"><label class="pull-right">网运时限(分)：</label></td>
											<td class="width-35"><form:input path="dntfaultime"
													htmlEscape="false" class="form-control " /></td>
										</tr>
										<tr>
											<td class="width-15 active"><label class="pull-right">网运剩余时间(分)：</label></td>
											<td class="width-35"><form:input path="dntiremain"
													htmlEscape="false" class="form-control " /></td>
											<td class="width-15 active"><label class="pull-right">受理时间：</label></td>
											<td class="width-35">
												<p class="input-group">
												<div class='input-group form_datetime' id='dfaultapttime'>
													<input type='text' name="dfaultapttime"
														class="form-control "
														value="<fmt:formatDate value="${documentarydetails.dfaultapttime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
													<span class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												</p>
											</td>
										</tr>
										<tr>
											<td class="width-15 active"><label class="pull-right"><font
													color="red">*</font>首次回应时间：</label></td>
											<td class="width-35">
												<p class="input-group">
												<div class='input-group form_datetime' id='dfirstreptime'>
													<input type='text' name="dfirstreptime"
														class="form-control "
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
													<input type='text' name="darchivetime"
														class="form-control "
														value="<fmt:formatDate value="${documentarydetails.darchivetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
													<span class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												</p>
											</td>
										</tr>
										<tr>
											<td class="width-15 active"><label class="pull-right"><font
													color="red">*</font>申告时间：</label></td>
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
								<ul id="detailsUl" class="nav nav-tabs">
									<li class="active"><a data-toggle="tab" href="#tab-1"
										aria-expanded="true">单据反馈信息：</a></li>
									<li class=""><a data-toggle="tab" href="#tab-2"
										aria-expanded="false">历史工单：</a></li>
								</ul>
								<a class="btn btn-white btn-sm" href="javascript:void(0);"
									id="detailmain_b" title="折叠"><i class="fa fa-plus"></i> </a>
								<div class="tab-content" id="deal_info_box">
									<div id="tab-1" class="tab-pane fade in  active">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="hide"></th>
													<th width="10">反馈序号</th>
													<th width="80">反馈部门</th>
													<th width="80">反馈人员</th>
													<th width="80">反馈人电话</th>
													<th width="80">反馈时间</th>
													<th width="80">反馈来源</th>
													<th>反馈内容</th>
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
				var feedbackRowIdx = 0, 
				feedbackTpl = $("#feedbackTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(documentarydetails.feedbackList)};
					for (var i=0; i<data.length; i++){
						addRow('#feedbackList', feedbackRowIdx, feedbackTpl, data[i]);
						feedbackRowIdx = feedbackRowIdx + 1;
					}
				});
			</script>
			</div>
			
				<div id="tab-2" class="tab-pane fade">
			
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>单号</th>
						<th><font color="red">*</font>申告时间</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="testDataChild2List">
				</tbody>
			</table>
			<script type="text/template" id="testDataChild2Tpl"> 
					//<!--
				<tr id="testDataChild2List{{idx}}">
					<td>
						<a onclick=newtable("{{row.sbillno}}")>{{row.sbillno}}</a>
					</td>
					<td>
						{{row.reportTime}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var testDataChild2RowIdx = 0, 
				testDataChild2Tpl = $("#testDataChild2Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(documentarydetails.repetitiveDocument)};
					for (var i=0; i<data.length; i++){
						addRow('#testDataChild2List', testDataChild2RowIdx, testDataChild2Tpl, data[i]);
						testDataChild2RowIdx = testDataChild2RowIdx + 1;
					}
				});
			</script>
			</div>
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