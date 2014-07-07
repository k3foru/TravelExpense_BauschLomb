package util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import modules.UserLogin;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import common.XLsWriter;

/**
 * Web Driver Configuration Class using TestNG Framework.
 * 
 * @author Abhishek Singh
 * 
 */
public class TestNgHelper{
	
	protected static WebDriver webDriver;
	private Set_Read_ProperpertiesFile sRPF;
	public static String  parentWindowHandle;
	
	public String testCaseID;
	public String testCase;
	public static String summary;
	public static String description;
	public static int testCaseNo = 3;
	public static StringBuffer sb = new StringBuffer(4000); 
	
	@BeforeSuite(alwaysRun=true)
	public void startDriver() throws Exception{
		
		sRPF=new Set_Read_ProperpertiesFile();
		
		// *********************** Run with Internet Explorer Browser. *************** //
		
		System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
		DesiredCapabilities browserCapabillities = DesiredCapabilities.internetExplorer();  
        browserCapabillities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
		//browserCapabillities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		webDriver = new InternetExplorerDriver(browserCapabillities);
		
		//webDriver.manage().window().maximize();
		webDriver.get(sRPF.userLogin_Data.get("TEUrl")); 
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Do the Login As Normal User In Application.
		UserLogin userLogin=new UserLogin();
		userLogin.goToUserWelcomeHome(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		
		// TO Delete the ScreenShots directory if exist and create a empty directory with same name.
		CommonUse.createAndDeleteDirectory(new File("ScreenShot_TestFailed"));
		CommonUse.createAndDeleteDirectory(new File("ScreenShot_TestPassed"));
	}
	
	@BeforeClass(alwaysRun = true)
	public void createIntance() throws Exception
	{
		parentWindowHandle = webDriver.getWindowHandle();
		generateLogFile();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void selectParentWindow() throws Exception{
		webDriver.switchTo().window(parentWindowHandle);
		
		if(!isTextPresent("Welcome "+UserLogin.loggedInUserName)){
			// Do the Login As Normal User In Application.
			UserLogin userLogin=new UserLogin();
			userLogin.userLogout();
			userLogin.goToUserWelcomeHome(sRPF.userLogin_Data.get("username"), sRPF.userLogin_Data.get("password"));
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void toTekeScreenshot(ITestResult result)
	{
		System.out.println("Calling After Method");
		try
		{
	        String methodName = result.getName();
	        String classname = result.getTestClass().getRealClass().getName();
		   if(!result.isSuccess())
		    {
			   // Get the Screen shot while test case Failed.
		    	String filename = classname+"."+methodName+".png";
	    		File sourceimageFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	    		FileUtils.copyFile(sourceimageFile, new File("ScreenShot_TestFailed/"+filename));
	    		// Screen Shot will attached with log file.
	    		Reporter.log("<font color=\"RED\">Failed Screendhot</font> : <a href=\"..\\..\\ScreenShot_TestFailed\\"+filename+"\"><img src=\"..\\..\\ScreenShot_TestFailed\\"+filename+"\"width=\"100\" height=\"131\" border=\"0\"></a>");
	    		// Write whole Test Result Status of Test Case in Excel Report file.
	    		XLsWriter witer = new XLsWriter();
	    		witer.writeDataInXLs(methodName, methodName, summary, description, "Fail");
	    		testCaseNo++;
	    		sb.delete(0, sb.length());
		    }
		   else{
			   // Get the Screen shot while test case Passed.
			   String filename = classname+"."+methodName+".png";
			   File sourceimageFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
			   FileUtils.copyFile(sourceimageFile, new File("ScreenShot_TestPassed/"+filename));
			   // Screen Shot will attached with log file.
			   Reporter.log("<font color=\"GREN\">Passed Screendhot</font> : <a href=\"..\\..\\ScreenShot_TestPassed\\"+filename+"\"><img src=\"..\\..\\ScreenShot_TestPassed\\"+filename+"\"width=\"100\" height=\"131\" border=\"0\"></a>");
			   // Write whole Test Result Status of Test Case in Excel Report file.
	    		XLsWriter witer = new XLsWriter();
	        	witer.writeDataInXLs(methodName, methodName, summary, description, "Pass");
	        	testCaseNo++;
	        	sb.delete(0, sb.length());
		   }
		}
		catch (Exception e){}
		
		// close all window except parent window.
		if(!webDriver.getWindowHandle().equals(parentWindowHandle))
		{
			webDriver.close();
		}
	}
	
	@AfterClass(alwaysRun=true)
	public void stopBrowser(){
	}
	
	@AfterSuite(alwaysRun=true)
	public void stopDriver(){
		webDriver.quit(); 
	}
	
	// Generate a log file of Test.
	public void generateLogFile() throws SecurityException, IOException{
		Logger logger = Logger.getLogger("");
		FileHandler fh;
		fh = new FileHandler("log/logs.txt");
		logger.addHandler(fh);
		logger.setLevel(Level.ALL);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
	
	// Customize Method for wait for element on page.
    public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
        return new ExpectedCondition<WebElement>() {
          public WebElement apply(WebDriver driver) {
            WebElement element = driver.findElement(by);
            return element.isDisplayed() ? element : null;
          }
        };
    }
    
    // Customize Method for wait for condition on page.
    public  static void waitForCondition(By by) {
        WebDriverWait wait = new  WebDriverWait(webDriver, 100);
        wait.until(visibilityOfElementLocated(by));
   
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        //WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0,0,5));
      }
      
    // Customize Method for select Item by Index in Drop down.
    public static void selectByIndex(String id, String index) {

    	Select select = new Select(webDriver.findElement(By.id(id)));
    	int byIndex = Integer.parseInt(index);
    	select.selectByIndex(byIndex);
    }
        
 // Customize Method for select Item by Label in Drop down.
    public static void selectBylabel(String id, String label) {
    	
    	Select select = new Select(webDriver.findElement(By.id(id)));
    	select.selectByVisibleText(label);
    }
    
    // Customize Method for Compare And select Item by Label in Drop down if present.
    public static void selectAndCompareItemBylabel(String id, String label) {

    	WebElement selectElement = webDriver.findElement(By.id(id));
    	List<WebElement> componentList = selectElement.findElements(By.tagName("option"));
    	for (WebElement component : componentList){
    		if(label.equals(component.getText())){
    			component.click();
  	    	    break;
  	     }
    		else{
  	    	     	System.out.println(label+" not Exist");
  	      	}
    	}
    }
    
    // Customize Method to verify Element present on current page.
    public static boolean isElementPresent(By by) {
    	try{
    		webDriver.findElement(by);
        	return true;
    	}
    	catch(Exception e){
    		return false;
    	}
	}
    
    // Customize Method to verify Text present on current page.
    public static boolean isTextPresent(String text) {
    	if(webDriver.findElement(By.tagName("body")).getText().contains(text))
    		return true;
    	else
    		return false;
	}
    
    // Mouse Over to an Element
    public static void mouseOverElement(By by){
    	Actions builder = new Actions(webDriver);   
    	builder.moveToElement(webDriver.findElement(by)).perform();
    }
    
    public static void childWindowHandler(By by) throws InterruptedException{
    	
    	Set<String> oldWindows = webDriver.getWindowHandles();
    	webDriver.findElement(by).click();
    	Thread.sleep(5000);
    	Set<String> allWindows = webDriver.getWindowHandles();
    	allWindows.removeAll(oldWindows);
    	String newWindow = allWindows.iterator().next();
    	webDriver.switchTo().window(newWindow);
    	//webDriver.manage().window().maximize();
    	System.out.println("Title of window :::"+webDriver.getTitle());
    }
    
    protected static String getSelectedLabel(String id){
    	String selectedLabel = new Select(webDriver.findElement(By.id(id))).getFirstSelectedOption().getText();
		return selectedLabel;
    }
    
	public static void log(String str) throws IOException{
		Reporter.log("<li style=\"color:#0000FF\"> "+str+" </li>");
		description = sb.append("-").append(str).append(" \n").toString();
		//Reporter.log("<TABLE WORD-BREAK:BREAK-ALL hspace = 50 vspace = 50 ALIGN=CENTER width=0 BORDER=0> " +"<TR bgcolor= "+ color +" ><TD>"+ str +"</TD></TR></TABLE>");
	}
	
	public static void header(String str){
		Reporter.log("<h3 style=\"color:#000080\"> "+str+" </h3>");
		summary = str;
		//Reporter.log("<TABLE WORD-BREAK:BREAK-ALL hspace = 50 vspace = 50 ALIGN=CENTER width=0 BORDER=0> " +"<TR bgcolor= "+ color +" ><TD>"+ str +"</TD></TR></TABLE>");
	}
	
	// Override Verification Command.
	// @Override static method of super class (which assumes TestNG conventions)
	public void verifyTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	// @Override static method of super class (which assumes TestNG conventions)
	public static void assertEquals(Object actual, Object expected) {
		Assert.assertEquals(expected, actual);
	}

	// @Override static method of super class (which assumes TestNG conventions)
	public static void assertEquals(String actual, String expected) {
		Assert.assertEquals(expected, actual);
	}
	
	// @Override static method of super class (which assumes TestNG conventions)
	public static void assertEquals(String actual, String[] expected) {
		Assert.assertEquals(expected, actual);
	}

	// @Override static method of super class (which assumes TestNG conventions)
	public static void assertEquals(String[] actual, String[] expected) {
		Assert.assertEquals(expected, actual);
	}
	
	// @Override static method of super class (which assumes TestNG conventions)
	public static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}
	
	// @Override static method of super class (which assumes TestNG conventions)
	public static void assertFalse(boolean condition) {
		Assert.assertFalse(condition);
	}
}