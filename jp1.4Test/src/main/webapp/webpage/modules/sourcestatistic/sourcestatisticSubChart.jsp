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
	var data = JSON.parse(localStorage.getItem("sourcelistdata"));
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
  		            data : data.queryParams.groupFlag=="1"?data.sourceList:data.reportsubareaList,
		  		    //splitLine: { show: false }, //去除网格线
			  	    axisLabel: {
	  	                //interval: 0,
	  	                formatter: function(value) {
	  	                    return value.split("").join("\n");
	  	                }
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
		})
  	}
})
</script>

</head>
<body class="bg-white">
	<div id="mychart" class="col-sm-12"></div>
</body>
</html>