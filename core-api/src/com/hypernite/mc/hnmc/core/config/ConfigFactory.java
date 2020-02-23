package com.hypernite.mc.hnmc.core.config;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;
import com.hypernite.mc.hnmc.core.managers.YamlManager;

/**
 * Config 工廠
 */
public interface ConfigFactory {

    /**
     * 註冊
     *
     * @param yml         文件名稱
     * @param configClass 所屬文件的映射物件
     * @return this
     */
    ConfigFactory register(String yml, Class<? extends Configuration> configClass);

    /**
     * 註冊並使用 @Resource 定位文件位置
     *
     * @param configClass 所屬文件的映射物件
     * @return this
     */
    ConfigFactory register(Class<? extends Configuration> configClass);

    /**
     * @return 新的 Yaml 管理器 實例
     */
    YamlManager dump();


}
