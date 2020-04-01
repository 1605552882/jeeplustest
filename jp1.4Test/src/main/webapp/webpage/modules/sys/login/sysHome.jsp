<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="ani"/>
	
	<!-- 引入 echarts.js -->
	<%@ include file="/webpage/include/echarts.jsp"%>
	<style>

		#body-container {
			margin-left: 0px !important;
			/**padding: 10px;*/
			margin-top: 0px !important;
			overflow-x: hidden!important;
			transition: all 0.2s ease-in-out !important;
			height: 100% !important;
			width : 100%;
		}
		/*高度百分比显示*/
		.conter-wrapper,.row,.foo,.home-charts-middle,.top-right-chart,.top-left-chart{
			height: 100%;
		}
		.bottom-right-chart{
			height: 45%
		}
		#mychart1{
			height: 50%;
			width : 100%
		}
		#mychart2,#mychart3,#mychart4{
			height: 100%
		}
		.instead-br{
			height: 2%
		}
	</style>
	
</head>
<body class="">
<div id="body-container" class="wrapper wrapper-content">
	<div class="conter-wrapper home-container">
		<div class="row home-row" style="font-size: 20px;">
				<marquee id="system_announcement_show_area" style="color: red;" onmouseover=this.stop(); onmouseout=this.start();>通告展示区</marquee>
		</div>
	</div>
</div>
<script src="vendor/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="js/vendor.js"></script>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例4
    var myChart1 = echarts.init(document.getElementById("mychart1"));
    window.onresize = myChart1.resize;
    
    var myChart2 = echarts.init(document.getElementById("mychart2"));
    window.onresize = myChart2.resize;
    
    var myChart3 = echarts.init(document.getElementById("mychart3"));
    window.onresize = myChart3.resize;
    
    var myChart4 = echarts.init(document.getElementById("mychart4"));
    window.onresize = myChart4.resize;
    
    function getpData(){
    	var timeflag = $('select  option:selected').val();
    	
    	changeDateTimePicker(timeflag);
    	getWaterPieData();
    	
    	jp.get("${ctx}/statisticdocument/statisticdocument/data?daynum="+ timeflag, function (data) {
            
        	var documents = data.body.document; 
        	var xAxis = []; //x轴
        	var ybar = [];  //y轴柱状图
        	var handle1 = [] //1组
        	var handle2 = [] //2组
        	var handle3 = [] //3组
        	var handle4 = [] //4组
        	var handle5 = [] //5组
        	var error1 = [] //1组
        	var error2 = [] //2组
        	var error3 = [] //3组
        	var error4 = [] //4组
        	var error5 = [] //5组
        	var year = "";
        	var month = "";
        	for (var i = 0; i < documents.length; i++) {
        		var document = documents[i];
        		var daynum = document.daynum; // 当天工单量
        		var date = new Date(document.creatTime);
        		if (timeflag == 1) {
        			xAxis.push(date.getDate() + "日"); 
        		} else if (timeflag == 2) {
        			xAxis.push((date.getMonth()+1) + "月");
        		}
        		ybar.push(daynum);
        		handle1.push(document.onegroupnum);
        		handle2.push(document.twogroupnum);
        		handle3.push(document.thgroupnum);
        		handle4.push(document.fogroupnum);
        		handle5.push(document.fivegroupnum);
        		error1.push(document.onegrouperrornum);
        		error2.push(document.twogrouperrornum);
        		error3.push(document.thgrouperrornum);
        		error4.push(document.fogrouperrornum);
        		error5.push(document.fivegrouperrornum);
        		//var checknum = document.checknum //人工复检工单量
        		//var errornum = document.errornum //存在问题工单量
        		//var rate = (checknum - errornum) / checknum
			}
        	if (timeflag == 1) {
        		var date = new Date(documents[0].creatTime);
        		var subtext = date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日至";
        		date = new Date(documents[documents.length-1].creatTime);
        		subtext += date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日";
    		} else if (timeflag == 2) {
    			var date = new Date();
        		var subtext = date.getFullYear() + "年";
    		}
        	 option1 = {	
        			 		/* backgroundColor: '#0d235e', */ 
        			    	title: {
        			        text: '投诉工单量',
        			        subtext: subtext,
        			        x: 'center',
        			        y: 0,
        			        textStyle:{
        			            color:'#4F4F4F',
        			        },
        			        
        			    },
        			    tooltip: {
        			        trigger: 'axis',
        			        backgroundColor:'#4F4F4F',
        			        formatter: function (params) {
        			            let html=params[0].name+"<br>";
        			            for(let i=0;i<params.length;i++){
        			              html+='<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:'+params[i].color+';"></span>'
        			              if(params[i].seriesName=="及时率"){
        			                html+=params[i].seriesName+":"+params[i].value+"%<br>";
        			              }else{
        			                html+=params[i].seriesName+":"+params[i].value+"<br>";
        			              }
        			            }
        			            return html;
        			        },
        			        axisPointer: {
        			            type: 'shadow',
        			            label: {
        			                show: true,
        			                backgroundColor: '#4F4F4F'
        			            }
        			        }
        			    },
        			   /*  legend: {
        			        data: ['增值税应纳税额', '一般纳税人户数','增幅',],
        			        textStyle: {
        			            color: '#B4B4B4'
        			        },
        			        top:'7%',
        			    }, */
        			    grid:{
        			        x:'12%',
        			        width:'82%',
        			        y:'12%',
        			    },
        			    xAxis: {
        			        data: xAxis,
        			        axisLine: {
        			            lineStyle: {
        			                color: '#7B7B7B'
        			            }
        			        },
        			        axisTick:{
        			            show:false,
        			        },
        			    },
        			    yAxis: [
		       			     {
		
		       			        splitLine: {show: false},
		       			        axisLine: {
		       			            lineStyle: {
		       			                color: '#7B7B7B',
		       			            }
		       			        },
		       			        
		       			        axisLabel:{
		       			            formatter:'{value} ',
		       			        }
		       			    }/* ,
        			        {

        			        splitLine: {show: false},
        			        axisLine: {
        			            lineStyle: {
        			                color: '#7B7B7B',
        			            }
        			        },
        			        axisLabel:{
        			            formatter:'{value}% ',
        			        }
        			    } */
		       			    ],
        			    
        			    series: [/* {
        			        name: '及时率',
        			        type: 'line',
        			        smooth: true,
        			        showAllSymbol: true,
        			        symbol: 'emptyCircle',
        			        symbolSize: 8,
        			        yAxisIndex: 1,
        			        itemStyle: {
        			                normal: {
        			                color:'#F02FC2'},
        			        },
        			        data: yline
        			    }, */ 
        			    
        			    {
        			        name: '工单量',
        			        type: 'bar',
        			        barWidth: 10,
        			        itemStyle: {
        			            normal: {
        			                barBorderRadius: 5,
        			                color: new echarts.graphic.LinearGradient(
        			                    0, 0, 0, 1,
        			                    [
        			                        {offset: 0, color: '#956FD4'},
        			                        {offset: 1, color: '#3EACE5'}
        			                    ]
        			                )
        			            }
        			        },
        			        data: ybar
        			    },{
        			        name: '1组(系统检查)',
        			        type: 'bar',
        			        stack:'handle',
        			        barWidth: 10,
        			        itemStyle: {},
        			        data: handle1
        			    },{
        			        name: '2组(系统检查)',
        			        type: 'bar',
        			        stack:'handle',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: handle2
        			    },{
        			        name: '3组(系统检查)',
        			        type: 'bar',
        			        stack:'handle',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: handle3
        			    },{
        			        name: '4组(系统检查)',
        			        type: 'bar',
        			        stack:'handle',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: handle4
        			    },{
        			        name: '5组(系统检查)',
        			        type: 'bar',
        			        stack:'handle',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: handle5
        			    },{
        			        name: '1组人工复检',
        			        type: 'bar',
        			        stack:'error',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: error1
        			    },{
        			        name: '2组人工复检',
        			        type: 'bar',
        			        stack:'error',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: error2
        			    },{
        			        name: '3组人工复检',
        			        type: 'bar',
        			        stack:'error',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: error3
        			    },{
        			        name: '4组人工复检',
        			        type: 'bar',
        			        stack:'error',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: error4
        			    },{
        			        name: '5组人工复检',
        			        type: 'bar',
        			        stack:'error',
        			        barWidth: 10,
        			        itemStyle: { },
        			        data: error5
        			    }
        			    
        			   ]
		};
        	myChart1.off('click');
            myChart1.setOption(option1,true);
          //点击事件
    		myChart1.on('click', function (params) {
    			var dataIndex = params.dataIndex;//下标
    			var date = new Date(documents[dataIndex].creatTime);
    			//var xParam = params.name;//x轴坐标
    			var timeflag = $('select  option:selected').val();
    			if(timeflag==1){
        			$('#beginDate').data("DateTimePicker").date(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" 00:00:00")
        			$('#endDate').data("DateTimePicker").date(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" 23:59:59")
    			}else if(timeflag==2){
        			$('#beginDate').data("DateTimePicker").date(date.getFullYear()+"-"+(date.getMonth()+1))
        			$('#endDate').data("DateTimePicker").date(date.getFullYear()+"-"+(date.getMonth()+1))
    			}
    		})
        });
        
    }
    
    //获取水滴图数据
    function getWaterPieData(){
    	var timeflag = $('select  option:selected').val();
    	var beginDate = $("input[name='beginDate']").val();
    	var endDate = $("input[name='endDate']").val();
    	
    	if(timeflag=="2"){
    		beginDate = $("input[name='beginDate']").val()+"-01 00:00:00";
    		endDate = endDate + "-" +getLastDay(endDate.split("-")[0],endDate.split("-")[1])+" 23:59:59";
    	}
    	
    	if(compareDate(beginDate,endDate)){
    		return;
    	}
    	
        jp.get("${ctx}/statisticdocument/statisticdocument/sdData?daynum="+ timeflag+"&start="+beginDate+"&end="+endDate, function (data) {
        	var documents = data.body.document; 
	       	var value;
	    	var value1;
	       	var year = "";
         	var month = "";
         	var day = "";
        	for (var i = 0; i < documents.length; i++) {
        		var document = documents[i];
        		var daynum = document.daynum; // 当天工单量
        		var detectnum = document.detectnum //检测工单量
        		var errorNum = document.errornum //人工复检问题工单量
        		value = (detectnum / daynum).toFixed(3);//四舍五入保留3位小数
        		value1 = (errorNum / detectnum).toFixed(3);
        		if (i == 0) {
        			var date = new Date(document.creatTime);
  	        		year = date.getFullYear();
  	        		month = date.getMonth()+1;
  	        		day = date.getDate();
        		}
			};
           	 var data = [value, value,value,];
           	 var data1 = [value1, value1,value1,];
           	var subtext;
           	if (timeflag == 1) {
        		subtext = year+"年"+month+"月"+day+"日";
    		} else if (timeflag == 2) {
    			subtext = year+"年"+month+"月";
    		}
           	 
           	 //水滴图
           	 var option2 = {
           			/* backgroundColor: '#0d235e',  */
         			    title: {
         			    text: '系统检查问题工单占总工单量比率',
         			    subtext: getSubtext(),
         			    left: 'left',
       			        top: 10,
       			        textStyle: {
       			            color: '#4F4F4F'
       			        }
         			    },
           	     series: [{
           	         type: 'liquidFill',
           	         radius: '60%',
           	         center: ['50%', '50%'],
           	         data: data,
           	         backgroundStyle: {
           	             borderWidth: 5,
           	             borderColor: '#1daaeb',
           	             color: '#fff'
           	         },
           	         label: {
           	             normal: {
           	              	 formatter: function(){
           	              		if(value>=0.001 && value<=0.009){
           	              			return value * 100 + '%';
           	              		}else{
           	              			return Math.floor(value * 100) + '%';
           	              		}
           	              	 },
           	                 textStyle: {
           	                     fontSize: 10
           	                 }
           	             }
           	         }
           	     }]
           	 };
           	 
           	 //水滴图
           	 var option4 = {
           			/* backgroundColor: '#0d235e',  */
         			    title: {
         			    text: '人工复检问题工单占系统检查问题工单量比率',
         			    subtext: getSubtext(),
         			    left: 'left',
       			        top: 10,
       			        textStyle: {
       			            color: '#4F4F4F'
       			        }
         			    },
           	     series: [{
           	         type: 'liquidFill',
           	         radius: '60%',
           	         center: ['50%', '50%'],
           	         data: data1,
           	         backgroundStyle: {
           	             borderWidth: 5,
           	             borderColor: '#1daaeb',
           	             color: '#fff'
           	         },
           	         label: {
           	             normal: {
           	            	 formatter: function(){
           	              		if(value1>=0.001 && value1<=0.009){
           	              			return value1 * 100 + '%';
           	              		}else{
           	              			return Math.floor(value1 * 100) + '%';
           	              		}
           	              	 },
           	                 textStyle: {
           	                     fontSize: 10
           	                 }
           	             }
           	         }
           	     }]
           	 };
           	 myChart2.setOption(option2);
           	 myChart4.setOption(option4);
        });
        
	     jp.get("${ctx}/faultcategorystatistic/faultcategorystatistic/btData?faultcategory="+timeflag+"&beginCreateDate="+beginDate+"&endCreateDate="+endDate, function (data) {
	    	 var documents = data.body.document; 
        	 var sdata = [];
        	 var count = 0;
        	for (var i = 0; i < documents.length; i++) {
        		var document = documents[i];
        		var faultcategory = document.faultcategory; // 故障种类
        		var times = document.times //统计次数
        		if (i < 5) {
        			var map = {name : faultcategory , value : times};
            		sdata.push(map);
        		} else {
        			count = count + parseInt(times);
        		}
			}
        	sdata.push({name : '其它' , value : count});
	        //饼图
	       	 option3 = {
	       			/* backgroundColor: '#0d235e',  */
	       		    title : {
	       		        text: '投诉问题业务分布情况',
	       		        subtext: getSubtext(),
	       		    	 left: 'left',
	       		    	textStyle: {
    			            color: '#4F4F4F'
    			        }
	       		    },
	       		    tooltip : {
	       		        trigger: 'item',
	       		        formatter: "{a} <br/>{b} : {c} ({d}%)"
	       		    },
	       		    series : [
	       		        {
	       		            name: '投诉业务',
	       		            type: 'pie',
	       		            radius : '60%',
	       		            center: ['40%', '50%'],
	       		            data: sdata,
	       		            itemStyle: {
	       		                emphasis: {
	       		                    shadowBlur: 10,
	       		                    shadowOffsetX: 0,
	       		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	       		                },
	       		                normal:{
                                    color:function(params) {
                                        //自定义颜色
                                        var colorList = [          
                                               '#DC143C', '#00FFFF', '#00FF00', '#FFFF00', '#FF8C00', '#FF0000', '#FE8463',
                                            ];
                                            return colorList[params.dataIndex]
                                         }
                                    }
	       		            }
	       		        }
	       		    ]

	       			};  
	       	 	myChart3.setOption(option3); 
	        }); 
	       
    }
   
	 //初始化开始时间和结束时间
	 var yesterday = new Date(new Date().getTime()-3600*1000*24);
	 var yesterdayBegin = new Date(yesterday);//昨天开始
	 yesterdayBegin.setHours(0);
	 yesterdayBegin.setMinutes(0);
	 yesterdayBegin.setSeconds(0);
	 var yesterdayEnd = new Date(yesterday);//昨天结束
	 yesterdayEnd.setHours(23);
	 yesterdayEnd.setMinutes(59);
	 yesterdayEnd.setSeconds(59);
	 $('#beginDate').datetimepicker({
		 format: "YYYY-MM-DD 00:00:00",
		 defaultDate:yesterdayBegin
	}).bind('dp.change', function(e) {getWaterPieData()});
	$('#endDate').datetimepicker({
		 format: "YYYY-MM-DD 23:59:59",
		 defaultDate:yesterdayEnd
	}).bind('dp.change', function(e) {getWaterPieData()});
	
	//切换日期模式
	function changeDateTimePicker(timeflag){
		if(timeflag=="1"){
			$('#beginDate').data("DateTimePicker").format("YYYY-MM-DD 00:00:00")
			$('#endDate').data("DateTimePicker").format("YYYY-MM-DD 23:59:59")
		}else if(timeflag=="2"){
			$('#beginDate').data("DateTimePicker").format("YYYY-MM")
			$('#endDate').data("DateTimePicker").format("YYYY-MM")
		}
	}
	
	//获得某月的最后一天
	function getLastDay(year,month) { 
		var new_year = year; //取当前的年份 
		var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定） 
		if(month>12) { 
		new_month -=12; //月份减 
		new_year++; //年份增 
		} 
		var new_date = new Date(new_year,new_month,1); //取当年当月中的第一天
		return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期
	}
	
	function getSubtext(){
		var beginDate = $("input[name='beginDate']").val();
		var endDate = $("input[name='endDate']").val();
		var timeflag = $('select  option:selected').val();
		var subtext = "";
		if(timeflag=="1"){
			if(beginDate.substr(0,10)==endDate.substr(0,10)){//同一天
				subtext = beginDate.substr(0,4)+"年"+beginDate.substr(5,2)+"月"+beginDate.substr(8,2)+"日";
			}else{
				subtext = beginDate.substr(0,4)+"年"+beginDate.substr(5,2)+"月"+beginDate.substr(8,2)+"日"
						  +"\n至\n"+endDate.substr(0,4)+"年"+endDate.substr(5,2)+"月"+endDate.substr(8,2)+"日";
			}
		}else if(timeflag=="2"){
			if(beginDate.substr(0,7)==endDate.substr(0,7)){//同一月
				subtext = beginDate.substr(0,4)+"年"+beginDate.substr(5,2)+"月";
			}else{
				subtext = beginDate.substr(0,4)+"年"+beginDate.substr(5,2)+"月"
				+"\n至\n"+endDate.substr(0,4)+"年"+endDate.substr(5,2)+"月";
			}
		}
		return subtext;
	}
	
	//比较日期大小YY:mm:dd HH:MM:SS
	function compareDate(beginDate,endDate){
		return beginDate>endDate;
	}
    
    $(function () {  
    	getpData();
    })
	
</script>
</body>
</html>