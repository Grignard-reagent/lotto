-- 创建消息队列表
CREATE TABLE `message_queue` (
                                 `mid` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
                                 `mtype` varchar(50) NOT NULL COMMENT '消息类型',
                                 `mcontent` text NOT NULL COMMENT '消息内容',
                                 `mstatus` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-待处理，1-处理中，2-处理成功，3-处理失败',
                                 `mretry` int NOT NULL DEFAULT '0' COMMENT '重试次数',
                                 `create_time` datetime NOT NULL COMMENT '创建时间',
                                 `update_time` datetime NOT NULL COMMENT '更新时间',
                                 `next_retry_time` datetime DEFAULT NULL COMMENT '下次重试时间',
                                 `merror` varchar(500) DEFAULT NULL COMMENT '错误信息',
                                 PRIMARY KEY (`mid`),
                                 KEY `idx_mstatus` (`mstatus`),
                                 KEY `idx_create_time` (`create_time`),
                                 KEY `idx_next_retry_time` (`next_retry_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息队列表';

-- 创建消息处理日志表
CREATE TABLE `message_process_log` (
                                       `lid` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
                                       `mid` bigint NOT NULL COMMENT '消息ID',
                                       `lstatus` tinyint NOT NULL COMMENT '处理状态：0-失败，1-成功',
                                       `process_time` datetime NOT NULL COMMENT '处理时间',
                                       `lresult` varchar(500) DEFAULT NULL COMMENT '处理结果',
                                       `lerror` varchar(500) DEFAULT NULL COMMENT '错误信息',
                                       PRIMARY KEY (`lid`),
                                       KEY `idx_mid` (`mid`),
                                       KEY `idx_process_time` (`process_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息处理日志表';