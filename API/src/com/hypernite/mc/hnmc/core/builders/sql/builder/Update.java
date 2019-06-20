package com.hypernite.mc.hnmc.core.builders.sql.builder;

import com.hypernite.mc.hnmc.core.builders.sql.SQLConversion;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL命令生成器
 * 更新資料
 */
public class Update {

    private Map<String, String> updates = new HashMap<String, String>(); // 欄位對應值清單
    private Map<String, Algorithm> algorithms = new HashMap<String, Algorithm>(); // 欄位對應運算法清單
    private String from = null;
    private String where = null;
    private Integer limit = null;
    private Integer offset = null;
    private Order order = null;


    /**
     * 對數據庫插入資料
     */
    public Update() {
    }


    /**
     * 生成SQL命令字串
     *
     * @return SQL命令
     * @throws NullPointerException 如果缺少必要的數值
     */
    public String sql() {
        StringBuilder sql = new StringBuilder();

        // 組合 from
        if (from == null) throw new NullPointerException("Table is null"); // 不能為空值
        sql.append("UPDATE `").append(SQLConversion.safety(from)).append("`");

        // 組合 updates
        String all_update = Insert.complieUpdates(updates, algorithms);

        if (all_update != null) {
            sql.append(" SET ").append(all_update);
        }

        // 組合 where
        if (where != null) {
            sql.append(" WHERE ").append(where);
        }

        // 組合 order
        if (order != null) {
            sql.append(" ").append(order.part());
        }

        // 組合 offset
        if (offset != null) {
            sql.append(" OFFSET ").append(offset.toString());
        }

        // 組合 limit
        if (limit != null) {
            sql.append(" LIMIT ").append(limit.toString());
        }

        sql.append(";");

        return sql.toString();
    }


    /**
     * 更新資料
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Update update(String field, String value) {
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, value);

        return this;
    }

    /**
     * 更新資料
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Update update(String field, Integer value) {
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, value.toString());

        return this;
    }

    /**
     * 更新資料
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Update update(String field, Boolean value) {
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, value.toString());

        return this;
    }

    /**
     * 更新資料
     *
     * @param field     欄位名稱
     * @param value     值
     * @param algorithm 運算法
     * @return 更新資料生成器
     */
    public Update update(String field, Integer value, Algorithm algorithm) {
        algorithms.put(field, algorithm); // 運算法
        updates.put(field, value.toString());

        return this;
    }

    /**
     * 更新資料
     * 依賴欄位,並相加原本數據
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Update addition(String field, Integer value) {
        algorithms.put(field, Algorithm.ADDITION); // +
        updates.put(field, value.toString());

        return this;
    }

    /**
     * 更新資料
     * 依賴欄位,並相減原本數據
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Update subtraction(String field, Integer value) {
        algorithms.put(field, Algorithm.SUBTRACTION); // -
        updates.put(field, value.toString());

        return this;
    }


    /**
     * 選擇資料表
     *
     * @param from 資料表名稱
     * @return 更新資料生成器
     */
    public Update from(String from) {
        this.from = from;
        return this;
    }


    /**
     * 判斷式
     *
     * @param where 判斷式
     * @return 更新資料生成器
     */
    public Update where(Where where) {
        this.where = where.part();
        return this;
    }

    /**
     * 括號
     *
     * @param brackets 括號
     * @return 更新資料生成器
     */
    public Update brackets(Brackets brackets) {
        this.where = brackets.part();
        return this;
    }


    /**
     * 限制查詢回傳行數上限
     *
     * @param limit 查詢回傳行數上限
     * @return 更新資料生成器
     */
    public Update limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 限制查詢回傳從第幾行開始
     *
     * @param offset 查詢回傳從第幾行開始
     * @return 更新資料生成器
     */
    public Update offset(int offset) {
        this.offset = offset;
        return this;
    }


    /**
     * 排序
     *
     * @param order 排序
     * @return 更新資料生成器
     */
    public Update order(Order order) {
        this.order = order;
        return this;
    }


}
