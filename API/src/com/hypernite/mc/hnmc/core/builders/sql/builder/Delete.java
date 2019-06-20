package com.hypernite.mc.hnmc.core.builders.sql.builder;

import com.hypernite.mc.hnmc.core.builders.sql.SQL;
import com.hypernite.mc.hnmc.core.builders.sql.SQLConversion;

/**
 * SQL命令生成器
 * 刪除資料
 */
public class Delete {
    private String from = null;
    private String where = null;
    private Integer limit = null;
    private Integer offset = null;
    private Order order = null;


    /**
     * 刪除資料
     */
    public Delete() {
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
        sql.append("DELETE FROM `").append(SQLConversion.safety(from)).append("`");

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
     * 直接使用此SQL命令查詢數據庫,並返回結果控制庫
     *
     * @return SQL命令
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#update() ()
     * @see SQL#update(String)
     * @see SQL#U()
     * @see SQL#U(String)
     */
    public SQL sqlUpdate() {
        SQL sql = new SQL(sql());
        sql.update();

        return sql;
    }


    /**
     * 選擇資料表
     *
     * @param from 資料表名稱
     * @return 刪除資料生成器
     */
    public Delete from(String from) {
        this.from = from;
        return this;
    }


    /**
     * 判斷式
     *
     * @param where 判斷式
     * @return 刪除資料生成器
     */
    public Delete where(Where where) {
        this.where = where.part();
        return this;
    }

    /**
     * 括號
     *
     * @param brackets 括號
     * @return 刪除資料生成器
     */
    public Delete brackets(Brackets brackets) {
        this.where = brackets.part();
        return this;
    }


    /**
     * 限制查詢回傳行數上限
     *
     * @param limit 查詢回傳行數上限
     * @return 刪除資料生成器
     */
    public Delete limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 限制查詢回傳從第幾行開始
     *
     * @param offset 查詢回傳從第幾行開始
     * @return 刪除資料生成器
     */
    public Delete offset(int offset) {
        this.offset = offset;
        return this;
    }


    /**
     * 排序
     *
     * @param order 排序
     * @return 刪除資料生成器
     */
    public Delete order(Order order) {
        this.order = order;
        return this;
    }


}
