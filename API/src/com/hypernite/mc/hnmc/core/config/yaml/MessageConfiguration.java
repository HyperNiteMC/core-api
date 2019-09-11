package com.hypernite.mc.hnmc.core.config.yaml;


/**
 * 用於創建 訊息類別 config 時繼承
 */
public abstract class MessageConfiguration implements Configuration {

    private String prefix;

    /**
     * 若果 訊息類別 config 本身沒有 @Prefix, 返回的將是 null
     *
     * @return 前綴
     */
    public String getPrefix() {
        return prefix;
    }
}
