<%@ page contentType="text/html;charset=UTF-8" %>
<script>
	$(document).ready(function() {
		$("#tizhongzhibiao").bind("click",function(){
			doCalcBMI();
		});
	});
	
	
	function doCalcBMI(){
		var tizhong = $("#tizzhong").val();
		var shenggao = $("#shengao").val();
		alert(tizhong+'  '+shenggao);
		var bmi = tizhong/(shenggao^2);
        bmi= bmi.toFixed(2);
        $("#tizhongzhibiao").val(bmi);
        var grade =10;
        if(bmi<18.5){
        	grade =20;
        }else if(bmi>=18.5 && bmi<22.9){
        	grade =10;
        }else if(bmi >=22.9 && bmi < 24.9){
        	grade =30;
        }else if(bmi >24.9){
        	grade =40;
        }
        $("input:radio[name=yinyangzhuangkuang][value="+grade+"]").iCheck('check');
	}


</script>