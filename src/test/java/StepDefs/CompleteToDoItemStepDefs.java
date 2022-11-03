package StepDefs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import Base.SeleniumReusablies;
import Runner.TestRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CompleteToDoItemStepDefs {

	 public String activeItemText;
	
	public CompleteToDoItemStepDefs() throws Exception {
		PageFactory.initElements(TestRunner.driver, this);
	}
	
	
	@FindBy(xpath = "//li[@class='todo']/div/label")
	private WebElement activeToDoItem;
	
	@FindBy(xpath = "//li[@class='todo']/div/input")
	private WebElement activeToDolistCheckBox;
	
	@FindBys(@FindBy(xpath = "//li[@class='todo completed']/div/label"))
	private List<WebElement> completedToDolist;
	
	@And("^Get the text of to complete Todo item$")
	public void get_the_text_of_to_complete_Todo_item() throws Throwable {
	    activeItemText  = SeleniumReusablies.getTextByWebelement(activeToDoItem, "Todo item");
	}
	
	@When("^Click on check box of any item$")
	public void click_on_check_box_of_any_item() throws Throwable {
		//SeleniumReusablies.javaScript_ClickByWebElement(activeToDolistCheckBox, "activeItemCheckBox");
	   SeleniumReusablies.clickElementByWebElement(activeToDolistCheckBox, "activeItemCheckBox");
	   
	}

	@Then("^Check the item moved to completed list or not$")
	public void check_the_item_moved_to_completed_list_or_not() throws Throwable {
	    SeleniumReusablies.checkGiveTextInElements(completedToDolist, activeItemText);
	}
	
}
