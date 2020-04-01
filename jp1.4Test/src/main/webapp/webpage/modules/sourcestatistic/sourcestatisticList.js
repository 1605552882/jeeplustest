<%@ page contentType="text/html;charset=UTF-8" %>
<script>
var searchSource = "";
var searchCity = "";
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
		 format: "YYYY-MM-DD 00:00:00",
		 defaultDate:weekBeforeBegin
	}).bind('dp.change', function(e) {});
	$('#endCreateDate').datetimepicker({
		 format: "YYYY-MM-DD 23:59:59",
		 defaultDate:yesterdayEnd
	}).bind('dp.change', function(e) {});
	
	//查询
	getData();
	
	  $("#search").click("click", function() {// 绑定查询按扭
		  getData();
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $("#timeFlag").val("1");//天
		  $("#groupFlag").val("1");//分组
		  $('#beginCreateDate').data("DateTimePicker").format("YYYY-MM-DD 00:00:00").date(weekBeforeBegin)
		  $('#endCreateDate').data("DateTimePicker").format("YYYY-MM-DD 23:59:59").date(yesterdayEnd)
		  getData();
		});
	 
		$("#timeFlag").change(function(){
			if($("#timeFlag").val()=="1"){
				$('#beginCreateDate').data("DateTimePicker").format("YYYY-MM-DD 00:00:00")
				$('#endCreateDate').data("DateTimePicker").format("YYYY-MM-DD 23:59:59")
			}else if($("#timeFlag").val()=="2"){
				$('#beginCreateDate').data("DateTimePicker").format("YYYY-MM")
				$('#endCreateDate').data("DateTimePicker").format("YYYY-MM")
			}
		})

		$("#timeFlag,#groupFlag").change(function(){
			getData();
		});
		
	});

	
	function getData(){
		if($("input[name=beginCreateDate]").val()=="" || $("input[name=endCreateDate]").val()==""){
			jp.warning("申告时间不能为空");
			return;
		}
		
        jp.post("${ctx}/sourcestatistic/sourcestatistic/data",$("#searchForm").serializeJSON(), function (data) {
        	if(data.success == false){
        		//查询失败
        		jp.alert(data.msg);
        		return;
        	}
        	//查询成功
        	data = data.body.data;
        	data.timeFlag = $("#timeFlag").val();
        	initMyChart(data);
        });
	}

  	function initMyChart(data){
  		var groupList = $.extend(true, [], (data.queryParams.groupFlag=="1"?data.sourceList:data.reportsubareaList));
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
	                },  //切换为折线图，切换为柱状图
	                restore: {},  //还原
	                saveAsImage: {}   //保存为图片
	  		    }
	  		  },
  		    
  		    legend: {
  		    	type: 'scroll',
  		    	data:groupList
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
  		            data : data.datefmt
  		        }
  		    ],
  		    yAxis : [{
  		            type: 'value',
  		            name: '工单量',
  		            position: 'left',
  		            axisLabel: {
  		                formatter: '{value}'
  		            }
  		        }],
  		    series : []
  		};
  		
		//设置高度
		$("#mychart").css('height',window.screen.height/2);
		if(data.queryParams.groupFlag=="1"){
			//申告来源
			for(var i=0; i<data.sourceList.length; i++){
				var item = {
			            name:data.sourceList[i],
			            type:'bar',
			            stack: 'stack',
			            barWidth: '50%',
			            data:data.data[i]
			        };
				option.series[i] = item;
			}
		}else{
			//申告地市
			for(var i=0; i<data.reportsubareaList.length; i++){
				var item = {
			            name:data.reportsubareaList[i],
			            type:'bar',
			            stack: 'stack',
			            barWidth: '50%',
			            data:data.data[i]
			        };
				option.series[i] = item;
			}
		}
		
		var myChart = echarts.init(document.getElementById("mychart"));
		myChart.off('click');
		myChart.setOption(option,true);
		
		//选中的图例
		var selected;
		myChart.on('legendselectchanged', function(params) {
			selected = params.selected;
		});
		
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
		
		//点击事件
		myChart.on('click', function (params) {
			//点击柱状图
			var day = params.name;//x轴坐标
	        if(day != "" && data.datefmt.indexOf(day)!=-1){
	        	var index = data.datefmt.indexOf(day);//哪一天
	        	data.day = day;
	        	var arr = [];
	        	
	    		if(data.queryParams.groupFlag=="1"){
	    			//申告来源
		        	for(var i=0; i<data.sourceList.length; i++){
		        		arr[i] = data.data[i][index];
		        	}
		        	data.daydata = arr;
		        	localStorage.setItem("sourcelistdata",JSON.stringify(data));
	            	jp.openDialogView(day,"${ctx}/sourcestatistic/sourcestatistic/subChart",window.screen.width*0.8+"", window.screen.height*0.7+"");
	    		}else{
	    			//申告地市
		        	for(var i=0; i<data.reportsubareaList.length; i++){
		        		arr[i] = data.data[i][index];
		        	}
		        	data.daydata = arr;
		        	localStorage.setItem("sourcelistdata",JSON.stringify(data));
	            	jp.openDialogView(day,"${ctx}/sourcestatistic/sourcestatistic/subChart",window.screen.width*0.8+"", window.screen.height*0.7+"");
	    		}
	  		}
		})
		
  	}
  	
	function exportAll(){
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/sourcestatistic/sourcestatistic/export");
		 $('body').append(form);
		  $("#searchForm input").each(function(index, element) { 
		 			 	var input1 = $('<input>'); 
		 				input1.attr('type','hidden'); 
		 				input1.attr('name',element.name); 
		 				input1.attr('value',element.value); 
		 				form.append(input1);
		 				    }); 
		  $("#searchForm select").each(function(index, element) {
			 	var input1 = $('<input>'); 
				input1.attr('type','hidden'); 
				input1.attr('name',element.name); 
				input1.attr('value',element.value); 
				form.append(input1);
				    });
		 form.submit();
	}
</script>