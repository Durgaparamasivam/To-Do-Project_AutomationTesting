package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	public String filepath;
	File file;
	FileInputStream fip;
	static Workbook workbook;
	static Sheet sheet;
	Row row;

	public ExcelReader(String filepath) throws EncryptedDocumentException, InvalidFormatException, IOException {
		this.filepath = filepath;
		file = new File(filepath);
		fip = new FileInputStream(file);
		workbook = WorkbookFactory.create(fip);
	}

	public static Sheet getSheet(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	public static Row getRow(int rownum) {
		Row row = sheet.getRow(rownum);
		return row;
	}

	public String getCellDate(String sheetName, int rownum, int columnNum) {
		String value = null;
		
		getSheet(sheetName);
		row =  getRow(rownum);
		if(row.getCell(columnNum).CELL_TYPE_NUMERIC == row.getCell(columnNum).getCellType())
		{
			value =String.valueOf(row.getCell(columnNum).getNumericCellValue());
		}
		
		else if(row.getCell(columnNum).CELL_TYPE_STRING == row.getCell(columnNum).getCellType())
		{
			value = row.getCell(columnNum).getStringCellValue();	
		}
		return value;	
	}
}
