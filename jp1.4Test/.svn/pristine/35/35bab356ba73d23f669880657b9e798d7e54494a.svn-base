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

    	sum+=addRadio("jinshi");
    	
    	sum+=addRadio("xizao");
    	
    	sum+=addRadio("gerenweisheng");
    	
    	sum+=addRadio("chuangyi");
    	
    	sum+=addRadio("dabiankongzhi");
    	
    	sum+=addRadio("xiaobiankongzhi");
    	
    	sum+=addRadio("ruce");
    	
    	sum+=addRadio("chuangyizhuanyi");
    	
    	sum+=addRadio("buxing");
    	
    	sum+=addRadio("shangluolouti");
    	
    	$("#score").val(sum);

    }

	
</script>