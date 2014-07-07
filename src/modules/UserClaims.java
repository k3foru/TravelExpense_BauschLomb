package modules;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.testng.Reporter;

import com.thoughtworks.selenium.Selenium;

import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class UserClaims extends TestNgHelper{
	
	Set_Read_ProperpertiesFile sRPF;
	private Selenium selenium;
	private CommonMethods commonMethods;

	//Constructor Of the Class
	public UserClaims()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
		commonMethods = new CommonMethods();
	}

	//Normal User Home page - click to 'Claim' Tab and Go to Claim for 'Travel Expense'.
	public void claimForTravelExpense()throws Exception
	{
		Thread.sleep(1000);
		mouseOverElement(By.linkText(sRPF.userClaims_Xpath.get("Home_ClaimTab")));
		webDriver.findElement(By.linkText(sRPF.userClaims_Xpath.get("Home_ClaimTab_TravelExpenseLink"))).click();
		//webDriver.findElement(By.xpath(sRPF.userClaims_Xpath.get("Home_ClaimTab_TravelExpense"))).click();
		waitForCondition(By.id("ctl00_cphMain_ddlSearch"));
	}
	
	// On Travel Expense List page - click to Add New Button to add new claim.
	public void clickToAddNewClaimBtn(){
		
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("Home_ClaimTab_AddNewButn"))).click();
	}
	
	// On Travel Expense page - Fill 'Business Purpose of trip' and 'No. of Tickets' fields. 
	public void fillTravelExpenseClaim() throws Exception{
		
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtBusinessPurposeNPlan"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_txtBusinessPurposeNPlan"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_txtNoOfTickets"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_txtNoOfTickets"));
	}
	
	public void verifyElementsOnClaimPage(){
		
	}
	
	// On Travel Expense page - Check the validation of Fields on click of Save and Add button in 'Travel Transportation Section'.
	public void checkValidation_TravelTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		// Check the validation of Fields on click of Save and Add button.
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert = webDriver.switchTo().alert();
		Reporter.log("On click Add Button - validation for Fields which is Mandatory:: "+alert.getText());
		alert.accept();
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_SaveButton"))).click();
		Thread.sleep(1000);
		Alert alert2 = webDriver.switchTo().alert();
		Reporter.log("\n On click Save Button - validation for Fields which is Mandatory:: "+alert2.getText());
		alert2.accept();
		// Check the validation for 'Departure Date & Time' fields.
		commonMethods.selectGivenDateFromCalendar(sRPF.userClaims_Xpath.get("DateCalendarImgID"), sRPF.userClaims_Data.get("TravelExpense_DepartureDateForValidation"));
		selenium = new WebDriverBackedSelenium(webDriver, sRPF.userLogin_Data.get("TEUrl"));
		selenium.getEval("window.document.getElementById('"+sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTimeForValidation")+"'");
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert3 = webDriver.switchTo().alert();
		Reporter.log("\n On click Add Button - validation for 'Departure Date & Time' Fields which is Mandatory:: "+alert3.getText());
		alert3.accept();
		// Check the validation for 'Location from & Location to' fields.
		commonMethods.selectGivenDateFromCalendar(sRPF.userClaims_Xpath.get("DateCalendarImgID"), sRPF.userClaims_Data.get("TravelExpense_DepartureDate"));
		selenium = new WebDriverBackedSelenium(webDriver, sRPF.userLogin_Data.get("TEUrl"));
		selenium.getEval("window.document.getElementById('"+sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"'");
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlCurrency"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
		Alert alert4 = webDriver.switchTo().alert();
		Reporter.log("\n On click Add Button - Checked the validation for 'Location from & Location to' fields.:: "+alert4.getText());
		alert4.accept();
	}
	
	// On Travel Expense page - Fill All Expense Information in 'Travel Transportation Section'.
	public void fillTravelTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader"))).click();
		commonMethods.selectGivenDateFromCalendar(sRPF.userClaims_Xpath.get("DateCalendarImgID"), sRPF.userClaims_Data.get("TravelExpense_DepartureDate"));
		selenium = new WebDriverBackedSelenium(webDriver, sRPF.userLogin_Data.get("TEUrl"));
		selenium.getEval("window.document.getElementById('"+sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"').value='"+sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtDepartureTime")+"'");
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationFrom"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlLocationTo"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlTransportationType"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlPaymentMode"));
		selectBylabel(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_ddlCurrency"), sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_ddlCurrency"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtAmount"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).clear();
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtRate"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtRate"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
		Thread.sleep(1000);
	}
	
	public void clickToSaveButnTravelTransportation(){
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_SaveButton"))).click();
	}
	
	// Edit the Added Expense amount and click to update button.
	public void editAddedTravelTransportationDetails() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_imgEdit"))).click();
		Thread.sleep(100);
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).clear();
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_txtAmount"))).sendKeys(sRPF.userClaims_Data.get("TravelExpense_TravelTransportationHeader_txtEditedAmount"));
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_TravelTransportationHeader_AddButton"))).click();
	}
	
	public void lodgingSection(){
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_LodgingHeader"))).click();
	}
	
	public void localTransportationSection() throws Exception{
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_LocalTransportationHeader"))).click();
		Thread.sleep(1000);
		childWindowHandler(By.id(sRPF.userClaims_Xpath.get("TravelExpense_LocalTransportationHeader_AddButn")));
		Thread.sleep(4000);
		commonMethods.selectGivenDateFromCalendar(sRPF.userClaims_Xpath.get("TravelExpense_LocalTransportationHeader_DateCalendarImgID1"), sRPF.userClaims_Data.get("TravelExpense_DepartureDate"));
	}
	
	public void clickToSubmitClaim() throws InterruptedException{
		webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("TravelExpense_SubmitButton"))).click();
		Thread.sleep(1000); 
	}
}