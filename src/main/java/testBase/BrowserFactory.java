/**
 * Author: Prakash Narkhede
 * Blog: www.AutomationTalks.com
 * LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	//create webdriver object for given browser
	public WebDriver createBrowserInstance(String browser) {
		
		WebDriver driver = null;
		
		if(browser.equalsIgnoreCase("Chrome")) {
			
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			
		}else if (browser.equalsIgnoreCase("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.addArguments("-private");
			driver = new FirefoxDriver(foptions);
			
		} if (browser.equalsIgnoreCase("ie")) {
			
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions iOptions = new InternetExplorerOptions();
			iOptions.addCommandSwitches("-private");
			
			driver = new InternetExplorerDriver(iOptions);
		}
		return driver;
	}
	

}
