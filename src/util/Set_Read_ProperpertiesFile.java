package util;

import java.io.File;

public class Set_Read_ProperpertiesFile
{
	public ReadProperties userLogin_Xpath=new ReadProperties();
	public ReadProperties userLogin_Data=new ReadProperties();
	public ReadProperties userClaims_Xpath=new ReadProperties();
	public ReadProperties userClaims_Data=new ReadProperties();
	public ReadProperties preApprovalClaim_Xpath=new ReadProperties();  
	public ReadProperties preApprovalClaim_Data=new ReadProperties();
	public ReadProperties travelExpenseClaim_Xpath=new ReadProperties();  
	public ReadProperties travelExpenseClaim_Data=new ReadProperties();
	public ReadProperties nonTravelExpenseClaim_Xpath=new ReadProperties();  
	public ReadProperties nonTravelExpenseClaim_Data=new ReadProperties();
	public ReadProperties myApprovals_Xpath=new ReadProperties();  
	public ReadProperties myApprovals_Data=new ReadProperties();
	public ReadProperties advanceSearch_Xpath=new ReadProperties();  
	public ReadProperties advanceSearch_Data=new ReadProperties();
	public ReadProperties home_Xpath=new ReadProperties();  
	public ReadProperties home_Data=new ReadProperties();
	
	public Set_Read_ProperpertiesFile()throws Exception
	{
		//  Read user Login properties file    TravelExpenseClaim_Data
		
		userLogin_Xpath.setFile(new File("Resources/UserLogin_Xpath.properties"));
		userLogin_Data.setFile(new File("Resources/UserLogin_Data.properties"));
		userLogin_Xpath.readFile();
		userLogin_Data.readFile();
		
		//  Read user Claim properties file    TravelExpenseClaim_Data
		
		userClaims_Xpath.setFile(new File("Resources/UserClaims_Xpath.properties"));
		userClaims_Data.setFile(new File("Resources/UserClaims_Data.properties"));
		userClaims_Xpath.readFile();
		userClaims_Data.readFile();
		
		//  Read Pre Approval Claim properties file
		
		preApprovalClaim_Xpath.setFile(new File("Resources/PreApprovalClaim_Xpath.properties"));
		preApprovalClaim_Data.setFile(new File("Resources/PreApprovalClaim_Data.properties"));
		preApprovalClaim_Xpath.readFile();
		preApprovalClaim_Data.readFile();
		
		//  Read Travel Expense Claim properties file
		
		travelExpenseClaim_Xpath.setFile(new File("Resources/TravelExpenseClaim_Xpath.properties"));
		travelExpenseClaim_Data.setFile(new File("Resources/TravelExpenseClaim_Data.properties"));
		travelExpenseClaim_Xpath.readFile();
		travelExpenseClaim_Data.readFile();
		
		//  Read Non Travel Expense properties file
		
		nonTravelExpenseClaim_Xpath.setFile(new File("Resources/NonTravelExpenseClaim_Xpath.properties"));
		nonTravelExpenseClaim_Data.setFile(new File("Resources/NonTravelExpenseClaim_Data.properties"));
		nonTravelExpenseClaim_Xpath.readFile();
		nonTravelExpenseClaim_Data.readFile();
		
		//  Read My Approvals properties file
		
		myApprovals_Xpath.setFile(new File("Resources/MyApprovals_Xpath.properties"));
		myApprovals_Data.setFile(new File("Resources/MyApprovals_Data.properties"));
		myApprovals_Xpath.readFile();
		myApprovals_Data.readFile();
		
		//  Read Advance Search properties file
		
		advanceSearch_Xpath.setFile(new File("Resources/AdvanceSearch_Xpath.properties"));
		advanceSearch_Data.setFile(new File("Resources/AdvanceSearch_Data.properties"));
		advanceSearch_Xpath.readFile();
		advanceSearch_Data.readFile();
		
		//  Read Home Page properties file
		
		home_Xpath.setFile(new File("Resources/Home_Xpath.properties"));
		home_Data.setFile(new File("Resources/Home_Data.properties"));
		home_Xpath.readFile();
		home_Data.readFile();
	}
}