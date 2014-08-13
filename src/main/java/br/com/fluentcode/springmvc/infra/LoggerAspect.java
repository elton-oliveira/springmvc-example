package br.com.fluentcode.springmvc.infra;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Pointcut("execution(* br.com.fluentcode..*.*(..))")
	public void allMethods() {
	}
	
	@Around("allMethods()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger("aspect.".concat(joinPoint.getTarget().getClass().getName()));
		logger.trace("Running > {}", joinPoint.getSignature().toLongString());
		Object[] args = joinPoint.getArgs();
		for(int i=0; i<args.length; i++){
			Object arg = args[i];
			logger.trace("Arg[{}] > {}", i, arg); 
		}
		Object object = null;
		try {
			object = joinPoint.proceed();
			logger.trace("Return from {} > {}", joinPoint.getSignature().toShortString(), object);
		} catch (Throwable e) {
			logger.error("Throwable >", e);
			throw e;
		}
		return object;
	}
	
}
