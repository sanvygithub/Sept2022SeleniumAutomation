package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By productdetails = By.cssSelector("div#content h1");

	private By productImages = By.cssSelector("a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By priceMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return eleutil.doGetElementText(productdetails);
	}

	public int getProductImagesCount() {
		int ImagesCount = eleutil.waitForVisibilityeOfAllWebElements(productImages, TimeUtil.DEFAULT_TIME_OUT).size();
		System.out.println("The product Images count is:" + ImagesCount);
		return ImagesCount;
	}

	public Map<String, String> getProductMap() {
		productMap = new HashMap<String, String>();
		getProductMetaData();
		getPriceMetaData();
		System.out.println(productMap);
		return productMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleutil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String Text = e.getText();
			String MetaData[] = Text.split(":");
			String MetaKey = MetaData[0].trim();
			String MetaValue = MetaData[1].trim();
			productMap.put(MetaKey, MetaValue);
		}
	}

	// $2,000.00 //0
	// Ex Tax: $2,000.00 //1
	private void getPriceMetaData() {
		List<WebElement> metaDatapriceList = eleutil.getElements(priceMetaData);
		String price = metaDatapriceList.get(0).getText().trim();
		String ExTaxprice = metaDatapriceList.get(1).getText().trim();
		productMap.put("actualPrice", price);
		productMap.put("ExTax", ExTaxprice);

	}

}
