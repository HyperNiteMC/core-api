package com.hypernite.mc.hnmc.core.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
public abstract class ConfigSetter {
    private Plugin plugin;
    private List<String> configs = new ArrayList<>();

    /**
     * @param plugin  插件
     * @param configs 連帶 .yml 的 配置文件名稱,
     */
    public ConfigSetter(Plugin plugin, String... configs) {
        this.plugin = plugin;
        this.configs.addAll(List.of(configs));
    }

    /**
     * @param ymls 已加載的 yaml Map
     */
    public abstract void loadConfig(Map<String, FileConfiguration> ymls);

    public Plugin getPlugin() {
        return plugin;
    }

    public List<String> getConfigs() {
        return configs;
    }
}
