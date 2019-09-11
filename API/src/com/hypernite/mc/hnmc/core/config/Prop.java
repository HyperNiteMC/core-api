package com.hypernite.mc.hnmc.core.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 創建 config class 時用於標註 變數的 路徑, 若果不輸入則使用 變數名稱 作為路徑。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Prop {
    String path() default "";
}
