package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider

	public Object[][] getProductTestData() {
		return new Object[][] { {"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""} };
	}

	@Test(dataProvider = "getProductTestData")
	public void getProductHeaderTest(String Searchkey, String ProductName) {
		resultspage = accPage.performSearch(Searchkey);
		prodInfopage = resultspage.selectProduct(ProductName);
		String actHeader = prodInfopage.getProductHeader();
		Assert.assertEquals(actHeader, ProductName);

	}

	@DataProvider

	public Object[][] getProductImagesTestData() {
		return new Object[][] { {"Macbook", "MacBook Pro", 4},
			{"Macbook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
			{"Apple", "Apple Cinema 30\"", 6}  };
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void getProductImagesCount(String Searchkey, String ProductName, int count) {
		resultspage = accPage.performSearch(Searchkey);
		prodInfopage = resultspage.selectProduct(ProductName);
		int actImagesCount = prodInfopage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, count);

	}

	@Test
	public void getProductMetaData() {
		resultspage = accPage.performSearch("Macbook");
		prodInfopage = resultspage.selectProduct("MacBook Air");
		Map<String, String> actProductinfo = prodInfopage.getProductMap();

		softAssert.assertEquals(actProductinfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductinfo.get("Product Code"), "Product 17");
		softAssert.assertEquals(actProductinfo.get("Reward Points"), "700");
		softAssert.assertEquals(actProductinfo.get("Availability"), "In Stock");
		softAssert.assertAll();
	}

}
