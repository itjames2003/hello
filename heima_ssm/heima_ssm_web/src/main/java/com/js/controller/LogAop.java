package com.js.controller;

import com.js.domain.SysLog;
import com.js.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//日志切面类
@Component
@Aspect
public class LogAop {
	private Date startTime;//开始时间
	private Class clazz;//访问是的类
	private Method method;//访问的方法
	SysLog sysLog=new SysLog();
	@Autowired
	private ISysLogService sysLogService;
	//web 配置文件已配置了 所以直接注入进来得到
	@Autowired
	private HttpServletRequest request;

	//配置切入点表达式
	@Pointcut("execution(* com.js.controller.*.*(..))")
	public void pt() {
	}

	//前置通知 (获取开始时间 执行的类 访问的方法)
	@Before("pt()")
	public void doBefore(JoinPoint jp) throws NoSuchMethodException,SecurityException {
		startTime = new Date();
		sysLog.setVisitTime(startTime);

		clazz = jp.getTarget().getClass();
		String methodName = jp.getSignature().getName();//获取访问的方法名称
		Object[] args = jp.getArgs();
		//获取具体执行的方法的Method对象
		if (args == null || args.length == 0) {
			method = clazz.getMethod(methodName);//只能获取无参的方法
		} else {
			Class[] classArgs = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				classArgs[i] = args[i].getClass();
			}
			method=clazz.getMethod(methodName, classArgs);
		}
		sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
	}

	//后置通知 主要获取日志中其它信息，时长、ip、url...
	@After("pt()")
	public void doAfter(JoinPoint jp) {
		long time = new Date().getTime() - startTime.getTime();//获取访问时间
		sysLog.setExecutionTime(time);

		//获取URL
		String url="";
		if(clazz!=null&&method!=null&&clazz!=SysLogController.class){
			//获取类上的@RequestMapping
			RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
			if(classAnnotation !=null){
				String[] classValue = classAnnotation.value();
				//获取方法上的RequestMapping
				RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
				if(methodAnnotation!=null){
					String[] methodValue = methodAnnotation.value();
					url=classValue[0]+methodValue[0];
					sysLog.setUrl(url);
				}
			}
		}
		//获取ip
		String ip = request.getRemoteAddr();
		sysLog.setIp(ip);

		//获取当前操作的用户
		SecurityContext context =SecurityContextHolder.getContext();//从上下文获取当前登录的用户
		User principal = (User) context.getAuthentication().getPrincipal();
		String username = principal.getUsername();
		sysLog.setUsername(username);

		//将日志相关信息封装到syslog
		sysLogService.save(sysLog);

	}
}

