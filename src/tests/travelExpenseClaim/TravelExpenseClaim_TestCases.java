package tests.travelExpenseClaim;

import modules.TravelExpenseClaim;
import modules.UserLogin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class TravelExpenseClaim_TestCases extends TestNgHelper{
	
	private Set_Read_ProperpertiesFile sRPF;
	
	// Login as a Normal User and Click to 'Travel Expense' in Claim Tab and Verify Elements are present on page.
	@Test (description="Login as a Normal User and Click to 'Travel Expense' in Claim Tab and Verify Elements are present on page.", groups = { "verifyElementsPresentOnTravelExpenseListPage" })
	public void verifyElementsPresentOnTravelExpenseListPage() throws Exception
	{
		header("Login as a Normal User and Click to 'Travel Expense' in Claim Tab and Verify Elements are present on page.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		// Verify Elements are present on page (Search DDL, Search Button, Search Text, 'Add new button').
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_txtSearchValue"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_AddNewButn"))));
		log("Verified: Elements are present on page (Search DDL, Search Button, Search Text, Add new button).");
		Assert.assertTrue(isElementPresent(By.linkText(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_lnkViewClaimNo"))).getText())));
		log("Verified: Claim no. are a link");
		// Verify list of records should be displayed in a tabular format under columns on the page:
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ClaimNo"))).getText(), "Claim No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_RequestorName"))).getText(), "Requestor Name");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_TotalExpense"))).getText(), "Total Expense (RMB)");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_CreationDate"))).getText(), "Creation Date");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_Status"))).getText(), "Status");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_Action"))).getText(), "Action");
		log("Verified: list of records should be displayed in a tabular format under columns name present on the page:");
		// Verify application navigates to view Travel Expense List page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpenseList");
		log("Verified: application navigates to 'Travel Expense List' page by click on 'Travel Expense' link in claim tab");
	}
	
	// Login as a Normal User and go to Add new 'Travel Expense Claim' and Verify the which 'NonEditable' and Validation Over Submit Button while not Added any Details for Add New Claim.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Verify the which 'Not Editable' and Validation Over Submit Button while not Added any Details for new Claim.",groups = { "verifyElementsAndValidationOverSubmitButton" })
	public void verifyElementsAndValidationOverSubmitButton() throws Exception
	{
		header("Login as a Normal User and go to Add new 'Travel Expense Claim' and Verify the which 'NonEditable' and Validation Over Submit Button while not Added any Details for Add New Claim.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// From 'Travel Expense Claim' page click to 'Add New' Button. 
		travelExpenseClaim.clickToAddNewClaimBtn();
		Assert.assertTrue(isTextPresent("Travel Expense"));
		log("Verified: 'Travel Expense' level present.");
		// Verify application navigates to 'Travel Expense' Form page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpense");
		log("Verified: application navigates to 'TravelExpense' Fill Form page by click on 'Add New' Button on List page.");
		// Verify From 'Add New Travel Expense Claim' page verify the 'NonEditable Elements'.
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtRequestNo"))).isDisplayed());
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtRequestNo"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("disabled"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor")), UserLogin.loggedInUserName);
		log("Verified: 'Filed For', field selected Loggedin user name");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		log("Verified: 'Filed By', field Value is Loggedin user name");
		log("Verified The Elements : 'Request Number', 'Claim Number', 'Filed By' are NonEditable Text Field");
		// On 'Add New Travel Expense Claim' page click to 'Submit' Button, and verify the Validation for Fields Alert has to appear.
		travelExpenseClaim.clickToSubmitButtonAndCheckAlert();
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure to submit this claim?");
		alert.accept();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		log("Getting validation Alert for 'No. of Tickets' field is blank :: "+alert2.getText());
		log("Verified Valication on submit Button : Alert Appearing for 'fill No. of Tickets' while click to submit button without fill any details.");
		alert2.accept();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys("tesing");
		log("Verified 'No. of Tickets' field: can't send char in text field.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		travelExpenseClaim.clickToSubmitButtonAndCheckAlert();
		Alert alert3 = webDriver.switchTo().alert();
		Assert.assertEquals(alert3.getText(), "Are you sure to submit this claim?");
		alert3.accept();
		Thread.sleep(1000);
		Alert alert4 = webDriver.switchTo().alert();
		Assert.assertEquals(alert4.getText(), "Please specify at least one of the expense type.");
		alert4.accept();
		Thread.sleep(1000);
		log("Verified : Valication on submit Button : Alert Appearing for 'Please specify at least one of the expense type.' while click to submit button without fill any details.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))));
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.",groups = { "checkValidationOfTravelTransportationFields_TravelExpenseClaim" })
	public void checkValidationOfTravelTransportationFields_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.
		travelExpenseClaim.checkValidation_TravelTransportationSection();
		log("Verified : All fields validation and verification under 'Travel Transportation Section'.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Travel Transportation Section'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Travel Transportation Section'.",groups = { "addEditAndSaveTravelTransportationExpenseDetails_TravelExpenseClaim" })
	public void addEditAndSaveTravelTransportationExpenseDetails_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Travel Transportation Section'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Verify the Details After 'Add' Expense Under 'Travel Transportation Section'.
		travelExpenseClaim.fillTravelTransportationSection();
		travelExpenseClaim.clickToAddButnTravelTransportation();
		// Verify the Total Expense Amount in 'Travel Transportation' is Amount*Rate.
		int Amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		int Rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		int totalAmount = Amount*Rate;
		String totalAmountS = new Integer(totalAmount).toString();
		Assert.assertEquals(travelExpenseClaim.totalTravelTransportationExpense, new Integer(totalAmount).toString());
		Assert.assertTrue(isTextPresent(totalAmountS+".00"));
		log("Verified : the Total Expense Amount in 'Travel Transportation' is Amount*Rate = :: "+totalAmountS+".00");
		// Verified : 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Travel Transportation'.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))));
		log("Verified : 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Travel Transportation'.");
		// Verify Added Expense Details while Editing All Entered Data has to Appear in respective fields.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgEdit"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_CancelButton"))));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom")), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo")), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType")), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).getAttribute("value"), travelExpenseClaim.totalTravelTransportationExpense);
		log("Verified : Added Expense Details while Editing All Entered Data Appearing in respective fields.");
		// Cancel the Edit Action and verify the Cancel button not present.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_CancelButton"))).click();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_CancelButton"))));
		log("Verified, click to Cancel button of Edit Action and verify the Cancel button not present.");
		// Edit the Added Expense amount and click to update button and Verify the Updated Amount on page.
		travelExpenseClaim.editAddedTravelTransportationDetails();
		int editedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtEditedAmount"));
		int udatedAmount = editedAmount*Rate;
		String udatedAmountS = new Integer(udatedAmount).toString();
		Assert.assertEquals(travelExpenseClaim.totalTravelTransportationExpense, new Integer(udatedAmount).toString());
		Assert.assertTrue(isTextPresent(udatedAmountS+".00"));
		log("Verified after Edited added amount has changed Total Expence Amount in 'Travel Transportation' is :: "+udatedAmountS+".00");
		// Clicked to 'delete' button and verified to Alert appearing.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure you want to delete the record?");
		alert.dismiss();
		Thread.sleep(1000);
		log("Verified, Clicked to 'delete' button and verified to Alert appearing");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Are you sure you want to delete the record?");
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))));
		log("Verified, Clicked to 'delete' button and verified to Alert appearing and clicked to ok button, Added Expense Deleted.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		// Save the Added Expense click to save button and verify Added amount displaying on Header.
		travelExpenseClaim.fillTravelTransportationSection();
		travelExpenseClaim.clickToAddButnTravelTransportation();
		travelExpenseClaim.clickToSaveButnTravelTransportation();
		Assert.assertTrue(isTextPresent("Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense);
		log("Verified : Travel Transportation Added one expense and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', add two details with same data and Verify should not Add Under 'Travel Transportation Section' and verify two details to be added on header.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', add two details with same data and Verify should not Add Under 'Travel Transportation Section' and verify two details to be added on header.",groups = { "addAndSaveTwoDataInTravelTransportationExpense_TravelExpenseClaim" })
	public void addAndSaveTwoDataInTravelTransportationExpense_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', add two details with same data and Verify should not Add Under 'Travel Transportation Section' and verify two details to be added on header.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Add One Expense and verify Added amount displaying on Header.
		travelExpenseClaim.fillTravelTransportationSection();
		travelExpenseClaim.clickToAddButnTravelTransportation();
		Assert.assertTrue(isTextPresent("Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense));
		log("Verified : Travel Transportation Added and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
		// Add one more time same data to add expense, and verify validation alert has to appear to not save.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		travelExpenseClaim.fillTravelTransportationSection();
		travelExpenseClaim.clickToAddButnTravelTransportation();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Record with selected Departure date already exists.");
		alert1.accept();
		Thread.sleep(1000);
		log("Verified : Adding one more time same data to add expense, and validation alert has to appear to not save.");
		// Add One more Expense and verify both expense Added amount displaying on Header.
		travelExpenseClaim.fillSecondExpenseTravelTransportationSection();
		travelExpenseClaim.clickToAddButnTravelTransportation();
		int total = Integer.parseInt(travelExpenseClaim.totalTravelTransportationExpense);
		total = total+total;
		String SubTotal = new Integer(total).toString();
		Assert.assertTrue(isTextPresent("Travel Transportation - Sub Total: "+SubTotal));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+SubTotal);
		log("Verified : Travel Transportation Added both record and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+SubTotal+"' displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Lodging Header Section', and Verify Added total expense in 'Lodging Section' is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Lodging Header Section', and Verify Added total expense in 'Lodging Section' is equals to 'Amount*Rate'.",groups = { "checkValidationOfFieldsAndTotalRateInLodgingSection_TravelExpenseClaim" })
	public void checkValidationOfFieldsAndTotalRateInLodgingSection_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Lodging Header Section', and Verify Added total expense in 'Lodging Section' is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Check the Validation for fields under 'Lodging Section'.
		travelExpenseClaim.checkFieldsValidation_LodgingSection();
		log("Verified : All fields validation and verification under 'Lodging Section'.");
		// Verify Added total expense in 'Lodging Section' is equals to 'Amount*Rate'.
		int Amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtAmount"));
		int Rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtRate"));
		int totalAmount = Amount*Rate;
		Assert.assertEquals(travelExpenseClaim.totalLodgingExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Lodging Section' is equals to 'Amount*Rate'.");
		travelExpenseClaim.clickToAddButnInLodgingSection();
		// Verify Added Expense by click to add button and verify Added amount displaying on Lodging Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+new Integer(totalAmount).toString());
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Lodging Section'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Lodging Section'.",groups = { "addEditAndSaveInLodgingSectionExpenseDetails_TravelExpenseClaim" })
	public void addEditAndSaveInLodgingSectionExpenseDetails_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Lodging Section'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill details in Lodging section and add it.
		travelExpenseClaim.fillLodgingSection();
		travelExpenseClaim.clickToAddButnInLodgingSection();
		// Verified : 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Lodging section'.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgDelete"))));
		log("Verified : 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Lodging section'");
		// Verify Added Expense Details while Editing All Entered Data has to Appear in respective fields.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgEdit"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CancelButton"))));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlLocation")), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlLocation"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlPaymentMode")), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlPaymentMode"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlCurrency")), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlCurrency"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtAmount"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtRate"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).getAttribute("value"), travelExpenseClaim.totalLodgingExpense);
		log("Verified : Added Expense Details while Editing All Entered Data Appearing in respective fields.");
		// Cancel the Edit Action and verify the Cancel button not present.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CancelButton"))).click();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CancelButton"))));
		log("Verified, click to Cancel button of Edit Action and verify the Cancel button not present.");
		// Edit the Added Expense amount and click to update button and Verify the Updated Amount on page.
		travelExpenseClaim.editAddedLodgingSectionDetails();
		int editedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtEditedAmount"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtRate"));
		int udatedAmount = editedAmount*rate;
		Assert.assertEquals(travelExpenseClaim.totalLodgingExpense, new Integer(udatedAmount).toString());
		log("Verified : after Edited added amount has changed Total Expence Amount to :: "+udatedAmount);
		// Verify update expense amount displaying on Lodging Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+new Integer(udatedAmount).toString());
		log("Verified : update expense amount displaying on Lodging Header. - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		// Clicked to 'delete' button and verified to Alert appearing.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure you want to delete the record?");
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgDelete"))));
		log("Verified, Clicked to 'delete' button and verified to Alert appearing, cancel the alert Record not deleted");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Are you sure you want to delete the record?");
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgDelete"))));
		log("Verified, Clicked to 'delete' button and verified to Alert appearing and clicked to ok button, Added Expense Deleted.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).click();
		Thread.sleep(1000);
		// Save the Added Expense click to save button and verify Added amount displaying on Header.
		travelExpenseClaim.fillLodgingSection();
		travelExpenseClaim.clickToAddButnInLodgingSection();
		travelExpenseClaim.clickToSaveButnInLodgingSection();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', add two details with same data and Verify should not Add Under 'Lodging Section' and verify two details to be added on header.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', add two details with same data and Verify should not Add Under 'Lodging Section' and verify two details to be added on header.",groups = { "addAndSaveTwoDataInLodgingSectionExpense_TravelExpenseClaim" })
	public void addAndSaveTwoDataInLodgingSectionExpense_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', add two details with same data and Verify should not Add Under 'Lodging Section' and verify two details to be added on header.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Add One Expense and verify Added amount displaying on Header.
		travelExpenseClaim.fillLodgingSection();
		travelExpenseClaim.clickToAddButnInLodgingSection();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		// Add one more Expense with In Date within added expense date, and verify validation alert has to appear to not Add.
		travelExpenseClaim.fillSecondExpenseInLodgingSection();
		travelExpenseClaim.clickToAddButnInLodgingSection();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Checked In Date selected is not valid as it was already selected as a part of record.");
		log("Verified : Add one more Expense with In Date within added expense date, and verify validation alert has to appear to not Add. :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		log("Verified : Add one more Expense with In Date within added expense date, and verify validation alert has to appearing, record will not Add.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).click();
		Thread.sleep(1000);
		// Add One more Expense (Same Data)and verify both expense Added amount displaying on Header.
		travelExpenseClaim.fillLodgingSection();
		travelExpenseClaim.clickToAddButnInLodgingSection();
		travelExpenseClaim.clickToSaveButnInLodgingSection();
		int total = Integer.parseInt(travelExpenseClaim.totalLodgingExpense);
		total = total+total;
		String SubTotal = new Integer(total).toString();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+SubTotal);
		log("Verified : In Lodging Added two record (with Same Data) and on the header of Lodging section displaying Added with - 'Lodging - Sub Total: "+SubTotal);
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Local Transportation Section', and Verify Added total expense in 'local Transportation Section' is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'local Transportation Section', and Verify Added total expense in 'local Transportation Section' is equals to 'Amount*Rate'.",groups = { "checkValidationOfFieldsAndTotalRateInLocalTransportation_TravelExpenseClaim" })
	public void checkValidationOfFieldsAndTotalRateInLocalTransportation_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Local Transportation Section', and Verify Added total expense in 'local Transportation Section' is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Check the Validation for fields under 'local Transportation Section'.
		travelExpenseClaim.checkFieldsValidation_localTransportationSection();
		log("Verified : All fields validation and verification under 'Local Transportation Section'.");
		// Verify Added total expense in 'Local Transportation Section' is equals to 'Amount*Rate'. 
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtAmount1"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		int totalAmount = amount*rate;
		Assert.assertEquals(travelExpenseClaim.totalLocalTransportationExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Local Transportation Section' is equals to 'Amount*Rate'.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Delete' and 'save' Expense Under 'Local Transportation Section' and Verify Added total expense is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Delete' and 'save' Expense Under 'Local Transportation Section' and Verify Added total expense is equals to 'Amount*Rate'.",groups = { "addDeleteAndTotalRateVerificationInLocalTransportation_TravelExpenseClaim" })
	public void addDeleteAndTotalRateVerificationInLocalTransportation_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Delete' and 'save' Expense Under 'Local Transportation Section' and Verify Added total expense is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Add one record under 'Local Transportation Section'.
		travelExpenseClaim.fillDetailsInLocalTransportationSection();
		log("Verified : Record Added under 'local Transportation Section'.");
		// Verify Added total expense in 'local Transportation Section' is equals to 'Amount*Rate'.
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtAmount1"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		int totalAmount = amount*rate;
		Assert.assertEquals(travelExpenseClaim.totalLocalTransportationExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Local Transportation Section' is equals to 'Amount*Rate'.");
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		// Verify Added Expense by click to add button and verify Added amount displaying on local Transportation Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+new Integer(totalAmount).toString());
		log("Verified : In local Transportation Section Added one expense and on the header of local Transportation Total Amount - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete"))));
		log("Verified : For Add expense in 'Local Transportation Section' delete img button appearing.");
		// verify to click of delete button and check for validation Alert.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n Verified : For Added Expense in 'Local Transportation Section' click of delete button and check for validation Alert, Validation Alert - :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete"))));
		log("Verified : Cancel to Delete Action and verified Record not deleted.");
		// Delete the record and verify Record has to removed.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		log("\n Verified : For Added Expense in 'Local Transportation Section' click of delete button and check for validation Alert, Validation Alert - :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete"))));
		Assert.assertFalse(isTextPresent("Local Transportation (Travel Expense) - Sub Total: "+new Integer(totalAmount).toString()));
		log("Verified : Accepted to Delete Action and verified Record deleted.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Edit' and 'save' Added Expense Under 'Local Transportation Section' and Verify updated total expense.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Edit' and 'save' Added Expense Under 'Local Transportation Section' and Verify updated total expense.",groups = { "editAndSaveAddedRecordInLocalTransportation_TravelExpenseClaim" })
	public void editAndSaveAddedRecordInLocalTransportation_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Edit' and 'save' Added Expense Under 'Local Transportation Section' and Verify updated total expense.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Add one record under 'Local Transportation Section'.
		travelExpenseClaim.fillDetailsInLocalTransportationSection();
		log("Verified : Record Added under 'local Transportation Section'.");
		// Add one Record and Edit that.
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent(travelExpenseClaim.totalLocalTransportationExpense+".00"));
		log("Verified : Record Added under 'local Transportation Section'.");
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_AddEditButton")));
		// Edit the Added Record and verify All Entered Details are Appearing in Respective Fields. 
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtFrom1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtTo1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtPurpose1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtPurpose1"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlPaymentMode1")), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlPaymentMode1"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlCurrency1")), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlCurrency1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtAmount1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).getAttribute("value"), travelExpenseClaim.totalLocalTransportationExpense);
		log("Verified : Edited the Added Record and verify All Entered Details are Appearing in Respective Fields. ");
		// Edit the Added record by different Amount and Save it.
		travelExpenseClaim.editAddedRecordInLocalTransportationSection();
		// Verify updated total expense in 'local Transportation Section' is equals to 'updatedAmount*Rate'.
		int updatedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtEditedAmount1"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		int subAmount = updatedAmount*rate;
		Assert.assertEquals(travelExpenseClaim.totalLocalTransportationExpense, new Integer(subAmount).toString());
		log("Verified : updated total expense in 'local Transportation Section' is equals to 'updatedAmount*Rate'.");
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		travelExpenseClaim.clickToSaveButnInlocalTransportationSection();
		// Add updated Expense and verify updated total expense amount displaying on local Transportation Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+new Integer(subAmount).toString());
		log("Verified : updated total expense amount displaying on local Transportation Header - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Personal Meal Section', and Verify Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Personal Meal Section', and Verify Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.",groups = { "checkValidationOfFieldsAndTotalRateInPersonalMeal_TravelExpenseClaim" })
	public void checkValidationOfFieldsAndTotalRateInPersonalMeal_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Personal Meal Section', and Verify Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Check the Validation for fields under 'Personal Meal Section'.
		travelExpenseClaim.checkFieldsValidation_PersonalMealSection();
		log("Verified : All fields validation and verification under 'Personal Meal Section'.");
		// Verify Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.
		//int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtLunch1")) + Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtAmount1"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtRate1"));
		int totalAmount = amount*rate;
		Assert.assertEquals(travelExpenseClaim.totalPersonalMealExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Delete' and 'save' Expense Under 'Personal Meal Section' and Verify Added total expense is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Delete' and 'save' Expense Under 'Personal Meal Section' and Verify Added total expense is equals to 'Amount*Rate'.",groups = { "addDeleteAndTotalExpenseVerificationInPersonalMeal_TravelExpenseClaim" })
	public void addDeleteAndTotalExpenseVerificationInPersonalMeal_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Delete' and 'save' Expense Under 'Personal Meal Section' and Verify Added total expense is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Add one record under 'Personal Meal Section'.
		travelExpenseClaim.fillDetailsInPersonalMealSection();
		log("Verified : Record Added under 'Personal Meal Section'.");
		// Verify Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.
		//int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtLunch1")) + Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtAmount1"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtRate1"));
		int totalAmount = amount*rate;
		Assert.assertEquals(travelExpenseClaim.totalPersonalMealExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Personal Meal Section' is equals to 'Amount*Rate'.");
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		// Verify Added Expense by click to add button and verify Added amount displaying on Personal Meal Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+new Integer(totalAmount).toString());
		log("Verified : In Personal Meal Section Added one expense and on the header of Personal Meal Total Amount - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_imgDelete"))));
		log("Verified : For Add expense in 'Personal Meal Section' delete img button appearing.");
		// verify to click of delete button and check for validation Alert.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n Verified : For Added Expense in 'Personal Meal Section' click of delete button and check for validation Alert, Validation Alert - :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(500);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_imgDelete"))));
		log("Verified : Cancel to Delete Action and verified Record not deleted.");
		// Delete the record and verify Record has to removed.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		log("\n Verified : For Added Expense in 'Personal Meal Section' click of delete button and check for validation Alert, Validation Alert - :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_imgDelete"))));
		Assert.assertFalse(isTextPresent("Personal Meal - Sub Total: "+new Integer(totalAmount).toString()));
		log("Verified : Accecpted to Delete Action and verified Record deleted.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Edit' and 'save' Added Expense Under 'Personal Meal Section' and Verify updated total expense.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Edit' and 'save' Added Expense Under 'Personal Meal Section' and Verify updated total expense.",groups = { "editAndSaveAddedRecordInPersonalMeal_TravelExpenseClaim" })
	public void editAndSaveAddedRecordInPersonalMeal_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Edit' and 'save' Added Expense Under 'Personal Meal Section' and Verify updated total expense.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Add one record under 'Personal Meals Section'.
		travelExpenseClaim.fillDetailsInPersonalMealSection();
		log("Verified : Record Added under 'Personal Meal Section'.");
		// Add one Record and Edit that.
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent(travelExpenseClaim.totalPersonalMealExpense+".00"));
		log("Verified : Record Added under 'Personal Meal Section'.");
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_AddEditButton")));
		// Edit the Added Record and verify All Entered Details are Appearing in Respective Fields. 
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtLunch1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtLunch1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtDinner1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlPaymentMode1")), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_ddlPaymentMode1"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlCurrency1")), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_ddlCurrency1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtAmount1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtRate1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtRate1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).getAttribute("value"), travelExpenseClaim.totalPersonalMealExpense);
		log("Verified : Edited the Added Record and verify All Entered Details are Appearing in Respective Fields. ");
		// Edit the Added record by different Amount and Save it.
		travelExpenseClaim.editAddedRecordInPersonalMealSection();
		// Verify updated total expense in 'Personal Meals Section' is equals to 'updatedAmount*Rate'.
		//int updatedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtEditedLunch1")) + Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		int updatedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtEditedAmount1"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		int subAmount = updatedAmount*rate;
		Assert.assertEquals(travelExpenseClaim.totalPersonalMealExpense, new Integer(subAmount).toString());
		log("Verified : updated total expense in 'Personal Meals Section' is equals to 'updatedAmount*Rate'.");
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		travelExpenseClaim.clickToSaveButnInPersonalMealSection();
		// Add updated Expense and verify updated total expense amount displaying on Personal Meals Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+new Integer(subAmount).toString());
		log("Verified : updated total expense amount displaying on Personal Meals Header - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
	}

	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Other Header Section', and Verify Added total expense in 'Other Section' is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Other Header Section', and Verify Added total expense in 'Other Section' is equals to 'Amount*Rate'.",groups = { "checkValidationOfFieldsAndTotalRateInOtherSection_TravelExpenseClaim" })
	public void checkValidationOfFieldsAndTotalRateInOtherSection_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Other Header Section', and Verify Added total expense in 'Other Section' is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Check the Validation for fields under 'Other Section'.
		travelExpenseClaim.checkFieldsValidation_OtherSection();
		log("Verified : All fields validation and verification under 'Other Section'.");
		// Verify Added total expense in 'Other Section' is equals to 'Amount*Rate'.
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtAmount"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtRate"));
		int totalAmount = amount*rate;
		Assert.assertEquals(travelExpenseClaim.totalOtherExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Other Section' is equals to 'Amount*Rate'.");
		travelExpenseClaim.clickToAddButnInOtherSection();
		// Verify Added Expense by click to add button and verify Added amount displaying on Other Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+new Integer(totalAmount).toString());
		log("Verified : In Other Section Added one expense and on the header of Other Total Amount - 'Others - Sub Total: "+travelExpenseClaim.totalOtherExpense+"' is displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Other Section'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Other Section'.",groups = { "addEditAndSaveInOtherSectionExpenseDetails_TravelExpenseClaim" })
	public void addEditAndSaveInOtherSectionExpenseDetails_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Other Section'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill details in Other section and add it.
		travelExpenseClaim.fillDetailsInOtherSection();
		travelExpenseClaim.clickToAddButnInOtherSection();
		// Verified : 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Other section'.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgDelete"))));
		log("Verified : 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Other section'");
		// Verify Added Expense Details while Editing All Entered Data has to Appear in respective fields.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgEdit"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_CancelButton"))));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlExpenseType"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtExpDescription"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtExpDescription"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlPaymentMode")), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlPaymentMode"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlCurrency")), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlCurrency"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmount"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtAmount"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtRate"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtRate"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).getAttribute("value"), travelExpenseClaim.totalOtherExpense);
		log("Verified : Added Expense Details while Editing All Entered Data Appearing in respective fields.");
		// Cancel the Edit Action and verify the Cancel button not present.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_CancelButton"))).click();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_CancelButton"))));
		log("Verified, click to Cancel button of Edit Action and verify the Cancel button not present.");
		// Edit the Added Expense amount and click to update button and Verify the Updated Amount on page.
		travelExpenseClaim.editAddedOtherSectionDetails();
		int editedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtEditeAmount"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtRate"));
		int udatedAmount = editedAmount*rate;
		Assert.assertEquals(travelExpenseClaim.totalOtherExpense, new Integer(udatedAmount).toString());
		log("Verified : after Edited added amount has changed Total Expence Amount to :: "+udatedAmount);
		// Verify update expense amount displaying on Other Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+new Integer(udatedAmount).toString());
		log("Verified : update expense amount displaying on Others Header is:  - Sub Total: "+travelExpenseClaim.totalOtherExpense);
		// Clicked to 'delete' button and verified to Alert appearing.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure you want to delete the record?");
		log("\n\n Verified : Clicked to 'delete' button vrification Alert is :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgDelete"))));
		log("Verified : Clicked to 'delete' button and verified to Alert appearing, cancel the alert Record not deleted");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Are you sure you want to delete the record?");
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgDelete"))));
		log("Verified, Clicked to 'delete' button and verified to Alert appearing and clicked to ok button, Added Expense Deleted.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).click();
		Thread.sleep(1000);
		// Save the Added Expense click to save button and verify Added amount displaying on Header.
		travelExpenseClaim.fillDetailsInOtherSection();
		travelExpenseClaim.clickToAddButnInOtherSection();
		travelExpenseClaim.clickToSaveButnInOtherSection();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+travelExpenseClaim.totalOtherExpense);
		log("Verified : in Other section Added one expense and on the header of Other - 'Others - Sub Total: "+travelExpenseClaim.totalOtherExpense+"' is displaying.");
		//webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).click();
		Thread.sleep(1000);
		// Add One more Expense (with Same Data)and verify both expense Added amount displaying on Header.
		travelExpenseClaim.fillDetailsInOtherSection();
		travelExpenseClaim.clickToAddButnInOtherSection();
		travelExpenseClaim.clickToSaveButnInOtherSection();
		int total = Integer.parseInt(travelExpenseClaim.totalOtherExpense);
		total = total+total;
		String SubTotal = new Integer(total).toString();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+SubTotal);
		log("Verified : In Other Added two record (with Same Data) and on the header of Other section displaying Added with - 'Others - Sub Total: "+SubTotal);
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Internal Meeting Section', and Verify Added total expense in 'Internal Meeting Section' is equals to 'Amount*Rate'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Internal Meeting Section', and Verify Added total expense in 'Internal Meeting Section' is equals to 'Amount*Rate'.",groups = { "checkValidationOfFieldsAndTotalExpenseInInternalMeeting_TravelExpenseClaim" })
	public void checkValidationOfFieldsAndTotalExpenseInInternalMeeting_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Internal Meeting Section', and Verify Added total expense in 'Internal Meeting Section' is equals to 'Amount*Rate'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// go to 'Add Travel Expense Claim' and Check the Validation for fields under 'Internal Meeting Section'.
		travelExpenseClaim.checkFieldsValidation_InternalMeetingSection();
		log("Verified : All fields validation and verification under 'Internal Meeting Section'.");
		// Verify Added total expense in 'Internal Meeting Section' is equals to 'Amount*Rate'.
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtExceedAmount"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtRate"));
		int totalAmount = amount*rate;
		Assert.assertEquals(travelExpenseClaim.totalInternalMeetingExpense, new Integer(totalAmount).toString());
		log("Verified : Added total expense in 'Internal Meeting Section' is equals to 'Amount*Rate'.");
		// Verify Added Expense by click to add button and verify Added amount displaying on Internal Meeting Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+new Integer(totalAmount).toString());
		log("Verified : In Internal Meeting Section Added one expense and on the header of Internal Meeting Total Amount - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Check and select the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verify dependent radio button is enable and disable, and verify Add and Delete Rows.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Check and select the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verify dependent radio button is enable and disable, and verify Add and Delete Rows.",groups = { "checkAndVerifyTypeOfEntertainmentOptionAndAddDeleteRowsInInternalMeeting_TravelExpenseClaim" })
	public void checkAndVerifyTypeOfEntertainmentOptionAndAddDeleteRowsInInternalMeeting_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Check and select the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verify dependent radio button is enable and disable, and verify Add and Delete Rows.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		// select the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verify dependent radio button is enable and disable.
		travelExpenseClaim.checkAndVerifyTypeOfEntertainmentOption_InternalMeetingSection();
		log("Verified : seleced all the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verified dependent radio button is enable and disable.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ButtonAddNew"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete1"))));
		// click to delete action to delete the one row.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete1"))).click();
		Thread.sleep(1000);
		// Check the alert.
		Alert alert = webDriver.switchTo().alert();
		log("\n Verified : Validation to delete the row : validation in alert :: "+alert.getText());
		// cancel the delete alert and verify row should not deleted.
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete1"))));
		log("Verified : canceled the delete alert and verified row not deleted from the list.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete1"))).click();
		Thread.sleep(1000);
		// Check the alert.
		Alert alert1 = webDriver.switchTo().alert();
		log("\n Verified : Validation to delete the row : validation in alert :: "+alert1.getText());
		// Accept the delete alert and verify row should not deleted.
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_ucInternalMeeting_AccordionPane1_content_grdBusinessRelationship_ctl06_txtName")));
		log("Verified : accept (OK) the delete alert and verified row deleted from the list.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete1"))).click();
		Thread.sleep(1000);
		// Check the alert.
		Alert alert2 = webDriver.switchTo().alert();
		// Accept the delete alert and verify row should not deleted.
		alert2.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_ucInternalMeeting_AccordionPane1_content_grdBusinessRelationship_ctl05_txtName")));
		Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_ucInternalMeeting_AccordionPane1_content_grdBusinessRelationship_ctl06_txtName")));
		// Add one row by click of 'Add new Row', and verify new row Added.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ButtonAddNew"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_ucInternalMeeting_AccordionPane1_content_grdBusinessRelationship_ctl05_txtName")));
		log("Verified : Only One Row Added in List by one click of 'Add new Row' button");
	}
	
	// Login as a Normal User and go to Add 'Travel Expense Claim', add Claim check for Attendees attempt limit in Master page and verify Attendees details should be in 'Red' color if Attending more than limit.
	@Test (description="Login as a Normal User and go to Add 'Travel Expense Claim', add Claim check for Attendees attempt limit in Master page and verify Attendees details should be in 'Red' color if Attending more than limit.",groups = { "addClaimAndVerifyTimeOfAttandeesAndAmount_InInternalMeetingSetion" })
	public void addClaimAndVerifyTimeOfAttandeesAndAmount_InInternalMeetingSetion() throws Exception
	{
		header("Login as a Normal User and go to Add 'Travel Expense Claim', add Claim check for Attendees attempt limit in Master page and verify Attendees details should be in 'Red' color if Attending more than limit.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		UserLogin userLogin = new UserLogin();
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to get the 'Attendee Limit'.");
		// Go to 'User Profile' in 'Master' Tab and get the 'Travel Expense Range Amount'
		travelExpenseClaim.getAttendeeNoOFLimitFromManageExpenseLimitPage();
		// Do the Login as Normal User to Add the new claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		log("Verified: Logged in as Normal User to Add the new claim.");
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Add Claim 3 times with Same Details if Limit is 2.
		travelExpenseClaim.fillDetailsInInternalMeetingSectionForLimitOFAttendees();
		travelExpenseClaim.clickToAddButnInInternalMeetingSection();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		travelExpenseClaim.fillDetailsInInternalMeetingSectionForLimitOFAttendees();
		travelExpenseClaim.clickToAddButnInInternalMeetingSection();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		travelExpenseClaim.fillDetailsInInternalMeetingSectionForLimitOFAttendees();
		travelExpenseClaim.clickToAddButnInInternalMeetingSection();
		// Verified : 'view' 'Edit' and 'Delete' Action img displaying on page After Added Expense claim in 'Internal Meeting section'.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete"))));
		log("Verified : Attendees Lmit is 2 and Added 3 Claim in 'Internal Meeting section' Added Successfuly.");
		// Verify View Details of Added Expense.
		childWindowHandler(By.id("ctl00_cphMain_ucInternalMeeting_AccordionPane1_content_grdInternalMeeting_grdInternalMeeting_2_imgView"));
		Assert.assertTrue(isTextPresent("Attendees Information"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_txtName"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtName1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_txtTitle"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtTitle1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_txtCompany"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtCompany1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_lblInvitedTimes"))).getText(), "3");
		log("Verified : Added Three Claim with Same Details in 'Internal Meeting section' and getting 'Invited Times' increamented with 3 on View Page.");
		log("Verified : All 'Attendees Information' displaying same as entered data.");
	}

	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'View' 'Edit' 'Delete' and 'save' Expense Under 'Internal Meeting Section', Add One more Expense (with Same Data)and verify both expense Added amount displaying on Header.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'Edit' 'Delete' and 'save' Expense Under 'Internal Meeting Section', Add One more Expense (with Same Data)and verify both expense Added amount displaying on Header.",groups = { "addEditDeleteAndSaveInInternalMeetingSetionExpenseDetails_TravelExpenseClaim" })
	public void addEditDeleteAndSaveInInternalMeetingSetionExpenseDetails_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' 'View' 'Edit' 'Delete' and 'save' Expense Under 'Internal Meeting Section', Add One more Expense (with Same Data)and verify both expense Added amount displaying on Header.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill details in Internal Meeting section and add it.
		travelExpenseClaim.fillDetailsInInternalMeetingSection();
		travelExpenseClaim.clickToAddButnInInternalMeetingSection();
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalInternalMeetingExpense));
		// Verified : 'view' 'Edit' and 'Delete' Action img displaying on page After Added Expense claim in 'Internal Meeting section'.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete"))));
		log("Verified : 'View' 'Edit' and 'Delete' Action img appearing on page After Added Expense claim in 'Internal Meeting section'");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_SelectedTypeOfEntertainment"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"));
		log("Verified : Added 'TypeOfEntertainment' are displaying in grid.");
		// Verify View Details of Added Expense.
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView")));
		Assert.assertTrue(isTextPresent("Attendees Information"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_txtName"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtName1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_txtTitle"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtTitle1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_txtCompany"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtCompany1"));
		log("Verified : All 'Attendees Information' displaying same as entered data.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgView_CloseButton"))).click();
		Thread.sleep(1000);
		webDriver.switchTo().window(parentWindowHandle);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgEdit"))).click();
		Thread.sleep(600);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_CancelButton"))));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCity")), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCity"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPlace"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPlace"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtEWorkflow"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtEWorkflow"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment")), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtDuration"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtDuration"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPurpose"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPurpose"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlPaymentMode")), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlPaymentMode"));
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCurrency")), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCurrency"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmount"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtAmount"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtRate"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).getAttribute("value"), travelExpenseClaim.totalInternalMeetingExpense);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtName1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtName1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtTitle1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtTitle1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtCompany1"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtCompany1"));
		log("Verified : Added Expense Details while Editing All Entered Data Appearing in respective fields.");
		// Cancel the Edit Action and verify the Cancel button not present.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_CancelButton"))).click();
		Thread.sleep(100);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_CancelButton"))));
		log("Verified, click to Cancel button of Edit Action and verify the Cancel button not present.");
		// Edit the Added Expense amount and click to update button and Verify the Updated Amount on page.
		travelExpenseClaim.editAddedInternalMeetingSectionnDetails();
		int editedAmount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtEditedAmount"));
		int rate = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtRate"));
		int udatedAmount = editedAmount*rate;
		Assert.assertEquals(travelExpenseClaim.totalInternalMeetingExpense, new Integer(udatedAmount).toString());
		log("Verified : after Edited added amount has changed Total Expence Amount to :: "+udatedAmount);
		// Verify update expense amount displaying on InternalMeeting Header.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+new Integer(udatedAmount).toString());
		log("Verified : update expense amount displaying on Internal meeting Header is: - Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
		// Clicked to 'delete' button and verified to Alert appearing.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete"))).click();
		Thread.sleep(100);
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure you want to delete the record?");
		log("\n\n Verified : Clicked to 'delete' button vrification Alert is :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete"))));
		log("Verified : Clicked to 'delete' button and verified to Alert appearing, cancel the alert Record not deleted");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete"))).click();
		Thread.sleep(100);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Are you sure you want to delete the record?");
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgDelete"))));
		log("Verified, Clicked to 'delete' button and verified to Alert appearing and clicked to ok button, Added Expense Deleted.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		// Save the Added Expense click to save button and verify Added amount displaying on Header.
		travelExpenseClaim.fillDetailsInInternalMeetingSection();
		travelExpenseClaim.clickToAddButnInInternalMeetingSection();
		travelExpenseClaim.clickToSaveButnInInternalMeetingSection();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
		log("Verified : in Internal meeting section Added one expense and on the header of Internal meeting - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).click();
		// Add One more Expense (with Same Data)and verify both expense Added amount displaying on Header.
		travelExpenseClaim.fillDetailsInInternalMeetingSection();
		travelExpenseClaim.clickToAddButnInInternalMeetingSection();
		travelExpenseClaim.clickToSaveButnInInternalMeetingSection();
		int total = Integer.parseInt(travelExpenseClaim.totalInternalMeetingExpense);
		total = total+total;
		String SubTotal = new Integer(total).toString();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+SubTotal);
		log("Verified : In Internal meeting Added two record (with Same Data) and on the header of Internal meeting section displaying Added with - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+SubTotal);
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify 'Cash advance' field and the Attach Documents Section.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify 'Cash advance' field and the Attach Documents Section.",groups = { "verifyAttachDocumentsSection_TravelExpenseClaim" })
	public void verifyAttachDocumentsSection_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Verify 'Cash advance' field and the Attach Documents Section.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		//Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtCashAdvance"))).isEnabled());
		log("Verified : Login as Normal user and 'Cash advance' fields is noneditale");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_flUpload"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFileDescription"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_UploadButton"))));
		log("Verified : 'flUpload', 'FileDescription', 'UploadButton' displaying on the page.");
		// User does not browse any file and clicks on upload button, Verify alert message should be displayed prompting user to browse a file.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_UploadButton"))).click();
		Thread.sleep(400);
		Assert.assertTrue(isTextPresent("Select file to upload"));
		travelExpenseClaim.validationForAttachDocuments();
		Assert.assertTrue(isTextPresent("File Name"));
		Assert.assertTrue(isTextPresent("File Description"));
		Assert.assertTrue(isTextPresent("Attachment"));
		Assert.assertTrue(isTextPresent("Action"));
		Assert.assertTrue(isTextPresent(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtFileDescription")));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_grdAttachment_imgDelete"))));
		log("Verified : list of records displaying in a tabular format under following columns on the page:1) File Name2) Description3) Attachment4) Actions a) Delete");
		// delete the Attached Document by click of delete button.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_grdAttachment_imgDelete"))).click();
		Thread.sleep(100);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n Verified : Clicked to 'delete' button vrification Alert is :: "+alert.getText());
		Assert.assertEquals(alert.getText(), "Are you sure you want to delete attachment?");
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_grdAttachment_imgDelete"))));
		log("Verified : Clicked to 'delete' button and verified to Alert appearing, cancel the alert Attachment not deleted");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_grdAttachment_imgDelete"))).click();
		Thread.sleep(100);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Are you sure you want to delete attachment?");
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_grdAttachment_imgDelete"))));
		log("Verified, Clicked to 'delete' button and verified to Alert appearing and clicked to ok button, Added Attachment Deleted.");
		travelExpenseClaim.attachDocuments();
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_grdAttachment_imgDelete"))));
		log("Verified : File Attached Successfuly.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, After Added Expense Verify the Added Expense View Page to All Details are same as entered Data, Verify Details Page and view Comment page.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, After Added Expense Verify the Added Expense View Page to All Details are same as entered Data, Verify Details Page and view Comment page.",groups = { "fillAllExpenseSectionSaveAndVerifySummaryPage_TravelExpenseClaim" })
	public void fillAllExpenseSectionSaveAndVerifySummaryPage_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, After Added Expense Verify the Added Expense View Page to All Details are same as entered Data, Verify Details Page and view Comment page.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill All Expenses section in Travel Claim.
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		log("Verified : Total Expense of Travel Claim is Addition of All Total Expense in Travel Expense is - Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense);
		travelExpenseClaim.clickToSaveButton();
		Thread.sleep(1000);
		// Get Claim No.
		travelExpenseClaim.getAddedClaimtNo();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpenseList");
		log("Verified: application navigates to 'Travel Expense List' After fill All Expense and save.");
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		// Verify the Claim No., Requester name, Total Expense, Creation Date, Status, Actions.
		Assert.assertTrue(isElementPresent(By.linkText(TravelExpenseClaim.addedClaimNo)));
		log("Verified: Claim No. Is hyperlinked for all records displayed on the page.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		//Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_CreationDate"))).getText(), TravelExpenseClaim.addedDateTime);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Draft");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgDelete"))));
		log("Verified: The Claim No., Requester name, Total Expense, Creation Date, Status, Actions for Added Claim All data present in Respective Fields.");
		// After Added Expense Verify the Added Expense View Page to All Details are same as entered Data.
		// Click to view Img Button of Drafted Request, and Verify All Added Details and All fields nonEditable.
		travelExpenseClaim.clickToViewImgForAddedClaim();
		Assert.assertTrue(isTextPresent("Travel Expense Claim"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblApplicantName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lnkRequestNo"))).getText(), );
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBusinessPurpose"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblNoOfTickets"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblTotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("value"), "0.00");
		double balanceDue = Double.parseDouble(travelExpenseClaim.totalTravelClaimExpense+".00")-Double.parseDouble("0.00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDue).toString()+"0");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		log("Verified: On 'Added Expense View' Page, All Entered data details are Appearing, 'Submit' & 'Save' Button not displaying on View page.");
		// click 'Details List' Button and verify All Section's expenses and field should be nonEditable.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_DetailListButton"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("Claim Detail"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("value"), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor")), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		log("Verified: 'Filed For' & 'Filed By', field selected Loggedin user name and field is noneditable.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense);
		log("Verified : Travel Transportation Added one expense and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense);
		log("Verified : updated total expense amount displaying on local Transportation Header - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense);
		log("Verified : updated total expense amount displaying on Personal Meals Header - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+travelExpenseClaim.totalOtherExpense);
		log("Verified : in Other section Added one expense and on the header of Other - 'Others - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
		log("Verified : in Internal meeting section Added one expense and on the header of Internal meeting - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		log("Verified: On 'Claim Detail' Page, All Entered details are Appearing same, And All fields are nonEditable,  and 'Submit' & 'Save' Button not displaying on View page.");
		// come back to click of Back button.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))).click();
		// click 'View Comments' Button and verify All comments.
		travelExpenseClaim.clickToViewCommentButton();
		Assert.assertEquals(webDriver.getTitle(), "View Comments");
		Assert.assertTrue(isTextPresent("No records found."));
		webDriver.findElement(By.id("lnkCancel")).click();
		Thread.sleep(600);
		webDriver.switchTo().window(parentWindowHandle);
		log("Verified: For Drafted Claim on View Commetns Window having no Comments till now");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Verify Added claim Edit and Delete Action, Verify All Action.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Verify Added claim Edit and Delete Action, Verify All Action.",groups = { "fillAllExpenseSectionSaveEditAndDelete_TravelExpenseClaim" })
	public void fillAllExpenseSectionSaveEditAndDelete_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Verify Added claim Edit and Delete Action, Verify All Action.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill All Expenses section in Travel Claim.
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		log("Verified : Total Expense of Travel Claim is Addition of All Total Expense in Travel Expense is - Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense);
		travelExpenseClaim.clickToSaveButton();
		Thread.sleep(1000);
		travelExpenseClaim.getAddedClaimtNo();
		// click to Edit button to edit the Added Claim.
		travelExpenseClaim.clickToEditImgForAddedClaim();
		Assert.assertTrue(isTextPresent("Travel Expense"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("value"), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor"))).getAttribute("disabled"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor")), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		log("Verified: 'Filed For' & 'Filed By', field selected Loggedin user name and ClaimNo, 'Filed For' & 'Filed By' Fields is noneditable.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtRequestNo"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense);
		log("Verified : Travel Transportation Added one expense and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense);
		log("Verified : updated total expense amount displaying on local Transportation Header - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense);
		log("Verified : updated total expense amount displaying on Personal Meals Header - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+travelExpenseClaim.totalOtherExpense);
		log("Verified : in Other section Added one expense and on the header of Other - 'Others - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
		log("Verified : in Internal meeting section Added one expense and on the header of Internal meeting - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("readonly"), "true");
		log("Verified: On 'Travel Expense' Page, All Entered details are Appearing same entered Data,  and 'Submit', 'Save' & 'Back' Button displaying on Edit page.");
		// Edit The NoOfTickets field and Save it.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTicketsEdited"));
		travelExpenseClaim.clickToSaveButton();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		log("Verified: Edited the Added Claim and Seve it, Edited Successfuly, And Claim No Remain Same.");
		// Click to Claim No., verify page navigate to Edit Claim page.
		webDriver.findElement(By.linkText(TravelExpenseClaim.addedClaimNo)).click();
		Assert.assertTrue(isTextPresent("Travel Expense"));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		log("Verified: Clicked to Claim No link, verified page navigated to Edit Claim page.");
		travelExpenseClaim.clickToBackButton();
		// Click to delete button to delete the Added Claim.
		travelExpenseClaim.clickToDeleteImgForDratfedClaim();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n Verified: Click to Delete Img Button - Validation Msg :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		log("Verified: click cancle to Delete Alert, Claim not deleted.");
		travelExpenseClaim.clickToDeleteImgForDratfedClaim();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		log("\n\n Verified: Click to Delete Img Button - Validation Msg :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.linkText(TravelExpenseClaim.addedClaimNo)));
		log("Verified: click 'Ok' to Delete Alert, Cliam Deleted.");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Submit the Claim and verify the Claim Status and all details on View Summary page.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Submit the Claim and verify the Claim Status and all details on View Summary page.",groups = { "submitTravelExpenseClaim_VerifyStatusViewActionOnListPage" })
	public void submitTravelExpenseClaim_VerifyStatusViewActionOnListPage() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Submit the Claim and verify the Claim Status and all details on View Summary page.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill All Expenses section in Travel Claim.
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		log("Verified : Total Expense of Travel Claim is Addition of All Total Expense in Travel Expense is - Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense);
		travelExpenseClaim.clickToSubmitButtonAndCheckAlert();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n Verified: Click to Submit Button - Validation Msg :: "+alert.getText());
		alert.dismiss();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))));
		log("Verified: click cancle to Submit Claim Alert, Claim not Submited.");
		travelExpenseClaim.clickToSubmitButtonAndCheckAlert();
		Thread.sleep(1000);
		Alert alert1 = webDriver.switchTo().alert();
		log("\n\n Verified: Click to Submit Button - Validation Msg :: "+alert1.getText());
		alert1.accept();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))));
		log("Verified: click Ok' to Submit Claim Alert, Claim Submited Successful.");
		travelExpenseClaim.getAddedClaimtNo();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpenseList");
		log("Verified: application navigates to 'Travel Expense List' After fill All Expense and Submit Claim.");
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		// Verify the Claim No., Requester name, Total Expense, Creation Date, Status, Actions.
		Assert.assertTrue(isElementPresent(By.linkText(TravelExpenseClaim.addedClaimNo)));
		log("Verified: Claim No. Is hyperlinked for all records displayed on the page.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		//Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_CreationDate"))).getText(), TravelExpenseClaim.addedDateTime);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgCancel"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgDelete"))));
		log("Verified: the Requester name, Status, Creation Date, Actions for Added Request Detail and element present.");
		log("Verified: After Submit the Claim Status is : 'Awaiting approval from "+UserLogin.managerNameOfLoggedInUser+"'");
		log("Verified: The Claim No., Requester name, Total Expense, Creation Date, Status, Actions for Submited Claim All data present in Respective Fields.");
		// Click to view Img Button of Submitted Claim, and Verify All Added Details should be same as Entered Data, and fields nonEditable.
		travelExpenseClaim.clickToViewImgForAddedClaim();
		Assert.assertTrue(isTextPresent("Travel Expense Claim"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblApplicantName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lnkRequestNo"))).getText(), );
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBusinessPurpose"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblNoOfTickets"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblTotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("value"), "0.00");
		double balanceDue = Double.parseDouble(travelExpenseClaim.totalTravelClaimExpense+".00")-Double.parseDouble("0.00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDue).toString()+"0");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		log("Verified: On 'Submited Expense View' Page, All Entered data details are Appearing, 'Submit' & 'Save' Button not displaying on View page.");
		// click 'Details List' Button and verify All Section's expenses and field should be nonEditable.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_DetailListButton"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("Claim Detail"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("value"), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("readonly"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor")), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		log("Verified: 'Filed For' & 'Filed By', field selected Loggedin user name and field is noneditable.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense);
		log("Verified : Travel Transportation Added one expense and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense);
		log("Verified : updated total expense amount displaying on local Transportation Header - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense);
		log("Verified : updated total expense amount displaying on Personal Meals Header - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+travelExpenseClaim.totalOtherExpense);
		log("Verified : in Other section Added one expense and on the header of Other - 'Others - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
		log("Verified : in Internal meeting section Added one expense and on the header of Internal meeting - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		log("Verified: On 'Claim Detail' Page, All Entered details are Appearing same, And All fields are nonEditable,  and 'Submit' & 'Save' Button not displaying on View page.");
		// come back to click of Back button.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))).click();
		// click 'View Comments' Button and verify All comments.
		travelExpenseClaim.clickToViewCommentButton();
		Assert.assertEquals(webDriver.getTitle(), "View Comments");
		Assert.assertTrue(isTextPresent("No records found."));
		log("Verified: For Submited Claim on View Commetns Window having no Comments till now");
		webDriver.findElement(By.id("lnkCancel")).click();
		Thread.sleep(600);
		webDriver.switchTo().window(parentWindowHandle);
		// click 'Workflow Details' Button and verify All comments.
		travelExpenseClaim.clickToWorkFlowDetailsButton();
		Assert.assertEquals(webDriver.getTitle(), "Workflow Details");
		Assert.assertTrue(isTextPresent("Awaiting for Approval"));
		log("Verified: click to 'Workflow Details' button, Status is : 'Awaiting for Approval'");
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Submit the Claim and verify Cancel Action and and than Status than Edit the Cancel Claim.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Submit the Claim and verify Cancel Action and and than Status than Edit the Cancel Claim.",groups = { "submitTravelExpenseClaim_VerifyStatusViewActionOnListPage" })
	public void submitTravelExpenseClaim_VerifyCancelActionAndStatusOnListPage() throws Exception
	{
		header("Login as a Normal User and go to 'Travel Expense Claim', Fill All Expense Section, Submit the Claim and verify Cancel Action and and than Status than Edit the Cancel Claim.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// Go to Add Travel Expense claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		// Fill All Expenses section in Travel Claim.
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		travelExpenseClaim.clickToSubmitButton();
		travelExpenseClaim.getAddedClaimtNo();
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpenseList");
		if(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText().equalsIgnoreCase("Awaiting approval from "+UserLogin.managerNameOfLoggedInUser)){
			travelExpenseClaim.clickToCancelImgForAddedClaim();
			Thread.sleep(1000);
			Alert alert = webDriver.switchTo().alert();
			log("\n\n Verified : Click to Cancel Submited Claim, check the Validation Msg :: "+alert.getText());
			alert.dismiss();
			Thread.sleep(1000);
			log("\n\n Verified : Click to cancel the Cancle Alert, Claim not Cancelled");
			travelExpenseClaim.clickToCancelImgForAddedClaim();
			Thread.sleep(1000);
			Alert alert1 = webDriver.switchTo().alert();
			log("\n\n Click to Cancel Submited Request, check the Validation Msg :: "+alert1.getText());
			alert1.accept();
			Thread.sleep(1000);
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Cancelled");
			Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
			log("\n\n Verified : Accept Cancle Alert, Claim has been Cancelled");
			// Edit the Canceled Claim and submit the claim.
			travelExpenseClaim.clickToEditImgForAddedClaim();
			Assert.assertTrue(isTextPresent("Travel Expense"));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("disabled"), "true");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("value"), TravelExpenseClaim.addedClaimNo);
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor"))).getAttribute("disabled"), "true");
			Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor")), UserLogin.loggedInUserName);
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("disabled"), "true");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
			log("Verified: 'Filed For' & 'Filed By', field selected Loggedin user name and ClaimNo, 'Filed For' & 'Filed By' Fields is noneditable.");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtRequestNo"))).getAttribute("disabled"), "true");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense);
			log("Verified : Travel Transportation Added one expense and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
			log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense);
			log("Verified : updated total expense amount displaying on local Transportation Header - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense);
			log("Verified : updated total expense amount displaying on Personal Meals Header - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+travelExpenseClaim.totalOtherExpense);
			log("Verified : in Other section Added one expense and on the header of Other - 'Others - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
			log("Verified : in Internal meeting section Added one expense and on the header of Internal meeting - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
			Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_BackButton"))));
			Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
			Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("readonly"), "true");
			log("Verified: On 'Travel Expense' Page, All Entered details are Appearing same entered Data,  and 'Submit' & 'Back' Button displaying on Edit Cancel Claim page.");
			// Edit The NoOfTickets field and Save it.
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).clear();
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTicketsEdited"));
			travelExpenseClaim.clickToSubmitButton();
			String beforeCancelClaimNo = TravelExpenseClaim.addedClaimNo;
			travelExpenseClaim.getAddedClaimtNo();
			if(!beforeCancelClaimNo.equals(TravelExpenseClaim.addedClaimNo))
				log("Verified: Edited Canceled Claim and submit than New Claim No. are Generating ");
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
			log("Verified: Canceled Claim Edited and Submited with New Request No : "+TravelExpenseClaim.addedClaimNo);
		}
	}
	
	// Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, verify if 'Claim Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim.
	@Test (description="Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, verify if 'Claim Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim.",groups = { "verifyRequestAmountGreaterThanApproverRange_TravelExpenseClaim" })
	public void verifyRequestAmountGreaterThanApproverRange_TravelExpenseClaim() throws Exception
	{
		header("Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, verify if 'Claim Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		UserLogin userLogin = new UserLogin();
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to get the 'Travel Expense Range Amount'.");
		// Go to 'User Profile' in 'Master' Tab and get the 'Travel Expense Range Amount'
		travelExpenseClaim.getTravelExpenseAmountFromUserProfile();
		// Do the Login as Normal User to Add the new claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Logged in as Normal User to Add the new claim.");
		travelExpenseClaim.claimForTravelExpense();
		// On Travel Expense Listing page - click to 'Add New Button' to add new claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		// On Travel Expense Form page - Fill All Expense Information for 'Travel Expense Claim'.
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		travelExpenseClaim.fillTravelTransportationSectionForExceedAmount();
		travelExpenseClaim.clickToAddButnTravelTransportation();
		travelExpenseClaim.clickToSaveButnTravelTransportation();
		Thread.sleep(1000);
		travelExpenseClaim.getTotalExpenseInTravelClaim();
		int requestAmount = Integer.parseInt(travelExpenseClaim.totalTravelClaimExpense);
		int approvalAmount = Integer.parseInt(TravelExpenseClaim.managerApprovalAmount);
		// verify if 'Request Amount' Greater Than 'Approver Range Amount' than Claim should not submit (Alert Displaying) while submitting claim.
		if(requestAmount > approvalAmount){
			log("Verified: Request Amount is greater than Manager Approval Amount.");
			travelExpenseClaim.clickToSubmitButton();
			Alert alert1 = webDriver.switchTo().alert();
			log("\n\n Click to Submit and, check the Validation Msg if 'Request Amount is greater than Manager Approval Amount' :: "+alert1.getText());
			alert1.accept();
			Thread.sleep(1000);
			log("Verified: Alert Diplaying because Request Amount is greater than Manager Approval Amount.");
			// Edit the Expense.
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
			travelExpenseClaim.editAddedTravelTransportationDetails();
			travelExpenseClaim.clickToSaveButnTravelTransportation();
			travelExpenseClaim.clickToSubmitButton();
		}
		else
			log("Verified: Alert not Diplaying because Claim Amount is not greater than Manager Approval Amount.");
	}
	
	// Login as a Normal User, Submit 'Travel Expense Claim'  in Claim Tab, and Verify the Reject and Approve claim from his Manager(Admin).
	@Test (description="Login as a Normal User, Submit 'Travel Expense Claim'  in Claim Tab, and Verify the Reject and Approve claim from his Manager(Admin).",groups = { "submitTravelExpenseClaimAndRejectApproveFromAdmin" })
	public void submitTravelExpenseClaimAndRejectApproveFromAdmin() throws Exception
	{
		header("Login as a Normal User, Submit 'Travel Expense Claim'  in Claim Tab, and Verify the Reject and Approve claim from his Manager(Admin).");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		UserLogin userLogin = new UserLogin();
		travelExpenseClaim.claimForTravelExpense();
		// On 'Travel Expense Claim' Listing page - click to 'Add New Button' to add new claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		// On 'Travel Expense Claim' Listing page - Fill All Expense Information.
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		// Submit a Claim.
		travelExpenseClaim.clickToSubmitButton();
		travelExpenseClaim.getAddedClaimtNo();
		log("Verified: Normal User Submited Claim Successfuly");
		userLogin.userLogout();
		// Do the Login as Admin User (Manager of Normal User).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		Assert.assertTrue(isElementPresent(By.linkText("My Approvals")));
		webDriver.findElement(By.linkText("My Approvals")).click();
		Assert.assertTrue(isTextPresent("Need My Approval List"));
		// Verify Added Request by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_ClaimType"))).getText(), "Travel Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_LastModified"))).getText(), TravelExpenseClaim.addedDateTime);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		Assert.assertTrue(isElementPresent(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))));
		log("Verified: All recard added by Normal user same displaying in corresponding fields.");
		// Click to View Button and verify that All Details are Displaying on Summary page.
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblApplicantName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lnkRequestNo"))).getText(), );
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBusinessPurpose"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblNoOfTickets"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblTotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("value"), "0.00");
		double balanceDueAdmin = Double.parseDouble(travelExpenseClaim.totalTravelClaimExpense+".00")-Double.parseDouble("0.00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDueAdmin).toString()+"0");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_ApproveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_RejectButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_DetailListButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_WorkflowButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_ViewCommentsButton"))));
		log("Verified: On 'Submited Expense View' Page, All Entered data details are Appearing, 'Submit' & 'Save' Button not displaying on View page.");
		log("Verified: On My Approvale Summary page 'DetailListButton' 'WorkflowButton' 'ViewCommentsButton' 'Approve' and 'Reject' Button displaying.");
		// Reject the Claim.
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_RejectButton"))).click();
		Assert.assertFalse(isTextPresent(TravelExpenseClaim.addedClaimNo));
		log("Verified: Manager Rejected The rquest that not Appearing on 'Need My Approval List' page");
		// Do the Login as Normal User to edit the rejected claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Logged in as Normal to update his/her Rejected Claim.");
		travelExpenseClaim.claimForTravelExpense();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Rejected");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
		log("Verified: Claim Rejected by Admin status is 'Rejected' and only View & Edit button present.");
		// Click to view Img Button of Rejected Claim, and Verify All Added Details should be same as Entered Data, and fields nonEditable.
		travelExpenseClaim.clickToViewImgForAddedClaim();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblApplicantName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lnkRequestNo"))).getText(), );
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBusinessPurpose"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblNoOfTickets"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblTotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("value"), "0.00");
		double balanceDue = Double.parseDouble(travelExpenseClaim.totalTravelClaimExpense+".00")-Double.parseDouble("0.00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDue).toString()+"0");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_DetailListButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_WorkflowButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_ViewCommentsButton"))));
		log("Verified: On 'Submited Expense View' Page, All Entered data details are Appearing, 'Submit' & 'Save' Button not displaying on View page.");
		log("Verified: On My Approvale Summary page 'DetailListButton' 'WorkflowButton' 'ViewCommentsButton' and 'Back' Button displaying.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))).click();
		// Edit the Rejected Claim and submit the claim.
		travelExpenseClaim.clickToEditImgForAddedClaim();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtClaimNo"))).getAttribute("value"), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor"))).getAttribute("disabled"), "true");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_ddlFilledFor")), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFilledBy"))).getAttribute("value"), UserLogin.loggedInUserName);
		log("Verified: 'Filed For' & 'Filed By', field selected Loggedin user name and ClaimNo, 'Filed For' & 'Filed By' Fields is noneditable.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtRequestNo"))).getAttribute("disabled"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).getText(), "Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense);
		log("Verified : Travel Transportation Added one expense and on the header of Travel Transportation - 'Travel Transportation - Sub Total: "+travelExpenseClaim.totalTravelTransportationExpense+"' displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).getText(), "Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense);
		log("Verified : in Lodging Added one expense and on the header of Lodging - 'Lodging - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).getText(), "Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense);
		log("Verified : updated total expense amount displaying on local Transportation Header - 'Local Transportation (Travel Expense) - Sub Total: "+travelExpenseClaim.totalLocalTransportationExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).getText(), "Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense);
		log("Verified : updated total expense amount displaying on Personal Meals Header - 'Personal Meal - Sub Total: "+travelExpenseClaim.totalPersonalMealExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).getText(), "Others - Sub Total: "+travelExpenseClaim.totalOtherExpense);
		log("Verified : in Other section Added one expense and on the header of Other - 'Others - Sub Total: "+travelExpenseClaim.totalLodgingExpense+"' is displaying.");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).getText(), "Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense);
		log("Verified : in Internal meeting section Added one expense and on the header of Internal meeting - 'Internal meeting/Entertainment/Business Meals - Sub Total: "+travelExpenseClaim.totalInternalMeetingExpense+"' is displaying.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))));
		Assert.assertTrue(isTextPresent("Total Amount (RMB) "+travelExpenseClaim.totalTravelClaimExpense));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("readonly"), "true");
		log("Verified: On 'Travel Expense' Edit Page, All Entered details are Appearing same entered Data,  and 'Submit' & 'Back' Button displaying on Edit Rejected Claim page.");
		// Edit The NoOfTickets field and Submit it.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTicketsEdited"));
		travelExpenseClaim.clickToSubmitButton();
		String rejestedClaimNo = TravelExpenseClaim.addedClaimNo;
		travelExpenseClaim.getAddedClaimtNo();
		if(!rejestedClaimNo.equals(TravelExpenseClaim.addedClaimNo))
			log("Verified : Rejected Claim Updated and submited : new claim no. generated");
		else
			log("Verified : Rejected Claim Updated and submited : Not generated New Claim No.");
		userLogin.userLogout();
		// Do the Login as Admin User (Manager).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		Assert.assertTrue(isElementPresent(By.linkText("My Approvals")));
		webDriver.findElement(By.linkText("My Approvals")).click();
		Assert.assertTrue(isTextPresent("Need My Approval List"));
		// Verify Added Request by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		Thread.sleep(1000);
		// Approve the Claim.
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_ApproveButton"))).click();
		Thread.sleep(1000);
		// Do the Login as Normal User to check Approved claim.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Logged in as Normal to Check Approved Claim.");
		travelExpenseClaim.claimForTravelExpense();
		// Verified: claim Status is 'Approved' after Approved by Manager.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
		log("Verified: claim Status is 'Waiting for Finance 2 to settle' after Approved by Manager.");
		// Verified: for claim Status 'Waiting for Finance 2 to settle' only 'View' and 'cancel' Img button displaying.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgCancel"))));
		log("Verified: for claim Status 'Approved' only 'View' and 'cancel' Img button displaying.");
		// Verified: for claim Status 'Waiting for Finance 2 to settle' 'Edit', 'Delete' Img button not displaying.
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgDelete"))));
		log("Verified: for claim Status 'Rejected' : 'Edit', 'Delete' Img button not displaying.");
		// Verify Rejected Claim No. has been Rejected Yet.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_RejectedStatus"))).getText(), "Rejected");
		log("Verified:  Rejected Claim No. has been Rejected Yet.");
		// Verified: for claim Status 'Rejected' only 'View' Img button displaying.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_RejectedimgView"))));
		log("Verified: for claim Status 'Rejected' only 'View' Img button displaying.");
		// Verified: for claim Status 'Rejected' 'Edit', 'Delete' and 'cancel' Img button not displaying.
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_RejectedimgEdit"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_RejectedimgDelete"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_RejectedimgCancel"))));
		log("Verified: for claim Status 'Rejected' : 'Edit', 'Delete' and 'cancel' Img button not displaying.");
	}
	
	// Login as a Normal User, Submit 'Travel Expense Claim' in Claim Tab, Reject & Return claim By Finance User and verify All Status on Corresponding page.
	@Test (description="Login as a Normal User, Submit 'Travel Expense Claim' in Claim Tab, Reject & Return claim By Finance User and verify All Status on Corresponding page.",groups = { "submitTravelExpenseClaimAnd_ApproveByAdminAndRejectReturnByFinanceUser" })
	public void submitTravelExpenseClaimAnd_ApproveByAdminAndRejectReturnByFinanceUser() throws Exception
	{
		header("Login as a Normal User, Submit 'Travel Expense Claim' in Claim Tab, Reject & Return claim By Finance User and verify All Status on Corresponding page.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		UserLogin userLogin = new UserLogin();
		travelExpenseClaim.claimForTravelExpense();
		// On 'Travel Expense Claim' Listing page - click to 'Add New Button' to add new claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		// On 'Travel Expense Claim' Listing page - Fill All Expense Information.
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		// Submit a Claim.
		travelExpenseClaim.clickToSubmitButton();
		travelExpenseClaim.getAddedClaimtNo();
		log("Verified: Normal User Submited Claim Successfuly");
		userLogin.userLogout();
		// Do the Login as Admin User (Manager of Normal User).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		webDriver.findElement(By.linkText("My Approvals")).click();
		// Click to View Button and Approve the Claim.
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		// Approve the Claim by Admin.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_txtApproverComment"))).sendKeys(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtApproverCommentByAdminUser"));
		travelExpenseClaim.clickToApproveButtonByAdmin();
		userLogin.userLogout();
		// Do the Login as Finance User to Settle the Approved Claim by Admin.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("FinanceUserName"), sRPF.userLogin_Data.get("FinancePassword"));
		//Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Finance User Loggedin to Settel Normal User's Approved Claim.");
		travelExpenseClaim.goToUnsettledClaimsAsFinanceUser();
		Assert.assertTrue(isTextPresent("Listing of Unsettled Claims"));
		// Verify application navigates to view 'Unsettled Claims' page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "UnsettledClaimList");
		log("Verified: application navigates to 'UnsettledClaimList' page by click on 'Unsettled Claims' link in claim tab");
		// Verify Added Details by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimType"))).getText(), "Travel Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		//Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_CreationDate"))).getText(), TravelExpenseClaim.addedDateTime);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
		Assert.assertTrue(isElementPresent(By.linkText(TravelExpenseClaim.addedClaimNo)));
		log("Verified: All Details added by Normal user same displaying in corresponding Table.");
		// Click to Claim Link to go to Summary page.
		webDriver.findElement(By.linkText(TravelExpenseClaim.addedClaimNo)).click();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblApplicantName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lnkRequestNo"))).getText(), );
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBusinessPurpose"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblNoOfTickets"))).getText(), sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblTotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		// Verify 'Cash Advance' should be Editable and total expense minus 'CashAdvance' added by finance user equals to 'Balance Due Company To Employee (RMB)'.
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).isEnabled());
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("value"), "0.00");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TEViewSummary_txtCashAdvance"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).click();
		double balanceDueFinance = Double.parseDouble(travelExpenseClaim.totalTravelClaimExpense+".00")-Double.parseDouble(sRPF.travelExpenseClaim_Data.get("TEViewSummary_txtCashAdvance")+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDueFinance).toString()+"0");
		log("Verified : 'Cash Advance' is Editable and total expense minus 'CashAdvance' added by finance user is equals to 'Balance Due Company To Employee (RMB)'");
		// Verify Requester name and Approver name on Summary page.
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_lblRequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_lblApproverName"))).getText(), UserLogin.managerNameOfLoggedInUser);
		log("Verified : Requestor name and Approvar name on Summary page Present.");
		// Verify Approver comment field is Editable
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_txtApproverComment"))));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_txtApproverComment"))).sendKeys(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtSettledCommentByFinanceUser"));
		log("Verified : Approver comment field is Editable.");
		// Verify All Button Enable.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_DetailListButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_WorkflowButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_CountersignButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_FinanceRejectButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_FinanceReturnButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_SettleButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_PrintButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_ViewCommentsButton"))));
		log("Verified : All Button Present and Enable.");
		log("Verified: On 'Finance Sattle View' Page, All Entered data details are Appearing.");
		log("Verified: Finance Summary page.");
		// Click to Reject Button and again submit by Normal User.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_FinanceRejectButton"))).click();
		Assert.assertFalse(isTextPresent(TravelExpenseClaim.addedClaimNo));
		log("Verified: Rejeted Claim By Finance User and Claim removing from Unsettled List.");
		// Do the Login as Normal User to edit the rejected claim by Finance User.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Logged in as Normal to update his/her Rejected Claim.");
		travelExpenseClaim.claimForTravelExpense();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Rejected");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
		log("Verified: Logged in As a Normal User and getting Claim Rejected.");
		// Edit the Rejected Claim and submit the claim.
		travelExpenseClaim.clickToEditImgForAddedClaim();
		// Edit The NoOfTickets field and Submit it.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTicketsEdited"));
		travelExpenseClaim.clickToSubmitButton();
		log("Verified: Edited Rejected Claim and Submited it.");
		String rejestedClaimNo = TravelExpenseClaim.addedClaimNo;
		travelExpenseClaim.getAddedClaimtNo();
		if(!rejestedClaimNo.equals(TravelExpenseClaim.addedClaimNo))
			log("Rejected Claim Updated and submited : new claim no. generated");
		else
			log("Rejected Claim Updated and submited : Not generated New Claim No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		log("Verified: Edited Rejected Claim and Submited and Status Become : Awaiting approval from "+UserLogin.managerNameOfLoggedInUser);
		userLogin.userLogout();
		// Do the Login as Admin User (Manager) to Approve Claim.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		webDriver.findElement(By.linkText("My Approvals")).click();
		// Verify Added Claim by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		Thread.sleep(1000);
		// Approve the Claim By Admin.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_txtApproverComment"))).sendKeys(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtApproverCommentByAdminUser"));
		travelExpenseClaim.clickToApproveButtonByAdmin();
		userLogin.userLogout();
		// Do the Login as Finance User to Settle the Approved Claim by Admin.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("FinanceUserName"), sRPF.userLogin_Data.get("FinancePassword"));
		//Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Finance User Loggedin to Settel Normal User's Approved Claim.");
		travelExpenseClaim.goToUnsettledClaimsAsFinanceUser();
		// Verify application navigates to view 'Unsettled Claims' page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "UnsettledClaimList");
		log("Verified: application navigates to 'UnsettledClaimList' page by click on 'Unsettled Claims' link in claim tab");
		// Verify Added Details by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimType"))).getText(), "Travel Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
		log("Verified: All Details added by Normal user same displaying in corresponding Table.");
		// Click to Claim Link to go to Summary page to Click Return button.
		webDriver.findElement(By.linkText(TravelExpenseClaim.addedClaimNo)).click();
		// Click to Return Button and again submit by Normal User.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_FinanceReturnButton"))).click();
		Assert.assertFalse(isTextPresent(TravelExpenseClaim.addedClaimNo));
		log("Verified: Return Claim By Finance User and Claim removing from Unsettled List.");
		// Do the Login as Normal User to Submit the Returned claim by Finance User.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Logged in as Normal to update his/her Returned Claim.");
		travelExpenseClaim.claimForTravelExpense();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Returned");
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))));
		log("Verified: Logged in As a Normal User and getting Claim Status 'Returned'");
		// Edit the Returned Claim and submit the claim.
		travelExpenseClaim.clickToEditImgForAddedClaim();
		// Edit The NoOfTickets field and Submit it.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTicketsEdited"));
		travelExpenseClaim.clickToSubmitButton();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
		log("Verified: Edited Returned Claim and Submited and Status Become : 'Waiting for Finance 2 to settle'");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		log("Verified: Normal ser Submited Returned Claim by Finance User and verified Claim No. is same as before.");
		userLogin.userLogout();
		// Do the Login as Finance User to Settle Claim.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("FinanceUserName"), sRPF.userLogin_Data.get("FinancePassword"));
		log("Verified: Finance User Loggedin to Settel Normal User's Claim.");
		travelExpenseClaim.goToUnsettledClaimsAsFinanceUser();
		// Verify application navigates to view 'Unsettled Claims' page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "UnsettledClaimList");
		log("Verified: application navigates to 'UnsettledClaimList' page by click on 'Unsettled Claims' link in claim tab");
		// Verify Added Details by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimType"))).getText(), "Travel Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
		log("Verified: Noraml User Submited Claim Returned by Finance User and 'Finance User' getting diretly Status is : 'Waiting for Finance 2 to settle'.");
		log("Verified: All Details added by Normal user displaying Same in corresponding Table.");
	}
	
	// Login as a Normal User, Submit 'Travel Expense Claim'  in Claim Tab, Added Claim Approve By Admin and Settle claim By Finance User And Verify All Claim Details, Work Flow and Comment on Claim Summary page.
	@Test (description="Login as a Normal User, Submit 'Travel Expense Claim'  in Claim Tab, Added Claim Approve By Admin and Settle claim By Finance User And Verify All Claim Details, Work Flow and Comment on Claim Summary page.",groups = { "submitTravelExpenseClaim_ApproveByAdminAndSettleByFinanceUser" })
	public void submitTravelExpenseClaim_ApproveByAdminAndSettleByFinanceUser() throws Exception
	{
		header("Login as a Normal User, Submit 'Travel Expense Claim'  in Claim Tab, Added Claim Approve By Admin and Settle claim By Finance User And Verify All Claim Details, Work Flow and Comment on Claim Summary page.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		UserLogin userLogin = new UserLogin();
		travelExpenseClaim.claimForTravelExpense();
		// On 'Travel Expense Claim' Listing page - click to 'Add New Button' to add new claim.
		travelExpenseClaim.clickToAddNewClaimBtn();
		// On 'Travel Expense Claim' Listing page - Fill All Expense Information.
		travelExpenseClaim.fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim();
		travelExpenseClaim.fillAllExpenseSectionInTravelClaim();
		// Submit a Claim.
		travelExpenseClaim.clickToSubmitButton();
		travelExpenseClaim.getAddedClaimtNo();
		log("Verified: Normal User Submited Claim Successfuly");
		userLogin.userLogout();
		// Do the Login as Admin User (Manager of Normal User).
		userLogin.loginAsUser(sRPF.userLogin_Data.get("AdminUserName"), sRPF.userLogin_Data.get("AdminPassword"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Logged in as Manager (Admin) of Normal User to Approve his/her Claim.");
		// Click to 'My Approval' Tab to Approve Claim.
		webDriver.findElement(By.linkText("My Approvals")).click();
		// Click to View Button and Approve the Claim.
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_DataGrid_imgViewBtn"))).click();
		// Approve the Claim by Admin.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_txtApproverComment"))).sendKeys(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtApproverCommentByAdminUser"));
		travelExpenseClaim.clickToApproveButtonByAdmin();
		userLogin.userLogout();
		// Do the Login as Finance User to Settle the Approved Claim by Admin.
		userLogin.loginAsUser(sRPF.userLogin_Data.get("FinanceUserName"), sRPF.userLogin_Data.get("FinancePassword"));
		//Assert.assertTrue(isTextPresent("Welcome "+UserLogin.managerNameOfLoggedInUser));
		log("Verified: Finance User Loggedin to Settel Normal User's Approved Claim.");
		travelExpenseClaim.goToUnsettledClaimsAsFinanceUser();
		Assert.assertTrue(isTextPresent("Listing of Unsettled Claims"));
		// Verify application navigates to view 'Unsettled Claims' page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "UnsettledClaimList");
		log("Verified: application navigates to 'UnsettledClaimList' page by click on 'Unsettled Claims' link in claim tab");
		// Verify Added Details by Normal User details are matching.
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_ClaimType"))).getText(), "Travel Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_TotalExpense"))).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		//Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_CreationDate"))).getText(), TravelExpenseClaim.addedDateTime);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
		log("Verified: All Details added by Normal user same displaying in corresponding Table.");
		// Click to Claim Link to go to Summary page to Click Settle button.
		webDriver.findElement(By.linkText(TravelExpenseClaim.addedClaimNo)).click();
		// Verify 'Cash Advance' should be Editable and total expense minus 'CashAdvance' added by finance user equals to 'Balance Due Company To Employee (RMB)'.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TEViewSummary_txtCashAdvance"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).click();
		double balanceDueFinanceUser = Double.parseDouble(travelExpenseClaim.totalTravelClaimExpense+".00")-Double.parseDouble(sRPF.travelExpenseClaim_Data.get("TEViewSummary_txtCashAdvance")+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDueFinanceUser).toString()+"0");
		log("Verified : 'Cash Advance' is Editable and total expense minus 'CashAdvance' added by finance user is equals to 'Balance Due Company To Employee (RMB)'");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_txtApproverComment"))).sendKeys(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtSettledCommentByFinanceUser"));
		// Click to Settle Button.
		travelExpenseClaim.clickToSettleButton();
		Assert.assertFalse(isTextPresent(TravelExpenseClaim.addedClaimNo));
		log("Verified: By Finance User Clicked to Settle Button and Claim No. Removing from Unsettled List.");
		// Check Finance 'Settled Claim' page to Verify Settled Claim No. are Present in List.
		travelExpenseClaim.goToSettledClaimsAsFinanceUser();
		Assert.assertTrue(isTextPresent("Listing of Settled Claims"));
		// Verify application navigates to view 'Settled Claims' page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "SettledClaimList");
		log("Verified: Application navigates to 'SettledClaimList' page by click on 'Settled Claims' link in claim tab");
		// Search with Claim No.
		selectBylabel("ctl00_cphMain_ddlSearchCriteria", "Claim Number");
		webDriver.findElement(By.id("ctl00_cphMain_txtSearchKey")).sendKeys(TravelExpenseClaim.addedClaimNo);
		webDriver.findElement(By.id("ctl00_cphMain_lnkSearch")).click();
		Thread.sleep(2000);
		Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_grdSettledClaim_grdSettledClaim_1_lnkBtnViewClaim")));
		Assert.assertEquals(webDriver.findElement(By.id("ctl00_cphMain_grdSettledClaim_grdSettledClaim_0_lnkBtnViewClaim")).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdSettledClaim_grdSettledClaim_0']/td[3]")).getText(), UserLogin.loggedInUserName);
		Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdSettledClaim_grdSettledClaim_0']/td[4]")).getText(), "Travel Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdSettledClaim_grdSettledClaim_0']/td[5]")).getText(), travelExpenseClaim.totalTravelClaimExpense+".00");
		//Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdSettledClaim_grdSettledClaim_0']/td[6]")).getText(), TravelExpenseClaim.addedDateTime);
		log("Verified: Claim Settled by Finance User displaying with All Claim Details on 'Settled Claim' page.");
		// Click to Claim Link to go to Summary page to Click Settle button.
		webDriver.findElement(By.linkText(TravelExpenseClaim.addedClaimNo)).click();
		// Verify All Button Enable.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_DetailListButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_WorkflowButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_FinanceRejectButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_FinanceReturnButton"))));
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_SettleButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_BackButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_PrintButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_ViewCommentsButton"))));
		log("Verified: 'Reject', 'Return' & 'Settle' Button Not Displaying on 'Settled Claim' page.");
		// Do the Login as Normal User to verify Settled claim by Finance User.
		userLogin.userLogout();
		userLogin.loginAsUser(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		Assert.assertTrue(isTextPresent("Welcome "+UserLogin.loggedInUserName));
		log("Verified: Logged in as Normal to update his/her Returned Claim.");
		travelExpenseClaim.claimForTravelExpense();
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), TravelExpenseClaim.addedClaimNo);
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Settled");
		log("Verified: On Normal User page Claim Status is 'Settled'.");
		travelExpenseClaim.clickToViewImgForAddedClaim();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("readonly"), "true");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_txtCashAdvance"))).getAttribute("value"), sRPF.travelExpenseClaim_Data.get("TEViewSummary_txtCashAdvance")+".00");
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_lblBalanceDue"))).getText(), new Double(balanceDueFinanceUser).toString()+"0");
		log("Verified: Settlement Done - Verified 'Cash advance' amount added by Finance user and 'Balance Due Company To Employee (RMB)' are balencing from 'Total Treval Expense'.");
		// Verify Claim Submitted Date, Admin Approved Date and Finance Settled Date on User Claim Summary Page. 
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_lblClaimCreationDate"))).getText(), TravelExpenseClaim.claimSubmitedDate);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_lblFinance2SettleDate"))).getText(), TravelExpenseClaim.financeSettledDate);
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_lblAdminApprovedDate"))).getText(), TravelExpenseClaim.adminApprovedDate);
		log("Verified: Claim Submitted Date, Admin Approved Date and Finance Settled Date on User Claim Summary Page.");
		// click 'View Comments' Button and verify All comments.
		travelExpenseClaim.clickToViewCommentButton();
		Assert.assertEquals(webDriver.getTitle(), "View Comments");
		Assert.assertTrue(isTextPresent(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtApproverCommentByAdminUser")));
		Assert.assertTrue(isTextPresent(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtSettledCommentByFinanceUser")));
		log("Verified: For Settled Claim, on View Commetns Window displaying Same Comment sent by Admin User & Finance User");
		webDriver.findElement(By.id("lnkCancel")).click();
		Thread.sleep(600);
		webDriver.switchTo().window(parentWindowHandle);
		// click 'Workflow Details' Button and verify All comments.
		travelExpenseClaim.clickToWorkFlowDetailsButton();
		Assert.assertEquals(webDriver.getTitle(), "Workflow Details");
		Assert.assertTrue(isTextPresent("Settled"));
		Assert.assertTrue(isTextPresent(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtApproverCommentByAdminUser")));
		Assert.assertTrue(isTextPresent(sRPF.travelExpenseClaim_Data.get("UnsettledClaimList_txtSettledCommentByFinanceUser")));
		log("Verified: For Settled Claim, on View Commetns Windows displaying Same Comment sent by Admin User & Finance User");
		log("Verified: For Settled Claim, On 'Workflow Details' windows Diplaying, Status is : 'Settled' And Same Comment sent by Admin User & Finance User");
	}
	
	// Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, Check and verify paging on the 'My Travel Claim List' page.
	@Test (description="Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, Check and verify paging on the 'My Travel Claim List' page.",groups = { "checkVerifyPagingOnTravelExpenseClaimListPage" })
	public void checkVerifyPagingOnTravelExpenseClaimListPage() throws Exception
	{
		header("Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, Check and verify paging on the 'My Travel Claim List' page.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// On My Travel Claim List page - Check and verify paging.
		travelExpenseClaim.checkAndVerifyForPaging();
		log("Paging Verification Done on 'My Travel Claim List' page.");
	}
	
	// Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, On My Travel Claim List page - Check and verify For Search Option are Searching as per functionality.
	@Test (description="Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, On My Travel Claim List page - Check and verify For Search Option are Searching as per functionality.",groups = { "verifySearchFunctionalityOnTravelExpenseClaimListPage" })
	public void verifySearchFunctionalityOnTravelExpenseClaimListPage() throws Exception
	{
		header("Login as a Normal User and Click to 'Travel Expense Claim' in Claim Tab, On My Travel Claim List page - Check and verify For Search Option are Searching as per functionality.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		travelExpenseClaim.claimForTravelExpense();
		// On My Travel Claim List page - Check and verify For Search Option is searching as per functionality.
		travelExpenseClaim.checkVerifyForSearchOptionOnTravelExpenseClaimListPage();
		log("Verified: Search Functionality");
	}
	
	// Login as a Normal User go to 'Travel Expense Claim' Listing page and verify to Sorting of Claims list by 'Claim no.' and 'Creation date'.
	@Test (description="Login as a Normal User go to 'Travel Expense Claim' Listing page and verify to Sorting of Claims list by 'Claim no.' and 'Creation date'.' message display.",groups = { "verifySortingOfListsByClaimNoAndCreationDate" })
	public void verifySortingOfListsByClaimNoAndCreationDate() throws Exception
	{
		header("Login as a Normal User go to 'Travel Expense Claim' Listing page and verify to Sorting of Claims list by 'Claim no.' and 'Creation date'.");
		sRPF = new Set_Read_ProperpertiesFile(); 
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		// go to Travel Expense Claim page and Login as a Normal User go to 'Travel Expense Claim' Listing page and verify to Sorting of Claims list by 'Claim no.' and 'Creation date'.
		travelExpenseClaim.claimForTravelExpense();
		String lastClaimNo = webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText();
		String lastClaim = CommonUse.getSubStringFoRequestNo(lastClaimNo);
		String secondLastClaimNo = webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataSecondGrid_ClaimNo"))).getText();
		String secondLastClaim = CommonUse.getSubStringFoRequestNo(secondLastClaimNo);
		// verify all the records displayed are sorted in descending order default with respect to the records in the respective column.
		if(Integer.parseInt(lastClaim) > Integer.parseInt(secondLastClaim))
			log("Verified all the records displayed are sorted in descending order default with respect to the records in the respective column.");
		else
			log("Verified all the records displayed are sorted in ascending order default with respect to the records in the respective column.");
		String lastClaimAddedDate = webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_CreationDate"))).getText();
		// click to Claim No. column list link to check the sorting of list.
		webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[1]/th[1]/a")).click();
		Thread.sleep(1000);
		try{
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), lastClaimNo);
			log("Verified (checked with Claim no.): by click of 'Claim No. column list link' request lists are Not sortign in accending order.");
		}
		catch(Throwable e){
			log("Verified (checked with Claim no.) : by click of 'Claim No. column list link' request lists are sortign in accending order.");
		}
		try{
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_CreationDate"))).getText(), lastClaimAddedDate);
			log("Verified (checked with creation date) : by click of 'Claim No column list link' request lists are Not sortign in accending order.");
		}
		catch(Throwable t){
			log("Verified (checked with creation date) : by click of 'Claim No column list link' request lists are sortign in accending order.");
		}
		travelExpenseClaim.claimForTravelExpense();
		String lastClaimNoD = webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText();
		String lastClaimAddedDateD = webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_CreationDate"))).getText();
		// click to creation date column list link to check the sorting of list.
		webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[1]/th[4]/a")).click();
		Thread.sleep(1000);
		try{
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), lastClaimNoD);
			log("Verified (checked with Claim no.): by click of 'creation date column list link' request lists are Not sortign in accending order.");
		}
		catch(Throwable e){
			log("Verified (checked with Claim no.) : by click of 'creation date column list link' request lists are sortign in accending order.");
		}
		try{
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_CreationDate"))).getText(), lastClaimAddedDateD);
			log("Verified (checked with creation date) : by click of 'creation date column list link' request lists are Not sortign in accending order.");
		}
		catch(Throwable e){
			log("Verified (checked with creation date) : by click of 'creation date column list link' request lists are sortign in accending order.");
		}
	}
}
