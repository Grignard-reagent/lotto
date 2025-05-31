package com.lab.lotto.importexport.handler.impl;

import com.lab.lotto.importexport.handler.ImportHandler;
import com.lab.lotto.importexport.model.ImportResult;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVImportHandler implements ImportHandler {
    
    @Override
    public ImportResult handle(MultipartFile file) {
        List<Map<String, String>> data = new ArrayList<>();
        List<Map<String, String>> errorDetails = new ArrayList<>();
        int totalCount = 0;
        int failCount = 0;

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String[] headers = reader.readNext();
            if (headers == null) {
                return ImportResult.fail("文件为空", 0, 0, errorDetails);
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                totalCount++;
                try {
                    Map<String, String> row = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        row.put(headers[i], i < line.length ? line[i] : "");
                    }
                    data.add(row);
                } catch (Exception e) {
                    failCount++;
                    Map<String, String> error = new HashMap<>();
                    error.put("row", String.valueOf(totalCount));
                    error.put("error", e.getMessage());
                    errorDetails.add(error);
                }
            }
        } catch (IOException | CsvValidationException e) {
            return ImportResult.fail("文件读取失败: " + e.getMessage(), totalCount, failCount, errorDetails);
        }

        if (failCount > 0) {
            return ImportResult.fail("部分数据导入失败", totalCount, failCount, errorDetails);
        }
        return ImportResult.success("导入成功", totalCount, data);
    }

    @Override
    public boolean supports(String fileType) {
        return "csv".equalsIgnoreCase(fileType);
    }
} 