package Reports;

import java.io.IOException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report 
{
	
	 static ExtentReports reports;
	  static ExtentTest test ;
	
	public    void extentReportFilepath(String extentReportsFilepath) {
		reports = new ExtentReports(extentReportsFilepath, true);
	}
	
		
	public  void extentTestClass(String m) {
		
		test = reports.startTest(m);
	}
	
	public  void logInfo(String message) {
		test.log(LogStatus.INFO, message);
	}
	
	public  void logPass(String message) {
		test.log(LogStatus.PASS, message);
	}
	
	
	public void logFail(String message) {
		test.log(LogStatus.FAIL, message);
		
	}
	
	public  void LogFailScreenshot(String imagePath) throws IOException {
		//String methodName = result.getName();
		//test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumReusablies.takeScreenshot(imagePath)));
	}
	
	public  void closeExtentTest() {
		
		reports.endTest(test);
		reports.flush();
		//reports.close();
	}
	
	
}
