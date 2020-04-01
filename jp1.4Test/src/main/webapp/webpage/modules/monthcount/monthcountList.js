<%@ page contentType="text/html;charset=UTF-8" %>
<script>

//表格数据
var tableDate;
//定时器
var myInterval;

var option = {
 backgroundColor:'#fff',
    tooltip: {
      trigger: 'axis',
      axisPointer: { // 坐标轴指示器，坐标轴触发有效
        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
      }
    },
    grid: {
      left: '2%',
      right: '4%',
      bottom: '14%',
      top:'16%',
      containLabel: true
    },
     legend: {
    data: ['人工检查工单', '问题工单','在途工单', '归档工单'],
    right: 10,
    top:12,
    textStyle: {
        color: "#000"
    },
    itemWidth: 12,
    itemHeight: 10,
    // itemGap: 35
},
    xAxis: {
      type: 'category',
//      data: ['2012','2013','2014','2015','2016','2017','2018','2019'],
      axisLine: {
        lineStyle: {
          color: 'black'

        }
      },
      axisLabel: {
        // interval: 0,
        // rotate: 40,
        textStyle: {
          fontFamily: 'Microsoft YaHei'
        }
      },
    },

    yAxis: {
      type: 'value',
//      max:'1200',
      axisLine: {
        show: false,
        lineStyle: {
          color: 'black'
        }
      },
      splitLine: {
        show: true,
        lineStyle: {
          color: 'rgba(0,0,0,0.3)'
        }
      },
      axisLabel: {}
    },
    "dataZoom": [{
      "show": false,
      "height": 12,
      "xAxisIndex": [
        0
      ],
      bottom:'8%',
      "start": 10,
      "end": 90,
      handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
      handleSize: '110%',
      handleStyle:{
        color:"#d3dee5",

      },
      textStyle:{
        color:"#fff"},
      borderColor:"#90979c"
    }, {
      "type": "inside",
      "show": true,
      "height": 15,
      "start": 1,
      "end": 35
    }],
    series: [{
      name: '人工检查工单',
      type: 'bar',
      barWidth: '15%',
      itemStyle: {
        normal: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#fccb05'
            }, {
                offset: 1,
                color: '#f5804d'
            }]),
            barBorderRadius: 12,
        },
      }
//    ,data: [400, 400, 300, 300, 300, 400, 400, 400, 300]
    },
    {
      name: '问题工单',
      type: 'bar',
      barWidth: '15%',
      itemStyle: {
        normal: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#8bd46e'
            }, {
                offset: 1,
                color: '#09bcb7'
            }]),
            barBorderRadius: 11,
        }
        
      }
//    ,data: [400, 500, 500, 500, 500, 400,400, 500, 500]
    },
    {
        name: '在途工单',
        type: 'bar',
        barWidth: '15%',
        itemStyle: {
          normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#efcec9'
              }, {
                  offset: 1,
                  color: '#d98c80'
              }]),
              barBorderRadius: 11,
          }
    

        }
//      ,data: [400, 500, 500, 500, 500, 400,400, 500, 500]
      },
      {
          name: '归档工单',
          type: 'bar',
          barWidth: '15%',
          itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: '#8ffaf7'
                }, {
                    offset: 1,
                    color: '#0be1db'
                }]),
                barBorderRadius: 11,
            }
          }
//        ,data: [400, 500, 500, 500, 500, 400,400, 500, 500]
        }]
  };

  var app = {
    currentIndex: -1,
  };


$(document).ready(function() {
	 //初始化开始时间和结束时间
	 var yesterday = new Date(new Date().getTime()-3600*1000*24);
	 var yesterdayBegin = new Date(yesterday);//昨天开始
	 yesterdayBegin.setHours(0);
	 yesterdayBegin.setMinutes(0);
	 yesterdayBegin.setSeconds(0);
	 var today = new Date();//今天结束
	 today.setHours(23);
	 today.setMinutes(59);
	 today.setSeconds(59);
	 $('#beginSearchtime').datetimepicker({
		 format: "YYYY-MM-DD 00:00:00",
		 defaultDate:yesterdayBegin
	});
	$('#endSearchtime').datetimepicker({
		 format: "YYYY-MM-DD 23:59:59",
		 defaultDate:today
	});
	
	$('#monthcountTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               //showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: true,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/monthcount/monthcount/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	window.location = "${ctx}/monthcount/monthcount/form?id=" + row.id;
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该月度汇总记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/monthcount/monthcount/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#monthcountTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
               },
               responseHandler: function (res) {
            	   tableData = res.rows;
            	   
	           		//按组名排序
	           		var order = ['1组','2组','3组','4组','5组','支撑组','专岗','前置','合计'];
	           		//排序
	           		//定义了sort的比较函数
	           		tableData = tableData.sort(function(a,b){
	           			return order.indexOf(a.dutyGroup)-order.indexOf(b.dutyGroup);
	           		});
	           		
					// 基于准备好的dom，初始化echarts实例
					$("#mychart").css('height',window.screen.height/2);
//					console.log(window.screen.height);//768屏幕高
//					console.log(window.screen.width);//1366屏幕宽
            		var myChart = echarts.init(document.getElementById("mychart"));

            		//要显示的组
            		var includeGroupName = ['1组','2组','3组','4组','5组'];

            		window.onresize = myChart.resize;
            		var xArr = [];
            		var yArr1 = [];
            		var yArr2 = [];
            		var yArr3 = [];
            		var yArr4 = [];

            		$.each(tableData,function(index,obj){
            		     if(includeGroupName.indexOf(obj.dutyGroup)>-1){
            		    	 xArr.push(obj.dutyGroup);
            		    	 yArr1.push(obj.checknumber);
            		    	 yArr2.push(obj.errornumber);
            		    	 yArr3.push(obj.transitnumber);
            		    	 yArr4.push(obj.archivingnumber);
            		     }
            		});
            		
            		option.xAxis.data = xArr;
            		option.series[0].data = yArr1;
            		option.series[1].data = yArr2;
            		option.series[2].data = yArr3;
            		option.series[3].data = yArr4;

            		myChart.setOption(option);
            		clearInterval(myInterval);
        		    myInterval = setInterval(function () {
        			    var dataLen = option.series[0].data.length;

        			    // 取消之前高亮的图形
        			    myChart.dispatchAction({
        			      type: 'downplay',
        			      seriesIndex: 0,
        			      dataIndex: app.currentIndex
        			    });
        			    app.currentIndex = (app.currentIndex + 1) % dataLen;
        			    //console.log(app.currentIndex);
        			    // 高亮当前图形
        			    myChart.dispatchAction({
        			      type: 'highlight',
        			      seriesIndex: 0,
        			      dataIndex: app.currentIndex,
        			    });
        			    // 显示 tooltip
        			    myChart.dispatchAction({
        			      type: 'showTip',
        			      seriesIndex: 0,
        			      dataIndex: app.currentIndex
        			    });


        			  }, 1000);
            		

                   return res;
               },
               columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'dutyGroup',
		        title: '班组',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return "<a  onclick=\"newtable('"+row.dutyGroup+"')\" >"+value+"</a>"; 
		         }
		       
		    }
			,{
		        field: 'checknumber',
		        title: '检查单量',
		        sortable: true
		       
		    }
			,{
		        field: 'errornumber',
		        title: '问题单量',
		        sortable: true
		       
		    },{
		        field: 'transitnumber',
		        title: '在途单量',
		        sortable: true
		       
		    }
			,{
		        field: 'archivingnumber',
		        title: '归档单量',
		        sortable: true
		       
		    }
			,{
		        field: 'dutyPeopledetail',
		        title: '责任人明细',
		        sortable: true
		       
		    }
			/*,{
		        field: 'searchtime',
		        title: '查询时间',
		        sortable: true
		       
		    }*/
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#monthcountTable').bootstrapTable("toggleView");
		}
	  
	  $('#monthcountTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#monthcountTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#monthcountTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/monthcount/monthcount/import/template';
				  },
			    btn2: function(index, layero){
				        var inputForm =top.$("#importForm");
				        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				        inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				        inputForm.onsubmit = function(){
				        	jp.loading('  正在导入，请稍等...');
				        }
				        inputForm.submit();
					    jp.close(index);
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		$("#searchForm").validate({
			submitHandler: function(form){
				 $('#monthcountTable').bootstrapTable('refresh');
				  jp.post("${ctx}/monthcount/monthcount/getDetails",$("#searchForm").serialize(),function(data){
					  if(data.success){
							$('#details').html(data.msg);
						}
				  });
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});     
	  
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#monthcountTable').bootstrapTable('refresh');
		});
	});
	
function newtable(dutyGroup) {
	jp.openTab("${ctx}/monthcount/monthcount/form?dutyGroup="+dutyGroup+
			"&beginSearchtime="+$("input[name='beginSearchtime']").val()+
			"&endSearchtime="+$("input[name='endSearchtime']").val()+
			"&sStatus="+$("#sStatus").val(),"班组详情", false);
}


  function getIdSelections() {
        return $.map($("#monthcountTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该月度汇总记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/monthcount/monthcount/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#monthcountTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function edit(){
	  window.location = "${ctx}/monthcount/monthcount/form?id=" + getIdSelections();
  }
  
	function exportAll(){
		 // window.location = "${ctx}/monthcount/monthcount/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/monthcount/monthcount/export");
		 $('body').append(form);
		  $("#searchForm input").each(function(index, element) { 
		 			 	var input1 = $('<input>'); 
		 				input1.attr('type','hidden'); 
		 				input1.attr('name',element.name); 
		 				input1.attr('value',element.value); 
		 				form.append(input1);
		 				    }); 
		 form.submit();
	}
  
</script>