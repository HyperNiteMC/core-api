package com.hypernite.mc.hnmc.core.builders.function;

import org.bukkit.entity.Player;

/**
 * @see com.hypernite.mc.hnmc.core.builders.MessageBuilder#runClicks(int, ChatRunner)
 * @see com.hypernite.mc.hnmc.core.builders.MessageBuilder#run(ChatRunner)
 */
@FunctionalInterface
public interface ChatRunner {
    void run(Player player);
}
