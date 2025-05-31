package com.lab.lotto.importexport.service;

import com.lab.lotto.importexport.model.ExportResult;
import java.util.List;

public interface DataExportService {
    ExportResult exportData(List<?> data, String fileName, String fileType);
} 