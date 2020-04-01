<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	var width = window.screen.width;
	var height = window.screen.height;

	//初始化
	var progress1 = new Progress();progress1.renderOne('canvas1',140,4,0);
	var progress2 = new Progress();progress1.renderOne('canvas2',140,4,0);
	var progress3 = new Progress();progress1.renderOne('canvas3',140,4,0);
	var progress4 = new Progress();progress1.renderOne('canvas4',140,4,0);
	
	$("#search").click("click", function() {// 绑定查询按扭
		progress1.renderOne('canvas1',140,4,100);
		progress1.renderOne('canvas2',140,4,100);
		progress1.renderOne('canvas3',140,4,100);
		progress1.renderOne('canvas4',140,4,100);
//		$("#progress").css('width','100%');
//		$("#progress").parent().removeClass("active");
		$("#msg1").show();
		$("#search").attr("disabled","disabled");
		$("#stop").removeAttr("disabled");
		
		$("#panel-content1").append("<div style='color:green'>AAA查询：正常</div>");
		$("#panel-content1").append("<div style='color:green'>ANAA查询：正常</div>");
		$("#panel-content1").append("<div style='color:green'>HSS查询：正常</div>");
		
		$("#panel-content2").append("<div style='color:red'>软拨测：异常</div>");
	});
		 
	$("#stop").click("click", function() {// 停止询按扭
		$("#msg2").show();
		$("#search").removeAttr("disabled");
		$("#stop").attr("disabled","disabled");
	});
})   
	var Progress = function() {
	    var progress = {
	        textheight: null,
	        renderOne: function(id, length, r, percent) {
	            var canvas = document.getElementById(id);
	            var context = canvas.getContext("2d");
	            canvas.width = length;
	            canvas.height = length;
	            var i = 0;
	            var interval = setInterval(function() {
	                i++;
	                progress.render(context, length, r, i, percent);
	                if (i >= percent) {
	                    clearInterval(interval)
	                }
	            },
	            10)
	        },
	        render: function(context, length, r, i, percent) {
	            context.clearRect(0, 0, length, length);
	            context.beginPath();
	            var gradient = context.createLinearGradient(length, 0, 0, 0);
	            gradient.addColorStop("0", "#76EEC6");
	            gradient.addColorStop("1.0", "#63B8FF");
//	            context.strokeStyle = gradient;//外圆
	            context.strokeStyle = "#00ff00";//外圆
	            context.lineWidth = r;
	            context.arc(length / 2, length / 2, length / 2 - r, -0.5 * Math.PI, -0.5 * Math.PI + i * 0.02 * Math.PI, false);
	            context.stroke();
	            context.closePath();
	            context.beginPath();
	            context.strokeStyle = "#8d8d8d";
	            context.lineWidth = 2;
	            context.fillStyle = "#ffffff";
	            context.arc(length / 2, r, 0.6 * r, 0, 2 * Math.PI, false);
//	            context.stroke();//小圆圈
//	            context.fill();//小圆圈
	            context.closePath();
	            context.beginPath();
	            var radian = percent / 100 * 2 * Math.PI - 0.5 * Math.PI;
	            var x = Math.cos(radian) * (length / 2 - r) + length / 2;
	            var y = Math.sin(radian) * (length / 2 - r) + length / 2;
	            context.arc(x, y, 0.6 * r, 0, 2 * Math.PI, false);
//	            context.stroke();//小圆圈
//	            context.fill();//小圆圈
	            context.closePath();
	            context.beginPath();
	            context.lineWidth = 1;
	            context.strokeStyle = "#54DDAF";//外圆
	            context.fillStyle = "#54DDAF";//内圆
	            context.arc(length / 2, length / 2, length / 2 - 2 * r, 0, 2 * Math.PI);
	            context.fill();
	            context.closePath();
	            context.beginPath();
	            context.font = "bold " + (length / 2 - 2.5 * r) / 2 + "px 微软雅黑";
	            context.fillStyle = "#ffffff";
	            var text = percent + "%";
	            if(percent == 100){
	            	text = "√";
	            }else if(percent == 0){
	            	text = "待测";
	            }
	            textwidth = context.measureText(text).width;
	            if (this.textheight == null) {
	                var div = document.createElement("div");
	                document.body.appendChild(div);
	                div.innerHTML = text;
	                div.style.fontSize = ((length / 2 - 2.5 * r) / 2) + "px";
	                this.textheight = div.offsetHeight;
	                div.parentNode.removeChild(div)
	            }
	            textheight = this.textheight;
	            context.fillText(text, (length - textwidth) / 2, length / 2 + textheight / 4);
	            context.fill();
	            context.closePath()
	        }
	    };
	    return progress
	};
</script>