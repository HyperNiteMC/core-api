package com.hypernite.mc.hnmc.core;

import com.hypernite.mc.hnmc.core.builders.function.ChatRunnerManager;
import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import com.hypernite.mc.hnmc.core.listener.ItemEventManager;
import com.hypernite.mc.hnmc.core.managers.*;

/**
 * Manager API 獲取器
 */
public interface HyperNiteMCAPI {

    BungeeManager getBungeeManager();

    ChatFormatManager getChatFormatManager();

    ChatRunnerManager getChatRunnerManager();

    ConfigManager registerConfig(ConfigSetter setter);

    CoreScheduler getCoreSchelder();

    CommandRegister getCommandRegister();

    NameTagManager getNameTagManager();

    PlayerSkinManager getPlayerSkinManager();

    SQLDataSource getSQLDataSource();

    TabListManager getTabListManager();

    ItemEventManager getItemEventManager();

    CoreConfig getCoreConfig();

    VaultAPI getVaultAPI();

}
