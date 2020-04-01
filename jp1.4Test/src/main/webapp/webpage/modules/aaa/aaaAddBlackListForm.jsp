<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>加入黑名单窗口</title>
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
			var aaaQueryParam = JSON.parse(sessionStorage.getItem("aaaQueryParam"));
			var numberType = aaaQueryParam.searchType;
			var operateNumber = aaaQueryParam.userNoValue;
			//显示
			$("#numberTypeStr").html(numberType==0?"IMSI号码：":"手机号码：");
			//表单
			$("#numberType").val(numberType);
			$("#operateNumber").val(operateNumber);
			
			 //初始化开始时间和结束时间
			 var start = new Date();
			 start.setHours(0);
			 start.setMinutes(0);
			 start.setSeconds(0);
			 var end = new Date();
			 end.setHours(23);
			 end.setMinutes(59);
			 end.setSeconds(59);
			 $('#startTime').datetimepicker({
				 format: "HH:mm:ss",
				 defaultDate:start
			});
			$('#endTime').datetimepicker({
				 format: "HH:mm:ss",
				 defaultDate:end
			});
			
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/aaa/aaa/addBlackList",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	var sucDetail ="<font color='green'>"+data.msg+"</font>";
		                	top.layer.alert(sucDetail , {
		                		  icon: 1,
		                		  area:['auto','auto'],
		                		  title:"请求成功"
		                	})
	                    	jp.close($topIndex);//关闭dialog
	                    }else{
	                		var errDetail ="<font color='red'>"+data.msg+"</font>";
		                	top.layer.alert(errDetail , {
		                		  icon: 2,
		                		  area:['auto','auto'],
		                		  title:"请求出错"
		                	})
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
	</script>
</head>
<body class="bg-white">
		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right" id="numberTypeStr">手机号码：</label></td>
					<td class="width-35">
						<input id="operateNumber" name="operateNumber" class="form-control" readonly="readonly" />
						<input id="numberType" hidden="hidden" name="numberType" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
		   			<td class="width-35">
						<div class='input-group date' id='startTime' >
		                   <input type='text'  name="startTime" class="form-control"  />
		                   <span class="input-group-addon">
		                       <span class="glyphicon glyphicon-calendar"></span>
		                   </span>
			            </div>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">结束时间：</label></td>
		   			<td class="width-35">
						<div class='input-group date' id='endTime' >
		                   <input type='text'  name="endTime" class="form-control"  />
		                   <span class="input-group-addon">
		                       <span class="glyphicon glyphicon-calendar"></span>
		                   </span>
			            </div>
			        </td>
		  		</tr>
		 	</tbody>
		</table>
	</form>
</body>
</html>