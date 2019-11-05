package com.myntra.testscripts;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.myntra.driverscript.Driverscript;
import com.myntra.utility.Objectmap;

public class Login_logout extends Driverscript {
	public static Actions action;

	public static String Login(WebDriver oBrowser) {

		try {
			
			log.info("The  Execution of Login has started here...");
			Pattern testdatapattern = Pattern.compile(",");
			String arrTestData[] = testdatapattern.split(testDataColumn);
			String email = datatable.getCellData(testScriptExcelFile, "testdata", arrTestData[0], 2).trim();
			String password = datatable.getCellData(testScriptExcelFile, "testdata", arrTestData[1], 2).trim();

			if (Objectmap.isWebElementPresent("loginProfile", "Profile")) {
				action = new Actions(oBrowser);
				action.moveToElement(Objectmap.getWebelement("loginProfile")).build().perform();
				Objectmap.clickOnWebElement("loginbuttonclick", "Login");
			}

			
			if(Objectmap.isWebElementPresent("usernamemail", "Email")) {
				Objectmap.SetValueToWebElement("usernamemail", "Email", email);				
			}
           if(Objectmap.isWebElementPresent("userpassword", "Password")) {
				Objectmap.SetValueToWebElement("userpassword", "Password", password);				
           }
           if(Objectmap.isWebElementPresent("loginbtnclick", "Login")) {
        	   Objectmap.clickOnWebElement("loginbtnclick", "Login");				
			}

		} catch (Exception e) {
			e.printStackTrace();

			return "fail";

		}
		return "Pass";

	}

}
