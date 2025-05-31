package com.lab.lotto.importexport.progress;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProgressTrackingService {
    private final Map<String, ProcessProgress> progressMap = new ConcurrentHashMap<>();

    public void initProgress(String taskId, int totalCount) {
        ProcessProgress progress = new ProcessProgress();
        progress.setTaskId(taskId);
        progress.setTotalCount(totalCount);
        progressMap.put(taskId, progress);
    }

    public void updateProgress(String taskId, int processedCount) {
        ProcessProgress progress = progressMap.get(taskId);
        if (progress != null) {
            progress.updateProgress(processedCount);
        }
    }

    public void completeProgress(String taskId) {
        ProcessProgress progress = progressMap.get(taskId);
        if (progress != null) {
            progress.complete();
        }
    }

    public void failProgress(String taskId, String message) {
        ProcessProgress progress = progressMap.get(taskId);
        if (progress != null) {
            progress.fail(message);
        }
    }

    public ProcessProgress getProgress(String taskId) {
        return progressMap.get(taskId);
    }

    public void removeProgress(String taskId) {
        progressMap.remove(taskId);
    }
} 