package com.training.browsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinuxTest {
	
	ChromeOptions options = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		//Below code is disable notifications from chrome browser during the testing
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2); 
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--no-sandbox");
		
		/*FirefoxProfile profile = new FirefoxProfile();
	    profile.setPreference("permissions.default.desktop-notification", 1);
	    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
	    capabilities.setCapability("marionatte", false);
	    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
	    WebDriver driver = new FirefoxDriver(capabilities);*/
		System.setProperty("webdriver.gecko.driver",  "geckodriver");
		firefoxOptions.addArguments("--display=0");
	}
	
	@Test (enabled = false)
	public void loginTest1() {
		WebDriver driver = new ChromeDriver(options);
		facebookLoginTestChrome(driver);
		driver.close();
	}
	
	@Test (enabled = false)
	public void loginTest2() {
		WebDriver driver = new ChromeDriver(options);
		facebookLoginTestChrome(driver);
		driver.close();
	}
	
	@Test (enabled = false)
	public void loginTest3() {
		WebDriver driver = new FirefoxDriver(firefoxOptions);	
		facebookLoginTestFirefox(driver);
		driver.close();
	}
	
	@Test (enabled = false)
	public void loginTest4() {
		WebDriver driver = new FirefoxDriver(firefoxOptions);	
		facebookLoginTestFirefox(driver);
		driver.close();
	}
	
	public static void facebookLoginTestChrome(WebDriver driver) {
		driver.get("https://www.facebook.com");
		
		driver.findElement(By.id("email")).sendKeys("gamecheck280@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("system123");
		driver.findElement(By.name("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=\"submit\"]")));
		
		List <WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(By.tagName("span"));
		boolean userfound = false;
		for(WebElement e: list) {
			if (e.getText().contains("Gamecheck") && !e.getText().contains("Not you") && !e.getText().contains("Log in as")) {
				userfound = true;
				break;
			}
		}
		
		driver.findElement(By.id("userNavigationLabel")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span//form[@method='post']")));
		WebElement ele = driver.findElement(By.xpath("//span//form[@method='post']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='websubmit']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@role='button' and contains(text(), 'Create New Account')]")));
		
		AssertJUnit.assertTrue(userfound);
	}
	
	public static void facebookLoginTestFirefox(WebDriver driver) {
		driver.get("https://www.facebook.com");
		
		driver.findElement(By.id("email")).sendKeys("gamecheck280@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("system123");
		driver.findElement(By.name("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=\"submit\"]")));
		
		List <WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(By.tagName("span"));
		boolean userfound = false;
		for(WebElement e: list) {
			if (e.getText().contains("Gamecheck") && !e.getText().contains("Not you") && !e.getText().contains("Log in as")) {
				userfound = true;
				break;
			}
		}
		
		driver.findElement(By.id("userNavigationLabel")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span//form[@method='post']")));
		WebElement ele = driver.findElement(By.xpath("//span//form[@method='post']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='websubmit']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@role='button' and contains(text(), 'Create New Account')]")));
		
		AssertJUnit.assertTrue(userfound);
	}

	@AfterClass
	public void afterClass() {
		
	}
	
	public static void sleep(int n) {
		try {
			Thread.sleep(n*1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
