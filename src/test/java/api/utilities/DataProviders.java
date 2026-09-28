package api.utilities;


import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	/*depending on the requirement we create multiple data providers, here in case of User module all methods used any of the below 2 methods
	these data provideres  get the data from excel sheets, using DP we can avoid for loops, "35th min in video"
	*/
	
	
	
    // DataProvider 1: Fetches ALL user details row-by-row for Post/Create User operations
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        // Path to your Excel file located under test resources
        String path = System.getProperty("user.dir") + "//TestData//UserData.xlsx";
        
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        // 2D array to hold sheet data (excluding header row)
        String[][] apidata = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        
        return apidata;
    }

    // DataProvider 2: Fetches ONLY UserNames (Column Index 1) for Get/Delete User operations
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//TestData//UserData.xlsx";
        
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");

        // 1D array to hold just the usernames
        String[] apidata = new String[rownum];

        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1); // Column 1 contains UserNames
        }

        return apidata;
    }
}