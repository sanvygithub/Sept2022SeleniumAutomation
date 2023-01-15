package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	ElementUtil eleUtil;

	private WebDriver driver;

	private By search = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']//button[@type='button']");
	private By logoutLink = By.linkText("Logout");
	private By HeadersList = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getAccPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}

	public String getAccPageUrl() {
		return eleUtil.waitForUrlContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	}

	public boolean isSearchExists() {
		return eleUtil.isDisplayed(search);

	}

	public boolean isLogoutLinkExists() {
		return eleUtil.isDisplayed(logoutLink);
	}

	public List<String> acctPageHearders() {
		List<WebElement> secHeadersList = eleUtil.getElements(HeadersList);
		List<String> SecsHeadersValList = new ArrayList<String>();
		for (WebElement e : secHeadersList) {
			String Text = e.getText();
			SecsHeadersValList.add(Text);

		}

		return SecsHeadersValList;

	}

	public ResultsPage performSearch(String productName) {
		if(isSearchExists()) {
			System.out.println("The product name is :"+productName);
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			return new ResultsPage(driver);
			}
		return null;
		
	}

}
