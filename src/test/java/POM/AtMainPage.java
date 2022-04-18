package POM;

import org.openqa.selenium.By;
import selenium.ClsBrowser;
import selenium.ClsWebElements;

public class AtMainPage extends ClsWebElements{
	
	//Locators
	private By loginBtn = By.xpath("//a[@id='nav-link-accountList']");
	
	//Methods
	
	public void clickOnLogin() {
		WaitForLoad();
		Click(loginBtn);
	}

	
}
	
	
	
	
	
	
