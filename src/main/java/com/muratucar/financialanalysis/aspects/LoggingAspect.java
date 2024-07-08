package com.muratucar.financialanalysis.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	
	private static Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Before("execution(* com.muratucar.financialanalysis.service..*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
		logger.info("Entering: " + joinPoint.getSignature().toShortString());
	}

	@AfterReturning(pointcut = "execution(* com.muratucar.financialanalysis.service..*.*(..))", returning = "result")
	public void logMethodExit(JoinPoint joinPoint, Object result) {
		logger.info("Exiting: " + joinPoint.getSignature().toShortString() + " with result: " + result);
	}

	@After("execution(* com.muratucar.financialanalysis.service..*.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		logger.info("Çıkış: " + joinPoint.getSignature().toShortString());
	}

	@Before("execution(* com.muratucar.financialanalysis.repository..*.*(..))")
	public void logRepositoryMethodEntry(JoinPoint joinPoint) {
		logger.info("Entering Repository method: " + joinPoint.getSignature().toShortString());
	}

	@AfterReturning(pointcut = "execution(* com.muratucar.financialanalysis.repository..*.*(..))", returning = "result")
	public void logRepositoryMethodExit(JoinPoint joinPoint, Object result) {
		logger.info(
				"Exiting Repository method: " + joinPoint.getSignature().toShortString() + " with result: " + result);
	}

	@After("execution(* com.muratucar.financialanalysis.repository..*.*(..))")
	public void logRepositoryMethodExit(JoinPoint joinPoint) {
		logger.info("Exiting Repository method: " + joinPoint.getSignature().toShortString());
	}

	@Before("execution(* com.muratucar.financialanalysis.controller..*.*(..))")
	public void logControllerMethodEntry(JoinPoint joinPoint) {
		logger.info("Entering Controller method: " + joinPoint.getSignature().toShortString());
	}

	@AfterReturning(pointcut = "execution(* com.muratucar.financialanalysis.controller..*.*(..))", returning = "result")
	public void logControllerMethodExit(JoinPoint joinPoint, Object result) {
		logger.info(
				"Exiting Controller method: " + joinPoint.getSignature().toShortString() + " with result: " + result);
	}

	@After("execution(* com.muratucar.financialanalysis.controller..*.*(..))")
	public void logControllerMethodExit(JoinPoint joinPoint) {
		logger.info("Exiting Controller method: " + joinPoint.getSignature().toShortString());
	}

	 
	 
 
	
}
