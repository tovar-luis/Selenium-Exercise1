package POM;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import selenium.ClsReport;
import selenium.ClsWebElements;

public class AtPromotionsPage extends ClsWebElements{

	//Locators
	private By lightningDealsBtn = By.xpath("//span[text()='Oferta relámpago']");
	private By nextPageBtn = By.className("a-last");
	private By numPagesLbl = By.xpath("//*[@class='a-disabled'][3]");
	private By dealText = By.xpath("//div[contains(@class, 'DealContent')]");
	
	private ArrayList<String> listDeals = new ArrayList<>();
	private int numPages;
	
	//Methods
	public void filterLightningDeals() 
	{
		WaitForLoad();
		Click(lightningDealsBtn);
	}
	
	public void getDealElements(){
		List<WebElement> tmpListDeals;
		WaitForLoad();
		numPages = Integer.parseInt(getWebElement(numPagesLbl).getText());
		for(int i=0; i<numPages-1;i++) {
			WaitForLoad();
			tmpListDeals = getWebList(dealText);
			WaitForLoad();
			for(int j=0; j<tmpListDeals.size(); j++) {
				listDeals.add(tmpListDeals.get(j).getText());
			}
			Click(nextPageBtn);
		}
		tmpListDeals = getWebList(dealText);
		for(int i=0;i<tmpListDeals.size();i++) {
			listDeals.add(tmpListDeals.get(i).getText());
		}
	}

	public void printDealElements() {
		int numElements = listDeals.size();
		System.out.println(numElements);
		for(int i=0; i<numElements; i++) {
			System.out.println(listDeals.get(i));
			ClsReport.fnLog(Status.INFO, i+1 + ".- " + listDeals.get(i), false);
		}
	}
}
