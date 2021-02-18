package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import utilities.excelUtilities;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import appPages.newToursDemoSite_Home_Page;

public class baseRunner 
{
	public static String BrowserType_Value;
	public static WebDriver DriverOBJ;
	static Properties pfOBJ = new Properties();
	static String ProjectPath = System.getProperty("user.dir");
	
	public static String BT_Name = null;
	public static String TestEnv_Value = null;
	public static String TestEnv1 = "TEST";
	public static String Selected_BT;
	
	public static String ProjectRelativePath_Value;
	
	public static excelUtilities excelOBJ;
	public static Logger loggerOBJ;
	
	public static ExtentTest SignValidationReportOBJ;
	public static ExtentTest SignInValidationReportOBJ;
	
	
	public static ExtentSparkReporter ReporterOBJ;
	public static ExtentReports ReportOBJ;
	
	//Global Functions Object Creation
		static baseRunner brOBJ = new baseRunner();
	
	@Parameters({"BTN","TE_Value"})
	@BeforeSuite
	public static void setup_Fun(String BTN, String TE_Value)
	{
		
		// start reporters
        ReporterOBJ = new ExtentSparkReporter("TestExecutionReport.html");
        // create ExtentReports and attach reporter(s)
        ReportOBJ = new ExtentReports();
        ReportOBJ.attachReporter(ReporterOBJ);
        
	    //Creating Reporter Object to report statuses in this class.
			//SignValidationReportOBJ = ReportOBJ.createTest("NewToursSmokeTest", "New Tours Smoke Test Sample Report");
		//Creating Reporter Object to report statuses in this class.
        		
		//Excel Setup Starts
			ProjectRelativePath_Value = System.getProperty("user.dir");
			excelOBJ = new excelUtilities(ProjectRelativePath_Value+"\\src\\test\\resources\\testdata_Files\\newToursDemoSite_STS_DF.xlsx", "Sheet1");
		//Excel Setup Ends
        
		//Log4j Setup Starts
			loggerOBJ = LogManager.getLogger(log4jReference.class);
		//Log4j Setup Ends
			
			
		//Calling This function to Get BrowserType Value from Properties File
			
			System.out.println("Browser Name From Properties File Is: "+baseRunner.getProperties("BrowserType"));
			System.out.println("Test Environment Value From Properties File Is: "+baseRunner.getProperties("TestEnv"));
			
			System.out.println();
			
			System.out.println("Browser Name From testng.xml File Is: "+BTN);
			System.out.println("Test Environment Value From testng.xml File Is: "+TE_Value);
									
			baseRunner.setProperties("BrowserType", BTN);
			baseRunner.setProperties("TestEnv", TE_Value);
			
			System.out.println();
			System.out.println("Browser Name From Properties File After Set Is: "+baseRunner.getProperties("BrowserType"));
			System.out.println("Test Environment Value From Properties File After Set Is: "+baseRunner.getProperties("TestEnv"));
			
			// This section of Code is for verifying Config Property value and assigning value from testNG.xml and if it is empty then get it from user Starts
				String BT_Name = baseRunner.getProperties("BrowserType");
				String TestEnv_Value = baseRunner.getProperties("TestEnv");
				if (BT_Name.isEmpty()) 
					{
						//Calling and Assigning Returned Value from BrowserTypeRequest
							Selected_BT = brOBJ.BrowserTypeRequest_Fun();
					}
				else
					{
						System.out.println("Browser Name From Properties File After Setting Before Passing to Selected_Bt parameter Is: "+BT_Name);
						Selected_BT = BT_Name;
					}
			// This section of Code is for verifying Config Property value and assigning value from testNG.xml and if it is empty then get it from user Ends
			
		//Calling Browser Launch Function and Assigning Browser Type launched to DriverOBJ to use in the entire project
			DriverOBJ = brOBJ.LaunchBrowser_Fun(Selected_BT);
		}
	
	@BeforeTest
	public void Open_And_LaunchApp()
	{
		//Launch Application Starts
			brOBJ.LaunchApplication_Fun();
			String URL_Value = DriverOBJ.getCurrentUrl();
			System.out.println("Browser Launched: "+URL_Value);
		//Launch Application Ends
	}
	
//	public void Open_And_LaunchApp_login()
//	{
//		//Launch Application Starts
//			brOBJ.LaunchApplication_Fun();
//			String URL_Value = DriverOBJ.getCurrentUrl();
//			System.out.println("Browser Launched: "+URL_Value);
//		//Launch Application Ends
//	}
	
	public String BrowserTypeRequest_Fun()
	{
		String[] BT_Choices = {"IE", "Chrome", "Firefox", "Edge"}; //BrowserType_Choices
		String Selected_BT = (String) JOptionPane.showInputDialog(null, "Choose BrowserType", 
		"Select Browser Type Against YOu Want To Open Application", 
		JOptionPane.QUESTION_MESSAGE, null, 
		BT_Choices, BT_Choices[0]);
		System.out.println("Selected Browser Type Is: "+Selected_BT);
		return Selected_BT;
	}
	
	@AfterTest
	public void TearDown()
	{
		ReportOBJ.flush();
	}
		
	public WebDriver LaunchBrowser_Fun(String Selected_BT)
	{
		
		if (Selected_BT.equals("IE"))
    	{
			System.setProperty("webdriver.ie.driver", "C:\\Selenium_WebDriver\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
			DriverOBJ = new InternetExplorerDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
    
		if (Selected_BT.equals("Chrome"))
    	{
			System.setProperty("webdriver.chrome.driver", ProjectRelativePath_Value+"\\src\\test\\resources\\browsersDrivers\\chromedriver.exe");
			DriverOBJ = new ChromeDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
		
		if (Selected_BT.equals("Firefox"))
    	{
			System.setProperty("webdriver.gecko.driver", ProjectRelativePath_Value+"\\src\\test\\resources\\browsersDrivers\\geckodriver.exe");
			DriverOBJ = new FirefoxDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
		
		if (Selected_BT.equals("Edge"))
    	{
			System.setProperty("webdriver.edge.driver", ProjectRelativePath_Value+"\\src\\test\\resources\\browsersDrivers\\msedgedriver.exe");
			DriverOBJ = new EdgeDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
		
		// start reporters
				
		return DriverOBJ;
	}
	
	public void LaunchApplication_Fun()
	{
		DriverOBJ.navigate().to("http://demo.guru99.com/test/newtours/");
	}
	
	public void CloseBrowserAndApplication()
	{
		DriverOBJ.quit();
	}
	
	public static String getProperties(String ParameterName)
	{
		String Parameter_Value = null;
		try 
			{
				InputStream inputOBJ = new FileInputStream(ProjectPath+"\\src\\test\\java\\configPackage\\config.properties");
				try 
				{
					pfOBJ.load(inputOBJ);
					Parameter_Value = pfOBJ.getProperty(ParameterName);
					//System.out.println(Parameter_Value);
					
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		
		return Parameter_Value;
	}
	
	public static void setProperties(String PN, String PV)
	{
		try 
			{
				
				OutputStream outputOBJ = new  FileOutputStream(ProjectPath+"\\src\\test\\java\\configPackage\\config.properties");
				pfOBJ.setProperty(PN, PV);
				//pfOBJ.setProperty("BrowserType", "Firefox");
				//pfOBJ.setProperty("Status", "Passed");
				try 
					{
						pfOBJ.store(outputOBJ, "This is Writing BrowserType Value In The Properties File");
					} 
				catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} 
		catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void Login_Fun(String UN_V, String PWD_V)
	{
		try
			{
				//Creating Reporter Object to report statuses in this class.
					SignInValidationReportOBJ = ReportOBJ.createTest("SignInValidation", "SigIn Functional Validation In***"+TestEnv1+"***Environment");
				//Creating Reporter Object to report statuses in this class.
			
				//System.out.println("Is UserName Text Box Displayed - "+ DriverOBJ.findElement(newToursDemoSite_Home_Page.userNameTextBox).isDisplayed());
				if (DriverOBJ.findElement(newToursDemoSite_Home_Page.userNameTextBox).isDisplayed())
					{
						//SignInPageReportOBJ.log(Status.PASS, "With Log option Expected UserName TextBox Displayed");
					SignInValidationReportOBJ.pass("Expected UserName TextBox Displayed");
						DriverOBJ.findElement(newToursDemoSite_Home_Page.userNameTextBox).clear();
						DriverOBJ.findElement(newToursDemoSite_Home_Page.userNameTextBox).sendKeys(UN_V);
										
						if (DriverOBJ.findElement(newToursDemoSite_Home_Page.passwordTextBox).isDisplayed())
							{
							SignInValidationReportOBJ.pass("Expected Password TextBox Displayed");
				
								DriverOBJ.findElement(newToursDemoSite_Home_Page.passwordTextBox).clear();
								DriverOBJ.findElement(newToursDemoSite_Home_Page.passwordTextBox).sendKeys(PWD_V);
									
								if (DriverOBJ.findElement(newToursDemoSite_Home_Page.submitButton).isDisplayed())
									{
									SignInValidationReportOBJ.pass("Expected Submit Button Displayed");
										DriverOBJ.findElement(newToursDemoSite_Home_Page.submitButton).click();
										String SignInSuccessMessage_EV = "Login Successfully";
										String SignInSuccessMessage_AV = DriverOBJ.findElement(newToursDemoSite_Home_Page.SigInSuccessMessage).getText();
										if ("Login Successfully".equals(SignInSuccessMessage_AV))
											{
											SignInValidationReportOBJ.pass("Expected Login Success Message:"+SignInSuccessMessage_EV+" And Actual Login Success Message: "+SignInSuccessMessage_AV+" Are Matched With User Name: "+UN_V+", Password: "+PWD_V+" : Values");
											}
										else
											{
											SignInValidationReportOBJ.fail("Expected Login Success Message:"+SignInSuccessMessage_EV+" And Actual Login Success Message: "+SignInSuccessMessage_AV+" Are Not Matched With User Name: "+UN_V+", Password: "+PWD_V+" Values");
											}
									}
								else
									{
									SignInValidationReportOBJ.fail("Expected Submit Button Not Displayed");
									}
							}
						else
							{
							SignInValidationReportOBJ.fail("Expected Password TextBox Not Displayed");
							}
					}
				else
					{
					SignInValidationReportOBJ.fail("Expected User Name TextBox Not Displayed");
					}
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	
	
}
