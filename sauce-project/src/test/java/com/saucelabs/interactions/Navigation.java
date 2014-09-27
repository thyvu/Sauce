package com.saucelabs.interactions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.saucelabs.context.TestContext;

public class Navigation {
	private static final Map<String, String> MAP_DESTINATION;
	static {
		final HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("amazon", "http://www.amazon.com");
		temp.put("ebay", "http://www.ebay.com");
		MAP_DESTINATION = Collections.unmodifiableMap(temp);
	}
	
	private static final Map<String, String> MAP_TITLES;
	static {
		final HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("amazon", "Amazon");
		temp.put("ebay", "http://www.ebay.com");
		MAP_TITLES = Collections.unmodifiableMap(temp);
	}
	
	private final TestContext testContext;
	private String destination;

	public Navigation(TestContext testContext) {
		this.testContext = testContext;
	}

	public void navigateTo(String destination) {
		this.destination = destination;
		this.testContext.getWebDriver().get(
				MAP_DESTINATION.get(this.destination));

	}

	public void pageTitleIsCorrect() {
		Assert.assertTrue(this.testContext.getWebDriver().getTitle().contains(MAP_TITLES.get(this.destination)));
	}

	public void clickFirstLink() {
		this.testContext.getWebDriver().findElement(By.tagName("a"));
	}

}
