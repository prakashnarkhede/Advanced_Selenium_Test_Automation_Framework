package Tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.TaskPageObjects;
import testBase.DriverFactory;
import testBase.TestBase;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class TestCase extends TestBase{
	LoginPageObjects loginPage = new LoginPageObjects();
	HomePageObjects homePage = new HomePageObjects();
	TaskPageObjects taskPage = new TaskPageObjects();
	
	@Test
	public void TestCase1() throws Throwable {
		loginPage.login("admin@admin.com", "admin@123");
		//check if dashboard page opens
		homePage.checkIfDashBoardPageIsOpened();
		//add task
		homePage.clickOnSideSubMenu("Tasks", "Add Task");
		taskPage.createTask();
		taskPage.Search_Verify_TaskCreationOnUI();
		//verify task on UI
		//verify DB
		
		
	}
	
//	@Test
//	public void TestCase2() throws InterruptedException {
//		loginPage.login("admin@admin.com", "admin@1234");
//	}

}
