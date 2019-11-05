package com.myntra.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myntra.driverscript.Driverscript;
import com.myntra.utility.Objectmap;

public class Cart extends Driverscript 
{

	public static String AddToBag(WebDriver oBrowser)
	{
		log.info("The Execution of AddToBag is started here");

		try {
			Actions action = new Actions(oBrowser);
			WebDriverWait wait = new WebDriverWait(oBrowser, 10);
			WebElement search = Objectmap.getWebelement("searchtext");
			wait.until(ExpectedConditions.visibilityOf(search)).sendKeys("nike");
			Objectmap.getWebelement("brandselect").click();
			Thread.sleep(3000);
			WebElement img1 =Objectmap.getWebelement("hoveronbrand");
			action.moveToElement(img1).perform();
			Objectmap.getWebelement("addtobag").click();
		    Objectmap.getWebelement("productsize").click();
            }
	  catch (Exception e) 
		    {
			e.printStackTrace();
			return "fail";
			}
		
	
	return "pass";

	}
	
   public static String RemoveFromBag(WebDriver oBrowser) 
   {
	   log.info("The Execution of RemoveFromBag is started here");
	   try
	   {
		   Thread.sleep(3000);
		Objectmap.getWebelement("bag").click();
		Objectmap.getWebelement("removebutton").click();
		Objectmap.getWebelement("removeitem").click();
		Thread.sleep(5000);
		
		log.info("The item is Removed From the Bag  successfully!!!");
		Objectmap.getWebelement("logo");
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return "fail";
	   }
	 
	  return "pass";
	 
 }



}

	


