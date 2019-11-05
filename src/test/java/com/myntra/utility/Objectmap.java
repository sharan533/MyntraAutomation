 package com.myntra.utility;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.myntra.driverscript.Driverscript;

public class Objectmap extends Driverscript 
{

	public static WebElement getWebelement(String logicalname) 
	{
		String locator = objectMap.getProperty(logicalname);
		String arrlocator[] = locator.split(">");
		String locatorName = arrlocator[0];
        String locatorValue = arrlocator[1];

		WebElement webelement = null;
		try {
			if (locatorName.equalsIgnoreCase("xpath")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue));
			}

			else if (locatorName.equalsIgnoreCase("id")) {
				webelement = oBrowser.findElement(By.id(locatorValue));
			}

			else if (locatorName.equalsIgnoreCase("name")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue));
			}

			else if (locatorName.equalsIgnoreCase("cssSelector")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue));
			} else if (locatorName.equalsIgnoreCase("linkText")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue));
			} else if (locatorName.equalsIgnoreCase("tagName")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue));
			} else if (locatorName.equalsIgnoreCase("partialLinkText")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue));
			} else if (locatorName.equalsIgnoreCase("className")) {
				webelement = oBrowser.findElement(By.xpath(locatorValue)); 
			}
		}

		catch (NoSuchElementException e) 
		{
			e.printStackTrace();
			log.error(locatorName + " Element not found ");
			Assert.fail(locatorName + " Element not found ");

		}
		return webelement;

    }

   
	public static void clickOnWebElement (String locator, String name){

		try{

		if(getWebelement(locator).isEnabled()){

		getWebelement(locator).click();
		log.info("Clicked on "+"'"+name+"'"+ " button");

		}else{

		log.error("'"+name+"'" + " : Button is not enabled");
		Assert.fail("'"+name+"'" + " : Button is not enabled");
		}

		}catch(NoSuchElementException e){

		e.printStackTrace();
		log.error(locator + " : Button not found");
		Assert.fail(locator + " : Button not found");

		}catch(ElementNotVisibleException e){

		e.printStackTrace();
		log.error(locator + " : Button not visible");
		Assert.fail(locator + " : Button not visible");

		}
		
		}

		public static boolean isWebElementPresent(String locator, String name){

		boolean flag=false;
		try{

		if(getWebelement(locator).isDisplayed()){

		log.info("Element "+"'"+name+"'"+ " is present");
		flag=true;

		}else{
		log.error("'"+name+"'" + " : Element is not present");
		Assert.fail("'"+name+"'" + " : Element is not present");
		flag=false;
		}

		}catch(NoSuchElementException e){

		e.printStackTrace();
		log.error(locator + " : Element not found");
		Assert.fail(locator + " : Element not found");
		flag=false;

		}catch(ElementNotVisibleException e){

		e.printStackTrace();
		log.error(locator + " : Element not visible");
		Assert.fail(locator + " : Element not visible");
		flag=false;

		}
		
		return flag;
		}
		
		
		
		
		
		
		public static void SetValueToWebElement(String locator, String name,  String testdata)
		{
		try
		{
			if(getWebelement(locator).isEnabled()) {
				getWebelement(locator).sendKeys(testdata);
				log.info("Entered"+ testdata+ " in "+"'" + " field");
				
			}else
			{log.error("'" +name+"'"+": Field is not present");
			Assert.fail("'" +name+"'"+": Field is not present");	
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			log.error(locator + " : Field not found");
			Assert.fail(locator + " : Field not found");
		}catch(ElementNotVisibleException e)
		{
			e.printStackTrace();
			log.error(locator + " : Field not visible");
		    Assert.fail(locator + " : Field not visible");
		
		
		}
			
	
		}



}


