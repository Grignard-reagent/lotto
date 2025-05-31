package com.lab.lotto.importexport.handler.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.lab.lotto.importexport.handler.ExportHandler;
import com.lab.lotto.importexport.model.ExportResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ExcelExportHandler implements ExportHandler {
    
    @Override
    public ExportResult handle(List<?> data, String fileName) {
        if (data == null || data.isEmpty()) {
            return ExportResult.fail("没有数据可导出");
        }

        try {
            File file = new File(fileName);
            EasyExcel.write(file, data.get(0).getClass())
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet("Sheet1")
                    .doWrite(data);
            
            return ExportResult.success("导出成功", file);
        } catch (Exception e) {
            return ExportResult.fail("导出失败: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(String fileType) {
        return "xlsx".equalsIgnoreCase(fileType) || "xls".equalsIgnoreCase(fileType);
    }
} 