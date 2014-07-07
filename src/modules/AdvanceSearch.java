package modules;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class AdvanceSearch extends TestNgHelper{
	
	Set_Read_ProperpertiesFile sRPF;
	private CommonMethods commonMethods;
	public static String addedClaimNo = "";

	//Constructor Of the Class
	public AdvanceSearch()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
		commonMethods = new CommonMethods();
	}

	// Home page - click to 'Advance Search' Tab.
	public void goToAdvanceSearch()throws Exception
	{
		Thread.sleep(1000);
		webDriver.findElement(By.linkText(sRPF.home_Xpath.get("Home_AdvanceSearchTab"))).click();
	}
	
	// Get Claim No. for Added Non Travel Expense.
	public void getAddedClaimtNo(){
		addedClaimNo = webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimNo"))).getText();
	}
	
	// Advance Search page - click on 'Search' Button.
	public void clickToSearchButton()throws Exception
	{
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(2000);
	}
	
	// Advance Search page - click on 'Clear Search' Button.
	public void clickToClearSearchButton()throws Exception
	{
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ClearSearchButton"))).click();
		Thread.sleep(1000);
	}
	
	// Advance Search - search by Claim No.
	public void searchByClaimNo(){
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_txtClaimNo"))).sendKeys(addedClaimNo);
	}
	
	// Advance Search - search by Requester Name.
	public void searchByRequesterName(){
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ttxtApplicantName"))).sendKeys(UserLogin.loggedInUserName);
	}
	
	// Advance Search - search by Submitted Date.
	public void searchBySubmittedDate() throws Exception{
		commonMethods.selectGivenDateFromCalendar(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ToDateCalendar"), CommonUse.getAfterTodaysDate());
		commonMethods.selectGivenDateFromCalendar(sRPF.advanceSearch_Xpath.get("AdvanceSearch_FromDateCalendar"), CommonUse.getBeforeTodaysDate());
	}
	
	// Advance Search - search by Claim Status.
	public void searchByClaimStatus() throws Exception{
		// search with 'junk characters'
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_txtClaimNo"))).sendKeys("tset");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		Assert.assertTrue(isTextPresent("No record found."));
		log("\n\n Verified: Searched with 'junk characters' - message is displayed as 'No records found' on page.");
		clickToClearSearchButton();
		// Search with Status and check All option present.
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Waiting for approval");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Approved");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "3");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Draft");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "4");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Rejected");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "5");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Returned");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "6");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Settled");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "7");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Waiting for Finance 2 to settle");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "8");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus")), "Cancelled");
		log("\n\n Verified: Searched By Status. - All Status Type Appearing in Dropdon.");
		// Search with Status.
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Waiting for approval");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Awaiting approval"));
			//Assert.assertEquals(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Awaiting approval");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Approved");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Approved"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Approved");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Cancelled");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Cancelled"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Cancelled");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Draft");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Draft"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Draft");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Settled");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Settled"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Settled");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Rejected");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Rejected"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Rejected");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Returned");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Returned"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Returned");
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
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Waiting for Finance 2 to settle");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Waiting for Finance 2 to settle"));
			Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_Status"))).getText(), "Waiting for Finance 2 to settle");
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
	
	// Advance Search - Search by claim type.
	public void searchByClaimType() throws InterruptedException, IOException{
		// Search with Claim type and check All option present.
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "1");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType")), "Pre-Approval Form");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "2");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType")), "Non Travel Expense");
		selectByIndex(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "3");
		Assert.assertEquals(getSelectedLabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType")), "Travel Expense");
		log("\n\n Verified: Searched By Status. - All Claim Type Appearing in Dropdon.");
		// Search with Status.
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "Pre-Approval Form");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Pre-Approval Form"));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimType"))).getText(), "Pre-Approval Form");
			//Assert.assertFalse(isTextPresent("Non Travel Expense"));
			//Assert.assertFalse(isTextPresent("Travel Expense"));
			log("\n\n Verified: Searched By Claim Type. - Searcing by 'Pre-Approval Form' Claim Type : Only 'Pre-Approval Form' Claim Type request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "Non Travel Expense");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Non Travel Expense"));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimType"))).getText(), "Non Travel Expense");
			//Assert.assertFalse(isTextPresent("Pre-Approval Form"));
			//Assert.assertFalse(isTextPresent("Travel Expense"));
			log("\n\n Verified: Searched By Claim Type. - Searcing by 'Non Travel Expense' Claim Type : Only 'Non Travel Expense' Claim Type request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "Travel Expense");
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))).click();
		Thread.sleep(1000);
		if(!isTextPresent("No record found.")){
			Assert.assertTrue(isTextPresent("Travel Expense"));
			Assert.assertEquals(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimType"))).getText(), "Travel Expense");
			//Assert.assertFalse(isTextPresent("Pre-Approval Form"));
			//Assert.assertFalse(isTextPresent("Non Travel Expense"));
			log("\n\n Verified: Searched By Claim Type. - Searcing by 'Travel Expense' Claim Type : Only 'Travel Expense' Claim Type request displaying.");
		}
		else
			log("\n\n Verified: Record Not Found for Search Criteria");
	}
	
	// Search for 'Travel Expense' Settled Claim.
	public void searchForSettledClaims() throws Exception{
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "Travel Expense");
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Settled");
		clickToSearchButton();
	}
	
	// Select the Settled Claim for 'Archive'.
	public void checkSettledClaimForArchive() throws Exception{
		getAddedClaimtNo();
		Assert.assertFalse(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ClaimCheck1"))).isSelected());
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ClaimCheck1"))).click();
		webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ArchiveButton"))).click();
		Thread.sleep(1000);
	}
}
