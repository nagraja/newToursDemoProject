package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.*;

public class log4jReference 
{
	public static Logger loggerOBJ = LogManager.getLogger(log4jReference.class);
	
	public static void main(String[] args) 
	{
		System.out.println("\n*****This is Log4j Test*****Start\n");
		
		loggerOBJ.info("log4j info Log Option Test");
		loggerOBJ.error("log4j error Log Option Test");
		loggerOBJ.warn("log4j warn Log Option Test");
		
		System.out.println("\n*****This is Log4j Test*****End");
	}
	
	
}
