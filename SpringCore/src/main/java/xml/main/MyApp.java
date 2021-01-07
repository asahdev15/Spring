package xml.main;

import xml.domain.Coach;
import xml.domain.TrackCoach;

public class MyApp {

	public static void main(String[] args) {

		// create the object
		// Coach theCoach = new BaseballCoach();
		Coach theCoach = new TrackCoach();
		
		// use the object
		System.out.println(theCoach.getDailyWorkout());		
	}

}