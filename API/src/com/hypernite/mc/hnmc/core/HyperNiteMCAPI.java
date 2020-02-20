package com.hypernite.mc.hnmc.core;

import com.hypernite.mc.hnmc.core.factory.CoreFactory;
import com.hypernite.mc.hnmc.core.listener.ChatRunnerManager;
import com.hypernite.mc.hnmc.core.listener.ItemEventManager;
import com.hypernite.mc.hnmc.core.managers.*;

/**
 * Manager API 獲取器
 */
public interface HyperNiteMCAPI {

    /**
     * @return Bungee 管理器
     */
    BungeeManager getBungeeManager();

    /**
     * @return 聊天格式管理器
     */
    ChatFormatManager getChatFormatManager();

    ChatRunnerManager getChatRunnerManager();

    /**
     *
     * @return 工廠
     */
    CoreFactory getFactory();

    /**
     *
     * @return api主導的排程器
     */
    CoreScheduler getCoreSchelder();

    /**
     *
     * @return 註冊指令
     */
    CommandRegister getCommandRegister();

    /**
     *
     * @return nameTag管理
     */
    NameTagManager getNameTagManager();

    /**
     *
     * @return 玩家皮膚管理器
     */
    PlayerSkinManager getPlayerSkinManager();

    /**
     * @return SQL 管理器
     */
    SQLDataSource getSQLDataSource();

    /**
     * @return Redis 管理器
     */
    RedisDataSource getRedisDataSource();

    /**
     * @return TabList 管理
     */
    TabListManager getTabListManager();

    ItemEventManager getItemEventManager();

    /**
     *
     * @return api 文件
     */
    CoreConfig getCoreConfig();

    /**
     *
     * @return vault 的 api
     */
    VaultAPI getVaultAPI();

    /**
     *
     * @return 世界管理
     */
    WorldManager getWorldManager();

}
