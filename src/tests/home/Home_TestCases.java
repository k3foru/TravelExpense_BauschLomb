package tests.home;

import modules.PreApprovalClaim;
import modules.UserLogin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class Home_TestCases extends TestNgHelper{
	
	private Set_Read_ProperpertiesFile sRPF;
	
	// Check Validation of Log in Module And Verify the Alert over Validation.
	@Test (description="Check Validation of Log in Module And Verify the Alert over Validation.",groups = { "checkAndVarifyLoginModule" })
	public void checkAndVarifyLoginModule() throws Exception
	{
		header("Check Validation of Log in Module And Verify the Alert over Validation.");
		sRPF = new Set_Read_ProperpertiesFile();
		UserLogin userLogin = new UserLogin();
		userLogin.userLogout();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "Default");
		Assert.assertTrue(isTextPresent("Login"));
		log("Verified :  User On Log in Page.'");
		Assert.assertTrue(isElementPresent(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtPassword"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.userLogin_Xpath.get("UserLogin_ddlDomain"))));
		log("Verified :  Domain , User Name, Password and Login Button Present on Page.'");
		// Verify validation without enter any log information.
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))).click();
		Thread.sleep(600);
		Alert alert = webDriver.switchTo().alert();
		log("Verified : click to Login Button without enter any login Information getting Validation Alert :: "+alert.getText());
		alert.accept();
		Thread.sleep(600);
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).sendKeys(sRPF.userLogin_Data.get("username"));
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))).click();
		Thread.sleep(600);
		Alert alert1 = webDriver.switchTo().alert();
		log("Verified : Enter Only User Name and click to Login Button getting Validation Alert for Password :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(600);
		// Enter Wrong User Name And Password and check Validation.
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).clear();
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).sendKeys("test");
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtPassword"))).sendKeys("Tesring");
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))).click();
		Thread.sleep(600);
		Alert alert2 = webDriver.switchTo().alert();
		log("Verified : Enter Wrong User Name And Password and check Validation :: "+alert2.getText());
		alert2.accept();
		Thread.sleep(600);
		// Enter correct User Name And Wrong Password and check Validation.
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).clear();
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).sendKeys(sRPF.userLogin_Data.get("username"));
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtPassword"))).sendKeys("Tesring");
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))).click();
		Thread.sleep(600);
		Alert alert3 = webDriver.switchTo().alert();
		log("Verified : Enter Wrong User Name And Password and check Validation :: "+alert3.getText());
		alert3.accept();
		Thread.sleep(600);
		// Enter correct User Name And Password and Verify User should Navigate to Welcome page.
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).clear();
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtUserName"))).sendKeys(sRPF.userLogin_Data.get("username"));
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_txtPassword"))).sendKeys(sRPF.userLogin_Data.get("password"));
		webDriver.findElement(By.id(sRPF.userLogin_Xpath.get("UserLogin_btnLogin"))).click();
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Entered correct User Name, PasswordAfter and Verified User Navigated to Welcome Home page : Welcome "+UserLogin.loggedInUserName);
	}
	
	// Login as a Normal User, Go to Home page and verify Home page.
	@Test (description="Login as a Normal User, Go to Home page and verify Home page.",groups = { "normalUserLoggedIn_VerifyHomePage" })
	public void normalUserLoggedIn_VerifyHomePage() throws Exception
	{
		header("Login as a Normal User, Go to Home page and verify Home page.");
		sRPF = new Set_Read_ProperpertiesFile();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "Home");
		Assert.assertTrue(isTextPresent("Welcome to Travel Expense System"));
		log("Verified : Page Nevigating to Home page.");
		// Verify in My Approval Grid there is no  list of Request.
		Assert.assertTrue(isTextPresent("Need My Approval List"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.home_Xpath.get("Home_MyApproval_ltlEmptyGrid"))).getText(), "No records found.");
		log("Verified :  in 'My Approval' Grid there is no  list of Request, Message is 'No records found.'");
		Assert.assertTrue(isTextPresent("My Travel Pre-Approval List"));
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		Assert.assertTrue(isTextPresent("My Non-Travel Claim List"));
		log("Verified :  No Home page All three type of Claim Section displaying in Grid.'");
	}
	
	// Login as a Normal User, Go to Home page and verify All Three Grid Data.
	@Test (description="Login as a Normal User, Go to Home page and verify All Three Grid Data.",groups = { "normalUserLoggedIn_VerifyAllThreeSectionGridData" })
	public void normalUserLoggedIn_VerifyAllThreeSectionGridData() throws Exception
	{
		header("Login as a Normal User, Go to Home page and verify All Three Grid Data.");
		sRPF = new Set_Read_ProperpertiesFile();
		// Verify My Travel Pre-Approval List in Grid.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_RequestNo"))).getText(), "Request No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_RequestorName"))).getText(), "Requestor Name");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_TotalExpenseRMB"))).getText(), "Expense Budget (RMB)");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_CreationDate"))).getText(), "Creation Date");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_Status"))).getText(), "Status");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_FileClaim"))).getText(), "File Claim");
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_MoreLink"))));
		log("Verified My Travel Pre-Approval List in Grid :  All Field Displaying in Grid and More link Appearing.'");
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_MyPreApprovalListDashboard1_grdPreApprovalList_grdPreApprovalList_4_lnkViewRequest")));
		log("Verified In My Travel Pre-Approval List in Grid :  Last 5 Request Appearing.'");
		
		// Verify My Travel Claim List  in Grid.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_ClaimNo"))).getText(), "Claim No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_RequestorName"))).getText(), "Requestor Name");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_TotalExpenseRMB"))).getText(), "Total Expense (RMB)");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_CreationDate"))).getText(), "Creation Date");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_Status"))).getText(), "Status");
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_MoreLink"))));
		log("Verified My Travel Claim List in Grid :  All Field Displaying in Grid and More link Appearing.'");
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_MyTravelClaimListDashboard1_grdTE_grdTE_4_lnkClaimNo")));
		log("Verified In My Travel Claim List in Grid :  Last 5 Request Appearing.'");
		
		// Verify My Non-Travel Claim List in Grid.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_ClaimNo"))).getText(), "Claim No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_RequestorName"))).getText(), "Requestor Name");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_TotalExpenseRMB"))).getText(), "Total Expense (RMB)");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_CreationDate"))).getText(), "Creation Date");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_Status"))).getText(), "Status");
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_MoreLink"))));
		log("Verified My Non-Travel Claim List in Grid :  All Field Displaying in Grid and More link Appearing.'");
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_MyNonTravelClaimListDashboard1_grdTE_grdTE_4_lnkClaimNo")));
		log("Verified In My Non-Travel Claim List in Grid :  Last 5 Request Appearing.'");
	}
	
	// Login as User, Submit Request for Pre Approval Claim and Verify on Home page displaying in Grid.
	@Test (description="Login as User, Submit Request for Pre Approval Claim and Verify on Home page displaying in Grid.",groups = { "userLoggedIn_SubmitPreApprovalClaimVerifyOnHomePage" })
	public void userLoggedIn_SubmitPreApprovalClaimVerifyOnHomePage() throws Exception
	{
		header("Login as User, Submit Request for Pre Approval Claim and Verify on Home page displaying in Grid.");
		sRPF = new Set_Read_ProperpertiesFile();
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		preApprovalClaim.fillPreApprovalExpenseForm();
		// Submit the Added request.
		preApprovalClaim.clickToSubmitButton();
		preApprovalClaim.getAddedRequestNo();
		log("Verified: Normal User Added Request Successfuly");
		// Verify Added Request displaying on Home page in Pre Approval Grid with Correct Details.
		webDriver.findElement(By.linkText("Home")).click();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_FirstGridData_RequestNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_FirstGridData_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_FirstGridData_TotalExpenseRMB"))).getText(), PreApprovalClaim.totalSub+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_FirstGridData_CreationDate"))).getText(), PreApprovalClaim.addedDateTime);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_FirstGridData_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		log("Verified: Added Pre Approval Request displaying on Home page in Pre Approval Grid with Correct Details.");
		Assert.assertTrue(isElementPresent(By.linkText(PreApprovalClaim.addedRequestNo)));
		log("Verified: Added Pre Approval Request No. are a link On Home page.");
	}
	
	// Login as Normal User, Verify Normal User Access.
	@Test (description="Login as Normal User, Verify Normal User Access.",groups = { "normalUserLoggedIn_VerifyNormalUserAccess" })
	public void normalUserLoggedIn_VerifyNormalUserAccess() throws Exception
	{
		header("Login as Normal User, Verify Normal User Access.");
		sRPF = new Set_Read_ProperpertiesFile();
		// Verify 'Settled Claims' and 'Unsettled Claims' link not present in Claims tab for Normal User.
		mouseOverElement(By.linkText("Claims"));
		Thread.sleep(200);
		Assert.assertFalse(isElementPresent(By.linkText("Settled Claims")));
		Assert.assertFalse(isElementPresent(By.linkText("Unsettled Claims")));
		log("Verified: 'Settled Claims' and 'Unsettled Claims' link not present in Claims tab for Normal User.");
		// Verify 'Report' Tab not present for Normal User.
		Assert.assertFalse(isElementPresent(By.linkText("Reports")));
		log("Verified: 'Report' Tab not present for Normal User.");
	}
	
	// Login as a Admin User, Go to Home page and verify More Link navigating on Related Page.
	@Test (description="Login as a Admin User, Go to Home page and verify More Link navigating on Related Page.",groups = { "adminUserLoggedIn_VerifyHomePageMoreLinkNevigation" })
	public void adminUserLoggedIn_VerifyHomePageMoreLinkNevigation() throws Exception
	{
		header("Login as a Admin User, Go to Home page and verify More Link navigating on Related Page.");
		sRPF = new Set_Read_ProperpertiesFile();
		UserLogin userLogin = new UserLogin();
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) User.'");
		// Verify More Link in Need My Approval List
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_MyApproval_MoreLink"))));
		webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_MyApproval_MoreLink"))).click();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "MyApprovalsList");
		log("Verified More Link in Need My Approval List : Navigating to 'My Approvals List' Page.'");
		webDriver.navigate().back();
		Thread.sleep(1000);
		// Verify More Link in My Travel Pre-Approval.
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_MoreLink"))));
		webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_PreApproval_MoreLink"))).click();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "PreApprovalsList");
		log("Verified More Link in My Travel Pre-Approval : Navigating to 'Pre Approval List' Page.'");
		webDriver.navigate().back();
		Thread.sleep(1000);
		// Verify More Link in My Travel Claim
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_MoreLink"))));
		webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_TravelClaim_MoreLink"))).click();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpenseList");
		log("Verified More Link in My Travel Claim : Navigating to 'Travel Expense List' Page.'");
		webDriver.navigate().back();
		Thread.sleep(1000);
		// Verify More Link in My Non-Travel Claim
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_MoreLink"))));
		webDriver.findElement(By.xpath(sRPF.home_Xpath.get("Home_NonTravelClaim_MoreLink"))).click();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "NonTravelExpenseList");
		log("Verified More Link in My Non-Travel Claim : Navigating to 'Non Travel Expense List' Page.'");
		webDriver.navigate().back();
	}
	
	// Add Delegate to Other user and log in Other User and verify to Submit Claim behalf of Delegate User.
	@Test (description="Add Delegate to Other user and log in Other User and verify to Submit Claim behalf of Delegate User.",groups = { "addDelegateToOther_VerifytoAddClaimForDelegate" })
	public void addDelegateToOther_VerifytoAddClaimForDelegate() throws Exception
	{
		header("Add Delegate to Other user and log in Other User and verify to Submit Claim behalf of Delegate User.");
		sRPF = new Set_Read_ProperpertiesFile();
		// Add Delegate to Other user and log in Other User and verify to Submit Claim behalf of Delegate User.
		UserLogin userLogin = new UserLogin();
		userLogin.userLogout();
		// Do the Login as Other User.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		log("Verified: Logged in as Other User.'");
		// Go to User Profile page.
		mouseOverElement(By.linkText("Masters"));
		Thread.sleep(200);
		webDriver.findElement(By.linkText("User Profile")).click();
		Assert.assertTrue(isTextPresent("User Profile"));
		selectBylabel("ctl00_cphMain_ddlDelegateTo", UserLogin.loggedInUserName);
		webDriver.findElement(By.id("ctl00_cphMain_lnkSave")).click();
		Thread.sleep(900);
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
		Thread.sleep(900);
		log("Verified: Delegate Added Successfuly for User.'");
		// Login as user.
		userLogin.userLogout();
		// Do the Login as Other User.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		log("Verified: Logged in as Normal User.'");
		// Submit pre Approval Request for Delegate user.
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor")), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		log("Verified: Default Logged in user name appearing in 'Filed For' field.");
		// select Delegate user name to submit the request.
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor"), "0");
		Thread.sleep(900);
		Alert alert1 = webDriver.switchTo().alert();
		alert1.accept();
		Thread.sleep(900);
		String delegateName = getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor"));
		preApprovalClaim.fillPreApprovalExpenseForm();
		// Submit the Added request.
		preApprovalClaim.clickToSubmitButton();
		Thread.sleep(2000);
		preApprovalClaim.getAddedRequestNo();
		log("Verified: Normal User Added Request Successfuly behalf of Delegate User");
		// Verify Added Request Data.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestorName"))).getText(), delegateName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_ExpenseBudget"))).getText(), PreApprovalClaim.totalSub+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_CreationDate"))).getText(), PreApprovalClaim.addedDateTime);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgView"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgCancel"))));
		log("Verified: Requester name is Delegate user name on Listing page.");
	}
}
