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

    	sum+=addRadio("cengyoushuaidaijilu");
    	
    	sum+=addRadio("duoyuyixiangyiliaozhengduan");
    	
    	sum+=addRadio("buxingfuzhuqi");
    	
    	sum+=addRadio("jingmaizhushe");
    	
    	sum+=addRadio("butai");
    	
    	sum+=addRadio("jingshenzhuangtai");
    	
    	
    	
    	$("#score").val(sum);

    }

	
</script>