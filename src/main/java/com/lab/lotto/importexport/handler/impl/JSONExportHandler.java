package com.lab.lotto.importexport.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.lotto.importexport.handler.ExportHandler;
import com.lab.lotto.importexport.model.ExportResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class JSONExportHandler implements ExportHandler {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ExportResult handle(List<?> data, String fileName) {
        if (data == null || data.isEmpty()) {
            return ExportResult.fail("没有数据可导出");
        }

        try {
            File file = new File(fileName);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
            return ExportResult.success("导出成功", file);
        } catch (Exception e) {
            return ExportResult.fail("导出失败: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(String fileType) {
        return "json".equalsIgnoreCase(fileType);
    }
} 