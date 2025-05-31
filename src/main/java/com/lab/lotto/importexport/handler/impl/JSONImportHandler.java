package com.lab.lotto.importexport.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@Component
public class JSONImportHandler implements ImportHandler {

    @Autowired
    private ProgressTrackingService progressTrackingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ImportResult handle(MultipartFile file) {
        String taskId = UUID.randomUUID().toString();
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String, String>> errorDetails = new ArrayList<>();
        int totalCount = 0;
        int failCount = 0;

        try {
            List<Map<String, Object>> jsonData = objectMapper.readValue(
                file.getInputStream(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class)
            );

            totalCount = jsonData.size();
            progressTrackingService.initProgress(taskId, totalCount);

            for (Map<String, Object> item : jsonData) {
                try {
                    data.add(item);
                    progressTrackingService.updateProgress(taskId, data.size());
                } catch (Exception e) {
                    failCount++;
                    Map<String, String> error = new HashMap<>();
                    error.put("row", String.valueOf(data.size() + 1));
                    error.put("error", e.getMessage());
                    errorDetails.add(error);
                }
            }

            progressTrackingService.completeProgress(taskId);

            if (failCount > 0) {
                return ImportResult.fail("部分数据导入失败", totalCount, failCount, errorDetails);
            }
            return ImportResult.success("导入成功", totalCount, data);
        } catch (IOException e) {
            progressTrackingService.failProgress(taskId, e.getMessage());
            return ImportResult.fail("文件读取失败: " + e.getMessage(), totalCount, failCount, errorDetails);
        }
    }

    @Override
    public boolean supports(String fileType) {
        return "json".equalsIgnoreCase(fileType);
    }
} 