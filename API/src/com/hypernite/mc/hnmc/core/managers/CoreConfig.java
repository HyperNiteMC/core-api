package com.hypernite.mc.hnmc.core.managers;

import com.hypernite.mc.hnmc.core.config.RedisData;

public interface CoreConfig {

    /**
     * @return 前綴
     */
    String getPrefix();

    /**
     * @return 無權限訊息
     */
    String getNoPerm();

    /**
     * @return 不是玩家訊息
     */
    String getNotPlayer();

    /**
     * @return 找不到玩家訊息
     */
    String getNotFoundPlayer();

    /**
     * @return Redis 的資料
     */
    RedisData getRedisData();
}
