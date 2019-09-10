package com.hypernite.mc.hnmc.core.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用於 {@link ConfigSetter}
 *
 * @author Eric Lam
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Extract {

    /**
     * 此名稱將作為 Variables Map 的 key, 若無 則返回 與 variable 相同的名稱。
     *
     * @return 名稱
     */
    String name() default "";
}
