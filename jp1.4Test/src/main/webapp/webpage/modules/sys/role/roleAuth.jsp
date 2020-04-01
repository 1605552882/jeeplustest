<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>权限设置</title>
<meta name="decorator" content="ani" />
<%@include file="/webpage/include/treeview.jsp"%>
<script type="text/javascript">
	var validateForm;
	var $table; // 父页面table表格id
	var $topIndex;//弹出窗口的 index
	function doSubmit(table, index) {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		if (validateForm.form()) {
			$table = table;
			$topIndex = index;
			$("#inputForm").submit();
			return true;
		}

		return false;
	}
	$(document).ready(function() {

		//功能权限
		$('#menuTree').jstree({
			'core' : {
				"multiple" : true,
				"animation" : 0,
				"themes" : {
					"icons" : true,
					"stripes" : false
				},
				'data' : {
					"url" : "${ctx}/sys/menu/treeData?roleId=${role.id}",
					"dataType" : "json" // needed only if you do not supply JSON headers
				}
			},
			'plugins' : [ "checkbox", 'types', 'wholerow' ],
			"types" : {
				'default' : {
					'icon' : 'fa fa-file-text-o'
				},
				'html' : {
					'icon' : 'fa fa-file-code-o'
				},
				'btn' : {
					'icon' : 'fa fa-square'
				}
			},
			'checkbox' : {
				// 禁用级联选中  
				'three_state' : false,
				'cascade' : 'undetermined|up' //有三个选项，up, down, undetermined; 使用前需要先禁用three_state  
			},

		});
		//数据权限
		$('#dataRuleTree').jstree({
			'core' : {
				"multiple" : true,
				"animation" : 0,
				"themes" : {
					"icons" : true,
					"stripes" : false
				},
				'data' : {
					"url" : "${ctx}/sys/dataRule/treeData?roleId=${role.id}",
					"dataType" : "json" // needed only if you do not supply JSON headers
				}
			},
			'plugins' : [ "checkbox", 'types', 'wholerow' ],
			"types" : {
				'default' : {
					'icon' : 'fa fa-file-text-o'
				},
				'4' : {//权限type
					'icon' : ' fa fa-anchor'
				}
			},
			'checkbox' : {
				// 禁用级联选中  
				'three_state' : false,
				'cascade' : 'undetermined|up' //有三个选项，up, down, undetermined; 使用前需要先禁用three_state  
			},

		});

		validateForm = $("#inputForm").validate({

			submitHandler : function(form) {
				//功能权限
				var ref = $('#menuTree').jstree(true);
				var ids = ref.get_selected();
				//取半选节点ID
				$("#menuTree li").has("i[class*='jstree-undetermined']").each(function() {
					ids += "," + $(this).attr("id");
				});
				$("#menuIds").val(ids);
				debugger
				//数据权限
				var data_ref = $('#dataRuleTree').jstree(true);
				var nodes = data_ref.get_selected(true);
				var data_ids = new Array();
				for (var i = 0; i < nodes.length; i++) {

					if (nodes[i].type === '4') {
						data_ids.push(nodes[i].id);
					}
				}
				$("#dataRuleIds").val(data_ids.join(","));
				//用户组权限
				var datacityinfo = document.getElementById("datacityinfo");
				var cityinfo = "";
				$("input[type='checkbox']").each(function() {
					if ($(this).is(":checked")) {
						if($(this).val()!='quanxuan'){
							cityinfo += $(this).val() + "|";
						}
					} else {
					}
				});
				$("#network").val(cityinfo);
				jp.loading();
				$.post("${ctx}/sys/role/save", $('#inputForm').serialize(), function(data) {
					if (data.success) {
						jp.success(data.msg);

					} else {
						jp.error(data.msg);
					}

					jp.close($topIndex);//关闭dialog
				});
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});

		//获取后台传入的地市map
		var citysArraymap = "${citysArray}";
		//var reg=new RegExp('=',':');
		var str = citysArraymap.replace(/=/g, ":");//替换
		//转换对象
		var obj = eval('(' + str + ')');
		var i = 1;
		var datacityinfo = document.getElementById("datacityinfo");

		var htmldiv = "";
		var htmllabel = "";
		//加全选按钮
		var html = '<div><label><input type="checkbox" id="quanxuan" value="quanxuan">全选</label></div>';
		var getlocRight = '${role.network}';
		var locRight = getlocRight.split("|");
		for ( var key in obj) {
			if (i < 4) {
				if (getlocRight.indexOf(key) != -1) {
					htmllabel += '<label class="citylabe"><input type="checkbox" class="check" checked="checked" value="'+key+'">' + obj[key] + '</label>';
				} else {
					htmllabel += '<label class="citylabe"><input type="checkbox" class="check" value="'+key+'">' + obj[key] + '</label>';
				}

			}
			if (i == 3) {
				htmldiv += '<div class="checkbox">' + htmllabel + '</div>';
				html += htmldiv;
				htmllabel = "";
				htmldiv = "";
				i = 0;
			}

			i++;
		}
		datacityinfo.innerHTML = html;
		var isboolean = true;
		$("#quanxuan").click(function() {

			var quanxuan = document.getElementById('quanxuan');//全选
			var checkList = document.getElementsByClassName('check');//选择框列表

			//3.事件处理：选中所有选择框（设置checked属性为true）
			for (var i = 0; i < checkList.length; i++) {
				if (!isboolean) {
					checkList[i].checked = false;
				} else {
					checkList[i].checked = true;
				}
			}
			if (isboolean) {
				isboolean = false;
			} else {
				isboolean = true;
			}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab-1"
			aria-expanded="true">功能权限：</a></li>
		<li class=""><a data-toggle="tab" href="#tab-2"
			aria-expanded="false">数据权限：</a></li>
		<li class=""><a data-toggle="tab" href="#tab-3"
			aria-expanded="false">区域权限：</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab-1" class="tab-pane fade in  active">
			<div id="menuTree"></div>
		</div>
		<div id="tab-2" class="tab-pane fade in">
			<div id="dataRuleTree"></div>
		</div>
		<div id="tab-3" class="tab-pane fade in">
			<div id="datacityinfo" class="text-center"></div>
		</div>
	</div>
	<form:form id="inputForm" modelAttribute="role"
		action="${ctx}/sys/role/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<input name="office.id" type="hidden" value="${role.office.id}">
		<input name="office.name" type="hidden" value="${role.office.name}">
		<input name="name" type="hidden" value="${role.name}">
		<input name="oldName" type="hidden" value="${role.name}">
		<input name="enname" type="hidden" value="${role.enname}">
		<input name="oldEnname" type="hidden" value="${role.enname}">
		<input name="roleType" type="hidden" value="${role.roleType}">
		<input name="sysData" type="hidden" value="${role.sysData}">
		<input name="useable" type="hidden" value="${role.useable}">
		<input name="remarks" type="hidden" value="${role.remarks}">
		<form:hidden path="menuIds" />
		<form:hidden path="network" />
		<form:hidden path="dataRuleIds" />
	</form:form>
</body>
</html>