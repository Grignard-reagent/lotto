package com.lab.lotto.importexport.progress;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProcessProgress {
    private String taskId;
    private int totalCount;
    private int processedCount;
    private String status;
    private String message;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double progress;

    public ProcessProgress() {
        this.startTime = LocalDateTime.now();
        this.status = "PENDING";
        this.progress = 0.0;
    }

    public void updateProgress(int processedCount) {
        this.processedCount = processedCount;
        if (totalCount > 0) {
            this.progress = (double) processedCount / totalCount * 100;
        }
    }

    public void complete() {
        this.status = "COMPLETED";
        this.endTime = LocalDateTime.now();
        this.progress = 100.0;
    }

    public void fail(String message) {
        this.status = "FAILED";
        this.message = message;
        this.endTime = LocalDateTime.now();
    }
} 