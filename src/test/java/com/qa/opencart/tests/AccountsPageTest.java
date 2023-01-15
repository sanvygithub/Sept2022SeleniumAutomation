package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass

	public void setUp() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));;
	}

	@Test
	public void acctsPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		System.out.println("the title of the page is:" + actTitle);
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void acctsPageUrlTest() {
		String actUrl = accPage.getAccPageUrl();
		System.out.println("the url of the page is:" + actUrl);
		Assert.assertTrue(actUrl.contains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL));
	}

	@Test
	public void isSearchExistsTest() {
		Assert.assertTrue(accPage.isSearchExists());
	}

	@Test
	public void isLogoutExistsTest() {
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}

	@Test
	public void accPageHeadersListTest() {
		List<String> actHeaders = accPage.acctPageHearders();
		Assert.assertEquals(actHeaders, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@DataProvider

	public Object[][] getProduct() {
		return new Object[][] { 
			{ "macbook" }, { "iMac" }, { "samsung" } 
			};
	}

	@Test(dataProvider = "getProduct")
	public void productSearchPageTest(String productName) {
		resultspage = accPage.performSearch(productName);
		String actTitle = resultspage.getSearchpageTitle(productName);
		System.out.println("The title of the page is:" + actTitle);
		softAssert.assertEquals(actTitle, AppConstants.SEARCH_PAGE_FRACTION_URL + " " + productName);
		Assert.assertTrue(resultspage.getSearchProductCount() > 0);

	}

}
