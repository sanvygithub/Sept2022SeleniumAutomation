package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager OptionsManager;

	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		// String browserName = System.getProperty("browser");
		System.out.println("Browser Name is:" + browserName);

		highlight = prop.getProperty("highlight");

		OptionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(OptionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("Edge")) {
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
		} else {
			System.out.println("pls pass the right browser:" + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		System.out.println("------->Running test cases on environment----->" + envName);
		if (envName == null) {
			System.out.println("No env is given..hence running on QA env");

			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else

		{
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * take screenshot
	 * 
	 * @param methodName
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
