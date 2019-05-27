package com.hypernite.mc.hnmc.core.builders.function;

import java.util.UUID;

/**
 * @see com.hypernite.mc.hnmc.core.builders.MessageBuilder
 */
public interface ChatRunnerManager {

    void register(UUID id, ChatRunner runner);

    void register(UUID id, ChatRunner runner, int timeout);

}
