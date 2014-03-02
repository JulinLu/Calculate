import java.io.File;  
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Date;
import java.util.List; 
 









import jxl.Cell; 
import jxl.CellType;
import jxl.Sheet; 
import jxl.Workbook;  
import jxl.read.biff.BiffException;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.JxlWriteException;

//http://www.andykhan.com/jexcelapi/tutorial.html
public class ExcelReaderByJxlApi {
	public static List<String[]> readExcel(File excelFile,int rowNum) throws BiffException,IOException {
		//Create a list to store the data read from excel file
		List<String[]> list = new ArrayList<String[]>(); 
		Workbook rwb = null; 
		Cell cell = null;
		
		//Read file from Input Stream
		InputStream stream = new FileInputStream(excelFile); 
		rwb = Workbook.getWorkbook(stream); 
			
		///Or you can  get the file directly from the file
		///Workbook workbook = Workbook.getWorkbook(new File("abc.xls")); 
		//rwb = Workbook.getWorkbook(excelFile); 
				
		//Get a given sheet, by default begin from the first sheet
		//Sheet sheet = rwb.getSheet("login");== Sheet sheet = rwb.getSheet(0);
		//Sheet sheet = rwb.getSheet("book");==Sheet sheet = rwb.getSheet(1);
		Sheet sheet = rwb.getSheet(0);
		//Row numbers: the first row is about the header, ignore it, from  the second row
		for (int i = rowNum; i < sheet.getRows(); i++) {
			//Create a array to store the value for each column
			String[] str = new String[sheet.getColumns()]; 
			//Columns
			for (int j = 0; j < sheet.getColumns(); j++) { 
				//Get the value of row(i), column(j)
				cell = sheet.getCell(j, i); 
				str[j] = cell.getContents(); 
			}
			//Save the column to list
			list.add(str);	
		}
		
		/*
		///Example to get a cell and its value getCell(column,row);
		Cell a1 = sheet.getCell(0,0); 
		Cell b2 = sheet.getCell(1,1); 
		Cell c2 = sheet.getCell(2,1); 

		String stringa1 = a1.getContents(); 
		String stringb2 = b2.getContents(); 
		String stringc2 = c2.getContents();
		
		System.out.println(stringa1);
		System.out.println(stringb2);
		System.out.println(stringc2);
		*/
	    
		///if it is required to access the cell's contents as the exact type ie. as a numerical value or as a date, then the retrieved Cell must be cast to the correct type and the appropriate methods called.
		/*
		String stringa1 = null; 
		double numberb2 = 0; 
		Date datec2 = null; 
		Cell a1 = sheet.getCell(0,0); 
		Cell b2 = sheet.getCell(1,1); 
		Cell c2 = sheet.getCell(2,1); 
		if (a1.getType() == CellType.LABEL) 
		{ 
		  LabelCell lc = (LabelCell) a1; 
		  stringa1 = lc.getString(); 
		} 
		if (b2.getType() == CellType.NUMBER) 
		{ 
		  NumberCell nc = (NumberCell) b2; 
		  numberb2 = nc.getValue(); 
		} 
		if (c2.getType() == CellType.DATE) 
		{ 
		  DateCell dc = (DateCell) c2; 
		  datec2 = dc.getDate(); 
		} 	
		System.out.println(stringa1);
		System.out.println(numberb2);
		System.out.println(datec2);
		*/
		rwb.close();
		return list;
	}
	
	public static void writeExcel(File excelFile) throws BiffException,IOException, JxlWriteException, WriteException {

		//This creates the workbook object. The generated file will be located in the current working directory and will be called "output.xls".
		//WritableWorkbook workbook = Workbook.createWorkbook(new File("output.xls"));
		WritableWorkbook workbook = Workbook.createWorkbook(excelFile);
		//The code fragment below creates a sheet called "First Sheet" at the first position.
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		
		//There are a couple of points to note here. Firstly, the cell's location in the sheet is specified as part of the constructor information. Once created, it is not possible to change a cell's location, although the cell's contents may be altered. 
		//The other point to note is that the cell's location is specified as (column, row). Both are zero indexed integer values - A1 being represented by (0,0), B1 by (1,0), A2 by (0,1) and so on.
		Label label = new Label(0, 2, "A label record"); 
		sheet.addCell(label); 

		Number number = new Number(3, 4, 3.1459); 
		sheet.addCell(number); 
		
		// Create a cell format for Arial 10 point font 
		WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10); 
		WritableCellFormat arial10format = new WritableCellFormat (arial10font); 

		// Create the label, specifying content and format 
		Label label2 = new Label(1,0, "Arial 10 point label", arial10format); 
		sheet.addCell(label2); 
		
		//Cell formats objects are shared, so many cells may use the same format object, e.g.:
		Label label3 = new Label(2, 0, "Another Arial 10 point label", arial10format); 
		sheet.addCell(label3);
		
		//Because cell formats are shared, it is not possible to change the contents of a cell format object. (If this were permitted, then changing the contents of the object could have unforeseen repurcussions on the look of the rest of the workbook). In order to change the way a particular cell is displayed, the API does allow you to assign a new format to an individual cell.
		// Create a cell format for Times 16, bold and italic 
		WritableFont times16font = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD, true); 
		WritableCellFormat times16format = new WritableCellFormat (times16font); 

		// Create the label, specifying content and format 
		Label label4 = new Label(3,0, "Times 16 bold italic label", times16format); 
		sheet.addCell(label4); 
		
		//Formatting Numbers 
		WritableCellFormat integerFormat = new WritableCellFormat (NumberFormats.INTEGER); 
		Number number2 = new Number(0, 4, 3.141519, integerFormat); 
		sheet.addCell(number2); 

		WritableCellFormat floatFormat = new WritableCellFormat (NumberFormats.FLOAT); 
		Number number3 = new Number(1, 4, 3.141519, floatFormat); 
		sheet.addCell(number3);
		
		//It's possible for a user to define their own number formats, by passing in a number format string.
		NumberFormat fivedps = new NumberFormat("#.#####"); 
		WritableCellFormat fivedpsFormat = new WritableCellFormat(fivedps); 
		Number number4 = new Number(2, 4, 3.141519, fivedpsFormat); 
		sheet.addCell(number4);
		
		//Formatting Dates 
		// Get the current date and time from the Calendar object 
		Date now = Calendar.getInstance().getTime(); 
		DateFormat customDateFormat = new DateFormat ("MM dd yyyy hh:mm:ss"); 
		WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat); 
		DateTime dateCell = new DateTime(0, 6, now, dateFormat); 
		
		DateFormat customDateFormat1 = new DateFormat ("MMM dd yyyy hh:mm:ss"); 
		WritableCellFormat dateFormat1 = new WritableCellFormat (customDateFormat1); 
		DateTime dateCell1 = new DateTime(0, 7, now, dateFormat1); 
		
		sheet.addCell(dateCell);
		sheet.addCell(dateCell1);
		
		workbook.write(); 
		workbook.close(); 
		
	}

	public static void copyWriteExcel(File originalFile, File finalFile) throws BiffException,IOException, JxlWriteException, WriteException {

		//This creates a readable spreadsheet. To obtain a writable version of this spreadsheet, a copy must be made, as follows:
		//Workbook workbook = Workbook.getWorkbook(new File("myfile.xls"));
		Workbook workbook = Workbook.getWorkbook(originalFile);
		
		WritableWorkbook copy = Workbook.createWorkbook(finalFile, workbook);
		
		//Once we have a writable interface to the workbook, we may retrieve and modify cells. The following code fragment illustrates how to modify the contents of a label cell located in cell B3 in sheet 2 of the workbook
		WritableSheet sheet2 = copy.getSheet(1); 
		WritableCell cell = sheet2.getWritableCell(1, 2); 

		if (cell.getType() == CellType.LABEL) 
		{ 
		  Label l = (Label) cell; 
		  l.setString("modified cell"); 
		}
		
		//There is no need to call the add() method on the sheet, since the cell is already present on the sheet. The contents of numerical and date cells may be modified in a similar way, by using the setValue() and setDate() methods respectively.
		WritableCell cell1 = sheet2.getWritableCell(2, 4); 

		NumberFormat fivedps = new NumberFormat("#.#####"); 
		WritableCellFormat cellFormat = new WritableCellFormat(fivedps); 
		cell1.setCellFormat(cellFormat); 
		
		//Since the copy of the workbook is an ordinary writable workbook, new cells may be added to the sheet, thus:
		Label label = new Label(0, 2, "New label record"); 
		sheet2.addCell(label); 
		Number number = new Number(3, 4, 3.1459); 
		sheet2.addCell(number); 
		
		// All cells modified/added. Now write out the workbook 
		copy.write(); 
		copy.close();
		
	}
	
	public static void main(String[] args) {
		//String excelFileName = "C:\\Users\\Winfred\\workspace\\Calculator\\src\\abc.xls"; 
		System.out.println(System.getProperty("user.dir"));
		//String excelFileName = "abc.xls"; //If ues relative path, the file should be saved to "user.dir", normally it's the root path of project

		String dataDir = System.getProperty("user.dir") + "\\data";
		String excelInput = dataDir + "\\abc.xls";
		String excelOutput = dataDir + "\\output.xls";
		String destFile = dataDir + "\\copyFromabc.xls";
		
		
		try {
			List<String[]> list = ExcelReaderByJxlApi.readExcel(new	File(excelInput),1);
			for (int i = 0; i < list.size(); i++) {
				String[] str = (String[])list.get(i); 
				for (int j = 0; j < str.length; j++) {
					System.out.println(str[j]); 
				}
			}
		}catch (BiffException e){
				e.printStackTrace(); 
		}catch (IOException e) {
				e.printStackTrace();
		}
		
		try {
			ExcelReaderByJxlApi.writeExcel(new File(excelOutput));
		}catch (BiffException e){
				e.printStackTrace(); 
		}catch (IOException e) {
				e.printStackTrace();
		}catch (JxlWriteException e) {
			e.printStackTrace();
		}catch (WriteException e) {
			e.printStackTrace();
		}
		
		try {
			ExcelReaderByJxlApi.copyWriteExcel(new File(excelInput), new File(destFile));
		}catch (BiffException e){
			e.printStackTrace(); 
		}catch (IOException e) {
				e.printStackTrace();
		}catch (JxlWriteException e) {
			e.printStackTrace();
		}catch (WriteException e) {
			e.printStackTrace();
		}
	}

}
