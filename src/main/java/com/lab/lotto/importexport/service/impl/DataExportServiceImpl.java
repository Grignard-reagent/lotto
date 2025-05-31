package com.lab.lotto.importexport.service.impl;

import com.lab.lotto.importexport.handler.ExportHandler;
import com.lab.lotto.importexport.model.ExportResult;
import com.lab.lotto.importexport.service.DataExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataExportServiceImpl implements DataExportService {

    @Autowired
    private List<ExportHandler> exportHandlers;

    @Override
    public ExportResult exportData(List<?> data, String fileName, String fileType) {
        if (data == null || data.isEmpty()) {
            return ExportResult.fail("没有数据可导出");
        }

        ExportHandler handler = exportHandlers.stream()
                .filter(h -> h.supports(fileType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("不支持的文件类型: " + fileType));

        return handler.handle(data, fileName);
    }
} 