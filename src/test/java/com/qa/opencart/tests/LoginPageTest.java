package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Epic - 100: Design Login page for open cart shopping application")
@Story("US - 101: Create login page functionality for open cart login page")
public class LoginPageTest extends BaseTest {

	@Description("login Page Title Test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Title of the page is:" + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
	}

	@Description("login Page Url Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println("Url of the page is:" + actUrl);
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Description("Checking forgot pasword link exists")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void forgetPwdlinkExistTest() {
		Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}

	@Description("User is able to login on login page")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExists(), AppErrors.LOGIN_UNSUCCESSFUL);
	}

}
