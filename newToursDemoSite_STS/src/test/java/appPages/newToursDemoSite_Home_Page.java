package appPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class newToursDemoSite_Home_Page 
{
	//Declare WebDriver at the Class level so that you can use it in all the methods in this class
	public WebDriver DriverOBJ;
		
	//Page Objects Identification Starts
		public static By userNameTextBox = By.xpath("//*[@name='userName']");
		
		public static By passwordTextBox = By.xpath("//*[@name='password']");
			
		public static By submitButton=By.xpath("//*[@name='submit']");
		
		public static By DestinationLink = By.xpath("/html/body/div[3]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/font/a");
		
		
		public static By SigInSuccessMessage = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3");
	//Page Objects Identification Ends

	//This is a Constructor is special method which name is same as class name and it mainly used for initialization purpose Starts
		public newToursDemoSite_Home_Page(WebDriver DriverOBJ)
		{
			this.DriverOBJ = DriverOBJ;
					 
		}	
	//This is a Constructor is special method which name is same as class name and it mainly used for initialization purpose Ends
	
}
