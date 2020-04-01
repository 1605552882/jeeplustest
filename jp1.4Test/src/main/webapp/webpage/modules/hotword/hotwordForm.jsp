<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>热词管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			showsubs();
			
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
			$(document).on('click','#delCenterIpGrp',function(){ 
				  var el = this.parentNode.parentNode ;
				  var centerIp = $(this).parent().parent().find('#ipInput').val()
				  jp.confirm("是否删除",function(){
					  el.parentNode.removeChild(el);
					  jointsubs();
				  })
			});
		});
		
		//添加输入框项 
		//二级菜单
		function addsubs(obj,val){ 
			if(val==null){
				val="";
			}
			  var html = '<div class="input-group col-sm-12 ">'+ 
			        '<input class="form-control " type="text" id="subs" onBlur="jointsubs()" value="'+val+'">'+ 
			        '<span class="input-group-btn">'+ 
			              '<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" id="delCenterIpGrp"><span class="glyphicon glyphicon-minus"></span></button>'+ 
			        '</span>'+ 
			      '</div>'
			  obj.insertAdjacentHTML('beforebegin',html) 
		}
		function jointsubs(){
			var sub ="";
			$("input[id='subs']").each(function(){
	            //$(this).attr("name")  每个input框name的值
	            var val = $(this).val().trim();
	            if(val!=""){
	            	sub += val + "," //拼接
	            }
	        });
			if(sub!=""){
				sub = sub.substring(0,sub.length-1);
			}
			$("#sub").val(sub);
		}
		
		
		//回显二级菜单
		function showsubs(){
			var sub = $("#sub").val();
			var arr = sub.split(",");
			for(var i=0;i<arr.length; i++){
				if(i==0){
					$("#subs").val(arr[i]);
				}else{
					addsubs(document.getElementById("addsubsBtn"),arr[i]);
				}
			}
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
				<a class="panelButton" href="${ctx}/hotword/hotword"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="hotword" action="${ctx}/hotword/hotword/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
				<div class="form-group">
					<label class="col-sm-2 control-label">热词：</label>
					<div class="col-sm-10">
						<form:input path="hotword" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				
				<form:hidden path="sub"/>
				 <div class="form-group">
					<label class="col-sm-2 control-label" title="二级菜单：">二级菜单：</label>
					<div class="well input-group col-sm-10">
						<div class="input-group col-sm-12 ">
							<input class="form-control " type="text" id="subs" onBlur="jointsubs()">
							<span class="input-group-btn">
								<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" id="delCenterIpGrp"><span class="glyphicon glyphicon-minus"></span></button>
							</span>
						</div>
						<button class="btn btn-info" type="button" data-toggle="tooltip" title="新增" id="addsubsBtn" onclick="addsubs(this)" ><span class="glyphicon glyphicon-plus"></span></button>
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
				</div>
		<c:if test="${fns:hasPermission('hotword:hotword:edit') || isAdd}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
		                 </div>
		             </div>
		        </div>
		</c:if>
		</form:form>
		</div>				
	</div>
	</div>
</div>
</div>
</body>
</html>