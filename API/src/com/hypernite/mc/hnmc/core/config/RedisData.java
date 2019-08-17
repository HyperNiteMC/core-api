package com.hypernite.mc.hnmc.core.config;

public interface RedisData {

    String getHost();

    int getPort();

    int getTimeout();

    boolean usePassword();

    String getPassword();

}
