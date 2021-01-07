package annotations.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Component("bean-id")
//@Scope("prototype")
public class TennisCoach implements Coach {



	@Autowired
//	@Qualifier("happyFortuneService")
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;

	// define a default constructor
	public TennisCoach() {
		System.out.println(">> TennisCoach: inside default constructor");
	}

	/*
	@Autowired
	public TennisCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	*/
//
//	// define a setter method
//	@Autowired
//	public void setFortuneService(FortuneService theFortuneService) {
//		System.out.println(">> TennisCoach: inside setFortuneService() method");
//		this.fortuneService = theFortuneService;
//	}

	// define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> TennisCoach: inside of doMyStartupStuff()");
	}

	// define my destroy method
	// Not called for prototype beans
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println(">> TennisCoach: inside of doMyCleanupStuff()");
	}

	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}