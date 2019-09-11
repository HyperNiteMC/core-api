package com.hypernite.mc.hnmc.core.config.yaml;

import java.lang.annotation.*;

/**
 * 設定該變數為帶有前綴的訊息
 * <p>
 * 若果你本身的 訊息文件 Class 不帶有前綴，則會使用默認 Core 的前綴
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Prefixed {
}
