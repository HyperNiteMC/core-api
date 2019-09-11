package com.hypernite.mc.hnmc.core.config;

import java.lang.annotation.*;

/**
 * 標識該 Class 屬於 Config class 內的一種容器類型。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.TYPE_USE})
public @interface Component {
}
