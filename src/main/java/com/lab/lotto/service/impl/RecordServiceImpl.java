package com.lab.lotto.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.lotto.common.exception.ErrorCode;
import com.lab.lotto.common.exception.business.BusinessException;
import com.lab.lotto.dto.request.RecordQueryRequest;
import com.lab.lotto.entity.DrawFrequency;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.Record;
import com.lab.lotto.mapper.DrawFrequencyMapper;
import com.lab.lotto.mapper.PrizeMapper;
import com.lab.lotto.mapper.RecordMapper;
import com.lab.lotto.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;
    private final PrizeMapper prizeMapper;
    private final DrawFrequencyMapper drawFrequencyMapper;

    public RecordServiceImpl(RecordMapper recordMapper,
                           PrizeMapper prizeMapper,
                           DrawFrequencyMapper drawFrequencyMapper) {
        this.recordMapper = recordMapper;
        this.prizeMapper = prizeMapper;
        this.drawFrequencyMapper = drawFrequencyMapper;
    }

    @Override
    @Transactional
    public void createRecord(Record record) {
        // 检查抽奖频率
        checkDrawFrequency(record.getUid());
        // 检查奖品数量
        checkPrizeQuantity(record.getPid());
        // 创建抽奖记录
        recordMapper.insert(record);
        // 更新奖品数量
        updatePrizeQuantity(record.getPid());
        // 更新抽奖频率
        updateDrawFrequency(record.getUid());
    }

    @Override
    @Async("taskExecutor")
    @Transactional
    public void createRecordAsync(Record record) {
        try {
            createRecord(record);
        } catch (Exception e) {
            log.error("异步创建抽奖记录失败: {}", record.getRid(), e);
            throw new BusinessException(ErrorCode.BUSINESS_ERROR, "创建抽奖记录失败");
        }
    }

    @Override
    @Transactional
    public void batchCreateRecords(List<Record> records) {
        for (Record record : records) {
            try {
                createRecord(record);
            } catch (Exception e) {
                log.error("批量创建抽奖记录失败: {}", record.getRid(), e);
            }
        }
    }

    private void checkDrawFrequency(Integer uid) {
        DrawFrequency frequency = drawFrequencyMapper.selectLatestByUid(uid);
        if (frequency != null && frequency.getDrawCount() >= 10) {
            throw new BusinessException(ErrorCode.BUSINESS_ERROR, "超过抽奖次数限制");
        }
    }

    private void checkPrizeQuantity(Integer pid) {
        Prize prize = prizeMapper.selectById(pid);
        if (prize == null) {
            throw new BusinessException(ErrorCode.BUSINESS_ERROR, "奖品不存在");
        }
        if (prize.getPnowQ() <= 0) {
            throw new BusinessException(ErrorCode.BUSINESS_ERROR, "奖品已抽完");
        }
    }

    @Override
    @CacheEvict(value = "prize", key = "#pid")
    public void updatePrizeQuantity(Integer pid) {
        Prize prize = prizeMapper.selectById(pid);
        prize.setPnowQ(prize.getPnowQ() - 1);
        prize.setPupdateTime(LocalDateTime.now());
        prizeMapper.updateById(prize);
    }

    private void updateDrawFrequency(Integer uid) {
        DrawFrequency frequency = drawFrequencyMapper.selectLatestByUid(uid);
        if (frequency == null) {
            frequency = new DrawFrequency();
            frequency.setUid(uid);
            frequency.setDrawTime(LocalDateTime.now());
            frequency.setDrawCount(1);
            frequency.setTimeWindow(LocalDateTime.now().toString().substring(0, 10));
            frequency.setCreateTime(LocalDateTime.now());
            frequency.setUpdateTime(LocalDateTime.now());
            drawFrequencyMapper.insert(frequency);
        } else {
            frequency.setDrawCount(frequency.getDrawCount() + 1);
            frequency.setUpdateTime(LocalDateTime.now());
            drawFrequencyMapper.updateById(frequency);
        }
    }

    @Override
    public void updateRecord(Record record) {
        recordMapper.updateById(record);
    }

    @Override
    public void deleteRecord(Integer rid) {
        recordMapper.deleteById(rid);
        clearRecordCache(rid);
    }

    @Override
    public Record getRecord(Integer rid) {
        return recordMapper.selectById(rid);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordMapper.selectList(null);
    }

    @Override
    public List<Record> getRecordsByUser(Integer uid) {
        return recordMapper.selectList(new QueryWrapper<Record>().eq("uid", uid));
    }

    @Override
    public List<Record> getRecordsByPrize(Integer pid) {
        return recordMapper.selectList(new QueryWrapper<Record>().eq("pid", pid));
    }

    @Override
    public List<Record> getRecordsByType(Byte dtype) {
        return recordMapper.selectList(new QueryWrapper<Record>().eq("dtype", dtype));
    }

    @Override
    public List<Record> getRecordsByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return recordMapper.selectList(new QueryWrapper<Record>()
                .between("create_time", startTime, endTime));
    }

    @Override
    public List<Record> getRecordsByStatus(Byte dstatus) {
        return recordMapper.selectList(new QueryWrapper<Record>().eq("dstatus", dstatus));
    }

    @Override
    public List<Record> getRecordsByUserId(Integer uid) {
        return recordMapper.selectList(new QueryWrapper<Record>().eq("uid", uid));
    }

    @Override
    public List<Record> getRecordsByPrizeId(Integer pid) {
        return recordMapper.selectList(new QueryWrapper<Record>().eq("pid", pid));
    }

    @Override
    public boolean updateStatus(Integer rid, Byte dstatus) {
        Record record = new Record();
        record.setRid(rid);
        record.setDstatus(dstatus);
        record.setUpdateTime(LocalDateTime.now());
        return recordMapper.updateById(record) > 0;
    }

    @Override
    public boolean hasWon(Integer uid) {
        return recordMapper.selectCount(new QueryWrapper<Record>()
                .eq("uid", uid)
                .eq("dstatus", (byte) 1)) > 0;
    }

    @Override
    public IPage<Record> queryRecords(RecordQueryRequest request) {
        Page<Record> page = new Page<>(request.getPageNum(), request.getPageSize());
        return recordMapper.selectPage(page, buildQueryWrapper(request));
    }

    @Override
    public long countRecords(RecordQueryRequest request) {
        return recordMapper.selectCount(buildQueryWrapper(request));
    }

    private QueryWrapper<Record> buildQueryWrapper(RecordQueryRequest request) {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        if (request.getUid() != null) {
            wrapper.eq("uid", request.getUid());
        }
        if (request.getPid() != null) {
            wrapper.eq("pid", request.getPid());
        }
        if (request.getDtype() != null) {
            wrapper.eq("dtype", request.getDtype());
        }
        if (request.getDstatus() != null) {
            wrapper.eq("dstatus", request.getDstatus());
        }
        if (request.getStartTime() != null && request.getEndTime() != null) {
            wrapper.between("create_time", request.getStartTime(), request.getEndTime());
        }
        return wrapper;
    }

    @Override
    public void batchDeleteRecords(List<Integer> ids) {
        recordMapper.deleteBatchIds(ids);
        ids.forEach(this::clearRecordCache);
    }

    @Override
    public void updateRecordStatus(Integer id, Byte status) {
        Record record = new Record();
        record.setRid(id);
        record.setDstatus(status);
        record.setUpdateTime(LocalDateTime.now());
        recordMapper.updateById(record);
        clearRecordCache(id);
    }

    @Override
    public Map<String, Object> getStatisticsByUser(Integer uid) {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("total", recordMapper.selectCount(new QueryWrapper<Record>().eq("uid", uid)));
        statistics.put("won", recordMapper.selectCount(new QueryWrapper<Record>()
                .eq("uid", uid)
                .eq("dstatus", (byte) 1)));
        return statistics;
    }

    @Override
    public Map<String, Object> getStatisticsByPrize(Integer pid) {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("total", recordMapper.selectCount(new QueryWrapper<Record>().eq("pid", pid)));
        statistics.put("won", recordMapper.selectCount(new QueryWrapper<Record>()
                .eq("pid", pid)
                .eq("dstatus", (byte) 1)));
        return statistics;
    }

    @Override
    public Map<String, Object> getStatisticsByType(Byte dtype) {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("total", recordMapper.selectCount(new QueryWrapper<Record>().eq("dtype", dtype)));
        statistics.put("won", recordMapper.selectCount(new QueryWrapper<Record>()
                .eq("dtype", dtype)
                .eq("dstatus", (byte) 1)));
        return statistics;
    }

    @Override
    public Map<String, Object> getStatisticsByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        Map<String, Object> statistics = new HashMap<>();
        QueryWrapper<Record> wrapper = new QueryWrapper<Record>()
                .between("create_time", startTime, endTime);
        statistics.put("total", recordMapper.selectCount(wrapper));
        statistics.put("won", recordMapper.selectCount(wrapper.eq("dstatus", (byte) 1)));
        return statistics;
    }

    @Override
    public byte[] exportRecords(RecordQueryRequest request) {
        // TODO: 实现导出功能
        return new byte[0];
    }

    @Override
    public void clearRecordCache(Integer rid) {
        // TODO: 实现缓存清理
    }

    @Override
    public void clearUserRecordCache(Integer uid) {
        // TODO: 实现缓存清理
    }

    @Override
    public void clearPrizeRecordCache(Integer pid) {
        // TODO: 实现缓存清理
    }
} 