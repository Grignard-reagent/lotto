package com.lab.lotto.service;

import com.lab.lotto.entity.Configs;
import java.util.List;

public interface ConfigsService {
    /**
     * 创建配置
     * @param configs 配置信息
     * @return 创建的配置
     */
    Configs createConfigs(Configs configs);

    /**
     * 更新配置
     * @param configs 配置信息
     * @return 更新后的配置
     */
    Configs updateConfigs(Configs configs);

    /**
     * 删除配置
     * @param cid 配置ID
     * @return 是否删除成功
     */
    boolean deleteConfigs(Integer cid);

    /**
     * 获取配置
     * @param cid 配置ID
     * @return 配置信息
     */
    Configs getConfigs(Integer cid);

    /**
     * 获取所有配置
     * @return 配置列表
     */
    List<Configs> getAllConfigs();

    /**
     * 根据名称获取配置
     * @param cname 配置名称
     * @return 配置信息
     */
    Configs getConfigsByName(String cname);

    /**
     * 根据状态获取配置
     * @param status 配置状态
     * @return 配置列表
     */
    List<Configs> getConfigsByStatus(Integer status);

    /**
     * 更新配置状态
     * @param cid 配置ID
     * @param status 状态
     * @return 是否更新成功
     */
    boolean updateConfigsStatus(Integer cid, Integer status);
}