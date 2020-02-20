package com.hypernite.mc.hnmc.core.config.yaml;

import com.hypernite.mc.hnmc.core.config.FileController;

import java.io.IOException;

/**
 * 用於創建 Config class 時繼承
 */
public abstract class Configuration {

    private FileController controller;

    /**
     * 保存文件
     */
    public void save() throws IOException {
        controller.save(this);
    }

    /**
     * 重載文件
     */
    public void reload() {
        controller.reload(this);
    }
}
