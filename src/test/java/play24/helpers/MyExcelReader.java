
package play24.helpers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyExcelReader {

    public static Object[][] myExcelReader (String filePath) throws IOException {

        File file = new File(filePath);   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object

        int rowNumbers = sheet.getLastRowNum()+2;
        int lastCol = 0;
        for(int i=0;i<rowNumbers;i++){
           if (sheet.getRow(i)!=null){
               if (sheet.getRow(i).getLastCellNum()>lastCol){
                   lastCol = sheet.getRow(i).getLastCellNum();
               }
           }
        }

        int colNumbers = lastCol+1;



        Object[][] data = new Object[colNumbers][rowNumbers];
        int x;
        int y;

        for (y=1;y<rowNumbers;y++){
            if (sheet.getRow(y-1)!=null)
            {
                for (x=1;x<colNumbers;x++)
                {

                    if (sheet.getRow(y-1).getCell(x-1)==null)
                    {
                       // data[x][y]=null;
                    } else if (sheet.getRow(y-1).getCell(x-1).getCellType().equals(CellType.NUMERIC))
                    {
                        data[x][y]=sheet.getRow(y-1).getCell(x-1).getNumericCellValue();
                    }  else if(sheet.getRow(y-1).getCell(x-1).getCellType().equals(CellType.FORMULA))
                    {
                        data[x][y]=sheet.getRow(y-1).getCell(x-1).getNumericCellValue();
                    }  else {
                        data[x][y]=sheet.getRow(y-1).getCell(x-1).getStringCellValue();
                    }
                }
            } else
                {
                for (x=1;x<colNumbers;x++)
                {
                   // data[x][y]=null;
                }

                }
        }



        //System.out.println(data[12][8]);
                return data;
    }


    public static Object[][] myExcelReaderForDataProvider (String filePath, boolean skipFirstRow) throws IOException {

        File file = new File(filePath);   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object

        int offset;
        if (skipFirstRow==true){
            offset=1;
        } else {
            offset=0;
        }

        int rowNumbers = sheet.getLastRowNum()+1-offset;
        int lastCol = 0;
        for(int i=0;i<rowNumbers;i++){
            if (sheet.getRow(i)!=null){
                if (sheet.getRow(i).getLastCellNum()>lastCol){
                    lastCol = sheet.getRow(i).getLastCellNum();
                }
            }
        }

        int colNumbers = lastCol;



        Object[][] data = new Object[rowNumbers][colNumbers];
        int x;
        int y;


        for (y=0;y<rowNumbers;y++){
            if (sheet.getRow(y+offset)!=null)
            {
                for (x=0;x<colNumbers;x++)
                {

                    if (sheet.getRow(y+offset).getCell(x)==null)
                    {
                        // data[x][y]=null;
                    } else if (sheet.getRow(y+offset).getCell(x).getCellType().equals(CellType.NUMERIC))
                    {
                        data[y][x]=sheet.getRow(y+offset).getCell(x).getNumericCellValue();
                    }  else if(sheet.getRow(y+offset).getCell(x).getCellType().equals(CellType.FORMULA))
                    {
                        data[y][x]=sheet.getRow(y+offset).getCell(x).getNumericCellValue();
                    }  else {
                        data[y][x]=sheet.getRow(y+offset).getCell(x).getStringCellValue();
                    }
                }
            } else
            {
                for (x=0;x<colNumbers;x++)
                {
                    // data[x][y]=null;
                }

            }
        }



        //System.out.println(data[12][8]);
        return data;
    }
}


