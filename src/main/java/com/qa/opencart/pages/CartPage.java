package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {
	
	
	By cart=By.id("MacBook");
	
	public CartPage() {
		
	}
	
	public void addToCart() {
		System.out.println("adding to cart page");
		System.out.println("cart page added");
	}

}
