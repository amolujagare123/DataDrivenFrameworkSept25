package DataProvider;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginDemoXLX {

    @Test(dataProvider = "getData")
    public void loginTest1(String username,String password) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stock.scriptinglogic.in/");

        WebElement txtUsername = driver.findElement(By.xpath("//input[@id='login-username']"));
        txtUsername.sendKeys(username);

        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        txtPassword.sendKeys(password);

        WebElement btnLogin = driver.findElement(By.xpath("//input[@value='LOG IN']"));
        btnLogin.click();

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
        XSSFSheet sheet = workbook.getSheet("login");

        // 4. count the active rows
        int rowCount = sheet.getPhysicalNumberOfRows();

        Object[][] data = new Object[rowCount][2];

        for (int i=0;i<rowCount;i++)
        {
            XSSFRow row = sheet.getRow(i);

            data[i][0] = row.getCell(0).toString();
            data[i][1] = row.getCell(1).toString();

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
