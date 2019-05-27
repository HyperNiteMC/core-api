package com.hypernite.mc.hnmc.core.listener;

/**
 * @see com.hypernite.mc.hnmc.core.builders.ItemStackBuilder#onClick(ItemEventAction)
 * @see com.hypernite.mc.hnmc.core.builders.ItemStackBuilder#onInteract(ItemEventAction)
 */
public interface ItemEventManager {

    void registerItem(String id, ItemEventAction action);

    void removeItem(String id);

}
