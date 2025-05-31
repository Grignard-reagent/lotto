package com.lab.lotto.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lab.lotto.common.Result;
import com.lab.lotto.dto.request.RecordQueryRequest;
import com.lab.lotto.dto.response.RecordResponse;
import com.lab.lotto.entity.Record;
import com.lab.lotto.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 创建记录
     * @param record 记录信息
     * @return 创建的记录
     */
    @PostMapping
    public ResponseEntity<Void> createRecord(@RequestBody Record record) {
        recordService.createRecord(record);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新记录
     * @param rid 记录ID
     * @param record 记录信息
     * @return 更新后的记录
     */
    @PutMapping("/{rid}")
    public ResponseEntity<Void> updateRecord(@PathVariable Integer rid, @RequestBody Record record) {
        record.setRid(rid);
        recordService.updateRecord(record);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除记录
     * @param rid 记录ID
     * @return 操作结果
     */
    @DeleteMapping("/{rid}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer rid) {
        recordService.deleteRecord(rid);
        return ResponseEntity.ok().build();
    }

    /**
     * 获取记录
     * @param rid 记录ID
     * @return 记录信息
     */
    @GetMapping("/{rid}")
    public ResponseEntity<Record> getRecord(@PathVariable Integer rid) {
        Record record = recordService.getRecord(rid);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    /**
     * 获取所有记录
     * @return 记录列表
     */
    @GetMapping
    public ResponseEntity<IPage<Record>> queryRecords(RecordQueryRequest request) {
        IPage<Record> result = recordService.queryRecords(request);
        return ResponseEntity.ok(result);
    }

    /**
     * 根据用户ID获取记录
     * @param uid 用户ID
     * @return 记录列表
     */
    @GetMapping("/user/{uid}")
    public ResponseEntity<List<Record>> getRecordsByUser(@PathVariable Integer uid) {
        List<Record> records = recordService.getRecordsByUser(uid);
        return ResponseEntity.ok(records);
    }

    /**
     * 根据奖品ID获取记录
     * @param pid 奖品ID
     * @return 记录列表
     */
    @GetMapping("/prize/{pid}")
    public ResponseEntity<List<Record>> getRecordsByPrize(@PathVariable Integer pid) {
        List<Record> records = recordService.getRecordsByPrize(pid);
        return ResponseEntity.ok(records);
    }

    /**
     * 根据类型获取记录
     * @param dtype 类型
     * @return 记录列表
     */
    @GetMapping("/type/{dtype}")
    public ResponseEntity<List<Record>> getRecordsByType(@PathVariable Byte dtype) {
        List<Record> records = recordService.getRecordsByType(dtype);
        return ResponseEntity.ok(records);
    }

    /**
     * 根据时间范围获取记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 记录列表
     */
    @GetMapping("/time-range")
    public ResponseEntity<List<Record>> getRecordsByTimeRange(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        List<Record> records = recordService.getRecordsByTimeRange(startTime, endTime);
        return ResponseEntity.ok(records);
    }

    /**
     * 获取用户统计信息
     * @param uid 用户ID
     * @return 用户统计信息
     */
    @GetMapping("/statistics/user/{uid}")
    public ResponseEntity<Map<String, Object>> getStatisticsByUser(@PathVariable Integer uid) {
        Map<String, Object> statistics = recordService.getStatisticsByUser(uid);
        return ResponseEntity.ok(statistics);
    }

    /**
     * 获取奖品统计信息
     * @param pid 奖品ID
     * @return 奖品统计信息
     */
    @GetMapping("/statistics/prize/{pid}")
    public ResponseEntity<Map<String, Object>> getStatisticsByPrize(@PathVariable Integer pid) {
        Map<String, Object> statistics = recordService.getStatisticsByPrize(pid);
        return ResponseEntity.ok(statistics);
    }

    /**
     * 获取类型统计信息
     * @param dtype 类型
     * @return 类型统计信息
     */
    @GetMapping("/statistics/type/{dtype}")
    public ResponseEntity<Map<String, Object>> getStatisticsByType(@PathVariable Byte dtype) {
        Map<String, Object> statistics = recordService.getStatisticsByType(dtype);
        return ResponseEntity.ok(statistics);
    }

    /**
     * 获取时间范围统计信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 时间范围统计信息
     */
    @GetMapping("/statistics/time-range")
    public ResponseEntity<Map<String, Object>> getStatisticsByTimeRange(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<String, Object> statistics = recordService.getStatisticsByTimeRange(startTime, endTime);
        return ResponseEntity.ok(statistics);
    }

    /**
     * 导出记录
     * @param request 记录查询请求
     * @return 导出的记录数据
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportRecords(RecordQueryRequest request) {
        byte[] data = recordService.exportRecords(request);
        return ResponseEntity.ok(data);
    }
}