package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import net.sf.jlue.util.RandomString;

public class RandomUserName
{
	@SuppressWarnings("static-access")
	public static String getRandom(int length) {
	// magic
		RandomString rs= new RandomString();
		 char[] ch = {'a','b','c','d','e','f','g','h','i','j'}; 
	  	  return(rs.random(length,ch));
	
	//return myRandom.substring(length);
	
   }
	@SuppressWarnings("static-access")
	public String randomAlphaNumeric()
	{
		RandomString rs= new RandomString();
  	   //char[] ch = {'a','b','c','d','e','f','g','h','i','j'}; 
  	   return(rs.random(8));
	}
	
	
	public String getStringToDate(String projectStartDate,String currentDate )
	{
		 DateFormat df = new SimpleDateFormat ("MM/dd/YYYY");
		
        
         // Get Date 1
         Date d1 = null;
		try {
			d1 = df.parse(projectStartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         // Get Date 2
         Date d2 = null;
		try {
			d2 = df.parse(currentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         String s="";
        
         if (d1.equals(d2))
         { 
        	 s = df.format(d1);
        	 
         }
         else if (d1.before(d2))
         { 
        	s = df.format(d1);
        	 
         }
         else
         {
        	  s = df.format(d2);
        	
         }
        	
      return s;

	}
	
	public String incrStartDate(String dt)
	{
		String month[]=dt.split("/");
		int monthInt=Integer.parseInt(month[1]);
		monthInt++;
		return month[0]+"/"+monthInt+"/"+month[2];
	}
	public String incrEndDate(String dt)
	{
		String month[]=dt.split("/");
		int monthInt=Integer.parseInt(month[1]);
		monthInt=monthInt+2;
		return month[0]+"/"+monthInt+"/"+month[2];
	}
	
	//date like 12-Oct-2010 conver to like 10/12/2010 date
	
	public String dateFormatInString(String dateStringwithHifen)
	{
		String dateWithSlash[]=dateStringwithHifen.split("-");
		String day=dateWithSlash[0];
		String monthInChar=dateWithSlash[1];
		String yearInChar=dateWithSlash[2];
		if(monthInChar.equals("Jan"))
			monthInChar="1";
		else if(monthInChar.equals("Feb"))
			monthInChar="2";
		else if(monthInChar.equals("Mar"))
			monthInChar="3";
		else if(monthInChar.equals("Apr"))
			monthInChar="4";
		else if(monthInChar.equals("May"))
			monthInChar="5";
		else if(monthInChar.equals("Jun"))
			monthInChar="6";
		else if(monthInChar.equals("Jul"))
			monthInChar="7";
		else if(monthInChar.equals("Aug"))
			monthInChar="8";
		else if(monthInChar.equals("Sep"))
			monthInChar="9";
		else if(monthInChar.equals("Oct"))
			monthInChar="10";
		else if(monthInChar.equals("Nov"))
			monthInChar="11";
		else if(monthInChar.equals("Dec"))
			monthInChar="12";
		return monthInChar+"/"+day+"/"+yearInChar;
	}
	public void executeAutoItScript() throws Exception
	{
		Runtime rt = Runtime.getRuntime();
		try 
		{
			rt.exec("D:\\Upload.exe");
		}
		catch (IOException ioe) 
		{

				System.out.println(ioe.getMessage());
				ioe.printStackTrace();
		}
    } 
//		}
	public String invoiceCal(String dt,String custTerm)throws ParseException
	{
		// String dt = "9/16/2011";  // Start date
		int custTermInt = Integer.parseInt(custTerm);
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	 Calendar c = Calendar.getInstance();
    	 c.setTime(sdf.parse(dt));
    	 c.add(Calendar.DATE, custTermInt);  // number of days to add
    	 dt = sdf.format(c.getTime()); 
    	 //System.out.println("Converted string to date : " + dt);
		return dt;
	}
	
	
	public String getDayFromDate(String strDate)throws Exception
	{
		
    	SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
    	Date date = sdf1.parse(strDate);
    	SimpleDateFormat sdf2 = new SimpleDateFormat("EEEEEEE");
    	System.out.println("Date is : " + sdf2.format(date));
    	return sdf2.format(date);
	}
	
	/*public String getDiffFromDays(String str1,String str2)
	{
		String diffDays="5";
		if(str1.equals(str2))
			diffDays="0";
		
		return diffDays;
	}*/
	
	public String addedCustomerInvoiceDays(String dt,String day)throws Exception
	{
		// String dt="09/25/2011";
		 int dayInt=Integer.parseInt(day);
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(sdf.parse(dt));
    	   	
    	 //Calendar cal = new GregorianCalendar(2003, Calendar.JANUARY, 1);
    	// int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);   
    	 while(!(cal.get(Calendar.DAY_OF_WEEK)==(dayInt)))
    	 {
    		 cal.add(Calendar.DATE, 1);

    	 }
    	// cal.add(Calendar.DATE, i);
    	 dt = sdf.format(cal.getTime()); 
		return dt;
	}
	
	public String getDueDateForNextDateOfMonth(String dt,String invDays)throws Exception
	{
		 //String dt="09/22/2011";
		int invDaysInt=Integer.parseInt(invDays);
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(sdf.parse(dt));
    	
    	 if(cal.get(Calendar.DATE) > invDaysInt )
    	    
    		 cal.add(Calendar.MONTH,1);
    	
    	 System.out.println(Calendar.MONTH);
    	 dt = sdf.format(cal.getTime()); 
    	 System.out.println(dt);
         cal.set(Calendar.DATE,invDaysInt);
         dt = sdf.format(cal.getTime()); 
     	 System.out.println(dt);
		return dt;
	}
	public String getStringInDateFormat(String dt)throws Exception
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	 // date = (Date)formatter.parse(str_date);
    	 Calendar c = Calendar.getInstance();
    	 c.setTime(sdf.parse(dt));
    	 dt = sdf.format(c.getTime()); 
    	 return dt;
	}
	
	public String getGPCustNmbr(String gpCustomerNo) throws IOException 
	   {
			File inputWorkbook = new File("zcGPPayTerms.xls");
			Workbook w;
		    String dUEDTDS="";
			try 
			{
				//String gpCustomerNo="FUJITSU";
				w = Workbook.getWorkbook(inputWorkbook);
				
				
				// Get the first sheet
				Sheet sheet = w.getSheet(0);
				// Loop over first 10 column and lines
				//System.out.println("No of columns : "+sheet.getColumns());
				//System.out.println("No of columns : "+sheet.getRows());
				//System.out.println("No of columns : "+sheet.getName());	
				for (int j = 0; j < sheet.getColumns(); j++) {
					for (int i = 0; i < sheet.getRows(); i++) {
						Cell cell = sheet.getCell(j, i);
						//CellType type = cell.getType();
						if (cell.getType() == CellType.LABEL) 
						{
							
							//System.out.println("I got a label "+ cell.getContents());
							String contentOfExcel=cell.getContents().trim();
							//System.out.println("contentOfExcel :"+contentOfExcel);
							//System.out.println("gpCustomerNo :"+gpCustomerNo);
							if(contentOfExcel.equals(gpCustomerNo))
							{
								cell = sheet.getCell(j+1, i);
								//System.out.println("DUEDTDS "+ cell.getContents());
								dUEDTDS=cell.getContents();
							}
						}

						/*if (cell.getType() == CellType.NUMBER) {
							System.out.println("I got a number "
									+ cell.getContents());
						}*/

					}//inner for loop
				}//outer loop
			}//try block
			catch (BiffException e)
			{
				e.printStackTrace();
			}
			return dUEDTDS;
		
	  }
	
	
	public String dateFormatInStringWithZero(String dateStringwithHifen)
	{
		String dateWithSlash[]=dateStringwithHifen.split("-");
		String day=dateWithSlash[0];
		String monthInChar=dateWithSlash[1];
		String yearInChar=dateWithSlash[2];
		if(monthInChar.equals("Jan"))
			monthInChar="01";
		else if(monthInChar.equals("Feb"))
			monthInChar="02";
		else if(monthInChar.equals("Mar"))
			monthInChar="03";
		else if(monthInChar.equals("Apr"))
			monthInChar="04";
		else if(monthInChar.equals("May"))
			monthInChar="05";
		else if(monthInChar.equals("Jun"))
			monthInChar="06";
		else if(monthInChar.equals("Jul"))
			monthInChar="07";
		else if(monthInChar.equals("Aug"))
			monthInChar="08";
		else if(monthInChar.equals("Sep"))
			monthInChar="09";
		else if(monthInChar.equals("Oct"))
			monthInChar="10";
		else if(monthInChar.equals("Nov"))
			monthInChar="11";
		else if(monthInChar.equals("Dec"))
			monthInChar="12";
		return monthInChar+"/"+day+"/"+yearInChar;
	}//end of dateFormatInStringWithZero method
	
	public String dayOfMonth(String str)
	{
		 Calendar calendar = Calendar.getInstance();
		  String dateArr[]=str.split("/");
		  int year = Integer.parseInt(dateArr[2]);
		  int month =Integer.parseInt(dateArr[0])-1;
		  int day = Integer.parseInt(dateArr[1]);

		  calendar.set(year, month, day);

		  int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		  System.out.println("Max Day: " + maxDay);
		  String dateOfMonth=Integer.parseInt(dateArr[0])+"/"+maxDay+"/"+year;
		 return dateOfMonth;
	}//end of dayOfMonth
	public String dateOfNext2months(String str)throws Exception
	{
		
   	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   	 Calendar cal = Calendar.getInstance();
   	 cal.setTime(sdf.parse(str));
   	 cal.add(Calendar.MONTH,2);
   	 str = sdf.format(cal.getTime()); 
   	 return str;
	}//end of dateOfNext2months method
	public String endOfNextMonth(String str)throws Exception
	{
	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  	 Calendar cal = Calendar.getInstance();
  	 cal.setTime(sdf.parse(str));
  	 cal.add(Calendar.MONTH,1);
  	//cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
  	 
  	 str = sdf.format(cal.getTime()); 
  	 return str;
	}
	
	public String currentMonthEnd(String str)throws Exception
	{
	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  	 Calendar cal = Calendar.getInstance();
  	 cal.setTime(sdf.parse(str));
  	 cal.add(Calendar.MONTH,0);
  	//cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
  	 
  	 str = sdf.format(cal.getTime()); 
  	 return str;
	}
	
   
	public static void main(String a[]) throws Exception
	{
		
		//String user=t.getRandom(6);
		//System.out.println(t.addedCustomerInvoiceDays("09/22/2011","1"));
		//System.out.println("Testing : "+t.dateFormatInString("29-Feb-2012"));
		System.out.println("Testing : "+RandomUserName.getRandom(8));
		//System.out.println(t.getDueDateForNextDateOfMonth("09/25/2011","10"));getRandom
	}
}