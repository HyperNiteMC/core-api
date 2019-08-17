package com.hypernite.mc.hnmc.core.config;

/**
 * 獲取 Redis 的伺服器 資料
 */
public interface RedisData {

    /**
     * @return 返回IP
     */
    String getHost();

    /**
     *
     * @return 返回端口
     */
    int getPort();

    /**
     *
     * @return 返回過時值
     */
    int getTimeout();

    /**
     *
     * @return 是否使用密碼
     */
    boolean usePassword();

    /**
     *
     * @return 密碼
     */
    String getPassword();

}
