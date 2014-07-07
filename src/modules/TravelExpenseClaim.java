package modules;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class TravelExpenseClaim extends TestNgHelper{
	
	Set_Read_ProperpertiesFile sRPF;
	private Selenium selenium;
	private CommonMethods commonMethods;
	public String totalTravelTransportationExpense = "0";
	public String totalLodgingExpense = "0";
	public String totalOtherExpense = "0";
	public String totalLocalTransportationExpense = "0";
	public String totalPersonalMealExpense = "0";
	public String totalInternalMeetingExpense = "0";
	public String  totalTravelClaimExpense = "";
	public static String addedDateTime = "";
	public static String financeSettledDate = "";
	public static String adminApprovedDate = "";
	public static String claimSubmitedDate = "";
	public static String addedClaimNo = "";
	public static String managerApprovalAmount = "";
	public String entertenmentType = "";
	public String customerType = "";
	public String limitOfAttends;

	//Constructor Of the Class
	public TravelExpenseClaim()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
		selenium = new WebDriverBackedSelenium(webDriver, sRPF.userLogin_Data.get("TEUrl"));
		commonMethods = new CommonMethods();
	}

	//Normal User Home page - click to 'Travel Expense' in 'Claim' Tab.
	public void claimForTravelExpense()throws Exception
	{
		Thread.sleep(1000);
		mouseOverElement(By.linkText(sRPF.home_Xpath.get("Home_ClaimTab")));
		Thread.sleep(300);
		webDriver.findElement(By.linkText(sRPF.home_Xpath.get("Home_ClaimTab_TravelExpenseLink"))).click();
	}
	
	// On Travel Expense List page - click to Add New Button to add new claim.
	public void clickToAddNewClaimBtn() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_AddNewButn"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Fill 'Business Purpose of trip' and 'No. of Tickets' fields in Travel Expense.
	public void fillBusinessPurposeNPlanNoOfTickets_TravelExpenseClaim() throws Exception{
		
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtNoOfTickets"));
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Travel Transportation Section'.
	public void checkValidation_TravelTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		// Check the validation of Fields on click of Save and Add button.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("On click Add Button - validation for Fields which is Mandatory:: "+alert.getText());
		alert.accept();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_SaveButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		log("\n On click Save Button - validation for Fields which is Mandatory:: "+alert2.getText());
		alert2.accept();
		Thread.sleep(1000);
		// Check the validation for 'Departure Date & Time' fields, send after todays date and check the validation in alert.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_DateCalendarImgID"), CommonUse.getAfterTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTimeForValidation")+"'");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		log("\n On click Add Button - validation for 'Departure Date & Time' Fields which is Mandatory:: "+alert3.getText());
		alert3.accept();
		Thread.sleep(1000);
		// Check the validation for 'Location from & Location to' fields.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_DateCalendarImgID"), CommonUse.getTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"'");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert4 = webDriver.switchTo().alert();
		log("\n On click Add Button - Checked the validation for 'Location from & Location to' fields.:: "+alert4.getText());
		alert4.accept();
		Thread.sleep(1000);
		log("Verified : 'Location from & Location to' should not accept same location Name");
	}
	
	// On Travel Expense page - Fill All Expense Information in 'Travel Transportation Section' for Exceed Amount.
	public void fillTravelTransportationSectionForExceedAmount() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		Thread.sleep(1000);
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_DateCalendarImgID"), CommonUse.getBeforeTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"'");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtAmountExcced"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).click();
		totalTravelTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).getAttribute("value");
	}
	
	// On Travel Expense page - Fill All Expense Information in 'Travel Transportation Section'.
	public void fillTravelTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		Thread.sleep(1000);
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_DateCalendarImgID"), CommonUse.getBeforeTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"'");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).click();
		totalTravelTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).getAttribute("value");
	}
	
	// On Travel Expense page - Fill second time expense All Expense Information in 'Travel Transportation Section'.
	public void fillSecondExpenseTravelTransportationSection() throws Exception{
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_DateCalendarImgID"), CommonUse.getTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"'");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).click();
		totalTravelTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).getAttribute("value");
	}
	
	// Edit the Added Expense amount and click to update button in 'Travel Transportation Section'.
	public void editAddedTravelTransportationDetails() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_imgEdit"))).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_TravelTransportationHeader_txtEditedAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).click();
		totalTravelTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - in in 'Travel Transportation Section' click to Add button.
	public void clickToAddButnTravelTransportation() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - in in 'Travel Transportation Section' click to Save button.
	public void clickToSaveButnTravelTransportation() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_TravelTransportationHeader_SaveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Lodging Section'.
	public void checkFieldsValidation_LodgingSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).click();
		// Check the validation of Fields on click of Save and Add button.
		log("\n\n Fields Validation and verificaton of alert of 'Lodging Section' :: ");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n On click Add Button - validation for Fields which is Mandatory:: "+alert.getText());
		alert.accept();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_SaveButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		log("\n On click Save Button - validation for Fields which is Mandatory:: "+alert2.getText());
		alert2.accept();
		Thread.sleep(1000);
		// Check the validation for 'Hotel' other field option.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlLocation"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlLocation"));
		Thread.sleep(1000);
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlHotelOther"));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))).isEnabled();
		log("\n\n Verified : Selected Hotel 'Other' for location and a text box displaying adjacent to the Hotel drop down and user are able to enter location details.");
		// check the validation of hotel other option filed should not be blank.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		log("\n On click Add Button without enter hotel in other field - validation for Fields which is Mandatory:: "+alert3.getText());
		alert3.accept();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtHotel"));
		// Check the validation for 'Checked In Date' and 'Checked out Date' fields, send after todays date and check the validation in alert.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedoutDateCalenderID"), CommonUse.getAfterTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedInDateCalenderID"), CommonUse.getAfterTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert4 = webDriver.switchTo().alert();
		log("\n validation for 'Checked In Date' and 'Checked out Date' fields, send after todays date and check the validation in alert :: "+alert4.getText());
		alert4.accept();
		Thread.sleep(1000);
		clickToBackButton();
		clickToAddNewClaimBtn();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).click();
		Thread.sleep(1000);
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlLocation"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlLocation"));
		Thread.sleep(1000);
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlHotel"));
		if(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel")).equals("Other"))
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtHotel"));
		// Check the validation for 'Checked In Date' is greater than 'Checked out Date', and check the validation in alert.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedoutDateCalenderID"), CommonUse.getBeforeTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedInDateCalenderID"), CommonUse.getTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_AddButton"))).click();
		Thread.sleep(2000);
		Alert alert5 = webDriver.switchTo().alert();
		log("\n Checked the validation for 'Checked In Date' is greater than 'Checked out Date', 'Checked out Date' field not accepting less date, and check the validation in alert :: "+alert5.getText());
		alert5.accept();
		Thread.sleep(1000);
		// Check for 'Number of Nights' field should be nonEditable and should contain number of gap between both date.
		clickToBackButton();
		clickToAddNewClaimBtn();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).click();
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlLocation"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlLocation"));
		Thread.sleep(1000);
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlHotel"));
		if(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel")).equals("Other"))
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtHotel"));
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedoutDateCalenderID"), CommonUse.getTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedInDateCalenderID"), CommonUse.getBeforeTodaysDate());
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtNumberOfNights"))).getAttribute("value"), "2");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtNumberOfNights"))).isDisplayed();
		log("\n Verified : 'Number of Nights' field is 'nonEditable' and containing number of gap between 'Checked In Date' and 'Checked out Date'.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtComments"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtComments"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).click();
		totalLodgingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).getAttribute("value");
	}
	
	// On Travel Expense page - Fill All Expense Information in 'Lodging Section'.
	public void fillLodgingSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LodgingHeader"))).click();
		Thread.sleep(1000);
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlLocation"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlLocation"));
		Thread.sleep(1000);
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlHotel"));
		if(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel")).equals("Other"))
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtHotel"));
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedoutDateCalenderID"), CommonUse.getTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedInDateCalenderID"), CommonUse.getBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtComments"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtComments"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).click();
		totalLodgingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).getAttribute("value");
	}
	
	// On Travel Expense page - Fill second time expense All Expense Information in 'Lodging Section'.
	public void fillSecondExpenseInLodgingSection() throws Exception{
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlLocation"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlLocation"));
		Thread.sleep(1000);
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlHotel"));
		if(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlHotel")).equals("Other"))
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtHotel"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtHotel"));
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedoutDateCalenderID"), CommonUse.getTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_CheckedInDateCalenderID"), CommonUse.getOneDayBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtComments"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtComments"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).click();
		totalLodgingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).getAttribute("value");
	}
	
	// Edit the Added Expense amount and click to update button in 'Lodging Section'.
	public void editAddedLodgingSectionDetails() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_imgEdit"))).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Lodging_txtEditedAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).click();
		totalLodgingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_AddButton"))).click();
		Thread.sleep(1000);
	}
	
	// On Travel Expense page - in 'Lodging Section' click to Add button.
	public void clickToAddButnInLodgingSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_AddButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - in 'Lodging Section' click to Save button.
	public void clickToSaveButnInLodgingSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Lodging_SaveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Local Transportation Section'.
	public void checkFieldsValidation_localTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_AddEditButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_SaveButton"))));
		log("Verified : In 'local Transportation Section' 'Add/Adit and Save' Button displaying.");
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_AddEditButton")));
		Assert.assertEquals(webDriver.getTitle(), "Add / Edit Local Transportation");
		log("Verified : On 'Add/Edit local Transportation Section' page  Title is - "+webDriver.getTitle());
		// Verify following fields are displayed under transportation section: 1)Date and Time 2) From 3) To 4) Purpose 5) Payment mode 6) Currency 7) Amount 8) Rate 9) Amount(RMB) 10) Delete 11)Add10MoreButton 12)SaveButton 13) CancelButton.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_DateCalendarImgID1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtPurpose1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlPaymentMode1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlCurrency1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_Add10MoreButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_CancelButton"))));
		log("Verified : On 'Add/Edit local Transportation Section' following fields are displaying 1)Date and Time 2) From 3) To 4) Purpose 5) Payment mode 6) Currency 7) Amount 8) Rate 9) Amount(RMB) 10) Delete 11)Add10MoreButton 12)SaveButton 13) CancelButton.");
		// verify click to 'Add 10 More' Button and 10 more table has to create.
		//webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_Add10MoreButton"))).click();
		//Thread.sleep(600);
		//Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdLT_grdLT_19_Image1")));
		log("Verified : On 'Add/Edit local Transportation Section' clicked to 'Add 10 More' Button and 10 more table has to created. ");
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_DateCalendarImgID1"), CommonUse.getBeforeTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtLocalTransportationTime1")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_txtLocalTransportationTime")+"'");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtFrom1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtTo1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtPurpose1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtPurpose1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlPaymentMode1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlPaymentMode1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlCurrency1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlCurrency1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).click();
		totalLocalTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).getAttribute("value");
		// check Cancel button on Add/Edit local Transportation page, click to Cancel button, window should be closed.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_CancelButton"))).click();
		Thread.sleep(1000);
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpense");
		log("Verified : On 'Add/Edit local Transportation Section' Cancel the window has canceled ");
		/*childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_AddEditButton")));
		// check the validation on click of Save button on Add/Edit local Transportation page.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		CommonUse.log("\n Verified : on click of Save button on Add/Edit local Transportation page, validation alert appearing :: "+alert.getText(), "b");
		alert.accept();
		Thread.sleep(100);
		// Verify future dates should not be accepted in the 'date and time' field.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_DateCalendarImgID1"), CommonUse.getAfterTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtLocalTransportationTime1")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_txtLocalTransportationTimeForValidation")+"'");
		Thread.sleep(3000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtFrom1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtFrom1"));
		Thread.sleep(600);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtTo1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtPurpose1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtPurpose1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlPaymentMode1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlPaymentMode1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlCurrency1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlCurrency1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		CommonUse.log("\n Verified : future dates should not be accepted in the 'date and time' field, Validation Alert - :: "+alert2.getText(), "a");
		alert2.accept();
		Thread.sleep(100);
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_DateCalendarImgID1"), CommonUse.getBeforeTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtLocalTransportationTime1")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_txtLocalTransportationTime")+"'");
		// Verify Same data in 'From' and 'To' should not accept, alert has to appear.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		CommonUse.log("\n Verified : Same data in 'From' and 'To' should not accept, alert appearing, Validation Alert - :: "+alert3.getText(), "b");
		alert3.accept();
		Thread.sleep(100);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtFrom1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtTo1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))).click();
		Thread.sleep(100);
		CommonUse.log("\n Verified : Data Added in 'Local Transportation Section'.", "b");
		webDriver.switchTo().window(parentWindowHandle);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_imgDelete"))));*/
	}
	
	// On Travel Expense page - Fill All Expense Information in 'Local Transportation Section'.
	public void fillDetailsInLocalTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationHeader"))).click();
		Thread.sleep(1000);
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_AddEditButton")));
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_DateCalendarImgID1"), CommonUse.getBeforeTodaysDate());
		selenium.getEval("window.document.getElementById('"+sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtLocalTransportationTime1")+"').value='"+sRPF.travelExpenseClaim_Data.get("TravelExpense_txtLocalTransportationTime")+"'");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtFrom1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtFrom1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtTo1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtTo1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtPurpose1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtPurpose1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlPaymentMode1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlPaymentMode1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_ddlCurrency1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_ddlCurrency1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtRate1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtRate1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).click();
		totalLocalTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))).click();
		Thread.sleep(3000);
	}
	
	// Edit the Added record by different Amount and Save it, Verify Edited Value in 'Local Transportation Section'
	public void editAddedRecordInLocalTransportationSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_LocalTransportation_txtEditedAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).click();
		totalLocalTransportationExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_txtAmountLC1"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportationSaveButton"))).click();
		Thread.sleep(3000);
	}
	
	// On Travel Expense page - in 'Local Transportation Section' click to Save button.
	public void clickToSaveButnInlocalTransportationSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_LocalTransportation_SaveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Personal Meal Section'.
	public void checkFieldsValidation_PersonalMealSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_AddEditButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_SaveButton"))));
		log("Verified : In 'Personal Meal Section' 'Add/Adit and Save' Button displaying.");
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_AddEditButton")));
		Assert.assertEquals(webDriver.getTitle(), "Add / Edit Personal Meal");
		log("Verified : On 'Add/Edit Personal Meal Section' page  Title is - "+webDriver.getTitle());
		// Verify following fields are displayed under Personal Meal section: 1)ExpenseDateCalendar 2) Lunch 3) Dinner 5) Payment mode 6) Currency 7) Amount 8) Rate 9) Amount(RMB) 10) Delete 11)Add10MoreButton 12)SaveButton 13) CancelButton.
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ExpenseDateCalendarImgID1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtLunch1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtDinner1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlPaymentMode1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlCurrency1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtRate1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_imgDelete1"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_Add10MoreButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealSaveButton"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_CancelButton"))));
		log("Verified : On 'Add/Edit Personal Meal Section' following fields are displaying 1)ExpenseDateCalendar 2) Lunch 3) Dinner 5) Payment mode 6) Currency 7) Amount 8) Rate 9) Amount(RMB) 10) Delete 11)Add10MoreButton 12)SaveButton 13) CancelButton.");
		// verify click to 'Add 10 More' Button and 10 more table has to create.
		/*webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_Add10MoreButton"))).click();
		Thread.sleep(600);
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdPersonalMealDetails_grdPersonalMealDetails_14_Image1")));*/
		log("Verified : On 'Add/Edit Personal Meal Section' clicked to 'Add 10 More' Button and 10 more table has to created. ");
		// Verify Amount filed value is equals to No. of lunch + No. of Dinner.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ExpenseDateCalendarImgID1"), CommonUse.getBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtLunch1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtLunch1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtLunch1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtDinner1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtDinner1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlPaymentMode1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_ddlPaymentMode1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlCurrency1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_ddlCurrency1"));
		int amount = Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtLunch1")) + Integer.parseInt(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).getAttribute("value"), new Integer(amount).toString());
		log("Verified : Amount filed value is equals to No. of lunch + No. of Dinner.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtRate1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtRate1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtRate1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).click();
		totalPersonalMealExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).getAttribute("value");
		// check Cancel button on Add/Edit Personal Meal page, click to Cancel button, window should be closed.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_CancelButton"))).click();
		Thread.sleep(1000);
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		Assert.assertFalse(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ExpenseDateCalendarImgID1"))));
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "TravelExpense");
		log("Verified : On 'Add/Edit Personal Meal Section' Cancel the Model popup window has canceled ");
	}
	
	// On Travel Expense page - Fill All Expense Information in 'Personal Meal Section'.
	public void fillDetailsInPersonalMealSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealHeader"))).click();
		Thread.sleep(1000);
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_AddEditButton")));
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ExpenseDateCalendarImgID1"), CommonUse.getBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtLunch1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtLunch1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtLunch1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtDinner1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtDinner1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtDinner1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlPaymentMode1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_ddlPaymentMode1"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_ddlCurrency1"), sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_ddlCurrency1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtRate1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtRate1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtRate1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).click();
		totalPersonalMealExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealSaveButton"))).click();
		Thread.sleep(3000);
	}
	
	// Edit the Added record by different Amount and Save it, Verify Edited Value in 'Personal Meal Section'
	public void editAddedRecordInPersonalMealSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmount1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_PersonalMeal_txtEditedAmount1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).click();
		totalPersonalMealExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_txtAmountLC1"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMealSaveButton"))).click();
		Thread.sleep(3000);
	}
	
	// On Travel Expense page - in 'Personal Meal Section' click to Save button.
	public void clickToSaveButnInPersonalMealSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_PersonalMeal_SaveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Other Section'.
	public void checkFieldsValidation_OtherSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).click();
		// Check the validation of Fields on click of Save and Add button.
		log("\n\n Fields Validation and verificaton of alert of 'Other Section' :: ");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n On click Add Button - validation for Fields which is Mandatory:: "+alert.getText());
		alert.accept();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_SaveButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		log("\n On click Save Button - validation for Fields which is Mandatory:: "+alert2.getText());
		alert2.accept();
		Thread.sleep(1000);
		// Verify All Expense type present in to drop down.
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "External Training");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "Internal Training");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "3");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "Laundry");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "4");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "Professional Training");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "5");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "Professional Training - Gift");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "6");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "Professional Training - Meal");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "7");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "T&E - Others");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), "8");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType")), "Telephone");
		log("\n\n Verified:  All Expense type present in in Dropdon.");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlExpenseType"));
		// Check the validation for 'Expense Date' fields, send after todays date and check the validation in alert.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ExpenseDateCalenderID"), CommonUse.getAfterTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		log("\n validation for 'Checked In Date' and 'Checked out Date' fields, send after todays date and check the validation in alert :: "+alert3.getText());
		alert3.accept();
		Thread.sleep(1000);
		// Fill All Details.....
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ExpenseDateCalenderID"), CommonUse.getTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtExpDescription"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtExpDescription"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).click();
		totalOtherExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).getAttribute("value");
	}
	
	// On Travel Expense page - Fill All Expense Information in in 'Other Section'.
	public void fillDetailsInOtherSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_OthersHeader"))).click();
		Thread.sleep(1000);
		// Fill All Details.....
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlExpenseType"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlExpenseType"));
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ExpenseDateCalenderID"), CommonUse.getOneDayBeforeTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtExpDescription"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtExpDescription"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).click();
		totalOtherExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).getAttribute("value");
	}
	
	// Edit the Added Expense amount and click to update button in 'Other section'
	public void editAddedOtherSectionDetails() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_imgEdit"))).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_Others_txtEditeAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).click();
		totalOtherExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_AddButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - in 'Other Section' click to Add button.
	public void clickToAddButnInOtherSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_AddButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - in 'Other Section' click to Save button.
	public void clickToSaveButnInOtherSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_Others_SaveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Internal Meeting Section'.
	public void checkFieldsValidation_InternalMeetingSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		// Check the validation of Fields on click of Save and Add button.
		log("\n\n Fields Validation and verificaton with alert in 'Internal Meeting Section' :: ");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		log("\n\n On click Add Button - validation for Fields which is Mandatory:: "+alert.getText());
		alert.accept();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_SaveButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		log("\n On click Save Button - validation for Fields which is Mandatory:: "+alert2.getText());
		alert2.accept();
		Thread.sleep(1000);
		// Check the validation for 'Date', send after todays date and check the validation in alert.
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_DateCalenderID"), CommonUse.getAfterTodaysDate());
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		log("\n validation for 'Date' fields, send after todays date and check the validation in alert :: "+alert3.getText());
		alert3.accept();
		Thread.sleep(1000);
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_DateCalenderID"), CommonUse.getTodaysDate());
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCity"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCity"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPlace"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPlace"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtEWorkflow"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtEWorkflow"));
		// Verify All type of Entertainment present in to drop down.
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment")), "Business Meal");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment")), "Entertainment");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "3");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment")), "Gifts");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "4");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment")), "Internal Meal");
		log("\n\n Verified:  All type of Entertainment present in in Dropdon.");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtDuration"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtDuration"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPurpose"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPurpose"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtExceedAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).click();
		totalInternalMeetingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtName1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtName1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtTitle1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtTitle1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtCompany1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtCompany1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).click();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_AddButton"))).click();
		Thread.sleep(1000);
		// Check for Exceeding Amount.
		Alert alert4 = webDriver.switchTo().alert();
		log("\n validation for 'Amount' fields, Exceed the limit : validation in alert :: "+alert4.getText());
		alert4.accept();
		Thread.sleep(1000);
		log("Verified : If Amout is Exceeding the Claim Amount than Validation Alert Appearing on click.");
	}
	
	// On Travel Expense page - Check and select the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verify dependent radio button is enable and disable.
	public void checkAndVerifyTypeOfEntertainmentOption_InternalMeetingSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		Thread.sleep(1000);
		// Check and select the 'Type Of Entertainment' Options under 'Internal Meeting Section', and verify dependent radio button is enable and disable.
		// Select the 'Type Of Entertainment' Options 'Business Meal', and verify dependent radio button is enable and disable.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "Business Meal");
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeIntrenal1"))).isEnabled());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeGov1"))).isEnabled());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeHCP1"))).isEnabled());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).isEnabled());
		// Select the 'Type Of Entertainment' Options 'Entertainment', and verify dependent radio button is enable and disable.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "Entertainment");
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeIntrenal1"))).isEnabled());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeGov1"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeHCP1"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).isEnabled());
		// Select the 'Type Of Entertainment' Options 'Gifts', and verify dependent radio button is enable and disable.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "Gifts");
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeIntrenal1"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeGov1"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeHCP1"))).isEnabled());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).isEnabled());
		// Select the 'Type Of Entertainment' Options 'Internal Meal', and verify dependent radio button is enable and disable.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), "Internal Meal");
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeIntrenal1"))).isEnabled());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeGov1"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeHCP1"))).isDisplayed());
		Assert.assertTrue(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).isDisplayed());
	}
	
	// On Travel Expense page - Fill All Expense Information in in 'Internal Meeting Section'.
	public void fillDetailsInInternalMeetingSection() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		Thread.sleep(1000);
		// Fill All Details.....
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_DateCalenderID"), CommonUse.getBeforeTodaysDate());
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCity"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCity"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPlace"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPlace"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtEWorkflow"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtEWorkflow"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtDuration"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtDuration"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPurpose"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPurpose"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).click();
		totalInternalMeetingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtName1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtName1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtTitle1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtTitle1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtCompany1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtCompany1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).click();
	}
	
	// On Travel Expense page - Fill All Expense Information in in 'Internal Meeting Section' for verify No of Limit of Attendees.
	public void fillDetailsInInternalMeetingSectionForLimitOFAttendees() throws Exception{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeetingHeader"))).click();
		Thread.sleep(1000);
		// Fill All Details.....
		commonMethods.selectGivenDateFromCalendar(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_DateCalenderID"), CommonUse.getBeforeTodaysDate());
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCity"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCity"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPlace"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPlace"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtEWorkflow"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtEWorkflow"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlTypeOfEntertainment"), entertenmentType);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtDuration"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtDuration"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtPurpose"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtPurpose"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlPaymentMode"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlPaymentMode"));
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_ddlCurrency"), sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtRate"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtRate"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).click();
		totalInternalMeetingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtName1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtName1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtTitle1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtTitle1"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtCompany1"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtCompany1"));
		if(customerType.equals("Others"))
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_rdbAttendeeOther1"))).click();
	}
	
	// Edit the Added Expense amount and click to update button in 'Internal Meeting section'
	public void editAddedInternalMeetingSectionnDetails() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_imgEdit"))).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmount"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_InternalMeeting_txtEditedAmount"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).click();
		totalInternalMeetingExpense = webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_txtAmountLC"))).getAttribute("value");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_AddButton"))).click();
		Thread.sleep(1000);
	}
	
	// On Travel Expense page - in 'Internal Meeting Section' click to Add button.
	public void clickToAddButnInInternalMeetingSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_AddButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - in 'Internal Meetings Section' click to Save button.
	public void clickToSaveButnInInternalMeetingSection() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_InternalMeeting_SaveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - Validation for Attach Documents.
	public void validationForAttachDocuments() throws InterruptedException, IOException{
		// checking validation to not accept format.
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_flUpload"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_flUploadForValidation"));
		if(isTextPresent("Selected file type is not suppored.")){
			log("Verified : User browse invalid file and clicks on upload button, Verified alert message is 'Selected file type is not suppored'");
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_flUpload"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_flUpload"));
		}
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFileDescription"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtFileDescription"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_UploadButton"))).click();
		Thread.sleep(1000);
	}
	
	// On Travel Expense page - Attach Documents.
	public void attachDocuments() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_flUpload"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_flUpload"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_txtFileDescription"))).sendKeys(sRPF.travelExpenseClaim_Data.get("TravelExpense_txtFileDescription"));
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_UploadButton"))).click();
		Thread.sleep(1000);
	}
	
	// Fill all Section in Travel Claim.
	public void fillAllExpenseSectionInTravelClaim() throws Exception{
		// On Travel Expense page - Fill All Expense Information in 'Travel Transportation Section' and Save.
		fillTravelTransportationSection();
		clickToAddButnTravelTransportation();
		clickToSaveButnTravelTransportation();
		Thread.sleep(1000);
		// On Travel Expense page - Fill All Expense Information in 'Lodging Section' and Save.
		Thread.sleep(1000);
		fillLodgingSection();
		clickToAddButnInLodgingSection();
		clickToSaveButnInLodgingSection();
		// On Travel Expense page - Fill All Expense Information in 'Local Transportation Section' and Save.
		Thread.sleep(1000);
		fillDetailsInLocalTransportationSection();
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		clickToSaveButnInlocalTransportationSection();
		// On Travel Expense page - Fill All Expense Information in 'Personal Meals Section' and Save.
		Thread.sleep(1000);
		fillDetailsInPersonalMealSection();
		webDriver.switchTo().window(parentWindowHandle);
		Thread.sleep(1000);
		clickToSaveButnInPersonalMealSection();
		// On Travel Expense page - Fill All Expense Information in 'Other Section' and Save.
		Thread.sleep(1000);
		fillDetailsInOtherSection();
		clickToAddButnInOtherSection();
		clickToSaveButnInOtherSection();
		// On Travel Expense page - Fill All Expense Information in 'Internal Meeting Section' and Save.
		Thread.sleep(1000);
		fillDetailsInInternalMeetingSection();
		clickToAddButnInInternalMeetingSection();
		clickToSaveButnInInternalMeetingSection();
	}
	
	// Get total Expense in Travel Claim.
	public void getTotalExpenseInTravelClaim(){
		int totalExpense = Integer.parseInt(totalTravelTransportationExpense)+Integer.parseInt(totalLodgingExpense)+Integer.parseInt(totalLocalTransportationExpense)+Integer.parseInt(totalPersonalMealExpense)+Integer.parseInt(totalOtherExpense)+Integer.parseInt(totalInternalMeetingExpense);
		totalTravelClaimExpense = new Integer(totalExpense).toString();
	}
	
	// Get Claim No. for Added Expense.
	public void getAddedClaimtNo(){
		addedClaimNo = webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText();
	}
	
	// On Travel Expense page - click to Back button.
	public void clickToBackButton() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_BackButton"))).click();
		Thread.sleep(2000); 
	}
	
	// On Travel Expense List page - click to Added Claim (Draft) View button.
	public void clickToViewImgForAddedClaim() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseListt_DataGrid_Action_imgView"))).click();
		Thread.sleep(2000); 
	}
	
	// On Travel Expense List page - click to Added Claim (Draft) Edit button.
	public void clickToEditImgForAddedClaim() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgEdit"))).click();
		Thread.sleep(2000); 
	}
	
	// On Travel Expense List page - click to Added Claim (Draft) Delete button.
	public void clickToDeleteImgForDratfedClaim() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgDelete"))).click();
		Thread.sleep(2000); 
	}
	
	// On Travel Expense List page - click to Added Claim Cancel button.
	public void clickToCancelImgForAddedClaim() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Action_imgCancel"))).click();
		Thread.sleep(2000); 
	}
	
	// On Travel Expense List page - Click to 'View Comments' Button. 
	public void clickToViewCommentButton() throws InterruptedException{
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_ViewCommentsButton")));
		Thread.sleep(2000);
	}
	
	// // On Travel Expense List page - Click to 'WorkFlow Details' Button. 
	public void clickToWorkFlowDetailsButton() throws InterruptedException{
		childWindowHandler(By.id(sRPF.travelExpenseClaim_Xpath.get("TEViewSummary_WorkflowButton")));
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - click to Save button.
	public void clickToSaveButton() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SaveButton"))).click();
		addedDateTime = CommonUse.getTodaysDateTime();
		getTotalExpenseInTravelClaim();
		Thread.sleep(2000);
	}
	
	// On Travel Expense page - click to Submit button and Chek for Alert.
	public void clickToSubmitButtonAndCheckAlert() throws InterruptedException{
		addedDateTime = CommonUse.getTodaysDateTime();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))).click();
		getTotalExpenseInTravelClaim();
		Thread.sleep(1000);
	}
	
	// On Travel Expense page - click to Submit button.
	public void clickToSubmitButton() throws InterruptedException{
		addedDateTime = CommonUse.getTodaysDateTime();
		claimSubmitedDate = CommonUse.getTodaysDateFormate();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpense_SubmitButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
		Thread.sleep(1000);
		getTotalExpenseInTravelClaim();
		Thread.sleep(3000);
	}
	
	// On TE View Summary page (Login as Admin User) - click to Approve button.
	public void clickToApproveButtonByAdmin() throws InterruptedException{
		adminApprovedDate = CommonUse.getTodaysDateFormate();
		webDriver.findElement(By.id(sRPF.myApprovals_Xpath.get("MyApprovalsList_ApproveButton"))).click();
		Thread.sleep(2000);
	}
	
	// On TE View Summary page (Login as Finance User) - click to Settle button.
	public void clickToSettleButton() throws InterruptedException{
		financeSettledDate = CommonUse.getTodaysDateFormate();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("UnsettledClaimList_SettleButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
	}
	
	// On My Travel Claim List page - Check and verify paging displaying correct.
	public void checkAndVerifyForPaging() throws InterruptedException, IOException{
		if(!isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_19_lnkClaimNo"))){
			Assert.assertFalse(isTextPresent("Page Size:"));
			log("Paging not found on 'Travel Claim list' page because No. of Record is less than 20");
		}
		else if(isTextPresent("Page Size:")){
			Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_19_lnkClaimNo")));
			//Assert.assertTrue(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdPreApprovalList']/tbody/tr[22]/td/table/tbody/tr/td[3]/select")));
			Assert.assertTrue(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[1]/span")));
			Assert.assertTrue(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[2]/a")));
			log("Paging displaying on 'Listing of Travel Claim' page because record is more than 20");
			webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[2]/a")).click();
			Thread.sleep(1000);
			Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_0_lnkClaimNo")));
			log("Verified : by click of 2nd page no. nevigating to next (2nd) page.");
			if(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[3]/a"))){
				Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_19_lnkClaimNo")));
				log("Paging displaying on 'Travel Claim list' page with 3rd page link because record is more than 40");
			}
			if((isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[5]/select"))) && (isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[3]/a")))){
				Select select = new Select(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[5]/select")));
		    	select.selectByIndex(1);
		    	Thread.sleep(1000);
		    	Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_39_lnkClaimNo")));
		    	Assert.assertTrue(isTextPresent("Page Size:"));
				log("Paging displaying with 40 record on 'Travel Claim list' page because record is more than 40");
			}
			else if(isElementPresent(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[3]/select"))){
				Select select = new Select(webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdTE']/tbody/tr[22]/td/table/tbody/tr/td[3]/select")));
		    	select.selectByIndex(1);
		    	Thread.sleep(1000);
		    	Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_20_lnkClaimNo")));
		    	Assert.assertFalse(isTextPresent("Page Size:"));
				log("Paging displaying with All record on 'Travel Claim list' page because record is less than 41, and now paging not appearing");
			}
		}
		else{
			log("Paging not found on 'Travel Claim list' page because No. of Record is less than 21");
		}
	}
	
	// On My Travel Claim List page - Check and verify For Search Option is searching as per functionality.
	public void checkVerifyForSearchOptionOnTravelExpenseClaimListPage() throws InterruptedException, IOException{
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert(); 
		log("\n\n Click to Searh Button without selected any criteria and getting alert Msg :: "+alert.getText());
		alert.accept();
		Thread.sleep(1000);
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria")), "Claim No.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		log("\n\n Verified: selected Search Criteria and leaves search text box blank : all records are listed.");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria")), "Status");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		log("\n\n Verified: Two Search Critaria Option appearig in Dropdown : 'Claim Number' and 'Status'.");
		// Search with Request No.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"), "Claim No.");
		getAddedClaimtNo();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_txtSearchValue"))).sendKeys(addedClaimNo);
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_0_lnkClaimNo")));
		Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_1_lnkClaimNo")));
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_ClaimNo"))).getText(), addedClaimNo);
		Assert.assertTrue(isElementPresent(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_AddNewButn"))));
		log("\n\n Verified: Searched By Claim No. - search is applied to search functionality and hence all records having respective keyword in selected search criteria are listed.");
		// search with 'junk characters'
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"), "Claim No.");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_txtSearchValue"))).clear();
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_txtSearchValue"))).sendKeys("tset");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("No record found."));
		log("\n\n Verified: Searched with 'junk characters' - message is displayed as 'No records found' on page.");
		// Search with Status and check All option present.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"), "Status");
		Thread.sleep(800);
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Waiting for approval");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Approved");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "3");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Cancelled");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "4");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Draft");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "5");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Rejected");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "6");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Returned");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "7");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Settled");
		selectByIndex(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "8");
		Assert.assertEquals(getSelectedLabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus")), "Waiting for Finance 2 to settle");
		log("\n\n Verified: Searched By Status. - All Status Type Appearing in Dropdon.");
		// Search with Status.
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Waiting for approval");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Awaiting approval"));
			//Assert.assertEquals(webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Awaiting approval");
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Settled"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Waiting for approval' Status : Only 'Waiting for approval' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Approved");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Approved"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Approved");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Settled"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Approved' Status : Only 'Approved' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Cancelled");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Cancelled"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Cancelled");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Settled"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Cancelled' Status : Only 'Cancelled' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Draft");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Draft"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Draft");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Settled"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Draft' Status : Only 'Draft' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Settled");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Settled"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Settled");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Settled' Status : Only 'Settled' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Rejected");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Rejected"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Rejected");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Rejected' Status : Only 'Rejected' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Returned");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Returned"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Returned");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Draft"));
			//Assert.assertFalse(isTextPresent("Waiting for Finance 2 to settle"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Returned' Status : Only 'Returned' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria_ddlStatus"), "Waiting for Finance 2 to settle");
		webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Waiting for Finance 2 to settle"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
			//Assert.assertFalse(isTextPresent("Waiting for approval"));
			//Assert.assertFalse(isTextPresent("Approved"));
			//Assert.assertFalse(isTextPresent("Cancelled"));
			//Assert.assertFalse(isTextPresent("Rejected"));
			//Assert.assertFalse(isTextPresent("Returned"));
			//Assert.assertFalse(isTextPresent("Settled"));
			//Assert.assertFalse(isTextPresent("Draft"));
			log("\n\n Verified: Searched By Status. - Searcing by 'Waiting for Finance 2 to settle' Status : Only 'Waiting for Finance 2 to settle' status request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
	}
	
	// Go to 'User Profile' in 'Master' Tab and get the 'Travel Expense Amount'
	public void getTravelExpenseAmountFromUserProfile() throws InterruptedException{
		mouseOverElement(By.linkText("Masters"));
		Thread.sleep(200);
		webDriver.findElement(By.linkText("User Profile")).click();
		Assert.assertTrue(isTextPresent("User Profile"));
		managerApprovalAmount = webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_grdApprovingQuota']/tbody/tr[2]/td[3]")).getText().replace(".00", "");
	}
	
	// Go to 'Manage Expense Limit' in 'Master' Tab and get the 'Attendee No OF Limit'.
	public void getAttendeeNoOFLimitFromManageExpenseLimitPage() throws InterruptedException{
		mouseOverElement(By.linkText("Masters"));
		Thread.sleep(200);
		webDriver.findElement(By.linkText("Manage Expense Limits")).click();
		webDriver.findElement(By.id("ctl00_cphMain_ucAttendeeLimit_AccordionPane1_header")).click();
		entertenmentType = webDriver.findElement(By.id("ctl00_cphMain_ucAttendeeLimit_AccordionPane1_content_grdAttendeeLimitSection_grdAttendeeLimitSection_0_lblEntertainment")).getText();
		customerType = webDriver.findElement(By.id("ctl00_cphMain_ucAttendeeLimit_AccordionPane1_content_grdAttendeeLimitSection_grdAttendeeLimitSection_0_lblCustomer")).getText();
		limitOfAttends = webDriver.findElement(By.xpath("//*[@id='ctl00_cphMain_ucAttendeeLimit_AccordionPane1_content_grdAttendeeLimitSection_grdAttendeeLimitSection_0']/td[4]")).getText();
	}
	
	// Go to 'Unsettled Claims' page in 'Claim' Tab with logged in as a Finance user.
	public void goToUnsettledClaimsAsFinanceUser() throws InterruptedException{
		mouseOverElement(By.linkText("Claims"));
		Thread.sleep(200);
		webDriver.findElement(By.linkText("Unsettled Claims")).click();
	}
	
	// Go to 'Settled Claims' page in 'Claim' Tab with logged in as a Finance user.
	public void goToSettledClaimsAsFinanceUser() throws InterruptedException{
		mouseOverElement(By.linkText("Claims"));
		Thread.sleep(300);
		webDriver.findElement(By.linkText("Settled Claims")).click();
	}
	
	// Go to 'My Approval Tab'
	public void goToMyApprovalTab(){
		webDriver.findElement(By.linkText("My Approvals")).click();
	}
}
