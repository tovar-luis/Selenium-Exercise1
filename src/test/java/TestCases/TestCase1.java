package TestCases;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import com.aventstack.extentreports.Status;

import POM.AtCartPage;
import POM.AtTVMenuPage;
import selenium.ClsBrowser;
import selenium.ClsReport;
import selenium.ClsWebElements;

public class TestCase1
{
	private ClsWebElements webElements;
	
	
	@Before
	public void setup() 
	{
		ClsReport.fnSetupReport();
		ClsBrowser.BrowserName = "Chrome";
		webElements = new ClsWebElements();
		webElements.OpenBrowser();
	}
	

	@Test
	public void addTvTest()
	{
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("Add TV Test");
			webElements.NavigateToUrl("http://live.guru99.com/index.php/tv.html");
			webElements.WaitForLoad();
			ClsReport.fnLog(Status.PASS, "PAGE LOADED SUCCESFULLY", true);
			
			ClsReport.fnLog(Status.INFO, "INFO - Adding LG LCD TV to the cart", false);
			AtTVMenuPage objTVPage = new AtTVMenuPage();
			objTVPage.addLGTVToCart();
			
			ClsReport.fnLog(Status.INFO, "INFO - Checking if the TV was added to the cart", false);
			AtCartPage objCartPage = new AtCartPage();
			Assert.assertEquals("Shopping Cart", objCartPage.checkTitle());
			Assert.assertTrue(objCartPage.checkIfAdded());
			
			ClsReport.fnLog(Status.PASS, "TEST PERFORMED SUCCESFULLY", true);
		}
		catch(Exception e) 
		{
			ClsReport.fnLog(Status.FAIL, "not executed successfully. \n Exception: " + e.getMessage() , false);
		}
	}


	@After
	public void tearDown() 
	{
		webElements.CloseBrowser();
		ClsReport.fnCloseReport();
	}

}
