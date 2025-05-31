package com.lab.lotto.importexport.model;

import lombok.Data;
import java.io.File;

@Data
public class ExportResult {
    private boolean success;
    private String message;
    private File file;
    private String fileName;
    private long fileSize;

    public static ExportResult success(String message, File file) {
        ExportResult result = new ExportResult();
        result.setSuccess(true);
        result.setMessage(message);
        result.setFile(file);
        result.setFileName(file.getName());
        result.setFileSize(file.length());
        return result;
    }

    public static ExportResult fail(String message) {
        ExportResult result = new ExportResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
} 