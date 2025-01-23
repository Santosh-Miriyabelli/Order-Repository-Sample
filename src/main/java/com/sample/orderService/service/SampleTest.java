package com.sample.orderService.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sample.orderService.entity.OrderDetails;

public class SampleTest {

	public static void main3(String[] args) {
		 double a =1.1+0.1;
	        System.out.println(a);
	        
	        //1.2000000000000002
	}
	
	//To create an Excel for money tracking
		public static void main(String[] args) { 
			System.out.println("Start:");
			// Creating a new workbook 
			int columnval=2;//12
			int rowVal=1;//23
			Workbook workbook = new XSSFWorkbook(); 
			//workbook.createName("SampleName");
			// Creating a new sheet 
			Sheet sheet = workbook.createSheet("Data"); 
			// Creating header row 
			Row row = sheet.createRow(0); 
			 
			
			/*for(int i=0;i<12;i++) {
				rowVal=1;
				for(int j=0;j<35;j++) {
					Row rowJ = sheet.getRow(j);
					if(rowJ==null) {
						rowJ=sheet.createRow(j); 
					}
					Cell c =rowJ.createCell(i);
					String commnad ="=IF(ISBLANK(INDIRECT(ADDRESS(ROW(),COLUMN()-"+columnval+"))), \"\", SUMIF(INDIRECT(F2+1&\"_\"&INDIRECT(ADDRESS(ROW()-"+rowVal+",COLUMN()))&\"!E:E\"), INDIRECT(ADDRESS(ROW(),COLUMN()-"+columnval+")), INDIRECT(F2+1&\"_\"&INDIRECT(ADDRESS(ROW()-"+rowVal+",COLUMN()))&\"!C:C\")))";
					
					c.setCellValue(commnad);
					//System.out.println(commnad);
					rowVal++;
					
				}
				columnval++;
			}*/
			
			/*int cons=28;
			for(int i=0;i<40;i++) {
				
				Cell c =row.createCell(i);
				String commnad ="=INDIRECT(\"Summary!B\" & ROW() - (ROW()-"+(cons+i)+"))";
				
				c.setCellValue(commnad);
			}*/
			
			int aa=0;
			int bb=0;
			
			//String sa ="=IF(ISBLANK(INDIRECT(ADDRESS(ROW()-"+aa+",COLUMN()))), \"\", SUMIF(INDIRECT(F2&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-"+bb+"))&\"!E:E\"), INDIRECT(ADDRESS(ROW()-"+aa+",COLUMN())), INDIRECT(F2&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-"+bb+"))&\"!C:C\")))";
			
			for(int i=1;i<40;i++) {
				rowVal=1;
				for(int j=1;j<13;j++) {
					Row rowJ = sheet.getRow(j);
					if(rowJ==null) {
						rowJ=sheet.createRow(j); 
					}
					Cell c =rowJ.createCell(i);
					aa=j;
					bb=i;
					String plusOne="";
					if(j>9) {
						plusOne="+1";
					}
					
					//String hm ="=IF(ISBLANK(INDIRECT(ADDRESS(ROW()-1,COLUMN()))), \"\", IF(ISERR(INDIRECT(F2&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-1))&\"!A1\")), \"\",SUMIF(INDIRECT(F2&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-1))&\"!E:E\"), INDIRECT(ADDRESS(ROW()-1,COLUMN())), INDIRECT(F2&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-1))&\"!C:C\"))))";
					String sa ="=IF(ISBLANK(INDIRECT(ADDRESS(ROW()-"+aa+",COLUMN()))), \"\",IF(ISERR(INDIRECT(F2"+plusOne+"&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-"+i+"))&\"!A1\")), \"\", SUMIF(INDIRECT(F2"+plusOne+"&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-"+bb+"))&\"!E:E\"), INDIRECT(ADDRESS(ROW()-"+aa+",COLUMN())), INDIRECT(F2"+plusOne+"&\"_\"&INDIRECT(ADDRESS(ROW(),COLUMN()-"+bb+"))&\"!C:C\"))))";
					
					c.setCellValue(sa);
					//System.out.println(commnad);
					rowVal++;
					
				}
				columnval++;
			}
			System.out.println("done");
			 
			// Writing the workbook to a file 
			try  {
				FileOutputStream fileOut = new FileOutputStream("C:/Users/asus/Downloads/TestingStuff/data.xlsx");
				workbook.write(fileOut); workbook.close(); 
				} 
			catch (IOException e) {
					e.printStackTrace(); 
				} 
			System.out.println("Excel file created successfully!");
		}
	

}
