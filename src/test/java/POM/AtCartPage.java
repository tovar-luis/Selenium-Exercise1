package POM;

import org.openqa.selenium.By;

import selenium.ClsWebElements;

public class AtCartPage extends ClsWebElements{

	//Locators
	private By addedTV = By.xpath("//li[@class='success-msg']");
	private By checkoutBtn = By.xpath("//button[@title='Proceed to Checkout']");
		 
	//Methods
	public boolean checkIfAdded() {
		WaitForLoad();
		return checkIfExists(addedTV);
	}
	
	public void goToCheckout() {
		WaitForLoad();
		WaitForElementClickable(checkoutBtn);
		Click(checkoutBtn);
	}
	
	public String checkTitle() {
		return getTitle();
	}
			
}
