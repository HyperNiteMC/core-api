package com.hypernite.mc.hnmc.core.config.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.IOException;

/**
 * 用於創建 Config class 時繼承
 */
public abstract class Configuration {

    private FileConfiguration configuration;
    private File outputSource;

    /**
     * 獲取源文件
     *
     * @return 源文件
     */
    public FileConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * 保存源文件
     *
     * @return 成功
     */
    public boolean save() {
        Validate.notNull(configuration, "Configuration is null");
        Validate.notNull(outputSource, "OutputFile is null");
        try {
            configuration.save(outputSource);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
