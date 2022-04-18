package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.aventstack.extentreports.Status;

import POM.AtLoginPage;
import POM.AtMainPage;
import POM.AtMenuPage;
import POM.AtPromotionsPage;
import selenium.ClsBrowser;
import selenium.ClsReport;
import selenium.ClsWebElements;

import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class TestCase_Exec
{
	private String userName;
	private String password;
	private ClsWebElements webElements;
	
	
	@Before
	public void setup() 
	{
		ClsReport.fnSetupReport();
		ClsBrowser.BrowserName = "Chrome";
		webElements = new ClsWebElements();
		webElements.OpenBrowser();
	}
	
	@Parameters
	public static Collection<Object[]> loginData() {
		Object[][] arrayObject = getExcelData();
		return Arrays.asList(arrayObject);
	}
	
	public TestCase_Exec(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public static String[][] getExcelData(){
		String[][] dataTable = null;
		File file = new File ("./src/test/resources/Driver/DataDriver.xlsx");
		
		try {
			FileInputStream xlfile = new FileInputStream(file);
			XSSFWorkbook xlwb = new XSSFWorkbook(xlfile);
			XSSFSheet xlSheet = xlwb.getSheetAt(0);
			
			int numRows = xlSheet.getLastRowNum() +1;
			int numCols = xlSheet.getRow(0).getLastCellNum();
			
			dataTable = new String[numRows][numCols];
			
			for(int i=0; i<numRows; i++) {
				XSSFRow xlRow = xlSheet.getRow(i);
				for(int j=0; j<numCols; j++) {
					XSSFCell xlCell = xlRow.getCell(j);
					dataTable[i][j]= xlCell.toString();
				}
			}
			
		}catch(Exception e) {
			System.out.println("Error File Handling" + e.toString());
		}
		return dataTable;
	}
	
	@Test
	public void amazonTest()
	{
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("Amazon Test");
			webElements.NavigateToUrl("https://www.amazon.com.mx/");
			webElements.WaitForLoad();
			ClsReport.fnLog(Status.PASS, "PAGE LOADED SUCCESFULLY", true);
			
			AtMainPage objMainPage = new AtMainPage();
			objMainPage.clickOnLogin();
			Thread.sleep(2000);
			
			AtLoginPage objLoginPage = new AtLoginPage();
			objLoginPage.enterEmail(userName);
			Thread.sleep(2000);
			objLoginPage.enterPassword(password);
			
			AtMenuPage objMenuPage = new AtMenuPage();
			objMenuPage.goToPromotions();
			
			AtPromotionsPage objPromoPage = new AtPromotionsPage();
			objPromoPage.filterLightningDeals();
			Thread.sleep(2000);
			
			objPromoPage.getDealElements();
			objPromoPage.printDealElements();
			Thread.sleep(2000);
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
