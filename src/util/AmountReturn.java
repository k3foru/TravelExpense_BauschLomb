package util;

public class AmountReturn {
	
	public static double returnAmount(String str)
	{
		//String str="$ 27.5085"
		//System.out.println(str);
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
	public  static void main(String args[])
	{
		System.out.println(AmountReturn.returnAmount("151.00 USD"));
	}

}
