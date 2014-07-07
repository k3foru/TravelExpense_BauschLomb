package tests.advanceSearch;

import modules.AdvanceSearch;
import modules.TravelExpenseClaim;
import modules.UserLogin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class AdvanceSearch_TestCases extends TestNgHelper{
	
	private Set_Read_ProperpertiesFile sRPF;
	
	// User Logged in and Click to 'Advance Search' Tab, Verify Page navigate to 'Advance Search' page and All Fields are Present.
	@Test (description="User Logged in and Click to 'Advance Search' Tab, Verify Page navigate to 'Advance Search' page and All Fields are Present.", groups = { "verifyFieldsPresentOnAdvanceSearchPage" })
	public void verifyFieldsPresentOnAdvanceSearchPage() throws Exception
	{
		header("User Logged in and Click to 'Advance Search' Tab, Verify Page navigate to 'Advance Search' page and All Fields are Present.");
		sRPF = new Set_Read_ProperpertiesFile();
		AdvanceSearch advanceSearch = new AdvanceSearch();
		advanceSearch.goToAdvanceSearch();
		// Verify application navigates to Advance Search page
		Assert.assertEquals(CommonUse.getURLName(webDriver.getCurrentUrl()), "AdvanceSearch");
		log("Verified: application navigates to 'Advance Search' page by click on 'Advance Search' tab");
		Assert.assertTrue(isTextPresent("Advance Search"));
		// Verify All Fields are present on page (ClaimNo, Include Archive, ApplicantName, 'FromDateCalendar', 'ToDateCalendar', 'ClaimType', 'ClaimStatus', 'Search Button').
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_txtClaimNo"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_chkArch"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ttxtApplicantName"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_txtApprover"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_FromDateCalendar"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ToDateCalendar"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlDepartment"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"))));
		Assert.assertTrue(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SearchButton"))));
		log("Verified: Fields are present on page (ClaimNo, Include Archive, ApplicantName, 'FromDateCalendar', 'ToDateCalendar', 'ClaimType', 'ClaimStatus', 'Search Button').");
		advanceSearch.clickToSearchButton();
		Assert.assertTrue(isElementPresent(By.linkText(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimNo"))).getText())));
		log("Verified: Claim no. are a link");
		//  After click on 'Search' Button, list of records should be displayed in a tabular format under columns name present on the page
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ClaimNo"))).getText(), "Claim No.");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_RequestorName"))).getText(), "Requestor Name");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ClaimType"))).getText(), "Claim Type");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_TotalExpense"))).getText(), "Total Expense");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_CreationDate"))).getText(), "Creation Date");
		Assert.assertEquals(webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_Status"))).getText(), "Status");
		log("Verified: After click on 'Search' Button, list of records should be displayed in a tabular format under columns name present on the page.");
		log("Verified: After click on 'Search' Button, Default All Recard displaying on page without any search keyword.");
	}
	
	// User Logged in and Click to 'Advance Search' Tab, search By Claim No, Requester Name & Submitted Date and verify Search functionality searching by given keyword.
	@Test (description="User Logged in and Click to 'Advance Search' Tab, search By Claim No, Requester Name & Submitted Date and verify Search functionality searching by given keyword.", groups = { "searchByClaimNoRequesterNameAndSubmittedDate_VerifySearchFuntionality" })
	public void searchByClaimNoRequesterNameAndSubmittedDate_VerifySearchFuntionality() throws Exception
	{
		header("User Logged in and Click to 'Advance Search' Tab, search By Claim No, Requester Name & Submitted Date and verify Search functionality searching by given keyword.");
		sRPF = new Set_Read_ProperpertiesFile();
		AdvanceSearch advanceSearch = new AdvanceSearch();
		advanceSearch.goToAdvanceSearch();
		advanceSearch.clickToSearchButton();
		// search By Claim No
		advanceSearch.getAddedClaimtNo();
		advanceSearch.searchByClaimNo();
		advanceSearch.clickToSearchButton();
		Assert.assertEquals(webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimNo"))).getText(), AdvanceSearch.addedClaimNo);
		Assert.assertFalse(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_SecondGridData_ClaimNo"))));
		log("Verified: Search by claim no. and getting only searched claim no.");
		// search By Requester Name
		advanceSearch.clickToClearSearchButton();
		advanceSearch.searchByRequesterName();
		advanceSearch.clickToSearchButton();
		Assert.assertEquals( webDriver.findElement(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_RequestorName"))).getText(), UserLogin.loggedInUserName);
		log("Verified: Search by Requester Name and getting only searched Requester Name.");
		// search By Submitted Date
		advanceSearch.clickToClearSearchButton();
		advanceSearch.searchBySubmittedDate();
		advanceSearch.clickToSearchButton();
		if(isTextPresent("No record found."))
			Assert.assertFalse(isElementPresent(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_RequestorName"))));
		else
			Assert.assertTrue(isElementPresent(By.xpath(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_RequestorName"))));
		log("Verified: Search by Submitted Date and getting result by submitted date");
	}
	
	// User Logged in and Click to 'Advance Search' Tab, search By Claim Status. and verify Search functionality searching by given keyword.
	@Test (description="User Logged in and Click to 'Advance Search' Tab, search By Claim Status. and verify Search functionality searching by given keyword.", groups = { "searchByClaimStatus_VerifySearchResult" })
	public void searchByClaimStatus_VerifySearchResult() throws Exception
	{
		header("User Logged in and Click to 'Advance Search' Tab, search By Claim Status. and verify Search functionality searching by given keyword.");
		sRPF = new Set_Read_ProperpertiesFile();
		AdvanceSearch advanceSearch = new AdvanceSearch();
		advanceSearch.goToAdvanceSearch();
		// search By Claim Status
		advanceSearch.searchByClaimStatus();
		log("Verified: Search by claim Status.");
	}

	// User Logged in and Click to 'Advance Search' Tab, search By Claim Type. and verify Search functionality searching by given keyword.
	@Test (description="User Logged in and Click to 'Advance Search' Tab, search By Claim Type. and verify Search functionality searching by given keyword.", groups = { "searchByClaimType_VerifySearchResult" })
	public void searchByClaimType_VerifySearchResult() throws Exception
	{
		header("User Logged in and Click to 'Advance Search' Tab, search By Claim Type. and verify Search functionality searching by given keyword.");
		sRPF = new Set_Read_ProperpertiesFile();
		AdvanceSearch advanceSearch = new AdvanceSearch();
		advanceSearch.goToAdvanceSearch();
		// search By Claim Status
		advanceSearch.searchByClaimType();
		log("Verified: Search by Claim Type.");
	}
	
	// User Logged in and Click to 'Advance Search' Tab, Search By Claim Status 'Settled' and Claim Type 'Pre Approval' and result should be 'No record found.'.
	@Test (description="User Logged in and Click to 'Advance Search' Tab, Search By Claim Status 'Settled' and Claim Type 'Pre Approval' and result should be 'No record found.'.", groups = { "searchByClaimStatusAndClaimType_VerifySearchResult" })
	public void searchByClaimStatusAndClaimType_VerifySearchResult() throws Exception
	{
		header("User Logged in and Click to 'Advance Search' Tab, Search By Claim Status 'Settled' and Claim Type 'Pre Approval' and result should be 'No record found.'");
		sRPF = new Set_Read_ProperpertiesFile();
		AdvanceSearch advanceSearch = new AdvanceSearch();
		advanceSearch.goToAdvanceSearch();
		// Search By Claim Status 'Waiting for Finance 2 to settle' and Claim Type 'Pre Approval'.
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "Pre-Approval Form");
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Waiting for Finance 2 to settle");
		advanceSearch.clickToSearchButton();
		Assert.assertTrue(isTextPresent("No record found."));
		Assert.assertFalse(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimNo"))));
		log("Verified: Searched By Claim Status 'Waiting for Finance 2 to settle' and Claim Type 'Pre-Approval Form' and getting result is 'No record found.'");
		// Search By Claim Status 'Settled' and Claim Type 'Pre Approval'.
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimType"), "Pre-Approval Form");
		selectBylabel(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ddlClaimStatus"), "Settled");
		advanceSearch.clickToSearchButton();
		Assert.assertTrue(isTextPresent("No record found."));
		Assert.assertFalse(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_DataGrid_ClaimNo"))));
		log("Verified: Searched By Claim Status 'Settled' and Claim Type 'Pre-Approval Form' and getting result is 'No record found.'");
	}
	
	// User Logged in and Click to 'Advance Search' Tab, Select the Claim for 'Archive' and verify That claim no. should not appear on other page.
	@Test (description="User Logged in and Click to 'Advance Search' Tab, Select the Claim for 'Archive' and verify That claim no. should not appear on other page.", groups = { "VerifyToArchiveClaim_AdvanceSearch" })
	public void VerifyToArchiveClaim_AdvanceSearch() throws Exception
	{
		header("User Logged in and Click to 'Advance Search' Tab, Select the Claim for 'Archive' and verify That claim no. should not appear on other page.");
		sRPF = new Set_Read_ProperpertiesFile();
		AdvanceSearch advanceSearch = new AdvanceSearch();
		TravelExpenseClaim travelExpenseClaim = new TravelExpenseClaim();
		advanceSearch.goToAdvanceSearch();
		// Search for 'Travel Expense' Settled Claim.
		advanceSearch.searchForSettledClaims();
		// Select the Settled Claim for 'Archive'.
		if(isElementPresent(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_ClaimCheck1")))){
			advanceSearch.checkSettledClaimForArchive();
			Assert.assertFalse(isElementPresent(By.linkText(AdvanceSearch.addedClaimNo)));
			log("Verified: Checked Claim for 'Archive' and Archive claim no. removing from search list.");
			webDriver.findElement(By.id(sRPF.advanceSearch_Xpath.get("AdvanceSearch_chkArch"))).click();
			advanceSearch.clickToSearchButton();
			// Searching with 'Include Archive' and getting in List 'Archived claim no.
			Assert.assertTrue(isElementPresent(By.linkText(AdvanceSearch.addedClaimNo)));
			log("Verified: Searching with 'Include Archive' and getting in List 'Archived claim no.'");
			// Verify Archived Claim No. on 'Travel Expense' page
			travelExpenseClaim.claimForTravelExpense();
			// Search with  Archived Claim No.
			selectBylabel(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_ddlSearchCriteria"), "Claim No.");
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_txtSearchValue"))).sendKeys(AdvanceSearch.addedClaimNo);
			webDriver.findElement(By.id(sRPF.travelExpenseClaim_Xpath.get("TravelExpenseList_SearchButton"))).click();
			Thread.sleep(1000);
			Assert.assertTrue(isTextPresent("No record found."));
			Assert.assertFalse(isElementPresent(By.id("ctl00_cphMain_grdTE_grdTE_0_lnkClaimNo")));
			log("Verified: On Travel Expense page searching with 'Archived Claim No.' and getting result is 'No record found'.");
		}
		else
			log("There is no any one settled claim for Archive, All Claim Archived");
	}
}
