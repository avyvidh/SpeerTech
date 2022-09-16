 package pages;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.CommonBaseClass;

public class MyTestCasePage extends CommonBaseClass{

	
	@FindBy(how = How.XPATH, using = "//div[@id='header-secondary-outer']//a[text()='Contact']")
    private WebElement ContactLink;
	
	public WebElement getContactLink() {
		return ContactLink;
	}
	
	
	@FindBy(how = How.XPATH, using = "(//div[@class='wpb_text_column wpb_content_element '])[2]//h4")
    private WebElement ContactEmailLink;
	
	public WebElement getContactEmailLink() {
		return ContactEmailLink;
	}
	
	@FindBy(how = How.XPATH, using = "(//div[@class='wpb_text_column wpb_content_element '])[3]//h4")
    private WebElement OfficeAddress;
	
	public WebElement getOfficeAddress() {
		return OfficeAddress;
	}
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'firstname')]")
	private WebElement FirstName;
		
	public WebElement getFirstName() {
	return FirstName;	
	}
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'lastname')]")
	private WebElement LastName;
		
	public WebElement getLastName() {
	return LastName;	
	}
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'email')]")
	private WebElement Email;
		
	public WebElement getEmail() {
	return Email;	
	}
	
	
	@FindBy(how = How.XPATH, using = "//select[contains(@id,'industry')]")
	private WebElement Industry;
		
	public WebElement getIndustry() {
	return Industry;	
	}
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'city')]")
	private WebElement City;
		
	public WebElement getCity() {
	return City;	
	}
	
	@FindBy(how = How.XPATH, using = "//textarea[contains(@id,'message')]")
	private WebElement Messsage;
		
	public WebElement getMesssage() {
	return Messsage;	
	}
	
	
	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Submit')]")
	private WebElement SubmitButton;
		
	public WebElement getSubmitButton() {
	return SubmitButton;	
	}
	
	@FindBy(how = How.XPATH, using = "//label[@class='hs-error-msg']")
	private WebElement EmailErrorMessage;
		
	public WebElement getEmailErrorMessage() {
	return EmailErrorMessage;	
	}
	
	public ConcurrentHashMap<String,Integer> getLinks(ConcurrentHashMap<String, Integer> listoflink) throws Exception
	{
        
		waitForPageToLoad(7);
		int size = driver.findElements(By.xpath("//a")).size();		
		for(int i = 1; i <= size ; i++ )
		{
		   String href = driver.findElement(By.xpath("(//a)["+i+"]")).getAttribute("href");
		   
		   if( href ==null || href.isEmpty())
			   continue;
		   if(listoflink.containsKey(href))
			   listoflink.put(href,listoflink.get(href)+1);
		   else
		  listoflink.put(href,1);
			
		}
		return listoflink;
	}
}
