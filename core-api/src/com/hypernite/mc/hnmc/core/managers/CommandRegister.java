package com.hypernite.mc.hnmc.core.managers;

import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public interface CommandRegister {

    /**
     * 註冊指令
     *
     * @param plugin 插件
     * @param node   指令節點
     * @see CommandNode
     */
    void registerCommand(@Nonnull JavaPlugin plugin, @Nonnull CommandNode node);

}
