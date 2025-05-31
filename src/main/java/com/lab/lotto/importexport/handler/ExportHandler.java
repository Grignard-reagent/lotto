package com.lab.lotto.importexport.handler;

import com.lab.lotto.importexport.model.ExportResult;
import java.util.List;

public interface ExportHandler {
    ExportResult handle(List<?> data, String fileName);
    boolean supports(String fileType);
} 