package com.hypernite.mc.hnmc.core.builders.sql.builder;

/**
 * SQL命令生成器
 * 判斷式互相配合形式枚舉
 */
public enum Mutual {


    /**
     * 必須與另一判斷式同時符合
     **/
    AND("AND"),
    /**
     * 或是符合另一判斷式
     **/
    OR("OR");


    private String value;


    /**
     * 設定此枚舉的SQL字符串片段
     *
     * @param v SQL可拼湊字符串片段
     */
    Mutual(String v) {
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
