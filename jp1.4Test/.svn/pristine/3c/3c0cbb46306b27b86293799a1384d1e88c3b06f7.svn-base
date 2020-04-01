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
	var datas =${fns:toJson(list)};
	var x = [];
	var y = [];
	for (var a = 0; a<datas.length ; a++) {
		var data = datas[a];
		x.push(data.hotword);
		y.push(data.times);
	}
	
	
	
	initMyChart();
  	function initMyChart(){
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
  		            data : x,
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
	            data:y
	        }]
  		};
		//设置高度
		$("#mychart").css('height',window.screen.height*0.55);

		var myChart = echarts.init(document.getElementById("mychart"));
		myChart.setOption(option);
		
		/* //点击事件
		myChart.on('click', function (params) {
			var createDate = datas[0].createDate;
			jp.openDialogView(createDate,"${ctx}/faultcategorystatistic/faultcategorystatistic/subPie?hotword="+ params.name+"&createDate="+createDate,window.screen.width*0.8+"", window.screen.height*0.7+"");
		}) */
  	}
  	
})
</script>

</head>
<body class="bg-white">
	<div id="mychart" class="col-sm-12"></div>
</body>
</html>