<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工单检测规则配置管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					var r = checkSprocesssummarys();
					if(r){
						jp.loading();
						form.submit();
					}
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
	
	$(document).on('click','#delCenterIpGrp',function(){ 
		  var el = this.parentNode.parentNode ;
		  var centerIp = $(this).parent().parent().find('#ipInput').val()
		  jp.confirm("是否删除",function(){
			  el.parentNode.removeChild(el);
		  })
	});
	//添加输入框项 
	//故障内容
	function addsrepfaultdetail(obj){ 
		  var html = '<div class="input-group col-sm-12 ">'+ 
		        '<input class="form-control " type="text" id="srepfaultdetails" onBlur="jointsrepfaultdetail()">'+ 
		        '<span class="input-group-btn">'+ 
		              '<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" id="delCenterIpGrp"><span class="glyphicon glyphicon-minus"></span></button>'+ 
		        '</span>'+ 
		      '</div>'
		  obj.insertAdjacentHTML('beforebegin',html) 
	}
	function jointsrepfaultdetail(){
		var srepfaultdetail ="";
		$("input[id='srepfaultdetails']").each(function(){
            //$(this).attr("name")  每个input框name的值
            srepfaultdetail += $(this).val().trim() + ":" //拼接
        });
		
		$("#srepfaultdetail").val(srepfaultdetail);
	}
	//结单信息
	function addsprocesssummarys(obj){ 
		  var html = '<div class="input-group col-sm-12 ">'+ 
				        '<input class="form-control" type="text"  id="sprocesssummarys" onBlur="jointsprocesssummarys()"/>'+ 
				        '<span class="input-group-btn">'+ 
				              '<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" id="delCenterIpGrp"><span class="glyphicon glyphicon-minus"></span></button>'+ 
				        '</span>'+ 
		  			'</div>';
		  obj.insertAdjacentHTML('beforebegin',html) 
	}
	function jointsprocesssummarys(){
		checkSprocesssummarys();
		var srepfaultdetail ="";
		$("input[id='sprocesssummarys']").each(function(){
            //$(this).attr("name")  每个input框name的值
            srepfaultdetail += $(this).val().trim() + ":" //拼接
        });
		
		$("#sprocesssummary").val(srepfaultdetail);
	}
	
	//结单信息不能包含中文逗号
	function checkSprocesssummarys(){
		var result = true;
		$("input[id='sprocesssummarys']").each(function(){
			if($(this).val().trim().indexOf("，") >= 0){
				debugger
				$("#sprocesssummaryTip").show();
				result = false;
			}
        });
		if(result){
			$("#sprocesssummaryTip").hide();
		}
		return result;
	}
	</script>
</head>
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/documentconfig/documentconfig?isBack=true"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="documentconfig" action="${ctx}/documentconfig/documentconfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="sprocesssummary"/>
		<form:hidden path="srepfaultdetail"/>
		<sys:message content="${message}"/>	
			<table class="table table-bordered">
			   <tbody>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>业务类型：</label></td>
						<td class="width-35">
							<form:select path="busytype" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('document_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>故障分类：</label></td>
						<td class="width-35">
							<form:input path="faultclass" htmlEscape="false"    class="form-control "/>
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>故障内容：</label></td>
						<td class="width-35">
							<div class="well input-group col-sm-12">
								<button class="btn btn-info" type="button" data-toggle="tooltip" title="新增" id="addsrepfaultdetailBtn" onclick="addsrepfaultdetail(this)" ><span class="glyphicon glyphicon-plus"></span></button>
								<%-- <form:input path="srepfaultdetail" htmlEscape="false"    class="form-control "/> --%>
							</div>
						</td>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>结单信息：</label><label class="pull-right" id="sprocesssummaryTip" hidden="hidden"><font color="red">结单信息用英文逗号分隔，不能包含中文逗号</font></label></td>
						<td class="width-35">
							<div class="well input-group col-sm-12">
								<button class="btn btn-info" type="button" data-toggle="tooltip" title="新增" id="addsprocesssummarysBtn" onclick="addsprocesssummarys(this)" ><span class="glyphicon glyphicon-plus"></span></button> 
								<%-- <form:input path="sprocesssummary" htmlEscape="false"    class="form-control "/> --%>
							</div>
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>反馈信息：</label></td>
						<td class="width-35">
							<form:input path="feedbackrule" htmlEscape="false"    class="form-control "/>
						</td>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>检查规则：</label></td>
						<td class="width-35">
							<form:select path="rule" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('document_rule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>检查结论：</label></td>
						<td class="width-35">
							<form:input path="result" htmlEscape="false"    class="form-control "/>
						</td>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属科室：</label></td>
						<td class="width-35">
							<form:input path="office" htmlEscape="false"    class="form-control "/>
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否启用：</label></td>
						<td class="width-35">
							<form:select path="flag" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('config_use')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						</td>
					</tr>
				</tbody>
			</table>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">业务类型：</label>
					<div class="col-sm-10">
						<form:select path="busytype" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('document_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障分类：</label>
					<div class="col-sm-10">
						<form:input path="faultclass" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">故障内容：</label>
					<div class="well input-group col-sm-10">
						<button class="btn btn-info" type="button" data-toggle="tooltip" title="新增" id="addsrepfaultdetailBtn" onclick="addsrepfaultdetail(this)" ><span class="glyphicon glyphicon-plus"></span></button>
						<form:input path="srepfaultdetail" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group"> 
					<label class="col-sm-2 control-label" >结单信息: </label>
					<div class="well input-group col-sm-10">
						<button class="btn btn-info" type="button" data-toggle="tooltip" title="新增" id="addsprocesssummarysBtn" onclick="addsprocesssummarys(this)" ><span class="glyphicon glyphicon-plus"></span></button> 
					</div>
				 </div> 
				<div class="form-group">
					<label class="col-sm-2 control-label">反馈信息：</label>
					<div class="col-sm-10">
						<form:input path="feedbackrule" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">检查规则：</label>
					<div class="col-sm-10">
						<form:select path="rule" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('document_rule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">检查结论：</label>
					<div class="col-sm-10">
						<form:input path="result" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">所属科室：</label>
					<div class="col-sm-10">
						<form:input path="office" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否启用：</label>
					<div class="col-sm-10">
						<form:select path="flag" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('config_use')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div> --%>
				
		<c:if test="${fns:hasPermission('documentconfig:documentconfig:edit') || isAdd}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
		                 </div>
		             </div>
		        </div>
		</c:if>
		<script type="text/javascript">
			$(document).ready(function() {
				var data1 = ${fns:toJson(documentconfig.srepfaultdetail)}; //故障内容
				var data = ${fns:toJson(documentconfig.sprocesssummary)};//结单信息
				
				var array = data1.split(":");
				for (var i = 0 ; i < array.length ;i ++) {
					var html = '<div class="input-group col-sm-12 ">'+ 
							        '<input class="form-control" type="text"  id="srepfaultdetails" onBlur="jointsrepfaultdetail()"/>'+ 
							        '<span class="input-group-btn">'+ 
							              '<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" id="delCenterIpGrp"><span class="glyphicon glyphicon-minus"></span></button>'+ 
							        '</span>'+ 
			      			  '</div>';
			      $("#addsrepfaultdetailBtn").before(html);
			      $("[id=srepfaultdetails]").eq(i).val(array[i]);
				}
				
				var array = data.split(":");
				for (var i = 0 ; i < array.length ;i ++) {
					var html = '<div class="input-group col-sm-12 ">'+ 
							        '<input class="form-control" type="text"  id="sprocesssummarys" onBlur="jointsprocesssummarys()"/>'+ 
							        '<span class="input-group-btn">'+ 
							              '<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" id="delCenterIpGrp"><span class="glyphicon glyphicon-minus"></span></button>'+ 
							        '</span>'+ 
			      			  '</div>';
			      $("#addsprocesssummarysBtn").before(html);
			      $("[id=sprocesssummarys]").eq(i).val(array[i]);
				}
				
			})
		
		</script>
		</form:form>
		</div>				
	</div>
	</div>
</div>
</div>
</body>

</html>