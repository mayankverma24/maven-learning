package maventutorial.maven_learning;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDrivenTest {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\mayan\\Documents\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
			{
			XSSFSheet sheet=workbook.getSheetAt(i);
			Iterator<Row> rows= sheet.iterator();
			Row firstrow= rows.next();
			
			}
		}
	}
}
