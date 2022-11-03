package StepDefs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import Base.SeleniumReusablies;
import Runner.TestRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AddToDoItemStepDefs {

	public AddToDoItemStepDefs() throws Exception {
		PageFactory.initElements(TestRunner.driver, this);
	}
	
	@FindBy(xpath = "//input[@class='new-todo']")
	private WebElement toDoTextBox;
	
	@FindBys(@FindBy(xpath = "//li[@class='todo']/div/label"))
	private List<WebElement> activeToDolist;
	
	
	@Given("^Navigate to the url \"([^\"]*)\"$")
	public void navigate_to_the_url(String url) throws Throwable {
	    SeleniumReusablies.openUrl(url);
	}

	@Given("^Enter todo item in the test box as \"([^\"]*)\"$")
	public void enter_todo_item_in_the_test_box_as(String toDoData) throws Throwable {
	   SeleniumReusablies.enterDataByWebelement(toDoTextBox, toDoData,"ToDo Text box");
	}

	@When("^Click on enter$")
	public void click_on_enter() throws Throwable {
	  SeleniumReusablies.clickEnter();  
	}

	@Then("^Entered todo item \"([^\"]*)\" should be display below the text box$")
	public void entered_todo_item_should_be_display_below_the_text_box(String text) throws Throwable {
		SeleniumReusablies.checkGiveTextInElements(activeToDolist, text);
	}

}
