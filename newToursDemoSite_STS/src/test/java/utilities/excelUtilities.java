package utilities;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtilities 
{
	static XSSFWorkbook wbOBJ;
	static XSSFSheet wsOBJ;
	static XSSFCell cell;
	
	public excelUtilities(String excelPath, String sheetName)
	{
		
		try {
				wbOBJ = new XSSFWorkbook(excelPath);
				wsOBJ = wbOBJ.getSheet(sheetName);
			} 
		catch (IOException e) 
			{
				e.printStackTrace();
			}		
	}
	
	public static int GetRowCount()
	{
		int RowCount_Value = 0;
		RowCount_Value = wsOBJ.getPhysicalNumberOfRows();
		//System.out.println("Row Count Is: "+RowCount_Value);
		return RowCount_Value;
		
	}
	
	public static int GetColumnCount()
	{
		int ColumnCount_Value = 0;
		ColumnCount_Value = wsOBJ.getRow(0).getLastCellNum();
		//System.out.println("Row Count Is: "+ColumnCount_Value);
		return ColumnCount_Value;
	}
	
	public static String GetCellData(int rowNum, int colNum)
	{
		//int ColumnCountValue = wsOBJ.getRow(rowNum).getPhysicalNumberOfCells();
		//System.out.println("Column Count Is: "+ColumnCountValue);
		
		String CellData_Value;
		CellData_Value = wsOBJ.getRow(rowNum).getCell(colNum).toString();
		//System.out.println("Column Value Is: "+CellData_Value);
		return CellData_Value;
	}
	
	
}
