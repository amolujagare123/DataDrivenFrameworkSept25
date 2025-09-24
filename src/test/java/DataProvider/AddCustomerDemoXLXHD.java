package DataProvider;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class AddCustomerDemoXLXHD {
    WebDriver driver;
    @BeforeClass
    public void doLogin() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stock.scriptinglogic.in/");

        WebElement txtUsername = driver.findElement(By.xpath("//input[@id='login-username']"));
        txtUsername.sendKeys("admin");

        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        txtPassword.sendKeys("admin");

        WebElement btnLogin = driver.findElement(By.xpath("//input[@value='LOG IN']"));
        btnLogin.click();

    }

    @Test(dataProvider = "getData")
    public void addCustomerTest(String name,String address
            ,String contact1,String contact2)
    {
        driver.findElement(By.partialLinkText("Add Cu")).click();

        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("address")).sendKeys(address);
        driver.findElement(By.name("contact1")).sendKeys(contact1);
        driver.findElement(By.name("contact2")).sendKeys(contact2);
      //  driver.findElement(By.name("Submit")).click();
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @DataProvider
    Object[][] getData() throws IOException {
        // 1 . Read the file
        FileInputStream fis = new FileInputStream("Data/Data2.xlsx");

        // 2. create the workbook object
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 3. choose the sheet
        XSSFSheet sheet = workbook.getSheet("add customer");

        // 4. count the active rows
        int rowCount = sheet.getPhysicalNumberOfRows();

        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount-1][colCount];

        for (int i=0;i<rowCount-1;i++)
        {
            XSSFRow row = sheet.getRow(i+1);

        /*    data[i][0] = row.getCell(0).toString();
            data[i][1] = row.getCell(1).toString();
            data[i][2] = row.getCell(2).toString();
            data[i][3] = row.getCell(3).toString();*/

            for(int j=0;j<colCount;j++) {

                XSSFCell cell = row.getCell(j);

                if(cell==null)
                    data[i][j] = "";
                else {
                    cell.setCellType(CellType.STRING);
                    data[i][j] = cell.toString();
                }

            }

        }



        /*data[0][0] = "admin";
        data[0][1] = "admin";

        data[1][0] = "invalid-1";
        data[1][1] = "invalid-1";

        data[2][0] = "invalid-2";
        data[2][1] = "invalid-2";

        data[3][0] = "invalid-3";
        data[3][1] = "invalid-3";*/


        return data;
    }

}
