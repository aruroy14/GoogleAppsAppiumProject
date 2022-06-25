package com.googleapps;

import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class GoogleTasksApp {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;
  @Test
  public void createTasks() throws InterruptedException {
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  if (driver.findElementById("com.google.android.apps.tasks:id/welcome_get_started").isDisplayed())
	  {
		  driver.findElementById("com.google.android.apps.tasks:id/welcome_get_started").click();
	  }
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Create new task\"]").click();
	  driver.findElementByXPath("//android.widget.EditText[@text='New task']").sendKeys("Complete Activity with Google Tasks");
	  MobileElement saveButton = driver.findElementById("com.google.android.apps.tasks:id/add_task_done");
	  wait.until(ExpectedConditions.elementToBeClickable(saveButton));
	  saveButton.click();
	  
	  driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Create new task\"]").click();
	  driver.findElementByXPath("//android.widget.EditText[@text='New task']").sendKeys("Complete Activity with Google Keep");
	  wait.until(ExpectedConditions.elementToBeClickable(saveButton));
	  saveButton.click();
	  
	  driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Create new task\"]").click();
	  driver.findElementByXPath("//android.widget.EditText[@text='New task']").sendKeys("Complete the second Activity with Google Keep");
	  wait.until(ExpectedConditions.elementToBeClickable(saveButton));
	  saveButton.click();
	  
	  Thread.sleep(5000);
	  
	  List <MobileElement> taskList = driver.findElementsByXPath("//android.widget.TextView[@resource-id='com.google.android.apps.tasks:id/task_name']");
	  List<String> actualList = new ArrayList<String>();
	  for(MobileElement tasks : taskList)
	  {
		 // System.out.println(tasks.getText());
		  actualList.add(tasks.getText());
		 
	  }
	  
	  List<String> expectedList = new ArrayList<String>();
	  
	  expectedList.add("Complete the second Activity with Google Keep");
	  expectedList.add("Complete Activity with Google Keep");
	  expectedList.add("Complete Activity with Google Tasks");
	  
	  System.out.println("Expected list: "+expectedList);
	  System.out.println("Actual list: "+actualList);
	  Assert.assertEquals(actualList,expectedList);
  }
  @BeforeClass
  public void setUp() throws MalformedURLException {
	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setCapability("deviceName", "PixelEmulator");
	  caps.setCapability("platformName", "android");  
	  caps.setCapability("automationName", "UiAutomator2"); 
	  caps.setCapability("appPackage","com.google.android.apps.tasks");
	  caps.setCapability("appActivity",".ui.TaskListsActivity");
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
