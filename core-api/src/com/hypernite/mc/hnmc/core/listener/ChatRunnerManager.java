package com.hypernite.mc.hnmc.core.listener;

import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * @see com.hypernite.mc.hnmc.core.builders.MessageBuilder
 */
public interface ChatRunnerManager {

    void registerClicks(UUID id, Consumer<Player> runner, int clicks);

    void registerTimeout(UUID id, Consumer<Player> runner, int timeout);

}
