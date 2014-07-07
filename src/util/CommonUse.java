package util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** @author Abhishek Singh
 * 
 * */

public class CommonUse {
	
	public static String year_t;
	public static String month_t = "";
	public static String day_t;
	
	public static void separateDateFormate(String dateString) throws ParseException{
		//Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = (Date)formatter.parse(dateString);
		DateFormat year = new SimpleDateFormat("yyyy");
		DateFormat month = new SimpleDateFormat("M");
		DateFormat day = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		year_t = year.format(date);
		month_t = month.format(date); int month_N = Integer.parseInt(month_t); month_N = month_N-1; month_t = Integer.toString(month_N);
		day_t = day.format(date);
		//System.out.println("The day is: " + day_t);
	}
	
	// create directory if not exist and delete if exist.
	public static boolean createAndDeleteDirectory(File file){
	    // Delete Directory if already exists
	    if (file.exists()) {
	    	deleteDirectory(file);
	    	System.out.println("Successfull Deleted Directory "+file.getPath());
	    }
	    boolean status = file.mkdirs();
	    if (status) {
	        System.out.println("Successfull Creating Directory " + file.getPath());
	    }
	    return status;
	}
	
	// Delete the Existing Directory.
	public static boolean deleteDirectory(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            File delFile = new File(dir, children[i]);
	            if (!delFile.exists()) {
	                System.out.println("Cannot find directory to delete" + delFile.getPath());
	                return false;
	            }
	            boolean success = deleteDirectory(delFile);
	            if (!success) {
	                System.out.println("failure during delete directory" + delFile.getPath());
	                return false;
	            }
	        }
	        // The directory is now empty so now it can be smoked
	    }	        return dir.delete();
	}
	
	// to get Last file name of current URl
	public static String getURLName(String str)
	{
	   Character chr='p';
	   String charString="";
	   String charString1="";
	   
	   int dec=str.length();
	   String stopWhile=""; 
	   while(!stopWhile.equals("stop"))
	   {
		   chr=new Character(str.charAt(dec-6));
		   charString=chr.toString();
		  // System.out.println("-------------"+charString);
	
		   if(charString.equals("/"))
		   {
		      stopWhile="stop";
				     
		   }
		   else
		   {
			   dec--;
			   charString1 =charString+charString1;
		   }
	   }
		   return charString1; 
	}
	
	public static String getTodaysDate() {
 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	// Get Todays Date in '9/27/2012' Format
	public static String getTodaysDateFormate() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public static String getTodaysDateTime() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy HH:mm");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public static String getTodaysDateDayMonth() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public static String getOneDayBeforeTodaysDate() {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
  
	public static String getBeforeTodaysDate() {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, -2);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public static String getAfterTodaysDate() {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, 2);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public static String getSubStringFoRequestNo(String str){
		int total = str.length();
		str = str.substring(total-4);
		return str;
	}
	
	public static void main(String a[]) throws ParseException{
		//CommonUse.separateDateFormate(CommonUse.getAfterTodaysDate());
		System.out.println(CommonUse.getTodaysDateTime());
	}
}
