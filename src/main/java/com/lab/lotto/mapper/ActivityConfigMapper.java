package com.lab.lotto.mapper;

import com.lab.lotto.entity.ActivityConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActivityConfigMapper {
    @Select("SELECT * FROM activity_config WHERE activity_id = #{activityId}")
    ActivityConfig selectByActivityId(@Param("activityId") Long activityId);
} 