package com.lab.lotto.dto;

import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.Record;
import lombok.Data;

@Data
public class DrawResult {
    private boolean success;
    private String message;
    private Prize prize;
    private Record record;
    
    public static DrawResult success(Prize prize, Record record) {
        DrawResult result = new DrawResult();
        result.setSuccess(true);
        result.setPrize(prize);
        result.setRecord(record);
        return result;
    }
    
    public static DrawResult fail(String message) {
        DrawResult result = new DrawResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
} 