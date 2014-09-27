package com.saucelabs.stepdefs;

import com.saucelabs.interactions.Navigation;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NavigationStepDefs {
	
	private final Navigation navigation;
	
	public NavigationStepDefs(Navigation navigation) {
		super();
		this.navigation = navigation;
	}

	@When("^I navigate to \'(.*)\'")
	public void i_navigate(String destination) throws Throwable {
	   this.navigation.navigateTo(destination);
	}

	@Then("^I see the right title$")
	public void i_see_the_right_title() throws Throwable {
	    this.navigation.pageTitleIsCorrect();
	}
}
