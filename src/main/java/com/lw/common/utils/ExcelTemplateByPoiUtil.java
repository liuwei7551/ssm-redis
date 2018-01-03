package com.lw.common.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

public class ExcelTemplateByPoiUtil {

    private static final int DATA_LINE_NUMBER = 50;

    private HSSFWorkbook book;

    private static HSSFCellStyle titleStyle = null;
    private static HSSFCellStyle smallTitleStyle = null;
    private static HSSFCellStyle dataStyle = null;

    public ExcelTemplateByPoiUtil() {
        book = new HSSFWorkbook();
        setTitleCellStyle(book);
        setSmallTitleCellStyle(book);
        setDataCellStyle(book);
    }

    private void setTitleCellStyle(HSSFWorkbook book) {
        titleStyle = book.createCellStyle();

        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        titleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont font = book.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        titleStyle.setFont(font);
    }

    private void setSmallTitleCellStyle(HSSFWorkbook book) {
        smallTitleStyle = book.createCellStyle();

        smallTitleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        smallTitleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        smallTitleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        smallTitleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        smallTitleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        smallTitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        smallTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont font = book.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);
        smallTitleStyle.setFont(font);
    }

    private void setDataCellStyle(HSSFWorkbook book) {
        dataStyle = book.createCellStyle();

        dataStyle.setFillForegroundColor(HSSFColor.WHITE.index);

        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont font = book.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        dataStyle.setFont(font);

        dataStyle.setWrapText(true);
    }

    private void createRow(HSSFRow row, List<String> data, String title) {
        if (null != data && !data.isEmpty()) {
            HSSFCell cell;
            for (int i = 0, size = data.size(); i < size; i++) {
                cell = row.createCell(i);
                cell.setCellValue(data.get(i));
            }
            Name name = book.createName();
            name.setNameName("townshipIds");
            StringBuffer sb = new StringBuffer(title);
            sb.append("!$A$1:$");
            sb.append(CellReference.convertNumToColString(data.size() - 1));
            sb.append("$1");
            name.setRefersToFormula(sb.toString());
        }
    }

    private int createRow(HSSFSheet sheet, List<List<String>> datas, int rowIndex, String title) {
        if (null != datas && !datas.isEmpty()) {
            for (List<String> data : datas) {
                if (null != data && !datas.isEmpty()) {
                    Name name;
                    HSSFCell cell;
                    HSSFRow row = sheet.createRow(rowIndex);
                    for (int i = 0, size = data.size(); i< size; i++) {
                        cell = row.createCell(i);
                        cell.setCellValue(data.get(i));
                    }
                    name = book.createName();
                    name.setNameName(data.get(0));
                    StringBuffer sb = new StringBuffer(title);
                    sb.append("!$B$");
                    sb.append(rowIndex + 1);
                    sb.append(":$");
                    sb.append(CellReference.convertNumToColString(data.size() - 1));
                    sb.append("$");
                    sb.append(rowIndex + 1);
                    name.setRefersToFormula(sb.toString());

                    rowIndex ++;
                }
            }
        }
        return rowIndex;
    }

    private void createHideSheetForTCG(HSSFWorkbook book, Map<String, Object> data, String title) {
        HSSFSheet sheet = book.createSheet(title);
        List<String> townshipIds = (List<String>) data.get("townshipIds");
        HSSFRow townshipRow = sheet.createRow(0);
        createRow(townshipRow, townshipIds, title);
        List<List<String>> communityIds = (List<List<String>>) data.get("communityIds");
        int lastRowIndex = createRow(sheet, communityIds, 1, title);
        List<List<String>> gridIds = (List<List<String>>) data.get("gridIds");
        createRow(sheet, gridIds, lastRowIndex, title);

        book.setSheetHidden(1, true);
    }

    public HttpServletResponse excelName(HttpServletResponse response, String fileName) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + handleFileName(fileName) + ".xls");
        return response;
    }

    private String handleFileName(String fileName) {
        try {
            return new String(fileName.getBytes("GBK"), "ISO-8859-1");
        } catch (Exception e) {
            return "";
        }
    }

    private void createMainRow(HSSFSheet sheet, int index, int col) {

        HSSFRow row = sheet.createRow(index);
        HSSFCell cell;
        cell = row.createCell(col);
        cell.setCellStyle(dataStyle);
        cell = row.createCell(col + 1);
        cell.setCellStyle(dataStyle);
        cell = row.createCell(col + 2);
        cell.setCellStyle(dataStyle);

        DVConstraint constraint;
        CellRangeAddressList addressList;
        DataValidation dataValidation;
        constraint = DVConstraint.createFormulaListConstraint("townshipIds");
        addressList = new CellRangeAddressList(index, index + DATA_LINE_NUMBER, col, col);
        dataValidation = new HSSFDataValidation(addressList, constraint);
        sheet.addValidationData(dataValidation);

        constraint = DVConstraint.createFormulaListConstraint("INDIRECT($"+CellReference.convertNumToColString(col) + (index - 1) + ")");
        addressList = new CellRangeAddressList(index, index + DATA_LINE_NUMBER, col + 1, col + 1);
        dataValidation = new HSSFDataValidation(addressList, constraint);
        sheet.addValidationData(dataValidation);

        constraint = DVConstraint.createFormulaListConstraint("INDIRECT($"+ CellReference.convertNumToColString(col + 1) + (index - 1) + ")");
        addressList = new CellRangeAddressList(index, index + DATA_LINE_NUMBER, col + 2, col + 2);
        dataValidation = new HSSFDataValidation(addressList, constraint);
        sheet.addValidationData(dataValidation);
    }

    private void setTitleLineInformation(HSSFSheet sheet, String title) {
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell cell = titleRow.createCell(0);
        cell.setCellStyle(titleStyle);
        cell.setCellValue(title);
    }

    private void setSmallTitleInformation(HSSFSheet sheet, List<String> smallTitles) {
        HSSFRow titleRow = sheet.createRow(1);
        HSSFCell cell;
        for (int i = 0, size = smallTitles.size(); i< size; i++) {
            sheet.setColumnWidth(i, 20 * 256);
            cell = titleRow.createCell(i);
            cell.setCellStyle(smallTitleStyle);
            cell.setCellValue(smallTitles.get(i));
        }
    }

    public HSSFWorkbook downloadExcelTemplate(Map<String, Object> data, String title) throws Exception{

        Map<String,Object> tcgData = (Map<String, Object>) data.get("tcg");
        List<String> smallTitles = (List<String>) data.get("smallTitles");

        HSSFSheet sheet = book.createSheet(title);
        createHideSheetForTCG(book, tcgData, "hideSheetForTCG");

        setTitleLineInformation(sheet, title);
        setSmallTitleInformation(sheet, smallTitles);
        createMainRow(sheet, 2,smallTitles.size() - 3);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,smallTitles.size() - 1));
        sheet.createFreezePane(0 ,2);

        return book;
    }

}