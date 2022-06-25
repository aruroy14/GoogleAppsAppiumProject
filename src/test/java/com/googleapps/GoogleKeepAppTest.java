package com.googleapps;

import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class GoogleKeepAppTest {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;
  @Test
  public void googleKeepTest() {
//	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	  if (driver.findElementById("com.google.android.apps.tasks:id/welcome_get_started").isDisplayed())
//	  {
//		  driver.findElementById("com.google.android.apps.tasks:id/welcome_get_started").click();
//	  }
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  String expectedTitle = "TitleOfNote";
	  driver.findElementByAccessibilityId("New text note").click();
	  driver.findElementById("com.google.android.keep:id/editable_title").sendKeys(expectedTitle);
	  driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("Adding note for self");
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  String actualTitle = driver.findElementByXPath("//android.widget.TextView[contains(@resource-id,'com.google.android.keep:id/index_note_title')]").getText();
	  
	  System.out.println("Expected Title: "+expectedTitle);
	  System.out.println("Actual Title: "+actualTitle);
	  Assert.assertEquals(actualTitle,expectedTitle);
  }
  @BeforeClass
  public void setUp() throws MalformedURLException {
	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setCapability("deviceName", "PixelEmulator");
	  caps.setCapability("platformName", "android");  
	  caps.setCapability("automationName", "UiAutomator2"); 
	  caps.setCapability("appPackage","com.google.android.keep");
	  caps.setCapability("appActivity",".activities.BrowseActivity");
	  caps.setCapability("noreset",true);	 
	  
	  URL appServer = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer,caps);
		wait = new WebDriverWait(driver,10);
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
