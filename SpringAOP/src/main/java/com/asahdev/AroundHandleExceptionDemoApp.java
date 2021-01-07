package com.asahdev;

import java.util.logging.Logger;

import com.asahdev.config.DemoConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.asahdev.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

	private static Logger myLogger = 
					Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
			
	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService theFortuneService = 
				context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		myLogger.info("\nMain Program: AroundDemoApp");
		
		myLogger.info("Calling getFortune");
		
		String data = theFortuneService.getFortune(true);
		
		myLogger.info("\nMy fortune is: " + data);
		
		myLogger.info("Finished");
		
		// close the context
		context.close();
	}

}
