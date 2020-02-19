package com.hypernite.mc.hnmc.core.config;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;

public interface FileController {

    <T extends Configuration> void save(T config);

    <T extends Configuration> void reload(T config);

}
