package com.myntra.testscripts;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.myntra.driverscript.Driverscript;

public class Initialize extends Driverscript {

	public static WebDriver launch() {
		String browserType;
		try {
			log.info("Launching browser");
			browserType = config.getProperty("browsertype");
			//browserType =System.getenv("Browser Type");
			switch (browserType.toLowerCase()) {
			case "firefox":
				log.info("The firefox browser is launched");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Library/drivers/geckodriver.exe");
				oBrowser = new FirefoxDriver();
				break;
			case "chrome":
				log.info("The Chrome browser is launched");
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Library/drivers/chromedriver.exe");
				oBrowser = new ChromeDriver();
				break;

			case "ie":
				log.info("The IE browser is launched");
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "/Library/drivers/IEDriverServer.exe" );
				oBrowser = new InternetExplorerDriver();
				break;

			default:
				System.out.println("Invalid Browser Name !!!!!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oBrowser;
	}

	public static String navigate(WebDriver oBrowser) {
		String url;
		try {
			log.info("the Execution of the Method navigate has started here....");
			url = config.getProperty("url");
			
		Pattern testdataPattern=Pattern.compile(",");
		String arrTestData[]=testdataPattern.split(testDataColumn);
		url=datatable.getCellData(testScriptExcelFile, "testdata", arrTestData[0], 2);
			
		
		oBrowser.get(url);
		oBrowser.manage().window().maximize();
		oBrowser.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		log.info("the Execution of the Method navigate has started here....");
		
		} catch (Exception e) {
			return "fail";

		}
		return "pass";

	}

	public static String closeApplication(WebDriver oBrowser) {
		try {
			oBrowser.close();
		} catch (Exception e) {
			return "fail";

		}

		return "pass";

	}

}
