package testBase;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import reusableComponents.ActionEngine;
import reusableComponents.PropertiesOperations;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class TestBase extends ActionEngine {
	BrowserFactory bf = new BrowserFactory();

	@BeforeMethod
	public void LaunchApplication() throws Exception {
		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = 	PropertiesOperations.getPropertyValueByKey("url");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(url);

	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}

}
