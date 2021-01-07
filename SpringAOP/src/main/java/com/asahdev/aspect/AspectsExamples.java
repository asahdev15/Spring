package com.asahdev.aspect;

public class AspectsExamples {
    // this is where we add all of our related advices for logging
    // let's start with an @Before advice

//	@Before("execution(* add*())")
//	@Before("execution(public void add*())")
//	@Before("execution(public void addAccount())")
//	@Before("execution(public void com.asahdev.dao.AccountDAO.addAccount())")
//	@Before("execution(* add*(com.asahdev.Account, ..))")
//	@Before("execution(* add*(..))")
//	@Before("execution(* com.asahdev.dao.*.*(..))")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//	}



//	@Pointcut("execution(* com.asahdev.dao.*.*(..))")
//	private void forDaoPackage() {}
//
//	@Before("forDaoPackage()")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====>>> Executing @Before advice on method");
//	}
//
//	@Before("forDaoPackage()")
//	public void performApiAnalytics() {
//		System.out.println("\n=====>>> Performing API analytics");
//	}


}
