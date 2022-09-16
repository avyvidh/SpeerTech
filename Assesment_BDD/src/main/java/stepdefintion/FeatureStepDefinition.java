package stepdefintion;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.MyTestCasePage;
import cucumber.api.java.en.Then;

import org.openqa.selenium.support.PageFactory;

import common.CommonBaseClass;
import common.ConfigFileReader;

public class FeatureStepDefinition extends CommonBaseClass {
	
	MyTestCasePage ProdigyLabsPage=PageFactory.initElements(driver, MyTestCasePage.class); 
	
  @Given("$")
  public void given() throws Throwable {
	  
	  setup();
	  openURL(ConfigFileReader.getProperty("baseURL"));
  }

  @When("^$")
  public void when() throws Throwable {
	  
	  waitForPageToLoad(7);
	  scroll();
	  
  }

  @Then("^$")
  public void then() throws Throwable {
	 
  }


}
