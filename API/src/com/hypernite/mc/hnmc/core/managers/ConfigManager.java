package com.hypernite.mc.hnmc.core.managers;

import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ConfigManager {

    /**
     * 設置訊息 yml
     *
     * @param config 訊息 yml 名稱
     */
    void setMsgConfig(String config);

    /**
     * @param config 訊息yml 名稱
     * @param prefix 前綴路徑
     */
    void setMsgConfig(String config, String prefix);

    /**
     * @param path 訊息路徑
     * @return 含 prefix 的訊息
     */
    String getMessage(String path);

    /**
     * @param path 訊息路徑
     * @return 不含prefix的訊息
     */
    String getPureMessage(String path);

    /**
     * @param path   StringList 訊息路徑
     * @param prefix 是否每行添加前綴
     * @return 字符串
     */
    String[] getMessageList(String path, boolean prefix);

    /**
     * @param config yml 名稱
     * @return 重載成功
     */
    boolean reloadConfig(String config);

    /**
     * @return 是否全部重載成功
     * @see #reloadConfig(String)
     */
    boolean reloadAllConfigs();

    /**
     * 獲取 yml
     *
     * @param config yml 名稱
     * @return yml
     */
    FileConfiguration getConfig(String config);

    /**
     * 獲取 Yml 設置器
     *
     * @return Yml 設置器
     * @see ConfigSetter
     */
    ConfigSetter getConfigSetter();

    /**
     * @param variable 變數名稱
     * @return 可能為 null 的變量
     */
    Optional<Object> getData(String variable);

    /**
     * @param variable 變數名稱
     * @param tClass   變數形態
     * @param <T>      形態
     * @return 可能為 null 的變量
     */
    <T> Optional<T> getData(String variable, Class<T> tClass);

    /**
     * @param variable 變數名稱
     * @param tClass   變數形態
     * @param <T>      形態
     * @return List
     */
    <T> List<T> getDataList(String variable, Class<T> tClass);

    /**
     * @param variable   變數名稱
     * @param keyClass   key 的形態
     * @param valueClass value 的形態
     * @param <K>        形態
     * @param <V>        形態
     * @return Map
     */
    <K, V> Map<K, V> getDataMap(String variable, Class<K> keyClass, Class<V> valueClass);
}
