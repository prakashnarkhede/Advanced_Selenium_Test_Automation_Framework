package pageObjects;

import org.openqa.selenium.By;

public class UsersPageObjects {
	
	By btn_addUser = By.xpath("//button[text()='Add User']");
	By field_Search = By.id("search_menu");
	By txt_Search = By.xpath("//*[@id='search_menu']//input[@name='search[keywords]']");
	By btn_Search = By.xpath("//*[@id='search_menu']//input[@type='submit']");
	By dd_group = By.id("users_users_group_id");
	By txt_FullName = By.name("users[name]");
	By txt_Password = By.name("users[password]");
	By txt_Email = By.name("users[email]");
	By txt_Phone = By.name("extra_fields[9]");
	By btn_UserPhoto = By.id("users_photo");
	By btn_Save = By.id("submit_button");
	By chk_notifyUser = By.id("users_notify");

}
