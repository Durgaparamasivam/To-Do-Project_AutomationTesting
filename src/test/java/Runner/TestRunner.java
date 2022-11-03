package Runner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Reports.Report;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import pogo.FilePaths;

@CucumberOptions(features = { "src/test/java/Features" }, 
glue = { "StepDefs" },
tags = {},
plugin = {"pretty"}
,monochrome = true,dryRun = false)

public class TestRunner extends AbstractTestNGCucumberTests {

	public static WebDriver driver;
	public static Report extendreport = null;

	public static Logger log;

	@BeforeSuite
	public void openBrowser() {

		extendreport = new Report();
		extendreport.extentReportFilepath(FilePaths.getExtentReportFilePath());
		extendreport.extentTestClass("Testt");

		PropertyConfigurator.configure(FilePaths.getLog4jPropertiesPath());

		log = Logger.getLogger(TestRunner.class);

		System.setProperty("webdriver.chrome.driver", FilePaths.getChromeDriverFilePath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@AfterSuite
	public void closeBrowser() {
		extendreport.closeExtentTest();
		driver.close();
		driver.quit();
	}

}
