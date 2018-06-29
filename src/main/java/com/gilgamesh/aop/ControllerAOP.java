package com.gilgamesh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAOP {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
	
	@Pointcut("execution(public com.gilgamesh.aop.ResultBean *(..))")
	public void pointcut() {
		
	}
	
	@Around("pointcut()")
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
		ResultBean<?> result;
		try {
			result = (ResultBean<?>) pjp.proceed();
		} catch (Throwable e) {
			result = handlerException(pjp, e);
		}
		return result;
	}
	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultBean<?> result = new ResultBean<>();
		// 已知异常
		if (e instanceof Exception) {
			result.setResultMsg(e.getLocalizedMessage());
			result.setResultCode(ResultBean.FAIL);
		} else {
			logger.error(pjp.getSignature() + " error ", e);
			result.setResultMsg(e.toString());
			result.setResultCode(ResultBean.FAIL);
			// 未知异常是应该重点关注的，这里可以做其他操作
		}
		return result;
	}
}
