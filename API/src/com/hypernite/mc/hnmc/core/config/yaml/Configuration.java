package com.hypernite.mc.hnmc.core.config.yaml;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 用於創建 Config class 時繼承
 */
public abstract class Configuration {

    private FileConfiguration configuration;

    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
