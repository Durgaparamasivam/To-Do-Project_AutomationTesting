package Base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import Runner.TestRunner;

public class SeleniumReusablies  {
	static JavascriptExecutor js = ((JavascriptExecutor) TestRunner.driver);
	
	static Actions actions = new Actions(TestRunner.driver) ;
	
	public static void openUrl(String url) {
		TestRunner.driver.get(url);
		TestRunner.extendreport.logInfo("Url opened");
		TestRunner.log.info("Url opened");
	}

	public static void highLightById(String id) {
		WebElement ele = TestRunner.driver.findElement(By.id(id));
		js.executeScript("arguments[0].style.border='3px solid red';", ele);
	}

	public static void highLightByXpath(String xpath) {
		WebElement ele = TestRunner.driver.findElement(By.xpath(xpath));
		js = ((JavascriptExecutor) TestRunner.driver);
		js.executeScript("arguments[0].style.border='3px solid red';", ele);
	}

	public static void highLightByElement(WebElement ele) {
		js.executeScript("arguments[0].style.border='3px solid red';", ele);
	}

	public static void enterElementById(String id, String data) {
		try {
			WebElement element = TestRunner.driver.findElement(By.id(id));
			if (element.isDisplayed() && element.isEnabled()) {
				highLightById(id);
				element.clear();
				element.sendKeys(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void enterElementByXpath(String xpath, String data) {
		try {
			WebElement element = TestRunner.driver.findElement(By.xpath(xpath));
			if (element.isDisplayed() && element.isEnabled()) {
				highLightByXpath(xpath);
				element.clear();
				element.sendKeys(data);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	// This method is to get value of given Attribute of passed webelement
	public static String getAttributeValueByWebelement(WebElement element, String attributeName) throws IOException {
		String value = null;
		if (element.isDisplayed() && element.isEnabled()) {
			value = element.getAttribute(attributeName);
			TestRunner.extendreport.logInfo("Attribute value for the given attribute " + attributeName +" is "+value);
			TestRunner.log.info("Attribute value for the given attribute " + attributeName +" is "+value);
			return value;
		} else {
			TestRunner.log.info("Unable get Attribute value for " + attributeName);
			TestRunner.extendreport.logFail("Unable get Attribute value for " + attributeName);
			TestRunner.extendreport.LogFailScreenshot("getAttributeValue" + attributeName);
			return value;
		}
	}
	
	// This method is to get value of given Attribute by passing ByTypes
	public static String getAttributeValueByTypes(By byType, String attributeName) throws IOException {
		String value = null;
		WebElement element = TestRunner.driver.findElement(byType);
		if (element.isDisplayed() && element.isEnabled()) {
			value = element.getAttribute(attributeName);
			TestRunner.extendreport.logInfo("Attribute value for the given attribute " + attributeName + " is " + value);
			TestRunner.log.info("Attribute value for the given attribute " + attributeName + " is " + value);
			return value;
		} else {
			TestRunner.log.info("Unable get Attribute value for " + attributeName);
			TestRunner.extendreport.logFail("Unable get Attribute value for " + attributeName);
			TestRunner.extendreport.LogFailScreenshot("getAttributeValue" + attributeName);
			return value;
		}
	}
	
	public static String getTextByWebelement(WebElement element, String elementName) throws IOException {
		String value = null;
		if (element.isDisplayed() && element.isEnabled()) {
			value = element.getText();
			TestRunner.extendreport.logInfo("Text for the given webelement "+elementName+" is " +value);
			TestRunner.log.info("Text for the given webelement "+elementName+" is " +value);
			return value;
		} else {
			TestRunner.log.info("Unable get the text of the given webelement "+elementName);
			TestRunner.extendreport.logFail("Unable get the text of the given webelement "+elementName);
			TestRunner.extendreport.LogFailScreenshot("getTextByWebelement"+elementName);
			return value;
		}
	}
	
	public static String getTextByType(By byType, String fieldName) throws IOException {
		String value = null;
		try {
		WebElement element = TestRunner.driver.findElement(byType);
		if (element.isDisplayed() && element.isEnabled()) {
			value = element.getText();
			TestRunner.extendreport.logInfo("Text for the given webelement "+fieldName+" is " +value);
			TestRunner.log.info("Text for the given webelement "+fieldName+" is " +value);
		} else {
			TestRunner.log.info("Unable get the text of the given webelement "+fieldName);
			TestRunner.extendreport.logInfo("Unable get the text of the given webelement "+fieldName);
		}
		} catch (Exception e) {
			TestRunner.log.info("Unable get the text of the given webelement "+fieldName);
			TestRunner.extendreport.logFail("Unable get the text of the given webelement "+fieldName);
			TestRunner.extendreport.LogFailScreenshot("getTextByType"+fieldName);
		}
		return value;
	}

	// This method is to enter date on element by passing webelement
	public static void enterDataByWebelement(WebElement element, String data, String fieldName) throws IOException {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.clear();
				element.sendKeys(data);
				TestRunner.extendreport.logInfo("Entered data in field " + fieldName);
				TestRunner.log.info("Entered data in field " + fieldName);
			}
			

		} catch (Exception e) {
			TestRunner.log.info("Unable to enter data in field " + fieldName +e.getMessage());
			TestRunner.extendreport.logFail("Unable to enter data into field " + fieldName);
			TestRunner.extendreport.LogFailScreenshot("enterDataByWebelement" + removeSpaceInString(fieldName));
		}

	}
	
	// This method is to enter date on element by passing webelement
		public static void enterIntegerDataByWebelement(WebElement element, int data, String fieldName) throws IOException {
			try {
				String intValue = SeleniumReusablies.typeCastIntToString(data);
				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					element.sendKeys(intValue);
					TestRunner.extendreport.logInfo("Entered data in field " + fieldName);
					TestRunner.log.info("Entered data in field " + fieldName);
				}
				else {
					TestRunner.extendreport.logInfo("Unable to entered data in field " + fieldName);
					TestRunner.log.info("Unable to entered data in field " + fieldName);
				}

			} catch (Exception e) {
				TestRunner.log.info("Unable to enter data in field " + fieldName);
				TestRunner.extendreport.logFail("Unable to enter data into field " + fieldName);
				TestRunner.extendreport.LogFailScreenshot("enterDataByWebelement" + removeSpaceInString(fieldName));
			}

		}
	
	// This method is to enter date on element by passing By
		public static void enterDataByType(By byelement, String data, String fieldName) throws IOException {
			try {
				WebElement element = TestRunner.driver.findElement(byelement);
				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					element.sendKeys(data);
					TestRunner.extendreport.logInfo("Entered data in field " + fieldName);
					TestRunner.log.info("Entered data in field " + fieldName);
				}
				else {
					TestRunner.extendreport.logInfo("Unable to entered data in field " + fieldName);
					TestRunner.log.info("Unable to entered data in field " + fieldName);
				}

			} catch (Exception e) {
				TestRunner.log.info("Unable to enter data in field " + fieldName);
				TestRunner.extendreport.logFail("Unable to enter data into field " + fieldName);
				TestRunner.extendreport.LogFailScreenshot("enterDataByWebelement" + removeSpaceInString(fieldName));
			}

		}
	
	// This method is select value from dropdowm by comparing with given text and
		// with dropdown values
		public static void selectElementByType(By dropdown, By options, String dropdownvalue,String fieldName) throws InterruptedException, IOException {
			try {
				WebElement element = TestRunner.driver.findElement(dropdown);
				if (element.isDisplayed() && element.isEnabled()) {
					element.click();
					Thread.sleep(2000);
					TestRunner.extendreport.logInfo("Clicked on dropdown " + fieldName);
					TestRunner.log.info("Clicked on dropdown " + fieldName);
					
					List<WebElement> values = TestRunner.driver.findElements(options);
					for (WebElement value : values) {
						// System.out.println("dropdown value is"+value.getText());
						if (value.getText().equalsIgnoreCase(dropdownvalue)) {
							Thread.sleep(1000);
							value.click();
							Thread.sleep(1000);
							TestRunner.extendreport.logPass("Selected the dropdown value as " + dropdownvalue);
							TestRunner.log.info("Selected the dropdown value as " + dropdownvalue);
						}
					}
				}

			} catch (Exception e) {

				TestRunner.log.info("Unable to select data in dropdown field " + fieldName + " " + e.getMessage());
				TestRunner.extendreport.logFail("Unable to selcet data into dropdown field " + fieldName);
				TestRunner.extendreport.LogFailScreenshot("Select" +removeSpaceInString(fieldName));

			}
		}
		
	
	// This method is select value from dropdowm by comparing with given text and
	// with dropdown values
	public static void selectElement(WebElement element, List<WebElement> options, String dropdownvalue,
			String fieldName) throws InterruptedException, IOException {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				Thread.sleep(2000);
				TestRunner.extendreport.logInfo("Clicked on dropdown " + fieldName);
				TestRunner.log.info("Clicked on dropdown " + fieldName);
				List<WebElement> values = options;
				for (WebElement value : values) {
					// System.out.println("dropdown value is"+value.getText());
					if (value.getText().equalsIgnoreCase(dropdownvalue)) {
						Thread.sleep(1000);
						value.click();
						Thread.sleep(1000);
						TestRunner.extendreport.logPass("Selected the dropdown value as " + dropdownvalue);
						TestRunner.log.info("Selected the dropdown value as " + dropdownvalue);
					}
					else {
						TestRunner.extendreport.logFail("No dropdown value matched with give value "+dropdownvalue);
						TestRunner.log.info("No dropdown value matched with give value "+dropdownvalue);
					}
				}
			}

		} catch (Exception e) {

			TestRunner.log.info("Unable to select data in dropdown field " + fieldName + " " + e.getMessage());
			TestRunner.extendreport.logFail("Unable to selcet data into dropdown field " + fieldName);
			TestRunner.extendreport.LogFailScreenshot("Select" +removeSpaceInString(fieldName));

		}
	}
	
	//This method is to Click on element by passing ByType
	public static void clickElementByType(By byType,String fieldName) throws InterruptedException, IOException {
		try {
			WebElement element = TestRunner.driver.findElement(byType);
		if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				Thread.sleep(2000);
				TestRunner.extendreport.logInfo("Clicked on field "+fieldName);
				TestRunner.log.info("Clicked on field "+fieldName);
			}
	} catch (Exception e) {
		TestRunner.log.info("Unable to click on element "+fieldName+" "+e.getMessage());
		TestRunner.extendreport.logFail("Unable to click on element "+fieldName);
		TestRunner.extendreport.LogFailScreenshot("click"+removeSpaceInString(fieldName));
	}
	}
	
	//This method is to Click on element by passing webelement
		public static void clickElementByWebElement(WebElement element,String fieldName) throws InterruptedException, IOException {
			try {	
					element.click();
					TestRunner.extendreport.logInfo("Clicked on field "+fieldName);
					TestRunner.log.info("Clicked on field "+fieldName);
				}
		 catch (Exception e) {
			TestRunner.log.info("Unable to click on element "+fieldName+" "+e.getMessage());
			TestRunner.extendreport.logFail("Unable to click on element "+fieldName);
			TestRunner.extendreport.LogFailScreenshot("click"+removeSpaceInString(fieldName));
		}
}
		
	
	//This method is to validate client created or not
	public static Boolean validateElementDisplayOrNot(WebElement element, String fieldName) throws IOException {
		boolean isDisplayed = false;
		try{
		if (element.isDisplayed() && element.isEnabled()) {
			SeleniumReusablies.highLightByElement(element);
			TestRunner.extendreport.logPass("WebElement "+fieldName+" displayed");
			TestRunner.log.info("WebElement "+fieldName+" displayed");
			isDisplayed = true;
		}
		else {
			TestRunner.extendreport.logPass("WebElement "+fieldName+" is not displayed/enabled");
			TestRunner.log.info("WebElement "+fieldName+" is not displayed/enabled");
		}
		
	} catch (org.openqa.selenium.NoSuchElementException e) {
		TestRunner.extendreport.logPass("WebElement "+fieldName+" is not displayed/enabled");
		TestRunner.log.info("WebElement "+fieldName+" is not displayed/enabled");
	}
		catch (Exception e) {
		TestRunner.log.info("Unable to find webelement "+fieldName+" "+e.getMessage()); 
		TestRunner.extendreport.logFail("Unable to find webelement "+fieldName); 
		TestRunner.extendreport.LogFailScreenshot("validateElementDisplayOrNot_"+removeSpaceInString(fieldName));
	}
		return isDisplayed;
		
}
	
	//This method is to validate client created or not
		public static Boolean validateElementDisplayOrNotByType(By byType, String fieldName) throws IOException {
			boolean isDisplayed = false;
			WebElement element = TestRunner.driver.findElement(byType);
			try{
			if (element.isDisplayed() && element.isEnabled()) {
				SeleniumReusablies.highLightByElement(element);
				TestRunner.extendreport.logPass("WebElement "+fieldName+" displayed");
				TestRunner.log.info("WebElement "+fieldName+" displayed");
				isDisplayed = true;
			}
			else {
				TestRunner.extendreport.logPass("WebElement "+fieldName+" is not displayed/enabled");
				TestRunner.log.info("WebElement "+fieldName+" is not displayed/enabled");
				isDisplayed = false;
			}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestRunner.extendreport.logPass("WebElement "+fieldName+" is not displayed/enabled");
			TestRunner.log.info("WebElement "+fieldName+" is not displayed/enabled");
		}
			catch (Exception e) {
			TestRunner.log.info("Unable to find webelement "+fieldName+" "+e.getMessage()); 
			TestRunner.extendreport.logFail("Unable to find webelement "+fieldName); 
			TestRunner.extendreport.LogFailScreenshot("validateElementDisplayOrNot_"+removeSpaceInString(fieldName));
		}
			return isDisplayed;
			
	}
	
	//This method is to validate client created or not by comparing with Text at webelement
	public static boolean validateElementWithExactText(WebElement element,String text, String fieldName) throws IOException {
		boolean status;
		try {
			if (element.getText().equalsIgnoreCase(text)) {
				SeleniumReusablies.highLightByElement(element);
				TestRunner.extendreport.logPass("WebElement text matched with "+text+" at "+fieldName+" field");
				TestRunner.log.info("WebElement text matched with "+text+" at "+fieldName+" field");
				return true;
			}
			else {
				TestRunner.extendreport.logFail("WebElement text not matched with "+text+" at "+fieldName+" field");
				TestRunner.log.info("WebElement text not matched with "+text+" at "+fieldName+" field");
				throw new Exception();
			}
			
	} catch (Exception e) {
		TestRunner.log.info("Unable to find webelement "+fieldName+" "+e.getMessage());
		TestRunner.extendreport.logFail("Unable to find webelement "+fieldName);
		TestRunner.extendreport.LogFailScreenshot("validateElementWithExactText"+removeSpaceInString(fieldName));
		return false;
	}
}
	
	//This method is to validate client created or not by comparing with Text at webelement
	public static boolean validateElementWithExactText(By byType,String text, String fieldName) throws IOException {
		boolean status;
		try {
			WebElement element = TestRunner.driver.findElement(byType);
			if (element.getText().equalsIgnoreCase(text)) {
				SeleniumReusablies.highLightByElement(element);
				TestRunner.extendreport.logPass("WebElement text matched with "+text+" at "+fieldName+" field");
				TestRunner.log.info("WebElement text matched with "+text+" at "+fieldName+" field");
				return true;
			}
			else {
				TestRunner.extendreport.logFail("WebElement text not matched with "+text+" at "+fieldName+" field");
				TestRunner.log.info("WebElement text not matched with "+text+" at "+fieldName+" field");
				throw new Exception();
			}
			
	} catch (Exception e) {
		TestRunner.log.info("Unable to find webelement "+fieldName+" "+e.getMessage());
		TestRunner.extendreport.logFail("Unable to find webelement "+fieldName);
		TestRunner.extendreport.LogFailScreenshot("validateElementWithExactText"+removeSpaceInString(fieldName));
		return false;
	}
}
	
	//This method is to validate client created or not by comparing with Text at weblement
		public static boolean validateElementWithContainsText(WebElement element,String text, String fieldName) throws IOException {
			boolean status;
			try {
				if (element.getText().contains(text)) {
					SeleniumReusablies.highLightByElement(element);
					TestRunner.extendreport.logPass("WebElement text contains text "+text+" at "+fieldName+" field");
					TestRunner.log.info("WebElement text contains text "+text+" at "+fieldName+" field");
					return true;
				}
				else {
					TestRunner.extendreport.logPass("WebElement text not contains text "+text+" at "+fieldName+" field");
					TestRunner.log.info("WebElement text not contains text "+text+" at "+fieldName+" field");
					throw new Exception();
				}
				
		} catch (Exception e) {
			TestRunner.log.info("Unable to find webelement "+fieldName+" "+e.getMessage());
			TestRunner.extendreport.logFail("Unable to find webelement "+fieldName);
			TestRunner.extendreport.LogFailScreenshot("validateElementWithText_"+removeSpaceInString(fieldName));
			return false;
		}
	}
	
	public static void clickEnter() {
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	public static void clickElementById(String id) {
		try {
			WebElement element = TestRunner.driver.findElement(By.id(id));
			if (element.isDisplayed() && element.isEnabled()) {
				highLightById(id);
				element.click();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void clickElementByXpath(String xpath) {
		try {
			WebElement element = TestRunner.driver.findElement(By.xpath(xpath));
			if (element.isDisplayed() && element.isEnabled()) {
				//highLightById(xpath);
				element.click();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void enterElementByxpath(String xpath, String data) {
		try {
			WebElement element = TestRunner.driver.findElement(By.xpath(xpath));
			if (element.isDisplayed() && element.isEnabled()) {
				highLightByXpath(xpath);
				element.clear();
				element.sendKeys(data);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	public static WebElement findElementByxpath(String xpath) {
				return TestRunner.driver.findElement(By.xpath(xpath));
	}
	
	public static String  takeScreenshot(String methodName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File srcFile = ((TakesScreenshot) TestRunner.driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/"+methodName+dateName+".png";
		FileUtils.copyFile(srcFile, new File(destination));
		return destination;
	}

	public static void implictyWait(int sec) {
		TestRunner.driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	
	public static void threadSleep(int sec) throws InterruptedException {
		Thread.sleep(sec*1000);
	}
	
	public static void click_Esc()
	{
		actions.sendKeys(Keys.ESCAPE).build().perform();
	}
	
	
	public static void refreshPage()
	{
		TestRunner.driver.navigate().refresh();
		System.out.println("Refresh browser");
	}
	
	
	public static  void explict_wait_till_visibilityOf(WebElement element) {
			WebDriverWait wait = new WebDriverWait(TestRunner.driver, 30000);
//			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.until(ExpectedConditions.visibilityOf(element));	
		
	}
	
	public static  Boolean explict_wait_till_InVisibilityOf(WebElement element, String fieldName) throws IOException {
		boolean isDisplayed = false;
		try {
			
		WebDriverWait wait = new WebDriverWait(TestRunner.driver, 10000);
		wait.until(ExpectedConditions.invisibilityOf(element));	
		isDisplayed = true;
		TestRunner.extendreport.logInfo("Element "+fieldName+" is InVisiable");
		TestRunner.log.info("Element "+fieldName+" is InVisiable");
		} catch (Exception e) {
			TestRunner.log.info("Element is "+fieldName+" Visiable"+e.getMessage());
			TestRunner.extendreport.logFail("Element is "+fieldName+" Visiable");
			TestRunner.extendreport.LogFailScreenshot("visibilityOf"+removeSpaceInString(fieldName));
		}
		
		return isDisplayed;
}
	
	public static void dragAndDropElement(WebElement dragElement, int Xcoordinate, int Ycoordinate, String fieldName) throws IOException {
		try {
			
			actions.doubleClick(dragElement).dragAndDropBy(dragElement, Xcoordinate, Ycoordinate).build().perform();;
			TestRunner.extendreport.logInfo(fieldName+" element is draganddroped");
			TestRunner.log.info(fieldName+" element is draganddroped");
		} catch (Exception e) {
			TestRunner.log.info("Unable to draganddroped "+fieldName+" element "+e.getMessage());
			TestRunner.extendreport.logFail("Unable to draganddroped "+fieldName+" element ");
			TestRunner.extendreport.LogFailScreenshot("dragAndDropElement"+removeSpaceInString(fieldName));
		}

	}
	
	
	public static  void explict_wait_till_visibilityOfAllElements(List<WebElement> elements) {
		try {
		
			WebDriverWait wait = new WebDriverWait(TestRunner.driver, 1000);
//			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		}
		catch(Exception e)
		{
			System.out.println(elements + "Element not present");
			
		}
		
	}
	//@SuppressWarnings("deprecation")
	public static void fluent_wait() {
		/*
		 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) .withTimeout(500,
		 * TimeUnit.SECONDS) .pollingEvery(3, TimeUnit.SECONDS)
		 * .ignoring(StaleElementReferenceException.class);
		 
*/
	}

	public static void javaScript_ClickByWebElement(WebElement element, String fieldName) throws IOException {
		try {
			Thread.sleep(3000);
			//if (element.isDisplayed() && element.isEnabled()) {
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(3000);
			TestRunner.extendreport.logInfo("Clicked on field " + fieldName);
			TestRunner.log.info("Clicked on field " + fieldName);
			//}
		} catch (Exception e) {
			TestRunner.log.info("Unable to click on element "+fieldName+" "+e.getMessage());
			TestRunner.extendreport.logFail("Unable to click on element "+ fieldName);
			TestRunner.extendreport.LogFailScreenshot("click" + removeSpaceInString(fieldName));
		}

	}
	
	public static void checkGiveTextInElements(List<WebElement> list,String text) throws IOException {
		String clientName = null;
		try {
			// SeleniumReusablies.enterDataByType(clientSideBarSearch, clientname,"Search");
			List<WebElement> values = list;
			for (WebElement value : values) {
				Thread.sleep(1000);
				clientName = value.getText();
				if (clientName.equalsIgnoreCase(text)) {
					SeleniumReusablies.implictyWait(30);
					TestRunner.extendreport.logPass("Text"+ text +" displayed in the list" );
					TestRunner.log.info("Text "+ text +" displayed in the list");
				}
			}
		} catch (Exception e) {
			TestRunner.log.info("Text"+ text +" not displayed in the list" + e.getMessage());
			TestRunner.extendreport.logFail("Text"+ text +" not displayed in the list");
			//extendreport.LogFailScreenshot("selectGivenClient");
		}
	}
	
	public static void javaScript_Click(WebElement element) throws InterruptedException
	{
		js = ((JavascriptExecutor) TestRunner.driver);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
	}
	
	public static  void scroll_Vertical() throws InterruptedException {
		js = ((JavascriptExecutor)TestRunner.driver);
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,1000)", "");	
	}
	
	public static  void scrollTOWebElement(WebElement webelement, String fieldName) throws IOException {
		try {
		js = ((JavascriptExecutor)TestRunner.driver);
		explict_wait_till_visibilityOf(webelement);
		js.executeScript("arguments[0].scrollIntoView();", webelement);	
		TestRunner.extendreport.logInfo("Scrolled to webelement " + fieldName);
		TestRunner.log.info("Scrolled to webelement " + fieldName);
		}
		catch (Exception e) {
			TestRunner.log.info("Unable to scroll to webelement "+fieldName+" "+e.getMessage());
			TestRunner.extendreport.logFail("Unable to scroll to webelement "+ fieldName);
			TestRunner.extendreport.LogFailScreenshot("scrollTOWebElement" + removeSpaceInString(fieldName));
		}
	}
	
	public static String decryptPassword(String encodedBytes) {
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		return new String(decodedBytes);
	}
	
	//
	public static void uploadDocument(WebElement element, String filepath) throws IOException {
		try {
			
			element.sendKeys(filepath);
			TestRunner.extendreport.logInfo("Successfully uploaded the file");
			TestRunner.log.info("Successfully uploaded the file");
		} catch (Exception e) {
			TestRunner.log.info("Unable to upload the file "+e.getMessage());
			TestRunner.extendreport.logFail("Unable to upload the file ");
			TestRunner.extendreport.LogFailScreenshot("uploadDoc");
		}
	}
	
	public static String removeSpaceInString(String name) {
		return name.replace(" ", "");
	}
	
	public static int typeCastStringToInt(String stringValue) {
		return Integer.parseInt(stringValue);	
	}
	
	public static String typeCastIntToString(int intValue) {
		return String.valueOf(intValue);	
	}
	
	
}
