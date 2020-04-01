<%@ page contentType="text/html;charset=UTF-8" %>
<script>

	$(document).ready(function() {
		$("#elderidName").bind("change",function(){
			//alert("change! "+$("#elderidId").val());
			loadData($("#elderidId").val());
		});
	});
	
	
	function loadData(elderid){
		$.ajax({ 
	            type: "post", 
                url: "${ctx}/nursingclass/edNursingclassLogEx/basedata", 
                data:{ "elderid":elderid},
                dataType: "json", 
                success: function (data) { 
                      //  $("input#showTime").val(data[0].demoData); 
                	console.log(data);  
                	console.log(data.body.zhili);
                	
                	if(data.body.fenji != ""){
                		$("input:radio[name=richanghuodongnengli][value="+data.body.fenji+"]").iCheck('check');
                	}
                	if(data.body.zhili != ""){
                		$("input:radio[name=zhili][value="+data.body.zhili+"]").iCheck('check');
                	}
                	if(data.body.huli != ""){
                		$("input:radio[name=hulijibie][value="+data.body.huli+"]").iCheck('check');
                	}
                }, 
                error: function (XMLHttpRequest, textStatus, errorThrown) { 
                	console.log(textStatus);  
                } 
	           });
	}
/*
 {"success":true,"errorCode":"-1","msg":"操作成功","body":{"fenji":"20","zhili":"","huli":""}}
 */
	
</script>