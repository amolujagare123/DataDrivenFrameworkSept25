package DataProvider.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ForDataProvider {

    public static Object[][] getMyData(String filePath,String sheetName) throws IOException {
        // 1 . Read the file
        FileInputStream fis = new FileInputStream(filePath);

        // 2. create the workbook object
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 3. choose the sheet
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // 4. count the active rows
        int rowCount = sheet.getPhysicalNumberOfRows();

        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount-1][colCount];

        for (int i=0;i<rowCount-1;i++)
        {
            XSSFRow row = sheet.getRow(i+1);


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

        return data;
    }
}
