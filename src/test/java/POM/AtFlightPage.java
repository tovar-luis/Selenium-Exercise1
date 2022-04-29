package POM;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import selenium.ClsReport;
import selenium.ClsWebElements;

public class AtFlightPage extends ClsWebElements{

	
	//Locators
	private By flightFilterSelect = By.id("mat-input-19");
	private By cheapFlightsOption = By.xpath("//option[@value='PriceLowToHigh']");
	private By cheapestFlight = By.xpath("(//div[@class='flightLists ng-star-inserted']//div[@class='flightItem ng-star-inserted'])[1]");
	private By priceButton = By.xpath("(//div[@class='flightLists ng-star-inserted']//div[@class='flightItem ng-star-inserted'])[1]//div[@class='col fareRegular ng-star-inserted']");
	private By basicCard = By.xpath("(//mat-card[contains(@class, 'basic')])[1]");
	private By flightPageValidation = By.xpath("//h1[text()='Elige tu vuelo de salida de']");
	private By selectedDay = By.xpath("//a[@class='selected-day']//span[@class='date']");
	
	
	//Methods
	public void filterFlightsByPrice(){
		WaitForLoad();
		GetFluentWait(cheapFlightsOption);
		WaitForElementClickable(cheapFlightsOption);
		Click(cheapFlightsOption);
	}
	
	public void selectCheapestOption() throws InterruptedException{
		Thread.sleep(1000);
		WaitForLoad();
		GetFluentWait(priceButton);
		WaitForElementClickable(priceButton);
		Click(priceButton);
		
		WaitForLoad();
		GetFluentWait(basicCard);
		WaitForElementClickable(basicCard);
		Click(basicCard);
		
	}
	
	public boolean validateRedirection() {
		WaitForLoad();
		return checkIfExists(flightPageValidation);
	}
	
	public String checkFlightDate() throws InterruptedException {
		Thread.sleep(3000);
		return getText(selectedDay);
	}
		
}