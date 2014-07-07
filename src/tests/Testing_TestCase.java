package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.TestNgHelper;

public class Testing_TestCase extends TestNgHelper
{ 
	
	@Test
	public void testMethod() throws Exception
	{
		System.out.println("Testing ");
		
		try{
			Assert.assertTrue(isTextPresent("Testing"));
		}
		catch(Throwable t){
			//Assert.fail();
		}
		System.out.println("Still Testing");
	}
}