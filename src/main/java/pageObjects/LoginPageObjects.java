package pageObjects;


import org.openqa.selenium.By;

import testBase.DriverFactory;
import testBase.TestBase;

public class LoginPageObjects extends TestBase  {

	By EMAIL = By.name("login[email]");
	By PASSWORD = By.name("login[password]");
	By LOGIN_BTN = By.xpath("//button[@type='submit' and text()='Login ']");


	//login to App
	public void login(String email, String password) {
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(EMAIL), "LoginEmailFIeld", email);
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(PASSWORD), "LoginPasswordFIeld", password);

		click_custom(DriverFactory.getInstance().getDriver().findElement(LOGIN_BTN), "LoginButton");

	}


}


