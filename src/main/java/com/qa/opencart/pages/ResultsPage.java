package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {

	WebDriver driver;
	ElementUtil eleutil;

	private By product = By.cssSelector("div.product-layout ");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}

	public String getSearchpageTitle(String productName) {
		return eleutil.waitForTitleContains(productName, TimeUtil.DEFAULT_TIME_OUT);
	}

	public int getSearchProductCount() {
		int productCount = eleutil.waitForVisibilityeOfAllWebElements(product, TimeUtil.DEFAULT_TIME_OUT).size();
		System.out.println("The product count is:" + productCount);
		return productCount;

	}

	public ProductInfoPage selectProduct(String mainProduct) {
		System.out.println("The product name is:" + mainProduct);
		eleutil.doClick(By.linkText(mainProduct));
		return new ProductInfoPage(driver);
	}
}
