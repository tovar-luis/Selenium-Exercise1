package TestCases;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import com.aventstack.extentreports.Status;

import POM.AtConfirmationPage;
import POM.AtFlightPage;
import POM.AtHomePage;
import selenium.ClsBrowser;
import selenium.ClsReport;
import selenium.ClsWebElements;

public class FlightTest
{
	private ClsWebElements webElements;
	private int budget = 1500;
	
	@Before
	public void setup() 
	{
		ClsReport.fnSetupReport();
		ClsBrowser.BrowserName = "Chrome";
		webElements = new ClsWebElements();
		webElements.OpenBrowser();
	}
	

	@Test
	public void scheduleFlightTest()
	{
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("Flight Test");
			webElements.NavigateToUrl("https://www.volaris.com/");
			webElements.WaitForLoad();
			ClsReport.fnLog(Status.PASS, "PAGE LOADED SUCCESFULLY", true);
			
			AtHomePage objHomePage = new AtHomePage();
			ClsReport.fnLog(Status.INFO, "INFO - Selecting flight destination", false);
			objHomePage.selectFlightDestination();
			ClsReport.fnLog(Status.INFO, "INFO - Selecting flight dates", false);
			objHomePage.selectFlightDates();
			
			ClsReport.fnLog(Status.INFO, "INFO - Validating redirection", false);
			AtFlightPage objFlightPage = new AtFlightPage();
			Assert.assertTrue(objFlightPage.validateRedirection());
			
			ClsReport.fnLog(Status.INFO, "INFO - Validating depart flight date", false);
			Assert.assertEquals("Sábado\n29 oct, 2022", objFlightPage.checkFlightDate());
			ClsReport.fnLog(Status.INFO, "INFO - Filtering flights by price", false);
			objFlightPage.filterFlightsByPrice();
			ClsReport.fnLog(Status.INFO, "INFO - Selecting cheapest option", false);
			objFlightPage.selectCheapestOption();
			ClsReport.fnLog(Status.INFO, "INFO - Validating return flight date", false);
			Assert.assertEquals("Viernes\n4 nov, 2022",objFlightPage.checkFlightDate()); 
			ClsReport.fnLog(Status.INFO, "INFO - Selecting cheapest option", false);
			objFlightPage.selectCheapestOption();
			
			AtConfirmationPage objConfirmationPage = new AtConfirmationPage(budget);
			ClsReport.fnLog(Status.INFO, "INFO - Closing extra charges window", false);
			objConfirmationPage.closeExtraCosts();
			ClsReport.fnLog(Status.INFO, "INFO - Adding airport tax", false);
			objConfirmationPage.addAirportTax();
			ClsReport.fnLog(Status.INFO, "INFO - Getting total cost", false);
			objConfirmationPage.checkTotalCost();
			
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