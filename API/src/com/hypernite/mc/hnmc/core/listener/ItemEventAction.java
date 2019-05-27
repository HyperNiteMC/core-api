package com.hypernite.mc.hnmc.core.listener;

import org.bukkit.event.Event;

@FunctionalInterface
public interface ItemEventAction<E extends Event> {
    void onEvent(E e);
}
