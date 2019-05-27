package com.hypernite.mc.hnmc.core.managers;

import org.bukkit.entity.Player;

public interface NameTagManager {
    /**
     * 新增玩家 NameTag
     *
     * @param player 玩家
     */
    void addPlayer(Player player);

    /**
     * 更新 NameTag
     *
     * @param player 玩家
     */
    void updatePlayer(Player player);

}
