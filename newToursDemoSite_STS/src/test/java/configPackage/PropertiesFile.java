package configPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile 
{
	static Properties pfOBJ = new Properties();
	static String ProjectPath = System.getProperty("user.dir");
	
	public static void main(String[] args) 
	{
		String BT_N = getProperties();
		System.out.println(BT_N);
		//setProperties();
		
	}
	
	
	public static String getProperties()
	{
		String BrowserType_Value = null;
		try 
			{
				InputStream inputOBJ = new FileInputStream(ProjectPath+"/src/main/java/configPackage/config.properties");
				try 
				{
					pfOBJ.load(inputOBJ);
					BrowserType_Value = pfOBJ.getProperty("BrowserType");
					System.out.println(BrowserType_Value);
					
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
		return BrowserType_Value;
		
		
	}
	
	public static void setProperties(String PK, String PV)
	{
		try 
			{
				
				OutputStream outputOBJ = new  FileOutputStream(ProjectPath+"/src/main/java/configPackage/config.properties");
				pfOBJ.setProperty(PK, PV);
				//pfOBJ.setProperty("TestEnv", TE_Value);
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
	
}
