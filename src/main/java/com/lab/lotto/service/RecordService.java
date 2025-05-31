package com.lab.lotto.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lab.lotto.dto.request.RecordQueryRequest;
import com.lab.lotto.entity.Record;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface RecordService {
    void createRecord(Record record);
    void createRecordAsync(Record record);
    void batchCreateRecords(List<Record> records);
    void updateRecord(Record record);
    void deleteRecord(Integer rid);
    Record getRecord(Integer rid);
    List<Record> getAllRecords();
    List<Record> getRecordsByUser(Integer uid);
    List<Record> getRecordsByPrize(Integer pid);
    List<Record> getRecordsByType(Byte dtype);
    List<Record> getRecordsByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    List<Record> getRecordsByStatus(Byte dstatus);
    List<Record> getRecordsByUserId(Integer uid);
    List<Record> getRecordsByPrizeId(Integer pid);
    boolean updateStatus(Integer rid, Byte dstatus);
    boolean hasWon(Integer uid);
    IPage<Record> queryRecords(RecordQueryRequest request);
    long countRecords(RecordQueryRequest request);
    void batchDeleteRecords(List<Integer> ids);
    void updateRecordStatus(Integer id, Byte status);
    Map<String, Object> getStatisticsByUser(Integer uid);
    Map<String, Object> getStatisticsByPrize(Integer pid);
    Map<String, Object> getStatisticsByType(Byte dtype);
    Map<String, Object> getStatisticsByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    byte[] exportRecords(RecordQueryRequest request);
    void clearRecordCache(Integer rid);
    void clearUserRecordCache(Integer uid);
    void clearPrizeRecordCache(Integer pid);
    void updatePrizeQuantity(Integer pid);
} 