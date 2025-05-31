package com.lab.lotto.importexport.controller;

import com.lab.lotto.importexport.model.ExportResult;
import com.lab.lotto.importexport.model.ImportResult;
import com.lab.lotto.importexport.progress.ProcessProgress;
import com.lab.lotto.importexport.progress.ProgressTrackingService;
import com.lab.lotto.importexport.service.DataExportService;
import com.lab.lotto.importexport.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/import-export")
public class DataImportExportController {

    @Autowired
    private DataImportService dataImportService;

    @Autowired
    private DataExportService dataExportService;

    @Autowired
    private ProgressTrackingService progressTrackingService;

    @PostMapping("/import")
    public ResponseEntity<ImportResult> importData(@RequestParam("file") MultipartFile file) {
        ImportResult result = dataImportService.importData(file);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/import/async")
    public ResponseEntity<String> importDataAsync(@RequestParam("file") MultipartFile file) {
        String taskId = UUID.randomUUID().toString();
        CompletableFuture.runAsync(() -> {
            try {
                dataImportService.importData(file);
            } catch (Exception e) {
                progressTrackingService.failProgress(taskId, e.getMessage());
            }
        });
        return ResponseEntity.ok(taskId);
    }

    @GetMapping("/import/progress/{taskId}")
    public ResponseEntity<ProcessProgress> getImportProgress(@PathVariable String taskId) {
        ProcessProgress progress = progressTrackingService.getProgress(taskId);
        return ResponseEntity.ok(progress);
    }

    @PostMapping("/export")
    public ResponseEntity<ExportResult> exportData(
            @RequestBody List<?> data,
            @RequestParam String fileName,
            @RequestParam String fileType) {
        ExportResult result = dataExportService.exportData(data, fileName, fileType);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/export/async")
    public ResponseEntity<String> exportDataAsync(
            @RequestBody List<?> data,
            @RequestParam String fileName,
            @RequestParam String fileType) {
        String taskId = UUID.randomUUID().toString();
        CompletableFuture.runAsync(() -> {
            try {
                dataExportService.exportData(data, fileName, fileType);
            } catch (Exception e) {
                progressTrackingService.failProgress(taskId, e.getMessage());
            }
        });
        return ResponseEntity.ok(taskId);
    }

    @GetMapping("/export/progress/{taskId}")
    public ResponseEntity<ProcessProgress> getExportProgress(@PathVariable String taskId) {
        ProcessProgress progress = progressTrackingService.getProgress(taskId);
        return ResponseEntity.ok(progress);
    }
} 