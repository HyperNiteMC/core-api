package com.hypernite.mc.hnmc.core.managers;

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
     * @return 資料庫文件
     */
    FileConfiguration getDataBase();
}
