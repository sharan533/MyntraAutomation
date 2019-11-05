package com.myntra.testscripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.myntra.driverscript.Driverscript;
import com.myntra.utility.Objectmap;

public class Wishlist extends Driverscript {

	public static WebDriverWait wait;
	public static String ProductName;
	public static Actions action;

	public static String addToWishList(WebDriver oBrowser) {

		try {

			log.info("execution of addToWishList method started.. ");
			
			
			
			Actions action = new Actions(oBrowser);
			WebDriverWait wait = new WebDriverWait(oBrowser, 10);
			WebElement search = Objectmap.getWebelement("searchtext");
			wait.until(ExpectedConditions.visibilityOf(search));
			search.sendKeys("nike");
			Objectmap.getWebelement("brandselect").click();
			Thread.sleep(3000);
			WebElement img1 =Objectmap.getWebelement("hoveronbrand");
			action.moveToElement(img1).perform();
			Objectmap.getWebelement("addtobag").click();
		    Objectmap.getWebelement("productsize").click();
		    Objectmap.getWebelement("bag").click();
		    WebElement movewishlst=Objectmap.getWebelement("movetowishlist");
		    wait.until(ExpectedConditions.visibilityOf(movewishlst));
		    movewishlst.click();
		    Objectmap.getWebelement("additemfromwishlist").click();
		    Objectmap.getWebelement("movetobag").click();
		    Objectmap.getWebelement("done").click();
		    
		    Objectmap.getWebelement("gotobag").click();
		    
            Objectmap.getWebelement("placeorder").click();
            Objectmap.getWebelement("addnewaddress").click();
            
		    

//			action = new Actions(oBrowser);
//			wait = new WebDriverWait(oBrowser, 30);
//			WebElement searchBar = Objectmap.getWebelement("SearchBar");
//			wait.until(ExpectedConditions.visibilityOf(searchBar));
//			searchBar.sendKeys("Nike shoes");
//			Objectmap.getWebelement("SearchButton").click();
//			WebElement product = Objectmap.getWebelement("Firstproduct");
//			action.moveToElement(product).perform();
//			Objectmap.getWebelement("WishList").click();
//			Thread.sleep(3000);
//			WebElement textMessage = Objectmap.getWebelement("Notification");
//			wait.until(ExpectedConditions.visibilityOf(textMessage));
//			String Message = textMessage.getText();
//			Assert.assertEquals(Message, "Added to wishlist", "Notification Message not matched");
//			log.info("execution of addToWishList method successfully ended.. ");

		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}

	public static String moveWishListToBag(WebDriver obrowser) {

		try {

			log.info("execution of moveWishListToBag method started.. ");
			Objectmap.getWebelement("WishListImage").click();
			ProductName = Objectmap.getWebelement("FirstProoductName").getText();
			System.out.println(ProductName);
			Objectmap.getWebelement("MoveToBag").click();
			Objectmap.getWebelement("wishListSizeButton").click();
			Objectmap.getWebelement("DoneButton").click();
			Objectmap.getWebelement("BagIcon").click();
			log.info("execution of moveWishListToBag method successfully ended.. ");

		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}

	public static String placeOrder(WebDriver obrowser) {

		try {
			log.info(" execution of placeOrder method started..");
			WebElement placeorder = Objectmap.getWebelement("PlaceOrder");
			wait.until(ExpectedConditions.visibilityOf(placeorder)).click();
			String Name = config.getProperty("Name");
			String MobileNo = config.getProperty("MobileNo");
			String Pincode = config.getProperty("Pincode");
			String Address_Area = config.getProperty("Address_Area");

			WebElement frame = Objectmap.getWebelement("iFrame");
			obrowser.switchTo().frame(frame);
			obrowser.switchTo().frame(1);
			obrowser.switchTo().frame(2);

			WebElement name = Objectmap.getWebelement("Name");
			name.click();
			name.sendKeys(Name);

			WebElement mobileNo = Objectmap.getWebelement("MobileNo");
			mobileNo.click();
			mobileNo.sendKeys(MobileNo);

			WebElement pincode = Objectmap.getWebelement("Pincode");
			pincode.click();
			pincode.sendKeys(Pincode);

			WebElement address_Area = Objectmap.getWebelement("Address_Area");
			address_Area.click();
			address_Area.sendKeys(Address_Area);

			Objectmap.getWebelement("Locality").click();
			WebElement villagename = Objectmap.getWebelement("VillageName");
			JavascriptExecutor js = (JavascriptExecutor) obrowser;
			js.executeScript("aurguments[0].ScrollIntoView(true)", villagename);
			villagename.click();
			Thread.sleep(5000);
			WebElement MyntraLogo = Objectmap.getWebelement("MyntraLogo");
			wait.until(ExpectedConditions.visibilityOf(MyntraLogo)).click();

			log.info("execution of placeOrder method ended.. ");
		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}

}
