package com.lab.lotto.importexport.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ImportResult {
    private boolean success;
    private String message;
    private int totalCount;
    private int successCount;
    private int failCount;
    private List<Map<String, String>> errorDetails;
    private List<?> data;

    public static ImportResult success(String message, int totalCount, List<?> data) {
        ImportResult result = new ImportResult();
        result.setSuccess(true);
        result.setMessage(message);
        result.setTotalCount(totalCount);
        result.setSuccessCount(totalCount);
        result.setFailCount(0);
        result.setData(data);
        return result;
    }

    public static ImportResult fail(String message, int totalCount, int failCount, List<Map<String, String>> errorDetails) {
        ImportResult result = new ImportResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setTotalCount(totalCount);
        result.setSuccessCount(totalCount - failCount);
        result.setFailCount(failCount);
        result.setErrorDetails(errorDetails);
        return result;
    }
} 