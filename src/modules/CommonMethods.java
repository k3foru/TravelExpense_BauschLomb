package modules;

import org.openqa.selenium.By;

import util.CommonUse;
import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class CommonMethods extends TestNgHelper{
	
	Set_Read_ProperpertiesFile sRPF;

	//Constructor Of the Class
	public CommonMethods()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
	}

	// Select Given Date from Calendar on Current Page.
	public void selectGivenDateFromCalendar(String calImgID, String dateString)throws Exception
	{
		Thread.sleep(1000);
		// Click to Calendar Img link
		webDriver.findElement(By.id(calImgID)).click();
		//webDriver.findElement(By.xpath("//input[@alt='Click to show calendar']")).click();
		Thread.sleep(1000);
		//webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("DateCalendarImgID_title"))).click();
		// Click to Calendar header title.
		webDriver.findElement(By.xpath("//div[@mode='title']")).click();
		Thread.sleep(1000);
		CommonUse.separateDateFormate(dateString);
		// Click to Selected Month
		webDriver.findElement(By.xpath("//div[@month='"+CommonUse.month_t+"']")).click();
		Thread.sleep(1000);
		// Click to selected Date.
		//webDriver.findElement(By.xpath("//*[text()='"+CommonUse.day_t+"']")).click();
		webDriver.findElement(By.xpath("//div[@title='"+CommonUse.day_t+"']")).click();
	}
	
	// Select Given Date from Second Calendar on Current Page.
	public void selectGivenDateFromSecondCalendar(String calImgID, String dateString)throws Exception
	{
		Thread.sleep(1000);
		webDriver.findElement(By.id(calImgID)).click();
		//webDriver.findElement(By.xpath("//input[@alt='Click to show calendar']")).click();
		Thread.sleep(1000);
		//webDriver.findElement(By.id(sRPF.userClaims_Xpath.get("DateCalendarImgID_title"))).click();
		webDriver.findElement(By.id("ctl00_cphMain_CalendarExtender2_title")).click();
		Thread.sleep(1000);
		CommonUse.separateDateFormate(dateString);
		webDriver.findElement(By.xpath("//*[@month='"+CommonUse.month_t+"']")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("//*[text()='"+CommonUse.day_t+"']")).click();
	}
}
