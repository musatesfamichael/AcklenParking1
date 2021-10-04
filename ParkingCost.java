package ParkingCostCalculator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ParkingCost {
	
	WebDriver driver;
	private String parkingLot;
	private String startDate;
	private String startTime;
	private int startAMPM;
	private String endDate;
	private String endTime;
	private int endAMPM;
	
	ParkingCost(WebDriver driver, String parkingLot, String startDate, String startTime, int startAMPM, String endDate, String endTime, int endAMPM)
    {
		this.driver = driver;
		this.parkingLot = parkingLot;
		this.startDate = startDate;
		this.startTime = startTime;
		this.startAMPM = startAMPM;
		this.endDate = endDate;
		this.endTime = endTime;
		this.endAMPM = endAMPM;
	}
	
	/*
	 * Opens Parking Cost Calculator web site.
	 * Validates URL and page title are correct 
	 */
	public static void openSite(WebDriver driver) throws WebDriverException
	{
		final String URL = "https://www.shino.de/parkcalc/";
		driver.get(URL);
		
		String currentURL = driver.getCurrentUrl();
		
		if(currentURL.equals(URL))
		{
			System.out.println("URL matches");
			
			String currentTitle = driver.getTitle();
			final String TITLE = "Parking Cost Calculator";
			
			if(currentTitle.equals(TITLE))
			{
				System.out.println("Title matches");
			}
			else
			{
				System.out.println("Title doesn't match...stopping test");
				System.out.println("Expected Title: " + TITLE + " , Actual Title: " + currentTitle);
				System.exit(0);
			}
		}
		else
		{
			System.out.println("URL doesn't match...stopping test");
			System.out.println("Expected URL: " + URL + " , Actual URL: " + currentURL);
			System.exit(0);
		}
	}
	
	/*
	 * Make sure heading is correct. This ensures the page is loaded.
	 */
	public static void checkHeading(WebDriver driver)
	{
		final String currentPageHeading = driver.findElement(By.className("PageTitle")).getText();
		final String PAGEHEADING = "PARKING COST CALCULATOR";
		
		if(currentPageHeading.equals(PAGEHEADING))
		{
			System.out.println("Page Heading matches");
		}
		else
		{
			System.out.println("Page Heading doesn't match...stopping test");
			System.out.println("Expected heading: " + PAGEHEADING + " , Actual heading: " + currentPageHeading);
			System.exit(0);
		}
	}
	
	/*
	 * Make sure submit button is present
	 */
	public static void checkButton(WebDriver driver)
	{
		if(driver.findElement(By.name("Submit")).isDisplayed())
		{
			System.out.println("Submit button is present");
		}
		else
		{
			System.out.println("Submit button is not displayed...stopping test");
			System.exit(0);
		}
	}
	
	/*
	 * Populate HTML form, click calculate button, and return the Estimate Parking Cost value from web page
	 */
	public Double calculateCost()
	{
		//retrieve web elements
		WebElement startingDate = this.driver.findElement(By.id("StartingDate"));
		WebElement startingTime = this.driver.findElement(By.id("StartingTime"));
		
		WebElement leavingDate = this.driver.findElement(By.id("LeavingDate"));
		WebElement leavingTime = this.driver.findElement(By.id("LeavingTime"));
		
		List<WebElement> startingTimeAMPM = this.driver.findElements(By.name("StartingTimeAMPM")); 
		List<WebElement> leavingTimeAMPM = this.driver.findElements(By.name("LeavingTimeAMPM"));
		
		//populate form
		new Select(this.driver.findElement(By.id("ParkingLot"))).selectByVisibleText(parkingLot);

		startingDate.clear();
		startingDate.sendKeys(this.startDate);
		startingTime.clear();
		startingTime.sendKeys(this.startTime);
		startingTimeAMPM.get(this.startAMPM).click();
		
		leavingDate.clear();
		leavingDate.sendKeys(this.endDate);
		leavingTime.clear();
		leavingTime.sendKeys(this.endTime);
		leavingTimeAMPM.get(this.endAMPM).click();
		
		//calculate button
		driver.findElement(By.name("Submit")).click();
		
		//retrieve cost value from HTML. strip non numeric text. convert to double
		List<WebElement> subHead = driver.findElements(By.className("SubHead"));
		WebElement spanElement = subHead.get(1);
		String cost = spanElement.getText();
		String removeNonNumeric = cost.substring(2).replace(",", "");//remove first two characters which are a $ and a space. remove the , symbol
		double costNumber = Double.parseDouble(removeNonNumeric);
		return costNumber;
		
		//return Double.parseDouble(driver.findElements(By.className("SubHead")).get(1).getText().substring(0, 2));
	}

	public String getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getStartAMPM() {
		return startAMPM;
	}

	public void setStartAMPM(int startAMPM) {
		this.startAMPM = startAMPM;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getEndAPMPM() {
		return endAMPM;
	}

	public void setEndAPMPM(int endAPMPM) {
		this.endAMPM = endAPMPM;
	}
}