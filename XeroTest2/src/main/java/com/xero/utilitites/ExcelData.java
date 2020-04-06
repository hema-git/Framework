package com.xero.utilitites;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	XSSFWorkbook wb;
	
	public ExcelData() {
		
		//File src =new File("C:\\hema\\Selenium\\MavenProject\\Xero\\XeroTest2\\src\\main\\java\\com\\xero\\testdata\\XeroLogin.xlsx");
		File src=new File("C:\\HemaGit\\GitRepos\\XeroFramework\\Framework\\XeroTest2\\src\\main\\java\\com\\xero\\testdata\\XeroLogin.xlsx");
		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("unable to read excel file "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public String getUserName(String sheetName,int rownum, int colnum) {
		return wb.getSheet(sheetName).getRow(rownum).getCell(colnum).getStringCellValue();
	}
	
	public String getPassword(String sheetName,int rownum, int colnum) {
		
		return wb.getSheet(sheetName).getRow(rownum).getCell(colnum).getStringCellValue();
		
	}

}
