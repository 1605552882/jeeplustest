<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA开户集成窗口</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		//1.自定义校验规则
		$.validator.addMethod(
		    //mdn规则
		    "checkMdn",
		    function(value,element,params){
		    	var reg = /^(((13|15|17|18|19)[0-9]{9})|((147)[0-9]{8}))$/;
			 	return reg.test(value);
		    }
		);
		$.validator.addMethod(
		    //imsi规则
		    "checkImsi",
		    function(value,element,params){
		    	var reg = /^(((46003)|(20404)|(45404)|(45403))[0-9]{10})$/;
			   	return reg.test(value);
		    }
		);
	
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
			  $topIndex = index;
			  $("#inputForm").submit();
			  return true;
		  }
		  return false;
		}

		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				//2.使用自定义规则
				rules:{
					"cMDN":{
						"required":true,
		                "checkMdn":true
					},
					"cIMSI":{
						"required":true,
		                "checkImsi":true
					},
					"cUserType":{
						"required":true
					},
					"gIMSI":{
						"required":true,
		                "checkImsi":true
					},
					"gUserType":{
						"required":true
					}
				},
				//3.自定义规则显示错误信息
				messages:{
					"cMDN":{
						"required":"手机号码不能为空",
		                "checkMdn":"请填写正确的手机号码"
					},
					"cIMSI":{
						"required":"IMSI号码不能为空",
		                "checkImsi":"请填写正确的IMSI号码"
					},
					"cUserType":{
						"required":"请选择C网预付费类型"
					},
					"gIMSI":{
						"required":"IMSI号码不能为空",
		                "checkImsi":"请填写正确的IMSI号码"
					},
					"gUserType":{
						"required":"请选择G网预付费类型"
					}
				},
				submitHandler: function(form){
					jp.post("${ctx}/aaa/aaa/integratedOpenAccount",$('#inputForm').serialize(),function(data){
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
					}else {
						error.insertAfter(element);
					}
				}
			});
			
			//是否为CG国际漫游
			$("#openGWebCB").change(function(){
				var ck = $(this).prop("checked");
				//openGWeb为Y或者N
				if(ck){
					gWebEnabled();
					$("#openGWeb").val("Y");
				}else{
					gWebDisabled();
					$("#openGWeb").val("N");
				}
			});
			
			//G网可编辑
			function gWebEnabled(){
				$("#gIMSI").attr("disabled",false);
				$("#gUserType").attr("disabled",false);
			}
			
			//G网不可编辑
			gWebDisabled();
			function gWebDisabled(){
				$("#gIMSI").attr("disabled",true);
				$("#gUserType").attr("disabled",true);
			}
			
			//C网和G网付费类型
			setCGUserType();
			function setCGUserType(){
				var aaafflx = getStoreByType("aaafflx");
				for(var i=0; i<aaafflx.totalCount; i++){
					$("#cUserType,#gUserType").append("<option value="+aaafflx.codingList[i].intValue+">"+aaafflx.codingList[i].chineseDescription+"</option>");
				}
			}
		});
		
		function getStoreByType(typeId){
			var result;
            $.ajax({
                type: "POST",
                url: "${ctx}/basisCoding/getListStoreByTypeFK",
                data: {"type":typeId},
                dataType: "json",
                async: false,
                success: function(data) {
                	result = data;
                }
            });
            return result;
		}
	</script>
</head>
<body class="bg-white">
		<form id="inputForm" class="form-horizontal">
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">手机号码：</label></td>
					<td class="width-35">
						<input class="form-control" id="cMDN" name="cMDN"/>
					</td>
					<td class="width-15 active"><label class="pull-right">CG国际漫游：</label></td>
		   			<td class="width-35">
						<input type="checkbox" id="openGWebCB"
						<shiro:lacksPermission name="aaa:aaa:cgkaihu">
							disabled="disabled"
						</shiro:lacksPermission>
						/><label for="openGWebCB">是否为CG国际漫游</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">C网IMSI号码：</label></td>
					<td class="width-35">
						<input class="form-control" id="cIMSI"  name="cIMSI"/>
					</td>
					<td class="width-15 active"><label class="pull-right">G网IMSI号码：</label></td>
					<td class="width-35">
						<input class="form-control" id="gIMSI" name="gIMSI"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">C网付费类型：</label></td>
					<td class="width-35">
						<select class="form-control" id="cUserType" name="cUserType">
							<option value="" disabled selected hidden="hidden">请选择C网付费类型</option>
						</select>
					</td>
					<td class="width-15 active"><label class="pull-right">G网付费类型：</label></td>
					<td class="width-35">
						<select class="form-control" id="gUserType" name="gUserType">
							<option value="" disabled selected hidden="hidden">请选择G网付费类型</option>
						</select>
					</td>
		  		</tr>
		 	</tbody>
		</table>
		<input id="openGWeb" name="openGWeb" value="N" hidden="hidden" />
	</form>
</body>
</html>