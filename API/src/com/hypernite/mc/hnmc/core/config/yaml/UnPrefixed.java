package com.hypernite.mc.hnmc.core.config.yaml;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 排除 前綴 模式，若果你本身并沒有為該父組件添加 @Prefixed, 將不會有任何作用。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface UnPrefixed {
}
