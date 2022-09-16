package test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.CommonBaseClass;
import common.ConfigFileReader;
import common.baseClass;
import pages.MyTestCasePage;



public class MyTestCase extends CommonBaseClass{
	
	
	static Properties properties; 
	
	@BeforeMethod
	public void beforeEachTest() {
		openURL(ConfigFileReader.getProperty("baseURL"));
		
	}
	@AfterMethod
	public void afterEachTest() {
		
	}
	
	
	@Test
	public void submitForm() throws Exception {
		
		try
		{
			MyTestCasePage MyTestCasePage=PageFactory.initElements(driver, MyTestCasePage.class);	
			System.out.println("Enter the number of cycle to run ");
			int n =new Scanner( System.in ).nextInt();
			
			ConcurrentHashMap<String, Integer> listoflink = new ConcurrentHashMap<String, Integer>();
			listoflink = MyTestCasePage.getLinks(listoflink);
		
			int i=1;
			do
			{
				openURL((listoflink.keySet().toArray()[i]).toString());
				waitForPageToLoad(7);
				int size = driver.findElements(By.xpath("//a")).size();		
				for(int k = 1; k <= size ; k++ )
				{
				   String href = driver.findElement(By.xpath("(//a)["+k+"]")).getAttribute("href");
				   
				   if( href ==null || href.isEmpty())
					   continue;
				   if(listoflink.containsKey(href))
					   listoflink.put(href,listoflink.get(href)+1);
				   else
				  listoflink.put(href,1);
				}
				i++;
			}while(i<n);
				
			for (Entry<String, Integer> entry : listoflink.entrySet()) {
				System.out.println("The final list of url and their count is " + listoflink.size());
				System.out.println(entry.getKey()+"	|	"+entry.getValue());
				
			}
				
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}