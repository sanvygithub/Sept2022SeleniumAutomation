package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	ElementUtil eleUtil;

	private WebDriver driver;

	// 1.Private By Locators

	By emailId = By.id("input-email");
	By password = By.id("input-password");
	By loginBtn = By.xpath("//input[@value='Login']");
	By forgetPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2.page constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3.Page Actions
	@Step("getting login page title...")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}

	@Step("getting login page Url...")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	}

	@Step("checking for Forgot pwd link")
	public boolean isForgetPwdLinkExist() {
		return eleUtil.isDisplayed(forgetPwdLink);
	}

	@Step("login with username:{0} and password :{1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Creds are:" + un + ":" + pwd);

		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("navigating to registration page")
	public RegPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}

}
