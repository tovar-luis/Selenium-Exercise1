package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

//Imported @Rule tag

import org.junit.Rule;

//Imported @Test tag

import org.junit.Test;
import org.junit.rules.TestName;

import com.aventstack.extentreports.Status;
import POM.AtLoginPage;
import selenium.ClsBrowser;
import selenium.ClsReport;

public class TestCase_Exec extends ClsBrowser
{
	@Rule public TestName TC_Name = new TestName();
	public String URL;
	
	@BeforeClass
	public static void beforeClass() 
	{
		ClsReport.fnSetupReport();
	}
	
	@Before
	public void setup() 
	{
		ClsBrowser.BrowserName = "Chrome";
		OpenBrowser();
	} 
	
	//Added missing @Test tag
	@Test
	public void FirstTC()
	{
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("First Test");
			URL = "https://positionsapp-uat.azurewebsites.net/#";
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "First Log Comment.", false);
			AtLoginPage objLogin = new AtLoginPage();
			objLogin.enterCredential();
			objLogin.startSession();
			objLogin.keepSessionDialog();
			objLogin.verifyActiveSession();
			ClsReport.fnLog(Status.PASS, "Second Log Comment.", true);
		}
		catch(Exception e) 
		{
			ClsReport.fnLog(Status.FAIL, "The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage() , false);
		}
	}
		
	//Added missing @Test tag
	@Test
	public void SecondTC()
	{
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("Second Test");
			//Corrected the URL (deleted the random characters at the beginning)
			URL = "https://positionsapp-uat.azurewebsites.net/#";
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "First Log Comment.", false);
			AtLoginPage objLogin = new AtLoginPage();
			objLogin.enterCredential();
			objLogin.startSession();
			objLogin.keepSessionDialog();
			objLogin.verifyActiveSession();
			ClsReport.fnLog(Status.PASS, "Second Log Comment.", true);
		}
		catch (Exception e) 
		{
			ClsReport.fnLog(Status.FAIL, "The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage() , false);
		}
	}
	
	//Uncommented the @Test tag
	@Test 
	public void NewTest()
	{
		try 
		{
			//Changed the "Second Test" to "Third Test"
			ClsReport.objTest = ClsReport.objExtent.createTest("Third Test");
			//Corrected the URL (deleted the random characters at the beginning)
			URL = "https://positionsapp-uat.azurewebsites.net/#";
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "First Log Comment.", false);
			AtLoginPage objLogin = new AtLoginPage();
			objLogin.enterCredential();
			objLogin.startSession();
			objLogin.keepSessionDialog();
			objLogin.verifyActiveSession();
			ClsReport.fnLog(Status.PASS, "Second Log Comment.", true);
		}
		catch (Exception e) 
		{
			ClsReport.fnLog(Status.FAIL, "The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage() , false);
		}
	}
	
	//Deleted repeated tearDown method

	@After
	public void tearDown() 
	{
		CloseBrowser();
		ClsReport.fnCloseReport();
	}

}
