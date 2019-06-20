package com.hypernite.mc.hnmc.core.builders.sql.builder;

/**
 * SQL命令生成器
 * 更新資料運算法枚舉
 */
public enum Algorithm {

    /**
     * 等於
     **/
    EQUAL("="),
    /**
     * 加法
     **/
    ADDITION("+"),
    /**
     * 減法
     **/
    SUBTRACTION("-");


    private String value;


    /**
     * 設定此枚舉的SQL字符串片段
     *
     * @param v SQL可拼湊字符串片段
     */
    Algorithm(String v) {
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
