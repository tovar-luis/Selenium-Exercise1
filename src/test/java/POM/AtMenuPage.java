package POM;

import org.openqa.selenium.By;

import selenium.ClsWebElements;

public class AtMenuPage extends ClsWebElements{
	
	//Locators
	private By promosBtn = By.xpath("//a[@href='/deals?ref_=nav_cs_gb']");
	 
	//Methods

	public void goToPromotions() 
	{
		WaitForLoad();
		Click(promosBtn);
	}
	
}