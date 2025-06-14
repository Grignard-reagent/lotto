# 抽奖系统（Lotto）

## 项目概述
本项目是一个基于 Spring Boot 的抽奖系统，提供用户管理、抽奖活动管理、奖品管理等功能。系统采用前后端分离架构，后端提供 RESTful API 接口，支持多种抽奖策略和奖品管理机制。

## 技术栈
- 后端框架：Spring Boot 3.1.0
- 数据库：MySQL 8.0.33
- 缓存：Redis
- ORM框架：MyBatis-Plus 3.5.3.1
- 安全框架：Spring Security + JWT
- 接口文档：Swagger 3.0.0
- 其他工具：
  - Lombok
  - Hutool
  - FastJSON2
  - EasyExcel
  - OpenCSV

## 系统架构
### 核心模块
- 用户管理模块
- 抽奖策略模块
- 奖品管理模块
- 抽奖记录模块
- 消息队列模块
- 系统配置模块

### 数据库设计
系统包含以下核心数据表：
1. user - 用户表
2. prize - 奖品表
3. drawstrategy - 抽奖策略表
4. strategyprize_cooperation - 策略奖品关联表
5. record - 抽奖记录表
6. messagequeue - 消息队列表
7. messageprocesslog - 消息处理日志表
8. configs - 配置表
9. blacklist - 黑名单表
10. drawfrequency - 抽奖频率表
11. system_config - 系统配置表

## 功能特性
### 1. 用户管理
- 用户注册与登录
- 角色权限控制（用户/管理员）
- 用户状态管理
- 黑名单机制

### 2. 抽奖功能
- 多种抽奖策略支持（权重抽奖、普通抽奖）
- 保底机制
- 抽奖频率限制
- 实时库存管理

### 3. 奖品管理
- 奖品CRUD操作
- 奖品类型管理
- 库存管理
- 概率设置

### 4. 数据管理
- 抽奖记录查询
- 数据导入导出（CSV/Excel）
- 基础统计报表
- 操作日志记录

### 5. 系统特性
- 分布式架构支持
- 缓存机制
- 消息队列
- 安全防护
- 并发控制

## 部署要求
### 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.x+
- Maven 3.x+

### 配置说明
1. 数据库配置
   - 数据库名：lottery_db
   - 端口：3306
   - 用户名：root
   - 密码：123456

2. 服务配置
   - 服务端口：8081
   - 接口文档：http://localhost:8081/swagger-ui/

## 项目特色
1. 灵活的抽奖策略
   - 支持多种抽奖算法
   - 可配置的保底机制
   - 动态概率调整

2. 完善的安全机制
   - JWT token认证
   - 角色权限控制
   - 黑名单机制
   - 抽奖频率限制

3. 高性能设计
   - Redis缓存支持
   - 消息队列处理
   - 数据库连接池优化
   - 并发控制机制

4. 便捷的数据管理
   - 多格式数据导入导出
   - 实时数据统计
   - 完整的操作日志
   - 灵活的配置管理

## 开发团队
谢子以
通信与信息工程学院
2024210152

## 注意事项
1. 生产环境部署前需要修改默认密码
2. 建议配置数据库定期备份
3. 需要定期清理过期的消息队列数据
4. 建议配置监控告警机制
