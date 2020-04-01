<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>AAA业务更改窗口</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
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
			
			var aaaInfo = JSON.parse(sessionStorage.getItem("aaaInfo"));
			$("#operateNumber").val(aaaInfo[0].aaaMDN);
			
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					var businessType = $("input[name='business']:checked").val();
					var operateNumber = $("#operateNumber").val();
					var operateParams = $("#"+businessType).val();
					if(typeof(businessType)=="undefined"){
						jp.warning("您没有做任何修改，请选择一种操作！");
					}else if(typeof(operateParams)=="undefined" || operateParams==""){
						jp.warning("请选择你要修改的参数！");
					}else{
						jp.loading();
						jp.post("${ctx}/aaa/aaa/businessModify",{"businessType":businessType,"operateNumber":operateNumber,"operateParams":operateParams},function(data){
							if(data.success){
		                    	$table.bootstrapTable('refresh');
		                    	jp.success(data.msg);
		                    	jp.close($topIndex);//关闭dialog
		                    }else{
	            	  			jp.error(data.msg);
		                    }
						})
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
			
			//初始化
			init();
			function init(){
				var aaagszlx = getStoreByType("aaagszlx");//归属组
				var aaamyqx = getStoreByType("aaamyqx");//漫游权限
				var aaapertypeset = getStoreByType("aaapertypeset");//接入类型
				for(var i=0; i<aaagszlx.codingList.length; i++){
					$("#GSZ").append("<option value="+aaagszlx.codingList[i].englishDescription+">"+aaagszlx.codingList[i].chineseDescription+"</option>");
				}
				for(var i=0; i<aaamyqx.codingList.length; i++){
					$("#MYQX").append("<option value="+aaamyqx.codingList[i].intValue+">"+aaamyqx.codingList[i].chineseDescription+"</option>");
				}
				for(var i=0; i<aaapertypeset.codingList.length; i++){
					$("#JRLX").append("<option value="+aaapertypeset.codingList[i].intValue+">"+aaapertypeset.codingList[i].englishDescription+"</option>");
				}
			}
			
			$("#tfjDiv,#jjsDiv,#vpdnDiv,#bdywDiv,#gszDiv,#fflxDiv,#myqxDiv,#jrlxDiv").hide();

			$("input[name='business']").click(function(){
				$("#tfjDiv,#jjsDiv,#vpdnDiv,#bdywDiv,#gszDiv,#fflxDiv,#myqxDiv,#jrlxDiv").hide();
				var businessType = $(this).val();
				switch(businessType){
				case 'TFJ':
					$("#tfjDiv").show();
					break;
				case 'JJS':
					$("#jjsDiv").show();
					break;
				case 'VPDN':
					$("#vpdnDiv").show();
					break;
				case 'BDYW':
					$("#bdywDiv").show();
					break;
				case 'GSZ':
					$("#gszDiv").show();
					break;
				case 'FFLX':
					$("#fflxDiv").show();
					break;
				case 'MYQX':
					$("#myqxDiv").show();
					break;
				case 'JRLX':
					$("#jrlxDiv").show();
					break;
				default:
					break;
				}
			});
			
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
					<td class="width-35" colspan="2">
						<input id="operateNumber" class="form-control" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="TFJ">
						停复机</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="JJS">
						卡业务受限加解锁</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="VPDN">
						卡绑定VPDN域名</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="BDYW">
						卡绑定业务</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="GSZ">
						卡归属组</label>
					</td>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="FFLX">
						卡付费类型</label>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active">
						<label class=""><input type="radio" name="business" value="MYQX">
						漫游权限</label>
					</td>
					<td class="width-15 active" colspan="2">
						<label class=""><input type="radio" name="business" value="JRLX">
						修改接入类型</label>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form>
	
	<div class="" style="margin:0 15px 0 15px">
		<div class="col-xs-12" id="tfjDiv">
			<label class="col-xs-4">停复机：</label>
			<select class="col-xs-8" id="TFJ" name="TFJ">
				<option value='1'>停机</option>
				<option value='0'>复机</option>
				<option value='3'>绿通</option>
			</select>
		</div>
		<div class="col-xs-12" id="jjsDiv">
			<label class="col-xs-4">加解锁：</label>
			<select class="col-xs-8" id="JJS" name="JJS">
				<option value='0'>解锁</option>
				<option value='1'>加锁</option>
			</select>
		</div>
		<div class="col-xs-12" id="vpdnDiv">
			<label class="col-xs-4">VPDN域名：</label>
			<input class="col-xs-8" id="VPDN" name="VPDN"/>
		</div>
		<div class="col-xs-12" id="bdywDiv">
			<label class="col-xs-4">绑定业务：</label>
			<select class="col-xs-8" id="BDYW" name="BDYW">
				<option value='ctbb@mycdma.cn'>黑莓用户组</option>
			</select>
		</div>
		<div class="col-xs-12" id="gszDiv">
			<label class="col-xs-4">业务归属组：</label>
			<select class="col-xs-8" id="GSZ" name="GSZ">
			</select>
		</div>
		<div class="col-xs-12" id="fflxDiv">
			<label class="col-xs-4">付费类型：</label>
			<select class="col-xs-8" id="FFLX" name="FFLX">
				<option value="0">后付费</option>
				<option value="1">预付费</option>
			</select>
		</div>
		<div class="col-xs-12" id="myqxDiv">
			<label class="col-xs-4">漫游权限：</label>
			<select class="col-xs-8" id="MYQX" name="MYQX">
			</select>
		</div>
		<div class="col-xs-12" id="jrlxDiv">
			<label class="col-xs-4">修改接入类型：</label>
			<select class="col-xs-8" id="JRLX" name="JRLX">
			</select>
		</div>
	</div>
</body>
</html>