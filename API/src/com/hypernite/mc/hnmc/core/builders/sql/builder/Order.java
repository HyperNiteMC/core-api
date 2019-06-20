package com.hypernite.mc.hnmc.core.builders.sql.builder;

import com.hypernite.mc.hnmc.core.builders.sql.SQLConversion;

/**
 * SQL命令生成器
 * 排序
 */
public class Order {

    private StringBuilder orders = new StringBuilder();


    public Order() {
    }


    /**
     * 轉成SQL可用於拼湊的字符串
     *
     * @return 可用於拼湊的字符串
     */
    public String part() {
        return "ORDER BY " + orders + "";
    }


    /**
     * 小到大
     * 少到多
     * 舊到新
     *
     * @param field 欄位
     * @return 排序
     */
    public Order increment(String field) {
        if (orders == null) {
            orders = new StringBuilder("`").append(SQLConversion.safety(field)).append("`");
        } else {
            orders.append(",`").append(SQLConversion.safety(field)).append("`");
        }
        return this;
    }


    /**
     * 大到小
     * 多到少
     * 新到舊
     *
     * @param field 欄位
     * @return 排序
     */
    public Order decrement(String field) {
        if (orders == null) {
            orders = new StringBuilder("`").append(SQLConversion.safety(field)).append("` DESC");
        } else {
            orders.append(",`").append(SQLConversion.safety(field)).append("` DESC");
        }
        return this;
    }


}
