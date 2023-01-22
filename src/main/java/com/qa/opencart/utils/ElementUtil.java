package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
			
		}
		return ele;
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();

	}

	public String doGetAttribute(By locator, String AttributeName) {
		return getElement(locator).getAttribute(AttributeName);
	}

	public void doClickElement(By Locator, String Text) {

		List<WebElement> tot_links = driver.findElements(Locator);
		System.out.println(tot_links.size());
		for (WebElement e : tot_links) {
			String Link = e.getText();
			System.out.println(Link);
			if (Link.contains(Text)) {
				e.click();
			}
		}
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> footerList = getElements(locator);
		List<String> eleText = new ArrayList<String>();

		for (WebElement e : footerList) {
			String Text = e.getText();
			// System.out.println(Text);
			eleText.add(Text);

		}
		return eleText;

	}

	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();

	}

	public boolean isSingleEleExists(By locator) {
		int countEleList = getElements(locator).size();
		System.out.println("Count of elements:" + countEleList);
		if (countEleList == 1) {
			return true;
		}
		return false;

	}

	public boolean isTwoEleExists(By locator) {
		int countEleList = getElements(locator).size();
		System.out.println("Count of elements:" + countEleList);
		if (countEleList == 2) {
			return true;
		}
		return false;

	}

	public boolean isTwoEleExists(By locator, int knownvalue) {
		int countEleList = getElements(locator).size();
		System.out.println("Count of elements:" + countEleList);
		if (countEleList == knownvalue) {
			return true;
		}
		return false;

	}

	public boolean isMultiEleExists(By locator) {
		int countEleList = getElements(locator).size();
		System.out.println("Count of elements:" + countEleList);
		if (countEleList > 2) {
			return true;
		}
		return false;

	}

	// * Drop down utils--select Based

	public void doSelectByIndex(By locator, int value) {
		Select e = new Select(getElement(locator));
		e.selectByIndex(value);
	}

	public void doSelectByVisibleText(By locator, String value) {
		Select e = new Select(getElement(locator));
		e.selectByVisibleText(value);
	}

	public void doSelectByValue(By locator, String Text) {
		Select e = new Select(getElement(locator));
		e.selectByValue(Text);

	}

	public void doGetOptions(By locator, String value) {
		WebElement S = getElement(locator);
		Select e = new Select(S);
		List<WebElement> options = e.getOptions();
		System.out.println(options.size());
		int Counter = 0;
		for (WebElement k : options) {
			String optionsList = k.getText();
			System.out.println(Counter + "=" + optionsList);
			if (optionsList.equals(value)) {
				k.click();
				break;
			}
			Counter++;
		}

	}

	// *************Actions Methods(Sendkeys)**********************************//

	public void doActionSendKeys(By Locator, String Value) {

		Actions Act = new Actions(driver);
		Act.sendKeys(getElement(Locator), Value).build().perform();
	}

	public void doActionsClick(By Locator) {
		Actions Act = new Actions(driver);
		Act.click(getElement(Locator)).build().perform();

	}

	// ************* Explicitly wait Util methods ****************//
// Title
	public String waitForTitleContains(String FractionValue, int Timeunit) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeunit));
		if (wait.until(ExpectedConditions.titleContains(FractionValue))) {
			return driver.getTitle();
		} else {
			System.out.println("Expected Title is not present");
			return null;
		}

	}

	public String waitForTitleIs(String titleVal, int Timeunit) { // Exact title we need to provide

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeunit));
		if (wait.until(ExpectedConditions.titleIs(titleVal))) {
			return driver.getTitle();
		} else {
			System.out.println("Expected Title is not present");
			return null;
		}

	}

//Url
	public String waitForUrlContains(String FractionValue, int Timeunit) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeunit));
		if (wait.until(ExpectedConditions.urlContains(FractionValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("Expected Url is not present");
			return null;
		}

	}

	public String waitForUrlToBe(String UrlValue, int Timeunit) { // Exact url value need to be given

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeunit));
		if (wait.until(ExpectedConditions.urlToBe(UrlValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("Expected Url is not present");
			return null;
		}

	}

	// Alerts
//WebDriverWait
	public Alert waitAlertIsPresentAndSwitch(int TimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

//FluentWait
	public Alert waitAlertIsPresentAndSwitch(int TimeUnit, int IntervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeUnit))
				.pollingEvery(Duration.ofSeconds(IntervalTime)).ignoring(NoAlertPresentException.class)
				.withMessage("Alert not found");
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForAlertAndGetText(int TimeUnit) {
		waitAlertIsPresentAndSwitch(TimeUnit).getText();
	}

	public void waitForAlertAndAccept(int TimeUnit) {
		waitAlertIsPresentAndSwitch(TimeUnit).accept();
	}

	public void waitForAlertAndDismiss(int TimeUnit) {
		waitAlertIsPresentAndSwitch(TimeUnit).dismiss();
	}

	public void waitForAlertAndSendKeys(int TimeUnit, String Value) {
		waitAlertIsPresentAndSwitch(TimeUnit).sendKeys(Value);
	}

	// Frame
	public void waitForFrameAndSwitch(int ID, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ID));

	}

	public void waitForFrameAndSwitch(By locator, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	public void waitForFrameAndSwitch(String nameOrId, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));

	}

	public void waitForFrameAndSwitch(WebElement frameElement, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}
	// List of WebElements

	public List<WebElement> waitForPresenceOfAllWebElements(By locator, int TimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public List<WebElement> waitForVisibilityeOfAllWebElements(By locator, int TimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param TimeUnit
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int TimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param TimeUnit
	 * @return
	 */

	public WebElement waitForElementVisible(By locator, int TimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param TimeUnit
	 * @param intervalTime
	 * @return
	 */

	public WebElement waitForElementVisible(By locator, int TimeUnit, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * 
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param TimeUnit
	 */

	public void waitForClickableElementAndClick(By locator, int TimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	/**
	 * 
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param TimeUnit
	 * @param intervalTimeUnit
	 */

	public void waitForClickableElementAndClick(By locator, int TimeUnit, int IntervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit), Duration.ofSeconds(IntervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}
	// Fluent Wait

	public void waitForElementToBeVisibleWithFluentWait(By Locator, int TimeUnit, int IntervalTimeUnit) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeUnit))
				.pollingEvery(Duration.ofSeconds(IntervalTimeUnit)).ignoring(ElementNotInteractableException.class)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage("Element not found");

		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).click();

	}

	// WebDriver with FluentWait methods
	public void waitForElementToBeVisibleWithWebDriverWait(By Locator, int TimeUnit, int IntervalTimeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeUnit),
				Duration.ofSeconds(IntervalTimeUnit));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.withMessage("Element NOt found.....");

		wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
	}

	// **********************Custom Wait****************************//
	public WebElement retryingElement(By Locator, int timeUnit) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeUnit) {
			try {
				element = getElement(Locator);
				System.out.println("Element found in : " + attempts);
				break;

			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : " + attempts + " for " + Locator);
				TimeUtil.applyWait(500);
			}
			attempts++;
		}
		if (element == null) {
			System.out.println("element is not found....tried for : " + timeUnit + " secs "
					+ " with the interval of 500 millisecs");
			//throw new FrameworkException("TimeOutException");
		}
		return element;

	}

	public WebElement retryingElement(By Locator, int timeUnit, int IntervalTime) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeUnit) {
			try {
				element = getElement(Locator);
				System.out.println("Element found in : " + attempts);
				break;

			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : " + attempts + " for " + Locator);

				TimeUtil.applyWait(IntervalTime);

			}
			attempts++;
		}
		if (element == null) {
			System.out.println("element is not found....tried for : " + timeUnit + " secs " + " with the interval of "
					+ IntervalTime + " secs");
			//throw new FrameworkException("TimeOutException");
		}
		return element;

	}

	// wait for pageloading
	public void waitForPageLoad(int TimeOut) {

		long endTime = System.currentTimeMillis() + TimeOut;
		while (System.currentTimeMillis() < endTime) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String PageState = js.executeScript(" return document.readyState").toString();
			if (PageState.equals("complete")) {
				System.out.println("Page is fully loaded");
				break;
			}
		}

	}

}
