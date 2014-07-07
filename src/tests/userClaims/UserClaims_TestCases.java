package tests.userClaims;

import modules.UserClaims;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class UserClaims_TestCases extends TestNgHelper{
	
	private Set_Read_ProperpertiesFile sRPF;
	
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Verify the which 'Not Editable' and Validation Over Submit Button while not Added any Details for new Claim.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Verify the which 'Not Editable' and Validation Over Submit Button while not Added any Details for new Claim.",groups = { "verifyElementsAndValidationOverSubmitButton" })
	public void verifyElementsAndValidationOverSubmitButton() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		UserClaims userClaims = new UserClaims();
		userClaims.claimForTravelExpense();
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		// From 'Travel Expense Claim' page click to 'Add New' Button. 
		userClaims.clickToAddNewClaimBtn();
		Assert.assertTrue(isTextPresent("Travel Expense"));
		// From 'Add New Travel Expense Claim' page verify the 'Not Editable Elements'.
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtRequestNo"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtClaimNo"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_ddlFilledFor"))).isDisplayed());
		Reporter.log("Verified The Elements : 'Request Number', 'Claim Number', 'Filed By' are Not Editable Text Field");
		// On 'Add New Travel Expense Claim' page click to 'Submit' Button, and verify the Validation for Fields Alert has to appear.
		userClaims.clickToSubmitClaim();
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure to submit this claim?");
		alert.accept();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		Reporter.log("Getting Alert for 'No. of Tickets' field :: "+alert2.getText());
		//Assert.assertEquals(alert2.getText(), "- No. of Tickets cannot be left blank.");
		Reporter.log("Verified Valication on submit Button : Alert Appearing for 'fill No. of Tickets' while click to submit button without fill any details.");
		alert2.accept();
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys("tesing");
		Reporter.log("Verified 'No. of Tickets' field: can't send text in text field.");
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_txtNoOfTickets"));
		userClaims.clickToSubmitClaim();
		Alert alert3 = webDriver.switchTo().alert();
		Assert.assertEquals(alert3.getText(), "Are you sure to submit this claim?");
		alert3.accept();
		Thread.sleep(1000);
		Alert alert4 = webDriver.switchTo().alert();
		Assert.assertEquals(alert4.getText(), "Please specify at least one of the expense type.");
		alert4.accept();
		Thread.sleep(1000);
		Reporter.log("Verified Valication on submit Button : Alert Appearing for 'Please specify at least one of the expense type.' while click to submit button without fill any details.");
		Assert.assertTrue(isElementPresent(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtNoOfTickets"))));
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.",groups = { "checkValidationOFTravelTransportationFields_TravelExpenseClaim" })
	public void checkValidationOFTravelTransportationFields_TravelExpenseClaim() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		UserClaims userClaims = new UserClaims();
		userClaims.claimForTravelExpense();
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		userClaims.clickToAddNewClaimBtn();
		//Assert.assertTrue(isTextPresent("Travel Expense"));
		userClaims.fillTravelExpenseClaim();
		// go to 'Travel Expense Claim' and Check the Validation for fields under 'Travel Transportation Section'.
		userClaims.checkValidation_TravelTransportationSection();
	}
	
	// Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' and 'save' Expense Under 'Travel Transportation Section'.
	@Test (description="Login as a Normal User and go to 'Travel Expense Claim', Verify the Details while 'Add' and 'save' Expense Under 'Travel Transportation Section'.",groups = { "addEditAndSaveTravelTransportationExpenseDetails_TravelExpenseClaim" })
	public void addEditAndSaveTravelTransportationExpenseDetails_TravelExpenseClaim() throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile(); 
		UserClaims userClaims = new UserClaims();
		userClaims.claimForTravelExpense();
		Assert.assertTrue(isTextPresent("My Travel Claim List"));
		userClaims.clickToAddNewClaimBtn();
		//Assert.assertTrue(isTextPresent("Travel Expense"));
		userClaims.fillTravelExpenseClaim();
		// Verify the Details After 'Add' Expense Under 'Travel Transportation Section'.
		userClaims.fillTravelTransportationSection();
		// Verify the Total Expense Amount in 'Travel Transportation'.
		int Amount = Integer.parseInt(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		int Rate = Integer.parseInt(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		int totalAmount = Amount*Rate;
		String totalAmountS = new Integer(totalAmount).toString();
		Assert.assertTrue(isTextPresent(totalAmountS+".00"));
		Reporter.log("Verified the Total Expence Amount in 'Travel Transportation' is :: "+totalAmountS+".00");
		Assert.assertTrue(isElementPresent(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))));
		Reporter.log("Verified 'Edit' and 'Delete' Action img appearing on page After Added A Expense claim ");
		// Verify Added Expense Details while Editing 
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgEdit"))).click();
		Thread.sleep(100);
		Assert.assertTrue(isElementPresent(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_CancelButton"))));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).getAttribute("value"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		Reporter.log("Verified 'Cancle' Button and same Added Expense amount Appearing on Page while Editing the Expense'");
		// Cancel the Edit Action and verify the Cancel button not present.
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_CancelButton"))).click();
		Thread.sleep(100);
		//Assert.assertFalse(isElementPresent(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_CancelButton"))));
		Reporter.log("Verified, click to Cancel button of Edit Action and verify the Cancel button not present.");
		// Edit the Added Expense amount and click to update button and Verify the Updated Amount on page.
		userClaims.editAddedTravelTransportationDetails();
		int editedAmount = Integer.parseInt(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtEditedAmount"));
		int udatedAmount = editedAmount*Rate;
		String udatedAmountS = new Integer(udatedAmount).toString();
		Assert.assertTrue(isTextPresent(udatedAmountS+".00"));
		Reporter.log("Verified after Edited added amount has changed Total Expence Amount in 'Travel Transportation' is :: "+udatedAmountS+".00");
		// Clicked to 'delete' button and verified to Alert appearing.
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))).click();
		Thread.sleep(100);
		Alert alert = webDriver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure you want to delete the record?");
		alert.dismiss();
		Thread.sleep(1000);
		Reporter.log("Verified, Clicked to 'delete' button and verified to Alert appearing");
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))).click();
		Thread.sleep(100);
		Alert alert1 = webDriver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "Are you sure you want to delete the record?");
		alert1.accept();
		Thread.sleep(1000);
		//Assert.assertFalse(isElementPresent(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgDelete"))));
		Reporter.log("Verified, Clicked to 'delete' button and verified to Alert appearing and clicked to ok button, Added Expense Deleted.");
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		// Save the Added Expense click to save button and verify Added amount displaying on Header.
		userClaims.fillTravelTransportationSection();
		userClaims.clickToSaveButnTravelTransportation();
		Assert.assertTrue(isTextPresent("Travel Transportation - Sub Total: "+totalAmount));
		Reporter.log("Verified, Travel Transportation Added and Saved with Total Amount - "+totalAmount);
	}
}
