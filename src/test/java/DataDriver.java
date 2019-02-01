import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriver {

	@DataProvider(name = "data")
	public  Object[][] dataSupplier() throws IOException {

		Object[][] data = null;
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		try {

			fis = new FileInputStream(".\\src\\test\\resources\\" + "TestData.xlsx");
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");

			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();
			Object[][] obj = new Object[lastRowNum][1];

			for (int i = 0; i < lastRowNum; i++) {
				try {
					Map<Object, Object> datamap = new HashMap<>();
					for (int j = 0; j < lastCellNum; j++) {

						try {

							datamap.put(sheet.getRow(0).getCell(j).toString(),
									sheet.getRow(i + 1).getCell(j).toString());
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					obj[i][0] = datamap;
					return obj;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
		return data;

	}
	 
	
	
	@Test(dataProvider = "data")
	  public void integrationTest(Map<Object, Object> map) {
	    System.out.println("-------------Test case started ----------------");
	    System.out.println(map.get("Uname"));
	    System.out.println(map.get("Password"));
	    System.out.println(map.get("DOB"));

	    System.out.println("-------------Test case Ended ----------------");
System.out.println();
	  }
	
}
