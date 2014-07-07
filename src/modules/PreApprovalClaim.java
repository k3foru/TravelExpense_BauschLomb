package modules;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class PreApprovalClaim extends TestNgHelper{
	
	Set_Read_ProperpertiesFile sRPF;
	private CommonMethods commonMethods;
	public static String addedRequestNo = "";
	public static String totalSub = "";
	public static String managerApprovalAmount = "";
	public static String addedDateTime = "";

	//Constructor Of the Class
	public PreApprovalClaim()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
		commonMethods = new CommonMethods();
	}

	//Normal User Home page - click to 'PreApproval Request' in 'Claim' Tab.
	public void claimForPreApprovalRequest()throws Exception
	{
		Thread.sleep(1000);
		mouseOverElement(By.linkText(sRPF.home_Xpath.get("Home_ClaimTab")));
		Thread.sleep(300);
		webDriver.findElement(By.linkText(sRPF.home_Xpath.get("Home_ClaimTab_PreApprovalRequestLink"))).click();
	}
	
	// On Pre Approval Request Listing page - click to 'Add New Button' to add new claim.
	public void clickToAddNewClaimBtn() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_AddNewButn"))).click();
		Thread.sleep(1000);
	}
	
	// On Pre-Approval Form page - Check the validation of Fields on click of Save and Submit button in 'Pre-Approval'.
	public void checkFieldValidation_PreApprovalForm() throws Exception{
		// Check the validation of Fields on click of Save and Add button.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n On click Submit Button - validation for Fields which is Mandatory:: "+alert.getText());
		alert.accept();
		// Check the validation for 'From Date' fields.
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnFromDate"), CommonUse.getBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		log("\n\n On click Submit Button - validation for 'From Date' :: "+alert2.getText());
		alert2.accept();
		// Check the validation for 'To Date' fields.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))).click();
		Thread.sleep(1000);
		clickToAddNewClaimBtn();
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnToDate"), CommonUse.getBeforeTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnFromDate"), CommonUse.getBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		log("\n\n On click Submit Button - validation for 'To Date' :: "+alert3.getText());
		alert3.accept();
		// Check the validation for 'To Date' fields.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))).click();
		Thread.sleep(1000);
		clickToAddNewClaimBtn();
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnToDate"), CommonUse.getAfterTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnFromDate"), CommonUse.getTodaysDate());
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtAccompaniedBy"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtBusinessPurposeNPlan"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		// Check the validation for 'Hotel Charges Section' fields.
		Thread.sleep(1000);
		Alert alert4 = webDriver.switchTo().alert();
		log("\n\n On click Add Button - validation for 'Hotel Charges Section' Fields :: "+alert4.getText());
		alert4.accept();
		Thread.sleep(1000);
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(1000);
		// Verify Added City and Edit, delete Action for Added record. 
		Assert.assertTrue(isTextPresent(sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity")));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgEdit"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgDelete"))));
		log("\n\n Verified: Added City and Edit, delete Action for Added record present on the page.");
		// Click Edit Img to Edit Added Record.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgEdit"))).click();
		Thread.sleep(1000);
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity")), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).getAttribute("value"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).getAttribute("value"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_CancelButton"))));
		log("\n\n Verified: All Added Record are displaying on correspoding filed while Editing.");
		// Click to 'cancel button' and verify ..
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_CancelButton"))).click();
		Thread.sleep(1000);
		//Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_CancelButton"))));
		log("\n\n Verified: Cancle Button working Fine");
		// To Update the Added record.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgEdit"))).click();
		Thread.sleep(1000);
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(1000);
		log("\n\n Verified: Edited Record Updated Successfuly.");
		// To Verify Add one more record with Same city, check the Validation.
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert5 = webDriver.switchTo().alert();
		log("\n\n To Verify Add one more record with Same city, check the Validation Msg :: "+alert5.getText());
		alert5.accept();
		Thread.sleep(1000);
		// Verification for Delete Button.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert6 = webDriver.switchTo().alert();
		log("\n\n Verified: Click to Delete Button - Validation Msg :: "+alert6.getText());
		alert6.dismiss();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgDelete"))));
		log("\n\n Verified: Cancle the Delete Alert, Record not Deleted");
		// Verification for Delete the Record.
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgDelete"))).click();
		Thread.sleep(1000);
		Alert alert7 = webDriver.switchTo().alert();
		log("\n\n Verified: Click to Delete Button - Validation Msg :: "+alert7.getText());
		alert7.accept();
		Thread.sleep(1000);
		//Assert.assertFalse(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgDelete"))));
		log("\n\n Verified: click to Delete Alert, Record Deleted");
		// Verify the Total Sub(Expense) of Pre-Approval Form.
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTravelSubsidies"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTravelSubsidies"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTrafficeExpenses"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTrafficeExpenses"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtMeals"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtMeals"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtOthers"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtOthers"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).click();
		int expense1 = Integer.parseInt(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		int expense2 = Integer.parseInt(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTravelSubsidies"));
		int expense3 = Integer.parseInt(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTrafficeExpenses"));
		int expense4 = Integer.parseInt(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtMeals"));
		int expense5 = Integer.parseInt(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtOthers"));
		int totalExpense = expense1+expense2+expense3+expense4+expense5;
		totalSub = new Integer(totalExpense).toString();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText(), totalSub);
		log("\n\n Verified: Sub Total is Equals to Total Expense : "+totalExpense);
	}
	
	// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request' Fill Exceed Amount.
	public void fillPreApprovalExpenseFormExceedAmount() throws Exception{
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnToDate"), CommonUse.getAfterTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnFromDate"), CommonUse.getTodaysDate());
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtAccompaniedBy"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtBusinessPurposeNPlan"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan"));
		if(!webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_chkboxAirTicket"))).isSelected())
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_chkboxAirTicket"))).click();
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelChargesExceedAmount"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTravelSubsidies"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTravelSubsidies"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTrafficeExpenses"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTrafficeExpenses"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtMeals"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtMeals"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtOthers"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtOthers"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).click();
		totalSub = webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText();
	}
	
	// On Pre-Approval Form page - Fill All Expense Information for 'Pre-Approval Request'.
	public void fillPreApprovalExpenseForm() throws Exception{
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnToDate"), CommonUse.getAfterTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnFromDate"), CommonUse.getTodaysDate());
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtAccompaniedBy"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedBy"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtBusinessPurposeNPlan"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlan"));
		if(!webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_chkboxAirTicket"))).isSelected())
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_chkboxAirTicket"))).click();
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCity"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNights"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelCharges"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTravelSubsidies"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTravelSubsidies"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTrafficeExpenses"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTrafficeExpenses"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtMeals"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtMeals"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtOthers"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtOthers"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).click();
		totalSub = webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText();
	}
	
	// On Edit Pre-Approval Form page - update Information for 'Pre-Approval Request'.
	public void updatePreApprovalAddedExpenseForm() throws Exception{
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnToDate"), CommonUse.getAfterTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ibtnFromDate"), CommonUse.getTodaysDate());
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtAccompaniedBy"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtAccompaniedBy"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtAccompaniedByEdited"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtBusinessPurposeNPlan"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtBusinessPurposeNPlanEdited"));
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ddlCity"), sRPF.preApprovalClaim_Data.get("PreApprovalForm_ddlCityEdited"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtNoOfNights"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtNoOfNightsEdited"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelChargesEdited"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTravelSubsidies"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTravelSubsidies"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTravelSubsidies"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTrafficeExpenses"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtTrafficeExpenses"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtTrafficeExpenses"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtMeals"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtMeals"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtMeals"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtOthers"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtOthers"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtOthers"));
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).click();
		totalSub = webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText();
	}
	
	public void getAddedRequestNo(){
		addedRequestNo = webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestNo"))).getText();
	}
	
	// On Pre-Approval Form page - click to Back Button.
	public void clickToBackButn() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_BackButton"))).click();
		Thread.sleep(1000); 
	}
	
	// On Pre-Approval Form page - click to Save Button.
	public void clickToSaveButn() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SaveButton"))).click();
		addedDateTime = CommonUse.getTodaysDateTime();
		Thread.sleep(2000); 
	}
	
	// On Pre-Approval Form page - click to Submit Button and check the Alert.
	public void clickToSubmitButtonToVerifyAlert() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		addedDateTime = CommonUse.getTodaysDateTime();
		Thread.sleep(1000); 
	}
	
	// On Pre-Approval Form page - click to Submit Button.
	public void clickToSubmitButton() throws InterruptedException, IOException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n Click to Submit and, check the Validation Msg :: "+alert.getText());
		alert.accept();
		Thread.sleep(1000);
		boolean outOfRangeAmount = false;
		try{
			webDriver.switchTo().alert();
			log("Verified: Alert Diplaying because Request Amount is greater than Manager Approval Amount.");
			outOfRangeAmount = true;
		}
		catch(Exception e){
			outOfRangeAmount = false;
		}
		if(outOfRangeAmount){
			Alert alert1 = webDriver.switchTo().alert();
			log("\n\n Click to Submit and, check the Validation Msg if 'Request Amount is greater than Manager Approval Amount' :: "+alert1.getText());
			alert1.accept();
			Thread.sleep(1000);
			
			// To Update the Added record.
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_imgEdit"))).click();
			Thread.sleep(1000);
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).clear();
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_txtHotelCharges"))).sendKeys(sRPF.preApprovalClaim_Data.get("PreApprovalForm_txtHotelChargesEdited"));
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_HotalCharges_AddButton"))).click();
			Thread.sleep(1000);
			totalSub = webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_lblSubTotal"))).getText();
			// Now Submit the Expense.
			webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_SubmitButton"))).click();
			Thread.sleep(1000);
			Alert alert2 = webDriver.switchTo().alert();
			log("\n\n Click to Submit and, check the Validation Msg :: "+alert2.getText());
			alert2.accept();
			Thread.sleep(1000);
			addedDateTime = CommonUse.getTodaysDateTime();
			log("\n\n Updated Expense Submited Successfuly");
		}
		else
			addedDateTime = CommonUse.getTodaysDateTime();
		//getAddedRequestNo();
	}
	
	// Click to Edit Img Button of Drafted Request.
	public void clickEditForDraftedRequest() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgEdit"))).click();
		Thread.sleep(1000);
	}
	
	// Click to view Img Button of Drafted Request.
	public void clickViewForDraftedRequest() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgView"))).click();
		Thread.sleep(1000);
	}
	
	// Click to Delete Img Button of Drafted Request.
	public void clickDeleteForDraftedRequest() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgDelete"))).click();
		Thread.sleep(1000);
	}
	
	// On Listing page clickToCancelSubmitedRequest
	public void clickToCancelSubmitedRequest() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Action_imgCancel"))).click();
		Thread.sleep(1000);
	}
	
	// Click to 'View Comment' Button of Submitted Request. 
	public void clickToViewCommentButton() throws InterruptedException{
		childWindowHandler(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalForm_ViewCommentsButton")));
		Thread.sleep(2000);
	}
	
	// To track the paging on the page 
	public void checkAndVerifyForPaging() throws InterruptedException, IOException{
		if(!isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_19_lnkViewRequest"))){
			Assert.assertFalse(isTextPresent("Page Size:"));
			log("Paging not found on 'Listing of Pre-Approvals' page because No. of Record is less than 20");
		}
		else if(isTextPresent("Page Size:")){
			Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_19_lnkViewRequest")));
			//Assert.assertTrue(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[3]/select")));
			Assert.assertTrue(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[1]/span")));
			Assert.assertTrue(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[2]/a")));
			log("Paging displaying on 'Listing of Pre-Approvals' page because record is more than 20");
			webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[2]/a")).click();
			Thread.sleep(1000);
			Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_0_lnkViewRequest")));
			if(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[3]/a"))){
				Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_19_lnkViewRequest")));
				log("Paging displaying on 'Listing of Pre-Approvals' page with 3rd page link because record is more than 40");
			}
			if((isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[4]/select"))) && (isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[3]/a")))){
				Select select = new Select(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[4]/select")));
		    	select.selectByIndex(1);
		    	Thread.sleep(1000);
		    	Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_39_lnkViewRequest")));
		    	Assert.assertTrue(isTextPresent("Page Size:"));
				log("Paging displaying with 40 record on 'Listing of Pre-Approvals' page because record is more than 40");
			}
			else if(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[3]/select"))){
				Select select = new Select(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[3]/select")));
		    	select.selectByIndex(1);
		    	Thread.sleep(1000);
		    	Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_20_lnkViewRequest")));
		    	Assert.assertFalse(isTextPresent("Page Size:"));
				log("Paging displaying with All record on 'Listing of Pre-Approvals' page because record is less than 41, and now paging not appearing");
			}
		}
		else{
			log("Paging not found on 'Listing of Pre-Approvals' page because No. of Record is less than 21");
		}
	}
	
	// check For Search Option On PreApproval Listing Page
	public void checkForSearchOptionOnPreApprovalListingPage() throws InterruptedException, IOException{
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(800);
		Alert alert = webDriver.switchTo().alert(); 
		log("\n\n Click to Searh Button without selected any criteria and getting alert Msg :: "+alert.getText());
		alert.accept();
		Thread.sleep(800);
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria")), "Request Number");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		log("\n\n Verified: selected Search Criteria and leaves search text box blank : all records are listed.");
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria")), "Status");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		log("\n\n Verified: Two Search Critaria Option appearig in Dropdown : 'Request Number' and 'Status'.");
		// Search with Request No.
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria"), "Request Number");
		getAddedRequestNo();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_txtSearchValue"))).sendKeys(addedRequestNo);
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_grdPreApprovalList_grdPreApprovalList_1_lnkViewRequest")));
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_RequestNo"))).getText(), addedRequestNo);
		Assert.assertTrue(isElementPresent(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_AddNewButn"))));
		log("\n\n Verified: Searched By Request No. - search is applied to search functionality and hence all records having respective keyword in selected search criteria are listed.");
		// search with 'junk characters'
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria"), "Request Number");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_txtSearchValue"))).clear();
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_txtSearchValue"))).sendKeys("tset");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("No records found."));
		log("\n\n Verified: Searched with 'junk characters' - message is displayed as 'No records found' on page.");
		// Search with Status and check All option present.
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria"), "Status");
		Thread.sleep(800);
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus")), "Waiting for approval");
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus")), "Approved");
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "3");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus")), "Cancelled");
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "4");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus")), "Draft");
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "5");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus")), "Rejected");
		selectByIndex(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "6");
		Assert.assertEquals(getSelectedLabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus")), "Returned");
		log("\n\n Verified: Searched By Status. - All Status Type Appearing in Dropdon.");
		// Search with Status.
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "Waiting for approval");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No records found.")){
			Assert.assertTrue(isTextPresent("Awaiting approval from"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Waiting for approval' Status : Only 'Waiting for approval' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "Approved");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No records found.")){
			Assert.assertTrue(isTextPresent("Approved"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Approved");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Approved' Status : Only 'Approved' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "Cancelled");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No records found.")){
			Assert.assertTrue(isTextPresent("Cancelled"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Cancelled");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Cancelled' Status : Only 'Cancelled' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "Draft");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No records found.")){
			Assert.assertTrue(isTextPresent("Draft"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Draft");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Draft' Status : Only 'Draft' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "Rejected");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No records found.")){
			Assert.assertTrue(isTextPresent("Rejected"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Rejected");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Draft"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Rejected' Status : Only 'Rejected' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		
		selectBylabel(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_ddlSearchCriteria_ddlStatus"), "Returned");
		webDriver.findElement(By.id(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No records found.")){
			Assert.assertTrue(isTextPresent("Returned"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.preApprovalClaim_Xpath.get("PreApprovalsList_DataGrid_Status"))).getText(), "Returned");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Draft"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Returned' Status : Only 'Returned' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
	}
	
	// Go to 'User Profile' in 'Master' Tab and get the 'Travel Expense Amount'
	public void getTravelExpenseAmountFromUserProfile() throws InterruptedException{
		mouseOverElement(By.linkText("Masters"));
		Thread.sleep(300);
		webDriver.findElement(By.linkText("User Profile")).click();
		Assert.assertTrue(isTextPresent("User Profile"));
		managerApprovalAmount = webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdApprovingQuota']/tbody/tr[2]/td[3]")).getText().replace(".00", "");
	}
	
	// Go to 'My Approval Tab'
	public void goToMyApprovalTab(){
		webDriver.findElement(By.linkText("My Approvals")).click();
	}
}
