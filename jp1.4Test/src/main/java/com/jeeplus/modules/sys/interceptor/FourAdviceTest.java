package com.jeeplus.modules.sys.interceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.service.BaseService;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.listener.PBSTrackManagerPostProcessor;
import com.jeeplus.modules.sys.utils.LOGunit;
import com.jeeplus.modules.sys.utils.UserUtils;

@Aspect
@Component
public class FourAdviceTest extends BaseService {
	@Pointcut("execution(* com.jeeplus.modules..*.*Controller.*(..))")
	private void testLog() {
	}

	@Before("testLog()")
	public void doBefore(JoinPoint joinPoint) {
//        System.out.println("*************doBefore*****************");
	}

	@AfterReturning(returning = "result", pointcut = "testLog()")
	public void doAfterReturning(Object result) {
		String cid = LogInterceptor.getConnId();
		if (cid != null) {
			Integer connid = Integer.valueOf(cid);
			if (connid != null) {
				User user = UserUtils.getUser();

				InetAddress address;
				try {
					if(result==null) {
						result="";
					}
					address = InetAddress.getLocalHost();
					LOGunit.web_log(user.getLoginName(), "FourAdviceTest", "doAfterReturning", "拦截AOP参数",
							address.getHostAddress(), "", "", new Date(), connid);
				} catch (UnknownHostException e) {
					System.out.println(e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@AfterThrowing(pointcut = "testLog()", throwing = "e")
	public void doAfterThrowing(Exception e){
		User user = UserUtils.getUser();
		String cid = LogInterceptor.getConnId();
		InetAddress address;
		try {
			if (cid != null) {
				Integer connid = Integer.valueOf(cid);
				address = InetAddress.getLocalHost();
				System.out.println("--------------------出现异常---------------");
				System.err.println(e.getMessage());
				System.err.println(e.getLocalizedMessage());
				
				LOGunit.web_log(user.getLoginName(), "FourAdviceTest", "doAfterThrowing", "出现异常", address.getHostAddress(),
						e.getMessage(), e.getLocalizedMessage().substring(0, 500), new Date(), connid);
			}

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@After("testLog()")
	public void doAfter() {
//    	System.out.println("*************doAfter*****************");
	}

	@Around("testLog()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("@Around:doAround1"); 
		Object o = pjp.proceed();
//        System.out.println("@Around:doAround2"); 
		return o;
	}

}
