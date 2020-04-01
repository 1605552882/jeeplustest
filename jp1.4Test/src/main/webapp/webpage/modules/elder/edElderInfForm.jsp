<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>长者基本资料管理</title>
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
					jp.post("${ctx}/elder/edElderInf/save",$('#inputForm').serialize(),function(data){
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
			
	        $('#chushengriqi').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#ruzhuriqi').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#riqi').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
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
		<form:form id="inputForm" modelAttribute="edElderInf" action="${ctx}/elder/edElderInf/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35">
						<form:input path="funame" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35">
						<form:radiobuttons path="sex" items="${fns:getDictList('male_or_femal')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">年龄：</label></td>
					<td class="width-35">
						<form:input path="nianling" htmlEscape="false"    class="form-control  isIntGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">出生日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='chushengriqi'>
			                    <input type='text'  name="chushengriqi" class="form-control "  value="<fmt:formatDate value="${edElderInf.chushengriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>						            
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">入住日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='ruzhuriqi'>
			                    <input type='text'  name="ruzhuriqi" class="form-control "  value="<fmt:formatDate value="${edElderInf.ruzhuriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>						            
			            </p>
					</td>
					<td class="width-15 active"><label class="pull-right">婚姻状况：</label></td>
					<td class="width-35">
						<form:radiobuttons path="hunyinzhuangkuang" items="${fns:getDictList('marrige_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">婚姻状况其他：</label></td>
					<td class="width-35">
						<form:input path="hunyinzhuangkuangqita" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">日常交流语言：</label></td>
					<td class="width-35">
						<form:radiobuttons path="richangjiaoliuyuyan" items="${fns:getDictList('speak_language')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">日常交流语言其他：</label></td>
					<td class="width-35">
						<form:input path="richangjiaoliuyuyanqita" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">教育程度：</label></td>
					<td class="width-35">
						<form:radiobuttons path="jiaoyuchengdu" items="${fns:getDictList('educational_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">教育程度其他：</label></td>
					<td class="width-35">
						<form:input path="jiaoyuchengduqita" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">现居住地：</label></td>
					<td class="width-35">
						<form:input path="xianjuzhudi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">同居人员：</label></td>
					<td class="width-35">
						<form:input path="tongjurenyuan" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">退休前职业：</label></td>
					<td class="width-35">
						<form:input path="tuixiuqianzhiye" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">宗教：</label></td>
					<td class="width-35">
						<form:radiobuttons path="zongjiao" items="${fns:getDictList('region_list')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">宗教其他：</label></td>
					<td class="width-35">
						<form:input path="zongjiaoqita" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人1：</label></td>
					<td class="width-35">
						<form:input path="lianxiren1" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系人1关系：</label></td>
					<td class="width-35">
						<form:input path="lianxiren1guanxi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人1电话：</label></td>
					<td class="width-35">
						<form:input path="lianxirendianhua" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系人1地址：</label></td>
					<td class="width-35">
						<form:input path="lianxiren1dizhi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人2：</label></td>
					<td class="width-35">
						<form:input path="lianxiren2" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系人2关系：</label></td>
					<td class="width-35">
						<form:input path="lianxiren2guanxi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人2电话：</label></td>
					<td class="width-35">
						<form:input path="lianxiren2dianhua" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系人2地址：</label></td>
					<td class="width-35">
						<form:input path="lianxiren2dizhi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">每月收入来源：</label></td>
					<td class="width-35">
						<sys:checkbox id="meiyueshourlaiyuan" name="meiyueshourlaiyuan" items="${fns:getDictList('income_list')}" values="${edElderInf.meiyueshourlaiyuan}" cssClass="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">退休金¥：</label></td>
					<td class="width-35">
						<form:input path="tuixiujin" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">亲友资助¥：</label></td>
					<td class="width-35">
						<form:input path="qinyouzizhu" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">五保户¥：</label></td>
					<td class="width-35">
						<form:input path="wubaohu" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">低保户¥：</label></td>
					<td class="width-35">
						<form:input path="dibaohu" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">其他政府资助¥：</label></td>
					<td class="width-35">
						<form:input path="qitazhengfuzizhu" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他资助¥：</label></td>
					<td class="width-35">
						<form:input path="qitazizhu" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right">总金额¥：</label></td>
					<td class="width-35">
						<form:input path="zongjine" htmlEscape="false"    class="form-control  isFloatGtZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他数据：</label></td>
					<td class="width-35">
						<form:input path="qitashuju" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">医疗费用支付方式：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yiliaofeiyongzhifufangshi" items="${fns:getDictList('medical_paytype')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">医疗费用支付方式其他：</label></td>
					<td class="width-35">
						<form:input path="yiliaofeiyongzhifufangshiqita" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">兴趣-看电视：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xqkandianshi" items="${fns:getDictList('interest_freq')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">兴趣-听收音机：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xqtingshouyinji" items="${fns:getDictList('interest_freq')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">兴趣-阅报阅读：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xqyuebaoyuedu" items="${fns:getDictList('interest_freq')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">兴趣-做运动：</label></td>
					<td class="width-35">
						<form:radiobuttons path="xqzuoyundong" items="${fns:getDictList('interest_freq')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">生活习惯：</label></td>
					<td class="width-35">
						<form:input path="shenghuoxiguan" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">病历：</label></td>
					<td class="width-35">
						<sys:checkbox id="bingli" name="bingli" items="${fns:getDictList('medical_record')}" values="${edElderInf.bingli}" cssClass="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">关节炎位置：</label></td>
					<td class="width-35">
						<form:input path="guanjieyanweizhi" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">癌症位置：</label></td>
					<td class="width-35">
						<form:input path="aizhengweizhi" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">精神病：</label></td>
					<td class="width-35">
						<form:input path="jingshenbing" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">其他内容：</label></td>
					<td class="width-35">
						<form:input path="qitaneirong" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">曾施手术内容：</label></td>
					<td class="width-35">
						<form:input path="cengshishoushu" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">食物敏感：</label></td>
					<td class="width-35">
						<form:radiobuttons path="shiwumingan" items="${fns:getDictList('yes_or_not')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">药物敏感：</label></td>
					<td class="width-35">
						<form:radiobuttons path="yaowumingan" items="${fns:getDictList('yes_or_not')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">食物敏感内容：</label></td>
					<td class="width-35">
						<form:input path="shiwuminganneirong" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">药物敏感内容：</label></td>
					<td class="width-35">
						<form:input path="yaowuminganneirong" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">与长者关系：</label></td>
					<td class="width-35">
						<form:input path="yuzhangzheguanxi" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">长者/家属：</label></td>
					<td class="width-35">
						<form:input path="zhangzhehuojiashu" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='riqi'>
			                    <input type='text'  name="riqi" class="form-control "  value="<fmt:formatDate value="${edElderInf.riqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>						            
			            </p>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">家庭状况：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#edFamilyInfList', edFamilyInfRowIdx, edFamilyInfTpl);edFamilyInfRowIdx = edFamilyInfRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th>姓名</th>
						<th>年龄</th>
						<th>关系</th>
						<th>性别</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="edFamilyInfList">
				</tbody>
			</table>
			<script type="text/template" id="edFamilyInfTpl">//<!--
				<tr id="edFamilyInfList{{idx}}">
					<td class="hide">
						<input id="edFamilyInfList{{idx}}_id" name="edFamilyInfList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="edFamilyInfList{{idx}}_delFlag" name="edFamilyInfList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="edFamilyInfList{{idx}}_fullname" name="edFamilyInfList[{{idx}}].fullname" type="text" value="{{row.fullname}}"    class="form-control "/>
					</td>
					
					
					<td>
						<input id="edFamilyInfList{{idx}}_nianling" name="edFamilyInfList[{{idx}}].nianling" type="text" value="{{row.nianling}}"    class="form-control  isIntGtZero"/>
					</td>
					
					
					<td>
						<input id="edFamilyInfList{{idx}}_guanxi" name="edFamilyInfList[{{idx}}].guanxi" type="text" value="{{row.guanxi}}"    class="form-control "/>
					</td>
					
					
					<td>
						<c:forEach items="${fns:getDictList('male_or_femal')}" var="dict" varStatus="dictStatus">
							<span><input id="edFamilyInfList{{idx}}_sex${dictStatus.index}" name="edFamilyInfList[{{idx}}].sex" type="radio" class="i-checks" value="${dict.value}" data-value="{{row.sex}}"><label for="edFamilyInfList{{idx}}_sex${dictStatus.index}">${dict.label}</label></span>
						</c:forEach>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#edFamilyInfList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var edFamilyInfRowIdx = 0, edFamilyInfTpl = $("#edFamilyInfTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(edElderInf.edFamilyInfList)};
					for (var i=0; i<data.length; i++){
						addRow('#edFamilyInfList', edFamilyInfRowIdx, edFamilyInfTpl, data[i]);
						edFamilyInfRowIdx = edFamilyInfRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>