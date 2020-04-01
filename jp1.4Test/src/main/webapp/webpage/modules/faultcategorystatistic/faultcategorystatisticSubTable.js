<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	
	var data = JSON.parse(localStorage.getItem("faultcategorypiedata"));
	
	if(data.timeFlag == "1"){
		$("#beginReportTime").val(data.day+" 00:00:00");
		$("#endReportTime").val(data.day+" 23:59:59");
	}else if(data.timeFlag == "2"){//2019-10
		var dayDate = stringToDate(data.day+"-01","-");//2019-10-01
		var end = getCurrentMonthLast(dayDate);
		$("#beginReportTime").val(data.day+"-01 00:00:00");
		$("#endReportTime").val(dateToString(end)+" 23:59:59");
	}
	$("#sStatus").val(data.queryParams.sStatus);
	$("#city").val(data.city);
	//故障种类 原始工单表documentarydetails中是sfaultcategory
	//故障种类 故障种类统计表statisticdocument中是faultcategory
	//故障时间 原始工单表documentarydetails中是report_time,beginReportTime和endReportTime作为查询条件
	//故障时间 故障种类统计表statisticdocument中是createDate

	if(data.faultcategory=="其它"){
		for(var i=0;i<data.faultcategoryList.length-1; i++){
			var temp = $("<input type='text' name=uselessFaultcategory["+i+"] value="+data.faultcategoryList[i]+" />");
			$("#searchForm").append(temp);
		}
	}else{
		$("#sfaultcategory").val(data.faultcategory);//主要的分类
	}
	
	$('#documentarydetailsTable').bootstrapTable({
		 
		  //请求方法
               method: 'post',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
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
               url: "${ctx}/documentdetect/documentarydetails/faultCategoryData",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义    
               contentType : "application/x-www-form-urlencoded",
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
               onContextMenuItem: function(row, $el){},
               /*onClickRow: function(row, $el){
               },*/
               columns: [/*{
		        checkbox: true
		       
		    }
			,*/{
		        field: 'sbillno',
		        title: '故障单号',
		        //width : 110 ,
		        width : '20%',
		        formatter:function(value, row , index){
		        	return "<a  onclick=\"newtable('"+row.id+"')\" >"+value+"</a>";  //href='javascript:newtable("+row.id+");'
		         }
		       
		    }
			/*,{
		        field: 'sfaulttitle',
		        title: '故障标题',
		        width : 150
		       
		    }*/
			,{
		        field: 'sfaultcategory',
		        title: '故障种类',
//		        width :210
		        width : '50%'
		       
		    }
			,{
		        field: 'sfaultrepresent',
		        title: '故障现象',
//		        width : 160
		        width : '20%' 
		       
		    }
			,{
		        field: 'sStatus',
		        title: '单据状态',
//		        width : 160
		        width : '10%' 
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端
		  $('#documentarydetailsTable').bootstrapTable("toggleView");
		}


	});
		
  function getIdSelections() {
        return $.map($("#documentarydetailsTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  	function exportAll(){
		 // window.location = "${ctx}/faultcategorystatistic/faultcategorystatistic/export";
		 var form = $("<form>");  
		 form.attr('target', '');
		 form.attr('method', 'post');
		 form.attr('action', "${ctx}/documentarydetails/documentarydetails/export");
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

  	
	function newtable(id) {
		jp.openDialogView("故障种类统计-工单明细","${ctx}/documentdetect/documentarydetails/samplform?id="+id,window.screen.width*0.8+"", window.screen.height*0.7+"");
	}
	

  	//字符串转日期
  	function stringToDate(dateStr,separator){
  	     if(!separator){
  	            separator="-";
  	     }
  	     var dateArr = dateStr.split(separator);
  	     var year = parseInt(dateArr[0]);
  	     var month;                    
  	     if(dateArr[1].indexOf("0") == 0){
  	         month = parseInt(dateArr[1].substring(1));
  	     }else{
  	          month = parseInt(dateArr[1]);
  	     }
  	     var day = parseInt(dateArr[2]);
  	     var date = new Date(year,month -1,day);
  	     return date;
  	 }
  	
  	//日期转字符串
  	function dateToString(date){
  	  var year = date.getFullYear(); 
  	  var month =(date.getMonth() + 1).toString(); 
  	  var day = (date.getDate()).toString();  
  	  if (month.length == 1) { 
  	      month = "0" + month; 
  	  } 
  	  if (day.length == 1) { 
  	      day = "0" + day; 
  	  }
  	  var dateTime = year + "-" + month + "-" + day;
  	  return dateTime; 
  	}
  	
  	//获取指定时间的最后一天
    function getCurrentMonthLast (date){
     var endDate = new Date(date); //date 是需要传递的时间如：2018-08
     var month=endDate.getMonth();
     var nextMonth=++month;
     var nextMonthFirstDay=new Date(endDate.getFullYear(),nextMonth,1);
     var oneDay=1000*60*60*24;
     return new Date(nextMonthFirstDay-oneDay);
    }
</script>