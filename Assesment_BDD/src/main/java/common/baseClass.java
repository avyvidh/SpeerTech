package common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class baseClass {
	
	public static WebDriver driver;
	static String screenShotPath="";
	static String rs_username;
	static String rs_password;
	static String trm_username;
	static String trm_password;
	static String tdss_username;
	static String tdss_password;
	@BeforeClass
	
	public void setup() {
        
		ConfigFileReader read=new ConfigFileReader();
		String browser = "Chrome";
		try 
		{
			System.out.println("Opening Browser :" + browser);
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
				
				driver = new ChromeDriver();
				driver.manage().window().maximize();

			} else {
				System.out
						.println("Browser Type Invalid .. Please check testng.xml file and make changes in parameters");
			}
		} catch (Exception exp) {
			//CaptureScreenshot("openBrowser");
			System.out.println("Exception Captured while opening Browser for browser type" + browser
					+ " Exception Message recieved is :" + exp.getMessage());
		}
		
		
		
		
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	
	
	
	
	
	
	
	public static void openURL(String URL) {
		try {
			System.out.println("Opening URL :" + URL);
			driver.get(URL);
			System.out.println(driver.getCurrentUrl() + " is opened");
		} catch (Exception exp) {
			CaptureScreenshot("openURL");
			System.out.println(
					"Exception catched while opening URL :" + URL + " with Exception message as :" + exp.getMessage());
		}
	}
	
    
	public static void passTextToWebelement(WebElement element, String inputData) {
		try {
			
			waitForElementExplicitly(element);

			if (element.isDisplayed()) {
				System.out.println("Entering :" + inputData);
				element.clear();
				element.sendKeys(inputData);
			} else {
				System.out.println("Element is not Displayed ..");
				Assert.assertTrue(false);
			}
		} catch (Exception exp) {
			CaptureScreenshot("passTextToWebelement");
			System.out.println("Exception occured while Entering value in Webelement" + element
					+ " and Exception Message recieved is :" + exp.getMessage());

		}

	}

	public static void selectFromDropBox(WebElement element, String visibleText) {
		try {

			Select select = new Select(element);
			System.out.println("Selecting " + visibleText + " from Dropbox");
			select.selectByVisibleText(visibleText);
		} catch (Exception exp) {
			CaptureScreenshot("selectFromDropBox");
			System.out.println("Exception Occured while Selecting from Dropbox for Element :" + element
					+ ", Exception Message Displayed :" + exp.getMessage());
		}
	}
	

	public static void scroll() {
		try {
			JavascriptExecutor jsx = (JavascriptExecutor)driver;
			jsx.executeScript("window.scrollBy(0,450)", "");
			
			
		} catch (Exception exp) {
			CaptureScreenshot("click");
			System.out.println(exp.getMessage());
			System.out.println("Exception Occurred while Scrolling the page :");
			Assert.assertTrue(false);
		}
	}

	public static void scrollIntoView(WebElement element) {
		try {
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
			
			
		} catch (Exception exp) {
			CaptureScreenshot("click");
			System.out.println(exp.getMessage());
			System.out.println("Exception Occurred while Scrolling the page :");
			Assert.assertTrue(false);
	}
	}
	
	public static void keyPressEscape() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
	        robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
	
	public static void click(WebElement element) {
		try {
			Thread.sleep(3000);
		    waitForElementExplicitly(element);
			element.click();
			
		} catch (Exception exp) {
			CaptureScreenshot("click");
			System.out.println("Exception Occurred while Clicking on Webelement :" + element);
			Assert.assertTrue(false);
		}
	}
	
	public static Boolean isDisplayed(WebElement element) {
		Boolean flag=false;
		try {
			waitForElementExplicitly(element);
			if(element.isDisplayed()) {
				flag=true;
			}
			
		} catch (Exception exp) {
			CaptureScreenshot("isDispalyed");
			System.out.println("Exception Occurred while Checking element displayed :" + element);
			Assert.assertTrue(false);
		}
		return flag;
	}
	
	public static void dragAndDropBy(WebElement source, int length, int breath) {
		Actions act=new Actions(driver);					
	      act.dragAndDropBy(source, length, breath).build().perform();
	}
	
	public static void clickAndHoldMoveToElement(WebElement targetHoldElement,WebElement targetDestinationElement ) {
		 Actions hold = new Actions(driver);
	      hold.clickAndHold(targetHoldElement)
	          .moveToElement(targetDestinationElement).release(targetDestinationElement)
	          .build().perform();
	}
	public static void verifyText(WebElement element, String match) {

		String actText = null;
		try {
			actText = element.getText();
			Thread.sleep(5000);
			if (actText.isEmpty()) {
				actText = element.getAttribute("value");
			} else if (!actText.equalsIgnoreCase(match)) {
				Assert.fail("Assertion Failed due to actual text :" + actText + " and Expected Text :" + match
						+ " are not matching");
			} else {
				System.out.println("Verified actual text== " + actText + "  expected text== " + match);
			}
		} catch (Exception exp) {
			//CaptureScreenshot("verfiyText");
			System.out.println("Exception Occured while verfiying text on Webelement");
			System.out.println("Message Recieved :" + exp.getMessage());
		}
	}

	public static Boolean verifyText(String actText, String match) {
		Boolean flag=false;
		try {
			Thread.sleep(5000);
			
			if (actText.equalsIgnoreCase(match)) {
				System.out.println("Verified actual text== " + actText + "  expected text== " + match);
				flag=true;
			}
			else {
				System.out.println("Actual msg== " + actText);
				System.out.println("Error msg== " + match );
				//Assert.assertTrue(false,"Actual msg did not matched with expected");
			}
			
		} catch (Exception exp) {
			
			System.out.println(exp);
		}
		return flag;
	}

	public static String getText(WebElement element) {
		String actText = null;
		try {
			waitForPageToLoad(90);
			actText = element.getText();

		} catch (Exception exp) {
			CaptureScreenshot("verifyTextContains");
			System.out.println("Exception Occured while fetching text of Webelement");

		}
		return actText;
	}

	public static void verifyElementEnabled(WebElement element) {

		try {
			System.out.println("Verfiying Element is Enabled on UI ");
			waitForPageToLoad(90);
			if (!element.isEnabled()) {
				Assert.fail(": is not displayeyd on UI ");
			}
		} catch (Exception exp) {
			CaptureScreenshot("verifyElementEnabled");
			System.out.println("Exception Occured while verfying Element is Enabled or not ," + " Message Recieved :"
					+ exp.getMessage());
		}
	}

	public static void closeBrowser() {
		System.out.println("Closing current browsers");
		try {
			driver.close();
		} catch (Exception exp) {
			CaptureScreenshot("closeBrowser");
			System.out.println("Exception Occured while Closing Browser , Message Recieved :" + exp.getMessage());
		}
	}

	public static void quitBrowser() {
		System.out.println("Closing all the Opened Browser in the Exection");
		try {
			driver.quit();
		} catch (Exception exp) {
			CaptureScreenshot("quitBrowser");
			System.out.println(
					"Exception occured while Closing all the browsers , Message Recieved :" + exp.getMessage());
		}
	}

	public static void waitForElementExplicitly(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void waitForPageToLoad(int seconds) {
		try {
			System.out.println("Waiting For the page to Load till :" + seconds + " secs");
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		} catch (Exception exp) {
			CaptureScreenshot("waitForPageToLoad");
			System.out.println("Exception Occured while waiting For page to Load Implicitly," + " Exception Message :"
					+ exp.getMessage());
		}
	}
	
	public static void CaptureScreenshot(String ImageName) {
		try {

			String imagePath = screenShotPath + ImageName + ".png";
			System.out.println("Capturing ScreenShot trigerred");
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(source, new File(imagePath));
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
		
	

}
