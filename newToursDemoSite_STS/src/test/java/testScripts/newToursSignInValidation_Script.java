package testScripts;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import appPages.newToursDemoSite_Home_Page;
import utilities.baseRunner;
import utilities.excelUtilities;

public class newToursSignInValidation_Script extends baseRunner
{
	//Creating Object for newToursDemoSite_Home_Page Starts
		static newToursDemoSite_Home_Page newToursDemoSite_Home_PageOBJ = new newToursDemoSite_Home_Page(DriverOBJ);
	//Creating Object for newToursDemoSite_Home_Page Ends

	@Test
	public static void SignIn()
	{
		//This section is to get the Row Count From DataFile Starts
			int RowCount_Value = excelOBJ.GetRowCount();
	    	System.out.println("Row Count: "+RowCount_Value);
		//This section is to get the Row Count From DataFile Ends
		
	    //This section is to get the Column Count From DataFile Starts
	    	int ColumnCount_Value = excelOBJ.GetColumnCount();
	    	System.out.println("Column Count: "+ColumnCount_Value);
		//This section is to get the Column Count From DataFile Ends
		
		//Hash Map Object Creation Starts to get the column name and corresponding values
	    	HashMap<String, String> columnnameOBJ = new HashMap<String, String>();
	    //Hash Map Object Creation Ends
	    	
	    for (int rli=1; rli<=(RowCount_Value-1); rli++)
		    {
		    	columnnameOBJ.clear();
		    	
		    	//This loop is to get read all Column names and their corresponding Values Starts
			    	for (int cni=0; cni<ColumnCount_Value; cni++)
				    {
				    	String Column_Name = excelUtilities.GetCellData(0, cni);
			    		String Column_Value = excelUtilities.GetCellData(rli, cni);
			    		System.out.println("Column Name Is: "+Column_Name+" And Corresponding Value Is: "+Column_Value);
			    		columnnameOBJ.put(Column_Name, Column_Value);
			    	}
		    	//This loop is to get read all Column names and their corresponding Values Ends
			    	
			    	
			    	if (columnnameOBJ.get("TC_ExecutionFlg_V").equals("yes"))
				    	{
			    			baseRunner.Login_Fun(columnnameOBJ.get("UserName_V"), columnnameOBJ.get("Password_V"));
			    			DriverOBJ.quit();
				    	}
			    	else
			    	{
			    		SignValidationReportOBJ.info("Test Case: "+columnnameOBJ.get("TC_Number")+"Is Skipped");
			    	}
			}
	}
}
