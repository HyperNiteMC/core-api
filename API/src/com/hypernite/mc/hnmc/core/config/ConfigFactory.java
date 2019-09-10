package com.hypernite.mc.hnmc.core.config;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;
import com.hypernite.mc.hnmc.core.managers.YamlManager;

public interface ConfigFactory {

    ConfigFactory register(String yml, Class<? extends Configuration> configClass);

    YamlManager dump();


}
