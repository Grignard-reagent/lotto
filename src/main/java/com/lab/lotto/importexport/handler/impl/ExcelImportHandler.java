package com.lab.lotto.importexport.handler.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lab.lotto.importexport.handler.ImportHandler;
import com.lab.lotto.importexport.model.ImportResult;
import com.lab.lotto.importexport.progress.ProgressTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExcelImportHandler implements ImportHandler {

    @Autowired
    private ProgressTrackingService progressTrackingService;

    @Override
    public ImportResult handle(MultipartFile file) {
        String taskId = UUID.randomUUID().toString();
        List<Map<String, String>> data = new ArrayList<>();
        List<Map<String, String>> errorDetails = new ArrayList<>();
        AtomicInteger totalCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        try {
            EasyExcel.read(file.getInputStream(), new AnalysisEventListener<Map<Integer, String>>() {
                private List<String> headers;

                @Override
                public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                    headers = new ArrayList<>(headMap.values());
                }

                @Override
                public void invoke(Map<Integer, String> rowData, AnalysisContext context) {
                    totalCount.incrementAndGet();
                    try {
                        Map<String, String> row = new HashMap<>();
                        for (int i = 0; i < headers.size(); i++) {
                            row.put(headers.get(i), rowData.getOrDefault(i, ""));
                        }
                        data.add(row);
                        progressTrackingService.updateProgress(taskId, totalCount.get());
                    } catch (Exception e) {
                        failCount.incrementAndGet();
                        Map<String, String> error = new HashMap<>();
                        error.put("row", String.valueOf(totalCount.get()));
                        error.put("error", e.getMessage());
                        errorDetails.add(error);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    progressTrackingService.completeProgress(taskId);
                }
            }).sheet().doRead();

            if (failCount.get() > 0) {
                return ImportResult.fail("部分数据导入失败", totalCount.get(), failCount.get(), errorDetails);
            }
            return ImportResult.success("导入成功", totalCount.get(), data);
        } catch (IOException e) {
            progressTrackingService.failProgress(taskId, e.getMessage());
            return ImportResult.fail("文件读取失败: " + e.getMessage(), totalCount.get(), failCount.get(), errorDetails);
        }
    }

    @Override
    public boolean supports(String fileType) {
        return "xlsx".equalsIgnoreCase(fileType) || "xls".equalsIgnoreCase(fileType);
    }
} 