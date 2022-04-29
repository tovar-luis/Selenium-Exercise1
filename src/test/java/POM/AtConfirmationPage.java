package POM;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import selenium.ClsReport;
import selenium.ClsWebElements;

public class AtConfirmationPage extends ClsWebElements{

	private int budget;
	
	//Locators
	private By closeDialog = By.className("closeDialog");
	private By totalCostDiv = By.xpath("//div[contains(@class, 'item GrandTotal')]//div[contains(text(), '$')]");
	private By airportTaxCheckbox = By.xpath("//mat-checkbox[contains(@class, 'checkforprice')]");
	
	public AtConfirmationPage(int budget) {
		this.budget = budget;
	}
	
	//Methods

	public void closeExtraCosts() {
		WaitForLoad();
		GetFluentWait(closeDialog);
		WaitForElementClickable(closeDialog);
		Click(closeDialog);
	}
	
	public void addAirportTax() {
		WaitForLoad();
		GetFluentWait(airportTaxCheckbox);
		WaitForElementClickable(airportTaxCheckbox);
		Click(airportTaxCheckbox);
	}
	
	public void checkTotalCost() {
		WaitForLoad();
		WaitForElement(totalCostDiv);
		
		String costString = getText(totalCostDiv);
		costString = costString.replaceAll("\\D+","");
		int cost = Integer.parseInt(costString);
		System.out.println(cost);
		
		if(cost>budget) {
			ClsReport.fnLog(Status.FAIL, "FAIL - Cost of the flights is over the budget (Cost: " + cost + ", Budget: " + budget + ")", false);
		}else {
			ClsReport.fnLog(Status.PASS, "SUCCESS - Cost of the flights is below the budget (Cost: " + cost + ", Budget: " + budget + ")", false);

		}
	}		
}
