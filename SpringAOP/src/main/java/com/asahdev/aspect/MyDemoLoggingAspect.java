package com.asahdev.aspect;

import com.asahdev.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());


	@Before("com.asahdev.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		myLogger.info("\n=====>>> Executing @Before advice on method");

		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		myLogger.info("Method: " + methodSig);

		// display method arguments

		// get args
		Object[] args = theJoinPoint.getArgs();

		// loop thru args
		for (Object tempArg : args) {
			myLogger.info(tempArg.getClass() + " : " + tempArg.toString());

			if (tempArg instanceof Account) {

				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;

				myLogger.info("-- account name: " + theAccount.getName());
				myLogger.info("-- account level: " + theAccount.getLevel());

			}
		}

	}


	// add a new advice for @AfterReturning on the findAccounts method

	@AfterReturning(
			pointcut="execution(* com.asahdev.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);

		// print out the results of the method call
		myLogger.info("\n=====>>> result is: " + result);

		// let's post-process the data ... let's modify it :-)

		// convert the account names to uppercase
		for (Account tempAccount : result) {

			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();

			// update the name on the account
			tempAccount.setName(theUpperName);
		}

		myLogger.info("\n=====>>> result is: " + result);

	}

	@AfterThrowing(
			pointcut="execution(* com.asahdev.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		myLogger.info("\n=====>>> The exception is: " + theExc);

	}

	@After("execution(* com.asahdev.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @After (finally) on method: " + method);

	}

	@Around("execution(* com.asahdev.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method: " + method);

		// get begin timestamp
		long begin = System.currentTimeMillis();

		// now, let's execute the method
		Object result = null;

		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());

//			// rethrow exception
//			throw e;

			// give users a custom messagee
			result = "Major accident! But no worries, "
					+ "your private AOP helicopter is on the way!";
		}

		// get end timestamp
		long end = System.currentTimeMillis();

		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n=====> Duration: " + duration / 1000.0 + " seconds");

		return result;
	}

}