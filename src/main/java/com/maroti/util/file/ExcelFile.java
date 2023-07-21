package com.maroti.util.file;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import static com.maroti.util.file.ExcelFileLoader.*;

/*
 * @Author : Maroti Pawar
 * */
@FunctionalInterface
public interface ExcelFile {

    public Object[][] getData(String fileName, int sheetIndex);


    public static XSSFWorkbook workBook(String fileName) throws URISyntaxException, IOException {
        FileInputStream stream = load(fileName);
        return new XSSFWorkbook(stream);
    }

    public static XSSFSheet sheet(String fileName, int sheetIndex) throws URISyntaxException, IOException {
        XSSFWorkbook workbook = workBook(fileName);
        return workbook.getSheetAt(sheetIndex);
    }

    public static Object[][] sheetAsObjectArray(String fileName, int sheetIndex) throws URISyntaxException, IOException {

        XSSFSheet sheet = sheet(fileName, sheetIndex);
        int rowNum = sheet.getLastRowNum();
        int colNum = sheet.getRow(0).getLastCellNum();

        Object[][] obj = new Object[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < colNum; j++) {
                try {
                    if (row.getCell(j).toString() != null) {
                        obj[i][j] = row.getCell(j).toString();
                    } else {
                        obj[i][j] = " ";
                    }
                } catch (Exception e) {

                }
            }
        }
        return obj;
    }

    public static List<List<String>> sheetAsList(String fileName, int sheetIndex) throws URISyntaxException, IOException {
        XSSFSheet sheet = sheet(fileName, sheetIndex);
        List rowList = new ArrayList();

        int rowNum = sheet.getLastRowNum();
        for (int i = 0; i < rowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            List cellList = new ArrayList();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                try {
                    if (row.getCell(j).toString() != null) {
                        cellList.add(row.getCell(j).toString());
                    } else {
                        cellList.add(" ");
                    }
                } catch (Exception e) {

                }
            }
            rowList.add(cellList);
        }
        return rowList;
    }

    public static Map sheetAsMap(String fileName, int sheetIndex) throws URISyntaxException, IOException {
        XSSFSheet sheet = sheet(fileName, sheetIndex);
        int rowNum = sheet.getLastRowNum();
        Map map = new LinkedHashMap();
        for (int i = 0; i < rowNum; i++) {
            XSSFRow values = sheet.getRow(i);
            XSSFRow keys = sheet.getRow(0);
            for (int j = 0; j < values.getLastCellNum(); j++) {
                try {
                    if (values.getCell(j).toString() != null) {
                        map.put(keys.getCell(j), values.getCell(j));
                    }
                } catch (Exception e) {
                    map.put(" ", " ");
                }
            }
        }
        return map;
    }


}
