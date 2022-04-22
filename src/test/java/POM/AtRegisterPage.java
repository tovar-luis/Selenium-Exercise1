package POM;

import org.openqa.selenium.By;

import selenium.ClsWebElements;

public class AtRegisterPage extends ClsWebElements{
	
	String email;

	//Locators
	private By firstNameInput = By.id("firstname");
	private By lastNameInput = By.id("lastname");
	private By emailInput = By.id("email_address");
	private By passwordInput = By.id("password");
	private By confirmationInput = By.id("confirmation");
	
	private By registerBtn = By.xpath("//button[@title='Register']");
	private By registeredText = By.xpath("//li[@class='success-msg']");
	
	 
	//Methods
	public AtRegisterPage(String email) {
		this.email = email;
	}
	
	public String checkTitle() {
		return getTitle();
	}
	
	
	public void fillAccountInfo() {
		WaitForLoad();
		WaitForElement(firstNameInput);
		SendKeys(firstNameInput, "Luis");
		
		WaitForLoad();
		WaitForElement(lastNameInput);
		SendKeys(lastNameInput, "Tovar");
		
		WaitForLoad();
		WaitForElement(emailInput);
		SendKeys(emailInput, email);
		
		WaitForLoad();
		WaitForElement(passwordInput);
		SendKeys(passwordInput, "P@ssw0rd");
		
		WaitForLoad();
		WaitForElement(confirmationInput);
		SendKeys(confirmationInput, "P@ssw0rd");
		
		
	}
	
	public void clickRegisterButton() {
		WaitForLoad();
		WaitForElementClickable(registerBtn);
		Click(registerBtn);
	}
	
	public boolean checkIfRegistered() {
		WaitForLoad();
		return checkIfExists(registeredText);
	}		
}
