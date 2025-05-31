package com.lab.lotto.importexport.handler;

import com.lab.lotto.importexport.model.ImportResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImportHandler {
    ImportResult handle(MultipartFile file);
    boolean supports(String fileType);
} 