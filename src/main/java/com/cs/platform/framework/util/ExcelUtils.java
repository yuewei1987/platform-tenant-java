package com.cs.platform.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * excel导出工具类
 */
public class ExcelUtils {
    protected static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    // 最大行数是65535，有一行是标题
    private static int maxRowIndex = 65534;

    public static boolean expExcel(HttpServletResponse response, String name, String[] headColName, String[] fieldColName, List<Map<String, Object>> data) {
        OutputStream ouputStream = null;
        try {
            String fileName = name;
            if (StringUtils.isEmpty(fileName)) {
                fileName = "新建文件";
            }
            ouputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            HSSFWorkbook wb = new HSSFWorkbook();
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));

            // 样式
            HSSFCellStyle style = wb.createCellStyle();
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // style.setWrapText(true);

            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("宋体");
            style.setFont(font);

            if (data != null) {
                createSheet(wb, 1, style, headColName, fieldColName, data);
            }
            wb.write(ouputStream);
        } catch (Exception e) {
            logger.error("导出出错了", e);
            return false;
        }
        return true;
    }

    private static void createSheet(HSSFWorkbook wb, int count, HSSFCellStyle style, String[] headColName, String[] fieldColName, List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        HSSFSheet sheet = wb.createSheet("Sheet" + count);
        sheet.setHorizontallyCenter(true);
        sheet.setDefaultColumnWidth(24);
        sheet.setDisplayGridlines(false);
        int rowIndex = 0;
        int cellIndex = 0;
        Row row;
        Cell cell;
        row = sheet.createRow(rowIndex);
        rowIndex++;
        for (String head : headColName) {
            cell = row.createCell(cellIndex);
            cellIndex++;
            cell.setCellValue(head);
            cell.setCellStyle(style);
        }

        int index = 0;
        while (!data.isEmpty()) {
            if (index >= maxRowIndex) {
                // 行超过65534时，递归调用，重新生成一个sheet
                int nextCount = count + 1;
                createSheet(wb, nextCount, style, headColName, fieldColName, data);
                return;
            }
            index++;
            Map<String, Object> map = data.remove(0);
            cellIndex = 0;
            row = sheet.createRow(rowIndex);
            rowIndex++;
            for (String colName : fieldColName) {
                cell = row.createCell(cellIndex);
                cellIndex++;
                cell.setCellValue(map.get(colName) != null ? map.get(colName).toString() : "");
                cell.setCellStyle(style);
            }
        }
    }

}
