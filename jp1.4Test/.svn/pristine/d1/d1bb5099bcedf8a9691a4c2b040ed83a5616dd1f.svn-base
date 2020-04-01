<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
		//初始化开始时间和结束时间
		 var weekBefore = new Date(new Date().getTime()-3600*1000*24*7);
		 var weekBeforeBegin = new Date(weekBefore);//一星期前开始
		 weekBeforeBegin.setHours(0);
		 weekBeforeBegin.setMinutes(0);
		 weekBeforeBegin.setSeconds(0);
		 
		 var yesterday = new Date(new Date().getTime()-3600*1000*24);
		 var yesterdayEnd = new Date(yesterday);//昨天结束
		 yesterdayEnd.setHours(23);
		 yesterdayEnd.setMinutes(59);
		 yesterdayEnd.setSeconds(59);
	 
		$('#beginCreateDate').datetimepicker({
			 format: "YYYY-MM-DD HH:mm:ss",
			  defaultDate:weekBeforeBegin
		});
		$('#endCreateDate').datetimepicker({
			 format: "YYYY-MM-DD HH:mm:ss",
			 defaultDate:yesterdayEnd
		});
		
		$("#search").click("click",function(){//绑定查询按钮
			getData();
		})
		
		 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $("#timeFlag").val("1");
		});
		getData();
		
	});
		
	function getData(){
		if ($("input[name=beginCreateDate]").val() == "" || $("input[name=endCreateDate]").val == "") {
			jp.warning("时间不能为空");
			return;
		}
	    jp.post("${ctx}/statistichotword/statistichotword/data",$("#searchForm").serialize(), function (data) {
	    	data.timeFlag = $("#timeFlag").val();
	    	initMyChart(data);
    	});
		
	}
  function initMyChart(data){
  		option = {
  			
  			//自定义颜色
			color:['#fff143','#c9dd22','#ff2d51','#44cef6','#d6ecf0','#ff8c31','#9ed048','#ffb3a7','#ffc64b','#f05654','#4a4266','#bbcdc5','#d9b611','#0c8918','#c83c23','#8d4bbb','#75878a','#e29c45','#00e09e','#f35336','#003371','#3d3b4f','#ca6924','#7fecad','#a98175','#b0a4e3','#5d513c','#9b4400','#1bd1a5','#ff0097','#e4c6d0','#312520','#896c39','#789262','#c91f37','#7c4b00','#424c50','#9d2933','#622a1d'],

  		    tooltip : {
  		        trigger: 'axis',
  		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
  		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
  		        }
  		    },
  		    
	  		  toolbox: {
	            show: true,
				padding:[20,5,5,5],//避免legend和toolbox重叠
	            feature: {
	                dataView: { 
	                    readOnly: false
	                }, //数据视图
	                magicType: {
	                    type: ['line', 'bar']
	                }
	  		    }
	  		  },
  		    
  		    legend: {
  		    	type: 'scroll',
  		    	data:legend
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
  		            data : data.body.xList
  		        }
  		    ],
  		    yAxis : [
  		        {
  		            type : 'value'
  		        }
  		    ],
  		    series : []
  		};
  		
  		
		//设置高度
		$("#mychart").css('height',window.screen.height/2);
		var i = 0;
		var legend = [];
		for(var key in data.body.ydataMap){
			var item = {
		            name: key,
		            type:'bar',
		            stack: 'stack',
		            barWidth: '50%',
		            data:data.body.ydataMap[key]
		        };
			option.series[i] = item;
			legend.push(key);
			i++;
		}

		var myChart = echarts.init(document.getElementById("mychart"));
		myChart.off('click');
		myChart.setOption(option,true);
		myChart.on("click",function(param){
			jp.openDialogView(param.name,"${ctx}/statistichotword/statistichotword/subChart?time="+param.name+"&sStatus="+$("#sStatus").val(),window.screen.width*0.8+"", window.screen.height*0.7+"");
		})
		
		//折线图不堆叠
        myChart.on('magictypechanged', params => {
            if(params.currentType==='line'){
                for(let i in params.newOption.series){
                    params.newOption.series[i].stack=null

                }
                myChart.setOption(params.newOption)

            }
            if(params.currentType==='bar'){
                for(let i in params.newOption.series){
                    params.newOption.series[i].stack="stack"

                }
                myChart.setOption(params.newOption)

            }
        });
  	}
</script>