package com.lab.lotto.importexport.handler.impl;

import com.lab.lotto.importexport.handler.ExportHandler;
import com.lab.lotto.importexport.model.ExportResult;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class CSVExportHandler implements ExportHandler {
    
    @Override
    public ExportResult handle(List<?> data, String fileName) {
        if (data == null || data.isEmpty()) {
            return ExportResult.fail("没有数据可导出");
        }

        File file = new File(fileName);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            // 写入表头
            Object firstObject = data.get(0);
            Field[] fields = firstObject.getClass().getDeclaredFields();
            String[] headers = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                headers[i] = fields[i].getName();
            }
            writer.writeNext(headers);

            // 写入数据
            for (Object obj : data) {
                String[] row = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(obj);
                    row[i] = value != null ? value.toString() : "";
                }
                writer.writeNext(row);
            }

            return ExportResult.success("导出成功", file);
        } catch (IOException | IllegalAccessException e) {
            return ExportResult.fail("导出失败: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(String fileType) {
        return "csv".equalsIgnoreCase(fileType);
    }
} 