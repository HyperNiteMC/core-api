package com.hypernite.mc.hnmc.core.factory;

import com.hypernite.mc.hnmc.core.config.ConfigFactory;

public interface CoreFactory {

    /**
     * @param className 連帶 package 的 Class 名稱
     * @return 反射工廠
     */
    ReflectionFactory getReflectionFactory(final String className);

    ConfigFactory getConfigFactory();

}
