package com.example.updateactive.Utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoadExcel {

    String path = "C:\\Users\\low85\\Desktop\\Active_2022_Desktop.xlsx";

    File excelFile;
    int sheetNumber = 12;

    public LoadExcel(File file, int sheet) {
        this.sheetNumber = sheet;
        this.excelFile = file;
    }

    public int getDeptCode(int rowNumber){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            int lastRow = sheet.getLastRowNum();

            Row row;
            Cell cell = null;

            if(rowNumber <= lastRow){
                row = sheet.getRow(rowNumber);
                cell = row.getCell(7);
                //changeCellBackgroundColor(cell);
            }else{
                //do something here like a popup
            }
            assert cell != null;
            return (int) cell.getNumericCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getNameOfDept(int rowNumber){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            int lastRow = sheet.getLastRowNum();

            Row row;
            Cell cell = null;

            if(rowNumber <= lastRow){
                row = sheet.getRow(rowNumber);
                cell = row.getCell(8);
               // changeCellBackgroundColor(cell);
            }else{
                //do something here like a popup
            }
            assert cell != null;
            return cell.getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getEmployeeID(int rowNumber){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            int lastRow = sheet.getLastRowNum();

            Row row;
            Cell cell = null;

            if(rowNumber <= lastRow){
                row = sheet.getRow(rowNumber);
                cell = row.getCell(0);
            }else{
                //do something here like a popup
            }
            assert cell != null;
            return (int) cell.getNumericCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getEmployeeFirstName(int rowNumber){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            int lastRow = sheet.getLastRowNum();

            Row row;
            Cell cell = null;

            if(rowNumber <= lastRow){
                row = sheet.getRow(rowNumber);
                cell = row.getCell(3);
            }else{
                //do something here like a popup
            }
            assert cell != null;
            return cell.getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getEmployeeLastName(int rowNumber){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            int lastRow = sheet.getLastRowNum();

            Row row;
            Cell cell = null;

            if(rowNumber <= lastRow){
                row = sheet.getRow(rowNumber);
                cell = row.getCell(2);
            }else{
                //do something here like a popup
            }
            assert cell != null;
            return cell.getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeCellBackgroundColor(Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        if(cellStyle == null) {
            cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        }
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
    }

}
