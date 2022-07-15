package com.revature.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect //An aspect is a modularization of a concern that cuts across multiple classes
@Configuration // this tells Spring to configure this aspect as part of the global ApplicationContext
public class BeforeAspect {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution (* com.revature.service.*.*(..))") //define a PointCut this is SpEL Spring Expression Language
	public void before(JoinPoint joinPoint) {
		
		//this code will fire BEFORE the invocation of any method in the data layer
		log.info("Checking for user access.....");
		
		log.info("Intercepted method call of {}", joinPoint);
		
	}
	
	
}
