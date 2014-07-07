package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GetProjectId {
	
	int increament=0;
	String charString="";
	Character chr='p';
	String charString1="";
	public String projectDefault(String str)
	{
		for(int i=0;i<=str.length()-1;i++)
		{
			chr=new Character(str.charAt(increament));
			charString=chr.toString();
			if(!chr.equals('*'))
			{	
				charString1 =charString1+charString;
			}
			increament++;
		}
		return charString1;
	}
	
   public String amountWithout$(String str)
   {
	   int increament=0;
		String charString="";
		Character chr='p';
		String charString1="";
      // String str = "$0.50";
    

        for(int i=0;i<=str.length()-1;i++)
		{
			chr=new Character(str.charAt(increament));
			charString=chr.toString();
			if(!chr.equals('$'))
			{	
				charString1 =charString1+charString;
			}
			increament++;
		}
        return charString1;
   }
   public String amountWithoutComma(String str)
   {
	   int increament=0;
		String charString="";
		Character chr='p';
		String charString1="";
      // String str = "$0.50";
    

        for(int i=0;i<=str.length()-1;i++)
		{
			chr=new Character(str.charAt(increament));
			charString=chr.toString();
			if(!chr.equals(','))
			{	
				charString1 =charString1+charString;
			}
			increament++;
		}
        return charString1;
   }
   
   public static String projecIdOfDefaultProj(String str)
   {
	   Character chr='p';
	   String charString="";
	   String projectID="";
	   
	   int dec=str.length();
	   String stopWhile=""; 
	   while(!stopWhile.equals("stop"))
	   {
		   chr=new Character(str.charAt(dec-2));
		   charString=chr.toString();
		  // System.out.println("-------------"+charString);
		
		   if(charString.equals("("))
		   {
		      stopWhile="stop";
		     
		   }
		   else
		   {
			  dec--;
			  projectID =charString+projectID;
		   }
		   
	   }
	   
	return projectID;
	   
   }//end of projecIdOfDefaultProj method
   
   public static String projectNameWithOutID(String str)
   {
	   int i=0;
	   Character chr='p';
	   String charString="";
	   String projectName = "";

	   String stopWhile=""; 
	   while(!stopWhile.equals("stop"))
	   {
		   chr=new Character(str.charAt(i));
		   charString=chr.toString();
		  // System.out.println("-------------"+charString);
		
		   if(charString.equals("("))
		   {
		      stopWhile="stop";
		      //charString1.substring(1, charString1.lastIndexOf('('));
		      projectName=projectName.trim();
		   }
		   else
		   {
			 i++;
			 projectName =projectName+charString;
		   }
		   
	   }
	   
	return projectName;
	
   }
   
   
   public String getURLName(String str)
   {
	   Character chr='p';
	   String charString="";
	   
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
   
   public String getStringAfterColon(String str)
   {
	   Character chr='p';
	   String charString="";
	   
	   int dec=str.length();
	   String stopWhile=""; 
	   while(!stopWhile.equals("stop"))
	   {
		   chr=new Character(str.charAt(dec-1));
		   charString=chr.toString();
		  // System.out.println("-------------"+charString);
		
		   if(charString.equals(":"))
		   {
		      stopWhile="stop";
		   }
		   else
		   {
			  dec--;
		      charString1 =charString+charString1;
		   }
	   }
	  String stringWithOutSpace=charString1.trim();
	  return stringWithOutSpace; 
   }
   public String getInvoiceNumber(String str1)
   {
	  String str="ZCST00000000";
	  String a=str.substring(4);
	  int b=(a.length())-str1.length();
	  for(int i=1;i<=b;i++)
	  {
		  str1="0"+str1;
	  }
	 
	  
	  
	  return str1; 
   }
   public String getBU_Name(String str)
   {

	   int increament=0;
		String charString="";
		Character chr='.';
		String charString1="";
		
			for(int i=0;i<=str.length()-1;i++)
			{
				chr=new Character(str.charAt(increament));
				charString=chr.toString();
				if(!(chr.equals('.') || chr.equals('(')))
				{	
					charString1 =charString1+charString;
				}
				if(chr.equals('('))
				{
					i=str.length();
				}
				increament++;
			}
			String bUName=charString1.trim();
			return bUName;
   }
   
    public static String title(String string)
    {  
    	   boolean reset=false;
		   String result = "";  
		   for (int i = 0; i < string.length(); i++){  
		    String next = string.substring(i, i + 1);  
		    if (i == 0 || reset)
		    {  
		     result += next.toUpperCase();  
		     reset=false;
		    }
		    else 
		    {  
		     result += next.toLowerCase();  
		    } 
		    if(next.equals(" "))
		    {
		    	reset=true;
		    }
		   }  
	    return result;  
	}
   
    public static String splitString(String str){
    	String getName []= str.split(" ");
    	String name = getName[1]+", "+getName[0];
    	return name;
    }
    
    public static String getTodaysDateInDateFormate() {
   	 
    	Calendar currentDate = Calendar.getInstance();
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
    }
    
    public static String getTodaysDate() {
    	 
    	Calendar currentDate = Calendar.getInstance();
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
    }
    
    public static String getDaysBeforeTodaysDateInDateFormate(String days) {
      	 
    	Calendar currentDate = Calendar.getInstance();
    	int daysBefore = Integer.parseInt(days);
    	currentDate.add(Calendar.DATE, -daysBefore);
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
    }
    
    public static String getDaysBeforeTodaysDate(String days) {
   	 
    	Calendar currentDate = Calendar.getInstance();
    	int daysBefore = Integer.parseInt(days);
    	currentDate.add(Calendar.DATE, -daysBefore);
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
    }
    
    public static Date convertStringInDateFormate(String dateString) throws ParseException {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    	Date date = (Date)formatter.parse(dateString);
		return date;
    }
    
    @SuppressWarnings("deprecation")
	public static String getDaysBeforeDate(String dateString, int before) throws ParseException {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    	Date date = (Date)formatter.parse(dateString);
    	date.setHours(-before);
    	String daysBeforeDate = formatter.format(date);
		return daysBeforeDate;
    }
    
    public static double returnAmount(String str)
	{
		int len=str.length();
		String willPayAmt="";
		int i=1;
	
		for( i=len-1;i>=0;i--)
		{
			
			Character ch=str.charAt(i);
			if(ch.equals('-')||ch.equals('0')||ch.equals('1')||ch.equals('2')||ch.equals('3')||ch.equals('4')||ch.equals('5')||ch.equals('6')||ch.equals('7')||ch.equals('8')||ch.equals('9')||ch.equals('.'))
			{
							
					willPayAmt=ch.toString()+ willPayAmt;
			}
		}
		double willPayAmtDouble = Double.parseDouble(willPayAmt);
		return willPayAmtDouble;
    }
    
    public static String get2DigitDecimal(double no){
    	DecimalFormat df = new DecimalFormat("000.00");
		String decimalNo = df.format(no);
		return decimalNo;
    }
    
    public static String convertNumInCurrencyFormate(double num){
    	Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String numberFormate = format.format(new BigDecimal(num));
		return numberFormate = numberFormate.replaceAll("Rs.", "");
    }
    
    public static String convertStringInCurrencyFormate(String num){
    	Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String numberFormate = format.format(new BigDecimal(num));
		return numberFormate = numberFormate.replaceAll("Rs.", "");
    }
    
    public static int test(int time){
    	int setTime=0;
    	for(int i=1; i<10; i++){
    		int j=i+1;
    		if(time<=i*6){
    			setTime = i*6;
    			break;
    		}
    		else if(time<=j*6){
    			setTime = j*6;
    			break;
    		}
    	}
    	int time1= setTime - time;
    	int time2 = time - (setTime-6);
    	if(time1 < time2)
    		return setTime;
    	else
    		return setTime-6;
    }
    
    public static void main(String a[]) throws ParseException
    {
    	new GetProjectId();
		System.out.println(GetProjectId.convertStringInDateFormate("17/05/2012"));
    }
}
