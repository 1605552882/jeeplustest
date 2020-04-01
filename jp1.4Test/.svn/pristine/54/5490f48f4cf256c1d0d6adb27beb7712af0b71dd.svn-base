<%@ page contentType="text/html;charset=UTF-8" %>
<script>

	$(document).ready(function() {
		$("#renzhinenglidefen").bind("click",function(){
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

    	sum+=addRadio("zhunagezhen");
    	
    	sum+=addRadio("zhunatiaolu");
    	
    	sum+=addRadio("jintianjihao");
    	
    	sum+=addRadio("yuefen");
    	
    	sum+=addRadio("nianfen");
    	
    	sum+=addRadio("yinianjitian");
    	
    	sum+=addRadio("jianguonianfen");
    	
    	sum+=addRadio("shuxueti");
    	
    	sum+=addRadio("currentpresidentname");
    	
    	sum+=addRadio("lastpresidentname");
    	
    	$("#renzhinenglidefen").val(sum);
    	
    	var grade =10;
    	
    	if(sum<=2){
    		grade =40;
    	}else if(sum>=3 && sum <=5){
    		grade =30;
    	}else if(sum >=6 && sum <= 7){
    		grade =20;
    	}else if(sum >= 8 ){
    		grade =10;
    	}
    	grade+=0;
    	

    	//$("[name=zhiliqingkuang]").eq(1).prop("checked",true);
    	//alert($("#zhiliqingkuang2").prop("checked"));
    	$("input:radio[name=zhiliqingkuang][value="+grade+"]").iCheck('check');
    	//$("#zhiliqingkuang2").prop("checked",true);
    	//$("#zhiliqingkuang2").iCheck('check');
    	//$("input[id='zhiliqingkuang2']").prop("checked", true);
    	 //$("input:radio[name=zhiliqingkuang][value=20]").attr("checked",true);
    	//$("input[name='zhiliqingkuang'][value='20']").attr("checked",true); 
    	//var rd=document.getElementsByName('zhiliqingkuang');
    	//rd[2].checked = true;
    	
    	//$("[name=zhiliqingkuang]").eq(1).attr("checked","checked");
    	//$("[name=zhiliqingkuang]").eq(1).click();
    	//$("input[name='zhiliqingkuang']:eq(0)").attr("checked",'checked'); 
    	//alert($("input[name='zhiliqingkuang']").get(0).html());
    	//$("input[name='zhiliqingkuang']").get(0).checked=true; 
    	//alert($("#zhiliqingkuang").val());
    	//$("#zhiliqingkuang").eq(1).prop("checked",true);
    	//$("#zhiliqingkuang").eq(1).click();
 //   	$("input[name='zhiliqingkuang']").eq(1).attr("checked","checked");
 //   	$("input[name='zhiliqingkuang']").eq(1).click();
    }

	
</script>