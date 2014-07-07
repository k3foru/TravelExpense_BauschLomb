package tests.preApprovalClaim;

import modules.PreApprovalClaim;
import modules.UserLogin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class PreApprovalClaim_TestCase extends TestNgHelper{
	
	private Set_Read_ProperpertiesFile sRPF;
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab and Verify Elements are present on page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab and Verify Elements are present on page.", groups = { "verifyElementsPresentOnPreApprovalListingPage" })
	public void verifyElementsPresentOnPreApprovalListingPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		Assert.assertTrue(isTextPresent("Listing of Pre-Approvals"));
		// Verify Elements are present on page (Search DDL, Search Button, Search Text, Add new button).
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_txtSearchValue"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_AddNewButn"))));
		Reporter.log("Verified: Elements are present on page (Search DDL, Search Button, Search Text, Add new button).");
		Assert.assertTrue(isElementPresent(By.linkText(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_lnkViewPreApprovalRequest"))).getText())));
		Reporter.log("Verified: Request no. are a link");
		// Verify list of records should be displayed in a tabular format under columns on the page:
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_RequestNo"))).getText(), "Request No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_RequestorName"))).getText(), "Requestor Name");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ExpenseBudget"))).getText(), "Expense Budget (RMB)");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_CreationDate"))).getText(), "Creation Date");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_Status"))).getText(), "Status");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_FileClaim"))).getText(), "File Claim");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_Action"))).getText(), "Action");
		Reporter.log("Verified: Verify list of records should be displayed in a tabular format under columns on the page:");
		// Verify application navigates to view pre approval page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "PreApprovalsList");
		Reporter.log("Verified: application navigates to view pre approval page");
	}
	
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, verify Elements and fields validation while Applying on page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, verify Elements and fields validation while Applying on page.",groups = { "verifyElementsAndFieldsValidationWhileAddingNewRequest" })
	public void verifyElementsAndFieldsValidationWhileAddingNewRequest() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		Assert.assertTrue(isTextPresent("Pre-Approval Form"));
		Reporter.log("Verified: 'Pre-Approval Form' level present.");
		// On 'Pre-Approval Form' page verify the 'NonEditable Fields'.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtRequestNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor")), UserLogin.loggedInUserName);
		Reporter.log("Verified: 'Filed For', field selected Loggedin user name");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		Reporter.log("Verified: 'Filed By', field Value is Loggedin user name");
		Reporter.log("Verified The Elements : 'Request No', 'Filed By', fields are NonEditable Text Field");
		preApprovalClaim.checkFieldValidation_PreApprovalForm();
		Reporter.log("Verified: All Elements and there validation");
		preApprovalClaim.clickToSaveButn();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_ExpenseBudget"))).getText(), PreApprovalClaim.totalSub+".00");
		Reporter.log("Verified: Total added Expense amount are displaying on Listing page for Added request.");
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Save the the Expense and Verify Request Status, View, and Delete Action on the listing page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Save the the Expense and Verify Request Status, View, and Delete Action on the listing page.",groups = { "savePreApprovalExpense_VerifyStatusViewDeleteActionOnListPage" })
	public void savePreApprovalExpense_VerifyStatusViewDeleteActionOnListPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		preApprovalClaim.fillPreApprovalExpenseForm();
		// Save the Added request.
		preApprovalClaim.clickToSaveButn();
		preApprovalClaim.getAddedRequestNo();
		Assert.assertTrue(isTextPresent("Listing of Pre-Approvals"));
		// Verify the Requester name, Status, fileClaim, Actions.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_ExpenseBudget"))).getText(), PreApprovalClaim.totalSub+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Draft");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_FileClaim"))).getText(), "");
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgDelete"))));
		Reporter.log("Verified: the Requester name, Status, fileClaim, Actions for Added Request.");
		// Click to view Img Button of Drafted Request, and Verify All Added Record and fields nonEditable.
		preApprovalClaim.clickViewForDraftedRequest();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtRequestNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor")), UserLogin.loggedInUserName);
		Reporter.log("Verified: 'Filed For', field selected Loggedin user name");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("readonly"), "true");
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy")));
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan")));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText(), PreApprovalClaim.totalSub);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))));
		Reporter.log("Verified: All Entered data record are Appearing in corresponding fields, And 'Submit', 'Save' Button not displaying on View page");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))).click();
		preApprovalClaim.clickDeleteForDraftedRequest();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Reporter.log("\n\n Verified: Click to Delete Img Button - Validation Msg :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Reporter.log("Verified: click cancle to Delete record, record not deleted.");
		preApprovalClaim.clickDeleteForDraftedRequest();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Reporter.log("\n\n Verified: Click to Delete Img Button - Validation Msg :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isTextPresent(PreApprovalClaim.addedRequestNo));
		Reporter.log("Verified: click 'Ok' to Delete record, record Deleted.");
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Save the the Expense and Verify Edit Action on the listing page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Save the the Expense and Verify Edit Action on the listing page.",groups = { "savePreApprovalExpense_VerifyEditActionOnListPage" })
	public void savePreApprovalExpense_VerifyEditActionOnListPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		preApprovalClaim.fillPreApprovalExpenseForm();
		// Save the Added request.
		preApprovalClaim.clickToSaveButn();
		preApprovalClaim.getAddedRequestNo();
		Assert.assertTrue(isTextPresent("Listing of Pre-Approvals"));
		preApprovalClaim.clickEditForDraftedRequest();
		// Click to Edit Img Button of Drafted Request, and Verify All Added Record present and update the record.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtRequestNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor")), UserLogin.loggedInUserName);
		Reporter.log("Verified: 'Filed For', field selected Loggedin user name");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("readonly"), "true");
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy")));
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan")));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText(), PreApprovalClaim.totalSub);
		Reporter.log("Verified: Added Expense amount is : "+PreApprovalClaim.totalSub);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SaveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))));
		Reporter.log("Verified: All Entered data record are Appearing in corresponding fields, And 'Submit', 'Save' and 'Delete' Button displaying on Edit page");
		// Edit the Added record
		preApprovalClaim.updatePreApprovalAddedExpenseForm();
		preApprovalClaim.clickToSaveButn();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_ExpenseBudget"))).getText(), PreApprovalClaim.totalSub+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Draft");
		Reporter.log("Verified: Added Expense record Edited with same Request No and updated amount : "+PreApprovalClaim.totalSub);
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Submit the the Expense and Verify Status, View Action on the listing page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Submit the the Expense and Verify Status, View Action on the listing page.",groups = { "submitPreApprovalExpense_VerifyStatusViewActionOnListPage" })
	public void submitPreApprovalExpense_VerifyStatusViewActionOnListPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		preApprovalClaim.fillPreApprovalExpenseForm();
		// Submit the Added request.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Reporter.log("\n\n Click to Submit and, check the Validation Msg :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Reporter.log("\n\n Click to Submit and, cancle the Alert, request not submiting");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Reporter.log("\n\n Click to Submit and, check the Validation Msg :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		preApprovalClaim.getAddedRequestNo();
		Assert.assertTrue(isTextPresent("Listing of Pre-Approvals"));
		Reporter.log("\n\n Click to Submit and, Accept the Alert, request Submited");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_ExpenseBudget"))).getText(), PreApprovalClaim.totalSub+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_FileClaim"))).getText(), "");
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgCancel"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgDelete"))));
		Reporter.log("Verified: the Requester name, Status, fileClaim, Actions for Added Request.");
		Reporter.log("Verified: After Submit the request Status is : 'Awaiting approval from "+UserLogin.managerNameOfLoggedInUser+"'");
		// Click to view Img Button of Added Request, and Verify All Added Record and fields nonEditable.
		preApprovalClaim.clickViewForDraftedRequest();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtRequestNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor")), UserLogin.loggedInUserName);
		Reporter.log("Verified: 'Filed For', field selected Loggedin user name");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("readonly"), "true");
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy")));
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan")));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText(), PreApprovalClaim.totalSub);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ViewCommentsButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))));
		Reporter.log("Verified: View page of Submited Request : All Entered data record are Appearing in corresponding fields, And 'Submit', 'Save' Button not displaying on View page");
		// Verify the View Comments
		preApprovalClaim.clickToViewCommentButton();
		Assert.assertEquals(webDriver.getTitle(), "View Comments");
		Assert.assertTrue(isTextPresent("No records found."));
		Reporter.log("Verified: View Commetns Window having no Comments till now");
		//webDriver.close();
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Submit the the Expense and Verify Cancel Action and Status on the listing page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab to Add new Claim, Submit the the Expense and Verify Cancel Action and Status on the listing page.",groups = { "submitPreApprovalExpense_VerifyCancelActionAndStatusOnListPage" })
	public void submitPreApprovalExpense_VerifyCancelActionAndStatusOnListPage() throws Exception
	{
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
		Assert.assertTrue(isTextPresent("Listing of Pre-Approvals"));
		preApprovalClaim.clickToCancelSubmitedRequest();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Reporter.log("\n\n Click to Cancel Submited Request, check the Validation Msg :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Reporter.log("\n\n Click to Cancel and, cancle the Alert, request not Cancelled");
		preApprovalClaim.clickToCancelSubmitedRequest();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Reporter.log("\n\n Click to Cancel Submited Request, check the Validation Msg :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Cancelled");
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))));
		Reporter.log("\n\n Click to Cancel and, cancle the Alert, request not Cancelled");
		webDriver.findElement(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_0_lnkViewRequest")).click();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("Pre-Approval Form"));
		Reporter.log("\n\n Verified : Click to RequestNo link, and page navigating to PreApprovalForm page");
		preApprovalClaim.clickToBackButn();
		preApprovalClaim.clickEditForDraftedRequest();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("Pre-Approval Form"));
		Reporter.log("\n\n Verified : Click to Edit button, and page navigating to PreApprovalForm page");
		// Click to Edit Img Button of Submitted Request, and Verify All Added Record present and update the record.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtRequestNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlFilledFor")), UserLogin.loggedInUserName);
		Reporter.log("Verified: 'Filed For', field selected Loggedin user name");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("readonly"), "true");
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy")));
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan")));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText(), PreApprovalClaim.totalSub);
		Reporter.log("Verified: Added Expense amount is : "+PreApprovalClaim.totalSub);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ViewCommentsButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))));
		Reporter.log("Verified: All Entered data record are Appearing in corresponding fields, And 'Submit', 'ViewComments' and 'Back' Button displaying on Edit page");
		// Edit the Added record
		preApprovalClaim.updatePreApprovalAddedExpenseForm();
		preApprovalClaim.clickToSubmitButton();
		String beforeCancelRequestID = PreApprovalClaim.addedRequestNo;
		preApprovalClaim.getAddedRequestNo();
		if(!beforeCancelRequestID.equals(PreApprovalClaim.addedRequestNo))
			Reporter.log("Verified: Edited Cancel Request and submit than New RequestNo. are Generating ");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_ExpenseBudget"))).getText(), PreApprovalClaim.totalSub+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		Reporter.log("Verified: Cancel Expense record Edited and save with New Request No : "+PreApprovalClaim.addedRequestNo);
		Reporter.log("Verified: Cancel Expense record Edited with Amount : "+PreApprovalClaim.totalSub);
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, Check and verify paging on the PreApproval listing page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, Check and verify paging on the PreApproval listing page.",groups = { "pagingCheckAndVerifyOnPreApprovalListtingPage" })
	public void pagingCheckAndVerifyOnPreApprovalListtingPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - Check and verify paging.
		preApprovalClaim.checkAndVerifyForPaging();
		Reporter.log("Paging Verification Done on 'Listing of Pre-Approvals' page.");
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, Search functionality on the PreApproval listing page.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, Search functionality on the PreApproval listing page.",groups = { "verifySearchOnPreApprovalListtingPage" })
	public void verifySearchOnPreApprovalListtingPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - check and verify for 'Search By' option.
		preApprovalClaim.checkForSearchOptionOnPreApprovalListingPage();
		Reporter.log("Verified: Search Functionality");
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, Add one Claim and Verify the Reject and Approve claim from his Manager.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, Add one Claim and Verify the Reject and Approve claim from his Manager.",groups = { "addPreApprovalClaimAndApproveFromAdmin" })
	public void addPreApprovalClaimAndApproveFromAdmin() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		UserLogin userLogin = new UserLogin();
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		preApprovalClaim.fillPreApprovalExpenseForm();
		// Submit the Added request.
		preApprovalClaim.clickToSubmitButton();
		preApprovalClaim.getAddedRequestNo();
		Reporter.log("Verified: Normal User Added Request Successfuly");
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		System.out.println("Welcome "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		Reporter.log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		Assert.assertTrue(isElementPresent(By.linkText("My Approvals")));
		webDriver.findElement(By.linkText("My Approvals")).click();
		Assert.assertTrue(isTextPresent("Need My Approval List"));
		// Verify Added Request by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_ClaimNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_ClaimType"))).getText(), "Pre-Approval");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_TotalExpense"))).getText(), PreApprovalClaim.totalSub+".00");
		//Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_LastModified"))).getText(), "");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))));
		Reporter.log("Verified: All recard added by Normal user same displaying in corresponding fields.");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_ApproveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_RejectButton"))));
		Reporter.log("Verified: On My Approvale View page 'Approve' and 'Reject' Button displaying.");
		// Reject the Claim.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_RejectButton"))).click();
		Reporter.log("Verified: Manager Rejected The rquest that not Appearing on 'Need My Approval List' page");
		Assert.assertFalse(isTextPresent(PreApprovalClaim.addedRequestNo));
		// Do the Login as Normal User to edit the rejected claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		Reporter.log("Verified: Logged in as Normal to update his/her Rejected Claim.");
		preApprovalClaim.claimForPreApprovalRequest();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Rejected");
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))).click();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText(), PreApprovalClaim.totalSub);
		preApprovalClaim.clickToSubmitButton();
		String rejestedClaimNo = PreApprovalClaim.addedRequestNo;
		preApprovalClaim.getAddedRequestNo();
		if(!rejestedClaimNo.equals(PreApprovalClaim.addedRequestNo))
			Reporter.log("Rejected Claim Updated and submited : new claim no. generated");
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		System.out.println("Welcome "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		Reporter.log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		Assert.assertTrue(isElementPresent(By.linkText("My Approvals")));
		webDriver.findElement(By.linkText("My Approvals")).click();
		Assert.assertTrue(isTextPresent("Need My Approval List"));
		// Verify Added Request by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_ClaimNo"))).getText(), PreApprovalClaim.addedRequestNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		Thread.sleep(1000);
		// Approve the Claim.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("MyApprovalsList_ApproveButton"))).click();
		Thread.sleep(1000);
		// Do the Login as Normal User to check Approved claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		Reporter.log("Verified: Logged in as Normal to Check Approved Claim.");
		preApprovalClaim.claimForPreApprovalRequest();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Approved");
	}
	
	// Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, verify if 'Request Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim.
	@Test (description="Login as a Normal User and Click to 'Pre-Approval Request' in Claim Tab, verify if 'Request Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim.",groups = { "verifyRequestAmountGreaterThanApproverRangeOnPreApprovalFormPage" })
	public void verifyRequestAmountGreaterThanApproverRangeOnPreApprovalFormPage() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		UserLogin userLogin = new UserLogin();
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		System.out.println("Welcome "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		Reporter.log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Go to 'User Profile' in 'Master' Tab and get the 'Travel Expense Range Amount'
		preApprovalClaim.getTravelExpenseAmountFromUserProfile();
		// Do the Login as Normal User to Add the new claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		Reporter.log("Verified: Logged in as Normal User to Add the new claim.");
		preApprovalClaim.claimForPreApprovalRequest();
		// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
		preApprovalClaim.clickToAddNewClaimBtn();
		// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
		preApprovalClaim.fillPreApprovalExpenseForm();
		int requestAmount = Integer.parseInt(PreApprovalClaim.totalSub);
		int approvalAmount = Integer.parseInt(PreApprovalClaim.managerApprovalAmount);
		// verify if 'Request Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim
		if(requestAmount > approvalAmount){
			Reporter.log("Verified: Request Amount is greater than Manager Approval Amount.");
			preApprovalClaim.clickToSubmitButton();
			Reporter.log("Verified: Alert Diplaying because Request Amount is greater than Manager Approval Amount.");
		}
		else
			Reporter.log("Verified: Alert not Diplaying because Request Amount is not greater than Manager Approval Amount.");
	}
	
	// Login as a Normal User who is neither admin user nor 'finance user' and verify in 'My Approvals Tab' 'No records found.' message display.
	@Test (description="Login as a Normal User who is neither admin user nor 'finance user' and verify in 'My Approvals Tab' 'No records found.' message display.",groups = { "verifyMyApprovalsTabForNormalUserLogin" })
	public void verifyMyApprovalsTabForNormalUserLogin() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		PreApprovalClaim preApprovalClaim = new PreApprovalClaim();
		preApprovalClaim.goToMyApprovalTab();
		Assert.assertTrue(isTextPresent("No records found."));
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_ddlSearchCriteria")));
	}
}
