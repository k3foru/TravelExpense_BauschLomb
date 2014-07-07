package common;

import  java.io.*;  
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import  org.apache.poi.hssf.usermodel.HSSFRow;

import util.TestNgHelper;

public class XLsWriter{
	
	private HSSFWorkbook workbook =null;
	private String filename="Summary/AutomationTestResult.xls" ;
 	
    public void writeDataInXLs(String tsetCaseID, String testCase, String summary, String desc, String status){
		try{
			FileInputStream fis=new FileInputStream(filename);
			workbook=new HSSFWorkbook(fis);
			HSSFSheet st=workbook.getSheet("TestResult");
			HSSFRow row=st.createRow(TestNgHelper.testCaseNo);
			row.createCell((short) 1).setCellValue("ScreenShot");
			row.createCell((short) 2).setCellValue(tsetCaseID);
			row.createCell((short) 3).setCellValue(testCase);
			row.createCell((short) 4).setCellValue(summary);
			row.createCell((short) 5).setCellValue(desc);
			row.createCell((short) 6).setCellValue(status);
			FileOutputStream fileOut =  new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");
		} 
		catch ( Exception ex ) {
			ex.getStackTrace();
		    System.out.println(ex.getMessage());
		}
    }
    
    public static void main(String[]args) throws IOException{
    	XLsWriter witer = new XLsWriter();
    	witer.writeDataInXLs("rrr", "yy", "ii", "hh","oo");
    }
}