package POM;

import org.openqa.selenium.By;

import selenium.ClsWebElements;

public class AtTVMenuPage extends ClsWebElements{

	//Locators
	private By lgTVBtn = By.xpath("(//button[@title='Add to Cart'])[1]");
	private By samsungTVBtn = By.xpath("(//button[@title='Add to Cart'])[2]");
	private By accountHeader = By.xpath("//a[@data-target-element='#header-account']");
	private By registerBtn = By.xpath("//a[@title='Register']");
		 
	//Methods
	public void addLGTVToCart() {
		WaitForLoad();
		WaitForElementClickable(lgTVBtn);
		Click(lgTVBtn);
	}
	
	public void addSamsungTVToCart() {
		WaitForLoad();
		WaitForElementClickable(samsungTVBtn);
		Click(samsungTVBtn);
	}
	
	public void goToRegisterPage() {
		WaitForLoad();
		WaitForElementClickable(accountHeader);
		Click(accountHeader);
		
		WaitForLoad();
		WaitForElementClickable(registerBtn);
		Click(registerBtn);
	}
			
}
