package com.hypernite.mc.hnmc.core.listener;

import org.bukkit.event.Event;

import java.util.function.Consumer;

/**
 * @see com.hypernite.mc.hnmc.core.builders.ItemStackBuilder
 */
public interface ItemEventManager {

    void registerItem(String id, Consumer<? extends Event> action);

    void removeItem(String id);

}
