package modules;

import util.Set_Read_ProperpertiesFile;
import util.TestNgHelper;

public class Home extends TestNgHelper{
	
	Set_Read_ProperpertiesFile sRPF;
	public static String addedClaimNo = "";

	//Constructor Of the Class
	public Home()throws Exception
	{
		sRPF = new Set_Read_ProperpertiesFile();
	}
}
