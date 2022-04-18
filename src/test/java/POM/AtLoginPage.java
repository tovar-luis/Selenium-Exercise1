package POM;

import org.openqa.selenium.By;
import selenium.ClsWebElements;

public class AtLoginPage extends ClsWebElements{
	
	//Locators
	private By emailInput = By.name("email");
	private By passwordInput = By.id("ap_password");
	private By continueBtn = By.xpath("//input[@id='continue']");
	private By signInBtn = By.id("signInSubmit");
	
	 
	//Methods
	public void enterEmail(String email) 
	{
		WaitForLoad();
		SendKeys(emailInput, email);
		WaitForElementClickable(continueBtn);
		Click(continueBtn);
	}
	
	public void enterPassword(String password) 
	{
		WaitForLoad();
		SendKeys(passwordInput, password);
		WaitForElementClickable(signInBtn);
		Click(signInBtn);
	}
	
	
	
}
