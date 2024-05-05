package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		Object arr[][]=null;

		try {
			FileInputStream fis = new FileInputStream(Constants.TEST_DATA_SHEET);
			try {
				book = WorkbookFactory.create(fis);
				sheet = book.getSheet(sheetName);
				arr = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
						arr[i][j] = sheet.getRow(i + 1).getCell(j).toString();
					}
				}

			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

}
