package org.generatecsv;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;


public class XMLToCSVDynamicConverter {

    public static void main(String[] args) throws Exception {
        String xmlFilePath = "C:\\Users\\aashvinb\\Downloads\\input.xml";
        String excelFilePath = "C:\\Users\\aashvinb\\Downloads\\output.csv";

        try {
            List<Map<String, String>> tableData = parseXml(xmlFilePath);
            if (tableData.isEmpty()) {
                System.out.println("No Data Found");
                return;
            }

            Set<String> columnNames = extractColumnNames(tableData);
            writeExcel(excelFilePath, columnNames, tableData);
            System.out.println("Excel File created at : " + excelFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String,String>> parseXml(String filePath) throws Exception {
        List<Map<String,String>> data = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filePath));

        document.getDocumentElement().normalize();
        NodeList allNodes = document.getElementsByTagName("root");

        System.out.println("allNodes : " + allNodes);
        for(int i = 0; i < allNodes.getLength(); i++){
            Node node = allNodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                List<Map<String, String>> row = new ArrayList<>();
                extractNodeData(node , row ,"root", null);
                    data.addAll(row);
            }
        }
        System.out.println("data : " + data);
        return data;
    }

    private static void extractNodeData(Node node , List<Map<String,String>> row, String parentTag, String parentValue) {
        if(node.getNodeType() == Node.ELEMENT_NODE) {
            String key = node.getNodeName();

            if (node.hasChildNodes() && node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                String value = node.getTextContent().trim();
                Map<String, String> rowData = new LinkedHashMap<>();
                rowData.put(key, value);
                row.add(rowData);
            } else {
                String value = node.getTextContent().trim();
                if (parentValue == null && !value.isEmpty()) {
                    parentValue = value;
                }
                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    extractNodeData(children.item(i), row, key, parentValue);
                }
            }
        }
    }

    private static boolean isLeafNode(Node node) {
        return node.hasChildNodes() && node.getChildNodes().getLength() ==1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE;
    }

    private static Set<String> extractColumnNames(List<Map<String,String>> data) {
        Set<String> columnNames = new LinkedHashSet<>();
        for(Map<String,String> row : data) {
            columnNames.addAll(row.keySet());
        }
        return columnNames;
    }

    private static void writeExcel(String filePath , Set<String> columnNames, List<Map<String,String>> data) throws FileNotFoundException {
        System.out.println("data 22 :" + data);
        try(Workbook workbook =  new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");
            int rowNum = 0;

            Row headerRow = sheet.createRow(rowNum++);
            int headerCellNum = 0;
            for(String column : columnNames) {
                Cell cell = headerRow.createCell(headerCellNum++);
                cell.setCellValue(column);
            }
            for(Map<String,String> row : data) {
                Row excelRow = sheet.createRow(rowNum++);
                int cellNum = 0;
                for(String column : columnNames) {
                    Cell cell = excelRow.createCell(cellNum++);
                    cell.setCellValue(row.getOrDefault(column,""));
                }
            }
            for(int i = 0; i<columnNames.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            try(FileOutputStream fileOut =  new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}



