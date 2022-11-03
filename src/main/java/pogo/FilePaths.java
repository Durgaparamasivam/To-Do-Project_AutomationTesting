package pogo;

import javax.print.event.PrintEvent;

public class FilePaths {
	
	private static String extentReportFilePath = System.getProperty("user.home")+"\\ToDoProject\\ExtentReport\\Report.html";
	private static String log4jPropertiesPath = System.getProperty("user.home")+"\\ToDoProject\\src/test\\resources\\Log4j.properties";
	private static String chromeDriverPath = System.getProperty("user.home")+"\\ToDoProject\\src\\test\\resources\\Drivers\\chromedriver.exe";
	
	public static String getChromeDriverFilePath() {
		return chromeDriverPath;
	}
	
	public static String getExtentReportFilePath() {
		return extentReportFilePath;
	}
	
	
	public static String getLog4jPropertiesPath() {
		return log4jPropertiesPath;
	}
}