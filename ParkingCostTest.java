package ParkingCostCalculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.LocalTime;

public class ParkingCostTest {

	final static byte AM = 0;
	final static byte PM = 1;

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:\\selenium-java-3.141.59\\geckodriver.exe");		
		WebDriver driver  = new FirefoxDriver();
		
		//checking to make sure the page has loaded
		ParkingCost.openSite(driver);
		ParkingCost.checkHeading(driver);
		ParkingCost.checkButton(driver);
		
		//testValet(driver);
		testShortTerm(driver);
	}
	
	public static void testValet(WebDriver driver)
	{
		System.out.println("---Valet Parking Scenarios---");
		final String VALET_PARKING = "Valet Parking";
		
		//Valet Parking prices
		final double VALET_5_HOUR_COST = 12.00;//1 min - 5 hours
		final double VALET_DAY_COST = 18.00;//5 hr 1 min - 24 hours
		
		//5 hour
		double valet5hrCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "05:00", PM).calculateCost();
		
		if(VALET_5_HOUR_COST == valet5hrCalcCost)
		{
			System.out.println(" 5 hour price is correct: " + valet5hrCalcCost);
		}
		else
		{
			System.out.println(" 5 hour price is incorrect. Expected: " + VALET_5_HOUR_COST + " Actual: " + valet5hrCalcCost);
		}
		
		//1 min
		double valet1minCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "12:01", PM).calculateCost();
		
		if(VALET_5_HOUR_COST == valet1minCalcCost)
		{
			System.out.println(" 1 min price is correct: " + valet1minCalcCost);
		}
		else
		{
			System.out.println(" 1 hour price is incorrect. Expected: " + VALET_5_HOUR_COST + " Actual: " + valet1minCalcCost);
		}
		
		//5 hr 1 min
		double valet5hr1minCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "05:01", PM).calculateCost();
		
		if(VALET_DAY_COST == valet5hr1minCalcCost)
		{
			System.out.println(" 5 hr 1 min price is correct. " + valet5hr1minCalcCost);
		}
		else
		{
			System.out.println(" 5 hr 1 hour price is incorrect. Expected: " + VALET_DAY_COST + " Actual: " + valet5hr1minCalcCost);
		}
		
		//24 hour
		double valet24hrCalCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", AM, "09/26/2021", "12:00", AM).calculateCost();
		
		if(VALET_DAY_COST == valet24hrCalCost)
		{
			System.out.println(" 24 hr price is correct. " + valet24hrCalCost);
		}
		else
		{
			System.out.println(" 24 hr price is incorrect. Expected: " + VALET_DAY_COST + " Actual: " + valet24hrCalCost);
		}
		
		//24 hour 1 min
		double valet24hr1minCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "09/26/2021", "12:01", PM).calculateCost();
		
		if((VALET_DAY_COST + VALET_5_HOUR_COST) == valet24hr1minCalcCost)
		{
			System.out.println(" 24 hr 1 min price is correct. " + valet24hr1minCalcCost);
		}
		else
		{
			System.out.println(" 24 hr 1 min price is incorrect. Expected: " + (VALET_DAY_COST + VALET_5_HOUR_COST) + " Actual: " + valet24hr1minCalcCost);
		}
		
		//29 hour
		double valet29hrCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", AM, "09/26/2021", "05:00", AM).calculateCost();
		
		if((VALET_DAY_COST + VALET_5_HOUR_COST) == valet29hrCalcCost)
		{
			System.out.println(" 29 hr price is correct. " + valet29hrCalcCost);
		}
		else
		{
			System.out.println(" 29 hr price is incorrect. Expected: " + (VALET_DAY_COST + VALET_5_HOUR_COST) + " Actual: " + valet29hrCalcCost);
		}
		
		//29 hr 1 min
		double valet29hr1minCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "09/26/2021", "5:01", PM).calculateCost();
		
		if((VALET_DAY_COST*2) == valet29hr1minCalcCost)
		{
			System.out.println(" 29 hr 1 min price is correct. " + valet29hr1minCalcCost);
		}
		else
		{
			System.out.println(" 29 hr 1 min price is incorrect. Expected: " + (VALET_DAY_COST*2) + " Actual: " + valet29hr1minCalcCost);
		}
		
		//48 hr
		double valet48hrCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", AM, "09/27/2021", "12:00", AM).calculateCost();
		
		if((VALET_DAY_COST*2) == valet48hrCalcCost)
		{
			System.out.println(" 48 hr price is correct. " + valet48hrCalcCost);
		}
		else
		{
			System.out.println(" 48 hr price is incorrect. Expected: " + (VALET_DAY_COST*2) + " Actual: " + valet48hrCalcCost);
		}
		
		//48 hr 1 min
		double valet48hr1minCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "09/27/2021", "12:01", PM).calculateCost();
		
		if((VALET_DAY_COST*2 + VALET_5_HOUR_COST)  == valet48hr1minCalcCost)
		{
			System.out.println(" 48 hr 1 min price is correct. " + valet48hr1minCalcCost);
		}
		else
		{
			System.out.println(" 48 hr 1 min price is incorrect. Expected: " + (VALET_DAY_COST*2 + VALET_5_HOUR_COST) + " Actual: " + valet48hr1minCalcCost);
		}
		
		//53 hr
		double valet53hrCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", AM, "09/27/2021", "05:00", AM).calculateCost();
		
		if((VALET_DAY_COST*2 + VALET_5_HOUR_COST)  == valet53hrCalcCost)
		{
			System.out.println(" 53 hr price is correct. " + valet53hrCalcCost);
		}
		else
		{
			System.out.println(" 53 hr price is incorrect. Expected: " + (VALET_DAY_COST*2 + VALET_5_HOUR_COST) + " Actual: " + valet53hrCalcCost);
		}
		
		//53 hr 1 min
		double valet53hr1minCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", AM, "09/27/2021", "05:01", AM).calculateCost();
		
		if((VALET_DAY_COST*3)  == valet53hr1minCalcCost)
		{
			System.out.println(" 53 hr 1 min price is correct. " + valet53hr1minCalcCost);
		}
		else
		{
			System.out.println(" 53 hr 1 min price is incorrect. Expected: " + (VALET_DAY_COST*3) + " Actual: " + valet53hr1minCalcCost);
		}
		
		//7 days
		double valet7DaysCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", AM, "10/02/2021", "12:00", AM).calculateCost();
		
		if((VALET_DAY_COST*7)  == valet7DaysCalcCost)
		{
			System.out.println(" 7 day price is correct. " + valet7DaysCalcCost);
		}
		else
		{
			System.out.println(" 7 day price is incorrect. Expected: " + (VALET_DAY_COST*7) + " Actual: " + valet7DaysCalcCost);
		}
		
		//30 days
		double valet30DaysCalcCost = new ParkingCost(driver, VALET_PARKING, "09/25/2021", "12:00", PM, "10/25/2021", "12:00", PM).calculateCost();
		
		if((VALET_DAY_COST*30)  == valet30DaysCalcCost)
		{
			System.out.println(" 30 day price is correct. " + valet30DaysCalcCost);
		}
		else
		{
			System.out.println(" 30 day price is incorrect. Expected: " + (VALET_DAY_COST*30) + " Actual: " + valet30DaysCalcCost);
		}
		
		//365 days
		double valet365DaysCalcCost = new ParkingCost(driver, VALET_PARKING, "01/01/2021", "12:00", AM, "01/01/2022", "12:00", AM).calculateCost();
		
		if((VALET_DAY_COST*365)  == valet365DaysCalcCost)
		{
			System.out.println(" 365 day price is correct. " + valet365DaysCalcCost);
		}
		else
		{
			System.out.println(" 365 day price is incorrect. Expected: " + (VALET_DAY_COST*365) + " Actual: " + valet365DaysCalcCost);
		}
		
	}
	
	public static void testShortTerm(WebDriver driver)
	{
		System.out.println("\n\n---Short Term Parking Scenarios---");
		final String SHORT_TERM_PARKING = "Short-Term Parking";
		
		//Short Term Parking prices
		final double FIRST_HOUR = 2.00;//1 min - 60 min
		final double ADDITIONAL_HALF_HOUR = 1.00;
		final double DAILY_MAXIMUM = 24.00;
		
		double shortTerm1minCalc = new ParkingCost(driver, SHORT_TERM_PARKING, "09/25/2021", "12:00", AM, "09/25/2021", "12:01", AM).calculateCost();
		
		if(FIRST_HOUR == shortTerm1minCalc)
		{
			System.out.println(" 1 min price is correct. " + shortTerm1minCalc);
		}
		else
		{
			System.out.println(" 1 min price is incorrect. Expected: " + FIRST_HOUR + " Actual: " + shortTerm1minCalc);
		}
		
		double shortTerm1hrCalc = new ParkingCost(driver, SHORT_TERM_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "01:00", PM).calculateCost();
		
		if(FIRST_HOUR == shortTerm1hrCalc)
		{
			System.out.println(" 1 hr price is correct. " + shortTerm1hrCalc);
		}
		else
		{
			System.out.println(" 1 hr price is incorrect. Expected: " + FIRST_HOUR + " Actual: " + shortTerm1hrCalc);
		}
		
		double shortTerm1hr1minCalc = new ParkingCost(driver, SHORT_TERM_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "01:01", PM).calculateCost();
		
		if((FIRST_HOUR + ADDITIONAL_HALF_HOUR) == shortTerm1hr1minCalc)
		{
			System.out.println(" 1 hr 1 min price is correct. " + shortTerm1hr1minCalc);
		}
		else
		{
			System.out.println(" 1 hr 1 min price is incorrect. Expected: " + (FIRST_HOUR + ADDITIONAL_HALF_HOUR) + " Actual: " + shortTerm1hr1minCalc);
		}
		
		double shortTerm1hr30minCalc = new ParkingCost(driver, SHORT_TERM_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "01:30", PM).calculateCost();
		
		if((FIRST_HOUR + ADDITIONAL_HALF_HOUR) == shortTerm1hr30minCalc)
		{
			System.out.println(" 1 hr 30 min price is correct. " + shortTerm1hr30minCalc);
		}
		else
		{
			System.out.println(" 1 hr 30 min price is incorrect. Expected: " + (FIRST_HOUR + ADDITIONAL_HALF_HOUR) + " Actual: " + shortTerm1hr30minCalc);
		}
		
		double shortTerm1hr31minCalc = new ParkingCost(driver, SHORT_TERM_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", "01:31", PM).calculateCost();
		
		if((FIRST_HOUR + ADDITIONAL_HALF_HOUR*2) == shortTerm1hr31minCalc)
		{
			System.out.println(" 1 hr 31 min price is correct. " + shortTerm1hr31minCalc);
		}
		else
		{
			System.out.println(" 1 hr 31 min price is incorrect. Expected: " + (FIRST_HOUR + ADDITIONAL_HALF_HOUR*2) + " Actual: " + shortTerm1hr31minCalc);
		}
		
		//DAILY_MAXIMUM
		LocalTime time = LocalTime.of(12, 00);
		
		for(int hour=1;hour <= 24;hour++)
		{
			String timeStr = time.plusHours(1).toString();
			
			double shortTermDailyMax = new ParkingCost(driver, SHORT_TERM_PARKING, "09/25/2021", "12:00", PM, "09/25/2021", timeStr, PM).calculateCost();
			
			if(hour > 1)
			{		
				if((FIRST_HOUR + ADDITIONAL_HALF_HOUR*2) == shortTerm1hr31minCalc)
				{
					System.out.println(" 1 hr 31 min price is correct. " + shortTerm1hr31minCalc);
				}
				else
				{
					System.out.println(" 1 hr 31 min price is incorrect. Expected: " + (FIRST_HOUR + ADDITIONAL_HALF_HOUR*2) + " Actual: " + shortTerm1hr31minCalc);
				}
			}
			else
			{
				if(FIRST_HOUR == shortTerm1minCalc)
				{
					System.out.println(" 1 min price is correct. " + shortTerm1minCalc);
				}
				else
				{
					System.out.println(" 1 min price is incorrect. Expected: " + FIRST_HOUR + " Actual: " + shortTerm1minCalc);
				}
			}

		}
		
	}
}