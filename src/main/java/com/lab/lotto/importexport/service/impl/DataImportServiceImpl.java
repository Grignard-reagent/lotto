package com.lab.lotto.importexport.service.impl;

import com.lab.lotto.importexport.handler.ImportHandler;
import com.lab.lotto.importexport.model.ImportResult;
import com.lab.lotto.importexport.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DataImportServiceImpl implements DataImportService {

    @Autowired
    private List<ImportHandler> importHandlers;

    @Override
    public ImportResult importData(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return ImportResult.fail("文件名不能为空", 0, 0, null);
        }

        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        ImportHandler handler = importHandlers.stream()
                .filter(h -> h.supports(fileType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("不支持的文件类型: " + fileType));

        return handler.handle(file);
    }
} 