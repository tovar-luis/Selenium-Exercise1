package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsWebElements;

public class AtHomePage extends ClsWebElements {

	//Locators
	private By originSelector = By.xpath("//div[@class='col btnSearch SearchOrigin']//a");
	private By originInput = By.id("fnameOrigin");
	private By destinationInput = By.id("fnameDestination");
	private By originAirportBtn = By.xpath("//div[text()='Guadalajara']");
	private By destinationAirportBtn = By.xpath("//div[text()='Cancún']");
	private By departMonthSelect = By.xpath("(//*[@class='monthselect'])[1]");
	private By departDaySelect = By.xpath("//*[contains(@class, 'calendar left')]//td[contains(@class, '20221029')]");
	private By returnDaySelect = By.xpath("//*[contains(@class, 'calendar right')]//td[contains(@class, '20221104')]");
	private By doneBtn = By.xpath("//button[contains(@class, 'btn-calendar d-none')]");
	private By searchFlightsBtn = By.xpath("//span[text()=' Buscar vuelos ']");
	private By monthOption = By.xpath("(//*[@class='monthselect'])[1]//option[@value='9']");
	
	//Methods
	public void selectFlightDestination() throws InterruptedException {
		Thread.sleep(1000);
		WaitForLoad();
		GetFluentWait(originSelector);
		WaitForElementClickable(originSelector);
		Click(originSelector);
		
		Thread.sleep(1000);
		WaitForLoad();
		GetFluentWait(originInput);
		WaitForElement(originInput);
		SendKeys(originInput, "Guadalajara");
		
		Thread.sleep(1000);
		WaitForLoad();
		GetFluentWait(originAirportBtn);
		WaitForElementClickable(originAirportBtn);
		Click(originAirportBtn);
		
		Thread.sleep(1000);
		WaitForLoad();
		GetFluentWait(destinationInput);
		WaitForElement(destinationInput);
		SendKeys(destinationInput, "Cancún");
		
		WaitForLoad();
		GetFluentWait(destinationAirportBtn);
		WaitForElementClickable(destinationAirportBtn);
		Click(destinationAirportBtn);
		
	}
	
	public void selectFlightDates(){
		WaitForLoad();
		GetFluentWait(departMonthSelect);
		WaitForElement(departMonthSelect);
		SelectItem(departMonthSelect, monthOption);
		
		WaitForLoad();
		GetFluentWait(departDaySelect);
		WaitForElementClickable(departDaySelect);
		Click(departDaySelect);
		
		WaitForLoad();
		GetFluentWait(returnDaySelect);
		WaitForElementClickable(returnDaySelect);
		Click(returnDaySelect);
		
		WaitForLoad();
		GetFluentWait(doneBtn);
		WaitForElementClickable(doneBtn);
		Click(doneBtn);
		
		WaitForLoad();
		GetFluentWait(searchFlightsBtn);
		WaitForElementClickable(searchFlightsBtn);
		Click(searchFlightsBtn);
	}

			
}