<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/webpage/include/taglib.jsp"%>

<!-- _login_page_ -->
<!--登录超时标记 勿删-->
<!DOCTYPE html>
<html>

<head>
<meta name="decorator" content="ani" />
<%-- <%@ include file="/webpage/include/bootstraptable.jsp"%> --%>
<%-- <script src="${ctxStatic}/plugin/jquery-validation/1.14.0/jquery.js" type="text/javascript"></script>
	<script src="${ctxStatic}/plugin/jquery-validation/1.14.0/jquery.validate.min.js" type="text/javascript"></script> --%>
<%-- 	<link type="text/css" href="${ctxStatic}/common/css/style.css" rel="stylesheet" /> --%>
<title>${fns:getConfig('productName')}登录</title>
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>
<script src="${ctxStatic}/common/js/anime.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {
				validateCode : {
					required : true,
					remote : "${pageContext.request.contextPath}/servlet/validateCodeServlet"
				}
			},
			messages : {
				username : {
					required : "请填写用户名."
				},
				password : {
					required : "请填写密码."
				},
				validateCode : {
					remote : "验证码不正确.",
					required : "请填写验证码."
				}
			},
			errorLabelContainer : "#messageBox",
			errorPlacement : function(error, element) {
				error.insertAfter(element);
			}
		});
	});
	// 如果在框架或在对话框中，则弹出提示并跳转到首页
	if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0) {
		alert('未登录或登录超时。请重新登录，谢谢！');
		top.location = "${ctx}";
	}
</script>

</head>


<body>

	<!-- <div class="page">
		<div class="container">
			<div class="left">
				<div class="login">登录</div>
				<div class="eula">欢迎使用！</div>
			</div>
			<div class="right">
				<svg viewBox="0 0 320 300">
        <defs>
          <linearGradient inkscape:collect="always" id="linearGradient"
						x1="13" y1="193.49992" x2="307" y2="193.49992"
						gradientUnits="userSpaceOnUse">
            <stop style="stop-color:#ff00ff;" offset="0" id="stop876" />
            <stop style="stop-color:#ff0000;" offset="1" id="stop878" />
          </linearGradient>
        </defs>
        <path
						d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143" />
      </svg>
				<div class="form">
					<label for="email">邮箱</label> <input type="email" id="email">
					<label for="password">密码</label> <input type="password"
						id="password"> <input type="submit" id="submit" value="提交">
				</div>
			</div>
		</div>
	</div> -->

	<div class="login-page">
		<div class="row">
			<marquee id="system_announcement_show_area"
				style="color: red; font-size: 20px;" onmouseover=this.stop();
				onmouseout=this.start();>通告展示区</marquee>
			<div class="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">

				<h1>全业务申告网络支撑系统</h1>
				<sys:message content="${message}" showType="1" />
				<form id="loginForm" role="form" action="${ctx}/login" method="post">
					<div class="form-content">
						<div class="form-group">
							<input type="text" id="username" name="username"
								class="form-control input-underline input-lg required"
								value="admin" placeholder="用户名">
						</div>

						<div class="form-group">
							<input type="password" id="password" name="password"
								value="skywin"
								class="form-control input-underline input-lg required"
								placeholder="密码">
						</div>
						<div class="form-group">
							<input type="text" id="sendSMS" name="sendSMS" value="1"
								class="form-control input-underline input-lg required"
								placeholder="验证码">
							<button id="getsms" class="btn btn-info">获取验证码</button>
						</div>
						<c:if test="${isValidateCodeLogin}">
							<div class="form-group  text-muted">
								<label class="inline"><font color="white">验证码:</font></label>
								<sys:validateCode name="validateCode"
									inputCssStyle="margin-bottom:20px;width:100px;height:40px;"
									buttonCssStyle="color:white" />
							</div>
						</c:if>

					</div>
					<input type="submit"
						class="btn btn-white btn-outline btn-lg btn-rounded progress-login"
						value="登录"> &nbsp;

				</form>
				<div>

					<p>斯凯文软件技术（广东）有限公司维护人员</p>
					<p>值班电话： 18926208919</p>
					<p>张某人 18926208919 zhangchao@skywin.com.cn</p>
				</div>
			</div>
		</div>
	</div>
<script>
var current = null;
document.querySelector('#email').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: 0,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#password').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -336,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#submit').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -730,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '530 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});</script>
	<script>
		$(function() {
			$('.theme-picker').click(function() {
				changeTheme($(this).attr('data-theme'));
			});

		});
		$(function() {
			$('#getsms').click(function() {
				var username = document.getElementById("username").value;
				var password = document.getElementById("password").value;
				/* , {
					username : username,
					password : password
				}, */
				jp.alert("获取验证码");
				jp.get("${ctx}/login/sendSms?username=" + username + "&password=" + password, function(data) {
					if (data.success == true) {
						jp.alert(data.message);
					} else {
						jp.alert(data.message);
					}
				});
			});

		});
		function changeTheme(theme) {
			$('<link>').appendTo('head').attr({
				type : 'text/css',
				rel : 'stylesheet'
			}).attr('href', '${ctxStatic}/common/css/app-' + theme + '.css');
			//$.get('api/change-theme?theme='+theme);
			$.get('${pageContext.request.contextPath}/theme/' + theme + '?url=' + window.top.location.href, function(result) {
			});
		}
	</script>
	<style>
li.color-picker i {
	font-size: 24px;
	line-height: 30px;
}

.red-base {
	color: #D24D57;
}

.blue-base {
	color: #3CA2E0;
}

.green-base {
	color: #27ae60;
}

.purple-base {
	color: #957BBD;
}

.midnight-blue-base {
	color: #2c3e50;
}

.lynch-base {
	color: #6C7A89;
}
</style>
</body>
</html>