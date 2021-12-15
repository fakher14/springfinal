package tn.spring.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogginAspect {

	private static final Logger l = LogManager.getLogger(LogginAspect.class);
	@Before("execution(* tn.spring.services.*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("In methode "+name + " : ");
	}
	/*
	 *@Around("execution(* tn.spring.services.*.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long elapsedTime = System.currentTimeMillis() - start;
	l.info("Method execution time: " + elapsedTime + " milliseconds.");
	return obj;
	} 
	 */
	
	
	@AfterReturning("execution(* tn.spring.services.*.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("after method " + name + " : ");
		}
	@AfterThrowing("execution(* tn.spring.services.*.*(..))")
	public void logMethodthrow(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("exeption in method " + name + " : ");
		}
	
	
	
}

