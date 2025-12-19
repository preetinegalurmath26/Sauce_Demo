package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static Map<String, String> getUserAdressDataFromExcelSheet(String sheetName) {
		Map<String, String> testData = new HashMap();
		try {
			String userDir = System.getProperty("user.dir");
			System.out.println(userDir);
			FileInputStream fis = new FileInputStream(userDir + "/TestData/SauceDemo(User_Adress).xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				if (row == null)
					continue;

				if (row.getCell(0) == null)
					continue;

				String key = row.getCell(0).getStringCellValue();
				String value = "";

				// handling null cell
				if (row.getCell(1) != null) {
					switch (row.getCell(1).getCellType()) {
					case STRING:
						value = row.getCell(1).getStringCellValue();
						break;
					case NUMERIC:
						value = String.valueOf((long) row.getCell(1).getNumericCellValue());
						break;
					default:
						value = "";
					}
				}

				System.out.println(key + " -> " + value);
				testData.put(key, value);
			}

			workbook.close();
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return testData;

//			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//				XSSFRow row = sheet.getRow(i);
//				String key = row.getCell(0).getStringCellValue();
//				String value = row.getCell(1).getStringCellValue();
//				System.out.println(key + "->" + value);
//				testData.put(key, value);
//				if (key.equals("First_Name")) 
//					testData.put(key, value);
//				 else if (key.equals("Last_Name"))
//					testData.put(key, value);
//				else if (key.equals("Postal_Code"))
//					testData.put(key, value);
//			}
//
//		} catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		return testData;
	}

	public static void main(String[] a) {
		getUserAdressDataFromExcelSheet("Sheet1");
	}
}
