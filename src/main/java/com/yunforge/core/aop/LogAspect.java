package com.yunforge.core.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yunforge.base.model.SysLog;
import com.yunforge.base.model.User;
import com.yunforge.base.service.SysLogManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.base.web.controller.DictController;
import com.yunforge.common.Constants;
import com.yunforge.core.annotation.MethodRemark;

@Component
@Aspect
// 该注解标示该类为切面类
public class LogAspect {
	final static Log log = LogFactory.getLog(LogAspect.class);
	@Autowired
	private SysLogManager sysLogManager;

	@Autowired
	private UserManager userManager;

	/*
	 * Web 层的Join point
	 */
	 @Pointcut("within(com.yunforge.*.web.controller..*)")
	//@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void inWebLayer() {
	}

	/*
	 * service 层的Join point
	 */
	@Pointcut("within(com.yunforge.*.service..*)")
	public void inServiceLayer() {
	}

	@Before("inWebLayer()")
	public void beforeInvoke() {
		System.out.println("aop 拦截 ，前置通知");// 目标对象的方法执行前执行
	}

	@AfterReturning(pointcut = "inWebLayer()", returning = "retVal")
	public void afterInvoke(Object retVal) {
		System.out.println("aop 拦截 ，后置通知" + ";retVal=" + retVal);// 目标对象的方法正常执行后执行
	}

	@After("inWebLayer()")
	public void afterReturnInvoke() {
		System.out.println("aop 拦截 ，最终通知");// 目标对象的方法无论正常还是异常都执行，即try{}catch(){}finally{}中finally代码段
	}

	// 方法执行的前后调用
	@Around("inWebLayer()")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Calendar ca = Calendar.getInstance();
		String operDate = df.format(ca.getTime());
		String ip = getIpAddress(request);
		Subject currentUser = SecurityUtils.getSubject();
		String fullName = null;
		String loginName = null;
		Object principal=currentUser.getPrincipal();
		if (currentUser != null && principal!=null) {
			loginName = principal.toString();
			log.info("登录名:"+loginName);
			User user = (User) currentUser.getSession().getAttribute(
					Constants.SESSION_USER_KEY);
			if (user != null) {
				fullName = user.getFullName();
			} else {
				fullName = userManager.getFullName(loginName);
			}
		} else {
			loginName = "anonymous";
			fullName = "匿名用户";
		}
		String methodRemark = getMethodRemark(point);
		String methodName = point.getSignature().getName();
		String packages = point.getThis().getClass().getName();
		if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
			try {
				packages = packages.substring(0, packages.indexOf("$$"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Object object;
		try {
			object = point.proceed();
		} catch (Exception e) {
			// 异常处理记录日志..log.error(e);
			throw e;
		}
		try {

			SysLog sysLog = new SysLog();
			sysLog.setIpAddress(ip);
			sysLog.setUsername(loginName);
			sysLog.setFullName(fullName);
			sysLog.setOperDesc(packages + "." + methodName + ";" + methodRemark);
			sysLog.setOperDate(operDate);
			sysLogManager.saveLog(sysLog);
		} catch(Exception e) {
			
		}
		return object;
	}

	@AfterThrowing("execution(* com.yunforge.*.web.controller.*.*(..))")
	public void exceptionInvoke() {
		System.out.println("aop 拦截 ，异常通知");// 目标对象的方法抛出异常将执行
	}

	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	// 获取方法的中文备注,用于记录用户的操作日志描述
	@SuppressWarnings("rawtypes")
	public static String getMethodRemark(ProceedingJoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();

		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String method = "";
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					MethodRemark methodRemarkCache = m
							.getAnnotation(MethodRemark.class);
					if (methodRemarkCache != null) {
						method = methodRemarkCache.remark();
					}
					break;
				}
			}
		}
		return method;
	}

}
