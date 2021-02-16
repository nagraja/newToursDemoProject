package utilities;

public class excelUtilitiesDemo 
{
	public static void main(String[] args) 
	{
		String ProjectRelativePath_Value = System.getProperty("user.dir");
		excelUtilities excelOBJ = new excelUtilities(ProjectRelativePath_Value+"/excel/Login_FunctionalTest_DF.xlsx", "Sheet1");
		
		excelOBJ.GetRowCount();
		excelOBJ.GetCellData(1, 1);
	}
}
