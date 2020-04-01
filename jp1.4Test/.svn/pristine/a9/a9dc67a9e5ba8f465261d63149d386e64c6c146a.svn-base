<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="ani"/>
	<!-- 引入 echarts.js -->
	<%@ include file="/webpage/include/echarts.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	var data = JSON.parse(localStorage.getItem("faultcategorylistdata"));
	initMyChart(data);
  	function initMyChart(data){
  		option = {
  		    tooltip : {
  		        trigger: 'axis',
  		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
  		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
  		        }
  		    },
  		    grid: {
  		        left: '3%',
  		        right: '4%',
  		        bottom: '3%',
  		        containLabel: true
  		    },
  		    xAxis : [
  		        {
  		            type : 'category',
  		            data : data.queryParams.groupFlag=="1"?data.faultcategoryLabelList:data.cityLabelList,
  		            axisLabel: {
  		            interval: 0/*, 
                    formatter:function(value)  
                    {  
                        return value.split("").join("\n");  
                    }*/
  		        	}
  		        }
  		    ],
  		    yAxis : [
  		        {
  		            type : 'value'
  		        }
  		    ],
  		    series : [{
	            type:'bar',
	            data:data.daydata
	        }]
  		};
		//设置高度
		$("#mychart").css('height',window.screen.height*0.55);

		var myChart = echarts.init(document.getElementById("mychart"));
		myChart.setOption(option);
		
		//点击事件
		myChart.on('click', function (params) {
	        if(params.componentType != "xAxis" && params.componentType != "yAxis"){
	        	if(data.queryParams.groupFlag == "1"){
	        		data.faultcategory = getDictValue(${fns:toJson(fns:getDictList('fiveFaultcategory'))},params.name);//x轴坐标
			        localStorage.setItem("faultcategorychartdata",JSON.stringify(data));
					jp.openDialogView(data.day+" "+data.faultcategory,"${ctx}/faultcategorystatistic/faultcategorystatistic/subPie",window.screen.width*0.8+"", window.screen.height*0.7+"");
	        	}else{
	        		data.city = getDictValue(${fns:toJson(fns:getDictList('documentCity'))},params.name);//x轴坐标
			        localStorage.setItem("faultcategorychartdata",JSON.stringify(data));
					jp.openDialogView(data.day+" "+data.city,"${ctx}/faultcategorystatistic/faultcategorystatistic/subPie",window.screen.width*0.8+"", window.screen.height*0.7+"");
	        	}
        	}
		})
  	}
  	
	//获取字典值
    function getDictValue(data, label, defaultLabel){
    	for (var i=0; i<data.length; i++){
    		var row = data[i];
    		if (row.label == label){
    			return row.value;
    		}
    	}
    	return defaultLabel;
    }

})
</script>

</head>
<body class="bg-white">
	<div id="mychart" class="col-sm-12"></div>
</body>
</html>