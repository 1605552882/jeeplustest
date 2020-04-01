<%@ page contentType="text/html;charset=UTF-8" %>
<script>

	$(document).ready(function() {
		$("#score").bind("click",function(){
			doSum();
		});
	});

	function addRadio(name){
		var obj = $("[name='"+name+"']").filter(":checked");
		if(obj != null){
    		return parseInt(obj.val());
    	}else{
    		return 0;
    	}
	}
	
    function doSum(){
    	var sum = 0;

    	sum+=addRadio("ganguanganjue");
    	
    	sum+=addRadio("chaoshiqingkuang");
    	
    	sum+=addRadio("huodongqingkuang");
    	
    	sum+=addRadio("yidongqingkuang");
    	
    	sum+=addRadio("mocali");
    	
    	sum+=addRadio("yinyangqingkuang");
    	
    	
    	
    	$("#score").val(sum);

    }

	
</script>