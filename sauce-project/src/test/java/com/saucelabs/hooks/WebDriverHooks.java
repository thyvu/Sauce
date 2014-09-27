package com.saucelabs.hooks;

import java.net.URL;
import java.util.LinkedList;

import org.junit.Rule;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.context.TestContext;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import com.saucelabs.selenium.client.factory.SeleniumFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class WebDriverHooks implements SauceOnDemandSessionIdProvider {
	private TestContext testContext;

	/**
	 * Constructs a {@link SauceOnDemandAuthentication} instance using the
	 * supplied user name/access key. To use the authentication supplied by
	 * environment variables or from an external file, use the no-arg
	 * {@link SauceOnDemandAuthentication} constructor.
	 */
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("thyvu", "bf9700a5-0cd2-482b-b59f-f2cd7ca4dc13");

	/**
	 * JUnit Rule which will mark the Sauce Job as passed/failed when the test
	 * succeeds or fails.
	 */
	@Rule
	public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	/**
	 * Instance variable which contains the Sauce Job Id.
	 */
	private String sessionId;

	public WebDriverHooks(final TestContext testContext) {
		this.testContext = testContext;
	}

	/**
	 * Constructs a new {@link RemoteWebDriver} instance which is configured to
	 * use the capabilities defined by the {@link #browser}, {@link #version}
	 * and {@link #os} instance variables, and which is configured to run
	 * against ondemand.saucelabs.com, using the username and access key
	 * populated by the {@link #authentication} instance.
	 *
	 * @throws Exception
	 *             if an error occurs during the creation of the
	 *             {@link RemoteWebDriver} instance.
	 */

	@Before
	public void before() throws Exception {
		if ("true".equals(System.getProperty("localDriver"))) {
			this.testContext.setWebDriver(new FirefoxDriver());
		}

		else {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("webDriver.browser"));
			capabilities.setCapability(CapabilityType.VERSION, System.getProperty("webDriver.version"));
			capabilities.setCapability(CapabilityType.PLATFORM, System.getProperty("webDriver.os"));
			capabilities.setCapability("name", "Sauce Sample Test");
			this.testContext.setWebDriver(SeleniumFactory.createWebDriver());
			this.sessionId = (((RemoteWebDriver) this.testContext.getWebDriver()).getSessionId()).toString();
		}
	}

	@After
	public void after() {
		this.testContext.getWebDriver().close();
	}

	/**
	 *
	 * @return the value of the Sauce Job id.
	 */
	@Override
	public String getSessionId() {
		return sessionId;
	}
}
