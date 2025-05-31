package com.lab.lotto.importexport.service;

import com.lab.lotto.importexport.model.ImportResult;
import org.springframework.web.multipart.MultipartFile;

public interface DataImportService {
    ImportResult importData(MultipartFile file);
} 