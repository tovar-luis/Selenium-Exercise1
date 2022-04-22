package TestCases;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import com.aventstack.extentreports.Status;

import POM.AtCartPage;
import POM.AtCheckoutPage;
import POM.AtTVMenuPage;
import selenium.ClsBrowser;
import selenium.ClsReport;
import selenium.ClsWebElements;

public class TestCase2
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
	public void checkoutTest()
	{
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("Complete Samsung TV purchase Test");
			webElements.NavigateToUrl("http://live.guru99.com/index.php/tv.html");
			webElements.WaitForLoad();
			ClsReport.fnLog(Status.PASS, "PAGE LOADED SUCCESFULLY", true);
			
			ClsReport.fnLog(Status.INFO, "INFO - Adding Samsung LCD TV to the cart", false);
			AtTVMenuPage objTVPage = new AtTVMenuPage();
			objTVPage.addSamsungTVToCart();
			
			ClsReport.fnLog(Status.INFO, "INFO - Checking if it was added", false);
			AtCartPage objCartPage = new AtCartPage();
			Assert.assertEquals("Shopping Cart", objCartPage.checkTitle());
			Assert.assertTrue(objCartPage.checkIfAdded());
			
			ClsReport.fnLog(Status.INFO, "INFO - Going to Checkout page", false);
			objCartPage.goToCheckout();
			
			ClsReport.fnLog(Status.INFO, "INFO - Checking if redirected to Checkout page", false);
			AtCheckoutPage objCheckoutPage = new AtCheckoutPage();
			Assert.assertEquals("Checkout", objCheckoutPage.checkTitle());
			
			ClsReport.fnLog(Status.INFO, "INFO - Filling billing information", false);
			objCheckoutPage.selectCheckoutAsGuest();
			objCheckoutPage.clickContinueCheckout();
			
			objCheckoutPage.fillBillingInfo();
			objCheckoutPage.clickContinueBilling();
			
			objCheckoutPage.clickContinueShipping();
			
			objCheckoutPage.selectCheckMoneyOrder();
			objCheckoutPage.clickContinuePayment();
			
			ClsReport.fnLog(Status.INFO, "INFO - Finishing the order", false);
			objCheckoutPage.clickPlaceOrder();
			
			ClsReport.fnLog(Status.INFO, "INFO - Checking if the order was placed succesfully", false);
			Assert.assertTrue(objCheckoutPage.checkIfSuccess());
			
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
