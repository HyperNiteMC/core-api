package com.hypernite.mc.hnmc.core.managers;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

public interface YamlManager {

    boolean reloadConfigs();

    boolean reloadConfig(String yml);

    FileConfiguration getFileConfig(String yml);

    <T extends Configuration> T getConfig(String yml);

    <T extends Configuration> T getConfigAs(String yml, Class<T> type);
}
