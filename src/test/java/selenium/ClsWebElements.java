package selenium;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class ClsWebElements extends ClsBrowser
{
	private int DefaultTimeout = 10;
	public static Wait<WebDriver> objFluentWait;
    public static WebDriverWait objExplicitWait;
    public static String strAction = "";
	

    public WebElement getWebElement(By by)
    {
        try
        {
        	//Changed Status.INFORMATION to Status.INFO
        	ClsReport.fnLog(Status.INFO, "Step - Get Web Element: " + by.toString(), false);
            WebElement pobjElement = ClsBrowser.objDriver.findElement(by);
            ClsReport.fnLog(Status.PASS, "Step - The Web Element: " + by.toString() + " was found as expected.", true);
            return pobjElement;
        }
        catch (NoSuchElementException pobjException)
        {
        	ClsReport.fnLog(Status.FAIL, "Step - The Web Element: " + by.toString() + " was not found as expected. Exception: " + pobjException.getStackTrace(), true);
        	return null;
        }
    }
    
    
    public WebElement getGetWebElement(String pstrLocator)
    {
    	return getWebElement(By.xpath(pstrLocator));
    }
	
	//Added log steps for standardization
    public List<WebElement> getWebList(By by)
    {
        try
        {
        	ClsReport.fnLog(Status.INFO, "Step - Get Web List: " + by.toString(), false);
            List<WebElement> pobjElement = ClsBrowser.objDriver.findElements(by);
            ClsReport.fnLog(Status.PASS, "Step - The Web List: " + by.toString() + " was found as expected.", true);
            return pobjElement;
        }
        catch (Exception pobjException)
        {
        	ClsReport.fnLog(Status.FAIL, "Step - The Web Element: " + by.toString() + " was not found as expected. Exception: " + pobjException.getStackTrace(), true);
            return null;
        }
    }
    
    
    public List<WebElement> getWebList(String pstrLocator)
    {
        return getWebList(By.xpath(pstrLocator));
    }
    
    
    public Object GetFluentWait(final String pstrLocator) 
    {
    	return GetFluentWait(By.xpath(pstrLocator));
    }
    
    
    public Object GetFluentWait(final By by) 
    {
    	try 
    	{
    		// Waiting 30 seconds for an element to be present on the page, checking
    	 	   // for its presence once every 5 seconds.
    	    	objFluentWait = new FluentWait<WebDriver>(ClsBrowser.objDriver)
    	 	       .withTimeout(Duration.ofSeconds(30L))
    	 	       .pollingEvery(Duration.ofSeconds(3L))
    	 	       .ignoring(NoSuchElementException.class);
    	 	       
    	    	//Get Web Element and perform action
    	    	WebElement objElement = objFluentWait.until(new Function<WebDriver, WebElement>() {
    	   	     public WebElement apply(WebDriver driver) {
    	   	       return driver.findElement(by);
    	   	     }
    	   	   });
    	    	
    	    	return objElement;
    	}
    	catch(Exception e) 
    	{
    		System.out.println("The element was ("+ by.toString() +") not located in the page");
            return null;
    	}
    }
    
    
    
    
	//Added log steps for standardization
    public boolean Click(final By by) 
	{
    	try 
    	{
    		ClsReport.fnLog(Status.INFO, "Step - Click on element: " + by.toString(), false);
    		WebElement objElement = (WebElement) GetFluentWait(by);
    		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, DefaultTimeout);
    		objExplicitWait.until(ExpectedConditions.elementToBeClickable(by));
    		objElement.click();
    		ClsReport.fnLog(Status.PASS, "Step - The Element " + by.toString() + " was clicked succesfully.", true);
    		return true;
    	}
    	catch(Exception e) 
    	{
    		ClsReport.fnLog(Status.FAIL, "Step - The Element: " + by.toString() + " was not clicked succesfuly. Exception: " + e.getStackTrace(), true);
    		return false;
    	}
	}
    
    
    public boolean Click(final String pstrLocator) 
	{
		return Click(By.xpath(pstrLocator));
	}
    
    
	//Added log steps for standardization
    public boolean SendKeys(final By by, String pValue) 
	{
    	try 
    	{
    		ClsReport.fnLog(Status.INFO, "Step - Send keys: " + pValue + " on element: " + by.toString(), false);
    		WebElement objElement = (WebElement) GetFluentWait(by);
    		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, DefaultTimeout);
    		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    		objElement.clear();
    		objElement.sendKeys(pValue);
    		ClsReport.fnLog(Status.PASS, "Step - Send keys: " + pValue + " on element: " + by.toString() + " were sent succesfully", true);
    		return true;
    	}
    	catch(Exception e) 
    	{
    		ClsReport.fnLog(Status.FAIL, "Step - Send keys: " + pValue + " on element: " + by.toString() + " were not sent succesfully. Exception: " + e.getStackTrace(), true);
    		return false;
    	}
		//Deleted "return null" statement, since the function can't return a null type
    	//and also this is and unreachable statement
	}
    
    public boolean SendKeys(final String pstrLocator, String pValue) 
    {
    	//Corrected "xpaths" to "xpath"
    	return SendKeys(By.xpath(pstrLocator), pValue);
    }
    
    
	//Added log steps for standardization    
    public boolean SelectItem(final By by, String pMethod, String pValue) 
    {
    	try 
    	{
    		ClsReport.fnLog(Status.INFO, "Step - Select Item: " + pValue + " by method: " + pMethod + " from: " + by.toString(), false);
    		WebElement objElement = (WebElement) GetFluentWait(by);
        	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        	Select selectObject = new Select(objElement);
        	
        	switch (pMethod.toUpperCase()) {
			case "BYVALUE":
				selectObject.selectByValue(pValue);
				break;
			case "BYINDEX":
				selectObject.selectByIndex(Integer.parseInt(pValue));
				break;
			case "BYTEXT":
				selectObject.selectByVisibleText(pValue);
				break;
			}
    		ClsReport.fnLog(Status.PASS, "Step - Select Item: " + pValue + " by method: " + pMethod + " from: " + by.toString() + " was done succesfully", true);
    		return true;
    	}
    	catch(Exception e) 
    	{
    		ClsReport.fnLog(Status.FAIL, "Step - Select Item: " + pValue + " by method: " + pMethod + " from: " + by.toString() + " was not done succesfully. Exception: " + e.getStackTrace(), true);
    		return false;
    	}    	
    }
    
    public boolean SelectItem(final String pstrLocator, String pMethod, String pValue) 
    {
    	return SelectItem(By.xpath(pstrLocator), pMethod, pValue);
    }
    
    
    
    public void WaitForElement(final String pstrLocator) 
    {
    	WaitForElement(By.xpath(pstrLocator));
//    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
//    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    }
    
    public void WaitForElement(final By by) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    
    
    
    public void WaitForElementClickable(final By by) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
    	objExplicitWait.until(ExpectedConditions.elementToBeClickable(by));
    	
    }
    
    public void WaitForElementClickable(final String pstrLocator) 
    {
    	WaitForElementClickable(By.xpath(pstrLocator));
    }
    
    
    public void WaitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 30);
        wait.until(pageLoadCondition);
    }
    
    
    public void LinkText(final String pstrLocator) 
    {
    	WebElement objElement = ClsBrowser.objDriver.findElement(By.linkText(pstrLocator));
    	objElement.click();
		
    }
    
    
    public void AcceptAlert() 
    {
    	WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 3000);
    	wait.until(ExpectedConditions.alertIsPresent());
    	Alert alert = ClsBrowser.objDriver.switchTo().alert();
    	alert.accept();
    }
    
    
    public String GetAlertText() 
    {
    	WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 3000);
    	wait.until(ExpectedConditions.alertIsPresent());
    	Alert alert = ClsBrowser.objDriver.switchTo().alert();
    	String alertMessage = ClsBrowser.objDriver.switchTo().alert().getText();
    	return alertMessage;
    }
    
    public boolean checkIfExists(By locator) {
    	try {
    		WebDriverWait wait = new WebDriverWait(objDriver, 15);
    		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    	
    }
    
    public String getTitle() {
    	return objDriver.getTitle();
    }
    
    
}
