package com.saucelabs.context;

import org.openqa.selenium.WebDriver;

public class TestContext {
	private WebDriver webDriver;
	
	public TestContext() {
		
	}
	
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	
}
