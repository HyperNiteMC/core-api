package com.hypernite.mc.hnmc.core.builders.sql.builder;

/**
 * SQL命令生成器
 * 選擇資料指定返回類型枚舉
 */
public enum Return {

    /**
     * 正常
     **/
    NOT(""),
    /**
     * 平均
     **/
    AVG("AVG"),
    /**
     * 總和
     **/
    SUM("SUM"),
    /**
     * 共有幾筆
     **/
    COUNT("COUNT"),
    /**
     * 最大值
     **/
    MAX("MAX"),
    /**
     * 最小值
     **/
    MIN("MIN"),
    /**
     * 標準差
     **/
    STDEV("STDEV"),
    /**
     * 變異數
     **/
    VAR("VAR");


    private String value;


    /**
     * 設定此枚舉的SQL字符串片段
     *
     * @param v SQL可拼湊字符串片段
     */
    Return(String v) {
        value = v;
    }


    /**
     * 取得此枚舉的SQL字符串片段
     *
     * @return SQL可拼湊字符串片段
     */
    public String getValue() {
        return value;
    }
}
