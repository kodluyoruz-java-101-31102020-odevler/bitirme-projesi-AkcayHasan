package com.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MethodRunningTimeAspect {

	@Around("@annotation(runningTimeAspect)")
	public Object execute(ProceedingJoinPoint proceedingJoinPoint, MethodRunningTime runningTimeAspect) throws Throwable {
		if(!runningTimeAspect.active()) {
			return proceedingJoinPoint.proceed();
		}
		String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = proceedingJoinPoint.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		
		System.out.println(className + "#" + methodName + " runnedin" + stopWatch.getTotalTimeMillis()+ " ms");
		return result;
		
	}
		
	
}
