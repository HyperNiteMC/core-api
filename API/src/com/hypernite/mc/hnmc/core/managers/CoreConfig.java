package com.hypernite.mc.hnmc.core.managers;

import com.hypernite.mc.hnmc.core.config.RedisData;
import org.bukkit.configuration.file.FileConfiguration;

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
     * @deprecated 即將過期
     * @return 資料庫文件
     */
    @Deprecated
    FileConfiguration getDataBase();

    /**
     * @return Redis 的資料
     */
    RedisData getRedisData();
}
