package modules;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class UserLogin extends TestNgHelper
{
	Set_Read_ProperpertiesFile sRPF;
	public static String loggedInUserName = "";
	public static String managerNameOfLoggedInUser = "";

	public UserLogin()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
	}
	
	public void goToUserWelcomeHome(String username, String password)throws Exception
	{
		loginAsUser(username, password);
		Assert.assertTrue(isTextPresent("Welcome to Travel Expense System"));
		getLoggedInUserName();
		getManagerNameOfLoggedInUser();
		Assert.assertTrue(isTextPresent("Welcome "+loggedInUserName));
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "Home");
		//log("Verified: After User Login URL Navigated to Welcome Home page");
	}//end of goToUserWelcomeHome
	
	public void loginAsUser(String username, String password) throws Exception
	{
		if (isTextPresent("Login")) {
			webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).sendKeys(username);
			webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtPassword"))).sendKeys(password);
			webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))).click();
		}
	}
	
	public void userLogout() throws IOException
	{
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("Home_Logout"))).click();
		waitForCondition(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin")));
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "Default");
		log("Verified: Logged in User Logout successfuly, URL nevigated to 'Default' page.");
	}
	
	public void getLoggedInUserName(){
		loggedInUserName = webDriver.findElement(By.xpath(sRPF.userLogin_Xpath.get("Home_UserNameLevel"))).getText();
		loggedInUserName = loggedInUserName.substring(8, loggedInUserName.indexOf("(")-1);
	}
	
	public void getManagerNameOfLoggedInUser(){
		managerNameOfLoggedInUser = webDriver.findElement(By.xpath(sRPF.userLogin_Xpath.get("Home_UserNameLevel"))).getText();
		managerNameOfLoggedInUser = managerNameOfLoggedInUser.substring(managerNameOfLoggedInUser.indexOf(":")+2, managerNameOfLoggedInUser.indexOf(")")-1);
	}
}
