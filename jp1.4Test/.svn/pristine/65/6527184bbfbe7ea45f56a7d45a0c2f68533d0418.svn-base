<%@ page contentType="text/html;charset=UTF-8" %>
<script>

function exportAll(){
	 // window.location = "${ctx}/elder/edElderInf/export";
	alert("hello test1");
	 var form = $("<form>");  
	 form.attr('target', '');
	 form.attr('method', 'post');
	 form.attr('action', "${ctx}/elder/edElderInf/export");
	 $('body').append(form);
	 
	 $("#searchForm input").each(function(index, element) { 
		 	var input1 = $('<input>'); 
			input1.attr('type',element.type); 
			input1.attr('name',element.name); 
			input1.attr('value',element.value); 
			alert(input1);
			form.append(input1);
			    }); 
	 
	 alert(form);
	 
	// form.submit();
	 
}

</script>