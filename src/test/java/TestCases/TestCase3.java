package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.aventstack.extentreports.Status;

import POM.AtCartPage;
import POM.AtRegisterPage;
import POM.AtTVMenuPage;
import selenium.ClsBrowser;
import selenium.ClsReport;
import selenium.ClsWebElements;

import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class TestCase3
{
	private String userName;
	private String accNum;
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
	
	public TestCase3(String userName, String accNum) {
		this.userName = userName;
		this.accNum = accNum;
	}
	
	public static String[][] getExcelData(){
		String[][] dataTable = null;
		File file = new File ("./src/test/resources/Driver/Guru99Accounts.xlsx");
		
		try {
			FileInputStream xlfile = new FileInputStream(file);
			XSSFWorkbook xlwb = new XSSFWorkbook(xlfile);
			XSSFSheet xlSheet = xlwb.getSheetAt(0);
			
			int numRows = xlSheet.getLastRowNum() +1;
			int numCols = xlSheet.getRow(0).getLastCellNum();
			
			dataTable = new String[numRows][numCols];
			
			
			XSSFRow xlRow = xlSheet.getRow(0);
			XSSFCell xlCell = xlRow.getCell(0);
			dataTable[0][0]= xlCell.toString();
			
			XSSFRow xlRow2 = xlSheet.getRow(0);
			XSSFCell xlCell2 = xlRow2.getCell(1);
			dataTable[0][1]= Character.toString(xlCell2.toString().charAt(0));
			
			int tempNum = Integer.parseInt(dataTable[0][1]);
			tempNum++;
			xlCell2.setCellValue(tempNum);
			
			try(FileOutputStream outputStream = new FileOutputStream("./src/test/resources/Driver/Guru99Accounts.xlsx")){
				xlwb.write(outputStream);
			}
			
			
			
		}catch(Exception e) {
			System.out.println("Error File Handling" + e.toString());
		}
		return dataTable;
	}
	
	@Test
	public void createAccountTest()
	{
		try 
		{
			String email = userName + accNum + "@gmail.com";
			System.out.println(email);
			
			ClsReport.objTest = ClsReport.objExtent.createTest("Create Account Test");
			webElements.NavigateToUrl("http://live.guru99.com/index.php/tv.html");
			webElements.WaitForLoad();
			ClsReport.fnLog(Status.PASS, "PAGE LOADED SUCCESFULLY", true);
			
			ClsReport.fnLog(Status.INFO, "INFO - Navigating to the register page", false);
			AtTVMenuPage objTVPage = new AtTVMenuPage();
			objTVPage.goToRegisterPage();
			
			ClsReport.fnLog(Status.INFO, "INFO - Filling the account information", false);
			AtRegisterPage objRegisterPage = new AtRegisterPage(email);
			objRegisterPage.fillAccountInfo();
			
			ClsReport.fnLog(Status.INFO, "INFO - Clicking on register button", false);
			objRegisterPage.clickRegisterButton();
			
			ClsReport.fnLog(Status.INFO, "INFO - Checking if registered correctly", false);
			Assert.assertTrue(objRegisterPage.checkIfRegistered());
			
			
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
