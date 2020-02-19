package com.hypernite.mc.hnmc.core.config.yaml;

import com.hypernite.mc.hnmc.core.config.FileController;

/**
 * 用於創建 Config class 時繼承
 */
public abstract class Configuration {

    private FileController controller;

    /**
     * 保存源文件
     */
    public void save() {
        controller.save(this);
    }

    public void reload() {
        controller.reload(this);
    }
}
