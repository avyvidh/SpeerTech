package testdata;

import java.sql.Date;

import org.testng.annotations.DataProvider;

public class TestData {
	static long millis = System.currentTimeMillis();
	static String time=String.valueOf(millis);
	static String Name="Name"+time;
	
	 @DataProvider(name="Name")
     public static Object[][] getDataFromDataprovider(){
          Object[][] data={
             { Name }
            
         };
          return data;
	 }
	 

}
