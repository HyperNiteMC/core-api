package com.hypernite.mc.hnmc.core.builders.function;

import java.util.UUID;

/**
 * @see com.hypernite.mc.hnmc.core.builders.MessageBuilder
 */
public interface ChatRunnerManager {

    void registerClicks(UUID id, ChatRunner runner, int clicks);

    void registerTimeout(UUID id, ChatRunner runner, int timeout);

}
