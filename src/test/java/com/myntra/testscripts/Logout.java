package com.myntra.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.myntra.driverscript.Driverscript;
import com.myntra.utility.Objectmap;

public class Logout extends Driverscript {
	static Actions action;

	public static String logout(WebDriver oBrowser) {
		try {
//			Actions action = new Actions(oBrowser);
//			WebElement profile = oBrowser.findElement(By.xpath("//span[@class='desktop-userTitle']"));
//
//			action.moveToElement(profile).perform();
//
//			WebElement logut = oBrowser.findElement(By.xpath("//div[text()=' Logout ']"));
//			action.moveToElement(logut).build().perform();
			
			log.info("The execution ofthe method logput has started here");
			ExpectedCondition<Boolean> expectation=new
					ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
				}
					};
					
		try {
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(oBrowser, 30); 
			wait.until(expectation);
		}catch(Throwable error)
		{Assert.fail("Timeout waiting for page load Request to complete. ");
		}
					
			
			
			action=new Actions(oBrowser);
			
			//WebDriverWait wait = new WebDriverWait(oBrowser, 30);
//		     WebElement profile1 = Objectmap.getWebelement("Profile");
			WebElement profile1 = oBrowser.findElement(By.xpath("//span[text()='Profile']"));
			 WebElement Logoutbutton = oBrowser.findElement(By.xpath("//div[text()=' Logout ']"));
		      
		      action.moveToElement(profile1).moveToElement(Logoutbutton).build().perform();
		     
		      Logoutbutton.click();
			log.info("The execution of the Method logout has ended here");

		} catch (Exception e) {
			log.error("There is an exception arised during execution of  logout method , the exception"+e);
			return "fail";
		}
		return "pass";

	}

}
