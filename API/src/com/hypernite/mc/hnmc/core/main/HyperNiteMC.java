package com.hypernite.mc.hnmc.core.main;

import com.hypernite.mc.hnmc.core.HyperNiteMCAPI;
import com.hypernite.mc.hnmc.core.factory.CoreFactory;
import com.hypernite.mc.hnmc.core.listener.ChatRunnerManager;
import com.hypernite.mc.hnmc.core.listener.ItemEventManager;
import com.hypernite.mc.hnmc.core.managers.*;

/**
 * 從這裏獲取 API
 */
public class HyperNiteMC implements HyperNiteMCAPI {

    public static HyperNiteMCAPI getAPI() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public BungeeManager getBungeeManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public ChatFormatManager getChatFormatManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public ChatRunnerManager getChatRunnerManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public CoreFactory getFactory() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public CoreScheduler getCoreSchelder() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public CommandRegister getCommandRegister() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public NameTagManager getNameTagManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public PlayerSkinManager getPlayerSkinManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public SQLDataSource getSQLDataSource() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public TabListManager getTabListManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public ItemEventManager getItemEventManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public CoreConfig getCoreConfig() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public VaultAPI getVaultAPI() {
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Override
    public WorldManager getWorldManager() {
        throw new RuntimeException("RUNTIME ERROR");
    }
}
